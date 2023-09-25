package com.example.logincompose.ui.repository

import com.example.logincompose.ui.data.remote.ApiClient
import com.example.logincompose.ui.data.remote.UserRequest
import com.example.logincompose.ui.data.remote.UserResponse
import com.example.logincompose.ui.data.remote.UserService
import com.example.logincompose.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (val userService:UserService = ApiClient.getUserService()){

    //posibles resultados de consumir el api:
    fun register(userRequest: UserRequest, callback: (Result<UserResponse>)->Unit){

        val register=userService.register(userRequest)

        register.enqueue(object : Callback<UserResponse>{

            //cuando la respuesta se a satisfatoria
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if(response.isSuccessful){
                    val userResponse=response.body()!!
                    callback(Result.Success(userResponse))
                }

            }
            //cuando la respuesta no sea satisfatoria
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                val message=t.message!!
                callback(Result.Error(message))

            }
        })
    }
}