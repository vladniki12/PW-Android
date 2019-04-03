package com.smcorp.pw.common.mvp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.kaopiz.kprogresshud.KProgressHUD
import com.smcorp.pw.common.UI.AlertDialogFragment
import com.smcorp.pw.common.di.component.DaggerFragmentComponent
import com.smcorp.pw.common.di.component.FragmentComponent
import com.smcorp.pw.common.view.PreparableView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


abstract class PWFragment: MvpAppCompatFragment(), PreparableView {

    private val progressDelay: Long = 800
    private val disposableListOnDestroy = CompositeDisposable()
    private val disposableListOnStop = CompositeDisposable()
    private val disposableListOnDestroyView = CompositeDisposable()
    lateinit var fragmentComponent: FragmentComponent
    private var loadingIndicatorDialog: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        fragmentComponent = DaggerFragmentComponent.create()
        daggerInjection()
    }

    /*
     * Override to inject presenter
     */
    open fun daggerInjection() {
        fragmentComponent.inject(this)
    }

    /*
     * Override to handle cancel button click of LoadingIndicatorActivity
     */

    open fun loadingCancelClicked() {}

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(arg0: Context, intent: Intent) {
//            val action = intent.action
//            if (action == Constants.LoadingIndicatorActivity.ON_CANCEL_LOADING_CLICKED_COMMAND) {
//                loadingCancelClicked()
//            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadingIndicatorDialog =
            KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false)
                .setAnimationSpeed(2).setDimAmount(0.5f)

        prepareView()

    }

    override fun onDestroy() {
        loadingIndicatorDialog?.dismiss()
        disposableListOnDestroy.clear()
        super.onDestroy()
        super.onPause()
    }

    override fun onDestroyView() {
        disposableListOnDestroyView.clear()
        super.onDestroyView()
    }

    override fun onStop() {
        disposableListOnStop.clear()
        super.onStop()
    }

    fun Disposable.disposeOnDestroy() {
        disposableListOnDestroy.add(this)
    }

    fun Disposable.disposeOnStop() {
        disposableListOnStop.add(this)
    }

    fun Disposable.disposeOnDestroyView() {
        disposableListOnDestroyView.add(this)
    }


    /*
        Loading indicator activity
     */

    fun showLoadingIndicatorActivity(title: String? = "") {
        context?.also { _ ->
            loadingIndicatorDialog?.dismiss()

            if (!title.isNullOrEmpty()) {

                loadingIndicatorDialog?.setLabel(title) // =  KProgressHUD.create(context).setLabel(title).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f)

            }
            loadingIndicatorDialog?.show()
        }

    }

    fun hideLoadingIndicatorActivity(delay: Long = progressDelay) {
        //delay before hide progress bar
        if (loadingIndicatorDialog?.isShowing == false) return

        Handler().postDelayed({
            loadingIndicatorDialog?.dismiss()
            loadingIndicatorDialog?.setLabel("")

        }, delay)
    }

    fun showError(title: String, message: String) {
        val dialogFragment = AlertDialogFragment()
        dialogFragment.setMessage(message)
        dialogFragment.setTitle("")
        dialogFragment.setVisibleRightBtn(false)
        dialogFragment.succesFun = {

        }

        dialogFragment.show(fragmentManager, dialogFragment.fragmentTag)
    }


}
