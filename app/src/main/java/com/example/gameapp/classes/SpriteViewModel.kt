package com.example.gameapp.classes

import androidx.lifecycle.ViewModel
import com.example.gameapp.R


/**
 * Prepare different Sprites for character selection.
 * Create an accesible Sprites array to show them in necessary views.
 */
class SpriteViewModel : ViewModel() {


    private val knight1 = SpriteObject("Male 1", 0, "Knight", R.drawable.male_1_2, R.drawable.male_1_animation )
    private val knight2 = SpriteObject("Male 2", 1, "Assassin", R.drawable.male_2_2, R.drawable.male_2_animation )
    private val knight4 = SpriteObject("Female 1", 2, "Mage", R.drawable.female_1_2, R.drawable.female_1_animation )
    private val knight5 = SpriteObject("Male 4", 3, "Dark knight", R.drawable.male_3_2, R.drawable.male_3_animation )
    private val knight7 = SpriteObject("Male 1", 0, "knight", R.drawable.male_1_2, R.drawable.male_1_animation )
    private val knight8 = SpriteObject("Male 2", 1, "Assassin", R.drawable.male_2_2, R.drawable.male_2_animation )
    private val knight10 = SpriteObject("Female 1", 2, "Mage", R.drawable.female_1_2, R.drawable.female_1_animation )
    private val knight11 = SpriteObject("Male 4", 3, "Dark knight", R.drawable.male_3_2, R.drawable.male_3_animation )

    private val _knightSprites: ArrayList<SpriteObject> =
        arrayListOf(
            knight1,
            knight2,
            knight4,
            knight5,
            knight7,
            knight8,
            knight10,
            knight11,
        )

    val knightSprites: ArrayList<SpriteObject> = _knightSprites;
}