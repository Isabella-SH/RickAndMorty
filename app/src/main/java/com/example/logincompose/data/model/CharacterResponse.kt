package com.example.logincompose.data.model

import com.google.gson.annotations.SerializedName

//kotlin->data class

//creamos los atributos con el mismo nombre que aparece en el json
//caso contrario agrgar @SerializedName("nombreoriginal")
data class Character(

    val id:Int,
    val name:String,
    val species: String,
    val gender: String,
    val status: String
)

data class CharacterResponse(
    @SerializedName("results")
    val characters:List<Character>
)