package com.example.projeto05new.views.musicas

import androidx.compose.foundation.clickable
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
fun MusicasDetailScreen(navController : NavController, musicasViewModel: MusicasViewModel, nome: String) {
    val musica = musicasViewModel.findByName(nome)

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
                onClick = { navController.navigate("musicas")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Musicas")
            }

            Button(
                onClick = {
                    navController.navigate("musicas_remover/${musica.nome}")
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
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
                    Text(text = "Nome: ${musica.nome}", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                }
                Row() {
                    Text(text = "Lançamento: ${musica.lancamento}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Row(
                    modifier = Modifier.clickable {
                        navController.navigate("cantores_detalhes/${musica.cantor}")
                    }
                ) {
                    Text(text = "Cantor: ${musica.cantor}", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
                Row() {
                    Text(text = "Genero: ${musica.genero}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
        }

    }

}