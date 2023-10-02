package com.example.logincompose.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logincompose.ui.characterdetail.CharacterDetail
import com.example.logincompose.ui.characterlist.CharacterList
import com.example.logincompose.ui.favorites.Favorites
import com.example.logincompose.ui.characterlist.CharacterListViewModel

//kotlin->class

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val navController= rememberNavController()

    //crear iteraccion con botones
    val bottomNavegationItems=listOf(
        BottomNavigationScreen.CharacterList,
        BottomNavigationScreen.Favorites
    )

    //esta sera la parte de navegacion en la parte inferior
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

        val navaBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navaBackStackEntry?.destination

        items.forEach{screen->
            BottomNavigationItem(

                selected = currentDestination?.hierarchy?.any{
                   it.route==screen.route
                }==true,
                onClick = { navController.navigate(screen.route) },
                icon = { Icon(screen.icon,null)}
            )
        }
    }
}


@Composable
fun Main(navController: NavHostController, modifier: Modifier){

    val viewModel:CharacterListViewModel= viewModel()
    //aqui recien llamo al view model

    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreen.CharacterList.route, //inicia mostrando la lista de characters
        modifier
    ){

        composable(BottomNavigationScreen.CharacterList.route){
            CharacterList(viewModel,navController)
        }

        //para poder visualizar los detalles de cada character


        composable(BottomNavigationScreen.Favorites.route){
            Favorites(viewModel)  //aca llamamos a la entidad que cree
        }

        composable(
            BottomNavigationScreen.CharacterDetail.route + "/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getInt("characterId")
            characterId?.let {
                CharacterDetail(viewModel, it)
            }
        }


    }
}

//creamos clase sellada
sealed class BottomNavigationScreen(val route: String, val icon: ImageVector){
    object CharacterList: BottomNavigationScreen("CharacterList", Icons.Filled.Home)
    object Favorites: BottomNavigationScreen("Favorites",  Icons.Filled.Favorite)
    object CharacterDetail: BottomNavigationScreen("CharacterDetail", Icons.Default.Info)
}









