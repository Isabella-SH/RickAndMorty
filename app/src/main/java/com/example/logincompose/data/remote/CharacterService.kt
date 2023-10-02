package com.example.logincompose.data.remote

import com.example.logincompose.data.model.Character
import com.example.logincompose.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

//kotlin->interface
interface CharacterService {

    //https://rickandmortyapi.com/api/character  ->este es tod el link
    @GET("character") //colocamos la ultima parte, que es la que llama a todos los resultados
    fun getAll(): Call<CharacterResponse>


    //busca en el json solo un character por su id
    @GET("character/{id}")
    fun getById(@Path("id") id:Int):Call<Character> //devuelve un character


}