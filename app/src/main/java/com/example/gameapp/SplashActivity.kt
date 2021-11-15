package com.example.gameapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {

    var soundUtils: SoundUtils = SoundUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        thread {

            try {
                Thread.sleep(3000)
            } catch (err: Exception){
                err.printStackTrace()
            } finally {
//                intent = Intent(this, BackgroundSoundService::class.java)
//                startService(intent)

                soundUtils.initBgMusic( applicationContext )

                val mainIntent = Intent( this, MainActivity::class.java )
                startActivity( mainIntent )
            }
        }

    }
}