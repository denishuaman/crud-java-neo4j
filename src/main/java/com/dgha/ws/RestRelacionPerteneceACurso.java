package com.dgha.ws;

import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.neo4j.driver.v1.Driver;

import com.dgha.entidad.Parametros;
import com.dgha.entidad.ResponseGeneral;
import com.dgha.services.SRelPerteneceACurso;
import com.dgha.util.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.spi.resource.Singleton;

@Path("/pertenece-a-curso")
@Singleton
public class RestRelacionPerteneceACurso {

	private Logger log = Logger.getLogger(this.getClass());
	private SRelPerteneceACurso sRelPerteneceACurso;
	private Gson gson;

	public RestRelacionPerteneceACurso() {
		log.info("Inicio de RestRelacionPerteneceACurso");
		sRelPerteneceACurso = SRelPerteneceACurso.getInstancia();
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@POST
	@Path("/relacionar-nuevos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response relacionarLibroACurso(@Context ServletContext context, Parametros parametros) {
		log.info("Inicio del servicio relacionar-nuevos");
		log.info("Parametros=" + gson.toJson(parametros));
		ResponseGeneral responseGeneral = null;
		try {
			Driver driver = (Driver) context.getAttribute("driver");
			responseGeneral = sRelPerteneceACurso.relacionarLibroNuevoACursoNuevo(parametros.getLibro(), parametros.getCurso(), driver);
		} catch (Exception e) {
			log.error("Error al relacionar el libro nuevo con el curso nuevo", e);
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_ERROR,
					"Error al relacionar libro nuevo con curso nuevo");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseGeneral).build();
		}
		return Response.status(Status.OK).entity(responseGeneral).build();
	}
}
