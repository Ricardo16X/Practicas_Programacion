package practica2;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;

public class CargaDatos implements ActionListener
{
	
	public JFrame frame;
	private JPanel panelEscoger;
	private JButton btnExaminar, btnEstablecerDatos;
	private JTextField txtRutaAbsoluta;
	private JButton btnLeerArchivo;
	private File fichero;
	private JTextArea textArea;
	private String cadena, cadenaModificada;
	
	static Jefe Jefes[] = new Jefe[25];
	static DisparoCup tipoDisparo[] = new DisparoCup[25];
	
	int contadorArchivo = 0;
	static int contJefe = 0;
	static int contDisparo = 0;
	
	//	Entero para controlar el último nivel.
	static int UltimoNivel = contJefe;
	
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
					CargaDatos window = new CargaDatos();
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
	public CargaDatos()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		FondoInicio fd = new FondoInicio();
		fd.setBounds(0, 0, 482, 373);
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				Menu ini = new Menu();
				ini.frmInicio.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		panelEscoger = new JPanel();
		panelEscoger.setLayout(null);
		frame.setContentPane(panelEscoger);
		
		frame.setBounds(100, 100, 490, 400);
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("iconoVentana.jpg")).getImage());
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Carga de Datos");
		txtRutaAbsoluta = new JTextField();
		txtRutaAbsoluta.setBounds(10, 83, 462, 20);
		frame.getContentPane().add(txtRutaAbsoluta);
		txtRutaAbsoluta.setColumns(10);
		fd.setLayout(null);
		
		JLabel lblCargaDeDatos = new JLabel("Carga de Datos");
		lblCargaDeDatos.setBounds(177, 0, 172, 34);
		fd.add(lblCargaDeDatos);
		lblCargaDeDatos.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		
		btnLeerArchivo = new JButton("Leer Archivo");
		btnLeerArchivo.setBounds(20, 339, 119, 23);
		btnLeerArchivo.setEnabled(false);
		btnLeerArchivo.addActionListener(this);
		fd.add(btnLeerArchivo);
		
		btnExaminar = new JButton("Examinar");
		btnExaminar.setBounds(210, 114, 91, 23);
		fd.add(btnExaminar);
		btnExaminar.addActionListener(this);
		
		btnEstablecerDatos = new JButton("Establecer Datos");
		btnEstablecerDatos.setBounds(318, 339, 154, 23);
		btnEstablecerDatos.setEnabled(false);
		btnEstablecerDatos.addActionListener(this);
		fd.add(btnEstablecerDatos);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 150, 462, 178);
		fd.add(textArea);
		
		frame.getContentPane().add(fd);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.btnExaminar)
		{
			JFileChooser archivo = new JFileChooser();
			archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CUPHEAD", "cuphead");
			archivo.setFileFilter(filtro);
			
			int seleccion = archivo.showOpenDialog(panelEscoger);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
				fichero = archivo.getSelectedFile();
				txtRutaAbsoluta.setText(fichero.getAbsolutePath());
				this.btnLeerArchivo.setEnabled(true);
			}
		}
		else if (arg0.getSource() == this.btnLeerArchivo)
		{
			try (FileReader leer = new FileReader(fichero))
			{
				cadena = "";
				int valor = leer.read();
				while (valor != -1)
				{
					cadena += (char)valor;
					valor = leer.read();
				}
				textArea.setText(cadena);
				textArea.setEditable(false);
				this.btnEstablecerDatos.setEnabled(true);
				leer.close();
			}
			catch (Exception error)
			{
				JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
			}
		}
		else if (arg0.getSource() == this.btnEstablecerDatos)
		{
			// Separar los datos cada @
			String[] cadenaSeparada = cadena.split("\n");
			cadenaModificada = "";
			for (int j = 0; j < cadenaSeparada.length; j++)
			{
				cadenaModificada += cadenaSeparada[j] + "@@";
			}
			String[] cadenaSeparada2 = cadenaModificada.split("@@");
			
			// Identificar a JEFE o CUPHEAD
			try
			{
				while (contadorArchivo < cadenaSeparada2.length)
				{
					if (cadenaSeparada2[contadorArchivo].equalsIgnoreCase("JEFE"))
					{
						Jefes[contJefe] = new Jefe(cadenaSeparada2[contadorArchivo + 1], Integer.parseInt(cadenaSeparada2[contadorArchivo + 2]), Integer.parseInt(cadenaSeparada2[contadorArchivo + 3]), (cadenaSeparada2[contadorArchivo + 4]), Double.parseDouble(cadenaSeparada2[contadorArchivo + 5]), cadenaSeparada2[contadorArchivo + 6]);
						contadorArchivo += 7;
						contJefe += 1;
					}
					else if (cadenaSeparada2[contadorArchivo].equalsIgnoreCase("CUPHEAD"))
					{
						
						tipoDisparo[contDisparo] = new DisparoCup(cadenaSeparada2[contadorArchivo + 1], (cadenaSeparada2[contadorArchivo + 2]), Double.parseDouble(cadenaSeparada2[contadorArchivo + 3]));
						contadorArchivo += 4;
						contDisparo += 1;
					}
				}
				JOptionPane.showMessageDialog(null, "Datos cargados correctamente!");
				btnLeerArchivo.setEnabled(false);
				btnEstablecerDatos.setEnabled(false);
				// Limpiando componentes.
				txtRutaAbsoluta.setText(null);
				textArea.setText(null);
				txtRutaAbsoluta.requestFocus();
				Menu.datos = true;
			}
			catch (Exception error)
			{
				JOptionPane.showMessageDialog(null, "Verifica que el archivo .cuphead tenga el formato correcto\n" + error.getMessage());
			}
		}
	}
}
