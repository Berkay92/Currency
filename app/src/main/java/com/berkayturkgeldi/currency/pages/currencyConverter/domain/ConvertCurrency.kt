package com.berkayturkgeldi.currency.pages.currencyConverter.domain

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.common.Result
import com.berkayturkgeldi.currency.network.error.CurrencyException

open class ConvertCurrency(
    private val currencyApiManager: CurrencyApiManager
) {
    suspend operator fun invoke(
        from: String,
        to: String,
        amount: Double
    ) : Result<Double, CurrencyException> {
        return currencyApiManager.convert(from, to, amount)
    }
}