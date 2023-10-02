package com.example.logincompose.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items            //!!!!!!!
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue                 //!!!!!!!
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.logincompose.data.model.Character
import com.example.logincompose.ui.home.BottomNavigationScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

//otlin->file
@Composable
fun CharacterList(viewModel: CharacterListViewModel, navController: NavHostController){

    //instancia del view model
    val characters:List<Character> by viewModel.characters.observeAsState(listOf())
    viewModel.getAll() //trae toda la info de los character

    LazyColumn{
        items(characters){character->

            CharacterCard(character,

                //defino las fiunciones y llamo su implementacion del viw model
                delete = {
                    viewModel.delete(character)
                },

                insert={
                    viewModel.save(character)
                },
                onClick={
                    navController.navigate(BottomNavigationScreen.CharacterDetail.route + "/${character.id}")
                }
            )
        }
    }
}

@Composable                             //lamo a los metodos del view model para realizar lo de favorite
fun CharacterCard(character: Character, delete:()->Unit, insert:()->Unit, onClick: () -> Unit) {

    //creo un estado que guarde si el character es un favorito o no
    val isFavorite= remember{ mutableStateOf(false) }
    isFavorite.value=character.isFavorite //actualizamos el valor de favorito tal como lo tiene el character

    Card(modifier = Modifier.padding(8.dp).clickable(onClick=onClick)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp))
        {

            GlideImage(
                imageModel = {character.image},
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .width(92.dp)
                    .clip(CircleShape)
            )

            Column(modifier = Modifier.weight(5f)) {

                Text(text=character.name)
                Text(text=character.species)

                Row(modifier = Modifier.padding(8.dp)) {

                    Text(
                        text=character.species,
                        modifier = Modifier
                            .background(Color.Green)
                            .padding(2.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text=character.gender,
                        modifier = Modifier
                            .background(Color.Yellow)
                            .padding(2.dp)
                    )

                }
            }

            IconButton(
                onClick = {

                    if(isFavorite.value){ //si es falso(valor inicial)
                        delete()  //borralo de favoritos
                    }else{ //si es true
                        insert()  //guardalo como favorito
                    }

                    //cada que de click, el valor que tenia, ahora sera lo contrario
                    isFavorite.value = !isFavorite.value

                },
                modifier = Modifier.weight(1f)
            )
            {
                Icon(Icons.Filled.Star,
                    null,
                    //si esta marcado como favorito cambia a un color "primary", sino sera un color "onSurface"
                    tint = if(isFavorite.value)MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
