package com.alexis.ec03_ay.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alexis.ec03_ay.databinding.ItemApiBinding
import com.alexis.ec03_ay.model.Character

class RVApiListAdapter(var characters: List<Character>, val onCharacterClick: (Character) -> Unit ): RecyclerView.Adapter<ApiVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiVH {
        val binding = ItemApiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ApiVH(binding, onCharacterClick)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ApiVH, position: Int) {
        holder.bind(characters[position])
    }
}

class  ApiVH(private val binding: ItemApiBinding, val onCharacterClick: (Character) -> Unit ): ViewHolder(binding.root){
    fun bind(character: Character) {
        binding.txtApiName.text =       "Nombre  : " + character.name
        binding.txtApiStatus.text =     "Estado  : " + character.status
        binding.txtApiSpecies.text =    "Especie : " + character.species
        binding.txtApiGender.text =     "Genero  : " + character.gender
        binding.root.setOnClickListener {
            onCharacterClick(character)
        }
    }

}