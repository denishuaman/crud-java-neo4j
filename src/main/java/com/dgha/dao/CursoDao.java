package com.dgha.dao;

import java.util.List;

import org.neo4j.driver.v1.Transaction;

import com.dgha.entidad.Curso;

public interface CursoDao {

	public List<Curso> listarCursos(Transaction transaccion) throws Exception;

	public void registrarCurso(Curso curso, Transaction transaccion) throws Exception;

	public void modificarCurso(Curso curso, Transaction transaccion) throws Exception;

	public void eliminarCurso(Curso curso, Transaction transaccion) throws Exception;
}
