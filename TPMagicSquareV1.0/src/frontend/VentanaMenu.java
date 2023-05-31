package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.Controlador;
import disenio.DisenioBoton;
import disenio.IconoCerrarVentana;
import sonido.Audios;

@SuppressWarnings("serial")
public class VentanaMenu extends JFrame
{
    private JPanel contentPane;
    private JFrame frmCuadroMagico;
    private JButton btnReglas;
    private JButton btnRecords;
    private JButton btnDificultad;
    private JButton btnSalir;
    private JButton btnMuteSoundMusic;
    private JLabel lblAutors;

    //-------------------Create the frame.
    public VentanaMenu()
    {
    	setUndecorated(true);
    	
    	contentPane = new JPanel();
    	setContentPane(contentPane);
    	contentPane.setLayout(null);
    	fabricaInterfaz.setVentana(this, "Cuadro M\u00E1gico",100, 100, 500, 450,new Color(0, 0, 51),new EmptyBorder(6, 5, 5, 5));

        //-------------------Boton comenzar
		JButton btnComenzar = fabricaInterfaz.crearBoton("Comenzar",162, 61, 159, 46);
    	
        /*
         * Hace que si el boton comenzar es apretado se va a la pagina comenzar
         */
        btnComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	Controlador.reproducirClick();
                Controlador.abrirVentana(Controlador.getVentanaInGame(), Controlador.getVentanaMain());    
            }
        });        
        
        /*
         * Hace que cambie el color del boton comenzar
         */
        
        btnComenzar.addMouseListener(new DisenioBoton(btnComenzar));
        /*
         * agrega el boton comenzar al panel
         */
        contentPane.add(btnComenzar);

        //-------------------Boton dificultad
        btnDificultad = fabricaInterfaz.crearBoton("Dificultad",162, 133, 159, 46);
        btnDificultad.addMouseListener(new DisenioBoton(btnDificultad));
        
        /*
         * Hace que si el boton dificultad es apretado se va a la pagina comenzar
         */
        btnDificultad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	Controlador.reproducirClick();
                Controlador.abrirVentana(Controlador.getVentanaDificultad(), Controlador.getVentanaMain());                
            }
        });
        
        contentPane.add(btnDificultad);

        //-------------------Boton Reglas
        btnReglas = fabricaInterfaz.crearBoton("Reglas", 162, 202, 159, 46);
        
        btnReglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent mentajeReglas) {
            	Controlador.reproducirClick();
                JOptionPane.showMessageDialog(frmCuadroMagico, 
                        "OBJETIVO: El objetivo del juego consiste en igualar los n�meros presentados como RESULTADO \n" +
                        "tanto en las filas como en las columnas. Para ello se deben completar los casilleros con n�meros \n"+
                        "mayores a 0 (cero), los cuales se ir�n sumando por fila y por columna. Una vez completada una fila\n"+
                        "y/o  columna,  si  la  suma  equivale  al  respectivo  \"RESULTADO\"  entonces dicha  fila/columna  se \n"+
                        "considerar�   como  acertada  y se marcar� en color VERDE,  si se pasa  del valor se  marcar�   en \n"+
                        "color ROJO.\n"+
                        "El juego se gana cuando todas las filas y todas las columnas equivalen al resultado.\r\n" + 
                        "\r\n" + 
                        "INSERTAR N�MERO: Para insertar un n�mero en el tablero hay que hacer un click sobre el casillero \n"+
                        " a insertar, luego escribir el n�mero deseado y dar ENTER (*).\r\n" + 
                        "\r\n" + 
                        "(*) Una vez pulsado ENTER no se podr� modificar el valor del casillero.");
            }
        });
        
        btnReglas.addMouseListener(new DisenioBoton(btnReglas));
        contentPane.add(btnReglas);
        
        //------------------boton records
        btnRecords = fabricaInterfaz.crearBoton("Records",162, 261, 159, 52);
        		
        		
        btnRecords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	Controlador.reproducirClick();
                Controlador.abrirVentana(Controlador.getVentanaRecords(), Controlador.getVentanaMain());                
            }
        });	
        btnRecords.addMouseListener(new DisenioBoton(btnRecords));
        contentPane.add(btnRecords);
        
        //-------------------boton Salir
        btnSalir = fabricaInterfaz.crearBoton("Salir", 162, 336, 159, 46);
        btnSalir.addMouseListener(new DisenioBoton(btnSalir));        		
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {	
            	Controlador.reproducirClick();
            	try {Thread.sleep(400);} catch (InterruptedException e1) {}
                System.exit(0);
            }
        });
        
        contentPane.add(btnSalir);
        
        
        /*
         * --------- Icono cerrar ventana ----------------
         */
        JLabel iconoXcerrar = new IconoCerrarVentana();
        
        getContentPane().add(iconoXcerrar);
        
        //-------------------autor/es
        lblAutors = new JLabel("Juan chauvet");
        lblAutors.setForeground(new Color(255, 255, 255));
        lblAutors.setBounds(10, 425, 141, 14);
        contentPane.add(lblAutors);
        
        
        //-------------------boton de musica 
        btnMuteSoundMusic = fabricaInterfaz.crearBotonMusica(456, 406, 34, 33);
        
        btnMuteSoundMusic.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Controlador.manejarMusica();
            }
        });
        
        contentPane.add(btnMuteSoundMusic);
        
        /*
         * Musica del Juego
         */
        Audios.musicaPlay();
    }

}
