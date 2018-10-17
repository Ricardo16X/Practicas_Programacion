package practica2;

public class Control
{
	private int teclaSalto, teclaAtras, teclaAdelante, teclaDisparo1,
			teclaDisparo2;
	private int disparo1, disparo2;
	
	public Control(int teclas, int teclas2, int teclas3, int teclas4, int teclas5, int dis1, int dis2)
	{
		teclaAdelante = teclas;
		teclaAtras = teclas2;
		teclaSalto = teclas3;
		teclaDisparo1 = teclas4;
		teclaDisparo2 = teclas5;
		disparo1 = dis1;
		disparo2 = dis2;
	}
	
	public int getTeclaSalto()
	{
		return teclaSalto;
	}
	
	public void setTeclaSalto(int teclaSalto)
	{
		this.teclaSalto = teclaSalto;
	}
	
	public int getTeclaAtras()
	{
		return teclaAtras;
	}
	
	public void setTeclaAtras(int teclaAtras)
	{
		this.teclaAtras = teclaAtras;
	}
	
	public int getTeclaAdelante()
	{
		return teclaAdelante;
	}
	
	public void setTeclaAdelante(int teclaAdelante)
	{
		this.teclaAdelante = teclaAdelante;
	}
	
	public int getTeclaDisparo1()
	{
		return teclaDisparo1;
	}
	
	public void setTeclaDisparo1(int teclaDisparo1)
	{
		this.teclaDisparo1 = teclaDisparo1;
	}
	
	public int getTeclaDisparo2()
	{
		return teclaDisparo2;
	}
	
	public void setTeclaDisparo2(int teclaDisparo2)
	{
		this.teclaDisparo2 = teclaDisparo2;
	}
	
	public int getDisparo1()
	{
		return disparo1;
	}
	
	public void setDisparo1(int disparo1)
	{
		this.disparo1 = disparo1;
	}
	
	public int getDisparo2()
	{
		return disparo2;
	}
	
	public void setDisparo2(int disparo2)
	{
		this.disparo2 = disparo2;
	}
	
}
