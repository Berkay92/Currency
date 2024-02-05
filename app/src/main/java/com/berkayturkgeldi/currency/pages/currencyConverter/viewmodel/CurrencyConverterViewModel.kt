package com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.pages.currencyConverter.domain.ConvertCurrency
import com.berkayturkgeldi.currency.pages.currencyConverter.domain.FetchCurrencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyConverterViewModel(
    private val fetchCurrencies: FetchCurrencies,
    private val convertCurrency: ConvertCurrency
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
            _state.update { it.copy(currencies = result.value) }
        } else {
            Log.d("myTag","error is $result")
        }
        _state.update { it.copy(isLoading = false) }
    }

    fun onFromCurrencySelected(currency: String) {
        _state.update { it.copy(fromCurrency = currency) }
        calculateConverter()
    }

    fun onToCurrencySelected(currency: String) {
        _state.update { it.copy(toCurrency = currency) }
        calculateConverter()
    }

    fun onFromValueChanged(from: Double) {
        _state.update { it.copy(fromValue = from) }
        calculateConverter()
    }

    fun onToValueChanged(to: Double) {
        _state.update { it.copy(toValue = to) }
        calculateConverter()
    }

    fun onSwitchCurrencies() {
        val tempFrom = _state.value.fromCurrency
        _state.update { it.copy(fromCurrency = it.toCurrency) }
        _state.update { it.copy(toCurrency = tempFrom) }
        calculateConverter()
    }

    private fun calculateConverter() {
        if (_state.value.fromCurrency != null && _state.value.toCurrency != null) {
            viewModelScope.launch {
                val result = convertCurrency.invoke(
                    from = _state.value.fromCurrency.toString(),
                    to = _state.value.toCurrency.toString(),
                    amount = _state.value.fromValue
                )
                if (result is Ok) {
                    _state.update { it.copy(toValue = result.value * it.fromValue, convertRate = result.value) }
                } else {
                    Log.d("myTag","Something went wrong! $result")
                }
            }

        }
    }

}
