package practica2;

import javax.swing.JLabel;

public class HiloReloj implements Runnable
{
	JLabel Reloj;
	
	int segundo, minuto;
	
	public HiloReloj(JLabel lblTiempo)
	{
		// TODO Auto-generated constructor stub
		Reloj = lblTiempo;
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
				
				// Cambiar el texto al JLabel
				if (segundo == 60)
				{
					segundo = 0;
					minuto++;
				}
				Reloj.setText("Tiempo: " + minuto + " : " + segundo);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
