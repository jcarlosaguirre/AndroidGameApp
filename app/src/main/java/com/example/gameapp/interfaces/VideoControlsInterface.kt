package com.example.gameapp.interfaces

import android.content.Context
import android.widget.VideoView

interface VideoControlsInterface {

    fun initBgVideo(view: VideoView, context: Context )
    fun changeBgVideo()
    fun pauseBgVideo()
}