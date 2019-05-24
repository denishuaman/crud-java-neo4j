package com.dgha.dao;

import java.util.List;

import com.dgha.entidad.Curso;

public interface CursoDao {

	public List<Curso> listarCursos();

	public void registrarCurso(Curso curso);

	public void modificarCurso(Curso curso);

	public void eliminarCurso(Curso curso);
}
