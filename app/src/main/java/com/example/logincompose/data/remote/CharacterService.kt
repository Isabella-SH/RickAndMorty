package com.example.logincompose.data.remote

import com.example.logincompose.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.Call

//kotlin->interface
interface CharacterService {

    //https://rickandmortyapi.com/api/character  ->este es tod el link
    @GET("character") //colocamos la ultima parte, que es la que llama a todos los resultados
    fun getAll(): Call<CharacterResponse>



}