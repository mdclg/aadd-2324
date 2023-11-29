package servicio;

@SuppressWarnings("serial")
public class ServicioException extends Exception {

	public ServicioException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public ServicioException(String msg) {
		super(msg);		
	}
	
		
}

