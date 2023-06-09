package com.example.leafdoctorapp.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.leafdoctorapp.databinding.ActivityMainBinding
import com.example.leafdoctorapp.ui.NavigationActivity
import com.example.leafdoctorapp.ui.authentication.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val vm : MainVM by lazy {
        ViewModelProvider(this)[MainVM::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activity : Intent? = null
        if (!vm.isFirstLaunch&&!vm.loggedIn){
            activity = Intent(this, SignInActivity::class.java)
            startActivity(activity)
            finish()
        }else if(!vm.isFirstLaunch&&vm.loggedIn){
            activity = Intent(this, NavigationActivity::class.java)
            startActivity(activity)
            finish()
        }
        setContentView(binding.root)

        binding.startedBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            vm.setNotFirstLaunch()
            startActivity(intent)
            finish()
        }
    }
}