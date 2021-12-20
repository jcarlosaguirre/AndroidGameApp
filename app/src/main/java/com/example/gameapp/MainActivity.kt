package com.example.gameapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gameapp.R.drawable.btn_fadeleft_bg
import com.example.gameapp.R.drawable.btn_fadeleft_bg_2
import com.example.gameapp.databinding.ActivityMainBinding
import com.example.gameapp.interfaces.OnFragmentActionsListener
import com.example.gameapp.services.SoundUtils
import com.example.gameapp.services.VideoUtils
import com.example.gameapp.ui.gallery.GalleryFragment
import com.example.gameapp.ui.login.LoginFragment

/**
 * Main activity.
 *
 * Menu where user can login or access to credits, options and a gallery
 * with resources used in the app.
 *
 * Implements OnFragmentActionsListener to trigger actions on activity
 * when x action occurs in fragment.
 *
 */
class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    // Binding
    private lateinit var binding: ActivityMainBinding

    // Background VideoView
    private lateinit var videoBg: VideoView

    // Keep current fragment (login or gallery) to play some actions later
    private var currentFragment: Fragment? = null

    private var menuButtons = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

//      Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Background VideoView
        videoBg = findViewById(R.id.videoView)
        VideoUtils.initBgVideo( videoBg, this )

//      Array with Main Menu buttons
        menuButtons.add(0, binding.menuBtn1)
        menuButtons.add(1, binding.menuBtn2)
        menuButtons.add(2, binding.menuBtn3)

        // Set disabled color to buttons
        menuButtons[2].setTextColor(Color.LTGRAY)


//      Load new fragment depending on menu button pressed
        menuButtons.forEachIndexed{ index, btn ->

            btn.setOnClickListener{
                clickButton( it )
                if (index == 0) cargarFragment( LoginFragment.newInstance() )
                else if (index == 1) cargarFragment( GalleryFragment.newInstance() )
            }
        }

    }


    /**
     * Stop background music on activity paused
     *
     */
    override fun onPause() {
        super.onPause()
        SoundUtils.pauseBgMusic()
        VideoUtils.pauseBgVideo( videoBg )
//        videoBg.pause()
    }

    /**
     * Resume music if music is initialized but paused
     *
     */
    override fun onResume() {
        super.onResume()

        if( SoundUtils.isMusicInit() &&
            !SoundUtils.isMusicPlaying()
        )
            SoundUtils.resumeBgMusic()

        if ( VideoUtils.isBgVideoInit() ) {
            VideoUtils.resumeBgVideo( videoBg )
        }
    }

//    override fun onStop() {
//        super.onStop()
//        SoundUtils.removeBgMusic()
//        Log.i("stop", "SSSSSTTTOOOOOPPPP")
//    }

    override fun onStop() {
        if( currentFragment != null ) onCloseFragment()
        super.onStop()
    }

    /**
     * Restore menu buttons background
     *
     */
    fun resetButtons(){

        for ( button in menuButtons ){
            button.setBackgroundResource( btn_fadeleft_bg )
            button.animate().scaleX(1F)
            button.animate().scaleY(1F)
        }
    }

    /**
     * Play button sound, change background of pressed button and
     * grow it up
     *
     * @param view
     */
    fun clickButton(view: View){

        SoundUtils.onClickBtn( this )

        resetButtons()

        view.setBackgroundResource( btn_fadeleft_bg_2 )
        view.animate().scaleX(1.05F)
        view.animate().scaleY(1.05F)

    }

    /**
     * Move menu and load new fragment instance
     *
     * @param fragment
     */
    fun cargarFragment(fragment: Fragment){

        currentFragment = fragment

        // Al cargar un fragment, desplaza el menú
        binding.mainMenuButtons.animate().translationX(600F)

        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace( binding.menuFragmentContainer.id, fragment)
        fragmentIntercambio.commit()
    }


    override fun onClickFragmentButton( detail: String, id: Int, data: Unit? ) {
//        Toast.makeText(this, "Has cerrado la ventana de login", Toast.LENGTH_SHORT).show()
    }


    override fun onCloseFragment() {

        supportFragmentManager.beginTransaction().remove( currentFragment!! ).commit()

        // Reposiciona el menú al cerrar el fragment
        binding.mainMenuButtons.animate().translationX(0F)
        currentFragment = null
    }


}