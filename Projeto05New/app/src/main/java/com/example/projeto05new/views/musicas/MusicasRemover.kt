package com.example.projeto05new.views.musicas

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MusicasRemover(navController : NavController, musicasViewModel: MusicasViewModel, name: String) {
    var nome by remember { mutableStateOf(name) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )

        val musica = musicasViewModel.findByName(nome)

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                musicasViewModel.delete(musica)
                navController.navigate("musicas")
            }
        ) {
            Text(text = "Remover")
        }
    }

}