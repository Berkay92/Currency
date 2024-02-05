package com.berkayturkgeldi.currency.network.error

sealed class CurrencyException(val message: String) {
    data object NetworkException : CurrencyException("Internet connection issue")
    data object ServerException : CurrencyException("Server Exception")
    data object UnexpectedException : CurrencyException("An unexpected error has occurred")
    data object UnkownException : CurrencyException("Unknown Exception")
}