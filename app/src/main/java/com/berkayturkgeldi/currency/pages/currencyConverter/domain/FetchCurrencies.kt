package com.berkayturkgeldi.currency.pages.currencyConverter.domain

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.common.Result

open class FetchCurrencies(
    private val currencyApiManager: CurrencyApiManager
) {
    suspend operator fun invoke() : Result<List<String>, Exception> {
        return currencyApiManager.getAllCurrencies()
    }
}