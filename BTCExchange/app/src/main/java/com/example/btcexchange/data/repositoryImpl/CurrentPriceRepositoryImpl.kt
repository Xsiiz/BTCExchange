package com.example.btcexchange.data.repositoryImpl

import android.util.Log
import com.example.btcexchange.data.model.CurrentPriceData
import com.example.btcexchange.data.service.CurrentPriceApiService
import com.example.btcexchange.domain.repository.CurrentPriceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class CurrentPriceRepositoryImpl(private val currentPriceApiService: CurrentPriceApiService): CurrentPriceRepository {
    override suspend fun fetchCurrentPriceFromApi(): CurrentPriceData? {
        var response = currentPriceApiService.fetchCurrentPrice()
        return if(response.isSuccessful) response.body() else null
    }
}