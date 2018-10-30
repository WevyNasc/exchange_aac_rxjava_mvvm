package br.com.wnascimento.exchange.features.exchange

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import br.com.wnascimento.exchange.di.ActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ExchangeModule {

    @JvmStatic
    @Provides
    @ActivityScope
    fun provideExchangeApi(retrofit: Retrofit): ExchangeApi =
        retrofit.create(ExchangeApi::class.java)

    @JvmStatic
    @Provides
    @ActivityScope
    fun provideExchangeRepository(exchangeApi: ExchangeApi): ExchangeRepositoryInterface =
        ExchangeRepository(exchangeApi)

    @JvmStatic
    @Provides
    @ActivityScope
    fun provideExchangeViewModel(
        exchangeActivity: ExchangeActivity,
        getExchangeUseCase: GetExchangeUseCase
    ): ExchangeViewModel {
        return ViewModelProviders.of(exchangeActivity, object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ExchangeViewModel(getExchangeUseCase) as T
            }

        })[ExchangeViewModel::class.java]
    }

}