package com.example.btcexchange.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.btcexchange.data.model.Bpi
import com.example.btcexchange.databinding.FragmentHomeBinding
import com.example.btcexchange.utils.Constants
import com.example.btcexchange.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mViewModel: MainViewModel by sharedViewModel()
    private val exchangeRateHistoryAdapter = ExchangeRateHistoryAdapter()
    private var currency: Bpi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        observeLiveData()
        return binding.root
    }

    private fun setupView() {
        binding.tvUsdCurrentPrice.text = currency?.USD?.rate.toString()
        binding.tvGbpCurrentPrice.text = currency?.GBP?.rate.toString()
        binding.tvEurCurrentPrice.text = currency?.EUR?.rate.toString()
        setupExchangeRateHistoryButton()
        setupSpinner()
    }

    private fun setupPriceInputButton(exchangeRate: Double?) {
        binding.btEnter.setOnClickListener {
            if (binding.edtPriceInput.text?.isEmpty() != true) {
                val priceInput = binding.edtPriceInput.text?.toString()?.toDouble()
                if (priceInput != null) {
                    mViewModel.calculateBitcoinPrice(priceInput, exchangeRate)
                }
            }
        }
    }

    private fun setupSpinner() {
        val spinnerCurrency = arrayOf("USD", "GBP", "EUR")
        val spinner = binding.currencySpinner
        val arrayAdapter = activity?.let { ArrayAdapter(it.applicationContext, android.R.layout.simple_spinner_dropdown_item, spinnerCurrency) }
        spinner.adapter = arrayAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.getItemAtPosition(position).toString()) {
                    Constants.USD_CURRENCY_TYPE -> setupPriceInputButton(currency?.USD?.rate_float)
                    Constants.GBP_CURRENCY_TYPE -> setupPriceInputButton(currency?.GBP?.rate_float)
                    Constants.EUR_CURRENCY_TYPE -> setupPriceInputButton(currency?.EUR?.rate_float)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }
        }
    }

    private fun setupExchangeRateHistoryButton() {
        binding.btExchangeRateHistory.setOnClickListener {
            findNavController().navigate(com.example.btcexchange.R.id.action_homeFragment_to_historyFragment)
        }
    }

    private fun observeLiveData() {
        mViewModel.totalBitcoinPriceLiveData.observe(viewLifecycleOwner) { totalBitcoinPrice ->
            binding.totalBitcoinPrice.text = totalBitcoinPrice.toString()
        }
        mViewModel.currencyPriceLiveData.observe(viewLifecycleOwner) { currentPrice ->
            currency = currentPrice?.bpi
            setupView()
        }
    }

}