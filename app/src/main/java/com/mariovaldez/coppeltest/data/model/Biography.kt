package com.mariovaldez.coppeltest.data.model

data class Biography(
    val `full-name` : String,
    val `alter-egos`: String,
    val aliases: List<String>,
    val `place-of-birth`: String,
    val `first-appearance`: String,
    val publisher : String,
    val alignment : String
)
