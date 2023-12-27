package com.example.rickandmorty.ui.character.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.remote.dto.RickMorty
import com.example.rickandmorty.databinding.RickMortyLayoutBinding
import com.example.rickandmorty.utils.downloadFromUrl
import com.example.rickandmorty.utils.placeholderProgressBar

class CharacterViewHolder(
    private val binding: RickMortyLayoutBinding,
    private val callbacks: CharacterRecyclerAdapter.CharacterCallbacks
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: RickMorty) = with(binding) {
        val imageLink = character.image
        textView.text = character.name
        imageView.downloadFromUrl(
            imageLink,
            placeholderProgressBar(context = itemView.context)
        )
        itemView.setOnClickListener {
            callbacks.onClickCharacter(characterId = character.id)
        }
    }

}