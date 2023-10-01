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

    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreen.CharacterList.route //inicia mostrando la lista de characters
    ){

        composable(BottomNavigationScreen.CharacterList.route){
            CharacterList(viewModel)
        }

        composable(BottomNavigationScreen.Favorites.route){

        }
    }

    //crear iteraccion con botones
    val bottomNavegationItems=listOf(
        BottomNavigationScreen.CharacterList,
        BottomNavigationScreen.Favorites
    )

}

//creamos clase sellada
sealed class BottomNavigationScreen(val route: String){

    object CharacterList: BottomNavigationScreen("CharacterList")
    object Favorites: BottomNavigationScreen("Favorites")
}











