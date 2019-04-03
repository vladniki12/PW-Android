package com.smcorp.pw.view.main.users_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.data.model.response.UsersResponse
import com.smcorp.pw.R
import com.smcorp.pw.common.extension.setVerticalLlm
import com.smcorp.pw.common.mvp.PWFragment
import com.smcorp.pw.view.main.users_fragment.items.UsersAdapter
import com.smcorp.pw.view.main.users_fragment.items.UsersModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.fragment_users.*
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.util.concurrent.TimeUnit

class UsersFragment: PWFragment(),UsersFragmentView {

    @InjectPresenter lateinit var mPresenter: UsersFragmentPresenter

    private var menu: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(com.smcorp.pw.R.layout.fragment_users, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.customToolbar?.setNavigationIcon(R.drawable.bar_back_white)
        activity?.toolbarTitle?.text = getString(R.string.users_title)

        if(!mPresenter.mSearchString.isNullOrEmpty()) {
            val searchView = activity?.materialSearchView

            searchView?.showSearch(false)
            searchView?.setText(mPresenter.mSearchString)
            searchView?.setEndSelectCursor()

        }

        activity?.customToolbar?.setNavigationOnClickListener {
            mPresenter.backOnClick()
        }
    }

    override fun prepareView() {

        usersRecyclerView.setVerticalLlm()

        usersRecyclerView.adapter = UsersAdapter(arrayListOf(), context!!)


    }

    class FragmentUsers: SupportAppScreen() {

        override fun getFragment(): Fragment {
            return UsersFragment()
        }
    }

    override fun showUsers(list: List<UsersResponse>) {
        var arrayUsers = mutableListOf<UsersModel>()
        list.forEach {
            arrayUsers.add(UsersModel(it.name) {
                mPresenter.createTransactionWithName(it)
            })
        }

        usersRecyclerView.adapter = UsersAdapter(arrayUsers, context!!)

    }

    override fun showError(id: Int) {
        showError("", getString(id))
    }

    override fun showError(message: String) {
        showError("", message )
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        menu.clear()
        inflater.inflate(R.menu.action_bar_users, menu)

        prepareSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun prepareSearchView(menu: Menu) {
        // style
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = activity?.materialSearchView



        searchView?.setMenuItem(searchItem)

        searchItem.setOnMenuItemClickListener {
            activity?.toolbarTitle?.visibility = View.VISIBLE
            searchView?.showSearch(true)

            return@setOnMenuItemClickListener true
        }

        searchView?.searchQueryChangeObservable
            ?.retry()
            ?.debounce(500, TimeUnit.MILLISECONDS)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { query ->
                mPresenter.mSearchString = query
                mPresenter.withSearch(query)

            }
            ?.disposeOnDestroyView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.materialSearchView?.closeSearch()
    }
}