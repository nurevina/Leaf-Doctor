package com.example.leafdoctorapp.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.leafdoctorapp.R
import com.example.leafdoctorapp.databinding.ActivityDetailHistoryBinding
import com.example.leafdoctorapp.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistoryActivity : AppCompatActivity() {

    private val binding: ActivityDetailHistoryBinding by lazy {
        ActivityDetailHistoryBinding.inflate(layoutInflater)
    }

    private val vm: HistoryVM by lazy {
        ViewModelProvider(this)[HistoryVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getStringExtra("EXTRA_ID")

        if (id.isNullOrBlank()) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        } else {
            vm.getHistoryDetail(id)
        }

        vm.nGetHistoryDetail.observe(this){
            binding.jenis.text = it.categories
            binding.imgItem.loadUrl(it.imageUrl!!)
            binding.diseaseDetection.text = it.itemData?.prediction
//            binding.detailText.text = it.itemData TODO : GAADA ISINYA DARI RESPONSE LAGI
        }

    }

}