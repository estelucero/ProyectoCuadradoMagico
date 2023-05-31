package grillaJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class modificarTest {

	@Test
	public void agregarNumero0() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertTrue(grilla.modificar(1, 1, 0));
	}
	
	
	@Test
	public void agregarNumeroFueraValoresInferior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertFalse(grilla.modificar(1, 1, -1));
	}
	@Test
	public void agregarNumeroFueraValoresSuperior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertFalse(grilla.modificar(1, 1, 3));
	}
	@Test
	public void agregarNumeroDentroValores() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertTrue(grilla.modificar(1, 1, 1));
	}
	@Test
	public void agregarNumeroFueraFilaSuperior() {
		GrillaJuego grilla=new GrillaJuego(2, 1, 2);
		assertFalse(grilla.modificar(2, 1, 1));
	}
	@Test
	public void agregarNumeroFueraFilaInferior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertFalse(grilla.modificar(-1, 1, 1));
	}
	@Test
	public void agregarNumeroFueraColumnaSuperior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertFalse(grilla.modificar(1, 3, 1));
	}
	@Test
	public void agregarNumeroFueraColumnaInferior() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertFalse(grilla.modificar(1, -1, 1));
	}
	@Test
	public void agregarNumero() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		assertTrue(grilla.modificar(1, 1, 1));
	}
	@Test
	public void reemplazarNumero() {
		GrillaJuego grilla=new GrillaJuego(3, 1, 2);
		grilla.modificar(1, 1, 1);
		assertTrue(grilla.modificar(1, 1, 2));
	}
	

}
