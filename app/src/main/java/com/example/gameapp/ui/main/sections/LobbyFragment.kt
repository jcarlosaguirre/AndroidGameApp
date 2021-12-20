package com.example.gameapp.ui.main.sections

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.databinding.FragmentHomeBinding
import com.example.gameapp.interfaces.OnFragmentActionsListener
import com.example.gameapp.ui.main.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class LobbyFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentHomeBinding? = null

    private var sections: Array<String> = arrayOf("Character", "Lobby", "Adventures")
    private lateinit var btnMenu: Array<Button>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listener: OnFragmentActionsListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if( context is OnFragmentActionsListener)
            listener = context

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root


        val btnCharacters: Button = binding.button
        val btnCharacters2: Button = binding.button2
        val btnCharacters3: Button = binding.button3

        btnMenu = arrayOf( btnCharacters, btnCharacters2, btnCharacters3 )

        btnMenu.forEachIndexed{ index, btn ->

            btn.setOnClickListener{
                Log.i("AAAAAA", sections[index])
                listener?.onClickFragmentButton( "HomeSection", index, null )
                pageViewModel.setIndex(index)
            }
        }

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): LobbyFragment {
            return LobbyFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}