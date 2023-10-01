package com.example.logincompose.data.local

import androidx.room.Entity

@Entity(tableName="characters")
data class CharacterEntity(

    //solo quiero guardar el dato del id
    val id:Int
)
