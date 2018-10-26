package br.com.wnascimento.exchange.di

import br.com.wnascimento.exchange.ExchangeApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BindActivities::class])
interface AppComponent {
    fun inject(app: ExchangeApplication)
}