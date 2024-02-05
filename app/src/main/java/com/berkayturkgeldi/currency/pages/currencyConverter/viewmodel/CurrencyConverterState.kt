package com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyConverterState(
    val isLoading: Boolean = false,
    val fromCurrency: String? = null,
    val toCurrency: String? = null,
    val fromValue: Double = 1.0,
    val toValue: Double = 0.0,
    val convertRate: Double = 0.0,
    val currencies: List<String> = listOf(),
    val exception: String? = null
)