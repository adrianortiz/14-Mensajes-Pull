package com.codizer.model;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class UserSessionSingleton {
	
	private static final UserSessionSingleton userSession = new UserSessionSingleton();
	private int id;
	private String nombre;
	private boolean session;

	/**
	 * Contructor privado que avita instanciar
	 * la clase actual
	 */
	private UserSessionSingleton() {
		this.id = 0;
		this.nombre = "";
		this.session = false;
	}
	
	/**
	 * Getter para nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter para nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter para id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter para id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método que permite saber si una
	 * sessión esta activa o no
	 * @return boolean
	 */
	public boolean isSession() {
		return session;
	}

	/**
	 * Setter para session
	 * @param session
	 */
	public void setSession(boolean session) {
		this.session = session;
	}

	/**
	 * Getter para obetner la instacia singleton
	 * de esta clase
	 * @return userSession
	 */
	public static UserSessionSingleton getUsersession() {
		return userSession;
	}
	
}
