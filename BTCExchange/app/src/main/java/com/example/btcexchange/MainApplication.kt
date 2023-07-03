package com.example.btcexchange

import android.app.Application
import com.example.btcexchange.data.di.networkingModule
import com.example.btcexchange.domain.di.repositoryModule
import com.example.btcexchange.viewModel.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    private val dataModules = listOf(networkingModule)
    private val domainModules = listOf(repositoryModule)
    private val viewModules = listOf(viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(androidContext = this@MainApplication)
            modules(dataModules + domainModules + viewModules)
        }
    }
}