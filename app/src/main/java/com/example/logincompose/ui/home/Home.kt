package com.example.logincompose.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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
            CharacterList(viewModel){id->
                navController.navigate("${Routes.CharacterDetail.route}/$id") //navego hacia la ruta de HeroDetail
            }
        }

        //para pasar un valor al composable
        composable(
            route= Routes.CharacterDetail.routeWithArgument,

            //en este caso el valor del id del heroe del que quiero ver su detalle, aca lo pasa como estring
            arguments= listOf(navArgument(Routes.CharacterDetail.argument) {type= NavType.StringType})
        ){backStackEntry->

            //obtengo el valor del id que le pase
            val id=backStackEntry.arguments?.getString("id") as String

            //luego le paso el Compose
            CharacterDetail(viewModel,id)
        }

        composable(BottomNavigationScreen.Favorites.route){
            Favorites(viewModel)  //aca llamamos a la entidad que cree
        }
    }
}


//creamos clase sellada
sealed class BottomNavigationScreen(val route: String, val icon: ImageVector){
    object CharacterList: BottomNavigationScreen("CharacterList", Icons.Filled.Home)
    object Favorites: BottomNavigationScreen("Favorites",  Icons.Filled.Favorite)
}

sealed class Routes(val route:String){

    //creo un objeto que llame a mi clase CharacterDetail
    object CharacterDetail: Routes("CharacterDetail"){   //este nombre con el de abajo debe ser el mismo

        //para indicar el id del character que quiero ver
        const val routeWithArgument="CharacterDetail/{id}" //este nombre con el de arriba debe ser el mismo
        const val argument="id"
    }
}












