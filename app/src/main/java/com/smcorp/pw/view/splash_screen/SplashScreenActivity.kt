package com.smcorp.pw.view.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.appComponent
import com.smcorp.pw.view.login.MainLoginActivity
import com.smcorp.pw.view.main.MainActivity

class SplashScreenActivity: MvpAppCompatActivity(),SplashScreenView {

    @InjectPresenter
    lateinit var mPresenter: SplashScreenPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        daggerInjection()


    }


    private fun daggerInjection() {
        appComponent()?.inject(mPresenter)
    }

    override fun enterApplication(isAuth: Boolean) {
        if(!isAuth) {
            val intent = Intent(this, MainLoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}