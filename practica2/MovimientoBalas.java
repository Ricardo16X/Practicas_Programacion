package practica2;

import javax.swing.JLabel;

public class MovimientoBalas implements Runnable
{
	JLabel[] imagenes;
	JLabel Cuphead, Jefe, lblVidaJefe;
	int contador = 0;
	int CoordenadaXBalaDerecha = 0, CoordenadaYBala, CoordenadaXBalaIzquierda;
	int vidaJefe;
	
	public MovimientoBalas(JLabel[] balas, int ContadorBalas, JLabel lblCuphead, int i, JLabel lblJefe, int vidaJefe, JLabel lblVidaJefe)
	{
		// TODO Auto-generated constructor stub
		contador = ContadorBalas;
		imagenes = balas;
		Cuphead = lblCuphead;
		CoordenadaYBala = i;
		Jefe = lblJefe;
		this.vidaJefe = vidaJefe;
		this.lblVidaJefe = lblVidaJefe;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		CoordenadaXBalaDerecha = Cuphead.getX() + 90;
		CoordenadaXBalaIzquierda = Cuphead.getX();
		while (true)
		{
			try
			{
				if (imagenes[contador].isVisible() == false)
				{
					imagenes[contador].setVisible(true);
				}
				CoordenadaXBalaDerecha += 5;
				imagenes[contador].setLocation(CoordenadaXBalaDerecha, CoordenadaYBala + 30);
				Thread.sleep(10);
				if (imagenes[contador].getX() > 750 && (imagenes[contador].getY() > Jefe.getY() && imagenes[contador].getY() < Jefe.getY() + 150))
				{
					setVidaJefe(10);
					lblVidaJefe.setText("HP: " + getVidaJefe());
					System.out.println(imagenes[contador].getLocation());
					imagenes[contador].setVisible(false);
					if(getVidaJefe() <= 0) {
						//	Aquí se ejecutará la nueva acción de subir nivel
						Jefe.setVisible(false);
						lblVidaJefe.setText("HP: " + 0);
					}
					break;
				}
				if (imagenes[contador].getX() >= 900)
				{
					imagenes[contador].setVisible(false);
					break;
				}
				
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}
	
	
	public void setVidaJefe(int restar) {
		
		VentanaJuego.VidaJefe -= restar;
		
							
	}
	public int getVidaJefe() {
		
		return VentanaJuego.VidaJefe ;
		
		
	}
	
	
	
	
	
	
}
