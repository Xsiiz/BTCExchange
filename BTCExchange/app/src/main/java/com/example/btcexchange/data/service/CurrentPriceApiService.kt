package com.example.btcexchange.data.service

import com.example.btcexchange.data.model.CurrentPriceData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CurrentPriceApiService {

    @GET("v1/bpi/currentprice.json")
    suspend fun fetchCurrentPrice(): Response<CurrentPriceData>

}