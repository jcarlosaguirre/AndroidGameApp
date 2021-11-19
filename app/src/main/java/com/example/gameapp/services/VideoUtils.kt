package com.example.gameapp.services

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.VideoView
import com.example.gameapp.R
import com.example.gameapp.interfaces.VideoControlsInterface

class VideoUtils {


    companion object : VideoControlsInterface {


        // Video/Music Resources
        private lateinit var videoMediaPlayer: MediaPlayer
        var videoUri: Uri? = Uri.parse("android.resource://com.example.gameapp/" + R.raw.main_video_bg_vert_2)!!

        private var mCurrentPosition: Int = 0


//        fun removeBgMusic() {
//
//            musicPlayer.stop()
//            musicPlayer.release()
//            bgMusicInit = false
//        }

        override fun initBgVideo(videoView: VideoView, context: Context) {

            videoView.setVideoURI( videoUri )
            videoView.start()

            videoView.setOnPreparedListener { mediaPlayer ->
                run {
                    videoMediaPlayer = mediaPlayer
                    videoMediaPlayer.isLooping = true

                    if (mCurrentPosition != 0){
                        videoMediaPlayer.seekTo(mCurrentPosition)
                        videoMediaPlayer.start()
                    }
                }
            }
        }

        override fun changeBgVideo() {
            TODO("Not yet implemented")
        }

        override fun pauseBgVideo() {
            videoMediaPlayer.stop()
        }
    }
}