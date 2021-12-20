package com.example.gameapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.viewpager.widget.ViewPager
import com.example.gameapp.classes.SpriteObject
import com.example.gameapp.databinding.ActivityHomeBinding
import com.example.gameapp.interfaces.APIService
import com.example.gameapp.interfaces.OnFragmentActionsListener
import com.example.gameapp.services.SoundUtils
import com.example.gameapp.services.VideoUtils
import com.example.gameapp.ui.main.SectionsPagerAdapter
import com.example.gameapp.ui.main.sections.AdaptadorAdventures
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Home Activity
 *
 * After login, user has a menu to choose which section is interested in.
 *
 */
class HomeActivity : AppCompatActivity(), OnFragmentActionsListener, SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var videoBg: VideoView
    private lateinit var homeContext: Context

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout


    private lateinit var adventuresAdapter: AdaptadorAdventures
    private val images = arrayListOf<String>()


    // Array of chosen characters
    private var characterSelection: Array<SpriteObject?> = arrayOf(null, null, null, null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeContext = this

        // Start music if it's not
        if( !SoundUtils.isMusicInit() ) SoundUtils.initBgMusic( applicationContext )

//      Binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//      Start VideoView for background video
        videoBg = findViewById(R.id.videoView)
        VideoUtils.initBgVideo( videoBg, this )

//      Set tabs content
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onClickFragmentButton( "HomeSection", tab.position, null )
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        viewPager.currentItem = 1

//      TopBar properties
        var topAppBar = binding.topAppBar

        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    binding.searchbar.visibility = if (binding.searchbar.visibility == View.GONE) View.VISIBLE else View.GONE
                    true
                }
                R.id.backFromSection -> {
                    // Handle more item (inside overflow menu) press
                    viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
        binding.searchbar.setOnQueryTextListener( this )
    }

    /**
     * Resume music and video on background
     *
     */
    override fun onResume() {
        super.onResume()
        if( SoundUtils.isMusicInit() && !SoundUtils.isMusicPlaying() ) SoundUtils.resumeBgMusic()
        if ( VideoUtils.isBgVideoInit() ) {
            VideoUtils.resumeBgVideo( videoBg )
        }
    }

    /**
     * Pause music and video on background
     *
     */
    override fun onPause() {
        super.onPause()
        SoundUtils.pauseBgMusic()
        VideoUtils.pauseBgVideo( videoBg )
    }


    /**
     * Set section title and hide views if necessary
     *
     * @param id
     */
    override fun onClickFragmentButton( detail: String, id: Int, data: Unit? ) {

        if( detail == "HomeSection" ){

            if( id == 0 ){ binding.topAppBar.title = "Character" }
            else if( id == 1 ){ binding.topAppBar.title = "Lobby" }
            else if( id == 2 ){ binding.topAppBar.title = "Adventures" }

            VideoUtils.moveVideoBg( videoBg, homeContext, id )
            viewPager.currentItem = id

            // Get "goBack" icon and hide if necessary
            var backIcon: ActionMenuItemView = findViewById(R.id.backFromSection)

            if( id == 1 ) backIcon.visibility = View.GONE
            else {

                // Rotate if user is in right fragment
                if(id == 2) backIcon.rotation = 180f
                else backIcon.rotation = 0f
                backIcon.visibility = View.VISIBLE
            }
        }
        else if( detail == "CharacterSelection" ){

            setTeamMembers( data as Array<SpriteObject?> )
            Log.i("UUUUUU--------", "Holaaaa.-----------------")
//            Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show()
        }



    }

    override fun onCloseFragment() {
        TODO("Not yet implemented")
    }

    fun setTeamMembers( characters: Array<SpriteObject?> ){
        characterSelection = characters
    }

    fun getTeamMembers(): Array<SpriteObject?>{

        return characterSelection
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String){

        CoroutineScope(Dispatchers.IO).launch {

            Log.i("-----TEXTO----", query)
            val call = getRetrofit().create(APIService::class.java).getAdventuresImages("$query/images")
            val puppies = call.body()

            runOnUiThread {

                if (call.isSuccessful){

                    val imagess = puppies?.imageUrls ?: emptyList()
                    images.clear()
                    images.addAll(imagess)
                    adventuresAdapter.notifyDataSetChanged()


                }
                else{
                    Toast.makeText(homeContext, "Un error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun getAdapter(): AdaptadorAdventures{

        adventuresAdapter = AdaptadorAdventures(images)
        return adventuresAdapter
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if( !query.isNullOrEmpty() ){
            searchByName( query )
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


}