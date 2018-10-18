package practica2;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HiloDisparosJefe implements Runnable
{
	// JPanel pantallaJuego;
	JLabel PosicionJefe, PosicionCuphead, vidaRestanteCuphead;
	int VidaCuphead, contadorBalas;
	JLabel[] DisparosJefe;
	
	int CoordY_Jefe;
	
	// Imagen de Impacto en Cuphead
	Image imagenIzq = new ImageIcon(getClass().getClassLoader().getResource("golpe_cuphead_atras.png")).getImage();
	Icon golpe_I = new ImageIcon(imagenIzq.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	
	Image imagenDer = new ImageIcon(getClass().getClassLoader().getResource("golpe_cuphead.png")).getImage();
	Icon golpe_D = new ImageIcon(imagenDer.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	
	public HiloDisparosJefe(JLabel lblJefe, int vidaCuphead, JLabel[] balasJefe, int contador_BalasJefe, JLabel lblCuphead, JLabel lblVidaCuphead)
	{
		// TODO Auto-generated constructor stub
		// pantallaJuego = panelJuego;
		PosicionJefe = lblJefe;
		VidaCuphead = vidaCuphead;
		DisparosJefe = balasJefe;
		contadorBalas = contador_BalasJefe;
		PosicionCuphead = lblCuphead;
		vidaRestanteCuphead = lblVidaCuphead;
	}
	
	@Override
	public void run()
	{
		
		CoordY_Jefe = PosicionJefe.getY() + 35;
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				if (DisparosJefe[contadorBalas].isVisible() == false)
				{
					DisparosJefe[contadorBalas].setVisible(true);
				}
				DisparosJefe[contadorBalas].setLocation(DisparosJefe[contadorBalas].getX() - 5, CoordY_Jefe);
				Thread.sleep(10);
				// Detiene el ciclo while cuando llega al borde izquierdo de la pantalla
				if (DisparosJefe[contadorBalas].getX() <= 0)
				{
					DisparosJefe[contadorBalas].setVisible(false);
					break;
				}
				if (((DisparosJefe[contadorBalas].getX() < PosicionCuphead.getX() + 80) && (DisparosJefe[contadorBalas].getX() > PosicionCuphead.getX())))
				{
					
					if (PosicionCuphead.getY() < DisparosJefe[contadorBalas].getY() && DisparosJefe[contadorBalas].getY() < PosicionCuphead.getY() + 80)
					{
						DisparosJefe[contadorBalas].setVisible(false);
						// Diseño de Golpe
						if (HiloImagenCuphead.FiguraDerecha)
						{
							PosicionCuphead.setIcon(golpe_D);
						}
						else if (HiloImagenCuphead.FiguraIzquierda)
						{
							PosicionCuphead.setIcon(golpe_I);
						}
						// Le bajo la vida al Cuphead;
						VentanaJuego.VidaCuphead -= 10;
						// Muestra de vida en el Jlabel
						vidaRestanteCuphead.setText("HP: " + VentanaJuego.VidaCuphead);
						
						if (VentanaJuego.VidaCuphead <= 0)
						{
							PosicionCuphead.setVisible(false);
						}
						break;
					}
					else if (DisparosJefe[contadorBalas].getY() < PosicionCuphead.getY() && DisparosJefe[contadorBalas].getY() + 40 > PosicionCuphead.getY())
					{
						DisparosJefe[contadorBalas].setVisible(false);
						// Le bajo la vida al Cuphead;
						VentanaJuego.VidaCuphead -= 10;
						// Muestra de vida en el Jlabel
						vidaRestanteCuphead.setText("HP: " + VentanaJuego.VidaCuphead);
						// Diseño de Golpe
						if (HiloImagenCuphead.FiguraDerecha)
						{
							PosicionCuphead.setIcon(golpe_D);
						}
						else if (HiloImagenCuphead.FiguraIzquierda)
						{
							PosicionCuphead.setIcon(golpe_I);
						}
						if (VentanaJuego.VidaCuphead <= 0)
						{
							PosicionCuphead.setVisible(false);
						}
						break;
					}
				}
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
