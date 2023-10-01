package com.example.logincompose.ui.characterlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

//otlin->file
@Composable
fun CharacterList(viewModel: CharacterListViewModel){

    //instancia del view model
    val characters=viewModel.characters.observeAsState(listOf())


}