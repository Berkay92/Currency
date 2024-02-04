package com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CurrencyConverterViewModel : ViewModel() {

    private val _state = MutableStateFlow(CurrencyConverterState())

    val state: StateFlow<CurrencyConverterState>
        get() = _state

}
