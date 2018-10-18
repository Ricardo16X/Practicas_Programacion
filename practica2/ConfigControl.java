package practica2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConfigControl implements WindowListener, ActionListener, KeyListener
{
	public JFrame frame;
	private JTextField teclaDerecha;
	private JTextField teclaIzq;
	private JTextField teclaSalto;
	private JTextField disparo1_1;
	private JTextField disparo2_1;
	private JButton btnAceptar;
	
	static int disparo1Elegido = 0, disparo2Elegido = 0;
	
	static Control control[] = new Control[1];
	
	// Array de Teclas seleccionadas
	int[] Teclas = new int[5];
	
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
					ConfigControl window = new ConfigControl();
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
	public ConfigControl()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Configuración de Controles");
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("iconoVentana.jpg")).getImage());
		
		JLabel lblConfiguracinGeneral = new JLabel("Configuraci\u00F3n General");
		lblConfiguracinGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblConfiguracinGeneral.setBounds(171, 11, 162, 21);
		frame.getContentPane().add(lblConfiguracinGeneral);
		
		JLabel lblTeclaAdelante = new JLabel("Tecla Adelante");
		lblTeclaAdelante.setBounds(10, 65, 115, 14);
		frame.getContentPane().add(lblTeclaAdelante);
		
		JLabel lblTeclaAtrs = new JLabel("Tecla Atr\u00E1s");
		lblTeclaAtrs.setBounds(10, 105, 115, 14);
		frame.getContentPane().add(lblTeclaAtrs);
		
		JLabel lblTeclaArriba = new JLabel("Tecla Salto");
		lblTeclaArriba.setBounds(10, 151, 115, 14);
		frame.getContentPane().add(lblTeclaArriba);
		
		JLabel lblTeclaAbajo = new JLabel("Tecla Disparo 1");
		lblTeclaAbajo.setBounds(10, 197, 115, 14);
		frame.getContentPane().add(lblTeclaAbajo);
		// JTextField
		teclaDerecha = new JTextField();
		teclaDerecha.addKeyListener(this);
		teclaDerecha.setBounds(171, 58, 100, 20);
		frame.getContentPane().add(teclaDerecha);
		teclaDerecha.setColumns(10);
		
		teclaIzq = new JTextField();
		teclaIzq.addKeyListener(this);
		teclaIzq.setBounds(171, 98, 100, 20);
		frame.getContentPane().add(teclaIzq);
		teclaIzq.setColumns(10);
		
		teclaSalto = new JTextField();
		teclaSalto.addKeyListener(this);
		teclaSalto.setBounds(171, 144, 100, 20);
		frame.getContentPane().add(teclaSalto);
		teclaSalto.setColumns(10);
		
		disparo1_1 = new JTextField();
		disparo1_1.addKeyListener(this);
		disparo1_1.setBounds(171, 190, 100, 20);
		frame.getContentPane().add(disparo1_1);
		disparo1_1.setColumns(10);
		
		disparo2_1 = new JTextField();
		disparo2_1.addKeyListener(this);
		disparo2_1.setColumns(10);
		disparo2_1.setBounds(171, 237, 100, 20);
		frame.getContentPane().add(disparo2_1);
		// Fin JTextField
		JLabel Label_ImagenJugador = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("muestraJugador.gif")));
		Label_ImagenJugador.setBackground(Color.WHITE);
		Label_ImagenJugador.setBounds(339, 43, 215, 215);
		frame.getContentPane().add(Label_ImagenJugador);
		
		JLabel lblTeclaDisparo = new JLabel("Tecla Disparo 2");
		lblTeclaDisparo.setBounds(10, 244, 115, 14);
		frame.getContentPane().add(lblTeclaDisparo);
		
		JComboBox<String> comboDisiparo1 = new JComboBox<String>();
		comboDisiparo1.setBounds(171, 278, 139, 22);
		for (int disparo1 = 0; disparo1 < CargaDatos.contDisparo; disparo1++)
		{
			comboDisiparo1.addItem(CargaDatos.tipoDisparo[disparo1].getNombre());
		}
		comboDisiparo1.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent cambio)
			{
				disparo1Elegido = comboDisiparo1.getSelectedIndex();
			}
		});
		frame.getContentPane().add(comboDisiparo1);
		
		JLabel lblTipoDisparo = new JLabel("Tipo Disparo 1");
		lblTipoDisparo.setBounds(10, 286, 115, 14);
		frame.getContentPane().add(lblTipoDisparo);
		
		JLabel lblTipoDisparo_1 = new JLabel("Tipo Disparo 2");
		lblTipoDisparo_1.setBounds(10, 331, 115, 14);
		frame.getContentPane().add(lblTipoDisparo_1);
		
		JComboBox<String> comboDisparo2 = new JComboBox<String>();
		comboDisparo2.setBounds(171, 323, 139, 22);
		for (int disparo2 = 0; disparo2 < CargaDatos.contDisparo; disparo2++)
		{
			comboDisparo2.addItem(CargaDatos.tipoDisparo[disparo2].getNombre());
		}
		comboDisparo2.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent cambio)
			{
				disparo2Elegido = comboDisparo2.getSelectedIndex();
			}
		});
		frame.getContentPane().add(comboDisparo2);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(189, 439, 91, 23);
		btnAceptar.addActionListener(this);
		frame.getContentPane().add(btnAceptar);
		frame.setVisible(false);
		
		frame.setLocationRelativeTo(null);
		this.frame.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAceptar)
		{
			// verificarIguales(Teclas);
			if (!(teclaDerecha.getText().isEmpty() || teclaIzq.getText().isEmpty() || teclaSalto.getText().isEmpty() || disparo1_1.getText().isEmpty() || disparo2_1.getText().isEmpty()))
			{
				if (verificarIguales(Teclas) < 1)
				{
					control[0] = new Control(Teclas[0], Teclas[1], Teclas[2], Teclas[3], Teclas[4], disparo1Elegido, disparo2Elegido);
					JOptionPane.showMessageDialog(null, "Control configurado!");
					Menu.control = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Hay teclas repetidas");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Faltan teclas por asignar!");
			}
		}
	}
	
	private int verificarIguales(int[] Teclas)
	{
		// TODO Auto-generated method stub
		int contador = 0;
		try
		{
			for (int i = 0; i < Teclas.length; i++)
			{
				for (int j = i + 1; j < Teclas.length; j++)
				{
					if (Teclas[j] == Teclas[i])
					{
						contador++;
					}
				}
			}
		}
		catch (NullPointerException error)
		{
			JOptionPane.showMessageDialog(null, "Faltan Opciones!!");
		}
		return contador;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		Menu ini = new Menu();
		ini.frmInicio.setVisible(true);
		this.frame.setVisible(false);
	}
	
	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		// Límite de Teclas
		if ((arg0.getKeyCode() >= 65 && arg0.getKeyCode() <= 90) || (arg0.getKeyCode() >= 37 && arg0.getKeyCode() <= 40))
		{
			if (arg0.getSource() == teclaDerecha)
			{
				teclaDerecha.setText("");
				teclaDerecha.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
				Teclas[0] = arg0.getKeyCode();
			}
			else if (arg0.getSource() == teclaIzq)
			{
				teclaIzq.setText("");
				teclaIzq.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
				Teclas[1] = arg0.getKeyCode();
			}
			else if (arg0.getSource() == teclaSalto)
			{
				teclaSalto.setText("");
				teclaSalto.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
				Teclas[2] = arg0.getKeyCode();
			}
			else if (arg0.getSource() == disparo1_1)
			{
				disparo1_1.setText("");
				disparo1_1.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
				Teclas[3] = arg0.getKeyCode();
			}
			else if (arg0.getSource() == disparo2_1)
			{
				disparo2_1.setText("");
				disparo2_1.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
				Teclas[4] = arg0.getKeyCode();
			}
		}
		else
		{
			if (arg0.getSource() == teclaDerecha)
			{
				teclaDerecha.setText("");
			}
			else if (arg0.getSource() == teclaIzq)
			{
				teclaIzq.setText("");
			}
			else if (arg0.getSource() == teclaSalto)
			{
				teclaSalto.setText("");
			}
			else if (arg0.getSource() == disparo1_1)
			{
				disparo1_1.setText("");
			}
			else if (arg0.getSource() == disparo2_1)
			{
				disparo2_1.setText("");
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
