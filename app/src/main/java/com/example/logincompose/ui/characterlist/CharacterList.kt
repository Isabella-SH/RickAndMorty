package com.example.logincompose.ui.characterlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items            //!!!!!!!
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue                 //!!!!!!!
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.logincompose.data.model.Character
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

//otlin->file
@Composable
fun CharacterList(viewModel: CharacterListViewModel){

    //instancia del view model
    val characters:List<Character> by viewModel.characters.observeAsState(listOf())
    viewModel.getAll() //trae toda la info de los character

    LazyColumn{
        items(characters){character->
            CharacterCard(character)
        }
    }
}

@Composable
fun CharacterCard(character: Character) {

    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {

            GlideImage(
                imageModel = {character.image},
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.width(92.dp).clip(CircleShape)
            )

            Text(text=character.name)
        }
    }

}
