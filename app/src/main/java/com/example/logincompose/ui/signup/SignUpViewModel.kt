package com.example.logincompose.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logincompose.ui.data.remote.UserRequest
import com.example.logincompose.ui.repository.UserRepository

//View model: sirve para preservar los estados
//"almacena los datos relacionados con la app que no se destruyen cuando el framework de Android destruye la actividad o el fragmento y los recrea"
//es mejor colocar esta clase en conjunto a la clase a la que afecta
//todos los estados deben de estar aqui
class SignUpViewModel(val userRepository: UserRepository =UserRepository()):ViewModel() {

                                     //USERNAME
    private var _username=MutableLiveData<String>()
    val username get()=_username //obtener el username

    fun updateUsername(username:String){
        _username.value=username
    }





                                       //PASSWORD
    private var _password=MutableLiveData<String>()
    val password get()=_password //obtener el password

    fun updatePassworde(password:String){
        _password.value=password
    }


                                          //CONFIRM PASSWORD
    private var _confirmPassword=MutableLiveData<String>()

    val confirmPassword get()=_confirmPassword //obtener el password
    fun updateConfirmPassword(confirmPassword:String){
        _confirmPassword.value=confirmPassword
    }





    //FUNCION DE ACCION DEL BOTON REGISTRARSE
    fun register(){

        val userRequest=UserRequest(username.value!!,password.value!!)
        userRepository.register(userRequest){}
    }



}