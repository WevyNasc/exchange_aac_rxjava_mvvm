package br.com.wnascimento.exchange.di

import br.com.wnascimento.exchange.features.exchange.ExchangeActivity
import br.com.wnascimento.exchange.features.exchange.ExchangeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindActivities {

    @ContributesAndroidInjector(modules = [ExchangeModule::class])
    @PerActivity
    abstract fun bindMainActivity(): ExchangeActivity
}