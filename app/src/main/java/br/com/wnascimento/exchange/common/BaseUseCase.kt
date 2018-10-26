package br.com.wnascimento.exchange.common

interface UserCase<T, R> {
    fun execute(params: T): R
}