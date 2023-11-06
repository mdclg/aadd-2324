package journals.servicio.test;

import java.util.Arrays;
import java.util.List;

import journals.modelo.Editorial;
import journals.repositorio.RepositorioEditorialAdHoc;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ProgramaConsultasMongoDB {

	public static void main(String[] args) throws RepositorioException {


		RepositorioEditorialAdHoc repositorioConcreto = FactoriaRepositorios.getRepositorio(Editorial.class);
		
		List<Editorial> resultados = repositorioConcreto.getEditorialByPaises(Arrays.asList("Canada", "Alemania"));
		System.out.println(resultados);
		
		List<Editorial> resultadosYears = repositorioConcreto.getEditorialByYears(10);
		System.out.println(resultadosYears);
		
		System.out.println(repositorioConcreto.getEditorialSinDireccion());
		
		repositorioConcreto.getEstadisticasPorPais().forEach(doc -> System.out.println(doc.toJson()));

	}

}
