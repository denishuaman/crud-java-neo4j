package com.dgha.dao;

import java.util.List;

import org.neo4j.driver.v1.Transaction;

import com.dgha.entidad.Libro;

public interface LibroDao {

	public List<Libro> listarLibros(Transaction transaccion) throws Exception;

	public void registrarLibro(Libro libro, Transaction transaccion) throws Exception;

	public void modificarLibro(Libro libro, Transaction transaccion) throws Exception;

	public void eliminarLibro(Libro libro, Transaction transaccion) throws Exception;
}
