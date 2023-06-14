package com.example.leafdoctorapp.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.leafdoctorapp.R
import com.example.leafdoctorapp.core.LoadingDialog
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

    private val loading: LoadingDialog by lazy {
        LoadingDialog(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val id = intent.getStringExtra("EXTRA_ID")

        if (id.isNullOrBlank()) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        } else {
            vm.getHistoryDetail(id)
        }
        vm.onLoading.observe(this){
            if (it) loading.show("Loading") else loading.dismiss()
        }

        vm.nGetHistoryDetail.observe(this){
            binding.jenis.text = it.history?.categories
            binding.imgItem.loadUrl(it.history?.imageUrl!!)
            binding.diseaseDetection.text = it.history.data?.labels ?: "Not defined"
//            binding.detailText.text = it.itemData TODO : GAADA ISINYA DARI RESPONSE LAGI
        }

    }

}