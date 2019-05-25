package com.dgha.dao;

import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;

public interface RelacionPerteneceACursoDao {

	public RelacionPerteneceACurso relacionar(Libro libro, Curso curso, String usuario);
}
