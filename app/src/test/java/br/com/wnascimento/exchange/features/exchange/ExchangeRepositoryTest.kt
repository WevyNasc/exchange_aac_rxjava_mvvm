package br.com.wnascimento.exchange.features.exchange

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class ExchangeRepositoryTest {

    companion object {
        const val BASE = "BRL"
    }

    private lateinit var exchangeRepository: ExchangeRepository

    @Mock
    private lateinit var exchangeApi: ExchangeApi

    private val testObserver = TestObserver<Exchange>()

    @Before
    fun before() {
        exchangeRepository = ExchangeRepository(exchangeApi)
    }

    @Test
    fun `should return exchange object`() {
        val exchange = Exchange("01-01-2018", mapOf(BASE to 1.0), BASE)
        whenever(exchangeApi.exchange(BASE)).thenReturn(Single.just(exchange))

        exchangeRepository.exchange(BASE).subscribe(testObserver)

        verify(exchangeApi).exchange(eq(BASE))
        testObserver.assertResult(exchange)
    }

    @Test
    fun `should return exception`() {
        val exchange = Exception()
        whenever(exchangeApi.exchange(BASE)).thenReturn(Single.error(exchange))

        exchangeRepository.exchange(BASE).subscribe(testObserver)

        verify(exchangeApi).exchange(eq(BASE))
        testObserver.assertError(exchange)
    }
}