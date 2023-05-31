package grillaJuego;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrillaJuego {

	/**
	 * Atributos la grilla normal y la grilla Solucion cada una con su respectivo
	 * resultado
	 */
	private int[][] _grilla;
	private Map<String, Integer> _resultados;

	private int[][] _grillaSol;
	private Map<String, Integer> _resultadosSolucion;
	private int _tamanio;

	private int _minimoValorAceptable;
	private int _maximoValorAceptable;
	private Random r = new Random();

	/*
	 * Esto sirve para el Junite hay que ver en el futuro como hago para sacarlo
	 */
	private static Integer seed;

	/*
	 * Genera la grilla tamanio x tamanio = n x n (cuadrada)
	 */
	protected static void cambiarSemilla(Integer numero) {
		GrillaJuego.seed = numero;
	}

	public GrillaJuego(int tamanio, int cota1, int cota2) {
		if (tamanio <= 1) {
			throw new IllegalArgumentException("El tamanio debe ser un entero positivo: " + tamanio);
		}
		if (cota1 < 1) {
			throw new IllegalArgumentException("El valor minimo debe ser un entero positivo: " + cota1);
		}
		if (cota2 < 1) {
			throw new IllegalArgumentException("El valor minimo debe ser un entero positivo: " + cota2);
		}
		if (cota2 == cota1) {
			throw new IllegalArgumentException("El valor minimo tiene que ser diferente de valor maximo");
		}
		_tamanio = tamanio;

		_resultados = new HashMap<String, Integer>();
		_resultadosSolucion = new HashMap<String, Integer>();
		_grilla = generarGrilla(tamanio);
		_grillaSol = generarGrillaSolucion(tamanio, cota1, cota2);

		_minimoValorAceptable = Math.min(cota1, cota2);
		_maximoValorAceptable = Math.max(cota1, cota2);

	}

	/*
	 * Agrega un numero en la posicion dada de la grilla
	 */
	private void agregarNumero(int fila, int columna, int num) {

		_grilla[fila][columna] = num;
		_resultados.put("f" + fila, _resultados.get("f" + fila) + num);
		_resultados.put("c" + columna, _resultados.get("c" + columna) + num);
	}

	/*
	 * Elimina el numero en una posicion dada de la grilla
	 */

	private void eliminarNumero(int fila, int columna) {

		_resultados.put("f" + fila, _resultados.get("f" + fila) - _grilla[fila][columna]);
		_resultados.put("c" + columna, _resultados.get("c" + columna) - _grilla[fila][columna]);
		_grilla[fila][columna] = 0;

	}

	/*
	 * Modifica el valor de una celda en la grilla
	 */
	public boolean modificar(int fila, int columna, int num) {

		if (!verificacionIngresos(fila, columna, num)) {
			return false;
		}
		eliminarNumero(fila, columna);
		agregarNumero(fila, columna, num);
		return true;
	}

	public boolean verificacionIngresos(int fila, int columna, int num) {

		return verificacionFilaColumna(fila, columna) && verificacionNumero(num);
	}

	/*
	 * Vaciamos una grilla para que el usuario intenete de vuelva jugar la grilla
	 */

	public void vaciarMatriz() {
		_grilla = new int[_tamanio][_tamanio];
		for (String f_c : _resultados.keySet()) {
			_resultados.put(f_c, 0);
		}
	}

	/*
	 * Verificamos que la matriz esta bien resuleta, esto podemos ver de cambiar la
	 * firma del metodo
	 */
	public boolean estaBienMatriz() {

		//return noHayFilasVacias() && sumaDeLasFilasCorrecto();
		return this.estaBienFilasyColumnas();

	}



	/*
	 * Imprime la matriz del jugador, aca creo que estamos haciendo verificaciones
	 * hay que revisar
	 */

	public void imprimir() {

		for (int fila = 0; fila < _tamanio; fila++) {
			for (int columna = 0; columna < _tamanio; columna++) {
				if (_grilla[fila][columna] > 0) {
					if (_grilla[fila][columna] > 9) {
						System.out.print(" " + _grilla[fila][columna]);
					} else {
						System.out.print("  " + _grilla[fila][columna]);
					}
				} else {
					System.out.print("  " + " ");
				}
			}
			if (_resultadosSolucion.get("f" + fila) > 9) {
				System.out.println(" " + _resultadosSolucion.get("f" + fila));
			} else {
				System.out.println("  " + _resultadosSolucion.get("f" + fila));
			}
		}

		for (int columna = 0; columna < _tamanio; columna++) {
			if (_resultadosSolucion.get("c" + columna) > 9) {
				System.out.print(" " + _resultadosSolucion.get("c" + columna));
			} else {
				System.out.print("  " + _resultadosSolucion.get("c" + columna));
			}
		}
		System.out.println("");
	}

	/*
	 * Imprime grilla solucion
	 */
	public void imprimirGrillaSolucion() {
		for (int fila = 0; fila < _tamanio; fila++) {
			for (int columna = 0; columna < _tamanio; columna++) {
				if (_grillaSol[fila][columna] > 0) {
					if (_grillaSol[fila][columna] > 9) {
						System.out.print(" " + _grillaSol[fila][columna]);
					} else {
						System.out.print("  " + _grillaSol[fila][columna]);
					}
				} else {
					System.out.print("  " + " ");
				}
			}
			if (_resultadosSolucion.get("f" + fila) > 9) {
				System.out.println(" " + _resultadosSolucion.get("f" + fila));
			} else {
				System.out.println("  " + _resultadosSolucion.get("f" + fila));
			}
		}

		for (int columna = 0; columna < _tamanio; columna++) {
			if (_resultadosSolucion.get("c" + columna) > 9) {
				System.out.print(" " + _resultadosSolucion.get("c" + columna));
			} else {
				System.out.print("  " + _resultadosSolucion.get("c" + columna));
			}
		}
		System.out.println("");
	}

	/*
	 * Obtenemos el tamanio de la matriz en una direccion
	 */
	public int getTamanio() {
		return this._tamanio;
	}

	/*
	 * Nos fijamos si la matriz esta vacia
	 */
	public boolean estaVacia() {
		boolean acum=true;
		for(int fila=0;fila<this._tamanio;fila++) {
			for(int columna=0;columna<this._tamanio;columna++) {
				acum=acum&&celdaVacia(fila, columna);
			}
		}
			
		
		return acum;

		
	}
	/*
	 * Esto nos devuelve una lista con las columnas y filas que estan bien
	 */
	public Map<String,boolean[]> filasYColumnasQueEstanBien(){
		Map<String,boolean[]> salida= new HashMap<String,boolean[]>();
		salida.put("fila", new boolean[this._tamanio]);
		salida.put("columna", new boolean[this._tamanio]);
		for(int indice=0;indice<this._tamanio;indice++) {
			if(this.resultadoDeLaFilaEstaBien(indice)&&!estaFilaTieneCeldasVacias(indice)) {
				salida.get("fila")[indice]=true;
			}
			if(resultadoDeLaColumnaEstaBien(indice)&&!estaColumnaTieneCeldaVacia(indice)) {
				salida.get("columna")[indice]=true;
			}
		}
		return salida;
		
		
	}
	

	/*
	 * ----------------------------------------------------------------- Metodos
	 * Privados ----------------
	 */

	private int[][] generarGrilla(int tamanio) {
		for (int indiceDiccionario = 0; indiceDiccionario < _tamanio; indiceDiccionario++) {
			_resultados.put("f" + indiceDiccionario, 0);
			_resultados.put("c" + indiceDiccionario, 0);
		}
		return new int[tamanio][tamanio];
	}

	private int[][] generarGrillaSolucion(int tamanio, int minimoValor, int maximoValor) {
		int[][] grilla = new int[tamanio][tamanio];

		for (int indiceDiccionario = 0; indiceDiccionario < tamanio; indiceDiccionario++) {
			_resultadosSolucion.put("f" + indiceDiccionario, 0);
			_resultadosSolucion.put("c" + indiceDiccionario, 0);
		}

		for (int fila = 0; fila < tamanio; fila++) {
			for (int columna = 0; columna < tamanio; columna++) {

				

				// Genero la grilla random
				grilla[fila][columna] = numeroAleatorio(minimoValor, maximoValor);

				// Sumando a cada fila
				_resultadosSolucion.put("f" + fila, _resultadosSolucion.get("f" + fila) + grilla[fila][columna]);

				// Sumando a cada columna
				_resultadosSolucion.put("c" + columna, _resultadosSolucion.get("c" + columna) + grilla[fila][columna]);

			}

		}
		return grilla;
	}

	/*
	 * Generamos un numero aleatorio entre el min y max
	 */
	private int numeroAleatorio(int min, int max) {

		if (seed != null) {

			// r.setSeed(seed);

		}

		return min + r.nextInt(max-min);
	}

	@SuppressWarnings("unused")
	private int obtenerResultado(String fila_o_col, int indice) {
		if (fila_o_col.equals("f") || fila_o_col.equals("c")) {
			if (indice >= 0 && indice < _tamanio) {
				return _resultados.get(fila_o_col + indice);
			} else {
				throw new IllegalArgumentException("El numero de fila o columan debe estar en el rango: " + indice);
			}
		} else {
			throw new IllegalArgumentException("Debe introducir f - fila o ' - columna: " + fila_o_col);
		}
	}

	private boolean indiceValido(int indiceDiccionario) {

		if (indiceDiccionario < this._tamanio && indiceDiccionario >= 0) {
			return true;
		}
		return false;
	}
	/*
	 * Se fija si esta bien la columna o fila dada
	 */

	
	
	private boolean estaBienFilasyColumnas() {
		return sumaDeLasFilasYColumnasCorrecto() && !hayCeldasVacias();
	}
	
	/*
	 * Veirifica si el diccionario solucion es igual al diccionario del jugador
	 */

	private boolean sumaDeLasFilasYColumnasCorrecto() {
		for (int indiceDiccionario = 0; indiceDiccionario < _tamanio; indiceDiccionario++) {
			// Comprobamos la suma de las filas
			if (!(resultadoDeLaFilaEstaBien(indiceDiccionario) && resultadoDeLaColumnaEstaBien( indiceDiccionario))) {
				return false;
			}
		}
		return true;
	}

	
	
	private boolean resultadoDeLaFilaEstaBien(int indiceDiccionario) {

		/*
		 * Verificamos que el indice este dentro del rango
		 */
		if (!indiceValido(indiceDiccionario)) {
			return false;
		}
		/*
		 * Verificamos si fila_columna es true es porque el usuario se refiere a que es una fila
		 */		
			return _resultados.get("f" + indiceDiccionario) == _resultadosSolucion.get("f" + indiceDiccionario);
	}
	
	private boolean resultadoDeLaColumnaEstaBien(int indiceDiccionario) {
		return _resultados.get("c" + indiceDiccionario) == _resultadosSolucion.get("c" + indiceDiccionario);
	}

	/*
	 * Se fija si el numero que ingreso el usuario esta bien
	 */
	private boolean verificacionNumero(int numeroIngreso) {
		
		if(numeroIngreso==0) {
			return true;
		}
		
		if (numeroIngreso < _minimoValorAceptable || numeroIngreso > _maximoValorAceptable) {
			return false;
		}
		return true;
	}
	/*
	 * Se fija si la fila y columna estan dentro de rango
	 */

	private boolean verificacionFilaColumna(int fila, int columna) {
		// Si se cumple alguna lanza false.
		return !(fila < 0 || fila >= _tamanio || columna < 0 || columna >= _tamanio);
	}
	
	private boolean hayCeldasVacias() {
		return hayFilasConCeldasVacias() || hayColumnasConCeldasVacias();
	}
	/*
	 * Verifica que todas las filas no tengan espaciosVacios
	 */
	private boolean hayFilasConCeldasVacias() {
		for (int fila = 0; fila < _tamanio; fila++) {
			if(estaFilaTieneCeldasVacias(fila)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean estaFilaTieneCeldasVacias(int fila) {
		for (int columna = 0; columna < _tamanio; columna++) {
			if (celdaVacia(fila, columna)) {
				return true;
			}
		}
		return false;
	}
	private boolean hayColumnasConCeldasVacias() {
		for (int columna = 0; columna < _tamanio; columna++) {
			if(estaColumnaTieneCeldaVacia(columna)) {
				return true;
			}
		}
		return false;
	}
	 private boolean estaColumnaTieneCeldaVacia(int columna) {
		for(int fila=0;fila<this._tamanio;fila++) {
			if(celdaVacia(fila, columna)) {
				return true;
			}
		}
		return false;
	}
	 private boolean celdaVacia(int fila,int columna) {
		 if(this._grilla[fila][columna]==0){
			 return true;
		 }
		 return false;
	 }
	boolean modificarSolucion(int fila, int columna, int num) {

		if (!verificacionIngresos(fila, columna, num)) {
			return false;
		}
		agregarNumeroSolucion(fila,columna,num);
		actualizarSolucion(fila,columna,num);
		return true;
	}
	private void actualizarSolucion(int fila,int columna,int num) {
				// Sumando a cada fila
				_resultadosSolucion.put("f" + fila, _resultadosSolucion.get("f" + fila) + num);

				// Sumando a cada columna
				_resultadosSolucion.put("c" + columna, _resultadosSolucion.get("c" + columna) + num);
	}

	private void agregarNumeroSolucion(int fila, int columna, int num) {
		_grillaSol[fila][columna]=num;
		
	}

	 void vaciarMatrizSolucion() {
		_grillaSol = new int[_tamanio][_tamanio];
		for (String f_c : _resultados.keySet()) {
			_resultadosSolucion.put(f_c, 0);
		}
	}

	public int obtenerResultadoSolucion(String fila_o_columna, int indice) {
		try {
			return _resultadosSolucion.get(fila_o_columna + indice);
		} catch (Exception e) {
			return 0;
		}
	}

	public int obtenerValoresGrillaSolucion(int fila, int columna) {
		// Verificacion....
		
		return _grillaSol[fila][columna];
	}
	
	
	}




