package com.example.projeto05new.views.musicas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto05new.data.models.Musica

@Composable
fun MusicasScreen(navController : NavController, musicasViewModel: MusicasViewModel) {
    val musicas by musicasViewModel.musicas.observeAsState(listOf())

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Top
        ){
            Text(text = "Musicas", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
        }
        
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = {
                    navController.navigate("musicas_add")
                }
            ) {
                Text(text = "Nova")
            }

            Button(
                onClick = {
                    musicasViewModel.insert(Musica(nome = "Bloqueado", lancamento = "2022", cantor = "Gustavo Lima", genero = "Sertanejo"))
                    musicasViewModel.insert(Musica(nome = "Solo", lancamento = "2018", cantor = "Kim Jennie", genero = "Kpop"))
                    musicasViewModel.insert(Musica(nome = "How you Like That", lancamento = "2020", cantor = "Blackpink", genero = "Kpop"))
                    musicasViewModel.insert(Musica(nome = "The Trooper", lancamento = "1983", cantor = "Paul Bruce Dickinson", genero = "Rock"))
                    musicasViewModel.insert(Musica(nome = "Alexander The Great", lancamento = "1986", cantor = "Paul Bruce Dickinson", genero = "Rock"))
                    musicasViewModel.insert(Musica(nome = "Playing With Fire", lancamento = "2016", cantor = "Blackpink", genero = "Kpop"))
                    musicasViewModel.insert(Musica(nome = "Orion", lancamento = "1986", cantor = "Metallica", genero = "Rock"))
                    musicasViewModel.insert(Musica(nome = "Don't Talk To Strangers", lancamento = "1983", cantor = "Ronnie James Dio", genero = "Rock"))
                    navController.navigate("musicas")
                }
            ) {
                Text(text = "inserção inicial")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LazyColumn() {
                items(musicas){ musica->
                    Row(

                    ) {
                        Text(text = "Nome: ${musica.nome}")
                    }
                    Row(

                    ) {
                        Button(
                            onClick = {
                                navController.navigate("musicas_detalhes/${musica.nome}")
                            },
                            modifier = Modifier.padding(15.dp)
                        )
                        {
                            Icon(
                                Icons.Default.List ,
                                contentDescription = "Detalhes"
                            )
                            Text(text = "Detalhes")
                        }
                    }
                }

            }
        }

    }

}
