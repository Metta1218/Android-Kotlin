package com.example.githubuser_awal.setting

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.githubuser_awal.data.model.SettingPreferences
import com.example.githubuser_awal.databinding.ActivitySetThemeBinding

class SetThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetThemeBinding
    private val viewModel: SettingViewModel by viewModels {
        SettingViewModel.Factory(SettingPreferences(this))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveTheme(isChecked)
        }

        viewModel.getTheme().observe(this) { isDarkTheme ->
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switch1.isChecked = isDarkTheme
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}