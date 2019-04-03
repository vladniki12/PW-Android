package com.smcorp.pw.view.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.login.signup.SignUpFragment
import com.smcorp.pw.view.login.signup.SignUpView
import com.smcorp.pw.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment: PWFragment(), SignInView {

    @InjectPresenter
    lateinit var mPresenter: SignInPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onResume() {
        super.onResume()
        editTextEmail.text.clear()
        editTextPassword.text.clear()
    }


    override fun prepareView() {
        btnSignUp.onClick {
            mPresenter.signUp()
        }

        btnSignIn.onClick {
            mPresenter.signIn(editTextEmail.text.toString(), editTextPassword.text.toString())
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
        showError("", message)
    }

}