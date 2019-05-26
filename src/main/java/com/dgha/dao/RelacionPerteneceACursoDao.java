package com.dgha.dao;

import org.neo4j.driver.v1.Transaction;

import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;

public interface RelacionPerteneceACursoDao {

	public void relacionar(Libro libro, Curso curso, RelacionPerteneceACurso relacionPerteneceACurso, Transaction transaccion) throws Exception;

	public void eliminarRelacion(Libro libro, Curso curso, Transaction transaccion) throws Exception;
}
