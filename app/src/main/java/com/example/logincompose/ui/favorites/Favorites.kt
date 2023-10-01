package com.example.logincompose.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.logincompose.data.local.CharacterEntity
import com.example.logincompose.ui.characterlist.CharacterCard
import com.example.logincompose.ui.characterlist.CharacterListViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Favorites(viewModel: CharacterListViewModel){

    Text(text = "Prueba")
    
    val favoriteCharacters=viewModel.getFavoriteCharacters()
    
    //si la lista de favoritos no esta vacia
    if(favoriteCharacters.isNotEmpty()){

        LazyColumn{

            items(favoriteCharacters){character->

                CharacterFavoriteCard(character)
            }
        }
    }
    else{
        Text(text = "No hay characteres favoritos")
    }
}

@Composable
fun CharacterFavoriteCard(character: CharacterEntity) {

    Card(modifier = Modifier.padding(8.dp)) {

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

                Row(modifier = Modifier.padding(8.dp)) {

                    Text(
                        text=character.status,
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
        }
    }
}

















