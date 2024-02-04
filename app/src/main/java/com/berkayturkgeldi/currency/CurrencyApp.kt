package com.berkayturkgeldi.currency

import android.app.Application
import com.berkayturkgeldi.currency.pages.currencyConverter.di.currencyConverterModule
import com.berkayturkgeldi.currency.pages.detail.di.detailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CurrencyApp)
            androidLogger()
            modules(
                currencyConverterModule,
                detailModule
            )
        }
    }
}