package com.dgha.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dgha.dao.impl.CursoDaoImpl;
import com.dgha.entidad.Curso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CursoDaoTest {

	private CursoDao dao;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		dao = new CursoDaoImpl();
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Ignore
	@Test
	public void listarCursos() {
		List<Curso> cursos = dao.listarCursos();
		System.out.println(gson.toJson(cursos));
	}

	@Ignore
	@Test
	public void registrarCurso() {
		Curso curso = new Curso();
		try {
			curso.setCodigoCurso("20190524002");
			curso.setNombreCurso("Computación e Informática");
			curso.setEstadoCurso("1");
			dao.registrarCurso(curso);
			Assert.assertTrue(curso.getId() > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void modificarCurso() {
		Curso curso = new Curso();
		try {
			curso.setId(0);
			curso.setCodigoCurso("201905222");
			curso.setNombreCurso("Ecuaciones Diferenciales");
			curso.setEstadoCurso("1");
			dao.modificarCurso(curso);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void eliminarCurso() {
		try {
			Curso curso = new Curso();
			curso.setId(0);
			dao.eliminarCurso(curso);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
