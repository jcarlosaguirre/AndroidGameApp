package com.example.gameapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.services.SoundUtils
import kotlin.concurrent.thread

 class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        SoundUtils.initBgMusic( applicationContext )

        thread {

            try {
                Thread.sleep(3000)
            } catch (err: Exception){
                err.printStackTrace()
            } finally {

                val mainIntent = Intent( this, MainActivity::class.java )
                startActivity( mainIntent )
            }
        }

    }
}