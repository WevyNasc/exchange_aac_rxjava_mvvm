package br.com.wnascimento.exchange.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun onDisposable(block: () -> Disposable) {
        compositeDisposable.add(block.invoke())
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}