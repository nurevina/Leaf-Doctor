package com.example.leafdoctorapp.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryData
import com.example.leafdoctorapp.data.model.networkmodel.response.HistoryItem
import com.example.leafdoctorapp.databinding.ActivitySignUpBinding
import com.example.leafdoctorapp.databinding.ItemHistoryBinding
import com.example.leafdoctorapp.util.loadUrl
import com.example.leafdoctorapp.util.toDate

class HistoryItemAdapter : RecyclerView.Adapter<HistoryItemAdapter.HViewHolder>() {

    private val historyItem = mutableListOf<HistoryItem>()
    private lateinit var listener : RecyclerViewListener

    fun setItem(data: List<HistoryItem>) {
        historyItem.clear()
        historyItem.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick : (String)-> Unit){
        listener = object : RecyclerViewListener{
            override fun onClick(id: String) {
                onClick(id)
            }
        }
    }

    inner class HViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: HistoryItem) {
            binding.jenis.text = item.categories
            binding.imgItem.loadUrl(item.imageUrl!!)
            binding.tvItem.text = "Created at : ${item.createdAt?.toDate()}"
            binding.root.setOnClickListener {
                listener.onClick(item.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HViewHolder(binding)
    }

    override fun getItemCount(): Int = historyItem.size

    override fun onBindViewHolder(holder: HViewHolder, position: Int) {
        holder.bind(historyItem[position])
    }
}

interface RecyclerViewListener{
    fun onClick(id : String)
}