package com.berkayturkgeldi.currency.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LatestResponse(
    val success: Boolean? = null,
    val error: Error? = null,
    val timestamp: Int? = null,
    val base: String? = null,
    val date: String? = null,
    val rates: Map<String, Double>? = null
)