package com.dgha.entidad;

import java.util.Date;

public class RelacionPerteneceACurso {

	private int id;
	private String usuarioCreacion;
	private Date fechaDeCreacion;
	private String usuarioModificacion;
	private Date fechaDeModificacion;

	public RelacionPerteneceACurso() {
	}

	public RelacionPerteneceACurso(int id, String usuarioCreacion, Date fechaDeCreacion, String usuarioModificacion,
			Date fechaDeModificacion) {
		this.id = id;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaDeCreacion = fechaDeCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaDeModificacion = fechaDeModificacion;
	}

	public RelacionPerteneceACurso(String usuarioCreacion, Date fechaDeCreacion, String usuarioModificacion,
			Date fechaDeModificacion) {
		this.usuarioCreacion = usuarioCreacion;
		this.fechaDeCreacion = fechaDeCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaDeModificacion = fechaDeModificacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaDeModificacion() {
		return fechaDeModificacion;
	}

	public void setFechaDeModificacion(Date fechaDeModificacion) {
		this.fechaDeModificacion = fechaDeModificacion;
	}
}
