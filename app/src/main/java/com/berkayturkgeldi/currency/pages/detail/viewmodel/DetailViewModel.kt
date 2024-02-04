package com.berkayturkgeldi.currency.pages.detail.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(DetailState())

    val state: StateFlow<DetailState>
        get() = _state

}