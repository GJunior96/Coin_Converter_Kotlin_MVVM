package com.example.coinconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinconverter.domain.model.Rates
import com.example.coinconverter.domain.repository.RatesRepository
import com.example.coinconverter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CoinConverterViewModel @Inject constructor(
    private val repository: RatesRepository,
): ViewModel() {
    private val _conversion = MutableStateFlow<CoinConverterEvent>(CoinConverterEvent.Empty)
    val conversion: StateFlow<CoinConverterEvent> = _conversion

    fun convert(
        amount: String,
        fromCoin: String,
        toCoin: String
    ) {
        val fromAmount = amount.toFloatOrNull()
        if(fromAmount == null) {
            _conversion.value = CoinConverterEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch {
            _conversion.value = CoinConverterEvent.Loading
            when(val ratesResponse = repository.getRates(fromCoin)) {
                is Resource.Error -> _conversion.value = CoinConverterEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> {
                    val rates = ratesResponse.data!!.rates
                    val rateFrom = getRateForCoin(fromCoin, rates)
                    val rateTo = rateFrom?.let { getRateForCoin(toCoin, rates)?.div(it) }
                    if(rateTo == null) {
                        _conversion.value = CoinConverterEvent.Failure("Unexpected error")
                    } else {
                        val convertedCoin = round(fromAmount * rateTo * 100) / 100
                        _conversion.value = CoinConverterEvent.Success(
                            "$fromAmount $fromCoin \n$convertedCoin $toCoin"
                        )
                    }
                }
            }
        }
    }

    private fun getRateForCoin(coin: String, rates: Rates) = when(coin) {
        "CAD - Canadian Dollar" -> rates.cAD
        "HKD - Hong Kong, Dollar" -> rates.hKD
        "ISK - Icelandic Krona" -> rates.iSK
        "EUR - Euro" -> rates.eUR
        "PHP - Philippine Peso" -> rates.pHP
        "DKK - Danish Krone" -> rates.dKK
        "HUF - Hungarian Forint" -> rates.hUF
        "CZK - Czech Koruna" -> rates.cZK
        "AUD - Australian Dollar" -> rates.aUD
        "RON - Romanian New Leu" -> rates.rON
        "SEK - Sweden, Krona" -> rates.sEK
        "IDR - Indonesian Rupiah" -> rates.iDR
        "INR - Indian Rupee" -> rates.iNR
        "BRL - Brazilian Real" -> rates.bRL
        "RUB - Russian Ruble" -> rates.rUB
        "HRK - Croatian Kuna" -> rates.hRK
        "JPY - Japanese Yen" -> rates.jPY
        "THB - Thai Bahat" -> rates.tHB
        "CHF - Switzerland, Swiss Franc" -> rates.cHF
        "SGD - Singapore Dollar" -> rates.sGD
        "PLN - Polish Zloty" -> rates.pLN
        "BGN - Bulgarian Lev" -> rates.bGN
        "CNY - Chinese Yuan Renminbi" -> rates.cNY
        "NOK - Norwegian Krone" -> rates.nOK
        "NZD - New Zealand Dollar" -> rates.nZD
        "ZAR - South African Rand" -> rates.zAR
        "USD - US Dollar" -> rates.uSD
        "MXN - Mexican Peso" -> rates.mXN
        "ILS - Israeli Shekel" -> rates.iLS
        "GBP - United Kingdom, British Pound" -> rates.gBP
        "KRW - South Korean Won" -> rates.kRW
        "MYR - Malaysian Ringgit" -> rates.mYR
        else -> null
    }
}
