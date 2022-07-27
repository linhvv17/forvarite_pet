package com.dev.forvaritepet.pet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.dev.forvaritepet.R
import com.dev.forvaritepet.databinding.FragmentPetBinding
import com.dev.forvaritepet.model.Pet
import com.dev.forvaritepet.repository.PetData

class PetFragment : Fragment() {

    private lateinit var binding: FragmentPetBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPetBinding.inflate(layoutInflater).apply {
            petFragment = this@PetFragment

            this.lifecycleOwner = lifecycleOwner
        }
        return binding.root
    }

    fun getPets(): Pet {
        return PetData.getMyPet(requireContext())
    }




    fun passToPetStoreFragment(){

        findNavController().navigate(R.id.petStoreFragment)
    }

}

