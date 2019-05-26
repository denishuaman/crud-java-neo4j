package com.dgha.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.impl.CursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.util.UtilConexion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CursoDaoTest {

	private CursoDao dao;
	private Gson gson;
	private UtilConexion conexion;
	private final Logger log = Logger.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {
		dao = new CursoDaoImpl();
		gson = new GsonBuilder().setPrettyPrinting().create();
		conexion = UtilConexion.getInstancia();
	}

	@Ignore
	@Test
	public void listarCursos() {
		List<Curso> cursos = null;
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursos = dao.listarCursos(transaccion);
			Assert.assertFalse(cursos.isEmpty());
			log.info(gson.toJson(cursos));
		} catch (Exception e) {
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

	@Ignore
	@Test
	public void registrarCurso() {
		Curso curso = new Curso();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			curso.setCodigoCurso("20190525001");
			curso.setNombreCurso("Algorítmica I");
			curso.setEstadoCurso("1");
			dao.registrarCurso(curso, transaccion);
			transaccion.success();
			Assert.assertTrue(curso.getId() > 0);
		} catch (Exception e) {
			transaccion.failure();
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

	@Ignore
	@Test
	public void modificarCurso() {
		Curso curso = new Curso();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			curso.setId(7);
			curso.setCodigoCurso("20190525002");
			curso.setNombreCurso("Algorítmica I");
			curso.setEstadoCurso("1");
			dao.modificarCurso(curso, transaccion);
			transaccion.success();
		} catch (Exception e) {
			transaccion.failure();
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

	@Ignore
	@Test
	public void eliminarCurso() {
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			Curso curso = new Curso();
			curso.setId(7);
			dao.eliminarCurso(curso, transaccion);
			transaccion.success();
		} catch (Exception e) {
			transaccion.failure();
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

}
