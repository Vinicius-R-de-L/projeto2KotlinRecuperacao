package com.example.projeto05new.views.cantores

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
import com.example.projeto05new.data.models.Cantor

@Composable
fun CantoresScreen(navController : NavController, cantoresViewModel: CantoresViewModel) {
    val cantores by cantoresViewModel.cantores.observeAsState(listOf())

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
            Text(text = "Cantores", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
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
                    navController.navigate("cantores_add")
                }
            ) {
                Text(text = "Novo")
            }
            
            Button(
                onClick = {
                    cantoresViewModel.insert(Cantor(nome = "Kim Jennie", idade = "26"))
                    cantoresViewModel.insert(Cantor(nome = "Paul Bruce Dickinson", idade = "63"))
                    cantoresViewModel.insert(Cantor(nome = "Blackpink", idade = "6"))
                    cantoresViewModel.insert(Cantor(nome = "Metallica", idade = "41"))
                    cantoresViewModel.insert(Cantor(nome = "Gustavo Lima", idade = "32"))
                    cantoresViewModel.insert(Cantor(nome = "Ronnie James Dio", idade = "67"))
                    //cantores = cantorDao.getAll()
                    navController.navigate("cantores")
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
                items(cantores){ cantor->
                    Row(

                    ) {
                        Text(text = "Nome: ${cantor.nome}")
                        Text(text = "   Idade: ${cantor.idade}")
                    }
                    Row(

                    ) {
                        Button(
                            onClick = {
                                navController.navigate("cantores_detalhes/${cantor.nome}")
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