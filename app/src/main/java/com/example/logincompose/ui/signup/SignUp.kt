package com.example.logincompose.ui.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
//solo si el valor del estado asociado cambia, el oposable se puede refrescar
@Composable //para que composable sea cambiante debe de tener un estado asociado
fun SignUp(viewModel: SignUpViewModel){

    //este username estara atento a los cambios de este username porque lo observa como un estado
    //patron bublisher-suscriber
    val username:String by viewModel.username.observeAsState("")
    val password:String by viewModel.password.observeAsState("")
    val confirmPassword:String by viewModel.confirmPassword.observeAsState("")



    Column (verticalArrangement = Arrangement.Center, //para centrarlo
        modifier = Modifier.fillMaxSize(), //para indicar que utiliza tod el espacio de largo
        horizontalAlignment = Alignment.CenterHorizontally //para indicar que utiliza tod el espacio de ancho
    ) {

        //OutlinedTextField: cajita de texto que se puede editar
        OutlinedTextField(
            value = username,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                //recoge los cambios con viewModel
                viewModel.updateUsername(newValue)  // sera el nuevo valor que se escriba en la cajita de texto
            },

            placeholder = { Text(text = "Username")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible
        )

        OutlinedTextField(
            value = password,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                //recoge los cambios con viewModel
                viewModel.updatePassworde(newValue)  // sera el nuevo valor que se escriba en la cajita de texto
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Password")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible

        )

        OutlinedTextField(
            value = confirmPassword,

            //nueva cadena de valor al que cambiara el value
            onValueChange = {newValue->
                //recoge los cambios con viewModel
                viewModel.updateConfirmPassword(newValue)  // sera el nuevo valor que se escriba en la cajita de texto
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Confirm Password")},

            modifier=Modifier.fillMaxWidth() //para que ocupe tod el ancho disponible

        )


        Button(onClick = {

            viewModel.register()

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


}







