package com.example.gameapp.ui.main.sections

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.R
import com.example.gameapp.databinding.FragmentLobbyBinding
import com.example.gameapp.resources.SpriteViewModel





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LobbyFragment : Fragment() {


    // Binding
    private lateinit var _binding: FragmentLobbyBinding


    // Modelo que incluye datos para el fragment
    private lateinit var spriteViewModel: SpriteViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    private lateinit var currentThread: Thread


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        _binding = FragmentLobbyBinding.inflate(inflater, container, false)


        // Añadir menus clicables a diferentes botones
        val btnEquip: Button = binding.btnEquip
        btnEquip.setOnClickListener{

            val menupopup = PopupMenu( requireContext(), btnEquip )
            menupopup.inflate( R.menu.equipment_menu )
            menupopup.setOnMenuItemClickListener{
                Toast.makeText( requireContext() , "Opción: " + it.title, Toast.LENGTH_SHORT ).show()
                true
            }
            menupopup.show()
        }

        val btnStats: Button = binding.btnStats
        btnStats.setOnClickListener{

            val menupopup = PopupMenu( requireContext(), btnStats )
            menupopup.inflate( R.menu.stats_menu )
            menupopup.setOnMenuItemClickListener{
                Toast.makeText( requireContext() , "Opción: " + it.title, Toast.LENGTH_SHORT ).show()
                true
            }
            menupopup.show()
        }

        /*Hacemos que el ListView responda ante los clicks*/
        binding.spritesGallery.isClickable = true

        /*Y le asignamos el adaptador que hemos creado previamente*/
        binding.spritesGallery.adapter = activity?.let { AdaptadorSpritesList (it, spriteViewModel.knightSprites) }

        // Evento para capturar el sprite seleccionado en el grid
        binding.spritesGallery.setOnItemClickListener { parent, view, position, id ->

            playSpriteExpositor( position )
        }

        // Iniciamos el expositor seleccionando el primer sprite
        playSpriteExpositor( 0 )

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    fun playSpriteExpositor( position: Int ){

        // Nombre y descripción del sprite
        binding.spriteName.text = spriteViewModel.knightSprites[position].nombre
        binding.spriteDetail.text = spriteViewModel.knightSprites[position].type

        // Asignar animación xml al "expositor"
        val spriteExpositor = binding.spriteExpositor
        spriteExpositor.setImageResource( spriteViewModel.knightSprites[position].anim )

        // Iniciar la animación
        var anim = spriteExpositor.drawable as AnimationDrawable
        anim.start()

//        currentThread = thread {
//
//            var count = 0
//            while (true){
//
//                try {
//                    spriteExpositor.setImageResource( spriteViewModel.knightSprites[position].sprite[count] )
//                    Thread.sleep(200)
//                    count = if (count == 2) 0 else count + 1
//
//                } catch (err: Exception) {
//                    err.printStackTrace()
//                }
//            }
//        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LobbyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LobbyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}