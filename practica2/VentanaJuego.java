package practica2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VentanaJuego implements KeyListener
{
	// Instancia de CargaDatos para obtener la vida de los jefes a jugar
	CargaDatos Jefes = new CargaDatos();
	// Instancia lista de marcadores cuando mueran todos los jefes o muera cuphead
	Marcadores marca = new Marcadores();
	// Diseño de Nivel
	JFrame frame;
	// JLabel de los personajes y su estatus de vida.
	JLabel lblNivel, lblTiempo, lblPuntaje, lblCuphead, lblJefe, lblSegundero;
	JLabel lblVidaCuphead, lblVidaJefe;
	// Ventana dónde se desarrollará el juego.
	private JPanel PanelSalud;
	JPanel PanelJuego;
	// Hilos a implementar
	HiloReloj hiloReloj;
	HiloMovimientoJefe hiloMovJefe;
	HiloSalto hiloSalto;
	HiloImagenCuphead hiloMovCup;
	HiloMovimientoCuphead hiloMoveCup;
	MovimientoBalas hiloMovBalas;
	HiloDisparosJefe hiloJefeDisparos;
	// Los objetos Thread a los cuales les pasaré la clase que que implementa la interfaz Runnable
	Thread threadReloj, threadMovJefe, threadMovCup, threadSalto, threadMoveCup,
			threadMovBalas, threadDisparosJefe;
	// Imagenes en Label
	Image Imagen;
	ImageIcon paraLabel;
	// Enteros para verificar la vida de Jefe y Cuphead además de iniciarlizarlos en 100 por ser el primer nivel...
	public static int VidaJefe = 100;
	public static int VidaCuphead = 100;
	public static int FrecuenciaDisparoJefe = 1000;
	static int Puntaje = 0;
	static int RapidezMovimientoJefe = 50;
	
	static int contadorSegundos = 1;
	// Entero que irá cambiando conforme se avance de Nivel y se comparará con contador de Jefes para saber si se ha llegado al último Nivel
	static int Nivel = 1;
	// 2 bala distinta para el jugador Cuphead que aparecerán dependiendo si es Disparo 1 o Disparo 2
	JLabel Balas1[] = new JLabel[50];
	JLabel Balas2[] = new JLabel[50];
	// 1 Tipo de bala para Jefe
	JLabel BalasJefe[] = new JLabel[100];
	// Contadores de balas para cuando el array se acabe, hacer el efecto de recarga rellenando nuevamente los arrays
	int contador_Balas1 = 0, contador_Balas2 = 0, contador_BalasJefe = 0;
	// Imagenes de las balas y su respectiva conversión para que se vean de manera correcta.
	private Image balaImagen;
	private ImageIcon BalaCuphead;
	private ImageIcon BalaJefe;
	// Timer que me ayuda a que el jefe dispare, ya que tuve inconvenientes con crear un hilo distinto que se ejecutara sin dormir el procesador cada X segundos
	Timer pausador = new Timer();
	TimerTask tareaPausada;
	Timer puntaje = new Timer();
	TimerTask puntajeTiempo;
	// Enteros finales que determinarán la velocidad de las balas
	static final int Rapido = 10, Medio = 50, Lento = 100;
	// Sólo para veficiar que el aviso del útimo nivel se muestre sólo 1 vez
	private boolean Aviso;
	
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
		hiloReloj = new HiloReloj(lblTiempo, lblSegundero);
		hiloMovJefe = new HiloMovimientoJefe(lblJefe);
		hiloSalto = new HiloSalto(lblCuphead);
		hiloMovCup = new HiloImagenCuphead(frame, lblCuphead);
		hiloMoveCup = new HiloMovimientoCuphead(frame, lblCuphead);
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
				if (arg0.getKeyCode() == ConfigControl.control[0].getTeclaSalto())
				{
					threadSalto = new Thread(hiloSalto);
					threadSalto.start();
				}
			}
		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 500);
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("iconoVentana.jpg")).getImage());
		frame.setTitle("Mini Cuphead - Práctica 2 IPC1");
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
		
		// Creación de Balas Iniciales
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
		
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala_jefe2.png")).getImage();
		BalaJefe = new ImageIcon(balaImagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		for (int i = 0; i < BalasJefe.length; i++)
		{
			BalasJefe[i] = new JLabel(BalaJefe);
			BalasJefe[i].setBounds(lblJefe.getX(), lblJefe.getY() + 35, 70, 70);
			BalasJefe[i].setVisible(false);
			PanelJuego.add(BalasJefe[i]);
		}
		// Fin Creación de Balas Iniciales
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
		lblTiempo.setBounds(126, 0, 193, 30);
		PanelEstado.add(lblTiempo);
		
		lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblPuntaje.setBounds(329, 0, 193, 30);
		PanelEstado.add(lblPuntaje);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(532, 5, 358, 21);
		PanelEstado.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		
		lblSegundero = new JLabel("1");
		lblSegundero.setForeground(Color.WHITE);
		lblSegundero.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblSegundero.setBounds(202, 0, 117, 30);
		lblSegundero.setVisible(false);
		PanelEstado.add(lblSegundero);
		
		PanelSalud = new JPanel();
		PanelSalud.setBackground(new Color(0, 102, 204));
		PanelSalud.setBounds(0, 448, 900, 27);
		frame.getContentPane().add(PanelSalud);
		PanelSalud.setLayout(null);
		
		lblVidaCuphead = new JLabel("HP: 100");
		lblVidaCuphead.setBounds(10, 5, 106, 21);
		lblVidaCuphead.setForeground(Color.WHITE);
		PanelSalud.add(lblVidaCuphead);
		lblVidaCuphead.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		
		lblVidaJefe = new JLabel("HP: " + 100 * Nivel);
		lblVidaJefe.setBounds(738, 5, 152, 21);
		lblVidaJefe.setForeground(Color.WHITE);
		PanelSalud.add(lblVidaJefe);
		lblVidaJefe.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		
		tareaPausada = new TimerTask()
		{
			@Override
			public void run()
			{
				if (VidaJefe > 0)
				{
					hiloJefeDisparos = new HiloDisparosJefe(lblJefe, VidaCuphead, BalasJefe, contador_BalasJefe, lblCuphead, lblVidaCuphead);
					threadDisparosJefe = new Thread(hiloJefeDisparos);
					contadorSegundos = Integer.parseInt(lblSegundero.getText());
					Puntaje += VidaJefe / contadorSegundos;
					lblPuntaje.setText("Puntaje: " + Puntaje);
					if (contador_BalasJefe < 49)
					{
						contador_BalasJefe++;
						threadDisparosJefe.start();
					}
					else
					{
						frame.remove(PanelJuego);
						BalasJefe = rellenarBalasJefe();
						for (int i = 0; i < BalasJefe.length; i++)
						{
							PanelJuego.add(BalasJefe[i]);
						}
						frame.getContentPane().add(PanelJuego);
						PanelJuego.updateUI();
						contador_BalasJefe = 0;
					}
				}
				else
				{
					if (Nivel < CargaDatos.UltimoNivel)
					{
						// Mientras la vida del Jefe > 0 y el Nivel <= Ultimo Nivel se ejecutará este blque.
						Nivel++;
						VidaCuphead = 100;
						VidaJefe = 100 * Nivel;
						RapidezMovimientoJefe -= RapidezMovimientoJefe / CargaDatos.UltimoNivel;
						// Muestra actualizada del Estado del Juego
						lblNivel.setText("Nivel : " + Nivel);
						lblVidaJefe.setText("HP: " + (100 * Nivel));
						lblVidaCuphead.setText("HP: " + VidaCuphead);
						// Fondo Nuevo por cada Nivel Pasado
						PanelJuego.setBackground(new Color((int)(Math.random() * 250 + 1), (int)(Math.random() * 250 + 1), (int)(Math.random() * 250) + 1));
					}
					else
					{
						// Esto Indica que se ha sobrepasado el último Nivel por lo tanto el Juego Termina.
						marca.frame.setVisible(true);
						frame.setVisible(false);
						pausador.cancel();
					}
				}
				if (VidaCuphead <= 0)
				{
					marca.frame.setVisible(true);
					frame.setVisible(false);
					pausador.cancel();
				}
				if (Nivel == 4)
				{
					if (Aviso == false)
					{
						JOptionPane.showMessageDialog(null, "Ultimo Nivel!!");
						Aviso = true;
					}
				}
			}
		};
		pausador.schedule(tareaPausada, 1000, CargaDatos.Jefes[Nivel - 1].getFrecuencia() * 500);
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
		if (arg0.getKeyCode() == ConfigControl.control[0].getTeclaDisparo1())
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
		if (arg0.getKeyCode() == ConfigControl.control[0].getTeclaDisparo2())
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
	
	public JLabel[] rellenarBalasJefe()
	{
		// TODO Auto-generated method stub
		JLabel[] RecargaJefe = new JLabel[100];
		balaImagen = new ImageIcon(getClass().getClassLoader().getResource("bala_jefe2.png")).getImage();
		BalaJefe = new ImageIcon(balaImagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		for (int i = 0; i < BalasJefe.length; i++)
		{
			RecargaJefe[i] = new JLabel(BalaJefe);
			RecargaJefe[i].setBounds(lblJefe.getX(), lblJefe.getY() + 35, 70, 70);
			RecargaJefe[i].setVisible(false);
			PanelJuego.add(RecargaJefe[i]);
		}
		return RecargaJefe;
	}
	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
	}
}
