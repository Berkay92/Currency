package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Result

interface CurrencyApiManager {

    suspend fun getAllCurrencies(): Result<List<String>, Exception>

}