package com.uniovi.sdi;

public class Comentario {

	private String nombre;
	private String comentario;

	public Comentario() {

	}

	public Comentario(String nombre, String comentario) {
		this.nombre = nombre;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
