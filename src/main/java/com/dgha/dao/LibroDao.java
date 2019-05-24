package com.dgha.dao;

import java.util.List;

import com.dgha.entidad.Libro;

public interface LibroDao {

	public List<Libro> listarLibros();

	public void registrarLibro(Libro libro);

	public void modificarLibro(Libro libro);

	public void eliminarLibro(Libro libro);
}
