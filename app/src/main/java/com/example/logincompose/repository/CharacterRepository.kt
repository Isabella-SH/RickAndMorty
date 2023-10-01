package com.example.logincompose.repository

import com.example.logincompose.data.model.CharacterResponse
import com.example.logincompose.data.remote.ApiClient
import com.example.logincompose.data.remote.CharacterService
import com.example.logincompose.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//kotlin->class
class CharacterRepository(
  val characterService:CharacterService =ApiClient.getCharacterService()

) {

  //posibles resultados de consumir el api:
  fun getAll(callback: (Result<CharacterResponse>) -> Unit){

    val getAll=characterService.getAll()

    //la variable
    getAll.enqueue(object : Callback<CharacterResponse> {

      //cuando la respuesta se a satisfatoria
      override fun onResponse(
        call: Call<CharacterResponse>,
        response: Response<CharacterResponse>
      ) {

        if(response.isSuccessful){

          callback(Result.Success(data=response.body()!!))
        }

      }

      //cuando la respuesta no sea satisfatoria
      override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {

        callback(Result.Error(message=t.localizedMessage!!))
      }
    })
  }



}