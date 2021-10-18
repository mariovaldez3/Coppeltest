package com.mariovaldez.coppeltest.data.remote

import com.mariovaldez.coppeltest.data.model.SuperHero

data class SearchResponse (
    val response : String,
    val error : String,
    val `results-for`:String,
    val results: List<SuperHero>
        )