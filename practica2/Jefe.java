package practica2;

public class Jefe {
	private String nombre,velocidad, ruta;
	private int nivel, frecuencia;
	private double  danio;

	public Jefe(String Nombre, int Nivel, int Frecuencia, String Velocidad, double Danio, String Ruta) {
		nombre = Nombre;
		nivel = Nivel;
		frecuencia = Frecuencia;
		velocidad = Velocidad;
		danio = Danio;
		ruta = Ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
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
