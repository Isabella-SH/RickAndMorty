package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.logincompose.ui.characterlist.CharacterList
import com.example.logincompose.ui.characterlist.CharacterListViewModel
import com.example.logincompose.ui.theme.LoginComposeTheme
import com.example.logincompose.ui.login.Login
import com.example.logincompose.ui.signup.SignUp
import com.example.logincompose.ui.signup.SignUpViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:CharacterListViewModel by viewModels()
        setContent {
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme

                CharacterList(viewModel)
            }
        }
    }
}
