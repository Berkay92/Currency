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
        // TODO : Hardcoded EUR here as Fixer API free subscription not allow changing base currency
        _state.update { it.copy(fromCurrency = "EUR") }
        calculateConverter()
    }

    fun onToCurrencySelected(currency: String) {
        _state.update { it.copy(toCurrency = currency) }
        calculateConverter()
    }

    fun onFromValueChanged(from: Double) {
        _state.update { it.copy(fromValue = from) }
        _state.update { it.copy(toValue = it.convertRate * it.fromValue) }
    }

    fun onToValueChanged(to: Double) {
        _state.update { it.copy(toValue = to) }
        _state.update { it.copy(fromValue = it.toValue / it.convertRate) }

    }

    fun onSwitchCurrencies() {
        // TODO : Fixer API free subscription not allow changing base currency so this function is disabled here
        /*
        val tempFrom = _state.value.fromCurrency
        _state.update { it.copy(fromCurrency = it.toCurrency) }
        _state.update { it.copy(toCurrency = tempFrom) }

         */
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
