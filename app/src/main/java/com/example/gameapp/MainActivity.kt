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
import com.example.gameapp.ui.login.LoginFragment


class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoBg: VideoView
    private lateinit var videoMediaPlayer: MediaPlayer
//    private lateinit var musicMediaPlayer: MediaPlayer
//    private lateinit var effectsMediaPlayer: MediaPlayer
    private var mCurrentPosition: Int = 0

    private var soundUtils: SoundUtils = SoundUtils()

    var menuButtons = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//      Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Video/Music Resources
        var videoUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.main_video_bg_vert_2)
        var musicUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.bg_menu_music)
        var btnEffectUri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.button_click_1)

//      Button effects
//        effectsMediaPlayer = MediaPlayer.create(this, btnEffectUri)
//        effectsMediaPlayer.setVolume(1F, 1F)


//      Music background
//        musicMediaPlayer = MediaPlayer.create(this, musicUri)
//        musicMediaPlayer.setVolume(0.5F, 0.5F)
//        musicMediaPlayer.isLooping = true
//        musicMediaPlayer.start()

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
        menuButtons.add(0, binding.innerContent.menuBtn1)
        menuButtons.add(1, binding.innerContent.menuBtn2)
        menuButtons.add(2, binding.innerContent.menuBtn3)

        menuButtons[1].setTextColor(Color.LTGRAY)
        menuButtons[2].setTextColor(Color.LTGRAY)

        menuButtons[0].setOnClickListener{
            clickButton( it )
            cargarFragment( LoginFragment() )
        }

    }

    override fun onPause() {
        super.onPause()
//        musicMediaPlayer.pause()
        videoBg.pause()

        soundUtils.stopBgMusic()
    }

    override fun onResume() {
        super.onResume()
        videoBg.start()
//        musicMediaPlayer.start()

        if( soundUtils.isMusicInit() && !soundUtils.isMusicPlaying() ) soundUtils.resumeBgMusic()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoMediaPlayer.release()
        soundUtils.removeBgMusic()
//        musicMediaPlayer.release()
//        effectsMediaPlayer.release()
    }



    fun resetButtons(){

        for ( i in menuButtons ){
            i.setBackgroundResource( button_bg )
            i.animate().scaleX(1F)
            i.animate().scaleY(1F)
        }
    }

    fun clickButton(view: View){

//        effectsMediaPlayer.start()

        soundUtils.onClickBtn( applicationContext )

        resetButtons()

        view.setBackgroundResource( button_bg_click )
        view.animate().scaleX(1.05F)
        view.animate().scaleY(1.05F)

    }

    fun cargarFragment(fragment: Fragment){

        binding.innerContent.mainMenuButtons.animate().translationX(600F)

        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace( binding.innerContent.menuFragmentContainer.id, fragment)
        fragmentIntercambio.commit()
    }

    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }

    override fun onCloseFragment() {

        binding.innerContent.mainMenuButtons.animate().translationX(0F)
    }


}