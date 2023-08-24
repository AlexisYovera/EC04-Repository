package com.alexis.ec03_ay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alexis.ec03_ay.databinding.FragmentApiFavBinding
import com.alexis.ec03_ay.ui.viewmodels.CharacterListViewModel

class ApiFavFragment : Fragment() {

    private lateinit var binding: FragmentApiFavBinding
    private lateinit var viewModel: CharacterFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CharacterFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiFavBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVApiListAdapter(listOf()){ character ->
            val destination = ApiFavFragmentDirections.actionApiFavFragmentToApiDetailFragment(character)
            findNavController().navigate(destination)
        }
        binding.rvFavorites.adapter = adapter
        viewModel.favorites.observe(requireActivity()){
            adapter.characters = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }

}