package util;

import java.awt.Color;

public class Registro {
	Color  color;
	int tiempo;
	public Registro(Color color,int tiempo) {
		this.color=color;
		this.tiempo=tiempo;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	

}
