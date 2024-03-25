package com.example.githubuser_awal.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser_awal.R
import com.example.githubuser_awal.data.model.SettingPreferences
import com.example.githubuser_awal.databinding.ActivitySplashBinding
import com.example.githubuser_awal.ui.main.MainActivity
import com.example.githubuser_awal.ui.main.UserViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)

        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.animate)
        val backgroundImg : ImageView = findViewById(R.id.githublogo)
        backgroundImg.startAnimation(sideAnimation)

        viewModel = ViewModelProvider(
            this,
            UserViewModel.Factory(SettingPreferences(this))
        )[UserViewModel::class.java]

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500L)
    }
}