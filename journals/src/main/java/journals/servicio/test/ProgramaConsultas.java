package journals.servicio.test;

import java.util.Arrays;

import journals.modelo.Revista;
import journals.repositorio.RepositorioJournalAdHoc;
import repositorio.FactoriaRepositorios;

public class ProgramaConsultas {

	public static void main(String[] args)  throws Exception {


		RepositorioJournalAdHoc repositorioConcreto = FactoriaRepositorios.getRepositorio(Revista.class);
		

		System.out.println(repositorioConcreto.getByNombre("Natur"));
		
		System.out.println(repositorioConcreto.getPublishedByTema(Arrays.asList(1,2)));
		
		System.out.println(repositorioConcreto.getByAceptaEnvios());

	}

}
