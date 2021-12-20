package com.example.gameapp.ui.main.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class AdaptadorAdventures( private val images: List<String> ) : RecyclerView.Adapter<AdventuresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventuresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AdventuresViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: AdventuresViewHolder, position: Int) {
        val item = images[position]
        holder.bind( item )
    }

    override fun getItemCount(): Int = images.size



}