package br.com.wnascimento.exchange.features.exchange

import br.com.wnascimento.exchange.common.SchedulerManagerMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetExchangeUseCaseTest {

    companion object {
        const val BASE = "BRL"
    }

    @Mock
    private lateinit var exchangeRepository: ExchangeRepository

    private lateinit var getExchangeUseCase: GetExchangeUseCase

    private val testObserver = TestObserver<Double>()

    @Before
    fun before() {
        getExchangeUseCase = GetExchangeUseCase(exchangeRepository, SchedulerManagerMock())
    }

    @Test
    fun `should return exchange value by country`() {
        val exchange = Exchange("01-01-2018", mapOf(BASE to 1.0), BASE)
        whenever(exchangeRepository.exchange(any())).thenReturn(Single.just(exchange))

        getExchangeUseCase.execute(Params(to = BASE)).subscribe(testObserver)

        testObserver.assertResult(exchange.rates[BASE])
        testObserver.assertNoErrors()
    }

}