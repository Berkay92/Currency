package com.berkayturkgeldi.currency.pages.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.pages.detail.domain.FetchPopularExchanges
import com.berkayturkgeldi.currency.pages.detail.domain.GetLastThreeDaysExchanges
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getLastThreeDaysExchanges: GetLastThreeDaysExchanges,
    private val fetchPopularExchanges: FetchPopularExchanges
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())

    val state: StateFlow<DetailState>
        get() = _state

    fun initPage(from: String, to: String) {
        _state.update { it.copy(fromCurrency = from, toCurrency = to) }
        if (to.isNullOrBlank().not()) {
            viewModelScope.launch {
                val result = getLastThreeDaysExchanges(to)
                if (result is Ok) {
                    Log.d("myTag","result list : ${result.value}")
                } else {
                    Log.d("myTag","Something went wrong : $result")
                }

                val popularExchangesResult = fetchPopularExchanges.invoke()
                if (popularExchangesResult is Ok) {
                    Log.d("myTag","popular result list : ${popularExchangesResult.value}")
                } else {
                    Log.d("myTag","Something went wrong for popular : $result")
                }
            }
        }
    }

}