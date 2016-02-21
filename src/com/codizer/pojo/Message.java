package com.codizer.pojo;

import java.sql.Timestamp;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class Message {
	
	private int id;
	private Timestamp fecha;
	private String msg;
	private int userId;
	
	/**
	 * Contructor publico y general
	 */
	public Message(){
		id = 0;
		fecha = null;
		msg = "";
		userId = 0;
	}
	
	/**
	 * Getter para id de Message
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter para id de Message
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter para id de Message
	 * @return fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}
	
	/**
	 * Setter para fecha de Message
	 * @param fecha
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Getter para msg de Message
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Setter para msg de Message
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Getter para userId de Message
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Setter para userId de Message
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * ToString para un objeto Message
	 */
	@Override
	public String toString() {
		return "Message [id=" + id + ", fecha=" + fecha + ", msg=" + msg + ", userId=" + userId + "]";
	}
	
}
