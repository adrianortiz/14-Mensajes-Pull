package com.codizer.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codizer.pojo.Message;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public interface MessageDao {
	/**
	 * Método save que guarda un objeto Message
	 * en la base de datos.
	 * @param msg
	 * @return boolean
	 */
	public boolean save(Message msg);
	
	/**
	 * Método findAll que busca todos los mensajes
	 * en la base de datos.
	 * @return List<Message>
	 */
	public List<Message> findAll();
	
	/**
	 * Método findAllByUserId que busca todos los
	 * mensajes en base al id de un usuario.
	 * @param id
	 * @return List<Message>
	 */
	public List<Message> findAllByUserId(int id);
	
	/**
	 * Método toMessageList que agrega objetos 
	 * Message en un List<Message>
	 * @param rs
	 * @return List<Message> 
	 * @throws SQLException
	 */
	public List<Message> toMessageList(ResultSet rs) throws SQLException;
}
