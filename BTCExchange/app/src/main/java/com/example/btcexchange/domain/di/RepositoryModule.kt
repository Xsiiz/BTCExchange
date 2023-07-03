package com.example.btcexchange.domain.di

import com.example.btcexchange.data.repositoryImpl.CurrentPriceRepositoryImpl
import com.example.btcexchange.domain.repository.CurrentPriceRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<CurrentPriceRepository> { CurrentPriceRepositoryImpl(get()) }
}