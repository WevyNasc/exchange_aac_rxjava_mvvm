package br.com.wnascimento.exchange.di

import br.com.wnascimento.exchange.common.SchedulerManager
import br.com.wnascimento.exchange.common.SchedulerManagerInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().setLenient().create()

        @JvmStatic
        @Provides
        @Singleton
        fun provideRetrofit(gson: Gson): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideSchedulerManager(): SchedulerManagerInterface {
            return SchedulerManager()
        }
    }
}