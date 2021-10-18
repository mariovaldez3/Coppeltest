package com.mariovaldez.coppeltest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariovaldez.coppeltest.data.model.SuperHero
import com.mariovaldez.coppeltest.data.remote.SearchResponse
import com.mariovaldez.coppeltest.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    private val _heroResult= MutableLiveData<List<SuperHero>>()
    val heroResult: LiveData<List<SuperHero>>
        get() = _heroResult

    private val _error= MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _errorNotFound= MutableLiveData<String>()
    val errorNotFound: LiveData<String>
        get() = _errorNotFound

    init {
        with(_error){postValue(null)}
        with(_heroResult){postValue(null)}
        with(_errorNotFound){postValue(null)}
    }

    fun search(name: String){
        println(name)
        viewModelScope.launch {
            repository.searchSuperhero(name).enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>,
                ) {
                    println(response)
                   if(response.isSuccessful){
                       if(response.body()!!.response.equals("success")){
                           val results : List<SuperHero> = response.body()!!.results
                           println(results)
                           _heroResult.postValue(results)
                       }
                       if(response.body()!!.response.equals("error")){
                           _errorNotFound.postValue(response.body()!!.error)
                       }
                   }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    println(t.message)
                    _error.postValue("You cannot have internet connection")
                }

            })
        }
    }

    fun init() {
        with(_error){postValue(null)}
        with(_errorNotFound){postValue(null)}
    }

}