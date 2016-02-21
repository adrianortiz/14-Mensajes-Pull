package com.codizer.pojo;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class User {
	
	private int id;
	private String nombre;
	
	/**
	 * Contructor general
	 */
	public User(){
		id = 0;
		nombre = "";
	}
	
	/**
	 * Getter para id de User
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter para id de User
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter para nombre de User
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter para nombre de User
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
