package practica2;

import javax.swing.JLabel;

public class HiloSalto implements Runnable
{
	JLabel Cuphead;
	
	public HiloSalto(JLabel lblCuphead)
	{
		// TODO Auto-generated constructor stub
		Cuphead = lblCuphead;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				Thread.sleep(4);
				Cuphead.setLocation(Cuphead.getX(), Cuphead.getY() - 1);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Cuphead.getY() + 90 == 240)
			{
				caer();
				break;
			}
		}
	}
	
	void caer()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				Cuphead.setLocation(Cuphead.getX(), Cuphead.getY() + 1);
				Thread.sleep(3);
				if (Cuphead.getY() + 90 == 410)
				{
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
