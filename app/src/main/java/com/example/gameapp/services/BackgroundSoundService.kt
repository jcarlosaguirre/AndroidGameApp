package com.example.gameapp.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.lifecycle.LifecycleObserver
import com.example.gameapp.R

class BackgroundSoundService : Service(), LifecycleObserver {

    private lateinit var musicPlayer: MediaPlayer
    private val musicBg = R.raw.bg_menu_music


    override fun onBind(arg0: Intent): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

//        if( musicPlayer.isPlaying ){
//            musicPlayer.stop()
//            musicPlayer.release()
//        }



        musicPlayer = MediaPlayer.create(this, musicBg)
        musicPlayer.isLooping = true // Set looping
        musicPlayer.setVolume(50f, 50f)
        musicPlayer.start()

        return START_REDELIVER_INTENT
    }

    override fun onStart(intent: Intent, startId: Int) {
        // TO DO
    }


    fun onStop() {
        musicPlayer.stop()
    }

    fun onPause() {
        musicPlayer.pause()
    }

    fun onResume() {
        musicPlayer.start()
    }


    override fun onDestroy() {
        musicPlayer.stop()
        musicPlayer.release()
    }

    override fun onLowMemory() {

    }

    companion object {
        private val TAG: String? = null
    }
}