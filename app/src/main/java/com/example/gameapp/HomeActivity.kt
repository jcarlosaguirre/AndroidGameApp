package com.example.gameapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.gameapp.databinding.ActivityHomeBinding
import com.example.gameapp.services.SoundUtils
import com.example.gameapp.services.VideoUtils
import com.example.gameapp.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var videoBg: VideoView
    private lateinit var homeContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeContext = this

        // Iniciar música si no está activada
        if( !SoundUtils.isMusicInit() ) SoundUtils.initBgMusic( applicationContext )


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoBg = findViewById(R.id.videoView)
        VideoUtils.initBgVideo( videoBg, this )

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
                VideoUtils.moveVideoBg( videoBg, homeContext, tab.position)
                Log.i("AAAAAAAAAAAAAAA", tab.position.toString())
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onResume() {
        super.onResume()
        if( SoundUtils.isMusicInit() && !SoundUtils.isMusicPlaying() ) SoundUtils.resumeBgMusic()
        VideoUtils.initBgVideo( videoBg, this )
    }

    override fun onPause() {
        super.onPause()
        SoundUtils.pauseBgMusic()
    }

//    override fun onStop() {
//        super.onStop()
//        SoundUtils.removeBgMusic()
//    }

}