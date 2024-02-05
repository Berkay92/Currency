package com.berkayturkgeldi.currency.pages.detail.domain

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.common.Result
import java.lang.Exception

open class GetLastThreeDaysExchanges(
    private val currencyApiManager: CurrencyApiManager
) {
    suspend operator fun invoke(to: String) : Result<Map<String, Double>, Exception> {
        return currencyApiManager.getLastThreeDaysExchanges(to)
    }
}