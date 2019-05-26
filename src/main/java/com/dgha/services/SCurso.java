package com.dgha.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Transaction;

import com.dgha.dao.CursoDao;
import com.dgha.dao.impl.CursoDaoImpl;
import com.dgha.entidad.Curso;
import com.dgha.entidad.ResponseGeneral;
import com.dgha.util.Constantes;
import com.dgha.util.UtilConexion;
import com.google.gson.Gson;

public class SCurso {

	private static final Logger log = Logger.getLogger(SCurso.class);
	private static SCurso instancia;
	private UtilConexion conexion;
	private CursoDao cursoDao;

	public SCurso() {
		log.info("Inicio del service SCurso");
		conexion = UtilConexion.getInstancia();
		this.cursoDao = new CursoDaoImpl();
	}

	public static SCurso getInstancia() {
		if (instancia == null) {
			log.info("Creando instancia de SCurso");
			instancia = new SCurso();
		}
		return instancia;
	}

	public List<Curso> listar(Driver driver) {
		log.info("Inicio del método listar");
		List<Curso> cursos = null;
		conexion.crearSesion(driver);
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

	public ResponseGeneral registrar(Curso curso, Driver driver) {
		log.info("Inicio del método registrar");
		ResponseGeneral responseGeneral = null;
		conexion.crearSesion(driver);
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.registrarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se registró el curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "Error al rgistrar curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}

	public ResponseGeneral modificar(Curso curso, Driver driver) {
		log.info("Inicio del método modificar");
		ResponseGeneral responseGeneral;
		conexion.crearSesion(driver);
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.modificarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se modificó el curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "No se pudo modificar el curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}

	public ResponseGeneral eliminarCurso(Curso curso, Driver driver) {
		log.info("Inicio del método eliminarCurso");
		ResponseGeneral responseGeneral;
		conexion.crearSesion(driver);
		Transaction transaccion = conexion.getSession().beginTransaction();
		try {
			cursoDao.eliminarCurso(curso, transaccion);
			transaccion.success();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_OK, "Se eliminó el curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
		} catch (Exception e) {
			transaccion.failure();
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_NO_OK, "No se pudo modificar el curso");
			log.info("responseGeneral="+new Gson().toJson(responseGeneral));
			log.error("Error", e);
		} finally {
			conexion.limpiarRecursos(transaccion);
		}
		return responseGeneral;
	}
}
