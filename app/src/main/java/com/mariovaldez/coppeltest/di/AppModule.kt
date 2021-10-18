package com.mariovaldez.coppeltest.di

import com.google.gson.GsonBuilder
import com.mariovaldez.coppeltest.network.remote.SuperheroApi
import com.mariovaldez.coppeltest.other.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit):SuperheroApi{
        return retrofit.create(SuperheroApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        val gson = gsonBuilder.create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Const.BASE_URL)
            .build()
    }
}