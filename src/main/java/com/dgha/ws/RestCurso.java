package com.dgha.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.dgha.entidad.Curso;
import com.dgha.entidad.ResponseGeneral;
import com.dgha.services.SCurso;
import com.dgha.util.Constantes;
import com.sun.jersey.spi.resource.Singleton;

@Path("/curso")
@Singleton
public class RestCurso {

	private Logger log = Logger.getLogger(this.getClass());
	private SCurso sCurso;

	public RestCurso() {
		sCurso = SCurso.getInstancia();
	}

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		List<Curso> cursos = new ArrayList<>();
		try {
			cursos = sCurso.listar();
		} catch (Exception e) {
			log.error("Error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(cursos).build();
		}
		return Response.status(Status.OK).entity(cursos).build();
	}

	@POST
	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrar(Curso curso) {
		ResponseGeneral responseGeneral;
		try {
			responseGeneral = sCurso.registrar(curso);
		} catch (Exception e) {
			log.error("Error en el servicio que registra el curso", e);
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_ERROR, "Error en el servicio que registra el curso");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseGeneral).build();
		}
		return Response.status(Status.OK).entity(responseGeneral).build();
	}

	@POST
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificar(Curso curso) {
		ResponseGeneral responseGeneral;
		try {
			responseGeneral = sCurso.modificar(curso);
		} catch (Exception e) {
			log.error("Error en el servicio que modifica el curso", e);
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_ERROR, "Error en el servicio que modifica el curso");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseGeneral).build();
		}
		return Response.status(Status.OK).entity(responseGeneral).build();
	}

	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminar(Curso curso) {
		ResponseGeneral responseGeneral;
		try {
			responseGeneral = sCurso.eliminarCurso(curso);
		} catch (Exception e) {
			log.error("Error en el servicio que elimina el curso", e);
			responseGeneral = new ResponseGeneral(Constantes.CODIGO_ERROR, "Error en el servicio que eliminar el curso");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseGeneral).build();
		}
		return Response.status(Status.OK).entity(responseGeneral).build();
	}

}
