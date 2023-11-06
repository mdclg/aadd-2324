package journals.modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Direccion {
	private String calle;
	private Integer numero;
	private String ciudad;
	@BsonProperty(value="codigo_postal")
	private String codigoPostal;

	public Direccion() {
		
	}
	
	public Direccion(String calle, Integer numero, String ciudad, String codigoPostal) {
		this.calle = calle;
		this.numero = numero;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
