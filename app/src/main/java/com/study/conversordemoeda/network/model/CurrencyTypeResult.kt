package com.study.conversordemoeda.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyType(
    val name: String,
    val acronym: String,
    val symbol: String,
    @SerialName("countryFlagImageUrl")
    val contryFlagImageUrl: String,
)

@Serializable
data class CurrencyTypesResult(
    val values: List<CurrencyType>
)



