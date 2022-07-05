package com.example.projeto05new.views.cantores

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
fun CantoresRemover(navController : NavController, cantoresViewModel: CantoresViewModel, name: String) {
    var nome by remember { mutableStateOf(name) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Primeiro Nome") }
        )

        val cantor = cantoresViewModel.findByName(nome = nome)

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                cantoresViewModel.delete(cantor)
                navController.navigate("cantores")
            }
        ) {
            Text(text = "Remover")
        }
    }

}