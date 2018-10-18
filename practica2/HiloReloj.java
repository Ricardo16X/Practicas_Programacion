package practica2;

import javax.swing.JLabel;

public class HiloReloj implements Runnable
{
	JLabel Reloj, Segundero;
	
	int segundo, minuto;
	int segundero;
	
	public HiloReloj(JLabel lblTiempo, JLabel lblSegundero)
	{
		// TODO Auto-generated constructor stub
		Reloj = lblTiempo;
		Segundero = lblSegundero;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		Reloj();
	}
	
	private void Reloj()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				Thread.sleep(1000);
				segundo++;
				segundero++;
				// Cambiar el texto al JLabel
				if (segundo == 60)
				{
					segundo = 0;
					minuto++;
				}
				Reloj.setText("Tiempo: " + minuto + " : " + segundo);
				Segundero.setText(Integer.toString(segundero));
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setSegundero(int segundos)
	{
		segundos++;
	}
	
	public int getSegundero()
	{
		return segundero;
	}
}
