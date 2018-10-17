package practica2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VentanaJuego implements KeyListener
{
	
	private JFrame frame;
	// Diseño de Nivel
	// JLabel
	JLabel lblNivel, lblTiempo, lblPuntaje, lblCuphead, lblJefe;
	JLabel lblVidaCuphead, lblVidaJefe;
	JPanel PanelJuego;
	// Fin JLabel
	
	// Hilos a implementar
	HiloReloj hiloReloj;
	HiloMovimientoJefe hiloMovJefe;
	HiloSalto hiloSalto;
	HiloImagenCuphead hiloMovCup;
	HiloMovimientoCuphead hiloMoveCup;
	MovimientoBalas hiloMovBalas;
	// Colisiones hiloColisiones;
	
	Thread threadReloj, threadMovJefe, threadMovCup, threadSalto, threadMoveCup,
			threadMovBalas, threadColisiones;
	// Fin Hilos
	
	// Imagenes en Label
	Image Imagen;
	ImageIcon paraLabel;
	private JPanel PanelSalud;
	
	public static int VidaJefe = 100;
	// Datos Cargados
	int Nivel = 1;
	
	JLabel Balas1[] = new JLabel[50];
	JLabel Balas2[] = new JLabel[50];
	
	int contador_Balas1 = 0, contador_Balas2 = 0;
	private Image balaImagen;
	private ImageIcon BalaCuphead;
	
	// Fin Imagenes en Label
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
					VentanaJuego window = new VentanaJuego();
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
	public VentanaJuego()
	{
		initialize();
		// Llamada de Hilos
		hiloReloj = new HiloReloj(lblTiempo);
		hiloMovJefe = new HiloMovimientoJefe(lblJefe);
		hiloSalto = new HiloSalto(lblCuphead);
		hiloMovCup = new HiloImagenCuphead(frame, lblCuphead);
		hiloMoveCup = new HiloMovimientoCuphead(frame, lblCuphead);
		// hiloColisiones = new Colisiones(lblCuphead, lblJefe, Balas);
		
		// Paso de Hilos
		threadReloj = new Thread(hiloReloj);
		threadMovJefe = new Thread(hiloMovJefe);
		threadMovCup = new Thread(hiloMovCup);
		threadMoveCup = new Thread(hiloMoveCup);
		// Inicio de Hilos
		threadReloj.start();
		threadMovJefe.start();
		threadMovCup.start();
		threadMoveCup.start();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == KeyEvent.VK_S)
				{
					threadSalto = new Thread(hiloSalto);
					threadSalto.start();
				}
			}
		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(this);
		
		PanelJuego = new JPanel();
		PanelJuego.setBackground(new Color((int)(Math.random() * 250 + 1), (int)(Math.random() * 250 + 1), (int)(Math.random() * 250) + 1));
		PanelJuego.setBounds(0, 30, 900, 420);
		frame.getContentPane().add(PanelJuego);
		PanelJuego.setLayout(null);
		
		lblJefe = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Jefe_1.png")));
		lblJefe.setBounds(740, 143, 150, 150);
		PanelJuego.add(lblJefe);
		
		Imagen = new ImageIcon(getClass().getClassLoader().getResource("caminataDer.png")).getImage();
		paraLabel = new ImageIcon(Imagen.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
		lblCuphead = new JLabel(paraLabel);
		lblCuphead.setBounds(0, 308, 90, 90);
		PanelJuego.add(lblCuphead);
		
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala1Jugador.png")).getImage();
		BalaCuphead = new ImageIcon(balaImagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		for (int i = 0; i < Balas1.length; i++)
		{
			Balas1[i] = new JLabel(BalaCuphead);
			Balas1[i].setBounds(lblCuphead.getX() + 90, lblCuphead.getY() + 30, 30, 30);
			Balas1[i].setVisible(false);
			PanelJuego.add(Balas1[i]);
		}
		
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala2Jugador.png")).getImage();
		BalaCuphead = new ImageIcon(balaImagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		for (int i = 0; i < Balas2.length; i++)
		{
			Balas2[i] = new JLabel(BalaCuphead);
			Balas2[i].setBounds(lblCuphead.getX() + 90, lblCuphead.getY() + 30, 30, 30);
			Balas2[i].setVisible(false);
			PanelJuego.add(Balas2[i]);
		}
		
		JLabel lblSuelo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("suelo.png")));
		lblSuelo.setBounds(0, 399, 605, 21);
		PanelJuego.add(lblSuelo);
		
		JPanel PanelEstado = new JPanel();
		PanelEstado.setOpaque(true);
		PanelEstado.setBackground(new Color(0, 102, 204));
		PanelEstado.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelEstado.setBounds(0, 0, 900, 30);
		frame.getContentPane().add(PanelEstado);
		PanelEstado.setLayout(null);
		
		lblNivel = new JLabel("Nivel: " + Nivel);
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblNivel.setBounds(10, 0, 106, 30);
		PanelEstado.add(lblNivel);
		
		lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblTiempo.setBounds(261, 0, 193, 30);
		PanelEstado.add(lblTiempo);
		
		lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblPuntaje.setBounds(689, 0, 193, 30);
		PanelEstado.add(lblPuntaje);
		
		PanelSalud = new JPanel();
		PanelSalud.setBackground(new Color(0, 102, 204));
		PanelSalud.setBounds(0, 448, 900, 27);
		frame.getContentPane().add(PanelSalud);
		PanelSalud.setLayout(null);
		
		lblVidaCuphead = new JLabel("HP: 100");
		lblVidaCuphead.setBounds(10, 0, 106, 21);
		lblVidaCuphead.setForeground(Color.WHITE);
		PanelSalud.add(lblVidaCuphead);
		lblVidaCuphead.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		
		lblVidaJefe = new JLabel("HP: " + 100 * Nivel);
		lblVidaJefe.setBounds(751, 5, 106, 21);
		lblVidaJefe.setForeground(Color.WHITE);
		PanelSalud.add(lblVidaJefe);
		lblVidaJefe.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
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
		if (arg0.getKeyCode() == KeyEvent.VK_J)
		{
			hiloMovBalas = new MovimientoBalas(Balas1, contador_Balas1, lblCuphead, lblCuphead.getY(), lblJefe, VidaJefe, lblVidaJefe);
			threadMovBalas = new Thread(hiloMovBalas);
			if (contador_Balas1 < 49)
			{
				contador_Balas1++;
				threadMovBalas.start();
			}
			else
			{
				frame.remove(PanelJuego);
				frame.revalidate();
				frame.repaint();
				Balas1 = rellenarBalas();
				for (int i = 0; i < Balas1.length; i++)
				{
					PanelJuego.add(Balas1[i]);
				}
				frame.getContentPane().add(PanelJuego);
				PanelJuego.revalidate();
				PanelJuego.repaint();
				PanelJuego.updateUI();
				contador_Balas1 = 0;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_K)
		{
			hiloMovBalas = new MovimientoBalas(Balas2, contador_Balas2, lblCuphead, lblCuphead.getY(), lblJefe, VidaJefe, lblVidaJefe);
			threadMovBalas = new Thread(hiloMovBalas);
			if (contador_Balas2 < 49)
			{
				contador_Balas2++;
				threadMovBalas.start();
			}
			else
			{
				frame.remove(PanelJuego);
				frame.revalidate();
				frame.repaint();
				Balas2 = rellenarBalas2();
				for (int i = 0; i < Balas2.length; i++)
				{
					PanelJuego.add(Balas2[i]);
				}
				frame.getContentPane().add(PanelJuego);
				PanelJuego.revalidate();
				PanelJuego.repaint();
				PanelJuego.updateUI();
				contador_Balas2 = 0;
			}
		}
	}
	
	private JLabel[] rellenarBalas2()
	{
		// TODO Auto-generated method stub
		JLabel[] Recarga2 = new JLabel[50];
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala2Jugador.png")).getImage();
		BalaCuphead = new ImageIcon(balaImagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		for (int i = 0; i < Balas2.length; i++)
		{
			Recarga2[i] = new JLabel(BalaCuphead);
			Recarga2[i].setBounds(lblCuphead.getX() + 90, lblCuphead.getY() + 30, 30, 30);
			Recarga2[i].setVisible(false);
			PanelJuego.add(Recarga2[i]);
		}
		return Recarga2;
	}
	
	private JLabel[] rellenarBalas()
	{
		// TODO Auto-generated method stub
		JLabel[] Recarga = new JLabel[50];
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala1Jugador.png")).getImage();
		BalaCuphead = new ImageIcon(balaImagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		for (int i = 0; i < Balas1.length; i++)
		{
			Recarga[i] = new JLabel(BalaCuphead);
			Recarga[i].setBounds(lblCuphead.getX() + 90, lblCuphead.getY() + 30, 30, 30);
			Recarga[i].setVisible(false);
			PanelJuego.add(Recarga[i]);
		}
		return Recarga;
	}
	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
