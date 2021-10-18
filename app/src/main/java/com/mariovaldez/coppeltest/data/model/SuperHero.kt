package com.mariovaldez.coppeltest.data.model

import java.io.Serializable

data class SuperHero(
    val id : String,
    val name : String,
    val powerstats: Powerstats,
    val biography : Biography,
    val appearance: Appearance,
    val work : Work,
    val connections: Connections,
    val image: Image
):Serializable