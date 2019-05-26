package com.dgha.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

public class UtilConexion {

	private Driver driver = null;
	private Session session = null;
	private static UtilConexion instancia;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("configuracion");
	private final Logger log = Logger.getLogger(this.getClass());
	private String url;
	private String usuario;
	private String constrasena;

	public UtilConexion() {
		this.url = resourceBundle.getString("url");
		this.usuario = resourceBundle.getString("usuario");
		this.constrasena = resourceBundle.getString("contrasena");
		cargarDriver();
	}

	public static UtilConexion getInstancia() {
		if (instancia == null) {
			instancia = new UtilConexion();
		}
		return instancia;
	}

	public void cargarDriver() {
		try {
			this.driver = GraphDatabase.driver(this.url, AuthTokens.basic(this.usuario, this.constrasena));
		} catch (Exception e) {
			log.error("Error al conectarse", e);
			throw e;
		}
	}

	public void crearSesion() {
		try {
			this.session = driver.session();
		} catch (Exception e) {
			log.error("Error al crear la sesión", e);
			throw e;
		}
	}

	public void cerrarSesion() {
		try {
			this.session.close();
		} catch (Exception e) {
			log.error("Error al cerrar sesión", e);
			throw e;
		}
	}
	
	public void limpiarRecursos(Transaction transaccion) {
		try {
			transaccion.close();
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
