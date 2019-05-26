package com.dgha.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import com.dgha.dao.CursoDao;
import com.dgha.entidad.Curso;

public class CursoDaoImpl implements CursoDao {

	@Override
	public List<Curso> listarCursos(Transaction transaccion) throws Exception {
		List<Curso> cursos = new ArrayList<>();
		String query = "match (n:Curso) return ID(n) as id, n.codigoCurso as codigoCurso, n.nombreCurso as nombreCurso, n.estadoCurso as estadoCurso";
		try {
			StatementResult statementResult = transaccion.run(query);
			while (statementResult.hasNext()) {
				Record record = statementResult.next();
				Curso curso = new Curso();
				curso.setId(record.get("id").asInt());
				curso.setCodigoCurso(record.get("codigoCurso").asString());
				curso.setNombreCurso(record.get("nombreCurso").asString());
				curso.setEstadoCurso(record.get("estadoCurso").asString());
				cursos.add(curso);
			}
		} catch (Exception e) {
			throw e;
		} 
		return cursos;
	}

	@Override
	public void registrarCurso(Curso curso, Transaction transaccion) throws Exception{
		String query = "create (c:Curso {codigoCurso: $codigoCurso, nombreCurso: $nombreCurso, estadoCurso: $estadoCurso}) \n"
				+ "return id(c) as id";
		try {
			StatementResult statementResult = transaccion.run(query, Values.parameters("codigoCurso", curso.getCodigoCurso(), "nombreCurso",
					curso.getNombreCurso(), "estadoCurso", curso.getEstadoCurso()));
			if(statementResult.hasNext()) {
				Record record = statementResult.next();
				curso.setId(record.get("id").asInt());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void modificarCurso(Curso curso, Transaction transaccion) throws Exception {
		String query = "match(n:Curso) where id(n) = $id \n"
				+ "set n.codigoCurso = $codigoCurso, n.nombreCurso = $nombreCurso, n.estadoCurso = $estadoCurso \n"
				+ "return id(n)";
		try {
			StatementResult statementResult = transaccion.run(query, Values.parameters("id", curso.getId(), "codigoCurso", curso.getCodigoCurso(),
					"nombreCurso", curso.getNombreCurso(), "estadoCurso", curso.getEstadoCurso()));
			// se realiza ello para verificar si lanza un error de constraint
			if(statementResult.hasNext()) {
				System.out.println("CursoDaoImpl.modificarCurso(): Se modificó el curso");
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public void eliminarCurso(Curso curso, Transaction transaccion) throws Exception {
		String query = "match(n:Curso) where id(n) = $id detach delete n";
		try {
			transaccion.run(query, Values.parameters("id", curso.getId()));
		} catch (Exception e) {
			throw e;
		} 
	}
}
