package com.example.logincompose.data.local

//kotlin->data class

// (Entity): Una entidad en Room representa una tabla dentro de la base de datos.
// Cada instancia de la entidad corresponde a una fila en la tabla.
// En términos sencillos, una entidad es como una clase que representa una
// estructura de datos en tu base de datos.

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="characters")
data class CharacterEntity(

    //solo quiero guardar el dato del id
    @PrimaryKey val id:Int
)
