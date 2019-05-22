package com.dgha.dao;

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
		curso.setCodigoCurso("201905223");
		curso.setNombreCurso("Algorítmica III");
		curso.setEstadoCurso("1");
		dao.registrarCurso(curso);
	}

	@Ignore
	@Test
	public void modificarCurso() {
		Curso curso = new Curso();
		curso.setId(21);
		curso.setCodigoCurso("201905224");
		curso.setNombreCurso("Ecuaciones Diferenciales");
		curso.setEstadoCurso("1");
		Assert.assertEquals(true, dao.modificarCurso(curso));
	}

	@Ignore
	@Test
	public void eliminarCurso() {
		Curso curso = new Curso();
		curso.setId(21);
		Assert.assertEquals(true, dao.eliminarCurso(curso));
	}

}
