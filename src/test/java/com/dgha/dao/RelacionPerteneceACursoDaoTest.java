package com.dgha.dao;

import static org.junit.Assert.fail;

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
		libro.setId(200);
		Curso curso = new Curso();
		curso.setId(100);
		RelacionPerteneceACurso relacionPerteneceACurso = new RelacionPerteneceACurso();
		relacionPerteneceACurso.setUsuarioCreacion("usuario.principal@email.com");
		try {
			dao.relacionar(libro, curso, relacionPerteneceACurso);
			Assert.assertTrue(relacionPerteneceACurso.getId() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
