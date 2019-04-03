package com.smcorp.pw.common.extension

import android.text.SpannableString
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smcorp.pw.common.di.component.AppComponent
import com.smcorp.pw.common.mvp.PWActivity
import com.smcorp.pw.view.PWApplication


fun AppCompatActivity.makeStatusBarTransparent() {
    // make status bar transparent
    val window = window
    val winParams = window.attributes
    val flagTransStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
    winParams.flags = winParams.flags.and(flagTransStatus)
    window.attributes = winParams
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

fun AppCompatActivity.showAlertDialog(message: String, title: String = "") {

}

fun AppCompatActivity.showSpannableAlertDialog(message: SpannableString) {

}

fun AppCompatActivity.appComponent(): AppComponent? = PWApplication.appComponent

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).commit()
}