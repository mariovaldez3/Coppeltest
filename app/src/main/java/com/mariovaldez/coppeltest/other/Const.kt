package com.mariovaldez.coppeltest.other

import com.mariovaldez.coppeltest.BuildConfig

class Const {
    companion object Constants{
        const val BASE_URL = "https://superheroapi.com/api/${BuildConfig.API_KEY}/"
        const val SEARCH = "search"
        const val HERO = "superhero"
    }
}