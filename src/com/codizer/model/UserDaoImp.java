package com.codizer.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codizer.pojo.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDaoImp implements UserDao {
	
	private DBSingleton conexion = DBSingleton.getDBSingleton();
	private Connection con = (Connection) conexion.getCon();
	private String sql;
	private Statement st;
	private ResultSet rs;
	
	/**
	 * Contructor inicial de UserDaoImp
	 */
	public UserDaoImp() {
		sql = "";
		st = null;
		rs = null;
	}

	/**
	 * Método login que permite el logeo de
	 * un usuario que existe en la base
	 * de datos y returna boolean
	 * en base a su existencia.
	 * 
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean login(User user) {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE nombre = '" + user.getNombre() + "';");
			
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Método save que almacena un objeto 
	 * User en la base de datos
	 * 
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean save(User user) {
		try {
			st = (Statement) con.createStatement();
			sql = "INSERT INTO user (nombre) VALUES ('"+ user.getNombre() +"');";
			st.executeUpdate(sql);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * Método findAll que busca a todos los usuarios
	 * de la base de datos
	 *  
	 * @return List<User>
	 */
	@Override
	public List<User> findAll() {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM user;");
			
			List<User> users = new ArrayList<>();
			while(rs.next()) {
				User newUser = new User();
				newUser.setId(rs.getInt("id"));
				newUser.setNombre(rs.getString("nombre"));
				users.add(newUser);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Método findByName que busca un usuario
	 * de la base de datos en base a su nombre
	 * 
	 * @param nombre
	 * @return User
	 */
	@Override
	public User findByName(String nombre) {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE nombre = '" + nombre + "';");
			
			User user = new User();
			
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("nombre"));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
