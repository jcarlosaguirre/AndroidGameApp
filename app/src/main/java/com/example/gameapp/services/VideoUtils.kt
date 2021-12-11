package com.example.gameapp.services

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import android.widget.VideoView
import com.example.gameapp.R
import com.example.gameapp.interfaces.VideoControlsInterface




class VideoUtils {


    // Clase estática para controlar el estado del video de fondo
    companion object : VideoControlsInterface {


        // Video Resources
        private lateinit var videoMediaPlayer: MediaPlayer
        var videoUri: Uri? = Uri.parse("android.resource://com.example.gameapp/" + R.raw.main_video_bg)!!

        // Progreso actual del video
        private var mCurrentPosition: Int = 0


        // Inciar el video de fondo
        override fun initBgVideo(videoView: VideoView, context: Context) {


            videoView.setVideoURI( videoUri )
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource( context, videoUri)
            val width: Int =
                Integer.valueOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH))

            videoView.layoutParams.width = width

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

        // Pausar la reproducción del video
        override fun pauseBgVideo() {
            videoMediaPlayer.stop()
        }

        fun moveVideoBg(videoView: VideoView, context: Context, position: Int){

            Toast.makeText( context, position.toString(), Toast.LENGTH_SHORT)
            videoView.animate().translationX(100F * -position)
        }
    }
}