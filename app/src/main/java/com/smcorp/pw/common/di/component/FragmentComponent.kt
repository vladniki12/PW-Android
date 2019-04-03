package com.smcorp.pw.common.di.component

import com.smcorp.pw.common.mvp.PWFragment
import dagger.Component

@Component
interface FragmentComponent {

    fun inject(fragment: PWFragment)
}