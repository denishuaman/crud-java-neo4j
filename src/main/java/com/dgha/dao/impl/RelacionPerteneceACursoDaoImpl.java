package com.dgha.dao.impl;

import org.neo4j.driver.internal.value.NullValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import com.dgha.dao.RelacionPerteneceACursoDao;
import com.dgha.entidad.Curso;
import com.dgha.entidad.Libro;
import com.dgha.entidad.RelacionPerteneceACurso;
import com.dgha.util.Funciones;
import com.dgha.util.UtilConexion;

public class RelacionPerteneceACursoDaoImpl implements RelacionPerteneceACursoDao {

	private UtilConexion utilConexion;

	public RelacionPerteneceACursoDaoImpl() {
		this.utilConexion = UtilConexion.getInstancia();
	}

	@Override
	public void relacionar(Libro libro, Curso curso, RelacionPerteneceACurso relacionPerteneceACurso) throws Exception {
		
		if (libro != null && curso != null && relacionPerteneceACurso != null) {
			Transaction transaccion = this.utilConexion.getSession().beginTransaction();
			
			String queryBuscarRelacion = "match (l:Libro) where id(l) = $ idLibro \n" + 
					"match (c:Curso) where id(c) = $idCurso \n" + 
					"match ((l)-[r:perteneceACurso]->(c)) \n" + 
					"return id(r) as idRelacion";
			
			String queryRelacionarLibroACurso = "match(l:Libro) where id(l) = $idLibro \n" 
					+ "match(c:Curso) where id(c) = $idCurso \n" 
					+ "create (l)-[r:perteneceACurso{usuarioCreacionRelPerteneceACurso: $usuarioCreacionRelPerteneceACurso,"
					+ "fechaCreacionRelPerteneceACurso: localdatetime({ timezone: 'America/Lima' }), "
					+ "usuarioModificacion: null, fechaModificacionRelPerteneceACurso: null}]->(c) \n" 
					+ "return  id(r) as id, "
					+ "r.fechaCreacionRelPerteneceACurso as fechaCreacion, "
					+ "r.usuarioModificacionRelPerteneceACurso as usuarioModificacion, "
					+ "r.fechaModificacionRelPerteneceACurso as fechaModificacion";
			
			System.out.println("RelacionPerteneceACursoDaoImpl.relacionar(): query=" + queryRelacionarLibroACurso);
			try {
				StatementResult resultBuscarRelacion = transaccion.run(queryBuscarRelacion,
						Values.parameters("idLibro", libro.getId(), "idCurso", curso.getId()));
				if (!resultBuscarRelacion.hasNext()) {
					StatementResult resultRelacionarLibroACurso = transaccion.run(queryRelacionarLibroACurso,
							Values.parameters("idLibro", libro.getId(), "idCurso", curso.getId(),
									"usuarioCreacionRelPerteneceACurso", relacionPerteneceACurso.getUsuarioCreacion()));
					if (resultRelacionarLibroACurso.hasNext()) {
						Record record = resultRelacionarLibroACurso.next();
						relacionPerteneceACurso.setId(record.get("id").asInt());
						relacionPerteneceACurso.setFechaDeCreacion(
								Funciones.convertToDateViaInstant(record.get("fechaCreacion").asLocalDateTime()));
						relacionPerteneceACurso.setUsuarioModificacion(!record.get("usuarioModificacion").equals(NullValue.NULL)
										? record.get("usuarioModificacion").asString()
										: null);
						relacionPerteneceACurso.setFechaDeModificacion(!record.get("fechaModificacion").equals(NullValue.NULL) 
										? Funciones.convertToDateViaInstant(record.get("fechaModificacion").asLocalDateTime())
										: null);
						transaccion.success();
					}
				} else {
					throw new Exception("La relación entre el libro y el curso ya existe");
				}
			} catch (Exception e) {
				transaccion.failure();
				throw e;
			} finally {
				this.utilConexion.limpiarRecursos(transaccion);
			}
		} else {
			throw new Exception("El curso, el libro o la relación son nulas o vacías");
		}
	}

	@Override
	public void eliminarRelacion(Libro libro, Curso curso) throws Exception {
		if (libro != null && curso != null) {
			Transaction transaccion = this.utilConexion.getSession().beginTransaction();
			String query = "match (l:Libro) where id(l) = $idLibro \n" + 
					"match (c:Curso) where id(c) = $idCurso \n" + 
					"match ((l)-[r:perteneceACurso]->(c)) \n" + 
					"delete r";
			try {
				transaccion.run(query, Values.parameters("idLibro", libro.getId(), "idCurso", curso.getId()));
				transaccion.success();
			} catch (Exception e) {
				transaccion.failure();
				throw e;
			} finally {
				this.utilConexion.limpiarRecursos(transaccion);
			}
		} else {
			throw new Exception("El libro o el curso son nulos");
		}
	}
}
