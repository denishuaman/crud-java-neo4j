package com.dgha.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import com.dgha.dao.LibroDao;
import com.dgha.entidad.Libro;

public class LibroDaoImpl implements LibroDao {

	@Override
	public List<Libro> listarLibros(Transaction transaccion) throws Exception {
		List<Libro> libros = new ArrayList<>();
		String query = "match (l: Libro) \n" + 
				"return ID(l) as id, \n" + 
				"l.clasificacion as clasificacion, \n" + 
				"l.titulo as titulo, \n" + 
				"l.anio as anio, \n" + 
				"l.autor as autor, \n" + 
				"l.codBarras as codBarras, \n" + 
				"l.codEditorial as codEditorial, \n" + 
				"l.numeroCalificaciones as numeroCalificaciones, \n" + 
				"l.ultCalifEntendible as ultCalifEntendible, \n" + 
				"l.califAcumEntendible as califAcumEntendible, \n" + 
				"l.califEntendible as califEntendible, \n" + 
				"l.ultCalifActualizado as ultCalifActualizado, \n" + 
				"l.califAcumActualizado as califAcumActualizado, \n" + 
				"l.califActualizado as califActualizado, \n" + 
				"l.ultCalifTeoria as ultCalifTeoria, \n" + 
				"l.califAcumTeoria as califAcumTeoria, \n" + 
				"l.califTeoria as califTeoria, \n" + 
				"l.ultCalifPractica as ultCalifPractica, \n" + 
				"l.califAcumPractica as califAcumPractica, \n" + 
				"l.califPractica as califPractica,\n" + 
				"l.ultCalifComentario as ultCalifComentario,\n" + 
				"l.califAcumComentario as califAcumComentario,\n" + 
				"l.califComentario as califComentario,\n" + 
				"l.calificacionPromedio as calificacionPromedio,\n" + 
				"l.numPedidos as numPedidos,\n" + 
				"l.estadoLibro as estadoLibro\n";
		try {
			StatementResult statementResult = transaccion.run(query);
			while (statementResult.hasNext()) {
				Record record = statementResult.next();
				Libro libro = new Libro();
				libro.setId(record.get("id").asInt());
				libro.setClasificacion(record.get("clasificacion").asString());
				libro.setTitulo(record.get("titulo").asString());
				libro.setAnio(record.get("anio").asInt());
				libro.setAutor(record.get("autor").asString());
				libro.setCodBarras(record.get("codBarras").asString());
				libro.setCodEditorial(record.get("codEditorial").asString());
				libro.setNumeroCalificaciones(record.get("numeroCalificaciones").asInt());
				libro.setUltCalifEntendible(record.get("ultCalifEntendible").asInt());
				libro.setCalifAcumEntendible(record.get("califAcumEntendible").asInt());
				libro.setCalifEntendible(record.get("califEntendible").asFloat());
				libro.setUltCalifActualizado(record.get("ultCalifActualizado").asInt());
				libro.setCalifAcumActualizado(record.get("califAcumActualizado").asInt());
				libro.setCalifActualizado(record.get("califActualizado").asFloat());
				libro.setUltCalifTeoria(record.get("ultCalifTeoria").asInt());
				libro.setCalifAcumTeoria(record.get("califAcumTeoria").asInt());
				libro.setCalifTeoria(record.get("califTeoria").asFloat());
				libro.setUltCalifPractica(record.get("ultCalifPractica").asInt());
				libro.setCalifAcumPractica(record.get("califAcumPractica").asInt());
				libro.setCalifPractica(record.get("califPractica").asFloat());
				libro.setUltCalifComentario(record.get("ultCalifComentario").asInt());
				libro.setCalifAcumComentario(record.get("califAcumComentario").asInt());
				libro.setCalifComentario(record.get("califComentario").asFloat());
				libro.setCalificacionPromedio(record.get("calificacionPromedio").asFloat());
				libro.setNumPedidos(record.get("numPedidos").asInt());
				libro.setEstadoLibro(record.get("estadoLibro").asString());
				libros.add(libro);
			}
		} catch (Exception e) {
			throw e;
		} 
		return libros;
	}

	/**
	 * Método para registrar un libro. La propiedad <b>clasificacion</b> debe tener un constraint UNIQUE
	 * con la siguiente query:
	 * <pre>CREATE CONSTRAINT ON (l:Libro) ASSERT l.clasificacion IS UNIQUE</pre>
	 * @param libro
	 */
	@Override
	public void registrarLibro(Libro libro, Transaction transaccion) throws Exception {
		String queryInsert = "create (\n" + 
				"l:Libro {\n" + 
				"clasificacion: $clasificacion, \n" + 
				"titulo: $titulo, \n" + 
				"anio: $anio, \n" + 
				"autor: $autor,  \n" + 
				"codBarras: $codBarras, \n" + 
				"codEditorial: $codEditorial, \n" + 
				"numeroCalificaciones: $numeroCalificaciones, \n" + 
				"ultCalifEntendible: $ultCalifEntendible, \n" + 
				"califAcumEntendible: $califAcumEntendible, \n" + 
				"califEntendible: toFloat($califEntendible), \n" + 
				"ultCalifActualizado: $ultCalifActualizado, \n" + 
				"califAcumActualizado: $califAcumActualizado, \n" + 
				"califActualizado: toFloat($califActualizado), \n" + 
				"ultCalifTeoria: $ultCalifTeoria, \n" + 
				"califAcumTeoria: $califAcumTeoria, \n" + 
				"califTeoria: toFloat($califTeoria), \n" + 
				"ultCalifPractica: $ultCalifPractica, \n" + 
				"califAcumPractica: $califAcumPractica, \n" + 
				"califPractica: toFloat($califPractica), \n" + 
				"ultCalifComentario: $ultCalifComentario, \n" + 
				"califAcumComentario: $califAcumComentario, \n" + 
				"califComentario: toFloat($califComentario),\n" + 
				"calificacionPromedio: toFloat($calificacionPromedio), \n" + 
				"numPedidos: $numPedidos,\n" + 
				"estadoLibro: $estadoLibro\n" + 
				"})\n" + 
				"return id(l) as id";
		try {
			StatementResult statementResult = transaccion.run(queryInsert, Values.parameters("clasificacion", libro.getClasificacion(), "titulo",
					libro.getTitulo(), "anio", libro.getAnio(), "autor", libro.getAutor(), "codBarras",
					libro.getCodBarras(), "codEditorial", libro.getCodEditorial(), "numeroCalificaciones",
					libro.getNumeroCalificaciones(), "ultCalifEntendible", libro.getUltCalifEntendible(),
					"califAcumEntendible", libro.getCalifAcumEntendible(), "califEntendible",
					libro.getCalifEntendible(), "ultCalifActualizado", libro.getUltCalifActualizado(),
					"califAcumActualizado", libro.getCalifAcumActualizado(), "califActualizado",
					libro.getCalifActualizado(), "ultCalifTeoria", libro.getUltCalifTeoria(), "califAcumTeoria",
					libro.getCalifAcumTeoria(), "califTeoria", libro.getCalifTeoria(), "ultCalifPractica",
					libro.getUltCalifPractica(), "califAcumPractica", libro.getCalifAcumPractica(), "califPractica",
					libro.getCalifPractica(), "ultCalifComentario", libro.getUltCalifComentario(),
					"califAcumComentario", libro.getCalifAcumComentario(), "califComentario",
					libro.getCalifComentario(), "calificacionPromedio", libro.getCalificacionPromedio(), "numPedidos",
					libro.getNumPedidos(), "estadoLibro", libro.getEstadoLibro()));
			if (statementResult.hasNext()) {
				Record record = statementResult.next();
				libro.setId(record.get("id").asInt());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void modificarLibro(Libro libro, Transaction transaccion) throws Exception {
		String query = "match (l: Libro) where id(l) = $id " +
				"set l.clasificacion = $clasificacion, \n" + 
				"l.titulo = $titulo, \n" + 
				"l.anio = $anio, \n" + 
				"l.autor = $autor, \n" + 
				"l.codBarras = $codBarras, \n" + 
				"l.codEditorial = $codEditorial, \n" + 
				"l.numeroCalificaciones = $numeroCalificaciones, \n" + 
				"l.ultCalifEntendible = $ultCalifEntendible, \n" + 
				"l.califAcumEntendible = $califAcumEntendible, \n" + 
				"l.califEntendible = $califEntendible, \n" + 
				"l.ultCalifActualizado = $ultCalifActualizado, \n" + 
				"l.califAcumActualizado = $califAcumActualizado, \n" + 
				"l.califActualizado = $califActualizado, \n" + 
				"l.ultCalifTeoria = $ultCalifTeoria, \n" + 
				"l.califAcumTeoria = $califAcumTeoria, \n" + 
				"l.califTeoria = $califTeoria, \n" + 
				"l.ultCalifPractica = $ultCalifPractica, \n" + 
				"l.califAcumPractica = $califAcumPractica, \n" + 
				"l.califPractica = $califPractica,\n" + 
				"l.ultCalifComentario = $ultCalifComentario,\n" + 
				"l.califAcumComentario = $califAcumComentario,\n" + 
				"l.califComentario = $califComentario,\n" + 
				"l.calificacionPromedio = $calificacionPromedio,\n" + 
				"l.numPedidos = $numPedidos,\n" + 
				"l.estadoLibro = $estadoLibro\n " +
				"return id(l)";
		try {
			StatementResult statementResult = transaccion.run(query,
					Values.parameters("id", libro.getId(), "clasificacion", libro.getClasificacion(), "titulo",
							libro.getTitulo(), "anio", libro.getAnio(), "autor", libro.getAutor(), "codBarras",
							libro.getCodBarras(), "codEditorial", libro.getCodEditorial(), "numeroCalificaciones",
							libro.getNumeroCalificaciones(), "ultCalifEntendible", libro.getUltCalifEntendible(),
							"califAcumEntendible", libro.getCalifAcumEntendible(), "califEntendible",
							libro.getCalifEntendible(), "ultCalifActualizado", libro.getUltCalifActualizado(),
							"califAcumActualizado", libro.getCalifAcumActualizado(), "califActualizado",
							libro.getCalifActualizado(), "ultCalifTeoria", libro.getUltCalifTeoria(), "califAcumTeoria",
							libro.getCalifAcumTeoria(), "califTeoria", libro.getCalifTeoria(), "ultCalifPractica",
							libro.getUltCalifPractica(), "califAcumPractica", libro.getCalifAcumPractica(),
							"califPractica", libro.getCalifPractica(), "ultCalifComentario",
							libro.getUltCalifComentario(), "califAcumComentario", libro.getCalifAcumComentario(),
							"califComentario", libro.getCalifComentario(), "calificacionPromedio",
							libro.getCalificacionPromedio(), "numPedidos", libro.getNumPedidos(), "estadoLibro",
							libro.getEstadoLibro()));
			if (statementResult.hasNext()) {
				System.out.println("LibroDaoImpl.modificarLibro(): Se modificó el libro");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminarLibro(Libro libro, Transaction transaccion) throws Exception {
		String query = "match (l:Libro) where id(l)=$id detach delete l";
		try {
			transaccion.run(query, Values.parameters("id", libro.getId()));
		} catch (Exception e) {
			throw e;
		}
	}

}
