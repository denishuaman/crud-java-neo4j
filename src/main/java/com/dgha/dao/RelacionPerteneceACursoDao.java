package com.dgha.dao;

import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;

public interface RelacionPerteneceACursoDao {

	public void relacionar(Libro libro, Curso curso, RelacionPerteneceACurso relacionPerteneceACurso) throws Exception;

	public void eliminarRelacion(Libro libro, Curso curso) throws Exception;
}
