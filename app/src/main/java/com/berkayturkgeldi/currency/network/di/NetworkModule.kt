package com.berkayturkgeldi.currency.network.di

import com.berkayturkgeldi.currency.network.CurrencyApiManager
import com.berkayturkgeldi.currency.network.FixerApi
import com.berkayturkgeldi.currency.network.FixerApiManager
import org.koin.dsl.module

val networkModule = module {
    single { FixerApi.retrofitService }
    single<CurrencyApiManager> { FixerApiManager(get()) }
}