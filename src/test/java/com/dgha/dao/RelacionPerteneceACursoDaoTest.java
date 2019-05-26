package com.dgha.dao;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.impl.RelacionPerteneceACursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;
import com.dgha.util.UtilConexion;

public class RelacionPerteneceACursoDaoTest {

	private RelacionPerteneceACursoDao dao;
	private UtilConexion conexion;
	private final Logger log = Logger.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {
		dao = new RelacionPerteneceACursoDaoImpl();
		conexion = UtilConexion.getInstancia();
	}

	@Ignore
	@Test
	public void relacionar() {
		Libro libro = new Libro();
		libro.setId(24);
		Curso curso = new Curso();
		curso.setId(1);
		RelacionPerteneceACurso relacionPerteneceACurso = new RelacionPerteneceACurso();
		relacionPerteneceACurso.setUsuarioCreacion("usuario.principal@email.com");
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			dao.relacionar(libro, curso, relacionPerteneceACurso, transaccion);
			transaccion.success();
			Assert.assertTrue(relacionPerteneceACurso.getId() > 0);
		} catch (Exception e) {
			transaccion.failure();
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

//	@Ignore
	@Test
	public void eliminarRelacion() {
		Libro libro = new Libro();
		libro.setId(24);
		Curso curso = new Curso();
		curso.setId(9);
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			dao.eliminarRelacion(libro, curso, transaccion);
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
