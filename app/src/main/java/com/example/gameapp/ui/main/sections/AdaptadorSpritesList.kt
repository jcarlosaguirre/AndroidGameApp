package com.example.gameapp.ui.main.sections

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.gameapp.R
import com.example.gameapp.classes.SpriteObject

/**
 * TODO
 *
 * @property context
 * @property knightSprites
 */
class AdaptadorSpritesList(
    private val context: Activity,
    private val knightSprites: ArrayList<SpriteObject>
) : ArrayAdapter<SpriteObject>(context, R.layout.sprite_room, knightSprites) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(
            R.layout.sprite_room,
            null
        )


        /*Aquí no podemos usar binding porque no tenemos un Activity asociado.*/
        val imgFoto: ImageView = view.findViewById(R.id.spriteModel)
        val knight = knightSprites[position]
        imgFoto.setImageResource(knight.sprite)

        return view
    }
}