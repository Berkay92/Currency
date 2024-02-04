package com.berkayturkgeldi.currency.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                onDetailsClicked = {
                    navController.navigate(Screen.DetailScreen.route)
                }
            )
        }

        composable(Screen.DetailScreen.route) {
            DetailScreen()
        }
    }
}