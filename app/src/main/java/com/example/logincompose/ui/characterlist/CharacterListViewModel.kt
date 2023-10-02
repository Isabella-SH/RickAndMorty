package com.example.logincompose.ui.characterlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logincompose.data.local.CharacterEntity
import com.example.logincompose.data.model.Character
import com.example.logincompose.repository.CharacterRepository
import com.example.logincompose.utils.Result
import retrofit2.Callback

//kotlin->class
class CharacterListViewModel (
    private val characterRepository: CharacterRepository= CharacterRepository()
):ViewModel(){//manejar los estados que tenfria characterlist

    //_CHARACTER TOMARA EL VALOR DE TOD LO QUE SE LLAMA CON EL REPOSITORY

    //tiene la lista de los character
    private var _characters=MutableLiveData<List<Character>>()

    //tiene un solo  character por id
    private var _oneCharacter= MutableLiveData<Result<Character>?>(null)

    //devolver la lista
    val characters get()=_characters

    //devolver un solo character
    val oneCharacter get()=_oneCharacter

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

    //devuelva un Character, con el metodo del propio repository, no del Dao
    fun getCharacterById(id:String){

        //_oneCharacter.value=null // Resetea el estado antes de hacer la llamada
        characterRepository.getById(id){result->

            _oneCharacter.value=result
        }
    }

}













