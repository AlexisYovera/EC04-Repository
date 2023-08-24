package com.alexis.ec03_ay.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexis.ec03_ay.databinding.ItemApiBinding
import com.google.firebase.firestore.DocumentSnapshot

class RVApiListFirebaseAdapter(private val dataList: MutableList<DocumentSnapshot>): RecyclerView.Adapter<RVApiListFirebaseAdapter.ApiFBVH>() {

    class  ApiFBVH(private val binding: ItemApiBinding ): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: DocumentSnapshot) {
            binding.txtApiName.text = "Nombre  : " + character.getString("name")
            binding.txtApiStatus.text = "Estado  : " + character.getString("status")
            binding.txtApiSpecies.text = "Especie : " + character.getString("species")
            binding.txtApiGender.text = "Genero  : " + character.getString("gender")

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiFBVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemApiBinding.inflate(inflater,parent,false)
        return ApiFBVH(binding)
    }

    override fun onBindViewHolder(holder: ApiFBVH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}