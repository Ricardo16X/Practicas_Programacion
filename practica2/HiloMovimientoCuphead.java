package practica2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HiloMovimientoCuphead implements Runnable
{
	
	JFrame Frame;
	JLabel Cuphead;
	
	public HiloMovimientoCuphead(JFrame frame, JLabel lblCuphead)
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
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_A:
						if (Cuphead.getX() > 0)
						{
							Cuphead.setLocation(Cuphead.getX() - 10, Cuphead.getY());
						}
						break;
					case KeyEvent.VK_D:
						if (Cuphead.getX() + 70 < 600)
						{
							Cuphead.setLocation(Cuphead.getX() + 10, Cuphead.getY());
						}
				}
			}
		});
	}
	
}
