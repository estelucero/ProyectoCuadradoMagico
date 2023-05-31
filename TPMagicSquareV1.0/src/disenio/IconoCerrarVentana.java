package disenio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class IconoCerrarVentana extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8319491395429590318L;

	public IconoCerrarVentana() {
		setText("X");
		setVisible(true);
		setForeground(new Color(255, 255, 255));
	    setFont(new Font("Verdana", Font.BOLD, 18));
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setOpaque(true);
	    setBackground(new Color(0, 0, 51));
	    setBounds(461, 0, 39, 34);
		// Cerrar ventana
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.red);
				setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(0, 0, 51));
				setForeground(new Color(255, 255, 255));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.lightGray);
				setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
}
