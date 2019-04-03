package com.smcorp.pw.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.appComponent
import com.smcorp.pw.common.extension.makeStatusBarTransparent
import com.smcorp.pw.common.extension.replaceFragment
import com.smcorp.pw.common.mvp.ChainHolder
import com.smcorp.pw.common.mvp.PWActivity
import com.smcorp.pw.navigation.BaseActivityNavigator
import com.smcorp.pw.navigation.BaseFragmentNavigator
import com.smcorp.pw.navigation.Screens
import com.smcorp.pw.view.login.signin.SignInFragment
import com.smcorp.pw.view.login.signup.SignUpFragment
import kotlinx.android.synthetic.main.content_main_menu.view.*

import java.lang.ref.WeakReference
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Replace








class MainLoginActivity: PWActivity(), ChainHolder {


    override val chain = ArrayList<WeakReference<Fragment>>()


    private val navigator = object : SupportAppNavigator(this, R.id.rootActivityLayout) {
        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
           // printScreensScheme()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)


        navigator.applyCommands(arrayOf(Replace(FragmentSignIn())))

        makeStatusBarTransparent()

        replaceFragment(SignInFragment(),R.id.rootActivityLayout)
        //replaceFragment(SignInFr)
    }

    override fun onResume() {
        super.onResume()
        mNavigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }

    class FragmentSignIn: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SignInFragment()
        }
    }

//    private val navigator = object : BaseFragmentNavigator(this,1) {
//
//        override fun createIntent(screenKey: String, data: Any?): Fragment = when (screenKey) {
//            Screens.LoginFragment.START_REGISTRATION_SCREEN-> {
//                SignUpFragment()
//            }
//            /*Screens.Activities.SIGN_UP_MAIN_ACTIVITY -> newIntent<MainSignupActivity>(this@MainLoginActivity)
//            Screens.Activities.FORGOT_PASWORD_MAIN_ACTIVITY -> newIntent<MainForgotPasswordActivity>(this@MainLoginActivity)
//            Screens.Activities.LOGIN_SOCIAL_MAIN_ACTIVITY -> {
//                val intent = newIntent<MainLoginSocialActivity>(this@MainLoginActivity)
//                val passData = data as? MainLoginSocialActivityData
//                passData?.let {
//                    val providerKey = Constants.Login.LoginSocial.SOCIAL_PROVIDER_MAP_KEY
//                    val mailKey = Constants.Login.LoginSocial.EMAIL_MAP_KEY
//                    val tokenKey = Constants.Login.LoginSocial.SOCIAL_TOKEN_MAP_KEY
//                    intent.putExtra(providerKey, it.provider)
//                    intent.putExtra(mailKey, it.mail)
//                    intent.putExtra(tokenKey, it.token)
//                }
//                intent
//            }
//            Screens.Activities.NAV_MENU_MAIN_ACTIVITY -> {
//                val intent = newIntent<MainMenuActivity>(this@MainLoginActivity)
//                val isProfileFilled = data as? Boolean
//                intent.putExtra(Constants.EnterApp.IS_PROFILE_FILLED_KEY, isProfileFilled)
//            }*/
//            else -> throw IllegalArgumentException("there is no activity for $screenKey")
//        }
//    }


}