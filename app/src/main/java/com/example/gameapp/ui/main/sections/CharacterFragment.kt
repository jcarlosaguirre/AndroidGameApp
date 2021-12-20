package com.example.gameapp.ui.main.sections

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.HomeActivity
import com.example.gameapp.classes.Character
import com.example.gameapp.classes.SpriteObject
import com.example.gameapp.classes.SpriteViewModel
import com.example.gameapp.databinding.FragmentCharacterBinding
import com.example.gameapp.interfaces.OnFragmentActionsListener


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterFragment : Fragment() {


    /**
     * Binding
     * This property is only valid between onCreateView and
     * onDestroyView
     */
    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding

    // Listener from parent
    private var listener: OnFragmentActionsListener? = null
    private lateinit var homeActivityContext: HomeActivity

    // Characters data model
    private lateinit var spriteViewModel: SpriteViewModel
    
    // Current character selected
    private var currentSpriteSelected: Int = 0
    private var replaceCharacter: Int? = null

    // Array of chosen characters
    private lateinit var characterSelection: Array<SpriteObject?>
    
    // Array with team members view bindings
    private lateinit var teamMembersBinding: Array<ImageButton>
    
    // Boolean shows stats name or value
    private var statsMode: Boolean = false
    
    // Boolean shows stats or team members
    private var statsSection: Boolean = true



    override fun onAttach(context: Context) {
        super.onAttach(context)

        if( context is OnFragmentActionsListener)
            listener = context

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



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

        // Get home activity context for accessing its functions
        homeActivityContext = (activity as HomeActivity)

        // Binding
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)


        // Change between Stas or team mebers sections
        val btnSection: Button = binding.btnCharacterSection
        btnSection.setOnClickListener{
            changeCharacterSection()
            btnSection.text = if(statsSection) "Team" else "Stats"
        }

        // GridView clickable
        binding.spritesGallery.isClickable = true

        // Get characters model with their data
        spriteViewModel =
            ViewModelProvider(this).get(SpriteViewModel::class.java)

        // Fill GridView with characters data model
        binding.spritesGallery.adapter = activity?.let { AdaptadorSpritesList (it, spriteViewModel.knightSprites) }

        // Click Listener on GridView elements
        binding.spritesGallery.setOnItemClickListener { _, _, position, _ ->

            // If team is not completed and character selected is not already in array, go ahead
            if ( replaceCharacter != null || currentSpriteSelected < 5 &&
                 !characterSelection.contains( spriteViewModel.knightSprites[position] ) ){

                    // Set selected sprite animation and "disable" it on grid
                    setCharacterSprite( position )
            }
        }

        // Array with team members buttons
        teamMembersBinding = arrayOf(   binding.spriteExpositor2, binding.spriteExpositor3,
                                        binding.spriteExpositor4, binding.spriteExpositor5
                                    )

        // Assign clickListener to team member buttons
        for ( (index: Int, teammate: ImageButton) in teamMembersBinding.withIndex() ){

            teammate.setOnClickListener{
                replaceCharacter = index;
            }
        }

        // Check if squad exists or initialize it
        initTeamMembers()

        // Change between stats names or values
        binding.statsData.setOnClickListener{
            showStatsValues()
        }

        return binding.root
    }


    /**
     * Get array with team members or init team with
     * first character
     *
     */
    fun initTeamMembers(){

        // Get current characters in team
        characterSelection = homeActivityContext.getTeamMembers()

        // If
        if ( characterSelection[0] != null ){
            for ((i, character) in characterSelection.withIndex()){

                when ( i ){
                    0 -> {
                        // Nombre y descripción del sprite
                        binding.spriteName.text = character?.name
                        binding.spriteDetail.text = character?.desc

                        // Asignar animación xml al "expositor"
                        val spriteExpositor = binding.spriteExpositor
                        spriteExpositor.setImageResource( character?.anim!! )

                        // Iniciar la animación
                        var anim = spriteExpositor.drawable as AnimationDrawable
                        anim.start()

                        playSpriteExpositor( i )
                    }
                    else -> {
                        Log.i("----------SHOW CHAR----", i.toString())
                        if ( characterSelection[i] != null ) teamMembersBinding[ i - 1 ].setImageResource( character?.sprite!! )
                    }
                }
            }
        }
        else{
            setCharacterSprite( 0 )
        }
    }

    /**
     * Set chosen character in team members array at
     * position
     *
     * @param position
     */
    fun setCharacterSprite( position: Int ){

        // Get sprite position in gallery
        var selectedSprite = binding.spritesGallery.getChildAt( position )
        var teammatePosition = 0

        if (selectedSprite != null) selectedSprite.alpha = 0.5f

        // Set Character in array of characters position and store it in HomeActivity
        if ( replaceCharacter != null ) {
            characterSelection[ replaceCharacter!! ] = spriteViewModel.knightSprites[position]
            teammatePosition = replaceCharacter!!
        }
        else {
            characterSelection[ currentSpriteSelected ] = spriteViewModel.knightSprites[position]
            teammatePosition = currentSpriteSelected - 1
        }

        homeActivityContext.setTeamMembers( characterSelection )



        when ( currentSpriteSelected ){
            0 -> {
                // Nombre y descripción del sprite
                binding.spriteName.text = spriteViewModel.knightSprites[position].name
                binding.spriteDetail.text = spriteViewModel.knightSprites[position].desc

                // Asignar animación xml al "expositor"
                val spriteExpositor = binding.spriteExpositor
                spriteExpositor.setImageResource( spriteViewModel.knightSprites[position].anim )

                // Iniciar la animación
                var anim = spriteExpositor.drawable as AnimationDrawable
                anim.start()

                playSpriteExpositor( position )
            }
            else -> {
                teamMembersBinding[ teammatePosition ].setImageResource( spriteViewModel.knightSprites[position].sprite )
            }
        }


        // Deactivate replace or increase position counter
        if ( replaceCharacter != null ) replaceCharacter = null
        else currentSpriteSelected++
    }

    /**
     * Set player character on expositor and start animation
     *
     * @param position
     */
    fun playSpriteExpositor( position: Int ){

        var player = Character( spriteViewModel.knightSprites[position] )

        binding.healthBar.progress = player.getStats()[0] * 200 / 100
        binding.defenseBar.progress = player.getStats()[1] * 200 / 100
        binding.strengthBar.progress = player.getStats()[2] * 200 / 100
        binding.magicBar.progress = player.getStats()[3] * 200 / 100
    }

    /**
     * Change between stat name or value
     *
     */
    fun showStatsValues(){

        statsMode = !statsMode

        binding.statHealthText.text = if ( statsMode ) "Health" else binding.healthBar.progress.toString() + " / 200"
        binding.statDefenseText.text = if ( statsMode ) "Defense" else binding.defenseBar.progress.toString() + " / 200"
        binding.statStrengthText.text = if ( statsMode ) "Strength" else binding.strengthBar.progress.toString() + " / 200"
        binding.statMagicText.text = if ( statsMode ) "Magic" else binding.magicBar.progress.toString() + " / 200"

    }

    /**
     * Change between Stats section or Team Members section
     *
     */
    fun changeCharacterSection(){

        statsSection = !statsSection

        if (statsSection){
            binding.statsData.visibility = View.VISIBLE
            binding.teamMembers.visibility = View.GONE
        }
        else{
            binding.statsData.visibility = View.GONE
            binding.teamMembers.visibility = View.VISIBLE
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
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {

        Toast.makeText(activity, "aaaaa", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
    }
}