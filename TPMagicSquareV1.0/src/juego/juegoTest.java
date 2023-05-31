package juego;

import static org.junit.Assert.*;

import org.junit.Test;

import juego.Juego.Dificultad;

public class juegoTest {

	
	

	@Test
	public void iniciarJuego() {
		Juego juego=new Juego(Dificultad.FACIL);
		assertTrue(juego.iniciar());
	}
	@Test
	public void iniciarJuegoDosVeces() {
		Juego juego=new Juego(Dificultad.FACIL);
		juego.iniciar();
		assertTrue(juego.iniciar());
	}
	
	@Test
	public void pararJuego() {
		Juego juego=new Juego(Dificultad.FACIL);
		
		assertFalse(juego.parar());
	}
	@Test
	public void pararJuego2Veces() {
		Juego juego=new Juego(Dificultad.FACIL);
		juego.parar();
		assertFalse(juego.parar());
	}
	@Test
	public void ingresoLetraFila() {
		Juego juego=new Juego(Dificultad.FACIL);
		
		assertFalse(juego.verificacionDeDatosValidos("a", "1", "3"));
	}
	@Test
	public void ingresoLetraColumna() {
		Juego juego=new Juego(Dificultad.FACIL);
		
		assertFalse(juego.verificacionDeDatosValidos("1", "a", "3"));
	}
	@Test
	public void ingresoLetraNumero() {
		Juego juego=new Juego(Dificultad.FACIL);
		
		assertFalse(juego.verificacionDeDatosValidos("1", "1", "a"));
	}
	@Test
	public void ingresoBien() {
		Juego juego=new Juego(Dificultad.FACIL);
		
		assertTrue(juego.verificacionDeDatosValidos("1", "1", "1"));
	}


}
