package com.dgha.entidad;

public class Curso {
	private int id;
	private String codigoCurso;
	private String nombreCurso;
	private String estadoCurso;

	/**
	 * Se inicializa con id = -1, porque en algunos casos se crea el nodo con id = 0
	 */
	public Curso() {
		this.id = -1;
	}

	public Curso(String codigoCurso, String nombreCurso, String estadoCurso) {
		this.id = -1;
		this.codigoCurso = codigoCurso;
		this.nombreCurso = nombreCurso;
		this.estadoCurso = estadoCurso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getEstadoCurso() {
		return estadoCurso;
	}

	public void setEstadoCurso(String estadoCurso) {
		this.estadoCurso = estadoCurso;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", codigoCurso=" + codigoCurso + ", nombreCurso=" + nombreCurso + ", estadoCurso="
				+ estadoCurso + "]";
	}

}
