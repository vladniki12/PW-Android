package com.smcorp.pw.common.executor

import com.smcorp.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UIThread @Inject
internal constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}
