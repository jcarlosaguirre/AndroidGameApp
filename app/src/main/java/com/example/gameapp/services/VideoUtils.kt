package com.example.gameapp.services

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import com.example.gameapp.R
import com.example.gameapp.interfaces.VideoControlsInterface


/**
 * Static class to control and manipulate background video all over the app
 *
 */
class VideoUtils {


    companion object : VideoControlsInterface {

        // MediaPlayer and array of files uris for background video
        private lateinit var videoMediaPlayer: MediaPlayer

        private val videoResources: Array<Uri> = arrayOf(
            Uri.parse("android.resource://com.example.gameapp/" + R.raw.main_video_bg)
        )

        // Current video progress
        private var mCurrentPosition: Int = 0

        // Current music initialization state
        private var bgVideoInit: Boolean = false


        /**
         * Set video on current videoView and assign it to video player
         *
         * @param activityCtx
         */
        override fun initBgVideo(videoView: VideoView, context: Context) {

            Log.i("CREADO", "--------VIDEO CREADO----------")

            // Set video on current videoView
            videoView.setVideoURI( videoResources[0] )

            // Use retriever to get properties of video
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource( context, videoResources[0])
            val width: Int =
                Integer.valueOf( retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)!! )

            // Set video width to current currentVideoView
            videoView.layoutParams.width = width

            // Start video and assign video mediaPlayer to this class mediaPlayer
            videoView.start()
            videoView.setOnPreparedListener { mediaPlayer ->
                run {
                    videoMediaPlayer = mediaPlayer
                    videoMediaPlayer.isLooping = true

                    Log.i("TIEMPO", mCurrentPosition.toString() )

                    if (mCurrentPosition != 0){
                        videoMediaPlayer.seekTo(mCurrentPosition)
                        videoMediaPlayer.start()
                        bgVideoInit = true
                    }
                }
            }
        }

        /**
         * Change background video resource
         *
         * @param videoView
         */
        override fun changeBgVideo( videoView: VideoView ) {
            TODO("Not yet implemented")
        }

        /**
         * Resume video on current videoView
         *
         * @param videoView
         */
        override fun resumeBgVideo( videoView: VideoView ) {
            Log.i("RESUME", "----------VIDEO ACTIVO DE NUEVO---------")
            videoView.resume()
            videoView.seekTo(mCurrentPosition)
        }

        /**
         * Pause current videoView
         *
         * @param videoView
         */
        override fun pauseBgVideo( videoView: VideoView ) {
            mCurrentPosition = videoView.currentPosition
            videoView.pause()
        }

        /**
         * Scroll background when sliding to fragment
         *
         * @param videoView
         * @param context
         * @param position
         */
        fun moveVideoBg( videoView: VideoView, context: Context, position: Int){

            Toast.makeText( context, position.toString(), Toast.LENGTH_SHORT)
            videoView.animate().translationX(100F * -position)
        }

        /**
         * Check different background video states
         *
         * @return
         */
        fun getVideoBgState(): Int{ return mCurrentPosition }
        fun isBgVideoInit(): Boolean{
            return try {
                bgVideoInit
            } catch ( err: IllegalStateException ) {
                false
            }
        }
    }
}