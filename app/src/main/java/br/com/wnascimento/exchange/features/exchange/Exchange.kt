package br.com.wnascimento.exchange.features.exchange

data class Exchange(val date: String, val rates: Map<String, Double>, val base: String)