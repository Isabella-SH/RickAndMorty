package com.example.logincompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//kotlin->class

//llamo a todas las entity
//false: porque nos e podria acceder a este esquema fuera de este espacio de trabajo

//AppDatabase sirve como la representación de la base de datos en tu aplicación.
@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false )
abstract class AppDataBase:RoomDatabase() {

    abstract  fun characterDao():CharacterDao

    companion object{

        //creo la instancia de esta interfaz
        fun getInstance(context: Context): AppDataBase {
            val db= Room.databaseBuilder(context,AppDataBase::class.java,"rickandmorty db").allowMainThreadQueries().build()

            return db
        }
    }
}