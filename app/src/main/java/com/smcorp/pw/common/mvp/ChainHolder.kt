package com.smcorp.pw.common.mvp

import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

interface ChainHolder {
    val chain: List<WeakReference<Fragment>>
}