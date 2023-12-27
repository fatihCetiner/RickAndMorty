package com.example.rickandmorty.ui.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.rickandmorty.data.remote.dto.RickMorty
import com.example.rickandmorty.databinding.FavRickMortyLayoutBinding
import com.example.rickandmorty.utils.downloadFromUrl

class FavoriteCharacterViewHolder(
    private val binding: FavRickMortyLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: RickMorty) = with(binding) {

        tvCharacterName.text = character.name
        tvCharacterStatus.text = character.status
        ivCharacterImage.downloadFromUrl(
            character.image,
            CircularProgressDrawable(binding.root.context)
        )
    }
}