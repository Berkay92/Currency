package com.berkayturkgeldi.currency.pages.detail.di

import com.berkayturkgeldi.currency.pages.detail.domain.GetLastThreeDaysExchanges
import com.berkayturkgeldi.currency.pages.detail.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailModule = module {
    viewModelOf(::DetailViewModel)
    single { GetLastThreeDaysExchanges(get()) }
}