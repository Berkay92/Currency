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

}