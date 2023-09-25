package com.example.logincompose.ui.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.logincompose.ui.data.remote.UserRequest
import com.example.logincompose.ui.repository.UserRepository
import com.example.logincompose.utils.Result

@OptIn(ExperimentalMaterial3Api::class)

//solo si el valor del estado asociado cambia, el oposable se puede refrescar
@Composable //para que composable sea cambiante debe de tener un estado asociado
fun SignUp(){

    //este es un estado, ya que es un estado cambiante
    val username= remember{ mutableStateOf("") }
    val password= remember{ mutableStateOf("") }
    val confirmPassword= remember{ mutableStateOf("") }



    Column (verticalArrangement = Arrangement.Center, //para centrarlo
        modifier = Modifier.fillMaxSize(), //para indicar que utiliza tod el espacio de largo
        horizontalAlignment = Alignment.CenterHorizontally //para indicar que utiliza tod el espacio de ancho
    ) {


        //OutlinedTextField: cajita de texto que se puede editar
        OutlinedTextField(
            value = username.value,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                username.value=newValue  // sera el nuevo valor que se escriba en la cajita de texto
            },

            placeholder = { Text(text = "Username")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible
        )

        OutlinedTextField(
            value = password.value,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                password.value=newValue  // sera el nuevo valor que se escriba en la cajita de texto
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Password")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible

        )

        OutlinedTextField(
            value = confirmPassword.value,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                confirmPassword.value=newValue  // sera el nuevo valor que se escriba en la cajita de texto
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Confirm Password")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible

        )

        val context= LocalContext.current
        val userRepository=UserRepository()
        val userRequest=UserRequest(username.value, password.value)

        Button(onClick = {

            userRepository.register(userRequest){result->

                if(result is Result.Success){
                    val id=result.data!!.id
                    Toast.makeText(context,"$id",Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Register")
        }

        TextButton(onClick = { }) {
            Text(text = "Login")
        }

    }
}

@Preview
@Composable
fun SignUpPreview(){

    SignUp()
}







