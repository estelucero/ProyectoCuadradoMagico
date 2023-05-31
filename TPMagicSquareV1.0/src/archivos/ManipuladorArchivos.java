package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juego.Juego.Dificultad;

public class ManipuladorArchivos {
	// Directorio del proyecto. (va cambiando dependiendo de la computador que
	// ejecute el proyecto)
	private static String _directorioProyecto = System.getProperty("user.dir");

	// Directorio donde quedara guardado el archivo tiempos.txt dentro de el
	// proyecto.
	private static String _directorioArchivo = _directorioProyecto + "//datosJuego//tiempos.txt";

	// Obtengo todas las lineas en un map
	private static Map<Integer, String> posicionesLineas = lineasDeText();
	/*
	 * Lee cada linea del archivo y la va aniaadiendo al diccionario
	 */
	public static Map<Integer, String> lineasDeText() {
		Map<Integer, String> diccionario = new HashMap<Integer, String>();
		ArrayList<String> lineas = (ArrayList<String>) leerArchivoTXT(_directorioArchivo);
		for (int indice = 1; indice <= 10; indice++) {
			try {
				String linea = lineas.get(indice - 1);
				diccionario.put(indice, linea);
			} catch (Exception e) {
				diccionario.put(indice, null);
			}

		}
		return diccionario;
	}

	/**
	 * Recibe la ruta del archivo.
	 * Lee un archivo .txt y devuelve una List<String> donde cada elemento es una linea del archivo
	 * @param rutaArchivo 
	 * @return
	 */
	public static List<String> leerArchivoTXT(String rutaArchivo) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lineas = new ArrayList<String>(10);

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(rutaArchivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lineas;

	}


	/**
	 * Obtiene el argumento "00:00:00" de una linea del .txt
	 * Para ello se usa el split que separa ambos parametros tiempo-nombre.
	 * @param cadenaPuesto
	 * @return -1 si la linea es null. tiempoTotal = "00:00:00"
	 */
	private static String obtenerTiempoFormatText(String cadenaPuesto) {
		if(cadenaPuesto == null) {
			return "-1";
		}
		String[] cadenaTiempo = cadenaPuesto.split("-");
		String tiempoTotal = cadenaTiempo[0]; // En la primera posicion se encuentra el tiempo - el otro es el nombre
		return tiempoTotal;
	}

	
	/**
	 * Esta funcion escribe en el archivo .txt que guarda el tiempo, nombre y dificultad, asi como tambien
	 * el puntaje obtenido
	 * @param tiempo
	 * @param nombre
	 * @param dificultad
	 */
	public static void escribirPuntaje(String tiempo, String nombre, Dificultad dificultad) {
		FileWriter fichero = null;
		PrintWriter escritor = null;
		try {
			fichero = new FileWriter(_directorioArchivo);									 // Representa el fichero .txt
			escritor = new PrintWriter(fichero);  			                                 // Se usa para usar la funcion de write en el fichero 
			
			Integer puestoParada = null; // Donde se sobreescribio
			String lineaAmover = null;	// La linea que hay que mover ya que se reemplazo
			Integer[] posiciones = new Integer[]{1,2,3,4,5,6,7,8,9,10}; // Todas las posiciones en orden simplemente para leer mejor
			
			Double puntaje = SistemaPuntaje.obtenerPuntaje(tiempo, Dificultad.FACIL);
			// Recorro el map que representa la tabla de records..
			for(Integer puesto: posiciones) {
				String tiempoActual = obtenerTiempoFormatText(posicionesLineas.get(puesto));
				Double puntajeActual = SistemaPuntaje.obtenerPuntaje(tiempoActual, Dificultad.FACIL);
			
				if(puntaje > puntajeActual) { 						     // Comparo los dos puntajes
					/*
					 * Lo que hago si ese puntaje es mayor:
					 * 	- Me guardo la linea que habia en esta posicion
					 * 	- Asi tambien como la posicion
					 *  - Creo la nueva linea y la agrego al map(tabla)
					 *  - Corto el ciclo
					 */
					lineaAmover = posicionesLineas.get(puesto);							
					puestoParada = puesto;
					String nuevaLinea = tiempo + "-" + nombre + "-" + Dificultad.FACIL + "-" + puntaje; 
					posicionesLineas.put(puesto, nuevaLinea);
					break;
				}
				else if(posicionesLineas.get(puesto) == null){
					/*
					 * Sino como estoy recorriendo de arriba hacia abajo (de 1 a 10)
					 * si encuentro un lugar con null lo escribo ahi y corto el ciclo
					 */
					posicionesLineas.put(puesto, tiempo + "-" + nombre + "-" + Dificultad.FACIL + "-" + puntaje);
					break;
				}
			}
			
			// Si puesto parada es null significa que no hubo una parada, por ende no hay que escribir nada.
			if(puestoParada != null) {
				
				String lineaAnterior = null;
				// Recorro el map en orden (puede mejorarse la complejidad)
				for(Integer puesto: posiciones) {
					
					// Si puesto esta debajo del puesto donde reemplazamos entonces -->
					if(puesto > puestoParada) {
						/*
						 * - Me guardo la linea actual en lineaAnterior
						 * - Reemplazo la linea por la lineaAmover
						 * - Luego la que tengo que mover sera la lineaAnterior asi que le paso el valor
						 * < Como son 10 posiciones nunca estare agregando una posicion 11. 
						 */
						lineaAnterior = posicionesLineas.get(puesto);
						posicionesLineas.put(puesto, lineaAmover);
						lineaAmover = lineaAnterior;
					}
				}
			}
			
			/*
			 * Una vez cambie la linea o no. Escribo devuelta el .txt gracias al map.
			 */
			for(Integer puesto: posiciones) {
				if(posicionesLineas.get(puesto) != null) {
					escritor.println(posicionesLineas.get(puesto));  					// Escribe una linea y agrega un salto de linea.
				}
			}
			
		} catch (Exception e) {
			
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
