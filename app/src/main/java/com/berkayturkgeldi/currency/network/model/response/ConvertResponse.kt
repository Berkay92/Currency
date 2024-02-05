package com.berkayturkgeldi.currency.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ConvertResponse(
    val success: Boolean? = null,
    val error: Error? = null,
    val query: Query? = null,
    val info: Info? = null,
    val date: String? = null,
    val result: Double? = null,
)

@Serializable
data class Query(
    val from: String? = null,
    val to: String? = null,
    val amount: Double? = null
)

@Serializable
data class Info(
    val timestamp: Int? = null,
    val rate: Double? = null,
)

