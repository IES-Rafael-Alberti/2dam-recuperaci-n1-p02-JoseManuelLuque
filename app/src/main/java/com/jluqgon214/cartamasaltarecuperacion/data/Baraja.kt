package com.jluqgon214.cartamasaltarecuperacion.data

import android.content.Context
import com.jluqgon214.cartamasaltarecuperacion.R

/**
 * Métodos:
 *
 * - crearBaraja() => Generar la lista de 52 cartas de la baraja.
 * - barajar() => Desordenar las cartas de la lista de cartas de la baraja (shuffle)
 * - dameCarta() => Retorna la última carta de la lista de cartas y la elimina de la baraja.
 */
class Baraja {
    companion object {
        var listaCartas = ArrayList<Carta>()
        fun crearBaraja(context: Context) {
            //Limpiamos la baraja por si hubiera una creada
            listaCartas.clear()

            for (palo in Palos.values()) {
                for ((indice, numero) in Naipes.values().withIndex()) {
                    val carta = Carta(
                        numero,
                        palo,
                        indice + 1,
                        asignarValor(indice + 1),
                        0
                    )
                    carta.idDrawable = getIdDrawable(context, "${carta.palo.name[0].toLowerCase()}${indice+1}")
                    listaCartas.add(carta)
                }
            }
        }

        private fun getIdDrawable(context: Context, nombreCarta: String): Int {
            return context.resources.getIdentifier(
                nombreCarta,
                "drawable",
                context.packageName
            )
        }

        private fun asignarValor(numero: Int): Int {
            return when {
                numero == 1 -> 11 // As
                numero > 10 -> 10 // Figuras (Jota, Reina, Rey)
                else -> numero
            }
        }

        private fun asignarImagen(numero: Int, palo: Palos): Int {
            val nombreImagen = "${palo.name[0].lowercaseChar()}${numero}".toInt()
            return nombreImagen
        }

        fun barajar() {
            listaCartas.shuffle()
        }

        fun dameCarta(): Carta {
            val carta = listaCartas.last()
            listaCartas.removeLast()
            return carta
        }

        fun borrarBaraja() {
            listaCartas.clear()
        }

        fun startGame(context: Context) {
            crearBaraja(context)
            barajar()
        }

        fun getReverseCard(): Carta {
            return (Carta(Naipes.As, Palos.Picas, 0, 0, R.drawable.reverse))
        }
    }
}