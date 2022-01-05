package com.example.coinconverter.data

import com.example.coinconverter.domain.model.CoinResponse
import com.example.coinconverter.domain.repository.RatesRepository
import com.example.coinconverter.util.Resource
import java.lang.Exception
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val api: CoinResponse
) : RatesRepository {
    override suspend fun getRates(base: String): Resource<CoinResponse> {
        return try {
            val response = api
            //val result = response.body()
            /*val response = api.getRates(base)
            val result = response.body()*/
            /*if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }*/
            if(response.rates.toString() != "") {
                Resource.Success(response)
            } else {
                Resource.Error("Error to load rates")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}