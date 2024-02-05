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
        val result = call(fixerApiService.getSymbols())
        return if (result.component2() == null) Ok(result.component1()?.symbols?.map { it.key } ?: listOf()) else Err(result.component2()!!)
    }

    override suspend fun convert(from: String, to: String, amount: Double) : Result<Double, CurrencyException> {
        //val x = fixerApiService.latest(from, listOf(to)) // Not available for free API subscription
        //val x = fixerApiService.latest(from) // Not available for free API subscription
        val result = call(fixerApiService.latest())
        return if (result.component2() == null) Ok(result.component1()?.rates?.getValue(to) ?: 0.0) else Err(result.component2()!!)

    }

    override suspend fun getLastThreeDaysExchanges(to: String): Result<Map<String, Double>, CurrencyException> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.now()
        val historyMap = mutableMapOf<String, Double>()
        var error : CurrencyException? = null
        (1..3).forEach { index ->
            var theDayBefore = today.minusDays(index.toLong())
            var theDayBeforeFormatted = theDayBefore.format(formatter)
            val result = call(fixerApiService.historic(theDayBeforeFormatted))
            if (result.component2() == null) {
                historyMap.put(theDayBeforeFormatted, result.component1()?.rates?.getValue(to) ?: 0.0)
            } else {
                error = result.component2()
            }
        }
        return if (error == null) Ok(historyMap.toMap()) else Err(error!!)
    }

    override suspend fun fetchPopularExchanges(): Result<Map<String, Double>, CurrencyException> {
        val result = call(fixerApiService.latest())
        return if (result.component2() == null) Ok(result.component1()?.rates ?: mapOf()) else Err(result.component2()!!)
    }

}