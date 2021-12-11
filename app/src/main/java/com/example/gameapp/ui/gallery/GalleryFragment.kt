package com.example.gameapp.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.classes.SpriteViewModel
import com.example.gameapp.databinding.FragmentGalleryBinding
import com.example.gameapp.interfaces.OnFragmentActionsListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment() {


    // Binding
    private lateinit var _binding: FragmentGalleryBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listener: OnFragmentActionsListener? = null


    // Modelo que incluye datos para el fragment
    private lateinit var spriteViewModel: SpriteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if( context is OnFragmentActionsListener)
            listener = context

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cerrar fragment de login
        val btClose = binding.btCloseLogin
        btClose.setOnClickListener {
            listener?.onCloseFragment()
            closeGallery()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Referencia al viewModel de sprites
        spriteViewModel =
            ViewModelProvider(this).get(SpriteViewModel::class.java)

            _binding = FragmentGalleryBinding.inflate(inflater, container, false)


        /*Y le asignamos el adaptador que hemos creado previamente*/
//        binding.galleryRecicleView.adapter = activity?.let { AdaptadorSpritesList(it, spriteViewModel.knightSprites) }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun closeGallery(){
        parentFragmentManager.beginTransaction().remove(this).commit()
    }
}