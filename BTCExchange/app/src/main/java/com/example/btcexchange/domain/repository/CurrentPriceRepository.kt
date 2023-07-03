package com.example.btcexchange.domain.repository

import com.example.btcexchange.data.model.CurrentPriceData
import retrofit2.Call
import retrofit2.Response

interface CurrentPriceRepository {
    suspend fun fetchCurrentPriceFromApi(): CurrentPriceData?
}