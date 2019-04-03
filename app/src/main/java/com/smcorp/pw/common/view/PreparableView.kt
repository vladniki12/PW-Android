package com.smcorp.pw.common.view

import com.arellomobile.mvp.MvpView

interface PreparableView: MvpView {
    /**
     * prepare ui elements for MvpView
     */
    fun prepareView()
}
