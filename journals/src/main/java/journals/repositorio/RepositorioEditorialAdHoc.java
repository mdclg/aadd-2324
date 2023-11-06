package journals.repositorio;

import java.util.List;

import org.bson.Document;

import journals.modelo.Editorial;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

public interface RepositorioEditorialAdHoc extends RepositorioString<Editorial>{
	
	public List<Editorial> getEditorialByPaises(List<String> paises) throws RepositorioException;
	public List<Editorial> getEditorialByPaisesv2(List<String> paises) throws RepositorioException;
	
	public List<Editorial> getEditorialByYears(int year) throws RepositorioException;

	public List<Editorial> getEditorialSinDireccion() throws RepositorioException;

	public List<Document> getEstadisticasPorPais() throws RepositorioException;


}
