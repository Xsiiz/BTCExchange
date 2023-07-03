package com.example.btcexchange.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btcexchange.data.model.CurrentPriceData
import com.example.btcexchange.domain.repository.CurrentPriceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val currentPriceRepository: CurrentPriceRepository,
) : ViewModel() {

    private val _totalBitcoinPriceLiveData: MutableLiveData<Double?> = MutableLiveData()
    val totalBitcoinPriceLiveData: LiveData<Double?> = _totalBitcoinPriceLiveData

    private val _currencyPriceLiveData: MutableLiveData<CurrentPriceData?> = MutableLiveData()
    val currencyPriceLiveData: LiveData<CurrentPriceData?> = _currencyPriceLiveData

    private val _historyExChangeRateLiveData: MutableLiveData<ArrayList<CurrentPriceData?>> =
        MutableLiveData()
    val historyExChangeRateLiveData: LiveData<ArrayList<CurrentPriceData?>> =
        _historyExChangeRateLiveData

    private var historyExChangeRateList: ArrayList<CurrentPriceData?> = arrayListOf()

    fun calculateBitcoinPrice(priceInput: Double?, exchangeRate: Double?) {
        var totalBitcion = exchangeRate?.let { priceInput?.div(it) }
        _totalBitcoinPriceLiveData.value = totalBitcion
    }

    fun getCurrentPriceFromApi() {
        viewModelScope.launch(Dispatchers.Main) {
            val currentPrice = currentPriceRepository.fetchCurrentPriceFromApi()
            Log.d("currentPrice", currentPrice.toString())
            _currencyPriceLiveData.value = currentPrice
            if (currentPrice != null) {
                if (historyExChangeRateList.isNotEmpty()) {
                    if (!historyExChangeRateList.contains(currentPrice)) {
                        historyExChangeRateList.add(currentPrice)
                    }
                } else {
                    historyExChangeRateList.add(currentPrice)
                }
            }
            historyExChangeRateList.let {
                _historyExChangeRateLiveData.postValue(historyExChangeRateList)
            }
        }
    }
}