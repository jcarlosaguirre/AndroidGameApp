package com.example.gameapp.classes

/**
 * Sprites available in game.
 * Each sprite type gives different stats to a character.
 *
 * @property name
 * @property type
 * @property desc
 * @property sprite
 * @property anim
 */
data class SpriteObject(
    var name: String,
    var type: Int,
    var desc: String,
    var sprite: Int,
    var anim: Int,

    )
