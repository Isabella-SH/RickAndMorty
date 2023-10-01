package com.example.logincompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.logincompose.data.model.Character
import kotlinx.coroutines.selects.select

//kotlin->interface

//DAO (Data Access Object): Un DAO en Room es una interfaz o clase abstracta
// que define los métodos para interactuar con la base de datos.
// Los métodos del DAO permiten realizar operaciones como insertar,
// actualizar, eliminar y consultar datos en la base de datos.
@Dao
interface CharacterDao {

    @Insert  //funcion de insertar
    fun save(character:CharacterEntity)

    @Query("select * from characters where:id")
    //debe encontrar un characterEntity, sino encuentra, es nulo(?)
    fun getById(id:Int):CharacterEntity?

    @Delete
    fun delete(character: CharacterEntity)


    //para llamar a todos mir favoritos, definirla en el repository
    @Query("select * from characters")
    fun getAll():List<CharacterEntity>


}