package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.logincompose.ui.home.Home
import com.example.logincompose.ui.theme.LoginComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme
                Home()
            }
        }
    }
}
