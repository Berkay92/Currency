package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Result
import com.berkayturkgeldi.currency.network.error.CurrencyException

interface CurrencyApiManager {

    suspend fun getAllCurrencies(): Result<List<String>, CurrencyException>

    suspend fun convert(from: String, to: String, amount: Double) : Result<Double, CurrencyException>

    suspend fun getLastThreeDaysExchanges(to: String): Result<Map<String, Double>, CurrencyException>

    suspend fun fetchPopularExchanges(): Result<Map<String, Double>, CurrencyException>

}