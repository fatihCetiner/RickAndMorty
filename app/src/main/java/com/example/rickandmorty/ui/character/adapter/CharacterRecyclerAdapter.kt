package com.example.rickandmorty.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.remote.dto.RickMorty
import com.example.rickandmorty.databinding.RickMortyLayoutBinding

class CharacterRecyclerAdapter(
    private val callbacks: CharacterCallbacks
) : PagingDataAdapter<RickMorty, CharacterViewHolder>(diffCallback) {

    interface CharacterCallbacks {
        fun onClickCharacter(characterId: Int)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
            override fun areItemsTheSame(
                oldItem: RickMorty,
                newItem: RickMorty
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RickMorty,
                newItem: RickMorty
            ) = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position) ?: return
        holder.bind(character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            binding = RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callbacks = callbacks
        )
    }
}