package com.smcorp.domain.interactor

import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class UseCase<T> internal constructor(protected val threadExecutor: ThreadExecutor,
                                               protected val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    internal abstract fun buildUseCaseObservable(): Observable<T>

    /**
     * Executes the current use case.

     * @param observer [DisposableObserver] which will be listening to the observable build
     * * by [.buildUseCaseObservable] ()} method.
     * *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    open fun execute(observer: DisposableObserver<T>) {
        val observable = buildUseCaseObservable()
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }

    open fun executeWithDelay(observer: DisposableObserver<T>, delay: Long, units: TimeUnit) {
        val observable = buildUseCaseObservable()
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .delaySubscription(delay, units, Schedulers.from(threadExecutor))
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Interrupt all from current [CompositeDisposable].
     */
    fun interrupt() {
        disposables.clear()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}