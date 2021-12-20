package com.example.gameapp.ui.main.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameapp.HomeActivity
import com.example.gameapp.databinding.FragmentAdventuresBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdventuresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdventuresFragment : Fragment() {

    private lateinit var _binding: FragmentAdventuresBinding
    private val binding get() = _binding

    private lateinit var homeActivityContext: HomeActivity


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

        // Binding
        _binding = FragmentAdventuresBinding.inflate(inflater, container, false)

        homeActivityContext = (activity as HomeActivity)

//        initRecyclerView( param1 )

        // Inflate the layout for this fragment
        return binding.root
    }

    fun initRecyclerView( data: ArrayList<String>? ){

        binding.adventureContainer.layoutManager = LinearLayoutManager( activity )
        binding.adventureContainer.adapter = homeActivityContext.getAdapter()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdventuresFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String/*, param2: String*/) =
            AdventuresFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}