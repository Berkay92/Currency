package com.berkayturkgeldi.currency.pages.detail.domain

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.common.Err
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.network.common.Result

open class FetchPopularExchanges(
    private val currencyApiManager: CurrencyApiManager
) {
    suspend operator fun invoke(): Result<Map<String, String>, Exception?> {
        val exchanges = currencyApiManager.fetchPopularExchanges()
        if (exchanges is Ok) {
            return Ok(
                mapOf(
                    "EUR" to exchanges.value["EUR"].toString(),
                    "USD" to exchanges.value["EUR"].toString(),
                    "GBP" to exchanges.value["GBP"].toString(),
                    "AED" to exchanges.value["AED"].toString(),
                    "KRW" to exchanges.value["KRW"].toString(),
                    "JPY" to exchanges.value["JPY"].toString(),
                    "CAD" to exchanges.value["CAD"].toString(),
                    "BRL" to exchanges.value["BRL"].toString(),
                    "THB" to exchanges.value["THB"].toString(),
                    "TRY" to exchanges.value["TRY"].toString(),
                )
            )
        } else return Err(exchanges.component2())

    }
}