package com.example.logincompose.data.remote

import com.example.logincompose.data.model.UserRequest
import com.example.logincompose.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("users")
    //@Body: indica que esta clase UserRequest sera el cuerpo de la rspuesta l envairlo al api
    fun register(@Body userRequest: UserRequest):Call<UserResponse>

}