package com.berkayturkgeldi.currency.pages.currencyConverter.di

import com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val currencyConverterModule = module {
    viewModelOf(::CurrencyConverterViewModel)
}