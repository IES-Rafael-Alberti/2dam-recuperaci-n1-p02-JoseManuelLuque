package com.jluqgon214.cartamasaltarecuperacion.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jluqgon214.cartamasaltarecuperacion.R

class CartaAltaViewModel(application: Application) : AndroidViewModel(application) {
    val context = MutableLiveData<Context>(getApplication<Application>().applicationContext)
    val gameStarted = MutableLiveData<Boolean>(false)

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
}