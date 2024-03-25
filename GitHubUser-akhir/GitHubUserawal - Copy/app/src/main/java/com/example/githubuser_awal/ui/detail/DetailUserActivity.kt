package com.example.githubuser_awal.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.githubuser_awal.databinding.ActivityDetailUserBinding
import com.google.android.material.snackbar.Snackbar

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    private val username by lazy { intent.getStringExtra(EXTRA_USERNAME) }
    private val avatarUrl by lazy { intent.getStringExtra(EXTRA_AVATAR_URL) }
    private val viewModel by viewModels<DetailUserViewModel> {
        DetailViewModelFactory(
            username!!,
            avatarUrl!!,
            this@DetailUserActivity.application
        )
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle().apply {
            putString(EXTRA_USERNAME, username)
        }

        viewModel.setUserDetail(username)
        viewModel.getUserDetail().observe(this) { userDetail ->
            userDetail?.let {
                binding.apply {
                    textView.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(ciProfile)

                    btnFav.apply {
                        isVisible = true
                        setOnClickListener {
                            if (viewModel.isFavorited.value!!) {
                                viewModel.deleteFavorite()
                                Snackbar.make(binding.root, "User Deleted from Favorites!", Snackbar.LENGTH_SHORT).show()
                            } else {
                                viewModel.insertFavorite()
                                Snackbar.make(binding.root, "User Added to Favorites!", Snackbar.LENGTH_SHORT).show()
                            }
                            viewModel.checkFavorite()
                        }
                    }
                }
            }
        }

        val sectionPagerAdapter = PagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
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

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_AVATAR_URL = "extra_avatar_url"
    }
}
