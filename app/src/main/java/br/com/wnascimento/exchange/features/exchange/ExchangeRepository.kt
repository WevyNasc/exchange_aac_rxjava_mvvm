package br.com.wnascimento.exchange.features.exchange

import io.reactivex.Single

interface ExchangeRepositoryInterface {
    fun exchange(base: String): Single<Exchange>
}

class ExchangeRepository(private val exchangeApi: ExchangeApi) :
    ExchangeRepositoryInterface {

    override fun exchange(base: String) : Single<Exchange> {
        return exchangeApi.exchange(base)
    }
}