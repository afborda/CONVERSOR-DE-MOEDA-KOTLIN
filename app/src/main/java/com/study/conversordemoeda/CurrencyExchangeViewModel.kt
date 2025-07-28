package com.study.conversordemoeda

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.conversordemoeda.network.KtorHttpClient
import com.study.conversordemoeda.network.model.CurrencyType
import com.study.conversordemoeda.network.model.ExchangeRateResult
import com.study.conversordemoeda.utils.CurrencyTypeAcronym
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrencyExchangeViewModel: ViewModel() {
  private val  _currencyTypes = MutableStateFlow<Result<List<CurrencyType>>>(Result.success(emptyList()))
  val currencyTypes: StateFlow<Result<List<CurrencyType>>> = _currencyTypes.asStateFlow()

  private val  _exchangeRate = MutableStateFlow<Result<ExchangeRateResult?>>(Result.success(null))
  val exchangeRate: StateFlow<Result<ExchangeRateResult?>> = _exchangeRate.asStateFlow()

  /**
   * Requisição para obter os tipos de moedas disponíveis
   */

  fun requireCurrencyTypes(){
    viewModelScope.launch {
      _currencyTypes.value = KtorHttpClient.getCurrencyTypes().mapCatching { result ->
        result.values
      }
    }
  }

  fun requireExchangeRate(from: CurrencyTypeAcronym, to: CurrencyTypeAcronym){

    if (from == to) {
      _exchangeRate.value = Result.success(ExchangeRateResult(from, to, 1.0))
      return
    }

    viewModelScope.launch {
      _exchangeRate.value = KtorHttpClient.getExchangeRate(from = from, to =  to)
    }
  }

}
