package journals.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import journals.modelo.Revista;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

public interface RepositorioJournalAdHoc extends RepositorioString<Revista>{
	
	public default	List<Revista> getByNombre(String keyword) throws RepositorioException{
		
		return getAll().stream().filter(revista -> revista.getNombre().contains(keyword)).collect(Collectors.toList());
	}
	
	public List<Revista> getPublishedByTema(List<Integer> temas) throws RepositorioException;
	
	public List<Revista> getByAceptaEnvios() throws RepositorioException;
}
