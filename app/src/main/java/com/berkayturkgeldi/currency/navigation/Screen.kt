package com.berkayturkgeldi.currency.navigation

import com.berkayturkgeldi.currency.navigation.NavigationConst.FROM_CURRENCY
import com.berkayturkgeldi.currency.navigation.NavigationConst.TO_CURRENCY

sealed class Screen(
    open val routeBody: String,
    open val suffix: String? = null,
) {
    val route: String
        get() {
            return if (routeBody.isNotBlank() && suffix != null) routeBody + suffix
            else routeBody
        }
    data object CurrencyConverterScreen : Screen("currencyConverter")
    data object DetailScreen : Screen("detail","/{$FROM_CURRENCY}/{$TO_CURRENCY}")

}

fun Screen.DetailScreen.buildRoute(
    from: String,
    to: String
): String {
    return "${routeBody}/$from/$to"
}