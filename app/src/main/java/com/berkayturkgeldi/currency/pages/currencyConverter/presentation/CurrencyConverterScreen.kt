package com.berkayturkgeldi.currency.pages.currencyConverter.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onSwitchCurrencies : () -> Unit = {},
    onDetailsClicked : () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CurrencyDropdown("From", state.currencies)
                Button(onClick = onSwitchCurrencies) {
                    Text(text = "Switch")
                }
                CurrencyDropdown("To", state.currencies)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CurrencyTextField(onValueChange = {})
                CurrencyTextField(onValueChange = {})
            }
            Button(onClick = onDetailsClicked) {
                Text(text = "See Details")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun CurrencyDropdown(
    label: String,
    currencyOptions: List<String>
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var currency by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        modifier = Modifier.width(120.dp),
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = currency,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            label = {
                Text(text = label)
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            currencyOptions.forEach { currencyOption ->
                DropdownMenuItem(
                    text = { Text(text = currencyOption) },
                    onClick = {
                        currency = currencyOption
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CurrencyTextField(
    onValueChange: (String) -> Unit
) {
    var currencyValue by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.width(120.dp),
        value = currencyValue,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = "")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterPreview() {
    CurrencyConverterContent(
        state = CurrencyConverterState()
    )
}