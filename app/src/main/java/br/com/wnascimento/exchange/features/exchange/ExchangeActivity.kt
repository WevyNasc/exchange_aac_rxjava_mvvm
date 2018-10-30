package br.com.wnascimento.exchange.features.exchange

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.wnascimento.exchange.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ExchangeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ExchangeViewModel


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
        viewModel.exchange.observe(this, Observer {
            when (it) {
                is ExchangeState.Success -> onSuccessExchange(it.result)
                is ExchangeState.Loading -> onLoading()
                is ExchangeState.Error -> onErrorExchange()
            }
        })
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
        result.visibility = GONE
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

}
