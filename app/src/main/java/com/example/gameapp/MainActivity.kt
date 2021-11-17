package com.example.gameapp

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
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
import com.example.gameapp.ui.login.LoginFragment


class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoBg: VideoView

    // Video/Music Resources
    private lateinit var videoMediaPlayer: MediaPlayer
    var videoUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.main_video_bg_vert_2)

    private var mCurrentPosition: Int = 0
    private var menuButtons = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//      Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//      Video background
        videoBg = findViewById(R.id.videoView)
        videoBg.setVideoURI( videoUri )
        videoBg.start()

        videoBg.setOnPreparedListener { mediaPlayer ->
            run {
                videoMediaPlayer = mediaPlayer
                videoMediaPlayer.isLooping = true

                if (mCurrentPosition != 0){
                    videoMediaPlayer.seekTo(mCurrentPosition)
                    videoMediaPlayer.start()
                }
            }
        }

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

        menuButtons[1].setOnClickListener{
            clickButton( it )
        }

        menuButtons[2].setOnClickListener{
            clickButton( it )
        }

    }


    override fun onPause() {
        super.onPause()
        videoBg.pause()

        SoundUtils.pauseBgMusic()
    }

    override fun onResume() {
        super.onResume()
        videoBg.start()

        if( SoundUtils.isMusicInit() &&
            !SoundUtils.isMusicPlaying()
        )
            SoundUtils.resumeBgMusic()

    }

//    override fun onStop() {
//        super.onStop()
//        SoundUtils.removeBgMusic()
//        Log.i("stop", "SSSSSTTTOOOOOPPPP")
//    }

    override fun onDestroy() {
        super.onDestroy()
        videoMediaPlayer.release()
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

        binding.mainMenuButtons.animate().translationX(600F)

        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace( binding.menuFragmentContainer.id, fragment)
        fragmentIntercambio.commit()
    }

    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }

    override fun onCloseFragment() {

        binding.mainMenuButtons.animate().translationX(0F)
    }


}