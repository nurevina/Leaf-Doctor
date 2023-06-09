package com.example.leafdoctorapp.ui.authentication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.leafdoctorapp.R
import com.example.leafdoctorapp.core.LoadingDialog
import com.example.leafdoctorapp.data.model.networkmodel.request.SignInRequest
import com.example.leafdoctorapp.data.model.networkmodel.request.SignUpRequest
import com.example.leafdoctorapp.databinding.ActivitySignUpBinding
import com.example.leafdoctorapp.ui.NavigationActivity
import com.example.leafdoctorapp.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
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

        viewModel.onSignedUp.observe(this) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }


        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this, R.layout.dropdown, resources.getStringArray(
                    R.array.provinsi
                )
            )


        binding.provincesEdt.threshold = 1

        binding.provincesEdt.setAdapter(adapter)

        binding.signUpButton.setOnClickListener {
            val username = binding.emailEdt.text.toString()
            val password = binding.passEdt.text.toString()
            val fullName = binding.fullnameEdt.text.toString()
            val provinsi = binding.provincesEdt.text.toString()

            val isReady = username.isNotEmpty()
                    && password.isNotEmpty()
                    && fullName.isNotEmpty()
                    && provinsi.isNotEmpty()

            if (username.isEmpty()) {
                binding.emailEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Username wajib diisi"
                }
            }

            if (password.isEmpty()) {
                binding.passEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Password wajib diisi"
                }
            }
            if (fullName.isEmpty()) {
                binding.fullnameEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Nama harus diisi"
                }
            }
            if (provinsi.isEmpty()) {
                binding.provincesEdtLayout.apply {
                    isErrorEnabled = true
                    error = "Harus memilih provinsi"
                }
            }

            if (isReady) {
                viewModel.signUp(
                    SignUpRequest(
                        username = username,
                        password = password,
                        provinsi = provinsi,
                        fullName = fullName
                    )
                )
            }
        }


        binding.tvSignin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

    }
}