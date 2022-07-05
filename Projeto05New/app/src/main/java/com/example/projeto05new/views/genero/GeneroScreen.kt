package com.example.projeto05new.views.genero

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
import com.example.projeto05new.data.models.Genero

@Composable
fun GeneroScreen(navController : NavController, generoViewModel: GeneroViewModel) {
    val generos by generoViewModel.generos.observeAsState(listOf())

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
            Text(text = "Gêneros Musicais", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
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
                    navController.navigate("genero_add")
                }
            ) {
                Text(text = "Novo")
            }

            Button(
                onClick = {
                    generoViewModel.insert(Genero(genero_musical = "Rock", descricao = "O Rock 'n' Roll é um gênero musical que teve origem nos Estados Unidos."))
                    generoViewModel.insert(Genero(genero_musical = "Kpop", descricao = "K-pop é um gênero musical originado na Coreia do Sul, que se caracteriza por uma grande variedade de elementos audiovisuais."))
                    generoViewModel.insert(Genero(genero_musical = "Pagode", descricao = "Musica popular brasileira."))
                    generoViewModel.insert(Genero(genero_musical = "Sertanejo", descricao = "Musica sertaneja e um genero musical do Brasil produzido a partir da decada de 1910 por compositores urbanos, rurais e outros."))
                    generoViewModel.insert(Genero(genero_musical = "Classica", descricao = "Musica de concerto, chamada popularmente de musica classica ou musica erudita, e a principal variedade de musica produzida ou enraizada nas tradiçoes de musica secular e liturgica ocidental"))
                    generoViewModel.insert(Genero(genero_musical = "Eletronica", descricao = "Musica eletronica e a musica feita com instrumentos eletronicos, como sintetizadores ou baterias eletronicas, bem como equipamentos eletronicos para produz as faixas finais"))
                    //generos = generoDao.getAll();
                    navController.navigate("genero")
                }
            ) {
                Text(text = "inserção inicial")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LazyColumn() {
                items(generos){ genero->
                    Row(

                    ) {
                        Text(text = "Nome: ${genero.genero_musical}")
                    }
                    Row(

                    ) {
                        Button(
                            onClick = {
                                navController.navigate("genero_detalhes/${genero.genero_musical}")
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