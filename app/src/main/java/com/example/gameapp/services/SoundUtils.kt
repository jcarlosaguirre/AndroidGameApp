package com.example.gameapp.services

import android.content.Context
import android.media.MediaPlayer
import com.example.gameapp.R
import com.example.gameapp.interfaces.SoundsControlsInterface


/**
 * Static class to control and manipulate music and sounds all over the app
 *
 */
class SoundUtils {

    companion object : SoundsControlsInterface {

        // MediaPlayers for music and button sounds
        private lateinit var buttonPlayer: MediaPlayer
        private lateinit var musicPlayer: MediaPlayer

        // Audio files path
        private val musicBg = R.raw.bg_menu_music
        private val soundBtn = R.raw.button_click_1

        // Current music initialization state
        private var bgMusicInit: Boolean = false


        /**
         * Create and start button sounds mediaPlayer
         *
         * @param activityCtx
         */
        override fun onClickBtn( activityCtx: Context) {

            buttonPlayer = MediaPlayer.create( activityCtx, soundBtn)
            buttonPlayer.setVolume(100F, 100F)
            buttonPlayer.start()
        }

        /**
         * Create and start background music mediaPlayer
         *
         * @param ctx
         */
        override fun initBgMusic(ctx: Context) {

            musicPlayer = MediaPlayer.create(ctx, musicBg)
            musicPlayer.isLooping = true // Set looping
            musicPlayer.setVolume(50f, 50f)
            musicPlayer.start()

            // Set music boolean to initialized
            bgMusicInit = true
        }

        //
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