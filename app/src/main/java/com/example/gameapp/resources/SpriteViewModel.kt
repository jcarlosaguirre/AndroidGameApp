package com.example.gameapp.resources

import androidx.lifecycle.ViewModel
import com.example.gameapp.R


// ViewModel para recoger los datos necesarios desde las clases oportunas
class SpriteViewModel : ViewModel() {



    /*Creamos un par de ptroductos*/
    private val knight1 = SpriteObject("Male 1", "knight", "Testing knight 1", R.drawable.male_1_2, R.drawable.male_1_animation )
    private val knight2 = SpriteObject("Male 2", "assassin", "Testing knight 2", R.drawable.male_2_2, R.drawable.male_2_animation )
    private val knight3 = SpriteObject("Male 3", "soldier", "Testing knight 1", R.drawable.male_3_2, R.drawable.male_3_animation )
    private val knight4 = SpriteObject("Female 1", "mage", "Testing knight 2", R.drawable.female_1_2, R.drawable.female_1_animation )
    private val knight5 = SpriteObject("Male 4", "dark knight", "Testing knight 1", R.drawable.male_3_2, R.drawable.male_3_animation )
    private val knight6 = SpriteObject("Female 2", "sorcerer", "Testing knight 2", R.drawable.female_1_2, R.drawable.female_1_animation )
    private val knight7 = SpriteObject("Male 1", "knight", "Testing knight 1", R.drawable.male_1_2, R.drawable.male_1_animation )
    private val knight8 = SpriteObject("Male 2", "assassin", "Testing knight 2", R.drawable.male_2_2, R.drawable.male_2_animation )
    private val knight9 = SpriteObject("Male 3", "soldier", "Testing knight 1", R.drawable.male_3_2, R.drawable.male_3_animation )
    private val knight10 = SpriteObject("Female 1", "mage", "Testing knight 2", R.drawable.female_1_2, R.drawable.female_1_animation )
    private val knight11 = SpriteObject("Male 4", "dark knight", "Testing knight 1", R.drawable.male_3_2, R.drawable.male_3_animation )
    private val knight12 = SpriteObject("Female 2", "sorcerer", "Testing knight 2", R.drawable.female_1_2, R.drawable.female_1_animation )

    private val _knightSprites: ArrayList<SpriteObject> =
        arrayListOf(
            knight1,
            knight2,
            knight3,
            knight4,
            knight5,
            knight6,
            knight7,
            knight8,
            knight9,
            knight10,
            knight11,
            knight12
        )

    val knightSprites: ArrayList<SpriteObject> = _knightSprites;
}