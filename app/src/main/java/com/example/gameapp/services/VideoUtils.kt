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
 * Static class to control and manipulate music and sounds all over the app
 *
 */
class VideoUtils {


    companion object : VideoControlsInterface {


        // MediaPlayer and array of files uris for background video
        private lateinit var videoMediaPlayer: MediaPlayer
        private lateinit var currentVideoView: VideoView

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

            // Set video on current videoView
            currentVideoView = videoView
            currentVideoView.setVideoURI( videoResources[0] )

            // Use retriever to get properties of video
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource( context, videoResources[0])
            val width: Int =
                Integer.valueOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH))

            // Set video width to current currentVideoView
            currentVideoView.layoutParams.width = width

            // Start video and assign video mediaPlayer to this class mediaPlayer
            currentVideoView.start()
            currentVideoView.setOnPreparedListener { mediaPlayer ->
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

        override fun changeBgVideo() {
            TODO("Not yet implemented")
        }

        override fun resumeBgVideo() {
            currentVideoView.resume()
            currentVideoView.seekTo(mCurrentPosition)
        }
        override fun pauseBgVideo() {
            mCurrentPosition = currentVideoView.currentPosition
            currentVideoView.pause()
        }

        /**
         * Scroll background when sliding to fragment
         *
         * @param videoView
         * @param context
         * @param position
         */
        fun moveVideoBg(videoView: VideoView, context: Context, position: Int){

            Toast.makeText( context, position.toString(), Toast.LENGTH_SHORT)
            currentVideoView.animate().translationX(100F * -position)
        }

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