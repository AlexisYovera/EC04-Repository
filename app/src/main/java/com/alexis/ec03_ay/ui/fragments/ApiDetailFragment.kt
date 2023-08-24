package com.alexis.ec03_ay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.alexis.ec03_ay.R
import com.alexis.ec03_ay.databinding.FragmentApiDetailBinding
import com.alexis.ec03_ay.model.Character
import com.alexis.ec03_ay.ui.viewmodels.CharacterListViewModel
import com.google.android.material.snackbar.Snackbar

class ApiDetailFragment : Fragment() {

    private lateinit var binding: FragmentApiDetailBinding
    private val args: ApiDetailFragmentArgs by navArgs()
    private lateinit var character: Character
    private lateinit var viewModel: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = args.character
        viewModel = ViewModelProvider(requireActivity())[CharacterDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApiDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtNamech.text = "Nombre : "+character.name
        binding.txtLabels.text = "Genero : "+character.gender
        binding.txtCharacter.text = "Raza :" + character.species + "\n" +"Estado : "+character.status
        if(character.isFavorite){
            binding.btnAddFavorites.visibility = View.GONE
        }
        binding.btnAddFavorites.setOnClickListener {
            character.isFavorite = true
            viewModel.addFavorites(character)
            Snackbar.make(binding.root, "Agregado a Favoritos", Snackbar.LENGTH_SHORT).show()

        }
    }

}