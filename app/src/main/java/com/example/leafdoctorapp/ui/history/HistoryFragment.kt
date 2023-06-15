package com.example.leafdoctorapp.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafdoctorapp.core.LoadingDialog
import com.example.leafdoctorapp.databinding.FragmentHistoryBinding
import com.example.leafdoctorapp.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!

    private val historyVM: HistoryVM by lazy {
        ViewModelProvider(this)[HistoryVM::class.java]
    }

    private val loading: LoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    private val adapter = HistoryItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter
        adapter.setOnClickListener {
            val nextScreen = Intent(requireContext(),DetailHistoryActivity::class.java)
            nextScreen.putExtra("EXTRA_ID", it)
            activity?.startActivity(nextScreen)
        }

        historyVM.getHistory()

        historyVM.onGetHistoryData.observe(requireActivity()) {
            adapter.setItem(it.history.orEmpty())
        }
        historyVM.onGetError.observe(requireActivity()){
                showErrorDialog(message = it.message){

                }
        }

        historyVM.onLoading.observe(requireActivity()){
            if (it) loading.show("Loading") else loading.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}