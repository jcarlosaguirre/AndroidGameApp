package com.example.gameapp.interfaces

import android.content.Context

/**
 * Control music and sounds over the app
 *
 */
interface SoundsControlsInterface {

    fun onClickBtn( context: Context )
    fun initBgMusic( context: Context )
    fun resumeBgMusic()
    fun pauseBgMusic()
}