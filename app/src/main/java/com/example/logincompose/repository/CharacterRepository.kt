package com.example.logincompose.repository

import com.example.logincompose.App
import com.example.logincompose.data.local.AppDataBase
import com.example.logincompose.data.local.CharacterDao
import com.example.logincompose.data.local.CharacterEntity
import com.example.logincompose.data.model.Character
import com.example.logincompose.data.model.CharacterResponse
import com.example.logincompose.data.remote.ApiClient
import com.example.logincompose.data.remote.CharacterService
import com.example.logincompose.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//kotlin->class

//VA A TRAER LOS DATOS, debe tener lo mismo que el servicio!!!!!!!!!!
//TAMBIEN DEBE IMPLEMNETAR LOS METODOS DEL DAO
class CharacterRepository(
  private val characterDao: CharacterDao= AppDataBase.getInstance(App.instance).characterDao(), //al llamarla desde view model, inicializa a characterdao
  private val characterService:CharacterService =ApiClient.getCharacterService()

) {

  //posibles resultados de consumir el api:
  fun getAll(callback: (Result<List<Character>>) -> Unit){

    val getAll=characterService.getAll()

    //la variable
    getAll.enqueue(object : Callback<CharacterResponse> {

      //cuando la respuesta se a satisfatoria
      override fun onResponse(
        call: Call<CharacterResponse>,
        response: Response<CharacterResponse>
      ) {
        if(response.isSuccessful){

          //en esta variable guardo todos los characters que se obtuvo del response
          val characters=response.body()!!.characters

          //de toda esta lista, busco en cada uno
          characters.forEach{character->
             //si este character es encontrado en la base de datos local,quiere decir que esta marcado como favorito
             //y  character.isFavorite deberia ser true, en caso no este en la bd local,  character.isFavorite sera false
            character.isFavorite=characterDao.getById(character.id) !=null
          }
          callback(Result.Success(data=response.body()!!.characters))
        }

      }

      //cuando la respuesta no sea satisfatoria
      override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {

        callback(Result.Error(message=t.localizedMessage!!))
      }
    })
  }


  fun getById(id:String,callback: (Result<Character>) -> Unit){

    val getById=characterService.getById(id)

    //la variable
    getById.enqueue(object: Callback<Character>{

      override fun onResponse(call: Call<Character>, response: Response<Character>) {

        if(response.isSuccessful){
          callback(Result.Success(data=response.body()!!))
        }
      }

      override fun onFailure(call: Call<Character>, t: Throwable) {
        callback(Result.Error(message=t.localizedMessage!!))
      }
    })
  }

  //elimina un favorito
  fun delete(character: Character){
    //en el Dao elimino un CharacterEntity con la informacion de un character:Character
    characterDao.delete(CharacterEntity(character.id))
    //luego indica que el atributo de isFavorite ahora es false
    character.isFavorite=false
  }

  //guarda un favorito
  fun save(character: Character){
    //en el Dao guardo un CharacterEntity con la informacion de un character:Character
    characterDao.save(CharacterEntity(character.id))
    //luego indica que el atributo de isFavorite ahora es true
    character.isFavorite=true
  }

}