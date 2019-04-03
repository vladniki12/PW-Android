package com.smcorp.pw.view.main.users_fragment

import com.arellomobile.mvp.MvpView
import com.smcorp.data.model.response.UsersResponse

interface UsersFragmentView: MvpView {
    fun showUsers(list: List<UsersResponse>)
    fun showError(id: Int)
    fun showError(message: String)
}