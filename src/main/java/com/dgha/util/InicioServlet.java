package com.dgha.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InicioServlet extends HttpServlet {

	private final Logger log = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.info("Inicio de la aplicación");
	}
}
