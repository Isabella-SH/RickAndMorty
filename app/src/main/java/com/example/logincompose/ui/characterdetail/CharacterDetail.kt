package com.example.logincompose.ui.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.logincompose.data.model.Character
import com.example.logincompose.ui.characterlist.CharacterListViewModel
import com.example.logincompose.utils.Result
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


//kotlin->file
@Composable
fun CharacterDetail(viewModel: CharacterListViewModel, id:String){

    // Obtenemos el estado del personaje
    val oneCharacterState: Result<Character>? by viewModel.oneCharacter.observeAsState()

    // Al entrar en el Composable, solicitamos los detalles del personaje con el ID proporcionado
    LaunchedEffect(id) {
        viewModel.getCharacterById(id)
    }

    // Ahora podemos acceder a la información del personaje si está disponible
    when (oneCharacterState) {

        is Result.Success -> {

            val character = (oneCharacterState as Result.Success).data

            if (character != null) {
                Detail(character)
            }
        }
        is Result.Error -> {
            val errorMessage = (oneCharacterState as Result.Error).message
            // Aquí puedes manejar el caso de un error si lo deseas
        }
        else -> {
            // Puedes mostrar un indicador de carga u otra interfaz de usuario mientras esperas la respuesta
        }
    }
}

@Composable
fun Detail( character: Character){

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

        }
    }
}







