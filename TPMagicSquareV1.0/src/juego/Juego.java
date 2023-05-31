package juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.Timer;

import grillaJuego.GrillaJuego;

public class Juego {

	private GrillaJuego _grilla;
	private Boolean _estaJugando;
	private Timer _cronometro;
	private String _textCronometro;
	protected int centesimas_segundos;
	protected int segundos;
	protected int minutos;
	private int dificultad;
	public enum Dificultad {
		FACIL(3),
		NORMAL(4),
		DIFICIL(5);

		private int valor;
		
		Dificultad(int i) {
			valor = i;
		}
		
		int getInt(){
			return this.valor;
		}
	}
	
	public Juego(Dificultad dificultad) {
		if(dificultad != null) {
			switch(dificultad) {
			case FACIL:
				this.dificultad=3;
				break;
			case NORMAL:
				this.dificultad=4;
				break;
			case DIFICIL:
				this.dificultad=5;
				break;
			default:
				System.out.println("ESTA POR DEFECTO");
				this.dificultad=4;
			}
		}else {
			this.dificultad = 4;
		}
		
		
		_grilla = new GrillaJuego(this.dificultad,1,9);
		_estaJugando = false;
	}
	
	public boolean iniciar() {
		_estaJugando = true;
		iniciarCronometro();
		return _estaJugando;
	}
	
	
	/*
	 * Va a ser el ingreso de la grilla
	 */
	public void ingresoNumeroEnGrillaJuego(int i, int j, int num) {
		_grilla.modificar(i, j, num);
	}
	
	public boolean gano() { 
		return _grilla.estaBienMatriz();
	}
	
	public boolean verificacionDeDatosValidos(String fila,String columna,String numero) {
		if(!(verificacionDeConversion(fila)&&verificacionDeConversion(columna)&&verificacionDeConversion(numero))) {
			return false;
		}
		return verificacionDeDatosValidos( Integer.parseInt(fila),Integer.parseInt(columna),Integer.parseInt(numero)) ;
	}
	/* 
	 * Verifica que los datos esten bien dentro del rango para que la grilla no se rompa
	 */

	
	
	public boolean parar() {
		_estaJugando = false;
		return _estaJugando;
	}
	
	/*
	 * Verifica que el numero y columna se puedan convertir,me parece que de esto se encarga el GUI
	 */
	private boolean verificacionDeConversion(String verficarEntrada){
		try {
			Integer.parseInt(verficarEntrada);
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	private boolean verificacionDeDatosValidos(int fila,int columna,int numero) {
		return this._grilla.verificacionIngresos(fila,columna,numero);
	}

	public int obtenerResultadosSolucion(String fila_o_columna, int indice) {
		try {
			return _grilla.obtenerResultadoSolucion(fila_o_columna, indice);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int obtenerValoresGrillaSolucion(int fila, int columna) {		
		return _grilla.obtenerValoresGrillaSolucion(fila,columna);
	
	}
	
	public Map<String, boolean[]> filasYColumnasQueEstanBien() {
		return _grilla.filasYColumnasQueEstanBien();
	}
	
	public void iniciarCronometro() {
		_cronometro = new Timer(10,_actualizadorCronometro);
		_cronometro.start();
		
	}
	
	private ActionListener _actualizadorCronometro = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 centesimas_segundos ++;
	            if(centesimas_segundos == 60)
	            {
	                segundos++;
	                centesimas_segundos = 0;
	            }
	            if(segundos == 60)
	            {
	                minutos ++;
	                segundos = 0;
	            }
				
				
				
				_textCronometro = (minutos<=9?"0":"")+minutos+":"+(segundos <= 9?"0":"")+segundos+":"+(centesimas_segundos <=9?"0":"")+centesimas_segundos;
		}
		
	};
	
	public String getTiempo() {
		return _textCronometro;
	}

	public int getTamanio() {
		return _grilla.getTamanio();
	}

}
