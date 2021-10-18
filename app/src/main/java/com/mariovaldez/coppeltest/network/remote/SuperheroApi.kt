package com.mariovaldez.coppeltest.network.remote

import com.mariovaldez.coppeltest.data.remote.SearchResponse
import com.mariovaldez.coppeltest.other.Const
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApi {

    @GET(Const.SEARCH + "/{name}")
    fun searchCharacter(
        @Path("name")name:String
    ):Call<SearchResponse>
}