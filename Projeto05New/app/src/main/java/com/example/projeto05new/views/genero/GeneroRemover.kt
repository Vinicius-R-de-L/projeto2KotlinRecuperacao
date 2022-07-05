package com.example.projeto05new.views.genero

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
fun GeneroRemover(navController : NavController, generoViewModel: GeneroViewModel, gender: String) {
    var generoMusical by remember { mutableStateOf(gender) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = generoMusical,
            onValueChange = { generoMusical = it },
            label = { Text(text = "Gênero Musical") }
        )

        val genero = generoViewModel.findByGenero(generoMusical)

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                generoViewModel.delete(genero)
                navController.navigate("genero")
            }
        ) {
            Text(text = "Remover")
        }
    }

}