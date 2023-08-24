package com.alexis.ec03_ay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.ec03_ay.databinding.FragmentApiListFiresotreBinding
import com.alexis.ec03_ay.ui.viewmodels.CharacterListViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ApiListFiresotreFragment : Fragment() {

    private lateinit var binding: FragmentApiListFiresotreBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CharacterListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApiListFiresotreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()

        // Realizar consulta a Firestore

        firestore.collection("character")
            .get()
            .addOnSuccessListener { documents ->
                val dataList = documents.documents
                val adapter = RVApiListFirebaseAdapter(dataList)
                binding.rvFirebaseList.layoutManager = LinearLayoutManager(requireContext())
                binding.rvFirebaseList.adapter = adapter
            }
            .addOnFailureListener { exception ->
                // Manejo de error en caso de fallo en la consulta
            }
    }


}