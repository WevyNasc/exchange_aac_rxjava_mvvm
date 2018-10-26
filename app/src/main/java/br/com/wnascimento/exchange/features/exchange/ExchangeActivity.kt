package br.com.wnascimento.exchange.features.exchange

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.VISIBLE
import br.com.wnascimento.exchange.R
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ExchangeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ExchangeViewModel

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        execute.setOnClickListener {
            viewModel.exchange(enterCountry.text.toString())
        }

        observeExchange()

    }

    private fun observeExchange() {
        compositeDisposable.add(viewModel
            .exchangeState
            .subscribe {
                when (it) {
                    is ExchangeState.Success -> onSuccessExchange(it.result)
                    is ExchangeState.Loading -> onLoading()
                    is ExchangeState.Error -> onErrorExchange()
                }
            })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun onErrorExchange() {
        hideLoading()
        showResult()
        result.text = getString(R.string.exchange_error)
    }

    private fun onSuccessExchange(value: Double) {
        hideLoading()
        showResult()
        result.text = value.toString()
    }

    private fun onLoading() {
        hideResult()
        showLoading()
    }

    private fun showResult() {
        result.visibility = VISIBLE
    }

    private fun hideResult() {
        result.visibility = VISIBLE
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

}
