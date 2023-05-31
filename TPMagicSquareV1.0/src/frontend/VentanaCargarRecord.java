package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controladores.Controlador;
import disenio.DisenioBoton;

@SuppressWarnings("serial")
public class VentanaCargarRecord extends  JFrame {

	private JTextField nombre;
	private JButton botonSubida ;
	private JPanel contentPane;
	private JLabel suRecord;
	private JLabel felicidades;
	private JLabel iconoXcerrar;
	private JLabel record;

	/**
	 * Create the application.
	 */
	public VentanaCargarRecord() {
		
		initialize();
		setUndecorated(true);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		 contentPane = new JPanel();
		setContentPane(contentPane);
		fabricaInterfaz.setVentana(this, "Record",  100, 100, 486, 450,new Color(0, 0, 51));
		
		/*
		 * Icono cerrar ventana 
		 */
		iconoXcerrar = fabricaInterfaz.crearBotonCerrar(447, 0, 39, 34);
		
		contentPane.add(iconoXcerrar);
		
		/*
		 * Felicidades
		 */
		 felicidades = fabricaInterfaz.crearLabelEstiloRecordCarga(23, 50, 450, 50, "!!FELICIDADES USTED HA GANADO¡¡");
		contentPane.add(felicidades);
		/*
		 * Texto 
		 */
		suRecord = fabricaInterfaz.crearLabelEstiloRecordCarga(150, 116, 200, 31, "SU RECORD ES :");
		contentPane.add(suRecord);
		nombre = new JTextField();
		nombre.setBounds(154, 219, 175, 45);
		
		nombre.setColumns(10);
		contentPane.add(nombre);
		/*
		 * Boton de subida 
		 */
		botonSubida = fabricaInterfaz.crearBoton("SUBIR", 154, 280, 175, 45);	
		botonSubida.addMouseListener(new DisenioBoton(botonSubida) );
		contentPane.add(botonSubida);
		botonSubida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Paso el nombre al archivo de txt
				Controlador.cargarNombre(nombre.getText());
				nombre.setText("");
				record.setText("");
				Controlador.abrirVentana(Controlador.getVentanaMain(), Controlador.getVentanaCargarRecord());
				
				Controlador.reproducirClick();
			}
		});
		/*
		 * Boton de continuar
		 */
		JButton botonIrIinicio = fabricaInterfaz.crearBoton("CONTINUAR", 154, 355, 175, 45);
			
		fabricaInterfaz.cambiarTamanioFuente(botonIrIinicio, 23);		
		botonIrIinicio.addMouseListener(new DisenioBoton(botonIrIinicio) );
		contentPane.add(botonIrIinicio);
		botonIrIinicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre.setText("");
				Controlador.abrirVentana(Controlador.getVentanaMain(), Controlador.getVentanaCargarRecord());
				Controlador.reproducirClick();
			}
		});
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
		/*
		 * Cargo el tiempo
		 */
		if(b == true) { // Solo cuando se llama a esta ventana me fijo el tiempo logrado
			record = fabricaInterfaz.crearLabelEstiloRecordCarga(168, 158, 150, 50, "");
			record.setText(Controlador.getRecord());
			record.setAlignmentX(SwingConstants.CENTER);
			contentPane.add(record);
		}
	}
	
}
