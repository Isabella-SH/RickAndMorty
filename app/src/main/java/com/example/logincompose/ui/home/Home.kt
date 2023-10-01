package com.example.logincompose.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logincompose.ui.characterlist.CharacterList
import com.example.logincompose.ui.characterlist.CharacterListViewModel

//kotlin->class

//minuto 2:10:00

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val navController= rememberNavController()


    //crear iteraccion con botones
    val bottomNavegationItems=listOf(
        BottomNavigationScreen.CharacterList,
        BottomNavigationScreen.Favorites
    )

    //esta sera la parte de navegacion
    Scaffold(
        bottomBar={
            AppBottomNavegation(navController,bottomNavegationItems)
        }
    ) { paddingValues ->

        Main(navController, modifier=Modifier.padding(paddingValues))


    }

}

@Composable
fun AppBottomNavegation(navController: NavHostController, items: List<BottomNavigationScreen>){

    BottomNavigation {

        items.forEach{

        }
    }
}


@Composable
fun Main(navController: NavHostController, modifier: Modifier){

    val viewModel:CharacterListViewModel= viewModel()
    //aqui recien llamo al view model

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


}
//creamos clase sellada
sealed class BottomNavigationScreen(val route: String){

    object CharacterList: BottomNavigationScreen("CharacterList")
    object Favorites: BottomNavigationScreen("Favorites")
}











