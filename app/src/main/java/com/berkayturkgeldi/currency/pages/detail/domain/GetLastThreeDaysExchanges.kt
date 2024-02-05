package com.berkayturkgeldi.currency.pages.detail.domain

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.common.Result
import com.berkayturkgeldi.currency.network.error.CurrencyException

open class GetLastThreeDaysExchanges(
    private val currencyApiManager: CurrencyApiManager
) {
    suspend operator fun invoke(to: String) : Result<Map<String, Double>, CurrencyException> {
        return currencyApiManager.getLastThreeDaysExchanges(to)
    }
}