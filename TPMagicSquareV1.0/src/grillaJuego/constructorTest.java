package grillaJuego;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class constructorTest {

	@Test(expected = IllegalArgumentException.class)
	public void construirGrillaTamanioInvalido() {
		GrillaJuego grilla=new GrillaJuego(1, 3, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void construirGrillaValor1Menor1() {
		GrillaJuego grilla=new GrillaJuego(3, 0, 8);
	}
	@Test(expected = IllegalArgumentException.class)
	public void construirGrillaValor2Menor1() {
		GrillaJuego grilla=new GrillaJuego(3, 5, 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void construirGrillaValor2IgualValor1() {
		GrillaJuego grilla=new GrillaJuego(3, 5, 5);
	}

	

}
