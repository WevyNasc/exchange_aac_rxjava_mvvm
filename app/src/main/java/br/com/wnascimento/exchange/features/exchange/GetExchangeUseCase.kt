package br.com.wnascimento.exchange.features.exchange

import br.com.wnascimento.exchange.common.SchedulerManager
import br.com.wnascimento.exchange.common.UserCase
import io.reactivex.Single
import javax.inject.Inject

class GetExchangeUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepositoryInterface,
    private val schedulerManager: SchedulerManager
) : UserCase<Params, Single<Double>> {
    override fun execute(params: Params): Single<Double> {
        return exchangeRepository
            .exchange(params.from)
            .subscribeOn(schedulerManager.io())
            .observeOn(schedulerManager.ui())
            .map { it.rates[params.to] }
    }
}

data class Params(val from: String = "BRL", val to: String)