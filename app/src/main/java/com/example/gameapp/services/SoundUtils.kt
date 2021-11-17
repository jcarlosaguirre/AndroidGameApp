package com.example.gameapp.services

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import com.example.gameapp.R
import com.example.gameapp.interfaces.SoundsInterface

class SoundUtils : Application() {



    companion object : SoundsInterface {


        private lateinit var buttonPlayer: MediaPlayer
        private lateinit var musicPlayer: MediaPlayer

        private val musicBg = R.raw.bg_menu_music
        private val soundBtn = R.raw.button_click_1

        private var bgMusicInit: Boolean = false




        override fun onClickBtn( activityCtx: Context) {

            buttonPlayer = MediaPlayer.create( activityCtx, soundBtn)
            buttonPlayer.setVolume(100F, 100F)
            buttonPlayer.start()
        }

        fun initBgMusic(ctx: Context) {

            musicPlayer = MediaPlayer.create(ctx, musicBg)
            musicPlayer.isLooping = true // Set looping
            musicPlayer.setVolume(50f, 50f)
            musicPlayer.start()

            bgMusicInit = true
        }

        fun pauseBgMusic() { musicPlayer.pause() }
        fun resumeBgMusic() { musicPlayer.start() }

        fun removeBgMusic() {

            musicPlayer.stop()
            musicPlayer.release()
            bgMusicInit = false
        }

        fun isMusicInit(): Boolean { return bgMusicInit }
        fun isMusicPlaying(): Boolean { return musicPlayer.isPlaying }

    }

}