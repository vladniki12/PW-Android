package com.smcorp.pw.view.main.history_transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.data.model.response.Transaction
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.setVerticalLlm
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.main.history_transactions.items.HistoryAdapter
import com.smcorp.pw.view.main.users_fragment.items.UsersAdapter
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_users.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class HistoryTransactionFragment: PWFragment(),HistoryTransactionView  {

    @InjectPresenter lateinit var mPresenter: HistoryTransactionPresenter

    override fun prepareView() {
        mPresenter.getHistory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.smcorp.pw.R.layout.fragment_history, container, false)
    }
    override fun onResume() {
        super.onResume()
        activity?.customToolbar?.setNavigationIcon(R.drawable.bar_back_white)
        activity?.toolbarTitle?.text = getString(R.string.history_title)


        activity?.customToolbar?.setNavigationOnClickListener {
            mPresenter.backOnClick()
        }

        historyRecyclerView.setVerticalLlm()

        historyRecyclerView.adapter = HistoryAdapter(arrayListOf(), context!!)


    }

    override fun showHistory(list: List<Transaction>) {
        historyRecyclerView.adapter = HistoryAdapter(list.reversed(), context!!)
    }

    class FragmentHistory: SupportAppScreen() {

        override fun getFragment(): Fragment {
            return HistoryTransactionFragment()
        }
    }

    override fun showError(id: Int) {
        showError("", getString(id))
    }

    override fun showError(message: String) {
        showError("", message )
    }

}
