package com.dgha.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dgha.dao.impl.RelacionPerteneceACursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;

public class RelacionPerteneceACursoDaoTest {

	private RelacionPerteneceACursoDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new RelacionPerteneceACursoDaoImpl();
	}

	@Test
	public void relacionar() {
		Libro libro = new Libro();
		libro.setId(25);
		Curso curso = new Curso();
		curso.setId(9);
		RelacionPerteneceACurso relacionPerteneceACurso = dao.relacionar(libro, curso, "usuario.total@email.com");
		Assert.assertNotNull(relacionPerteneceACurso);
	}

}
