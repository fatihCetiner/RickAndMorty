package com.example.rickandmorty.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.rickandmorty.core.model.RickMorty
import com.example.rickandmorty.databinding.FavRickMortyLayoutBinding
import com.example.rickandmorty.utils.downloadFromUrl

class FavoriteRecyclerAdapter :
    ListAdapter<RickMorty, FavoriteRecyclerAdapter.FavoriteCharacterViewHolder>(
        FavoriteCharacterDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharacterViewHolder {
        val binding =
            FavRickMortyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class FavoriteCharacterViewHolder(private val binding: FavRickMortyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentCharacter: RickMorty? = null

        fun bind(character: RickMorty) {

            currentCharacter = character

            binding.tvCharacterName.text = character.name
            binding.tvCharacterStatus.text = character.status
            binding.ivCharacterImage.downloadFromUrl(
                character.image,
                CircularProgressDrawable(binding.root.context)
            )

        }

    }
}

class FavoriteCharacterDiffCallback : DiffUtil.ItemCallback<RickMorty>() {
    override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem == newItem
    }
}