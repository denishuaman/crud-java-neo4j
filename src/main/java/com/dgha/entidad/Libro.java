package com.dgha.entidad;

public class Libro {

	private int id;
	private String clasificacion;
	private String titulo;
	private int anio;
	private String autor;
	private String codBarras;
	private String codEditorial;
	private int numeroCalificaciones;
	private int ultCalifEntendible;
	private int califAcumEntendible;
	private float califEntendible;
	private int ultCalifActualizado;
	private int califAcumActualizado;
	private float califActualizado;
	private int ultCalifTeoria;
	private int califAcumTeoria;
	private float califTeoria;
	private int ultCalifPractica;
	private int califAcumPractica;
	private float califPractica;
	private int ultCalifComentario;
	private int califAcumComentario;
	private float califComentario;
	private float calificacionPromedio;
	private int numPedidos;
	private String estadoLibro;

	/**
	 * Se inicializa con id = -1, porque en algunos casos se crea el nodo con id = 0
	 */
	public Libro() {
		this.id = -1;
	}

	public Libro(int id, String clasificacion, String titulo, int anio, String autor, String codBarras,
			int numeroCalificaciones, int ultCalifEntendible, int califAcumEntendible, float califEntendible,
			int ultCalifActualizado, int califAcumActualizado, float califActualizado, int ultCalifTeoria,
			int califAcumTeoria, float califTeoria, int ultCalifPractica, int califAcumPractica, float califPractica,
			int ultCalifComentario, int califAcumComentario, float califComentario, float calificacionPromedio,
			int numPedidos, String codEditorial, String estadoLibro) {
		this.id = id;
		this.clasificacion = clasificacion;
		this.titulo = titulo;
		this.anio = anio;
		this.autor = autor;
		this.codBarras = codBarras;
		this.numeroCalificaciones = numeroCalificaciones;
		this.ultCalifEntendible = ultCalifEntendible;
		this.califAcumEntendible = califAcumEntendible;
		this.califEntendible = califEntendible;
		this.ultCalifActualizado = ultCalifActualizado;
		this.califAcumActualizado = califAcumActualizado;
		this.califActualizado = califActualizado;
		this.ultCalifTeoria = ultCalifTeoria;
		this.califAcumTeoria = califAcumTeoria;
		this.califTeoria = califTeoria;
		this.ultCalifPractica = ultCalifPractica;
		this.califAcumPractica = califAcumPractica;
		this.califPractica = califPractica;
		this.ultCalifComentario = ultCalifComentario;
		this.califAcumComentario = califAcumComentario;
		this.califComentario = califComentario;
		this.calificacionPromedio = calificacionPromedio;
		this.numPedidos = numPedidos;
		this.codEditorial = codEditorial;
		this.estadoLibro = estadoLibro;
	}

	public Libro(String clasificacion, String titulo, int anio, String autor, String codBarras,
			int numeroCalificaciones, int ultCalifEntendible, int califAcumEntendible, float califEntendible,
			int ultCalifActualizado, int califAcumActualizado, float califActualizado, int ultCalifTeoria,
			int califAcumTeoria, float califTeoria, int ultCalifPractica, int califAcumPractica, float califPractica,
			int ultCalifComentario, int califAcumComentario, float califComentario, float calificacionPromedio,
			int numPedidos, String codEditorial, String estadoLibro) {
		this.clasificacion = clasificacion;
		this.titulo = titulo;
		this.anio = anio;
		this.autor = autor;
		this.codBarras = codBarras;
		this.numeroCalificaciones = numeroCalificaciones;
		this.ultCalifEntendible = ultCalifEntendible;
		this.califAcumEntendible = califAcumEntendible;
		this.califEntendible = califEntendible;
		this.ultCalifActualizado = ultCalifActualizado;
		this.califAcumActualizado = califAcumActualizado;
		this.califActualizado = califActualizado;
		this.ultCalifTeoria = ultCalifTeoria;
		this.califAcumTeoria = califAcumTeoria;
		this.califTeoria = califTeoria;
		this.ultCalifPractica = ultCalifPractica;
		this.califAcumPractica = califAcumPractica;
		this.califPractica = califPractica;
		this.ultCalifComentario = ultCalifComentario;
		this.califAcumComentario = califAcumComentario;
		this.califComentario = califComentario;
		this.calificacionPromedio = calificacionPromedio;
		this.numPedidos = numPedidos;
		this.codEditorial = codEditorial;
		this.estadoLibro = estadoLibro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public int getNumeroCalificaciones() {
		return numeroCalificaciones;
	}

	public void setNumeroCalificaciones(int numeroCalificaciones) {
		this.numeroCalificaciones = numeroCalificaciones;
	}

	public int getUltCalifEntendible() {
		return ultCalifEntendible;
	}

	public void setUltCalifEntendible(int ultCalifEntendible) {
		this.ultCalifEntendible = ultCalifEntendible;
	}

	public int getCalifAcumEntendible() {
		return califAcumEntendible;
	}

	public void setCalifAcumEntendible(int califAcumEntendible) {
		this.califAcumEntendible = califAcumEntendible;
	}

	public float getCalifEntendible() {
		return califEntendible;
	}

	public void setCalifEntendible(float califEntendible) {
		this.califEntendible = califEntendible;
	}

	public int getUltCalifActualizado() {
		return ultCalifActualizado;
	}

	public void setUltCalifActualizado(int ultCalifActualizado) {
		this.ultCalifActualizado = ultCalifActualizado;
	}

	public int getCalifAcumActualizado() {
		return califAcumActualizado;
	}

	public void setCalifAcumActualizado(int califAcumActualizado) {
		this.califAcumActualizado = califAcumActualizado;
	}

	public float getCalifActualizado() {
		return califActualizado;
	}

	public void setCalifActualizado(float califActualizado) {
		this.califActualizado = califActualizado;
	}

	public int getUltCalifTeoria() {
		return ultCalifTeoria;
	}

	public void setUltCalifTeoria(int ultCalifTeoria) {
		this.ultCalifTeoria = ultCalifTeoria;
	}

	public int getCalifAcumTeoria() {
		return califAcumTeoria;
	}

	public void setCalifAcumTeoria(int califAcumTeoria) {
		this.califAcumTeoria = califAcumTeoria;
	}

	public float getCalifTeoria() {
		return califTeoria;
	}

	public void setCalifTeoria(float califTeoria) {
		this.califTeoria = califTeoria;
	}

	public int getUltCalifPractica() {
		return ultCalifPractica;
	}

	public void setUltCalifPractica(int ultCalifPractica) {
		this.ultCalifPractica = ultCalifPractica;
	}

	public int getCalifAcumPractica() {
		return califAcumPractica;
	}

	public void setCalifAcumPractica(int califAcumPractica) {
		this.califAcumPractica = califAcumPractica;
	}

	public float getCalifPractica() {
		return califPractica;
	}

	public void setCalifPractica(float califPractica) {
		this.califPractica = califPractica;
	}

	public int getUltCalifComentario() {
		return ultCalifComentario;
	}

	public void setUltCalifComentario(int ultCalifComentario) {
		this.ultCalifComentario = ultCalifComentario;
	}

	public int getCalifAcumComentario() {
		return califAcumComentario;
	}

	public void setCalifAcumComentario(int califAcumComentario) {
		this.califAcumComentario = califAcumComentario;
	}

	public float getCalifComentario() {
		return califComentario;
	}

	public void setCalifComentario(float califComentario) {
		this.califComentario = califComentario;
	}

	public float getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}

	public int getNumPedidos() {
		return numPedidos;
	}

	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}

	public String getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(String codEditorial) {
		this.codEditorial = codEditorial;
	}

	public String getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(String estadoLibro) {
		this.estadoLibro = estadoLibro;
	}

}
