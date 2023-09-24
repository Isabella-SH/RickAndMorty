package com.example.logincompose.ui.login

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)

//solo si el valor del estado asociado cambia, el oposable se puede refrescar
@Composable //para que composable sea cambiante debe de tener un estado asociado
fun Login(){

    //este es un estado, ya que es un estado cambiante
    val username= remember{ mutableStateOf("") }
    val password= remember{ mutableStateOf("") }


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
            placeholder = { Text(text = "Password")}
        )

        Button(onClick = { }) {
            Text(text = "Sign in")
        }

        TextButton(onClick = { }) {
            Text(text = "Sign up")
        }

    }
}

@Preview
@Composable
fun LoginPreview(){

    Login()
}







