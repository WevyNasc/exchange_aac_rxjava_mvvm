package br.com.wnascimento.exchange.features.exchange

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import java.lang.Exception

class ExchangeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExchangeViewModel

    @Mock
    private val getExchangeUseCase: GetExchangeUseCase = mock()

    @Mock
    private val observer: Observer<ExchangeState> = mock()

    @Before
    fun before() {
        viewModel = ExchangeViewModel(getExchangeUseCase)
    }

    @Test
    fun `should return Success state`() {
        whenever(getExchangeUseCase.execute(any())).thenReturn(Single.just(1.0))

        viewModel.exchange.observeForever(observer)
        viewModel.exchange("BRL")

        assertEquals(viewModel.exchange.value, ExchangeState.Success(1.0))
        verify(observer).onChanged(ExchangeState.Loading)
        verify(observer).onChanged(ExchangeState.Success(1.0))
    }

    @Test
    fun `should return Error state`() {
        val exception = Exception()
        whenever(getExchangeUseCase.execute(any())).thenReturn(Single.error(exception))

        viewModel.exchange.observeForever(observer)
        viewModel.exchange("BRL")

        assertEquals(viewModel.exchange.value, ExchangeState.Error(exception))
        verify(observer).onChanged(ExchangeState.Loading)
        verify(observer).onChanged(ExchangeState.Error(exception))
    }

}