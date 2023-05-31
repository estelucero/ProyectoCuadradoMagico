package sonido;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Clase encargada de contener las direcciones de audios asi como reproducirlos
 * @author 
 */
public class Audios {

	private static String _dir = System.getProperty("user.dir");
	private static String _rutaMusica = _dir + "//src//sonido//musicGameTP.wav";
	private static String _rutaSonidoClick = _dir + "//src//sonido//sonidoClick.wav";
	private static String _rutaSonidoGano = _dir + "//src//sonido//sonidoWin.wav";
	private static String _rutaSonidoPerdio = _dir + "//src//sonido//sonidoPerdio.wav";
	private static String _rutaSonidoClickCelda = _dir + "//src//sonido//sonidoClickCelda.wav";
	private static Clip _musica;
	
	public static void musicaPlay()  {
		try {
		if(_musica == null || !_musica.isActive()) {
			File archivoMusica = new File(_rutaMusica);
			AudioInputStream stream = AudioSystem.getAudioInputStream(archivoMusica.getAbsoluteFile());
			_musica = AudioSystem.getClip();
			_musica.open(stream);
			_musica.loop(Clip.LOOP_CONTINUOUSLY);
			_musica.start();
		}
		}catch(Exception e){
			
		}
	}
	
	public static void musicaStop() {
		if(_musica != null && _musica.isActive()) {
			_musica.stop();
		}
	}
	
	public static void sonidoClick() {
		reproducir(_rutaSonidoClick, (float) 0.1, false);
	}
	
	public static void sonidoClickCelda() {
		reproducir(_rutaSonidoClickCelda, (float) 0.1, false);
	}

	public static void sonidoPerdio() {
		reproducir(_rutaSonidoPerdio, (float) 0.1, false);
	}
	
	public static void sonidoGano() {
		reproducir(_rutaSonidoGano, (float) 0.1, false);
	}
	
	/**
	 * Reproduce un archivo .wav pasadole su ruta. 
	 * Volumen es un valor entre 1.0 y 0.0 donde 1.0 = 100% y 0.0 = 0% de volumen,
	 * Recibe un tercer parametro para reproducirlo en loop de asi quererlo:
	 * 			 false = no hay loop
	 * 			 true  = hay loop
	 * @param ruta
	 * @param volumen
	 * @param loop
	 */
	private static void reproducir(String ruta, Float volumen, boolean loop) {
		try {
			// Creo el archivo
			File archivo = new File(ruta);
			// Creo el Stream de Audio
			AudioInputStream stream = AudioSystem.getAudioInputStream(archivo.getAbsoluteFile());
			// Le asigno un clip a mi variable Clip
			Clip sonido = AudioSystem.getClip();
			// Abro el audio que esta en la en mi stream 
			sonido.open(stream);
			
			// Volumen del clip
			FloatControl gainControl = (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);        
			gainControl.setValue(20f * (float) Math.log10(volumen));
			
			// Si quiere que se reproduzca en loop
			if(loop) {
				sonido.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			// Reproduzco el audio
			sonido.start();
		} catch (Exception e) {
		}
	}
}
