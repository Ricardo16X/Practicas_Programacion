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
	Image iconDer, iconIzq, iconDisparoDer, iconDisparoIzq;
	Icon imageDer, imageIzq, imageDisparoDer, imageDisparoIzq;
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
		iconIzq = new ImageIcon(getClass().getClassLoader().getResource("caminataIzq.png")).getImage();
		imageIzq = new ImageIcon(iconIzq.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
		
		iconDer = new ImageIcon(getClass().getClassLoader().getResource("caminataDer.png")).getImage();
		imageDer = new ImageIcon(iconDer.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
		
		iconDisparoDer = new ImageIcon(getClass().getClassLoader().getResource("CupDisparoDer.png")).getImage();
		imageDisparoDer = new ImageIcon(iconDisparoDer.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
		
		// TODO Auto-generated method stub
		Frame.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_A:
						Cuphead.setIcon(imageIzq);
						FiguraDerecha = false;
						FiguraIzquierda = true;
						break;
					case KeyEvent.VK_D:
						Cuphead.setIcon(imageDer);
						FiguraDerecha = true;
						FiguraIzquierda = false;
						break;
					case KeyEvent.VK_J:
					case KeyEvent.VK_K:
						if (FiguraIzquierda)
						{
							Cuphead.setIcon(imageDisparoDer);
						}
						else if (FiguraDerecha)
						{
							Cuphead.setIcon(imageDisparoDer);
						}
						
						break;
				}
				
			}
		});
	}
}
