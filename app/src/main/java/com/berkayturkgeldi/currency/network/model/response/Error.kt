package com.berkayturkgeldi.currency.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: Int? = null,
    val type: String? = null,
    val info: String? = null
)