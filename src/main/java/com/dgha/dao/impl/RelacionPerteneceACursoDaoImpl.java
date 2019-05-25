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
	public RelacionPerteneceACurso relacionar(Libro libro, Curso curso, String usuario) {
		RelacionPerteneceACurso relacionPerteneceACurso=null;
		Transaction transaccion = this.utilConexion.getSession().beginTransaction();
		String query = "match(l:Libro) where id(l) = $idLibro \n" 
				+ "match(c:Curso) where id(c) = $idCurso \n" 
				+ "create (l)-[r:perteneceACurso{usuarioCreacionRelPerteneceACurso: $usuarioCreacionRelPerteneceACurso,"
				+ "fechaCreacionRelPerteneceACurso: localdatetime({ timezone: 'America/Lima' }), "
				+ "usuarioModificacion: null, fechaModificacionRelPerteneceACurso: null}]->(c) \n" 
				+ "return  id(r) as id, "
				+ "r.usuarioCreacionRelPerteneceACurso as usuarioCreacion, "
				+ "r.fechaCreacionRelPerteneceACurso as fechaCreacion, "
				+ "r.usuarioModificacionRelPerteneceACurso as usuarioModificacion, "
				+ "r.fechaModificacionRelPerteneceACurso as fechaModificacion";
		System.out.println("RelacionPerteneceACursoDaoImpl.relacionar(): query=" + query);
		try {
			StatementResult statementResult = transaccion.run(query, Values.parameters("idLibro", libro.getId(),
					"idCurso", curso.getId(), "usuarioCreacionRelPerteneceACurso", usuario));
			if (statementResult.hasNext()) {
				Record record = statementResult.next();
				relacionPerteneceACurso = new RelacionPerteneceACurso();
				relacionPerteneceACurso.setId(record.get("id").asInt());
				relacionPerteneceACurso.setUsuarioCreacion(record.get("usuarioCreacion").asString());
				relacionPerteneceACurso.setFechaDeCreacion(Funciones.convertToDateViaInstant(record.get("fechaCreacion").asLocalDateTime()));
				relacionPerteneceACurso.setUsuarioModificacion(!record.get("usuarioModificacion").equals(NullValue.NULL) ? record.get("usuarioModificacion").asString() : null);
				relacionPerteneceACurso.setFechaDeModificacion(!record.get("fechaModificacion").equals(NullValue.NULL) ? Funciones.convertToDateViaInstant(record.get("fechaModificacion").asLocalDateTime()) : null);
				transaccion.success();
			}
		} catch (Exception e) {
			transaccion.failure();
			e.printStackTrace();
			throw e;
		} finally {
			this.utilConexion.limpiarRecursos(transaccion);
		}
		return relacionPerteneceACurso;
	}
}
