package com.example.gameapp.interfaces

import android.content.Context

interface SoundsControlsInterface {

    fun onClickBtn( context: Context )
    fun initBgMusic( context: Context )
    fun resumeBgMusic()
    fun pauseBgMusic()
}