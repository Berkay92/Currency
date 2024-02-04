package com.berkayturkgeldi.currency.navigation

sealed class Screen(
    val route: String
) {

    data object CurrencyConverterScreen : Screen("currencyConverter")
    data object DetailScreen : Screen("detail")
}