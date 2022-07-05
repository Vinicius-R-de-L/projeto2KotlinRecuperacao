package com.example.projeto05new.views.genero

import androidx.compose.foundation.layout.*
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
fun GeneroDetailScreen(navController : NavController, generoViewModel: GeneroViewModel, genero_musical: String) {
    val genero = generoViewModel.findByGenero(genero_musical)

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
                onClick = { navController.navigate("genero")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Generos")
            }

            Button(
                onClick = {
                    navController.navigate("genero_remover/${genero.genero_musical}")
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
                Row() {
                    Text(text = "Nome: ${genero.genero_musical}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                }
                Row() {
                    Text(text = "Descrição: ${genero.descricao}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
        }

    }

}