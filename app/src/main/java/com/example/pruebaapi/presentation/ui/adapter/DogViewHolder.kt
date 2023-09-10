package com.example.pruebaapi.presentation.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaapi.databinding.ItemDogBinding

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)

    fun bind(message: String,clickListener: (String, Int) -> Unit) {
        binding.tvDogs.text = message

//   envia Toast con el nombre de la raza que clickeamos
        binding.tvDogs.setOnClickListener {  clickListener(message, binding.tvDogs.id) }
        binding.cardbreed.setOnClickListener { clickListener(message,binding.cardbreed.id) }

    }


}