package com.example.logincompose.ui.characterlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items            //!!!!!!!
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue                 //!!!!!!!
import androidx.compose.runtime.livedata.observeAsState
import com.example.logincompose.data.model.Character

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

    Card {
        Row {
            Text(text=character.name)
        }
    }

}
