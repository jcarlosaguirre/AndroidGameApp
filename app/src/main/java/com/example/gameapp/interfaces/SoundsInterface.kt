package com.example.gameapp.interfaces

import android.content.Context

interface SoundsInterface {

    fun initBgMusic(context: Context)

    fun stopBgMusic()

    fun resumeBgMusic()

    fun removeBgMusic()

    fun onClickBtn( context: Context )
}