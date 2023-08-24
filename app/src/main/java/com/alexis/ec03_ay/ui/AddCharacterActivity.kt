package com.alexis.ec03_ay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.alexis.ec03_ay.R
import com.alexis.ec03_ay.databinding.ActivityAddCharacterBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCharacterBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firestore = Firebase.firestore

        binding.btnRegisterCharacter.setOnClickListener {
            val name = binding.tilNameCharacter.editText?.text.toString()
            val status = binding.tilStatusCharacter.editText?.text.toString()
            val species = binding.tilSpeciesCharacter.editText?.text.toString()
            val gender = binding.tilGenderCharacter.editText?.text.toString()
            val isFavorite = binding.switchFavorite.isActivated
            if(name.isNotEmpty() && status.isNotEmpty() && species.isNotEmpty() && gender.isNotEmpty()){
                addToFireStore(name,status,species,gender,isFavorite)
            }
        }
    }

    private fun addToFireStore(name: String, status: String, species: String, gender: String, favorite: Boolean) {
        val newCharacter = hashMapOf<String,Any>(
            "name" to  name,
            "status" to status,
            "species" to species,
            "gender" to gender,
            "isFavorite" to favorite
        )
        firestore.collection("character").add(newCharacter)
            .addOnSuccessListener {
                Toast.makeText(this,"Nota con ID : ${it.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Ocurrio un Error !!", Toast.LENGTH_SHORT).show()
            }


    }
    private fun getNoteFireStore(){
        firestore.collection("character").get()
            .addOnSuccessListener {
                for(document in it.documents){
                    Log.d("Characters Firebase",document.id)
                }
            }
    }


}