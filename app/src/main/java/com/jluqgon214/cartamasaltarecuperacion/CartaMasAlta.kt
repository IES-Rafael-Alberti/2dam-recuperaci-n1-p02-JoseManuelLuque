package com.jluqgon214.cartamasaltarecuperacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.cartamasaltarecuperacion.data.Baraja
import com.jluqgon214.cartamasaltarecuperacion.data.CartaAltaViewModel


@Composable
fun CartaMasAlta(viewModel: CartaAltaViewModel, navController: NavController) {

    val imagenIdJugador1: Int by viewModel.imageIdJugador1.observeAsState(initial = R.drawable.reverse)
    val imagenIdJugador2: Int by viewModel.imageIdJugador2.observeAsState(initial = R.drawable.reverse)
    val showWinnerDialog: Boolean by viewModel.showWinnerDialog.observeAsState(initial = false)

    if (showWinnerDialog) {
        AlertDialogGanador(
            onDismissRequest = { viewModel._showWinnerDialog.value = false },
            onConfirmation = { viewModel._showWinnerDialog.value = false },
            viewModel
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .size(250.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Numero de cartas en la Baraja: ${Baraja.listaCartas.size}",
            color = Color.White
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Jugador 1:", color = Color.White)

        MostrarCarta(viewModel = viewModel, navController = navController, imagenId = imagenIdJugador1)

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Jugador 2:", color = Color.White)

        MostrarCarta(viewModel = viewModel, navController = navController, imagenId = imagenIdJugador2)
    }

    Row(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            Baraja.borrarBaraja()
            Baraja.crearBaraja(viewModel.context.value!!)
            Baraja.barajar()
            viewModel.getReverseCard()
        }, Modifier.padding(10.dp)) {
            Text(text = "Reiniciar")
        }
    }
}

@Composable
fun AlertDialogGanador(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    viewModel: CartaAltaViewModel
) {
    AlertDialog(
        title = {
            Text(text = "Y el ganador es ...")
        },
        text = {
            Text(text = "El Jugador ${viewModel.winner.value}")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Volver a sacar cartas")
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarCarta(viewModel: CartaAltaViewModel, navController: NavController, imagenId: Int) {
    Card(
        onClick = {
            viewModel.getCard()
            viewModel.ComprobarGanador()
        },
        modifier = Modifier
            .width(150.dp)
            .height(228.dp)
    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = "Carta",
            modifier = Modifier.fillMaxSize()
        )
    }
}