package com.jluqgon214.cartamasaltarecuperacion.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.cartamasaltarecuperacion.CartaMasAlta
import com.jluqgon214.cartamasaltarecuperacion.PantallaCambio
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