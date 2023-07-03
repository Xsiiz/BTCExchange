package com.example.btcexchange.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btcexchange.data.model.CurrentPriceData
import com.example.btcexchange.databinding.ItemHistoryBinding

class ExchangeRateHistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var historyExChangeRateList: ArrayList<CurrentPriceData?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return historyExChangeRateList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bindView(position)
        }
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int){
            binding.tvTimeHistory.text =historyExChangeRateList[position]?.time?.updated.toString()
            binding.tvUsdHistory.text =historyExChangeRateList[position]?.bpi?.USD?.rate.toString()
            binding.tvGbpHistory.text =historyExChangeRateList[position]?.bpi?.GBP?.rate.toString()
            binding.tvEurHistory.text =historyExChangeRateList[position]?.bpi?.EUR?.rate.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHistoryData(historyExChangeRateList: ArrayList<CurrentPriceData?>) {
        Log.d("Runner","Run")
        this.historyExChangeRateList = historyExChangeRateList
        notifyDataSetChanged()
    }

}