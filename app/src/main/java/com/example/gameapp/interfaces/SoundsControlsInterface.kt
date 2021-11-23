package com.example.gameapp.interfaces

import android.content.Context

interface SoundsControlsInterface {

    // Funciones a implementar en la clase que extienda esta interfaz
    fun onClickBtn( context: Context )
    fun initBgMusic( context: Context )
    fun resumeBgMusic()
    fun pauseBgMusic()
}