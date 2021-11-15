package com.example.gameapp

import android.content.Context
import android.media.MediaPlayer
import com.example.gameapp.interfaces.SoundsInterface

class SoundUtils : SoundsInterface {
    
    private var bgMusicInit: Boolean = false;

    private val soundBtn = R.raw.button_click_1
    private lateinit var buttonPlayer: MediaPlayer

    private lateinit var musicPlayer: MediaPlayer
    private val musicBg = R.raw.bg_menu_music

    override fun initBgMusic( context: Context ){

        musicPlayer = MediaPlayer.create(context, musicBg)
        musicPlayer.isLooping = true // Set looping
        musicPlayer.setVolume(50f, 50f)
        musicPlayer.start()

        bgMusicInit = true
    }

    override fun stopBgMusic() {
        musicPlayer.stop()
    }

    override fun resumeBgMusic() {
        musicPlayer.start()
    }

    override fun removeBgMusic() {
        musicPlayer.stop()
        musicPlayer.release()
        bgMusicInit = false
    }

    fun isMusicInit(): Boolean {
        return bgMusicInit
    }
    
    fun isMusicPlaying(): Boolean {
        return musicPlayer.isPlaying
    }

    override fun onClickBtn( activityContext: Context) {

        buttonPlayer = MediaPlayer.create( activityContext, soundBtn)
        buttonPlayer.setVolume(100f, 100f)
        buttonPlayer.start()
    }




}