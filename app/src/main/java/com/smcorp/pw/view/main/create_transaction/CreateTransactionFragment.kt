package com.smcorp.pw.view.main.create_transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.domain.repository.TransactionRepository
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.onClick
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.main.MainActivity
import com.smcorp.pw.view.main.history_transactions.HistoryTransactionFragment
import com.smcorp.pw.view.main.main_screen.MainScreenPresenter
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.fragment_create_transaction.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CreateTransactionFragment: PWFragment(), CreateTransactionView {

    @InjectPresenter
    lateinit var mPresenter: CreateTransactionPresenter





    private val userNameResponseKey = "user_name"

    companion object {
        fun newInstance(userName: String): CreateTransactionFragment {
            val fragment = CreateTransactionFragment()
            val args = Bundle()
            args.putSerializable(fragment.userNameResponseKey, userName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.smcorp.pw.R.layout.fragment_create_transaction, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.customToolbar?.setNavigationIcon(R.drawable.bar_back_white)
        activity?.toolbarTitle?.text = getString(R.string.create_transaction_title)


        activity?.customToolbar?.setNavigationOnClickListener {
            mPresenter.backOnClick()
        }
    }

    override fun prepareView() {


        btnCreateTransaction.onClick {
            mPresenter.createTransaction(editTextAmount.text.toString())
        }

        mPresenter.userName =
            arguments?.getSerializable(userNameResponseKey) as String

        textViewToUserName.text = getString(R.string.to_username_title) + mPresenter.userName
        //to_username_title
    }

    class FragmentCreateTransaction(var userName: String): SupportAppScreen() {

        override fun getFragment(): Fragment {
            return CreateTransactionFragment.newInstance(userName)
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