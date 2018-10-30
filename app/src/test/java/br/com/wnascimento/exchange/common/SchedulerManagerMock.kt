package br.com.wnascimento.exchange.common

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerManagerMock : SchedulerManagerInterface {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}