package com.niks0n.domain.models

data class OfferModel(
    val id: String,
    val title: String,
    val link: String,
    val button: ButtonModel
)

data class ButtonModel(
    val text: String
)