package br.com.wnascimento.exchange.features.exchange

import br.com.wnascimento.exchange.di.PerActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ExchangeModule {

    @JvmStatic
    @Provides
    @PerActivity
    fun provideExchangeApi(retrofit: Retrofit) : ExchangeApi =
        retrofit.create(ExchangeApi::class.java)

    @JvmStatic
    @Provides
    @PerActivity
    fun provideExchangeRepository(exchangeApi: ExchangeApi) : ExchangeRepositoryInterface =
        ExchangeRepository(exchangeApi)

}