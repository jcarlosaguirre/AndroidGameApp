package com.example.gameapp.interfaces

import android.content.Context
import android.widget.VideoView

interface VideoControlsInterface {

    // Funciones a implementar en la clase que extienda esta interfaz
    fun initBgVideo(view: VideoView, context: Context )
    fun changeBgVideo()
    fun pauseBgVideo()
}