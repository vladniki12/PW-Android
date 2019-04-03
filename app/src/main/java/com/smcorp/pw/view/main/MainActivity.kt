package com.smcorp.pw.view.main

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.makeStatusBarTransparent
import com.smcorp.pw.common.extension.replaceFragment
import com.smcorp.pw.common.mvp.ChainHolder
import com.smcorp.pw.common.mvp.PWActivity
import com.smcorp.pw.common.view.PreparableView
import com.smcorp.pw.view.login.signin.SignInFragment
import com.smcorp.pw.view.main.main_screen.MainScreenFragment
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import java.lang.ref.WeakReference


class MainActivity: PWActivity(), ChainHolder, PreparableView {


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
        setContentView(R.layout.activity_main )


        navigator.applyCommands(arrayOf(Replace(FragmentMainScreen())))

        makeStatusBarTransparent()
        setSupportActionBar(customToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        prepareView()
        //setToolbarTitle("asdasdasd")
        //replaceFragment(MainScreenFragment(), R.id.rootActivityLayout)
        //replaceFragment(SignInFr)
    }


    override fun prepareView() {
        main_appbar.visibility = View.VISIBLE

        marginTopContentMain(true)
    }

    override fun onResume() {
        super.onResume()
        mNavigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }

    class FragmentMainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainScreenFragment()
        }
    }

    fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
    }


    fun marginTopContentMain(toolbar: Boolean){
        val sizeToolbar: Float = if (toolbar) 80f else 0f
        setMarginTop((convertDpToPx(sizeToolbar) - getStatusBarHeight()), // 80dp - size toolbar
            inc_content_main)
    }
    fun convertDpToPx(dp : Float) : Int {

        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun getStatusBarHeight(): Int {
        val height: Int
        val myResources = resources
        val idStatusBarHeight = myResources.getIdentifier(
            "status_bar_height", "dimen", "android")
        if (idStatusBarHeight > 0) {
            height = resources.getDimensionPixelSize(idStatusBarHeight)
        } else {
            height = 0
        }

        return height
    }


    fun setMarginTop(px: Int, view: View) {
        val p = view.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(0, px, 0, 0)
        view.layoutParams = p
    }
}


