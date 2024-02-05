package com.berkayturkgeldi.currency.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SymbolsResponse(
    val success: Boolean? = null,
    val error: Error? = null,
    val symbols: Map<String, String>? = null
)

@Serializable
data class Error(
    val code: Int? = null,
    val type: String? = null,
    val info: String? = null
)
