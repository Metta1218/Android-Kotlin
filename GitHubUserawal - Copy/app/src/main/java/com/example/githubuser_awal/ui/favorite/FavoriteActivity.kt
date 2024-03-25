package com.example.githubuser_awal.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_awal.data.model.FavoriteEntity
import com.example.githubuser_awal.data.model.User
import com.example.githubuser_awal.databinding.ActivityFavoriteBinding
import com.example.githubuser_awal.ui.detail.DetailUserActivity
import com.example.githubuser_awal.ui.main.UserAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModelFactory(
            application
        )
    }

    private val favoriteAdapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Favorites"
        }

        viewModel.getFavorites().observe(this) {
            favoriteAdapter.setList(it as ArrayList<FavoriteEntity>)
        }

        binding.rvUsers.apply {
            favoriteAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {}

                override fun onFavClicked(data: FavoriteEntity) {
                    Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                        it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                        it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
                        startActivity(it)
                    }
                }
            })

            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
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