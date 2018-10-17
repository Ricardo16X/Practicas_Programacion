package practica2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

public class Marcadores
{
	
	private JFrame frame;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Marcadores window = new Marcadores();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Marcadores()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(251, 210, 20));
		frame.setBounds(100, 100, 400, 500);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Juego Terminado - Gracias por Jugar Mini Cuphead!");
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("iconoVentana.jpg")).getImage());
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				Menu mn = new Menu();
				mn.frmInicio.setVisible(true);
				frame.setVisible(false);
			}
		});
		JLabel lblDibujoSaltarin = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("iconoMarcador.gif")));
		lblDibujoSaltarin.setBounds(152, 42, 100, 100);
		frame.getContentPane().add(lblDibujoSaltarin);
		
		table = new JTable();
		table.setBounds(10, 153, 372, 309);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(table);
		
		JLabel lblMarcadores = new JLabel("Marcadores");
		lblMarcadores.setForeground(Color.WHITE);
		lblMarcadores.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblMarcadores.setBounds(142, 11, 122, 27);
		frame.getContentPane().add(lblMarcadores);
		
	}
}
