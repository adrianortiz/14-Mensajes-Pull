package com.codizer.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author Adrian Ortiz
 * Singleto que gestiona la Base de datos
 *
 */
public final class DBSingleton {
	
	private static final DBSingleton conexionDB = new DBSingleton();

	private final String USERNAME = "root";
	private final String PASSWORD = "w#9M_*G<LIad";
	private final String HOST = "127.0.0.1";
	private final String PORT = "3306";
	private final String DATABASE = "singleton";
	private final String CLASSNAME = "com.mysql.jdbc.Driver";
	private final String SSL = "?autoReconnect=true&useSSL=false";
	private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + SSL;
    
    private Connection con;
    
    /**
     * Contructor privado que evita crear nuevas
     * instancias de esta clase.
     */
    private DBSingleton() {
    	
    	try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conectado");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
    
    /**
     * Método que retorna una unica instacia
     * de la clase DBSingleton
     * @return conexionDB DBSingleton
     */
    public static DBSingleton getDBSingleton() {
        return conexionDB;
    }

    /**
     * Método que devuelve la conexión
     * del singleton DBSingleton
     * @return con Connection
     */
	public Connection getCon() {
		return con;
	}

	/**
	 * Método que permite modificar
	 * la conexión del singleton
	 * @param con Connection
	 */
	public void setCon(Connection con) {
		this.con = con;
	}
    
}
