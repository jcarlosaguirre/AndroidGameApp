package com.example.gameapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gameapp.R
import com.example.gameapp.ui.main.sections.AdventuresFragment
import com.example.gameapp.ui.main.sections.CharacterFragment
import com.example.gameapp.ui.main.sections.LobbyFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3,

        )

    private var sectionsList: Array<Fragment> = arrayOf(
        CharacterFragment.newInstance("", ""),
        LobbyFragment.newInstance(1),
        AdventuresFragment.newInstance("2")
    )

//    fun setAdventuresData( data: ArrayList<String> ){
//        sectionsList[2] = AdventuresFragment.newInstance(data)
//    }



    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        when( position ){

            0 -> return sectionsList[0]
            1 -> return sectionsList[1]
            2 -> {


                return sectionsList[2]
            }
        }

        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}