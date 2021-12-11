package com.example.gameapp.classes

class Character {

    /**
     * Character stats and array
     */
    private var health: Int = 0
    private var defense: Int = 0
    private var strength: Int = 0
    private var magic: Int = 0

    private var stats: Array<Int> = arrayOf(health, defense, strength, magic)


    /**
     * Constructs a character based on type of sprite
     */
    constructor(base: SpriteObject){

        when(base.type){
            0 -> {
                health = 6
                defense = 4
                strength = 5
                magic = 3
            }
            1 -> {
                health = 7
                defense = 5
                strength = 3
                magic = 3
            }
            2 -> {
                health = 5
                defense = 4
                strength = 3
                magic = 6
            }
        }

        setStats()
    }

    /**
     * @param x
     * x is the quantity of points for upgrading a stat
     */
    private fun setHealthPoint( x: Int = 1 ){ health += x }
    private fun setDefensePoint( x: Int = 1 ){ defense += x }
    private fun setStrengthPoint( x: Int = 1 ){ strength += x }
    private fun setMagicPoint( x: Int = 1 ){ magic += x }

    /**
     * Update stats array by setting current stats in it
     *
     */
    private fun setStats(){

        stats = arrayOf(health, defense, strength, magic)
    }

    /**
     *
     * @return stats array
     */
    fun getStats(): Array<Int>{
        return stats
    }

}