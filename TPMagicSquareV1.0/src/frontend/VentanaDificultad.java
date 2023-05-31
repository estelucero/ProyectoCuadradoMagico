package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.Controlador;
import disenio.DisenioBoton;
import disenio.IconoCerrarVentana;
import juego.Juego.Dificultad;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaDificultad extends JFrame {
	
	private JButton btnFacil;
	private JButton btnMedio;
	private JButton btnDificil; 
	Dificultad dificultadActual = Dificultad.NORMAL;

    public VentanaDificultad() {
        // Tamanios
        super();
        setUndecorated(true);
        setTitle("Cuadro Magico");
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 584, 500);

        //-------------------ContentePane
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 51));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //-------------------Boton de facil
        btnFacil = new JButton("Facil");
        btnFacil.setFocusable(false);
        btnFacil.setForeground(new Color(255, 255, 255));
        btnFacil.setBorderPainted(false);
        btnFacil.setBackground(new Color(0, 0, 51));
        btnFacil.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        btnFacil.setBounds(86, 142, 114, 34);
//        btnFacil.addMouseListener(new Hover(btnFacil));
        contentPane.add(btnFacil);
        btnFacil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.reproducirClick();
				Controlador.setearDificultad(Dificultad.FACIL);
				cambiarDificultad(Dificultad.FACIL);
			}
		});

        //-------------------Boton de medio
        btnMedio = new JButton("Medio");
        btnMedio.setFocusable(false);
        btnMedio.setBackground(new Color(255, 255, 255));
        btnMedio.setBorderPainted(false);
        btnMedio.setForeground(new Color(0, 0, 51));
        btnMedio.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        btnMedio.setBounds(86, 230, 114, 34);
//        btnMedio.addMouseListener(new Hover(btnMedio));
        contentPane.add(btnMedio);
        btnMedio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.reproducirClick();
				Controlador.setearDificultad(Dificultad.NORMAL);
				cambiarDificultad(Dificultad.NORMAL);
			}
		});

        //-------------------Boton de dificil
        btnDificil = new JButton("Dificil");
        btnDificil.setFocusable(false);
        btnDificil.setBackground(new Color(0, 0, 51));
        btnDificil.setBorderPainted(false);
        btnDificil.setForeground(new Color(255, 255, 255));
        btnDificil.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        btnDificil.setBounds(86, 318, 114, 34);
//        btnDificil.addMouseListener(new Hover(btnDificil));
        contentPane.add(btnDificil);
        btnDificil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.reproducirClick();
				Controlador.setearDificultad(Dificultad.DIFICIL);
				cambiarDificultad(Dificultad.DIFICIL);
			}
		});

        //-------------------Boton de Atras
        JButton btnAtras = new JButton("Atras");
        btnAtras.setFocusable(false);
        btnAtras.setBorderPainted(false);
        btnAtras.setBackground(new Color(0, 0, 51));
        btnAtras.setForeground(new Color(255,255,255));
        btnAtras.setFont(new Font("Serif", Font.PLAIN, 15));
        btnAtras.setBounds(10, 460, 85, 29);
        btnAtras.addMouseListener(new DisenioBoton(btnAtras));
        contentPane.add(btnAtras);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               Controlador.reproducirClick();
               Controlador.abrirVentanaMain();
            }
            
        });

        //-----------Textos de dificultad de grilla--------------
        JLabel lblNewLabel = new JLabel("Grilla de 3x3");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        lblNewLabel.setBounds(266, 149, 271, 27);
        contentPane.add(lblNewLabel);

        JLabel lblGrillaDex = new JLabel("Grilla de 4x4");
        lblGrillaDex.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrillaDex.setForeground(new Color(255, 255, 255));
        lblGrillaDex.setFont(new Font("Serif", Font.PLAIN, 20));
        lblGrillaDex.setBounds(266, 237, 281, 27);
        contentPane.add(lblGrillaDex);
        
        JLabel lblGrillaDex_1 = new JLabel("Grilla de 5x5");
        lblGrillaDex_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrillaDex_1.setForeground(new Color(255, 255, 255));
        lblGrillaDex_1.setFont(new Font("Serif", Font.PLAIN, 20));
        lblGrillaDex_1.setBounds(266, 325, 281, 27);
        contentPane.add(lblGrillaDex_1);
        
        //-----------Icono cerrar ventana
        JLabel iconoXcerrar = new IconoCerrarVentana();
        iconoXcerrar.setBounds(545, 0, 39, 34);
        contentPane.add(iconoXcerrar);
        
        JLabel explicacionDificultad = new JLabel("Los valores permitidos para cada dificutlad son de 1-9");
        explicacionDificultad.setFont(new Font("Serif", Font.BOLD, 20));
        explicacionDificultad.setForeground(Color.WHITE);
        explicacionDificultad.setHorizontalAlignment(SwingConstants.CENTER);
        explicacionDificultad.setBounds(26, 45, 521, 58);
        contentPane.add(explicacionDificultad);
	        
        //------------------------------------------------------
    }
    
    private void cambiarDificultad(Dificultad dificultad) {
    	switch (dificultad) {
		case FACIL: {
			desmarcarBoton(btnMedio);
			desmarcarBoton(btnDificil);
			marcarBoton(btnFacil);
			break;
		}
		case NORMAL: {
			desmarcarBoton(btnFacil);
			desmarcarBoton(btnDificil);
			marcarBoton(btnMedio);
			break;
		}
		case DIFICIL: {
			desmarcarBoton(btnMedio);
			desmarcarBoton(btnFacil);
			marcarBoton(btnDificil);
			break;
		}
		default:
			break;
		}
    	
    }
    
    /**
     * Desmarca el boton pasado como parametro
     */
    private void desmarcarBoton(JButton boton) {
    	boton.setBackground(new Color(0,0,51));
    	boton.setForeground(new Color(255,255,255));
    }
   
    /**
     * Marca el boton pasado como parametro
     * @param boton
     */
    private void marcarBoton(JButton boton) {
    	boton.setBackground(new Color(255,255,255));
    	boton.setForeground(new Color(0,0,51));
    }
    
    /**
     * La interaccion con los botones facil normal y dificil
     * @author 
     *
     */
    @SuppressWarnings("unused")
	private class Hover implements MouseListener{

    	JButton boton;
    	
    	public Hover(JButton boton_) {
    		boton = boton_;
    	}
    	
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			boton.setBackground(new Color(255,255,255));
			boton.setForeground(new Color(0,0,51));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			boton.setBackground(new Color(0,0,51));
			boton.setForeground(new Color(255,255,255));
		}
    	

    }
}
