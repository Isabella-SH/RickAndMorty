package com.example.logincompose.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logincompose.data.local.CharacterEntity
import com.example.logincompose.data.model.Character
import com.example.logincompose.repository.CharacterRepository
import com.example.logincompose.utils.Result

//kotlin->class
class CharacterListViewModel (
    private val characterRepository: CharacterRepository= CharacterRepository()
):ViewModel(){//manejar los estados que tenfria characterlist

    //_CHARACTER TOMARA EL VALOR DE TOD LO QUE SE LLAMA CON EL REPOSITORY

    //tiene la lista de los character
    private var _characters=MutableLiveData<List<Character>>()

    //devolver la lista
    val characters get()=_characters

    fun getAll(){

        characterRepository.getAll { result->
            if (result is Result.Success){
                _characters.value=result.data!!
            }
        }
    }

    //borrar de favoritos
    fun delete(character: Character){
        characterRepository.delete(character)
    }

    //guardar como favoritos
    fun save(character: Character){
        characterRepository.save(character)
    }

    //devuelva la lista de CharacterEntity
    fun getFavoriteCharacters(): List<CharacterEntity> {
        return characterRepository.getAllFavorites()
    }

}













