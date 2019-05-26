package com.dgha.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.impl.LibroDaoImpl;
import com.dgha.entidad.Libro;
import com.dgha.util.UtilConexion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LibroDaoTest {

	private LibroDao dao;
	private Gson gson;
	private UtilConexion conexion;
	private final Logger log = Logger.getLogger(this.getClass());

	@Before
	public void setup() {
		dao = new LibroDaoImpl();
		gson = new GsonBuilder().setPrettyPrinting().create();
		conexion = UtilConexion.getInstancia();
	}

	@Ignore
	@Test
	public void listarLibros() {
		List<Libro> libros = new ArrayList<>();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			libros = dao.listarLibros(transaccion);
			log.info("LibroDaoTest.listarLibros(): libros=" + gson.toJson(libros));
			Assert.assertFalse(libros.isEmpty());
		} catch (Exception e) {
			log.error("Error", e);
			fail(e.getMessage());
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
	}

	@Ignore
	@Test
	public void registrarLibro() {
		Transaction transaccion = conexion.getSession().beginTransaction();
		Libro libro = new Libro("CLAS009", "Fundamentos de programación", 2011, "Juan Pérez", "3215885454", 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "BRUÑO", "1");
		try {
			dao.registrarLibro(libro, transaccion);
			transaccion.success();
			Assert.assertTrue(libro.getId() > 0);
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
	public void modificarLibro() {
		Transaction transaccion = conexion.getSession().beginTransaction();
		Libro libro = new Libro(0, "CLAS010", "Fundamentos de programación I", 2011, "Juan Pérez", "3215885454", 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "BRUÑO", "1");
		try {
			log.info("LibroDaoTest.modificarLibro(): libro=" + gson.toJson(libro));
			dao.modificarLibro(libro, transaccion);
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
	public void eliminarLibro() {
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			Libro libro = new Libro();
			libro.setId(0);
			dao.eliminarLibro(libro, transaccion);
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
