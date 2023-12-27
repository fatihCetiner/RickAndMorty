package com.example.rickandmorty.ui.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentHomeFragmentsBinding
import com.example.rickandmorty.ui.character.adapter.CharacterRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragments : Fragment() {

    private var _binding: FragmentHomeFragmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: CharacterRecyclerAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeFragmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.page_characters)
        setupRv()
        loadingData()
        isLoading()
        //observe()
    }
    /*
    private fun observe() {
        lifecycleScope.launchWhenCreated {

            Log.d("Observing", "collectLatest block started")

            viewModel.navigateDetailScreen.collectLatest { characterId ->

                Log.d("Observing", "collectLatest block called with characterId: $characterId")

                val action =
                    HomeFragmentsDirections.actionHomeFragmentsToDetailsFragment(characterId)
                findNavController().navigate(action)

                Log.d("Observing", "Navigation completed")
            }
        }
    }

     */

    private fun setupRv() {
        mAdapter = CharacterRecyclerAdapter(object : CharacterRecyclerAdapter.CharacterCallbacks {
            override fun onClickCharacter(characterId: Int) {
                viewModel.clickCharacter(characterId)
                val action =
                    HomeFragmentsDirections.actionHomeFragmentsToDetailsFragment(characterId)
                findNavController().navigate(action)
            }
        })
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.listData.collectLatest { pagingData ->
                    mAdapter.submitData(pagingData)
                }
            } catch (e: Exception) {
                Log.e("Error", "Not Loading Data !")
            }
        }
    }

    private fun isLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                binding.errorMsg.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}