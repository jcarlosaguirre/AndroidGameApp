package com.example.gameapp.services

import android.content.Context
import android.media.MediaPlayer
import com.example.gameapp.R
import com.example.gameapp.interfaces.SoundsControlsInterface

class SoundUtils {

    // Clase estática para acceder a los archivos de audio desde cualquier parte de la aplicación
    companion object : SoundsControlsInterface {


        private lateinit var buttonPlayer: MediaPlayer
        private lateinit var musicPlayer: MediaPlayer

        private val musicBg = R.raw.bg_menu_music
        private val soundBtn = R.raw.button_click_1

        private var bgMusicInit: Boolean = false



        // Reproducir sonido de boton
        override fun onClickBtn( activityCtx: Context) {

            buttonPlayer = MediaPlayer.create( activityCtx, soundBtn)
            buttonPlayer.setVolume(100F, 100F)
            buttonPlayer.start()
        }

        // Controles para la musica de fondo de la aplicación
        override fun initBgMusic(ctx: Context) {

            musicPlayer = MediaPlayer.create(ctx, musicBg)
            musicPlayer.isLooping = true // Set looping
            musicPlayer.setVolume(50f, 50f)
            musicPlayer.start()

            bgMusicInit = true
        }

        override fun pauseBgMusic() { musicPlayer.pause() }

        override fun resumeBgMusic() { musicPlayer.start() }

//        fun removeBgMusic() {
//
//            musicPlayer.stop()
//            musicPlayer.release()
//            bgMusicInit = false
//        }

        // Consultar el estado de la aplicación
        fun isMusicInit(): Boolean { return bgMusicInit }
        fun isMusicPlaying(): Boolean { return musicPlayer.isPlaying }

    }

}