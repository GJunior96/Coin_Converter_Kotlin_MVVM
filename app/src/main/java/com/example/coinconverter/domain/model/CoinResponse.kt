package com.example.coinconverter.domain.model

data class CoinResponse(
    val base: String,
    val date: String,
    val rates: Rates
)
