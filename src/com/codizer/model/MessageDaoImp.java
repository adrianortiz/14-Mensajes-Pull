package com.codizer.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codizer.pojo.Message;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class MessageDaoImp implements MessageDao {

	private DBSingleton conexion = DBSingleton.getDBSingleton();
	private Connection con = (Connection) conexion.getCon();
	
	private String sql;
	private Statement st;
	private ResultSet rs;
	
    /**
     * Contructor publico
     */
	public MessageDaoImp() {
		sql = "";
		st = null;
		rs = null;
	}
	
	/**
	 * Método save que guarda un objeto Message
	 * en la base de datos.
	 * @param msg
	 * @return boolean
	 */
	@Override
	public boolean save(Message msg) {
		try {
			st = (Statement) con.createStatement();
			sql = "INSERT INTO msg (fecha, msg, user_id) VALUES ('"+ msg.getFecha() +"', '"+ msg.getMsg() +"', "+ msg.getUserId() +")";
			st.executeUpdate(sql);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método findAll que busca todos los mensajes
	 * en la base de datos.
	 * @return List<Message>
	 */
	@Override
	public List<Message> findAll() {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM msg;");
			return toMessageList(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Método findAllByUserId que busca todos los
	 * mensajes en base al id de un usuario.
	 * @param id
	 * @return List<Message>
	 */
	@Override
	public List<Message> findAllByUserId(int id) {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM msg WHERE user_id=" + id + ";");
			
			return toMessageList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.codizer.model.MessageDao#toMessageList(java.sql.ResultSet)
	 */
	@Override
	public List<Message> toMessageList(ResultSet rs) throws SQLException {
		List<Message> msgs = new ArrayList<>();
		
		while(rs.next()) {
			Message newMsg = new Message();
			
			newMsg.setId(rs.getInt("id"));
			newMsg.setFecha(rs.getTimestamp("fecha"));
			newMsg.setMsg(rs.getString("msg"));
			newMsg.setUserId(rs.getInt("user_id"));
			
			msgs.add(newMsg);
		}
		return msgs;
	}

}
