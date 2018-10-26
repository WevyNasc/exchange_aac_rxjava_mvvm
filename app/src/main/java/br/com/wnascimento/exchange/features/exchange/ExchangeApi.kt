package br.com.wnascimento.exchange.features.exchange

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {

    @GET("latest")
    fun exchange(@Query("base") base: String) : Single<Exchange>

}