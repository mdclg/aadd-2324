package journals.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;


public class Editorial implements Serializable, Identificable{

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID) 
    private String id;
    private String nombre; 
	@BsonProperty(value="fecha_fundacion")
    private LocalDate fechaFundacion; 
	@BsonProperty(value="editor_jefe")
    private String editorJefe;
	@BsonProperty(value="paises_publicacion")
    private List<String> paisesPublicacion; 
	
    private Direccion sede;
    
    public Editorial() {
    	
    }
    
    
    public Editorial(String nombre, LocalDate fechaFundacion, String editorJefe,
			List<String> paisesPublicacion, Direccion sede) {
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.editorJefe = editorJefe;
		this.paisesPublicacion = paisesPublicacion;
		this.sede = sede;
	}
    
    


	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}





	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}





	public String getEditorJefe() {
		return editorJefe;
	}





	public void setEditorJefe(String editorJefe) {
		this.editorJefe = editorJefe;
	}





	public List<String> getPaisesPublicacion() {
		return paisesPublicacion;
	}





	public void setPaisesPublicacion(List<String> paisesPublicacion) {
		this.paisesPublicacion = paisesPublicacion;
	}





	public Direccion getSede() {
		return sede;
	}





	public void setSede(Direccion sede) {
		this.sede = sede;
	}





	@Override
	public String toString() {
		return "Editorial [ID=" + id + ", nombre=" + nombre + "]";
	}
    

}
