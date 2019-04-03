package com.smcorp.pw.view.main.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.data.model.response.UserInfoToken
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.login.MainLoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import java.util.concurrent.TimeUnit

class MainScreenFragment: PWFragment(), MainScreenView {


    @InjectPresenter
    lateinit var mPresenter: MainScreenPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(com.smcorp.pw.R.layout.fragment_main_screen, container, false)
    }

    override fun onStart() {
        super.onStart()


        activity?.customToolbar?.navigationIcon = null



        activity?.toolbarTitle?.text = getString(R.string.main_screen_title)
        //activity?.toolbarTitle?.text
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.action_bar_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_exit) {
            mPresenter.exit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun prepareView() {

        btnCreateTransaction.onClick {
            mPresenter.openCreateTransaction()
        }

        btnHistory.onClick {
            mPresenter.openHistory()
        }
    }



    override fun onResume() {
        super.onResume()
        mPresenter.getInfo()
    }

    override fun updateInfo(userInfo: UserInfoToken) {
        textViewBalance.text = getString(R.string.balance_title) + " " +userInfo.balance.toString()
        textViewUsername.text = getString(R.string.username_title) + " " + userInfo.name
        textViewEmail.text = getString(R.string.email_title) + " " + userInfo.email
        textViewId.text = getString(R.string.id_title) + " " + userInfo.id
    }

    override fun showError(id: Int) {
        showError("", getString(id))
    }

    override fun showError(message: String) {
        showError("", message )
    }

    override fun openLoginScreen() {
        val intent = Intent(context, MainLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}