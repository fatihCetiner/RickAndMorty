package com.example.rickandmorty.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailsBinding
import com.example.rickandmorty.utils.downloadFromUrl
import com.example.rickandmorty.utils.placeholderProgressBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var id: Int? = null

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Character Detail"

        arguments?.let {
            id = DetailsFragmentArgs.fromBundle(it).id
            viewModel.getCharacter(id!!)
        }
        observeLiveData()


        binding.floatingActionButton.setOnClickListener {
            val snackbar = Snackbar.make(view, "Character Saved", Snackbar.LENGTH_LONG)
            snackbar.setAction("My Favorite Characters") {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.detailsFragment, false) //  Close detailsFragment on return
                    .build()

                Navigation.findNavController(view)
                    .navigate(R.id.action_detailsFragment_to_favoriteFragments, null, navOptions)
            }
            snackbar.show()
        }

    }




    private fun observeLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { rickmorty ->

            rickmorty?.let {
                binding.tvCharacterName.text = rickmorty.name
                binding.tvCharacterStatus.text = "Status: ${rickmorty.status}"
                binding.tvCharacterSpecies.text = "Species: ${rickmorty.species}"
                binding.tvCharacterGender.text = "Gender: ${rickmorty.gender}"
                binding.tvCharacterCreated.text = "Created: ${rickmorty.created}"

                binding.detailImageView.downloadFromUrl(
                    rickmorty.image,
                    placeholderProgressBar(requireContext())
                )
            }

        })
    }


}