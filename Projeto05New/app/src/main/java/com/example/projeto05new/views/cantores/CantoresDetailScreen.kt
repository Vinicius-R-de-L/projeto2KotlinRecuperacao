package com.example.projeto05new.views.cantores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CantoresDetailScreen(navController : NavController, cantoresViewModel: CantoresViewModel, nome: String) {
    val cantor = cantoresViewModel.findByName(nome)

    Spacer(modifier = Modifier.height(30.dp))

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.navigate("cantores")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Cantores")
            }

            Button(
                onClick = {
                    navController.navigate("cantores_remover/${cantor.nome}")
                }
            ) {
                Text(text = "Remover")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Row(){
                    Text(text = "Nome: ${cantor.nome}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                }
                Row() {
                    Text(text = "Idade: ${cantor.idade}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                LazyColumn(){
                    items(cantoresViewModel.loadCantorAndMusicNames(cantor.nome.toString())){
                        if(it.cantor == cantor.nome)
                            Text(text = "Musica: ${it.nome}")
                    }
                }
            }


        }

    }

}