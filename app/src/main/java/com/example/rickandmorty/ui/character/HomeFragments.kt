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

    private val viewModel: HomeViewModel by viewModels()

    private val characterAdapter: CharacterRecyclerAdapter by lazy {
        CharacterRecyclerAdapter(object : CharacterRecyclerAdapter.CharacterCallbacks {
            override fun onClickCharacter(characterId: Int) {
                viewModel.clickCharacter(characterId)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFragmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.page_characters)

        initViews()
        observe()
    }


    fun initViews(){
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }

    private fun observe() = with(binding) {

        lifecycleScope.launchWhenCreated {
            viewModel.navigateDetailScreen.collectLatest { characterId ->
                val action =
                    HomeFragmentsDirections.actionHomeFragmentsToDetailsFragment(characterId)
                findNavController().navigate(action)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.listData.collectLatest { pagingData ->
                    characterAdapter.submitData(pagingData)
                }
            } catch (e: Exception) {
                Log.e("Error", "Not Loading Data !")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            characterAdapter.loadStateFlow.collectLatest { loadStates ->
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
                errorMsg.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}