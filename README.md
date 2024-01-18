# Practica02 - Recuperación Trimestre 1

## Carta más alta

Creardos clases enumeradas con la información de los Palos y Naipes de una baraja francesa:

	* Palos: OROS, BASTOS, ESPADAS y COPAS.
	* Napies: UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, SOTA, CABALLO y REY.


Crear dos clases llamadas Carta y Baraja.

- La clase Carta tendrá las siguientes propiedades:
	
	* nombre (Naipes)
	* palo (Palos)
	* puntos (Int)
	* idDrawable (Int)

	** idDrawable es un número que me ayudará a encontrar mi imagen en los recursos.

- La clase Baraja tendrá su única propiedad y los métodos dentro de un companiion object, ya que la vamos a tratar de forma estática y no vamos a generar objetos de dicha clase:
	
	* listaCartas (ArrayList<Carta>)
	
	Métodos:
	
	* crearBaraja() => Generar la lista de 40 cartas de la baraja.
	* barajar() => Desordenar las cartas de la lista de cartas de la baraja (shuffle)
	* dameCarta() => Retorna la última carta de la lista de cartas y la elimina de la baraja.

Funcionamiento de la aplicación:

	* La aplicación debe mostrar dos cartas boca abajo (jugador 1 y jugador 2).

	* Debajo de ella un botón: "Reiniciar".

	* Si pulsamos en cualquiera de las dos cartas, la imagen de la carta boca abajo debe cambiar por la imagen de la carta de la baraja que está en la última posición (método dameCarta de Baraja). Debe existir una única baraja y dará 2 cartas, una al jugador 1 y otra al jugador 2.

	* Si pulsamos en el botón "Reiniciar" volverá a crear la lista de cartas de la baraja, las desordenará y mostrará las imágenes de las cartas boca abajo.

        * Dependiendo del resultado de la carta más alta, debe mostrar un texto o diálogo "Jugador 1 gana".

        * Opcional: puedes dar un número de posibilidades mayor para adquirir una carta, por ejemplo de 1 a 3 posibilidades. Para este punto deberás añadir un botón para indicar que te quedas con la carta actual y no seguir pidiendo otra.

Recursos:

	* 41 imágenes en la carpeta drawable, una boca abajo y el resto de cartas de una baraja española (por ejemplo, en Freepik tenéis recursos gratuitos con los que podéis hacerlo).
	* Las imágenes tendrán un tamaño reducido que ocupen poco espacio (si son redondeadas, mejor en PNG por permitir la transparencia del fondo).
	* Para retornar el id de un recurso mediante una variable podéis usar 
	
	    val context = LocalContext.current

		context.resources.getIdentifier("nombreRecurso", "drawable", context.packageName)

	** Si generáis los nombres de los recursos cómo el nombre del palo y su idDrawable os será muy fácil crear una función Composable que recupere el id del recurso...
		Por ejemplo, algo así... "${carta.palo.toString().lowercase()}_${carta.idDrawable}"
