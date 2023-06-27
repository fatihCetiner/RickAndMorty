package com.example.rickandmorty.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentFavoriteFragmentsBinding
import com.example.rickandmorty.ui.favorite.adapter.FavoriteRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragments : Fragment() {


    private var _binding: FragmentFavoriteFragmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: FavoriteRecyclerAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteFragmentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Favorite"

        setupRv()
        observeCharacterList()
        loadCharacters()
    }


    private fun setupRv() {

        mAdapter = FavoriteRecyclerAdapter()
        binding.favCharacterRv.apply {

            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeCharacterList() {
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            mAdapter.submitList(characters)
            checkListEmpty(characters.isEmpty())
        }
    }

    private fun loadCharacters() {
        viewModel.getAllCharacters()
    }

    private fun checkListEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.isEmptyMessage.visibility = View.VISIBLE
            binding.favCharacterRv.visibility = View.GONE
        } else {
            binding.isEmptyMessage.visibility = View.GONE
            binding.favCharacterRv.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}