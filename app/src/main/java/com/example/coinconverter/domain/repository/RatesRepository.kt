package com.example.coinconverter.domain.repository

import com.example.coinconverter.domain.model.CoinResponse
import com.example.coinconverter.util.Resource

interface RatesRepository {
    suspend fun getRates(base: String): Resource<CoinResponse>
}