package br.com.wnascimento.exchange.features.exchange

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.wnascimento.exchange.common.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ExchangeViewModel @Inject constructor(
    private val getExchangeUseCase: GetExchangeUseCase
) : BaseViewModel() {

    private val exchangeState: MutableLiveData<ExchangeState> = MutableLiveData()

    val exchange: LiveData<ExchangeState>
        get() = exchangeState

    fun exchange(country: String) = onDisposable {
        getExchangeUseCase.execute(Params(to = country))
            .doOnSubscribe { exchangeState.postValue(ExchangeState.Loading) }
            .subscribeBy(onSuccess = ::onExchangeSuccess, onError = ::onExchangeError)
    }

    private fun onExchangeSuccess(value: Double) =
        exchangeState.postValue(ExchangeState.Success(value))

    private fun onExchangeError(error: Throwable) =
        exchangeState.postValue(ExchangeState.Error(error))

}

sealed class ExchangeState {
    data class Success(val result: Double) : ExchangeState()
    data class Error(val e: Throwable) : ExchangeState()
    object Loading : ExchangeState()
}
