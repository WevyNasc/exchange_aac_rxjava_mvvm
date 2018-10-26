package br.com.wnascimento.exchange.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel {

    private val compositeDisposable = CompositeDisposable()

    protected fun onDisposable(block: () -> Disposable) {
        compositeDisposable.add(block.invoke())
    }

    fun unregisterSubscribers() {
        compositeDisposable.clear()
    }

}