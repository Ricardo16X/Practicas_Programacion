package practica2;

public class DisparoCup
{
	private String velocidad, nombre;
	private double danio;
	private int velocidadDisparo;
	
	public DisparoCup(String Nombre, String Velocidad, double Danio, int VelocidadDisparo)
	{
		nombre = Nombre;
		velocidad = Velocidad;
		danio = Danio;
		velocidadDisparo = VelocidadDisparo;
	}
	
	public int getVelocidadDisparo()
	{
		return velocidadDisparo;
	}
	
	public void setVelocidadDisparo(int velocidadDisparo)
	{
		this.velocidadDisparo = velocidadDisparo;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getVelocidad()
	{
		return velocidad;
	}
	
	public void setVelocidad(String velocidad)
	{
		this.velocidad = velocidad;
	}
	
	public double getDanio()
	{
		return danio;
	}
	
	public void setDanio(double danio)
	{
		this.danio = danio;
	}
	
}
