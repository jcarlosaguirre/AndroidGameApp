package com.example.gameapp.interfaces

import android.content.Context
import android.widget.VideoView

/**
 * Control video background over the app
 *
 */
interface VideoControlsInterface {

    // Funciones a implementar en la clase que extienda esta interfaz
    fun initBgVideo(view: VideoView, context: Context )
    fun changeBgVideo( videoView: VideoView )
    fun pauseBgVideo( videoView: VideoView )
    fun resumeBgVideo( videoView: VideoView )
}