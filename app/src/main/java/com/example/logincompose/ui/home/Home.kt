package com.example.logincompose.ui.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logincompose.ui.characterlist.CharacterList
import com.example.logincompose.ui.characterlist.CharacterListViewModel

//kotlin->class

@Composable
fun Home() {

    val navController= rememberNavController()
    val viewModel:CharacterListViewModel= viewModel()

    NavHost(navController = navController, startDestination = "CharacterList" ){

        composable("CharacterList"){
            CharacterList(viewModel)
        }

    }

}