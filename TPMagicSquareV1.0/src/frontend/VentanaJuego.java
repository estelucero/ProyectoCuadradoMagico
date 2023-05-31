package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controladores.Controlador;
import disenio.DisenioBoton;
import disenio.IconoCerrarVentana;
import juego.Juego;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private JButton iniciar;
	private JButton rendirse;
	private JPanel Grilla;
	private JTextField[][] matriz;
	private Juego __juego__;

	private JLabel tiempoText;
	private JLabel tiempoJuego;
	private Thread actualizarTiempo;

	private static Color COLORFONDO = new Color(0, 0, 51);

	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(COLORFONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		iniciar = new JButton("Iniciar");
		iniciar.setBounds(10, 22, 89, 23);
		iniciar.setFocusable(false);
		iniciar.setBorderPainted(false);
		iniciar.setForeground(new Color(255, 255, 255));
		iniciar.setBackground(new Color(0, 0, 51));
		iniciar.setFont(new Font("Leelawadee UI", Font.BOLD, 13));
		iniciar.addMouseListener(new DisenioBoton(iniciar));
		contentPane.add(iniciar);

		rendirse = new JButton("Rendirse");
		rendirse.setBounds(98, 22, 89, 23);
		rendirse.setFocusable(false);
		rendirse.setBorderPainted(false);
		rendirse.setEnabled(false);
		rendirse.setForeground(new Color(255, 255, 255));
		rendirse.setBackground(new Color(0, 0, 51));
		rendirse.setFont(new Font("Leelawadee UI", Font.BOLD, 13));
		rendirse.addMouseListener(new DisenioBoton(rendirse));
		contentPane.add(rendirse);

		tiempoText = new JLabel("Tiempo: ");
		tiempoText.setHorizontalAlignment(SwingConstants.CENTER);
		tiempoText.setBounds(185, 22, 89, 23);
		tiempoText.setFocusable(false);
		tiempoText.setForeground(new Color(255, 255, 255));
		tiempoText.setBackground(new Color(0, 0, 51));
		tiempoText.setFont(new Font("Leelawadee UI", Font.BOLD, 13));
		contentPane.add(tiempoText);

		tiempoJuego = new JLabel("");
		tiempoJuego.setHorizontalAlignment(SwingConstants.CENTER);
		tiempoJuego.setForeground(Color.WHITE);
		tiempoJuego.setBounds(263, 22, 89, 23);
		tiempoJuego.setForeground(new Color(255, 255, 255));
		tiempoJuego.setBackground(new Color(0, 0, 51));
		tiempoJuego.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(tiempoJuego);

		JLabel iconoXcerrar = new IconoCerrarVentana();
		iconoXcerrar.setBounds(761, 0, 39, 34);
		contentPane.add(iconoXcerrar);

		iniciar.addActionListener(new AccionesIniciar());
		rendirse.addActionListener(new AccionesRendirse());

		// --------------boton Volver
		JButton botonVolver = fabricaInterfaz.crearBoton("Volver", 10, 600, 110, 30);

		botonVolver.setHorizontalAlignment(SwingConstants.CENTER);
		fabricaInterfaz.cambiarTamanioFuente(botonVolver, 20);
		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.reproducirClick();
				Controlador.abrirVentana(Controlador.getVentanaMain(),Controlador.getVentanaInGame());
				frenarTodo();

			}
		});

		botonVolver.addMouseListener(new DisenioBoton(botonVolver));
		getContentPane().add(botonVolver);

	}

	private void crearGrilla(int tamanio) {
		// Si la grilla no es nula tengo que remover la anterior
		if (Grilla != null) {
			contentPane.remove(Grilla);
		}
		matriz = new JTextField[tamanio][tamanio];
		Grilla = new JPanel();
		Grilla.setVisible(false); // Evito que se vea
		Grilla.setOpaque(false);
		Grilla.setBounds(10, 64, 780, 507);
		contentPane.add(Grilla);
		Grilla.setLayout(new GridLayout(tamanio, tamanio, 20, 20));

		for (int i = 0; i < tamanio; i++) {
			for (int j = 0; j < tamanio; j++) {
				JTextField celda = new JTextField();
				celda.setColumns(10); // No se que hace lo puso window builder
				celda.setFont(new Font("Helvetica", Font.BOLD, 30));
				celda.setHorizontalAlignment(SwingConstants.CENTER);

				if (i == tamanio - 1 && j == tamanio - 1) {
					// Estoy en la esquina derecha inferior. No la quiero mostrar
					celda.setVisible(false);
				} else if (i == tamanio - 1 || j == tamanio - 1) {
					// Las ultima fla y la ultima columna se pintan en rojo.
					celda.setBackground(Color.RED);
					celda.setForeground(Color.WHITE);
					celda.setEditable(false);
				} else {
					// Si no son las ultimas les agrego interaccion
					celda.addKeyListener(new AccionesCelda(celda, i, j));
					
					// Sonido al clickear en la celda
					celda.addMouseListener( new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							Controlador.reproducirClickCelda();
						}
					});
				}

				matriz[i][j] = celda;
				Grilla.add(celda);
			}
		}
	}

	private class AccionesCelda implements KeyListener{
		JTextField _celda;
		int _fila;
		int _columna;

		/**
		 * Sirve para identificar la celda
		 * 
		 * @param celda
		 * @param fila
		 * @param columna
		 */
		public AccionesCelda(JTextField celda, int fila, int columna) {
			super();
			if (celda != null) {
				_celda = celda;
				_fila = fila;
				_columna = columna;
			} else {
				throw new IllegalArgumentException("Debe pasar una celda como parametro");
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			int key = e.getKeyChar();
			boolean esNumero = (key >= 49) && (key <= 57);
			if (!esNumero || _celda.getText().length() > 0) {
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Al soltar la tecla se ejecuta este evento.
			if (__juego__ != null) {
				if (_celda.getText().length() != 0) {
					__juego__.ingresoNumeroEnGrillaJuego(_fila, _columna, Integer.valueOf(_celda.getText()));
				} else {
					__juego__.ingresoNumeroEnGrillaJuego(_fila, _columna, 0);
				}

				cambiarEstadosCasillas();

				if (__juego__.gano()) {
					frenarTodo();
					jugadorGano();
				}
			}
		}

		/**
		 * Cambia de color las casillas de los resultados objetivos.
		 * Si esta bien las colaca en verde.
		 * Si esta mal las coloca en rojo.
		 */
		private void cambiarEstadosCasillas() {
			if (__juego__ != null && matriz != null) {
				Map<String, boolean[]> filasYcolumnas = __juego__.filasYColumnasQueEstanBien();
				boolean[] filas = filasYcolumnas.get("fila");
				boolean[] columnas = filasYcolumnas.get("columna");
				for (int i = 0; i < filas.length; i++) {
					if (filas[i] == true) {
						matriz[i][matriz.length - 1].setBackground(Color.green);
						;
					} else {
						matriz[i][matriz.length - 1].setBackground(Color.red);
						;
					}
				}
				for (int i = 0; i < columnas.length; i++) {
					if (columnas[i] == true) {
						matriz[matriz.length - 1][i].setBackground(Color.green);
					} else {
						matriz[matriz.length - 1][i].setBackground(Color.red);
					}
				}
			}
		}

	}

	private class AccionesIniciar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Vacio la Grilla
			vaciarGrilla();

			// Inicia la instancia de Juego. Creo la grilla logica.
			__juego__ = new Juego(Controlador.getDificultad());
			__juego__.iniciar();

			// Creo la Grilla GUI para el usuario.
			crearGrilla(__juego__.getTamanio() + 1);

			// Carga a la grilla los valores de la instancia de Juego
			cargarValoresAlaGrilla();

			// Muestro la grilla
			Grilla.setVisible(true);

			// Deshabilita la opcion del boton
			deshabilitar();

			// Habilito la opcion de rendirse
			rendirse.setEnabled(true);

			// Correr cronometro
			actualizarTiempo = new Thread(new ActualizadorCronometro());
			actualizarTiempo.start();
			
        	Controlador.reproducirClick();
		}

		private void deshabilitar() {
			iniciar.setBackground(new Color(0, 0, 51));
			iniciar.setForeground(new Color(255, 255, 255));
			iniciar.setEnabled(false);
		}

		private void cargarValoresAlaGrilla() {
			if (__juego__ != null) {
				// Carga los valores en las casillas rojas y setea su color Rojo
				for (int indice = 0; indice < matriz.length - 1; indice++) {
					matriz[matriz.length - 1][indice].setText("" + __juego__.obtenerResultadosSolucion("c", indice));
					matriz[indice][matriz.length - 1].setText("" + __juego__.obtenerResultadosSolucion("f", indice));
					matriz[matriz.length - 1][indice].setBackground(Color.red);
					matriz[indice][matriz.length - 1].setBackground(Color.red);
				}
			}
		}

		private void vaciarGrilla() {
			if (matriz != null) {
				for (int fila = 0; fila < __juego__.getTamanio(); fila++) {
					for (int columna = 0; columna < __juego__.getTamanio(); columna++) {
						matriz[fila][columna].setText("");
					}
				}
			}

		}

	}

	private class AccionesRendirse implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Controlador.reproducirSonidoPerdio();
			
			// Freno el juego
			frenarTodo();

			// Explicar al usuario
			mensajeRendirse();

			// Cargo una posible Solucion
			cargarSolucion();

			// Deshabilito rendirse
			deshabilitar();

			// Habilito iniciar
			iniciar.setEnabled(true);
			
        	Controlador.reproducirClick();
		}

		private void deshabilitar() {
			rendirse.setBackground(new Color(0, 0, 51));
			rendirse.setForeground(new Color(255, 255, 255));
			rendirse.setEnabled(false);
		}

		private void mensajeRendirse() {
			JOptionPane.showMessageDialog(getContentPane(),
					"Usted se ha rendido. A continuacion se mostrara una solucion.");
		}

		private void cargarSolucion() {
			if (__juego__ != null) {
				for (int fila = 0; fila < matriz.length - 1; fila++) {
					for (int columna = 0; columna < matriz.length - 1; columna++) {
						matriz[fila][columna].setText("" + __juego__.obtenerValoresGrillaSolucion(fila, columna));
					}
				}
				Grilla.setVisible(true);
			}

		}

	}

	@SuppressWarnings("deprecation")
	private void frenarTodo() {
		if(Grilla != null) {
			Grilla.setVisible(false);
		}
		if (actualizarTiempo != null) {
			// Freno el cronometro
			actualizarTiempo.stop();
		}
		iniciar.setEnabled(true);
		rendirse.setEnabled(false);
	}

	private void jugadorGano() {
		Controlador.reproducirSonidoGano();
		iniciar.setEnabled(true);
		rendirse.setEnabled(false);
		//Aca agrego para haer que se vaya a la ventana para meter los records 
		setVisible(false);
		Controlador.setearTiempoLogrado(__juego__.getTiempo());
		Controlador.abrirVentana(Controlador.getVentanaCargarRecord(), Controlador.getVentanaInGame());
	}

	private class ActualizadorCronometro implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (__juego__ != null && !__juego__.gano()) {
					tiempoJuego.setText(__juego__.getTiempo());
				} else {
					tiempoJuego.setText("00:00:00");
				}
			}
		}

	}

}
