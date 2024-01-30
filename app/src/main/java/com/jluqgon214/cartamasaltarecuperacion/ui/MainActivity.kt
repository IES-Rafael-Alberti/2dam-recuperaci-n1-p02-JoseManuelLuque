package com.jluqgon214.cartamasaltarecuperacion.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.cartamasaltarecuperacion.CartaMasAlta
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
                    }
                }
            }
        }
    }
}