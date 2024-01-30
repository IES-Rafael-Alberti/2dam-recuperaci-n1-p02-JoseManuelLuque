package com.jluqgon214.cartamasaltarecuperacion.data

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jluqgon214.cartamasaltarecuperacion.R

class CartaAltaViewModel(application: Application) : AndroidViewModel(application) {
    val context = MutableLiveData<Context>(getApplication<Application>().applicationContext)
    val gameStarted = MutableLiveData<Boolean>(false)
    val showWinnerDialog = MutableLiveData<Boolean>(false)
    val winner = MutableLiveData<Any>()

    var cartaJ1 = MutableLiveData<Carta>(
        Carta(
        Naipes.As,
        Palos.Oro,
        1,
        R.drawable.reverse
    )
    )
    var cartaJ2 = MutableLiveData<Carta>(
        Carta(
            Naipes.As,
            Palos.Oro,
            1,
            R.drawable.reverse
        )
    )


    init {
        Reiniciar(context.value!!)
    }
    fun Reiniciar(context: Context) {
        Baraja.borrarBaraja()
        Baraja.crearBaraja(context = context)
        Baraja.barajar()
    }
    
    fun getReverseCard(){
        cartaJ1.value?.idDrawable = R.drawable.reverse
        cartaJ2.value?.idDrawable = R.drawable.reverse
    }

    fun CalcularPuntos(): Int{
        if(cartaJ1.value?.puntos!! > cartaJ2.value?.puntos!!) {
            return 1
        }
        if(cartaJ1.value?.puntos!! < cartaJ2.value?.puntos!!) {
            return 2
        }
        else{
            return 0
        }
    }

    fun ComprobarGanador() {
        if (gameStarted.value == false) {
            Baraja.startGame(context.value!!)
            gameStarted.value = true
        }
        if (Baraja.listaCartas.isEmpty()) {
            Toast.makeText(
                context.value,
                "No quedan cartas en la baraja",
                Toast.LENGTH_LONG
            ).show()
        } else {
            cartaJ1.value = Baraja.dameCarta()
            cartaJ2.value = Baraja.dameCarta()

            if (CalcularPuntos() == 1) {
                showWinnerDialog.value = true
                winner.value = 1
            }
            if (CalcularPuntos() == 2) {
                showWinnerDialog.value = true
                winner.value = 2
            }
            if (CalcularPuntos() == 0) {
                showWinnerDialog.value = true
                winner.value = 0
            }
        }
    }
}