package com.berkayturkgeldi.currency.pages.currencyConverter.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel.CurrencyConverterState
import com.berkayturkgeldi.currency.pages.currencyConverter.viewmodel.CurrencyConverterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyConverterScreen(
    vm: CurrencyConverterViewModel = getViewModel(),
    onDetailsClicked : () -> Unit
) {
    val state = vm.state.collectAsState().value

    CurrencyConverterContent(
        state = state,
        onDetailsClicked = onDetailsClicked
    )
}

@Composable
fun CurrencyConverterContent(
    state: CurrencyConverterState,
    onDetailsClicked : () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Currency Converter Screen")
            Button(onClick = onDetailsClicked) {
                Text(text = "See Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterPreview() {
    CurrencyConverterContent(
        state = CurrencyConverterState()
    )
}