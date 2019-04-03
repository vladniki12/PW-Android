package com.smcorp.pw.view.login.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*


class SignUpFragment: PWFragment(), SignUpView {


    @InjectPresenter
    lateinit var mPresenter: SignUpPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.smcorp.pw.R.layout.fragment_signup, container, false)
    }


    override fun onResume() {
        super.onResume()

    }

    override fun prepareView() {

        btnSignUp.onClick {
            mPresenter.signUp(editTextEmail.text.toString(),
                              editTextUserName.text.toString(),
                              editTextPassword.text.toString(),editTextConfirmPassword.text.toString())
        }

        btnSignIn.onClick {
            mPresenter.backOnClick()
        }


    }

    override fun openMainScreen() {
        val intent = Intent(context, MainActivity::class.java)

        startActivity(intent)
    }

    override fun showError(id: Int) {
        showError("", getString(id))
    }

    override fun showError(message: String) {
        showError("", message )
    }

}