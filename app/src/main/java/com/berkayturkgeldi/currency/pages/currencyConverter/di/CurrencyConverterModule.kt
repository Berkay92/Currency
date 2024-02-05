package com.berkayturkgeldi.currency.pages.currencyConverter.di

import com.berkayturkgeldi.currency.pages.currencyConverter.domain.ConvertCurrency
import com.berkayturkgeldi.currency.pages.currencyConverter.domain.FetchCurrencies
import com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val currencyConverterModule = module {
    viewModelOf(::CurrencyConverterViewModel)
    single { FetchCurrencies(get()) }
    single { ConvertCurrency(get()) }

}