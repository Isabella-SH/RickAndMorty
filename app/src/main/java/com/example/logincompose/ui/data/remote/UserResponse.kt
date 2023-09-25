package com.example.logincompose.ui.data.remote

//RESPONSE: Lo QUErecibo al hacer la consulta
data class UserResponse (

    val userName:String,
    val password: String,
    val id: Int
)