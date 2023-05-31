package controladores;
import javax.swing.JFrame;

import archivos.ManipuladorArchivos;
import frontend.VentanaCargarRecord;
import frontend.VentanaDificultad;
import frontend.VentanaJuego;
import frontend.VentanaMenu;
import frontend.VentanaRecord;
import juego.Juego.Dificultad;
import sonido.Audios;

/**
 * Clase que se encarga del manejo de las ventanas, asi como de algunos eventos de las mismas.
 * Estos eventos son: guardado y carga de datos como el tiempo, dificultad y nombre.
 * 					  Manejo de musica del juego.
 * Para ello se apoya de las clases ManipuladorArchivos y Audios
 * @author 
 */
public class Controlador {
	
	private static VentanaJuego _ventanaInGame = new VentanaJuego();
	private static VentanaDificultad _ventanaDificultad = new VentanaDificultad();
	private static VentanaMenu _ventanaMain = new VentanaMenu();
	private static VentanaRecord _ventanaRecords = new VentanaRecord();
	private static VentanaCargarRecord _ventanaCargaRecord= new VentanaCargarRecord();
	private static Dificultad _dificultad;
	private static boolean musica=true;
	private static String _tiempoLogrado = null;
	
	/**
	 * Recibe una ventana para abrir y otra para cerrar.
	 * @param ventanaAbrir
	 * @param ventanaCerrar
	 */
	public static void abrirVentana(JFrame ventanaAbrir,JFrame ventanaCerrar) {
		ventanaAbrir.setLocationRelativeTo(null);
		ventanaAbrir.setVisible(true);
		ventanaCerrar.setVisible(false);
		ventanaAbrir.setResizable(false);
		
	}
	public static VentanaRecord getVentanaRecords() {
		return _ventanaRecords;
	}
	public static VentanaJuego getVentanaInGame() {
		return _ventanaInGame;
	}
	public static VentanaDificultad getVentanaDificultad() {
		return _ventanaDificultad;
	}
	public static VentanaMenu getVentanaMain() {
		return _ventanaMain;
	}
	public static VentanaCargarRecord getVentanaCargarRecord() {
		return _ventanaCargaRecord;
	}
	

	
	public static void abrirVentanaInGame() {
		_ventanaInGame.setVisible(true);
		_ventanaDificultad.setVisible(false);
		_ventanaMain.setVisible(false);
		_ventanaRecords.setVisible(false);
	}
	
	
	public static void abrirVentanaDificultad() {
		_ventanaDificultad.setVisible(true);
		_ventanaMain.setVisible(false);
		_ventanaInGame.setVisible(false);
		_ventanaRecords.setVisible(false);

	}
	
	public static void abrirVentanaMain() {
		_ventanaMain.setVisible(true);
		_ventanaDificultad.setVisible(false);
		_ventanaInGame.setVisible(false);
		_ventanaRecords.setVisible(false);
	}
	public static void abrirVentanaRecord() {
		_ventanaRecords.setVisible(true);
		_ventanaMain.setVisible(false);
		_ventanaDificultad.setVisible(false);
		_ventanaInGame.setVisible(false);
		
	}
	public static void cerrarVentanaInGame() {
		_ventanaInGame.setVisible(false);
	}
	public static void cerrarVentanaDificultad() {
		_ventanaDificultad.setVisible(false);
	}
	public static void cerrarVentanaMain() {
		_ventanaMain.setVisible(false);
	}
	public static void cerrarVentanaRecords() {
		_ventanaRecords.setVisible(false);
	}
	
	/**
	 * Guarda la dificultad para luego pasarsela al objeto juego
	 * @param dificultad
	 */
	public static void setearDificultad(Dificultad dificultad) {
		_dificultad = dificultad;
	}

	/**
	 * Retorna la dificultad anteriormente seteada por el usuario
	 * @return _dificultad
	 */
	public static Dificultad getDificultad() {
		return _dificultad;
	}
	
	/**
	 * Guarda el tiempo del juego para luego guardarlo en el archivo .txt si 
	 * asi se desea.
	 * @param tiempo
	 */
	public static void setearTiempoLogrado(String tiempo) {
		_tiempoLogrado = tiempo;
	}
	
	/**
	 * Carga el nombre, puntaje, tiempo y dificultad al archivo .txt
	 * @param nombre
	 */
	public static void cargarNombre(String nombre) {
//		ManipuladorArchivos.escribirTiempo(_tiempoLogrado , nombre);	
		ManipuladorArchivos.escribirPuntaje(_tiempoLogrado, nombre, _dificultad);
	}

	/**
	 * Retorna el record anteriormente guardado, esto se usa para mostrarlo en la pantalla 
	 * que muestra el record.
	 * @return
	 */
	public static String getRecord() {
		return _tiempoLogrado;
	}
	
	/**
	 * Manejo de musica y sonidos
	 */
	
	/**
	 * Frena la musica si esta siendo reproducida y la reproduce si esta frenada.
	 */
	public static  void manejarMusica() {
		if(musica) {
			musica=false;
			Audios.musicaStop();
			return;
		}else {
			musica=true;
			Audios.musicaPlay();
		}
	}
	
	public static void reproducirClick() {
		Audios.sonidoClick();
	}
	
	public static void reproducirClickCelda() {
		Audios.sonidoClickCelda();
	}
	
	public static void reproducirSonidoPerdio() {
		Audios.sonidoPerdio();
	}
	
	public static void reproducirSonidoGano() {
		Audios.sonidoGano();
	}

}
