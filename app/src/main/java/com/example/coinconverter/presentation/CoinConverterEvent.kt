package com.example.coinconverter.presentation

sealed class CoinConverterEvent {
    class Success(val resultText: String): CoinConverterEvent()
    class Failure(val errorText: String): CoinConverterEvent()
    object Loading: CoinConverterEvent()
    object Empty: CoinConverterEvent()
}