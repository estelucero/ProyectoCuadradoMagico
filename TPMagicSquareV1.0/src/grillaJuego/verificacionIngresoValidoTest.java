package grillaJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class verificacionIngresoValidoTest {

	@Test
	public void filaInvalidaSuperior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(3, 1, 1));
	}
	@Test
	public void filaInvalidaInferior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(-1, 1, 1));
	}
	@Test
	public void columnaInvalidaSuperior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(1, 3, 1));
	}
	@Test
	public void columnaInvalidaInferior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(1, -1, 1));
	}
	@Test
	public void numeroInvalidoMayor() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(1, 1, 3));
	}
	@Test
	public void numeroInvalidoMenor() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertFalse(grilla.verificacionIngresos(1, 1, -1));
	}
	@Test
	public void aciertoNumero() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		
		assertTrue(grilla.verificacionIngresos(1, 1, 1));
	}

}
