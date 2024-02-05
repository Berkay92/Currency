package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Err
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.network.common.Result
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    override suspend fun getLastThreeDaysExchanges(to: String): Result<List<Double>, Exception> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.now()
        val historyList = mutableListOf<Double>()
        var error : java.lang.Exception? = null
        (1..3).forEach { index ->
            var theDayBefore = today.minusDays(index.toLong())
            var theDayBeforeFormatted = theDayBefore.format(formatter)
            val x = fixerApiService.historic(theDayBeforeFormatted)
            if (x.isSuccessful) {
                historyList.add(x.body()?.rates?.getValue(to) ?: 0.0)
            } else {
                error = java.lang.Exception()
            }
        }
        return if (error == null) Ok(historyList.toList()) else Err(java.lang.Exception())
    }

}