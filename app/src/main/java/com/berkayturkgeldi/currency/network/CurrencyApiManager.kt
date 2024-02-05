package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Result

interface CurrencyApiManager {

    suspend fun getAllCurrencies(): Result<List<String>, Exception>

    suspend fun convert(from: String, to: String, amount: Double) : Result<Double, Exception>

    suspend fun getLastThreeDaysExchanges(to: String): Result<Map<String, Double>, Exception>

    suspend fun fetchPopularExchanges(): Result<Map<String, Double>, Exception>

}