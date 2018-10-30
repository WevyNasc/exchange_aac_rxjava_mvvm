package br.com.wnascimento.exchange.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerManager: SchedulerManagerInterface {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}

interface SchedulerManagerInterface {
    fun io(): Scheduler
    fun ui(): Scheduler
}