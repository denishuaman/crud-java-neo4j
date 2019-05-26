package com.dgha.util;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

public class UtilConexion {

	private Session session = null;
	private static UtilConexion instancia;
	private static final Logger log = Logger.getLogger(UtilConexion.class);

	public static UtilConexion getInstancia() {
		if (instancia == null) {
			log.info("Creando Utilconexion");
			instancia = new UtilConexion();
		}
		return instancia;
	}

	public void crearSesion(Driver driver) {
		log.info("Creando sesión");
		try {
			this.session = driver.session();
		} catch (Exception e) {
			log.error("Error al crear la sesión", e);
			throw e;
		}
	}

	public void cerrarSesion() {
		log.info("Inicio del método cerrarSesion");
		try {
			if (this.session != null) {
				log.info("Cerrando sesión");
				this.session.close();
			}
		} catch (Exception e) {
			log.error("Error al cerrar sesión", e);
			throw e;
		}
	}

	public void limpiarRecursos(Transaction transaccion) {
		log.info("Inicio del método limpiarRecursos");
		try {
			if (transaccion != null) {
				log.info("Cerrando transacción");
				transaccion.close();
			}
		} catch (Exception e) {
			transaccion.failure();
			transaccion.close();
		}
		cerrarSesion();
	}

	public Session getSession() {
		return session;
	}
}
