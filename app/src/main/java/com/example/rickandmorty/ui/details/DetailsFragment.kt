package com.example.rickandmorty.ui.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailsBinding
import com.example.rickandmorty.utils.DateConverter.Companion.convertDate
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
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Character Detail"

        arguments?.let {
            id = DetailsFragmentArgs.fromBundle(it).id
            viewModel.getCharacter(id!!)
        }
        observeLiveData()

        binding.floatingActionButton.setOnClickListener {
            saveCharacter()
            showSnackBar()
        }

    }

    private fun saveCharacter() {
        val character = viewModel.characterLiveData.value
        character?.let {
            viewModel.saveCharacter(it)
        }
    }

    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Character Saved", Snackbar.LENGTH_SHORT)
        snackbar.setAction("My Favorite Characters") {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.detailsFragment, false) //  Close detailsFragment on return
                .build()

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_detailsFragment_to_favoriteFragments, null, navOptions)
        }
        snackbar.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { rickmorty ->

            rickmorty?.let {

                binding.tvCharacterName.text = rickmorty.name
                binding.tvCharacterStatus.text = "Status: ${rickmorty.status}"
                binding.tvCharacterSpecies.text = "Species: ${rickmorty.species}"
                binding.tvCharacterGender.text = "Gender: ${rickmorty.gender}"
                binding.tvCharacterCreated.text = "Created: ${convertDate(rickmorty.created)}"

                val episodes = rickmorty.episode.map { url ->
                    val parts = url.split("/")
                    parts.last().toString()
                }
                binding.tvCharacterEpisode.text = "Episode: ${episodes.get(0)}"

                binding.detailImageView.downloadFromUrl(
                    rickmorty.image,
                    placeholderProgressBar(requireContext())
                )
            }

        })
    }

}