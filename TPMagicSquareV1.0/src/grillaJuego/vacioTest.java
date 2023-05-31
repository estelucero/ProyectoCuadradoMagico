package grillaJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class vacioTest {

	@Test
	public void grillaVacioAcierto() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		for(int fila=0;fila<grilla.getTamanio();fila++) {
			for(int columna=0;columna<grilla.getTamanio();columna++) {
				grilla.modificar(fila, columna, 1);
			}
			
		}
		grilla.vaciarMatriz();
		assertTrue(grilla.estaVacia());
	}
	@Test
	public void grillaVacioFalso() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		grilla.modificar(0, 0, 1);
		grilla.estaVacia();
		assertFalse(grilla.estaVacia());
		}
	}


