package grillaJuego;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ganaJuegoTest {

	GrillaJuego grilla;

	@Before
	public void incializar() {
		GrillaJuego.cambiarSemilla(5);
		grilla = new GrillaJuego(2, 1, 9);
		grilla.vaciarMatrizSolucion();
		grilla.modificarSolucion(0, 0, 2);
		grilla.modificarSolucion(0, 1, 1);
		grilla.modificarSolucion(1, 0, 2);
		grilla.modificarSolucion(1, 1, 3);

	}

	@Test
	public void ganoJuego() {
		grilla.imprimirGrillaSolucion();
		grilla.modificar(0, 0, 2);
		grilla.modificar(0, 1, 1);
		grilla.modificar(1, 0, 2);
		grilla.modificar(1, 1, 3);
		assertTrue(grilla.estaBienMatriz());

	}

	@Test
	public void noGanoJuegoCompleto() {
		grilla.modificar(0, 0, 9);
		grilla.modificar(0, 1, 1);
		grilla.modificar(1, 1, 9);
		grilla.modificar(1, 0, 9);
		assertFalse(grilla.estaBienMatriz());

	}

	@Test
	public void grillaNoCompletaDiagonales() {
		grilla.imprimirGrillaSolucion();
		grilla.modificar(0, 0, 3);
		grilla.modificar(1, 1, 5);
		assertFalse(grilla.estaBienMatriz());

	}
	@Test
	public void grillaNoCompletaColumna() {
		grilla.imprimirGrillaSolucion();
		grilla.modificar(0, 0, 1);
		grilla.modificar(0, 1, 2);
		assertFalse(grilla.estaBienMatriz());

	}
	@Test
	public void grillaNoCompletaFila() {
		grilla.imprimirGrillaSolucion();
		grilla.modificar(0, 1, 1);
		grilla.modificar(1, 1, 3);
		assertFalse(grilla.estaBienMatriz());

	}
	@Test
	public void grillaVaciaTest() {
		grilla.vaciarMatriz();
		
		assertFalse(grilla.estaBienMatriz());

	}

}
