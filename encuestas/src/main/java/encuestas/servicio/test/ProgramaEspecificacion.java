package encuestas.servicio.test;

import encuestas.modelo.Encuesta;
import encuestas.reglas.ReglasEncuesta;
import encuestas.repositorio.RepositorioEncuestasAdHoc;
import especificacion.Especificacion;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;

public class ProgramaEspecificacion {

	public static void main(String[] args) throws Exception {
		
		RepositorioEncuestasAdHoc repositorioConcreto = FactoriaRepositorios.getRepositorio(Encuesta.class);

		System.out.println(repositorioConcreto.getByActivas());
	}
}
