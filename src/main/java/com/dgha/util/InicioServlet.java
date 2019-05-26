package com.dgha.util;

import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class InicioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Logger log = Logger.getLogger(this.getClass());
	private ResourceBundle resourceBundle;
	private String url;
	private String usuario;
	private String constrasena;
	private Driver driver = null;

	public void init(ServletConfig config) throws ServletException {
		log.info("Inicio de InicioServlet");
		super.init(config);
		resourceBundle = ResourceBundle.getBundle("configuracion");
		this.url = resourceBundle.getString("url");
		this.usuario = resourceBundle.getString("usuario");
		this.constrasena = resourceBundle.getString("contrasena");
		this.cargarDriver();
		config.getServletContext().setAttribute("driver", this.driver);
	}

	public void cargarDriver() {
		log.info("Cargando driver");
		try {
			this.driver = GraphDatabase.driver(this.url, AuthTokens.basic(this.usuario, this.constrasena));
		} catch (Exception e) {
			log.error("Error al conectarse", e);
			throw e;
		}
	}
}
