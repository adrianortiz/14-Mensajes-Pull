package com.codizer.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.codizer.model.MessageDao;
import com.codizer.model.MessageDaoImp;
import com.codizer.model.UserSessionSingleton;
import com.codizer.pojo.Message;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class MyPullsUISingleton extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final MyPullsUISingleton myPullsUISingleton = new MyPullsUISingleton();
	private UserSessionSingleton session = UserSessionSingleton.getUsersession();
	
	private JLabel lbMyPullTitle = new JLabel("My pulls");
	private TextArea txtMyPulls = new TextArea();
	
	private Container c = getContentPane();
	
	private Color whiteColor = new Color(255, 255, 255);
	
    /**
     * Contructor privado que evita crear nuevas
     * instancias de esta clase.
     */
	private MyPullsUISingleton() {
		super.setTitle("My Pulls");
		super.setSize(320, 550);
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PullUISingleton pullUISingleton = PullUISingleton.getInstance();
				pullUISingleton.setVisible(false);
				
				LoginUISingleton loginUI = LoginUISingleton.getInstance();
				loginUI.setVisible(true);
				
				session.setSession(false);
			}
		});
		
		controllersUI();
	}

	/**
	 * Controles pricipales de la interface de usuario
	 */
	private void controllersUI() {
		c.setLayout(null);
		c.setBackground(whiteColor);
		
		lbMyPullTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbMyPullTitle.setOpaque(true);
		lbMyPullTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbMyPullTitle.setBounds(0, 0, 320, 52);
		
		txtMyPulls.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		txtMyPulls.setBounds(0, 52, 320, 498);
		
		c.add(txtMyPulls);
		c.add(lbMyPullTitle);
	}
	
	/**
	 * Setter que permite cargar los mensajes
	 * de un usuario en la vista
	 */
	public void setLoadData() {
		
		MessageDao msgDao = new MessageDaoImp();
		List<Message> msgs = msgDao.findAllByUserId(session.getId());
		
		String mensajes = "";
		for (Message message : msgs) {
			mensajes += "  By " + session.getNombre() + " - " + message.getFecha() +"\n  "+ message.getMsg() + "\n\n\n";
		}
		
		txtMyPulls.setText(mensajes);
	}
	
	/**
	 * Método que permite obtener la instancia
	 * unica de la clase MyPullsUISingleton
	 * @return myPullsUISingleton
	 */
	public static MyPullsUISingleton getInstance() {
		return myPullsUISingleton;
	}
	
}
