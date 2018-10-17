package practica2;

import javax.swing.JLabel;

public class HiloMovimientoJefe implements Runnable
{
	
	JLabel Jefe;
	
	boolean topeArriba = false, topeAbajo = false;
	int posYBajada, posYSubida;
	
	public HiloMovimientoJefe(JLabel lblJefe)
	{
		// TODO Auto-generated constructor stub
		Jefe = lblJefe;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				Thread.sleep(5);
				Jefe.setLocation(Jefe.getX(), Jefe.getY() - 1);
				if(Jefe.getY() == -5) {
					break;
				}
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		moverAbajo();
	}

	private void moverAbajo()
	{
		// TODO Auto-generated method stub
		while(true) {
			try
			{
				Thread.sleep(5);
				Jefe.setLocation(Jefe.getX(), Jefe.getY() + 1);
				if(Jefe.getY() == 275) {
					moverArriba();
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

	private void moverArriba()
	{
		// TODO Auto-generated method stub
		while(true) {
			try
			{
				Thread.sleep(5);
				Jefe.setLocation(Jefe.getX(), Jefe.getY() - 1);
				if(Jefe.getY() == -5) {
					moverAbajo();
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
}
