package com.jluqgon214.cartamasaltarecuperacion.data

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jluqgon214.cartamasaltarecuperacion.R

class CartaAltaViewModel(application: Application) : AndroidViewModel(application) {
    val context = MutableLiveData<Context>(getApplication<Application>().applicationContext)
    val gameStarted = MutableLiveData<Boolean>(false)
    val winner = MutableLiveData<Any>()

    val _showWinnerDialog = MutableLiveData<Boolean>()
    val showWinnerDialog : LiveData<Boolean> = _showWinnerDialog

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc : LiveData<String> = _imageDesc

    private val _imageIdJugador1 = MutableLiveData<Int>()
    val imageIdJugador1 : LiveData<Int> = _imageIdJugador1

    private val _imageIdJugador2 = MutableLiveData<Int>()
    val imageIdJugador2 : LiveData<Int> = _imageIdJugador2


    var cartaJ1 = MutableLiveData<Carta>()
    var cartaJ2 = MutableLiveData<Carta>()

    fun getCard() {
        cartaJ1.value = Baraja.dameCarta()
        _imageIdJugador1.value = cartaJ1.value?.idDrawable

        cartaJ2.value = Baraja.dameCarta()
        _imageIdJugador2.value = cartaJ2.value?.idDrawable
    }


    init {
        Reiniciar(context.value!!)
    }
    fun Reiniciar(context: Context) {
        getReverseCard()
        Baraja.borrarBaraja()
        Baraja.crearBaraja(context = context)
        Baraja.barajar()
    }
    
    fun getReverseCard(){
        cartaJ1.value?.idDrawable = R.drawable.reverse
        _imageIdJugador1.value = R.drawable.reverse
        cartaJ2.value?.idDrawable = R.drawable.reverse
        _imageIdJugador2.value = R.drawable.reverse
    }

    fun CalcularPuntos(): Int{
        if(cartaJ1.value?.puntos!! > cartaJ2.value?.puntos!!) {
            return 1
        }
        return if(cartaJ1.value?.puntos!! < cartaJ2.value?.puntos!!) {
            2
        } else{
            0
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
            if (CalcularPuntos() == 1) {
                _showWinnerDialog.value = true
                winner.value = 1
            }
            if (CalcularPuntos() == 2) {
                _showWinnerDialog.value = true
                winner.value = 2
            }
            if (CalcularPuntos() == 0) {
                _showWinnerDialog.value = true
                winner.value = "Ninguno, Empate"
            }
        }
    }
}