package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Err
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.network.common.Result

class FixerApiManager(
    private val fixerApiService: FixerApiService,
) : CurrencyApiManager {
    override suspend fun getAllCurrencies(): Result<List<String>, Exception> {
        val x = fixerApiService.getSymbols()
        return if (x.isSuccessful) Ok(x.body()?.symbols?.map { it.key } ?: listOf()) else Err(java.lang.Exception())
    }

    override suspend fun convert(from: String, to: String, amount: Double) : Result<Double, Exception> {
        //val x = fixerApiService.latest(from, listOf(to))
        //val x = fixerApiService.latest(from)
        val x = fixerApiService.latest()
        return if (x.isSuccessful) Ok(x.body()?.rates?.getValue(to) ?: 0.0) else Err(java.lang.Exception())

    }

}