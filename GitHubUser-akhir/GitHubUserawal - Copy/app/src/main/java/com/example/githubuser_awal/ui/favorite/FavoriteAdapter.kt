package com.example.githubuser_awal.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser_awal.data.model.FavoriteEntity
import com.example.githubuser_awal.databinding.ItemUserBinding
import com.example.githubuser_awal.ui.main.UserAdapter

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private val listFav: ArrayList<FavoriteEntity> = arrayListOf()
    private var onItemClickCallback: UserAdapter.OnItemClickCallback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listUser: ArrayList<FavoriteEntity>) {
        this.listFav.clear()
        this.listFav.addAll(listUser)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFav[position])
    }

    override fun getItemCount() = listFav.size

    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteEntity) {
            binding.apply {
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(binding.imgUserPhoto)

                tvItemName.text = user.login

                root.setOnClickListener {
                    onItemClickCallback?.onFavClicked(user)
                }
            }
        }
    }

    fun setOnItemClickCallback (onItemClickCallback: UserAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}