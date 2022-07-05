package com.example.projeto05new.views.musicas

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto05new.data.models.Musica
import com.example.projeto05new.views.cantores.CantoresViewModel
import com.example.projeto05new.views.genero.GeneroViewModel


@Composable
fun MusicasAdd(navController : NavController, musicasViewModel: MusicasViewModel, cantoresViewModel: CantoresViewModel, generoViewModel: GeneroViewModel){

    var nome by remember { mutableStateOf("") }
    var lancamento by remember { mutableStateOf("") }
    var cantor by remember { mutableStateOf("Cantor       -       (Selecione um cantor)") }
    var genero by remember { mutableStateOf("Genero       -       (Selecione um genero)") }

    val cantores by cantoresViewModel.cantores.observeAsState(listOf())
    val generos by generoViewModel.generos.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
            .fillMaxHeight()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = lancamento,
            onValueChange = { lancamento = it },
            label = { Text(text = "Lançamento") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropDown(text = cantor) {
            Row(

            ) {
                LazyColumn(){
                    items(cantores){ singer ->
                        Row() {
                            Text(
                                text = "Nome: ${singer.nome}",
                                modifier = Modifier
                                    .height(30.dp)
                                    .clickable {
                                        cantor = "${singer.nome}"
                                    }
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        DropDown(text = genero) {
            LazyColumn(){
                items(generos){ gender ->
                    Row() {
                        Text(
                            text = "Genero: ${gender.genero_musical}",
                            modifier = Modifier
                                .height(30.dp)
                                .clickable {
                                    genero = "${gender.genero_musical}"
                                }
                                .fillMaxWidth()
                                .background(Color.LightGray)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                musicasViewModel.insert(Musica(nome = nome, lancamento = lancamento, cantor = cantor, genero = genero))
                navController.navigate("musicas")
            }
        ) {
            Text(text = "Adicionar")
        }
    }

}

@Composable
fun DropDown(
    text: String,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
){
    var isOpen by remember{
        mutableStateOf(initiallyOpened)
    }

    val rotateX = animateFloatAsState(
        targetValue = if(isOpen) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                //color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Abrir ou fechar o menu drop down",
                //tint = Color.White,
                modifier = modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                    rotationX = rotateX.value
                }
                .height(if (isOpen) 80.dp else 0.dp)
        ){
            content()
        }
    }
}