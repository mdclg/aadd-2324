package editorial.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import editorial.dao.LibroDAO;
import editorial.modelo.Libro;

@FacesConverter(forClass=Libro.class, managed=true)
public class LibroConversor  implements Converter<Libro>{

	@Override
	public Libro getAsObject(FacesContext context, UIComponent component, String value) {

		if(value == null || value.isEmpty()) {
			return null;
		}
		try {
			return LibroDAO.getLibroDAO().findById(Integer.parseInt(value));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			throw new ConverterException();
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Libro value) {
		if(value == null) {
			return "";
		}
		if(value.getId() != null) {
			return value.getId().toString();
		}
		else {
			throw new ConverterException();
		}
	}

}
