package com.smcorp.pw.common.mvp

import android.content.BroadcastReceiver
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.smcorp.pw.common.UI.AlertDialogFragment
import com.smcorp.pw.common.extension.appComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


abstract class PWActivity: MvpAppCompatActivity() {

    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    private var broadcastReceiver: BroadcastReceiver? = null

    private val disposableListOnDestroyView = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInjection()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //checkDrawOverlayPermission()
        }
    }

    open fun daggerInjection() {
        appComponent()?.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    //  views
    fun setCustomTopBarWithBackButton(toolbarId: Int, titleId: Int) {
        // set title
        val titleTextView = findViewById<TextView>(titleId)
        titleTextView.text = title

        val toolbar = findViewById<Toolbar>(toolbarId)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.bar_back_white)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    override fun onResume() {
        super.onResume()
    }




    override fun onDestroy() {
        super.onDestroy()
        disposableListOnDestroyView.clear()

    }

    fun Disposable.disposeOnDestroyView() {
        disposableListOnDestroyView.add(this)
    }

    fun showError(title: String, message: String) {
            val dialogFragment = AlertDialogFragment()
            dialogFragment.setMessage(message)
            dialogFragment.setTitle("")
            dialogFragment.setTextLeftButton(title)
            dialogFragment.setVisibleRightBtn(false)
            dialogFragment.succesFun = {

            }

            dialogFragment.show(supportFragmentManager, dialogFragment.fragmentTag)
    }




}