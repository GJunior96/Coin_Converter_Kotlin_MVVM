package com.example.coinconverter.di

import com.example.coinconverter.data.RatesRepositoryImpl
import com.example.coinconverter.domain.model.CoinResponse
import com.example.coinconverter.domain.repository.RatesRepository
import com.example.coinconverter.util.JsonFile
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val gson = Gson()

    @Singleton
    @Provides
    fun provideCoinConverterApi(): CoinResponse = gson.fromJson(JsonFile.jsonFile, CoinResponse::class.java)

    @Singleton
    @Provides
    fun provideRatesRepository(api: CoinResponse): RatesRepository = RatesRepositoryImpl(api)

}