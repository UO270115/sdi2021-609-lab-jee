package com.uniovi.entities;

public class Professor {

	private Long id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String categoria;

	public Professor() {

	}

	public Professor(Long id, String dni, String nombre, String apellidos, String categoria) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", categoria="
				+ categoria + "]";
	}

}
