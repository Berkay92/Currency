package com.berkayturkgeldi.currency.pages.detail.viewmodel

import androidx.compose.runtime.Immutable

@Immutable
data class DetailState(
    val isLoading: Boolean = false,
    val fromCurrency: String? = null,
    val toCurrency: String? = null,
)