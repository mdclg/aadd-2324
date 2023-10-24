package jpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import editorial.modelo.Editorial;
import editorial.modelo.Empleado;
import editorial.modelo.EmpleadoColaborador;
import editorial.modelo.EmpleadoPlantilla;
import utils.EntityManagerHelper;

public class ProgramaJPA {

	private static LocalDate parseDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate.parse(date, formatter);
		try {
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}

	}

	private static void crearEditorial() {

		Editorial e = new Editorial();
		e.setFechaFundacion(parseDate("01/11/2020"));
		e.setNombre("Duermevela");
		e.setGeneros(Arrays.asList("fantasía", "ciencia ficción"));

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			EntityManagerHelper.closeEntityManager();
		}

		System.out.println("Se ha creado la editorial con id: " + e.getId());

	}

	private static void crearDirector() {
		Empleado empleadoDirector = new Empleado();
		empleadoDirector.setNombre("Paolo");
		empleadoDirector.setApellidos("Lucas Lucas");
		empleadoDirector.setFechaNacimiento(parseDate("08/03/1980"));
		empleadoDirector.setNss("11111111111");
		// empleadoDirector.setSalario(38500.0);
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			Editorial editorial = em.find(Editorial.class, 1);
			empleadoDirector.setEditorial(editorial); // el empleado trabaja en la editorial
			em.persist(empleadoDirector);
			em.flush();
			editorial.setNombre("loquesea");
			editorial.setDirector(empleadoDirector);
			
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		EntityManagerHelper.closeEntityManager();

		System.out.println("Se ha creado el director con id: " + empleadoDirector.getId());

	}

	// Fetch eager y lazy
	private static void crearEmpleados() {

		Empleado empleado1 = new Empleado();
		empleado1.setNombre("Micaela");
		empleado1.setApellidos("Lax Lax");
		empleado1.setFechaNacimiento(parseDate("08/03/1980"));
		empleado1.setNss("11111111111");
		// empleado1.setSalario(38500.0);
		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();
			Editorial editorial = em.find(Editorial.class, 1);
			empleado1.setEditorial(editorial);
			em.persist(empleado1);
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		EntityManagerHelper.closeEntityManager();
		EntityManager em2 = EntityManagerHelper.getEntityManager();
		Editorial editorial = em2.find(Editorial.class, 1);
		System.out.println("editorial " + editorial.getNombre()); // punto interrpción
		System.out.println("n empleados " + editorial.getEmpleados().size());
		System.out.println("años " + editorial.getAnyos()); // punto interrupción
		System.out.println("Empleados de la editorial " + editorial.getEmpleados().size());

	}

	// Modificaciones en cascada
	private static void borrarEditorial() {

		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			Editorial editorial = em.find(Editorial.class, 1);
			em.remove(editorial);
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}

	}

	private static void cicloVidaEntidad() {

		Editorial e = new Editorial();
		e.setFechaFundacion(parseDate("20/05/2014"));
		e.setNombre("Hoja de Lata");
		e.setGeneros(Arrays.asList("narrativa", "no ficción"));

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(e);
			em.flush();
			if (em.contains(e)) {
				System.out.println("TRAS PERSIST: La editorial Hoja de Lata está Managed");
			} else {
				System.out.println("TRAS PERSIST: La editorial Hoja de Lata está Detached");
			}
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			EntityManagerHelper.closeEntityManager();
		}

		// Al cerrar el entityManager que gestionaba la entidad, se queda detached.
		// Aunque volvamos a abrir otro, la editorial seguirá detached

		EntityManager em2 = EntityManagerHelper.getEntityManager();
		if (em2.contains(e)) {
			System.out.println("TRAS ENTITYMANAGER CLOSE: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS ENTITYMANAGER CLOSE: La editorial Hoja de Lata está Detached");
		}
		e = em2.merge(e);

		if (em2.contains(e)) {
			System.out.println("TRAS MERGE: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS MERGE: La editorial Hoja de Lata está Detached");
		}

		// La vuelvo a cerrar

		EntityManagerHelper.closeEntityManager();

		EntityManager em3 = EntityManagerHelper.getEntityManager();

		if (em3.contains(e)) {
			System.out.println("TRAS ENTITYMANAGER CLOSE 2: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS ENTITYMANAGER CLOSE 2: La editorial Hoja de Lata está Detached");
		}
		e = em3.find(Editorial.class, e.getId());

		if (em3.contains(e)) {
			System.out.println("TRAS FIND: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS FIND: La editorial Hoja de Lata está Detached");
		}

	}

	private static void crearEmpleadosHerencia() {

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {

			em.getTransaction().begin();

			EmpleadoPlantilla ep = new EmpleadoPlantilla();

			ep.setNombre("MC");
			ep.setApellidos("LG");
			ep.setFechaNacimiento(parseDate("08/03/1980"));
			ep.setNss("111111123451");
			ep.setSalario(38500.0);

			Editorial editorial = em.find(Editorial.class, 1);
			ep.setEditorial(editorial);

			EmpleadoColaborador ec = new EmpleadoColaborador();
			ec.setNombre("Lola");
			ec.setApellidos("PQ");
			ec.setFechaNacimiento(parseDate("08/03/1980"));
			ec.setNss("11345255111");
			ec.setPrecioHora(30.0);
			ep.setEditorial(editorial);

			em.persist(ep);
			em.persist(ec);
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

		EntityManagerHelper.closeEntityManager();

	}

	private static void query() {
		try {

			String queryString = "  ";

			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);

			

			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			query.getResultList();

			

		} catch (RuntimeException re) {

			throw re;

		}
	}

	public static void main(String[] args) {
		//crearDirector();
		//crearEmpleados();
		cicloVidaEntidad();
	}

}
