package br.com.wnascimento.exchange.features.exchange

import br.com.wnascimento.exchange.common.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ExchangeViewModel @Inject constructor(
    private val getExchangeUseCase: GetExchangeUseCase
) : BaseViewModel() {

    val exchangeState: BehaviorSubject<ExchangeState> = BehaviorSubject.create()

    fun exchange(to: String) = onDisposable {
        getExchangeUseCase.execute(Params(to = to))
            .doOnSubscribe { exchangeState.onNext(ExchangeState.Loading) }
            .subscribeBy(
                onSuccess = { exchangeState.onNext(
                    ExchangeState.Success(
                        it
                    )
                ) },
                onError = { exchangeState.onNext(
                    ExchangeState.Error(
                        it
                    )
                ) }
            )
    }

}

sealed class ExchangeState {
    data class Success(val result: Double) : ExchangeState()
    data class Error(val e: Throwable) : ExchangeState()
    object Loading : ExchangeState()
}
