package com.berkayturkgeldi.currency.pages.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Detail Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    DetailContent(
        state = DetailState()
    )
}