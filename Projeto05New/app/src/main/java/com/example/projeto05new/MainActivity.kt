package com.example.projeto05new

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.ui.theme.Projeto05NewTheme
import com.example.projeto05new.views.cantores.*
import com.example.projeto05new.views.genero.*
import com.example.projeto05new.views.musicas.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        //ModelViews
        val cantoresViewModel = CantoresViewModel(db.cantorDao())
        val musicasViewModel = MusicasViewModel(db.musicaDao())
        val generoViewModel = GeneroViewModel(db.generoDao())

        setContent {
            Projeto05NewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MusicApp(
                        cantoresViewModel,
                        musicasViewModel,
                        generoViewModel,
                    )
                }
            }
        }
    }
}




@Composable
fun MusicApp(cantoresViewModel: CantoresViewModel, musicasViewModel: MusicasViewModel, generoViewModel: GeneroViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar (
                modifier = Modifier.height(80.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavScreens.forEach { botNavScreen ->
                    BottomNavigationItem(
                        icon = { Icon(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(6.dp),
                            contentDescription = stringResource(id = botNavScreen.name),
                            painter = painterResource(id = botNavScreen.icon)
                        )},
                        label = { Text(text = stringResource(id = botNavScreen.name))},
                        selected = currentDestination?.hierarchy?.any{
                                it.route == botNavScreen.route
                             } == true,
                        onClick = {
                            navController.navigate(botNavScreen.route){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.CantoresScreen.route
        ){
            composable(Screen.CantoresScreen.route){
                CantoresScreen(navController, cantoresViewModel)
            }
            composable(
                Screen.CantoresDetails.route,
                arguments = listOf(navArgument(DETAIL_ARGUMENT_NOMECANTOR){
                    type = NavType.StringType
                })
            ){
                Log.d("Args", it.arguments?.getString(DETAIL_ARGUMENT_NOMECANTOR).toString())
                CantoresDetailScreen(navController, cantoresViewModel, it.arguments?.getString(DETAIL_ARGUMENT_NOMECANTOR).toString())
            }
            composable(Screen.CantoresAdd.route){
                CantoresAdd(navController, cantoresViewModel)
            }
            composable(
                Screen.CantoresRemover.route,
                arguments = listOf(navArgument(REMOVER_ARGUMENT_NAMECANTOR){
                    type = NavType.StringType
                })
            ){
                CantoresRemover(navController, cantoresViewModel, it.arguments?.getString(REMOVER_ARGUMENT_NAMECANTOR).toString())
            }



            composable(Screen.MusicasScreen.route){
                MusicasScreen(navController, musicasViewModel)
            }
            composable(
                Screen.MusicasDetails.route,
                arguments = listOf(navArgument(DETAIL_ARGUMENT_NOME){
                    type = NavType.StringType
                })
            ){
                MusicasDetailScreen(navController, musicasViewModel, it.arguments?.getString(DETAIL_ARGUMENT_NOME).toString())
            }
            composable(Screen.MusicasAdd.route){
                MusicasAdd(navController, musicasViewModel, cantoresViewModel, generoViewModel)
            }
            composable(
                Screen.MusicasRemover.route,
                arguments = listOf(navArgument(REMOVER_ARGUMENT_NAMEMUSICA){
                    type = NavType.StringType
                })
            ){
                MusicasRemover(navController, musicasViewModel, it.arguments?.getString(REMOVER_ARGUMENT_NAMEMUSICA).toString())
            }



            composable(Screen.GeneroScreen.route){
                GeneroScreen(navController, generoViewModel)
            }
            composable(
                Screen.GeneroDetails.route,
                arguments = listOf(navArgument(DETAIL_ARGUMENT_GENEROMUSICAL){
                    type = NavType.StringType
                })
            ){
                Log.d("Args", it.arguments?.getString(DETAIL_ARGUMENT_GENEROMUSICAL).toString())
                GeneroDetailScreen(navController, generoViewModel, it.arguments?.getString(DETAIL_ARGUMENT_GENEROMUSICAL).toString())
            }
            composable(Screen.GeneroAdd.route){
                GeneroAdd(navController, generoViewModel)
            }
            composable(
                Screen.GeneroRemover.route,
                arguments = listOf(navArgument(REMOVER_ARGUMENT_GENERO){
                    type = NavType.StringType
                })
            ){
                GeneroRemover(navController, generoViewModel, it.arguments?.getString(REMOVER_ARGUMENT_GENERO).toString())
            }
        }
    }
}


private val bottomNavScreens = listOf(
    Screen.CantoresScreen,
    Screen.GeneroScreen,
    Screen.MusicasScreen
)


// ROTAS
const val DETAIL_ARGUMENT_NOMECANTOR = "nome"

const val DETAIL_ARGUMENT_GENEROMUSICAL = "genero_musical"

const val DETAIL_ARGUMENT_NOME = "nome"

const val REMOVER_ARGUMENT_NAMECANTOR = "nome"

const val REMOVER_ARGUMENT_GENERO = "genero"

const val REMOVER_ARGUMENT_NAMEMUSICA = "nome"


sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
){
    object CantoresScreen: Screen("cantores", R.drawable.cantor, R.string.cantores)
    object CantoresDetails: Screen("cantores_detalhes/{$DETAIL_ARGUMENT_NOMECANTOR}", R.drawable.cantor, R.string.cantores)
    object CantoresAdd: Screen("cantores_add", R.drawable.cantor, R.string.cantores)
    object CantoresRemover: Screen("cantores_remover/{$REMOVER_ARGUMENT_NAMECANTOR}", R.drawable.cantor, R.string.cantores)

    object MusicasScreen: Screen("musicas", R.drawable.musica, R.string.musica)
    object MusicasDetails: Screen("musicas_detalhes/{$DETAIL_ARGUMENT_NOME}", R.drawable.musica, R.string.musica)
    object MusicasAdd: Screen("musicas_add", R.drawable.musica, R.string.musica)
    object MusicasRemover: Screen("musicas_remover/{$REMOVER_ARGUMENT_NAMEMUSICA}", R.drawable.musica, R.string.musica)


    object GeneroScreen: Screen("genero", R.drawable.genero, R.string.genero)
    object GeneroDetails: Screen("genero_detalhes/{$DETAIL_ARGUMENT_GENEROMUSICAL}", R.drawable.genero, R.string.genero)
    object GeneroAdd: Screen("genero_add", R.drawable.genero, R.string.genero)
    object GeneroRemover: Screen("genero_remover/{$REMOVER_ARGUMENT_GENERO}", R.drawable.genero, R.string.genero)

}



