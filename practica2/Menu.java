package practica2;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;

public class Menu implements ActionListener, WindowFocusListener
{
	
	public JFrame frmInicio;
	JButton btnInicio, btnConfigControl, btnCargaDatos, btnSalir;
	static boolean control = false, datos = false;
	
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
					Menu window = new Menu();
					window.frmInicio.setVisible(true);
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
	Menu()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		FondoInicio fondoI = new FondoInicio();
		fondoI.setBounds(0, 0, 394, 375);
		frmInicio = new JFrame();
		frmInicio.setResizable(false);
		frmInicio.setTitle("Mini Cuphead");
		frmInicio.setBounds(100, 100, 400, 400);
		frmInicio.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("iconoVentana.jpg")).getImage());
		frmInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicio.getContentPane().setLayout(null);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setBounds(149, 98, 91, 23);
		btnInicio.setEnabled(false);
		btnInicio.addActionListener(this);
		frmInicio.getContentPane().add(btnInicio);
		
		btnConfigControl = new JButton("Configurar Controles");
		btnConfigControl.setEnabled(false);
		btnConfigControl.setBounds(116, 154, 154, 23);
		btnConfigControl.addActionListener(this);
		frmInicio.getContentPane().add(btnConfigControl);
		
		btnCargaDatos = new JButton("Cargar Datos");
		btnCargaDatos.setBounds(136, 210, 121, 23);
		btnCargaDatos.addActionListener(this);
		frmInicio.getContentPane().add(btnCargaDatos);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(149, 341, 91, 23);
		btnSalir.addActionListener(this);
		frmInicio.getContentPane().add(btnSalir);
		
		frmInicio.addWindowFocusListener(this);
		frmInicio.getContentPane().add(fondoI);
		frmInicio.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnSalir)
		{
			System.exit(0);
		}
		else if (e.getSource() == this.btnConfigControl)
		{
			ConfigControl config = new ConfigControl();
			config.frame.setVisible(true);
			this.frmInicio.setVisible(false);
		}
		else if (e.getSource() == this.btnCargaDatos)
		{
			CargaDatos datos = new CargaDatos();
			datos.frame.setVisible(true);
			this.frmInicio.setVisible(false);
		}
	}
	
	@Override
	public void windowGainedFocus(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == frmInicio)
		{
			if (control && datos)
			{
				btnInicio.setEnabled(true);
			}
			if (datos)
			{
				btnConfigControl.setEnabled(true);
			}
		}
	}
	
	@Override
	public void windowLostFocus(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
