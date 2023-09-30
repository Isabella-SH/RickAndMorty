package com.example.logincompose.data.remote

//kotlin->object

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val API_BASE_URL="https://plain-marbled-muskox.glitch.me/"  //siempre hasta un "/"
    private var userService: UserService?=null

    fun getUserService(): UserService {

        if(userService ==null){

            val retrofit=Retrofit
                .Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            userService =retrofit.create(UserService::class.java)
        }
        return userService as UserService
    }


    ///CREO UNA INSTANCIA DEL SERVICIO

    const val RICK_AND_MORTY_API_BASE_URL="https://rickandmortyapi.com/api/"  //siempre hasta un "/"
    private var characterService: CharacterService?=null

    fun getCharacterService(): CharacterService {

        if(characterService ==null){

            val retrofit=Retrofit
                .Builder()
                .baseUrl(RICK_AND_MORTY_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            characterService =retrofit.create(CharacterService::class.java)
        }
        return characterService as CharacterService
    }






}