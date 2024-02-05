package com.berkayturkgeldi.currency.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berkayturkgeldi.currency.navigation.NavigationConst.FROM_CURRENCY
import com.berkayturkgeldi.currency.navigation.NavigationConst.TO_CURRENCY
import com.berkayturkgeldi.currency.pages.currencyConverter.presentation.CurrencyConverterScreen
import com.berkayturkgeldi.currency.pages.detail.presentation.DetailScreen

@Composable
fun SetupMainNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CurrencyConverterScreen.route
    ) {
        composable(Screen.CurrencyConverterScreen.route) {
            CurrencyConverterScreen(
                onDetailsClicked = { from, to ->
                    navController.navigate(Screen.DetailScreen.buildRoute(from, to))
                }
            )
        }

        composable(Screen.DetailScreen.route) {
            val from = it.arguments?.getString(FROM_CURRENCY).orEmpty()
            val to = it.arguments?.getString(TO_CURRENCY).orEmpty()
            DetailScreen(from = from, to = to)
        }
    }
}