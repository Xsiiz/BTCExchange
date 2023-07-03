package com.example.btcexchange.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btcexchange.databinding.FragmentHistoryBinding
import com.example.btcexchange.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HistoryFragment: Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val mViewModel: MainViewModel by sharedViewModel()
    private val exchangeRateHistoryAdapter = ExchangeRateHistoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        observeLiveData()
        setupRecyclerView()
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rcvHistory.layoutManager = LinearLayoutManager(context)
        binding.rcvHistory.adapter = exchangeRateHistoryAdapter
    }

    private fun observeLiveData() {
        mViewModel.historyExChangeRateLiveData.observe(viewLifecycleOwner) { historyExchangeRateList ->
            Log.d("History", historyExchangeRateList.toString())
            historyExchangeRateList.let {
                exchangeRateHistoryAdapter.addHistoryData(historyExchangeRateList)
            }
        }
    }
}