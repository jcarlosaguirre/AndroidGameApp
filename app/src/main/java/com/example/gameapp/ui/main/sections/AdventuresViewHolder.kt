package com.example.gameapp.ui.main.sections

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class AdventuresViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind(image:String){

        Picasso.get().load(image).into(binding.ivDog)
    }
}