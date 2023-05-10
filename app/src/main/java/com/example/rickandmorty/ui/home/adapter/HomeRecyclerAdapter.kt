package com.example.rickandmorty.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.rickandmorty.databinding.RickMortyLayoutBinding
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.ui.home.HomeFragmentsDirections
import com.example.rickandmorty.utils.downloadFromUrl
import com.example.rickandmorty.utils.placeholderProgressBar

class HomeRecyclerAdapter :
    PagingDataAdapter<RickMorty, HomeRecyclerAdapter.MyViewHolder>(diffCallback) {

    inner class MyViewHolder(val binding: RickMortyLayoutBinding) : ViewHolder(binding.root)

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // this method getItem() is from PagingDataAdapter...
        val currentItem = getItem(position)

        holder.binding.apply {
            textView.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

            // use to Glide

            imageView.downloadFromUrl(imageLink, placeholderProgressBar(context = holder.itemView.context))

            holder.itemView.setOnClickListener {
                val action = HomeFragmentsDirections.actionHomeFragmentsToDetailsFragment()
                Navigation.findNavController(it).navigate(action)
            }

            // use to coil
            /*
            imageView.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }

             */
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}