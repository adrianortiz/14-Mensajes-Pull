package com.codizer.model;

import java.util.List;
import com.codizer.pojo.User;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public interface UserDao {
	
	/**
	 * Método login que permite el logeo de
	 * un usuario que existe en la base
	 * de datos y returna boolean
	 * en base a su existencia.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean login(User user);
	
	/**
	 * Método save que almacena un objeto 
	 * User en la base de datos
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean save(User user);
	
	/**
	 * Método findAll que busca a todos los usuarios
	 * de la base de datos
	 *  
	 * @return List<User>
	 */
	public List<User> findAll();
	
	/**
	 * Método findByName que busca un usuario
	 * de la base de datos en base a su nombre
	 * 
	 * @param nombre
	 * @return User
	 */
	public User findByName(String nombre);
}
