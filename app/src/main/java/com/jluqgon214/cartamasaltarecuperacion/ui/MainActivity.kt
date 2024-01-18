package com.jluqgon214.cartamasaltarecuperacion.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.cartamasaltarecuperacion.data.Baraja
import com.jluqgon214.cartamasaltarecuperacion.data.CartaAltaViewModel
import com.jluqgon214.cartamasaltarecuperacion.ui.theme.CartaMasAltaRecuperacionTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CartaAltaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaMasAltaRecuperacionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "CartaMasAlta"
                    ) {
                        composable("CartaMasAlta") {
                            CartaMasAlta(viewModel, navController)
                        }

                        composable("pantallaCambio") {
                            PantallaCambio(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartaMasAlta(viewModel: CartaAltaViewModel, navController: NavController) {

    var gameStarted by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .fillMaxSize()
            .size(250.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = viewModel.cartaActual.value!!.idDrawable),
            contentDescription = "Carta",
            Modifier.size(250.dp)
        )
    }

    Row(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (!gameStarted) {
                Baraja.startGame(viewModel.context.value!!)
                gameStarted = true
            }
            if (Baraja.listaCartas.isEmpty()) {
                //showToast("No quedan cartas en la baraja")
                Log.d("Exception", "Baraja de cartas Vacia")
            } else {
                viewModel.cartaActual.value = Baraja.dameCarta()
                Log.d("Yo mismo", "${viewModel.cartaActual.value!!} ${Baraja.listaCartas.size}")
            }
            navController.navigate("pantallaCambio")

        }, Modifier.padding(10.dp)) {
            Text(text = "Dame Carta")
        }
        Button(onClick = {
            Baraja.borrarBaraja()
            Baraja.crearBaraja(viewModel.context.value!!)
            Baraja.barajar()
        }, Modifier.padding(10.dp)) {
            Text(text = "Reiniciar")
        }
    }
}

@Composable
fun PantallaCambio(navController: NavController) {
    navController.navigate("CartaMasAlta")
}