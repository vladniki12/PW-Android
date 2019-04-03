package com.smcorp.pw.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.smcorp.pw.common.extension.showAlertDialog
import com.smcorp.pw.common.extension.showSpannableAlertDialog
import com.smcorp.pw.common.mvp.PWActivity
import com.smcorp.pw.view.PWApplication
import es.dmoral.toasty.Toasty
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command


abstract class BaseFragmentNavigator(
    private val activity: PWActivity,
    localContainerId: Int) : SupportAppNavigator(activity, localContainerId) {

    protected val context = activity as Context
    protected val localFragmentManager = activity.supportFragmentManager!!

    override fun applyCommand(command: Command?) {


        activity.currentFocus?.let {
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it.windowToken, 0)
        }
        when (command) {
            is ToastCommand -> when (command) {
                is InfoToastCommand -> Toasty.info(activity, command.message, Toast.LENGTH_LONG).show()
                is WarningToastCommand -> Toasty.warning(activity, command.message, Toast.LENGTH_LONG).show()
                is SuccessToastCommand -> Toasty.success(activity, command.message, Toast.LENGTH_LONG).show()
                is ErrorToastCommand -> Toasty.error(activity, command.message, Toast.LENGTH_LONG).show()
            }
            is DialogCommand -> activity.showAlertDialog(command.message, command.title)
            is SpannableDialogCommand -> activity.showSpannableAlertDialog(command.message)
            is ExternalCommand -> when (command) {
                is UriViewCommand -> activity.startActivity(Intent(Intent.ACTION_VIEW, command.uri))
            }
            is NewRootActivityCommand -> {
                startNewActivity(command)
                activity.finishAffinity()
            }
            is StartActivityCommand -> startNewActivity(command)
            else -> super.applyCommand(command)
        }
    }

    private fun startNewActivity(command: ActivityCommand, options: Bundle? = null) {
       /* val intent = createIntent(command.screenKey, command.data)
        if (intent == null) {


            return
        }
        ContextCompat.startActivity(activity, intent, options)*/
    }

    /**
     * Creates Activity matching `screenKey`.
     *
     * @param screenKey screen key
     * @param data      initialization data
     * @return instantiated fragment for the passed screen key
     */
    open fun createIntent(screenKey: String, data: Any?): Fragment? = null


    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
        fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }





}