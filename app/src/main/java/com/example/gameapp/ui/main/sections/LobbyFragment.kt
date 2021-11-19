package com.example.gameapp.ui.main.sections

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gameapp.R
import com.example.gameapp.databinding.FragmentLobbyBinding
import kotlin.concurrent.thread

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
    @SuppressLint("ResourceType")
    private var maleSprite: List<List<Int>> =
        listOf(
            listOf(R.raw.male_1_1, R.raw.male_1_2, R.raw.male_1_3),
            listOf(R.raw.male_2_1, R.raw.male_2_2, R.raw.male_2_3),
            listOf(R.raw.male_3_1, R.raw.male_3_2, R.raw.male_3_3),
        )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


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



        _binding = FragmentLobbyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spriteExpositor = binding.spriteExpositor
        val spriteExpositor = binding.spriteExpositor


        thread {

            var count = 0

            while (true){

                try {

                    spriteExpositor.setImageResource(maleSprite[0][count])
                    Thread.sleep(200)
                    count = if (count == 2) 0 else count + 1


                } catch (err: Exception) {
                    err.printStackTrace()
                }
            }

        }

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