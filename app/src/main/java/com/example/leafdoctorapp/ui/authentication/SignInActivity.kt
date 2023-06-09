package com.example.leafdoctorapp.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.leafdoctorapp.core.LoadingDialog
import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.databinding.ActivitySignInBinding
import com.example.leafdoctorapp.ui.NavigationActivity
import com.example.leafdoctorapp.util.showErrorDialog
import com.example.leafdoctorapp.util.showSuccessDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    private val viewModel: AuthenticationVM by lazy {
        ViewModelProvider(this)[AuthenticationVM::class.java]
    }

    private val loading: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onLoading.observe(this) {
            if (it) loading.show("Mohon tunggu") else loading.dismiss()
        }
        viewModel.onGetError.observe(this) {
            showErrorDialog(title = "Error !", message = it.message) { }
        }

        viewModel.onSignedIn.observe(this) {

            startActivity(Intent(this, NavigationActivity::class.java))
            finish()

        }

        binding.emailEdt.setOnFocusChangeListener { _, _ ->
            binding.emailEdtLayout.isErrorEnabled = false
        }
        binding.passwordEdt.setOnFocusChangeListener { _, _ ->
            binding.passwordEdtLayout.isErrorEnabled = false
        }
        binding.signInButton.setOnClickListener {
            val username = binding.emailEdt.text.toString()
            val password = binding.passwordEdt.text.toString()
            val isReady = !username.isEmpty() && !password.isEmpty()
            if (username.isEmpty()) {
                binding.emailEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Username wajib diisi"
                }
            }

            if (password.isEmpty()) {
                binding.passwordEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Password wajib diisi"
                }
            }

            if (isReady) {
                viewModel.signIn(SignInRequest(username, password))
            }

        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}