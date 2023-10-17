package journals.modelo;

public class Autor {

	private Integer id; 
    private String nombre;
    private String apellidos;
    private String email;
	private boolean corresponding;
	
	private Edicion edicion;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isCorresponding() {
		return corresponding;
	}
	public void setCorresponding(boolean corresponding) {
		this.corresponding = corresponding;
	}
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
    
    
}
