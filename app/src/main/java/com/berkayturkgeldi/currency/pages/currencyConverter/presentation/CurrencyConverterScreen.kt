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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
        onFromCurrencySelected = vm::onFromCurrencySelected,
        onToCurrencySelected = vm::onToCurrencySelected,
        onFromValueChanged = vm::onFromValueChanged,
        onToValueChanged = vm::onToValueChanged,
        onSwitchCurrencies = vm::onSwitchCurrencies,
        onDetailsClicked = onDetailsClicked
    )
}

@Composable
fun CurrencyConverterContent(
    state: CurrencyConverterState,
    onFromCurrencySelected : (String) -> Unit = {},
    onToCurrencySelected : (String) -> Unit = {},
    onFromValueChanged : (Double) -> Unit = {},
    onToValueChanged : (Double) -> Unit = {},
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
                CurrencyDropdown("From", state.fromCurrency, state.currencies) {
                    onFromCurrencySelected.invoke(it)
                }
                Button(onClick = onSwitchCurrencies) {
                    Text(text = "Switch")
                }
                CurrencyDropdown("To", state.toCurrency, state.currencies) {
                    onToCurrencySelected.invoke(it)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CurrencyTextField(
                    stateValue = state.fromValue,
                    onValueChange = onFromValueChanged
                )
                CurrencyTextField(
                    stateValue = state.toValue,
                    onValueChange = onToValueChanged
                )
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
    stateValue: String?,
    currencyOptions: List<String>,
    onCurrencySelected: (String) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var currency by remember {
        mutableStateOf("")
    }

    LaunchedEffect(stateValue) {
        if (stateValue != null) {
            currency = stateValue
        }
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
                        onCurrencySelected.invoke(currencyOption)
                    }
                )
            }
        }
    }
}

@Composable
fun CurrencyTextField(
    stateValue: Double,
    onValueChange: (Double) -> Unit
) {
    var currencyValue by remember { mutableDoubleStateOf(stateValue) }

    LaunchedEffect(stateValue) {
        currencyValue = stateValue
    }

    TextField(
        modifier = Modifier.width(120.dp),
        value = currencyValue.toString(),
        onValueChange = {
            currencyValue = it.toDouble()
            onValueChange(currencyValue)
        },
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