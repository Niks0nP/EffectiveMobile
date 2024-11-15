package com.niks0n.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String,
    @SerialName("link") val link: String,
    @SerialName("button") val button: Button? = null
)

@Serializable
data class Button(
    @SerialName("text") val text: String
)
