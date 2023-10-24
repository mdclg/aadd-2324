package editorial.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="distribuidor")
public class Distribuidor implements Serializable{
	
	@Id
	private String CIF;
	@Column(name="nombre")
	private String nombre;
	
	@ManyToMany(mappedBy = "distribuidores")
	private List<Editorial> editoriales;
	
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
