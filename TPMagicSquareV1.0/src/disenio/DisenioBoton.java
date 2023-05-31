package disenio;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class DisenioBoton implements MouseListener {

	JButton _boton;
	Color _backgroundOriginal;
	Color _backgroundCambiar;
	Color _letraOriginal;
	Color _letraCambiar;
	
	public DisenioBoton(JButton boton) {
		if(boton != null) {
    		_boton = boton;
    		_backgroundOriginal = boton.getBackground();
    		_backgroundCambiar = Color.white;
    		_letraOriginal = boton.getForeground();
    		_letraCambiar = boton.getBackground();
		}

	}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(_boton != null && _boton.isEnabled()) {
			_boton.setBackground(_backgroundCambiar);
			_boton.setForeground(_letraCambiar);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(_boton != null && _boton.isEnabled()) {
			_boton.setBackground(_backgroundOriginal);
			_boton.setForeground(_letraOriginal);
		}
	}



	
	
	
	
}
