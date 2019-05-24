package com.dgha.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dgha.dao.impl.LibroDaoImpl;
import com.dgha.entidad.Libro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LibroDaoTest {

	private LibroDao dao;
	private Gson gson;

	@Before
	public void setup() {
		dao = new LibroDaoImpl();
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Ignore
	@Test
	public void listarLibros() {
		List<Libro> libros = new ArrayList<>();
		libros = dao.listarLibros();
		System.out.println("LibroDaoTest.listarLibros(): libros=" + gson.toJson(libros));
		Assert.assertFalse(libros.isEmpty());
	}

	@Ignore
	@Test
	public void registrarLibro() {
		Libro libro = new Libro("CLAS0015", "Física Universitaria 4", 2011, "Juan Pérez", "3215885454", 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "BRUÑO", "1");
		dao.registrarLibro(libro);
		Assert.assertTrue(libro.getId() > 0);
	}

	@Ignore
	@Test
	public void modificarLibro() {
		try {
			Libro libro = new Libro(48, "CLAS0015", "Física Universitaria 5", 2011, "Juan Pérez", "3215885454", 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "BRUÑO", "1");
			System.out.println("LibroDaoTest.modificarLibro(): libro="+gson.toJson(libro));
			dao.modificarLibro(libro);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void eliminarLibro() {
		try {
			Libro libro = new Libro();
			libro.setId(6);
			dao.eliminarLibro(libro);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
