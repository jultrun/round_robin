package proceso;

import java.awt.Color;

public class Proceso {
	private String nombre;
	private int tiempoDeLlegada;
	private int tiempoDeEjecucion;
	private int tiempoRequerido;
	private int tiempoEjecutado;
	private int tiempoEjecutadoPorQuatum;
	private int timempoDeInicio;
	private int timempoFinal;
	private boolean ejecucion;
	private Color color;
	
	public Proceso(String nombre, int tiempoDeLlegada, int tiempoDeEjecucion,Color color) {
		this.nombre = nombre;
		this.tiempoDeLlegada = tiempoDeLlegada;
		this.tiempoDeEjecucion = tiempoDeEjecucion;
		this.tiempoRequerido = 0;
		this.tiempoEjecutado = 0;
		this.tiempoEjecutadoPorQuatum = 0;
		this.color=color;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTiempoDeLlegada() {
		return tiempoDeLlegada;
	}
	public void setTiempoDeLlegada(int tiempoDeLlegada) {
		this.tiempoDeLlegada = tiempoDeLlegada;
	}
	public int getTiempoDeEjecucion() {
		return tiempoDeEjecucion;
	}
	public void setTiempoDeEjecucion(int tiempoDeEjecucion) {
		this.tiempoDeEjecucion = tiempoDeEjecucion;
	}
	public int getTiempoRequetido() {
		return tiempoRequerido;
	}
	public void setTiempoRequetido(int tiempoDeEspera) {
		this.tiempoRequerido = tiempoDeEspera;
	}
	public int getTiempoEjecutado() {
		return tiempoEjecutado;
	}
	public void setTiempoEjecutado(int tiempo) {
		this.tiempoEjecutado = tiempo;
	}
	public boolean isEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}
	public int getTiempoEjecutadoPorQuatum() {
		return tiempoEjecutadoPorQuatum;
	}
	public void setTiempoEjecutadoPorQuatum(int tiempoEjecutadoPorQuatum) {
		this.tiempoEjecutadoPorQuatum = tiempoEjecutadoPorQuatum;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public int getTimempoDeInicio() {
		return timempoDeInicio;
	}
	public void setTimempoDeInicio(int timempoDeInicio) {
		this.timempoDeInicio = timempoDeInicio;
	}
	public int getTimempoFinal() {
		return timempoFinal;
	}
	public void setTimempoFinal(int timempoFinal) {
		this.timempoFinal = timempoFinal;
	}
		

}
