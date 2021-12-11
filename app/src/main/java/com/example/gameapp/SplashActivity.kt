package com.example.gameapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.services.SoundUtils
import kotlin.concurrent.thread

/**
 * Splash Screen Activity
 *
 * Show blank screen for x seconds while loading data before entering the
 * main activity.
 *
 */
 class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Start background music
        SoundUtils.initBgMusic( applicationContext )

        // Start a new Thread for loading required data and then change activity
        thread {

            try {
                Thread.sleep(1000)
            } catch (err: Exception){
                err.printStackTrace()
            } finally {

                val mainIntent = Intent( this, MainActivity::class.java )
                startActivity( mainIntent )
            }
        }

    }
}