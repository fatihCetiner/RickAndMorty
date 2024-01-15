package com.example.rickandmorty.ui.signin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentHomeFragmentsBinding
import com.example.rickandmorty.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

        activity?.title = getString(R.string.page_sign_in)

        /*
        binding.registerTv.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

         */
    }

        fun goToSignUp(view: View){
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        Log.d("FragmentA", "TextView'a tıklandı, FragmentB'ye geçiş yapılıyor.")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}