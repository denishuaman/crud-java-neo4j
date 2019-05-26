package com.dgha.services;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.CursoDao;
import com.dgha.dao.LibroDao;
import com.dgha.dao.RelacionPerteneceACursoDao;
import com.dgha.dao.impl.CursoDaoImpl;
import com.dgha.dao.impl.LibroDaoImpl;
import com.dgha.dao.impl.RelacionPerteneceACursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;
import com.dgha.entidad.ResponseGeneral;
import com.dgha.util.Constantes;
import com.dgha.util.UtilConexion;

public class SRelPerteneceACurso {

	private static final Logger log = Logger.getLogger(SRelPerteneceACurso.class);
	private static SRelPerteneceACurso instancia;
	private RelacionPerteneceACursoDao perteneceACursoDao;
	private LibroDao libroDao;
	private CursoDao cursoDao;
	private UtilConexion conexion;

	public SRelPerteneceACurso() {
		log.info("Inicio del service SRelPerteneceACurso");
		conexion = UtilConexion.getInstancia();
		perteneceACursoDao = new RelacionPerteneceACursoDaoImpl();
		libroDao = new LibroDaoImpl();
		cursoDao = new CursoDaoImpl();
	}

	public static SRelPerteneceACurso getInstancia() {
		if (instancia == null) {
			log.info("Creando instancia de SRelPerteneceACurso");
			instancia = new SRelPerteneceACurso();
		}
		return instancia;
	}

	/**
	 * Método que registra el libro y el curso, luego relaciona ambos.
	 * 
	 * @param libro Libro nuevo a crear.
	 * @param curso Curso nuevo a crear.
	 * @return Response que contiene la respuesta del método.
	 */
	public ResponseGeneral relacionarLibroNuevoACursoNuevo(Libro libro, Curso curso, Driver driver) throws Exception {
		ResponseGeneral responseGeneral = null;
		if (libro != null && curso != null) {
			conexion.crearSesion(driver);
			Transaction transaccion = conexion.getSession().beginTransaction();
			try {
				libroDao.registrarLibro(libro, transaccion);
				cursoDao.registrarCurso(curso, transaccion);
				RelacionPerteneceACurso perteneceACurso = new RelacionPerteneceACurso();
				perteneceACurso.setUsuarioCreacion("usuario.principal@email.com");
				perteneceACursoDao.relacionar(libro, curso, perteneceACurso, transaccion);
				transaccion.success();
				responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se relacionó el libro al curso");
			} catch (Exception e) {
				transaccion.failure();
				responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "No se pudo relacionar el libro nuevo al curso nuevo");
				log.error("Error al relacionar el libro nuevo con el curso nunevo", e);
			} finally {
				conexion.limpiarRecursos(transaccion);
			}
		}
		return responseGeneral;
	}
}
