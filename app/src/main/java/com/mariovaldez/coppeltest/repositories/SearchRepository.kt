package com.mariovaldez.coppeltest.repositories

import com.mariovaldez.coppeltest.network.remote.SuperheroApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val api: SuperheroApi
) {

    fun searchSuperhero(name : String) = api.searchCharacter(name)
}