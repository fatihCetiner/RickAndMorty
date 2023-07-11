package com.example.rickandmorty.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentHomeFragmentsBinding
import com.example.rickandmorty.ui.home.adapter.HomeRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragments : Fragment() {

    private var _binding: FragmentHomeFragmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: HomeRecyclerAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        activity?.title = "Character"

        setupRv()
        loadingData()
    }

    private fun setupRv() {

        mAdapter = HomeRecyclerAdapter()
        binding.recyclerView.apply {

            layoutManager = GridLayoutManager(requireContext(), 2)

            adapter = mAdapter
            setHasFixedSize(true)
        }
    }


    private fun loadingData() {

        lifecycleScope.launch {
            try {
                viewModel.listData.collectLatest { pagingData ->
                    withContext(Dispatchers.Main) {
                        mAdapter.submitData(pagingData)
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to load data!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}