package com.alexis.ec03_ay.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexis.ec03_ay.R
import com.alexis.ec03_ay.databinding.FragmentApiLogoutBinding
import com.alexis.ec03_ay.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class ApiLogoutFragment : Fragment() {

    private lateinit var binding : FragmentApiLogoutBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApiLogoutBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener {
            cerrarSesion()
        }

    }

    private fun cerrarSesion() {
        firebaseAuth.signOut()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }


}