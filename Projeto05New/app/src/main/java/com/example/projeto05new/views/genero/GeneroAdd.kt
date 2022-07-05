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
import com.example.projeto05new.data.models.Genero

@Composable
fun GeneroAdd(navController : NavController, generoViewModel: GeneroViewModel){

    var generoMusical by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = generoMusical,
            onValueChange = { generoMusical = it },
            label = { Text(text = "Genero Musical") }
        )
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text(text = "Descrição") }
        )
        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                generoViewModel.insert(Genero(genero_musical = generoMusical, descricao = descricao))
                navController.navigate("genero")
            }
        ) {
            Text(text = "Adicionar")
        }
    }

}