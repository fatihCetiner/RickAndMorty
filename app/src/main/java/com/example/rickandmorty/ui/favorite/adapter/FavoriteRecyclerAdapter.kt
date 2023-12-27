package com.example.rickandmorty.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.rickandmorty.data.remote.dto.RickMorty
import com.example.rickandmorty.databinding.FavRickMortyLayoutBinding
import com.example.rickandmorty.utils.downloadFromUrl

class FavoriteRecyclerAdapter : ListAdapter<RickMorty, FavoriteCharacterViewHolder>(
    FavoriteCharacterDiffCallback()
) {
    class FavoriteCharacterDiffCallback : DiffUtil.ItemCallback<RickMorty>() {
        override fun areItemsTheSame(
            oldItem: RickMorty,
            newItem: RickMorty
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RickMorty,
            newItem: RickMorty
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharacterViewHolder {
        val binding =
            FavRickMortyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

}

