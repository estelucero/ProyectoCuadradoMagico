package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import disenio.IconoCerrarVentana;

@SuppressWarnings("serial")
public class fabricaInterfaz extends JFrame {

	
	
	
	public static void setVentana(JFrame ventana,String titulo,int x,int y,int ancho,int alto) {
		
		ventana.setTitle(titulo);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(x, y, ancho, alto);
		ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setLayout(null);
        
	}
	public static void setVentana(JFrame ventana,String titulo,int x,int y,int ancho,int alto,Color colorFondo) {
		
		ventana.setTitle(titulo);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(x, y, ancho, alto);
		ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setBackground(colorFondo);
        ventana.getContentPane().setLayout(null);
       
        
	}
	public static void setVentana(JFrame ventana,String titulo,int x,int y,int ancho,int alto,Color colorFondo,Border borde) {
		 
		ventana.setTitle(titulo);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(x, y, ancho, alto);
		ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setBackground(colorFondo);
        ((JComponent) ventana.getContentPane()).setBorder(borde);
        ventana.getContentPane().setLayout(null);
        
	}
	
	
	public static JButton crearBoton(String texto, int x, int y, int ancho, int alto) {

		JButton boton = new JButton(texto);

		boton.setFocusable(false);
		boton.setBorderPainted(false);
		
		boton.setFont(new Font("Georgia", Font.PLAIN, 25));
		boton.setForeground(new Color(255, 255, 255));
		boton.setBackground(new Color(0, 0, 51));
		boton.setBounds(x, y, ancho, alto);
		boton.setVisible(true);
		return boton;
	}
	public static void cambiarTamanioFuente(JComponent componente,float tamanio) {
		componente.setFont(componente.getFont().deriveFont(tamanio));
	}

	public static IconoCerrarVentana crearBotonCerrar(int x, int y, int ancho, int alto) {
		IconoCerrarVentana boton = new IconoCerrarVentana();
		boton.setBounds(x, y, ancho, alto);
		return boton;
	}
	
	public static JButton crearBotonMusica(int x,int y,int ancho,int alto) {
		 
		JButton boton=new JButton(new ImageIcon("Media/icono_musica.png"));
		boton.setBounds(x, y, ancho, alto);
		boton.setFocusable(false);
		return boton;
	     
	}
	public static JLabel crearLabelEstiloRecord(int x,int y,int ancho,int alto,String texto) {
		JLabel label =new JLabel(texto);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(x, y, ancho, alto);
		return label;
		
	}
	public static JLabel crearLabelEstiloRecordCarga(int x,int y,int ancho,int alto,String texto) {
		JLabel label =new JLabel(texto);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 255, 255));
		label.setBackground(new Color(228, 221, 225));
		label.setFont(new Font("Georgia", Font.PLAIN, 25));
		label.setBounds(x, y, ancho, alto);
		return label;
		
	}
	


}
