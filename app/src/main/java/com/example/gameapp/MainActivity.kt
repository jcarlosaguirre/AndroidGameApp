package com.example.gameapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gameapp.R.drawable.button_bg
import com.example.gameapp.R.drawable.button_bg_click
import com.example.gameapp.databinding.ActivityMainBinding
import com.example.gameapp.interfaces.OnFragmentActionsListener
import com.example.gameapp.services.SoundUtils
import com.example.gameapp.services.VideoUtils
import com.example.gameapp.ui.login.LoginFragment


class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoBg: VideoView


    private var menuButtons = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//      Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//      Video background
        videoBg = findViewById(R.id.videoView)
        VideoUtils.initBgVideo( videoBg, this )

//      Main Menu
        menuButtons.add(0, binding.menuBtn1)
        menuButtons.add(1, binding.menuBtn2)
        menuButtons.add(2, binding.menuBtn3)

        menuButtons[1].setTextColor(Color.LTGRAY)
        menuButtons[2].setTextColor(Color.LTGRAY)

//        Al primer boton se le añade funcionalidad extra
        menuButtons[0].setOnClickListener{
            clickButton( it )
            cargarFragment( LoginFragment() )
        }

        menuButtons.forEachIndexed{ index, btn ->

            btn.setOnClickListener{
                clickButton( it )
                if (index == 0) cargarFragment( LoginFragment() )
            }
        }

    }




    override fun onPause() {
        super.onPause()
        SoundUtils.pauseBgMusic()
    }

    override fun onResume() {
        super.onResume()

        if( SoundUtils.isMusicInit() &&
            !SoundUtils.isMusicPlaying()
        )
            SoundUtils.resumeBgMusic()

//      Video background
        videoBg = findViewById(R.id.videoView)
        VideoUtils.initBgVideo( videoBg, this )

    }

//    override fun onStop() {
//        super.onStop()
//        SoundUtils.removeBgMusic()
//        Log.i("stop", "SSSSSTTTOOOOOPPPP")
//    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun resetButtons(){

        for ( button in menuButtons ){
            button.setBackgroundResource( button_bg )
            button.animate().scaleX(1F)
            button.animate().scaleY(1F)
        }
    }

    fun clickButton(view: View){

        SoundUtils.onClickBtn( applicationContext )

        resetButtons()

        view.setBackgroundResource( button_bg_click )
        view.animate().scaleX(1.05F)
        view.animate().scaleY(1.05F)

    }

    fun cargarFragment(fragment: Fragment){

        // Al cargar un fragment, desplaza el menú
        binding.mainMenuButtons.animate().translationX(600F)

        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace( binding.menuFragmentContainer.id, fragment)
        fragmentIntercambio.commit()
    }

    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }

    override fun onCloseFragment() {

        // Reposiciona el menú al cerrar el fragment
        binding.mainMenuButtons.animate().translationX(0F)
    }


}