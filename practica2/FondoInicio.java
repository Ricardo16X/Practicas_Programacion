package practica2;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoInicio extends JPanel{
	
	ImageIcon img;
	
	FondoInicio(){
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		img = new ImageIcon(getClass().getClassLoader().getResource("fondo_Inicio.png"));
		g.drawImage(img.getImage(), 0, 0, null);
	}
}
