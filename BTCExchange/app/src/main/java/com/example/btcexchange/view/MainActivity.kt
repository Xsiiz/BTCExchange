package com.example.btcexchange.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.btcexchange.databinding.ActivityMainBinding
import com.example.btcexchange.viewModel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private val mViewModel: MainViewModel by viewModel()

    private lateinit var apiHandler: Handler

    private val fetchRepeat = object : Runnable {
        override fun run() {
            mViewModel.getCurrentPriceFromApi()
            apiHandler.postDelayed(this, 60000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupView()
        setContentView(binding.root)
        apiHandler = Handler(Looper.getMainLooper())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        apiHandler.post(fetchRepeat)
    }



    private fun setupView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
    }
}