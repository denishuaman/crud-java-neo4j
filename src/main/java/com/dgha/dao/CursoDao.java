package com.dgha.dao;

import java.util.List;

import com.dgha.entidad.Curso;

public interface CursoDao {

	public List<Curso> listarCursos();

	public void registrarCurso(Curso curso);

	public boolean modificarCurso(Curso curso);

	public boolean eliminarCurso(Curso curso);
}
