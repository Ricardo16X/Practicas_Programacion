package practica2;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HiloImagenCuphead implements Runnable
{
	
	JFrame Frame;
	JLabel Cuphead;
	// Redimensionar imágenes
	Image iconIzq = new ImageIcon(getClass().getClassLoader().getResource("caminataIzq.png")).getImage();
	Icon imageIzq = new ImageIcon(iconIzq.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
	
	Image iconDer = new ImageIcon(getClass().getClassLoader().getResource("caminataDer.png")).getImage();
	Icon imageDer = new ImageIcon(iconDer.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
	
	Image iconDisparoDer = new ImageIcon(getClass().getClassLoader().getResource("CupDisparoDer.png")).getImage();
	Icon imageDisparoDer = new ImageIcon(iconDisparoDer.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
	
	int TeclaIzq = ConfigControl.control[0].getTeclaAtras();
	
	static boolean FiguraDerecha = false, FiguraIzquierda = false;
	
	public HiloImagenCuphead(JFrame frame, JLabel lblCuphead)
	{
		// TODO Auto-generated constructor stub
		Frame = frame;
		Cuphead = lblCuphead;
	}
	
	@Override
	public void run()
	{
		
		// TODO Auto-generated method stub
		Frame.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == TeclaIzq)
				{
					Cuphead.setIcon(imageIzq);
					FiguraDerecha = false;
					FiguraIzquierda = true;
				}
				else if (e.getKeyCode() == ConfigControl.control[0].getTeclaAdelante())
				{
					Cuphead.setIcon(imageDer);
					FiguraDerecha = true;
					FiguraIzquierda = false;
				}
				if (e.getKeyCode() == ConfigControl.control[0].getTeclaDisparo1() || e.getKeyCode() == ConfigControl.control[0].getTeclaDisparo2())
				{
					if (FiguraIzquierda)
					{
						Cuphead.setIcon(imageDisparoDer);
					}
					else if (FiguraDerecha)
					{
						Cuphead.setIcon(imageDisparoDer);
					}
				}
			}
		});
	}
}
