package com.berkayturkgeldi.currency.pages.detail.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.berkayturkgeldi.currency.pages.detail.viewmodel.DetailState
import com.berkayturkgeldi.currency.pages.detail.viewmodel.DetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    vm: DetailViewModel = getViewModel(),
    from: String = "",
    to: String = ""
) {
    LaunchedEffect(Unit) {
        vm.initPage(from, to)
    }

    val state = vm.state.collectAsState().value
    DetailContent(
        state = state
    )
}

@Composable
fun DetailContent(
    state: DetailState
) {
    val ctx = LocalContext.current

    LaunchedEffect(state.exception) {
        if (state.exception != null) {
            Toast.makeText(ctx, "Error : ${state.exception}", Toast.LENGTH_LONG).show()
        }
    }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Last 3 days")
            Divider()
            state.lastThreeDays.forEach { entry ->
                Text(text = entry.key)
                Text(text = entry.value.toString())
                Text(text = "-- -- --")
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Popular Exchanges")
            Divider()
            state.popularExchanges.forEach { entry ->
                Text(text = entry.key)
                Text(text = entry.value)
                Text(text = "-- -- --")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    DetailContent(
        state = DetailState()
    )
}