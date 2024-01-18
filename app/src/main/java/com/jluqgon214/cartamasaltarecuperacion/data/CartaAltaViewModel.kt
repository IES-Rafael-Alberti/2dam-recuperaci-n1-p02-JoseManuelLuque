package com.jluqgon214.cartamasaltarecuperacion.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jluqgon214.cartamasaltarecuperacion.R

class CartaAltaViewModel(application: Application) : AndroidViewModel(application) {
    val context = MutableLiveData<Context>(getApplication<Application>().applicationContext)

    var cartaActual = MutableLiveData<Carta>(
        Carta(
        Naipes.As,
        Palos.Corazones,
        1,
        11,
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
}