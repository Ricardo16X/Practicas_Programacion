package practica2;

public class DisparoCup {
	private String velocidad, nombre;
	private double  danio;
	
	public DisparoCup(String Nombre, String Velocidad, double Danio){
		nombre = Nombre;
		velocidad = Velocidad;
		danio = Danio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public double getDanio() {
		return danio;
	}

	public void setDanio(double danio) {
		this.danio = danio;
	}
	
}
