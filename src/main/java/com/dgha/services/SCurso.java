package com.dgha.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.CursoDao;
import com.dgha.dao.impl.CursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.entidad.ResponseGeneral;
import com.dgha.util.Constantes;
import com.dgha.util.UtilConexion;

public class SCurso {

	private Logger log = Logger.getLogger(this.getClass());
	private static SCurso instancia;
	private UtilConexion conexion;
	private CursoDao cursoDao;

	public SCurso() {
		conexion = UtilConexion.getInstancia();
		this.cursoDao = new CursoDaoImpl();
	}

	public static SCurso getInstancia() {
		if (instancia == null) {
			instancia = new SCurso();
		}
		return instancia;
	}

	public List<Curso> listar() {
		List<Curso> cursos = null;
		conexion.crearSesion();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursos = cursoDao.listarCursos(transaccion);
			transaccion.success();
		} catch (Exception e) {
			transaccion.failure();
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return cursos;
	}

	public ResponseGeneral registrar(Curso curso) {
		ResponseGeneral responseGeneral = null;
		conexion.crearSesion();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.registrarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se registró el curso");
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "Error al rgistrar curso");
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}

	public ResponseGeneral modificar(Curso curso) {
		ResponseGeneral responseGeneral;
		conexion.crearSesion();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.modificarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se modificó el curso");
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "No se pudo modificar el curso");
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}

	public ResponseGeneral eliminarCurso(Curso curso) {
		ResponseGeneral responseGeneral;
		conexion.crearSesion();
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.eliminarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se eliminó el curso");
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "No se pudo modificar el curso");
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}
}
