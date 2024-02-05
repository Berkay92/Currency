package com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.pages.currencyConverter.domain.FetchCurrencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyConverterViewModel(
    private val fetchCurrencies: FetchCurrencies
) : ViewModel() {

    private val _state = MutableStateFlow(CurrencyConverterState())

    val state: StateFlow<CurrencyConverterState>
        get() = _state

    init {
        fetchAllCurrencies()
    }

    private fun fetchAllCurrencies() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        val result = fetchCurrencies.invoke()
        if (result is Ok) {
            Log.d("myTag","currencies : $result")
        } else {
            Log.d("myTag","error is $result")
        }
        _state.update { it.copy(isLoading = false) }
    }

}
