package com.example.coinconverter.data

import com.example.coinconverter.domain.model.CoinResponse
import com.example.coinconverter.domain.model.Rates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
   //suspend fun getRates(base: String): Response<CoinResponse>
    //@GET("/latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<CoinResponse>
}
