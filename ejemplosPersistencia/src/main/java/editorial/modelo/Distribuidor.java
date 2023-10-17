package editorial.modelo;

import java.io.Serializable;

public class Distribuidor implements Serializable{
	
	private String CIF;
	private String nombre;
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
