package com.dev.forvaritepet.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.forvaritepet.databinding.PetItemBinding
import com.dev.forvaritepet.model.Pet
import com.dev.forvaritepet.petstore.PetStoreFragment
import com.dev.forvaritepet.repository.PetData


class PetsAdapter(
    private val petStoreFragment: PetStoreFragment,
    val onItemClickListener: (Pet) -> (Unit)
) : RecyclerView.Adapter<PetsAdapter.ViewHolder>() {

    private val pets: List<Pet?> by lazy {
        PetData.getListOfPets(petStoreFragment.requireContext())
    }

    inner class ViewHolder(private val binding: PetItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: Pet) {
            binding.apply {
                this.pet = pet
                container.setOnClickListener {
                    onItemClickListener(pet)
                    Log.d("onItemClickListener", "onItemClickListener: " + pet.name)
                }
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PetItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (null != pets[position]) {
            holder.bind(pets[position]!!)
        }
    }

    override fun getItemCount() = pets.size
}