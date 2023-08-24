package com.alexis.ec03_ay.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alexis.ec03_ay.R
import com.alexis.ec03_ay.databinding.FragmentApiListBinding
import com.alexis.ec03_ay.ui.AddCharacterActivity

import com.alexis.ec03_ay.ui.viewmodels.CharacterListViewModel

class ApiListFragment : Fragment() {

    private lateinit var binding: FragmentApiListBinding
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CharacterListViewModel::class.java]



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiListBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVApiListAdapter(listOf()){ character ->
            val destination = ApiListFragmentDirections.actionApiListFragmentToApiDetailFragment(character)
            findNavController().navigate(destination)
        }
        binding.rvApiList.adapter = adapter
        viewModel.characters.observe(requireActivity()){
            adapter.characters = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getCharactersFromService()
    }

}