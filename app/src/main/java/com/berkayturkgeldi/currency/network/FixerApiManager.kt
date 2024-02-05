package com.berkayturkgeldi.currency.network

import com.berkayturkgeldi.currency.network.common.Err
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.network.common.Result
import com.berkayturkgeldi.currency.network.error.CurrencyException
import com.berkayturkgeldi.currency.network.error.call
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FixerApiManager(
    private val fixerApiService: FixerApiService,
) : CurrencyApiManager {
    override suspend fun getAllCurrencies(): Result<List<String>, CurrencyException> {
        val x = call(fixerApiService.getSymbols())
        return if (x.component2() == null) Ok(x.component1()?.symbols?.map { it.key } ?: listOf()) else Err(x.component2()!!)
    }

    override suspend fun convert(from: String, to: String, amount: Double) : Result<Double, Exception> {
        //val x = fixerApiService.latest(from, listOf(to))
        //val x = fixerApiService.latest(from)
        val x = fixerApiService.latest()
        return if (x.isSuccessful) Ok(x.body()?.rates?.getValue(to) ?: 0.0) else Err(java.lang.Exception())

    }

    override suspend fun getLastThreeDaysExchanges(to: String): Result<Map<String, Double>, Exception> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.now()
        val historyMap = mutableMapOf<String, Double>()
        var error : java.lang.Exception? = null
        (1..3).forEach { index ->
            var theDayBefore = today.minusDays(index.toLong())
            var theDayBeforeFormatted = theDayBefore.format(formatter)
            val x = fixerApiService.historic(theDayBeforeFormatted)
            if (x.isSuccessful) {
                historyMap.put(theDayBeforeFormatted, x.body()?.rates?.getValue(to) ?: 0.0)
            } else {
                error = java.lang.Exception()
            }
        }
        return if (error == null) Ok(historyMap.toMap()) else Err(java.lang.Exception())
    }

    override suspend fun fetchPopularExchanges(): Result<Map<String, Double>, Exception> {
        val x = fixerApiService.latest()
        return if (x.isSuccessful) Ok(x.body()?.rates ?: mapOf()) else Err(java.lang.Exception())
    }

}