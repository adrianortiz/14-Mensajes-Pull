package com.codizer.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.codizer.model.MessageDao;
import com.codizer.model.MessageDaoImp;
import com.codizer.model.UserDao;
import com.codizer.model.UserDaoImp;
import com.codizer.model.UserSessionSingleton;
import com.codizer.pojo.Message;
import com.codizer.pojo.User;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class PullUISingleton extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final PullUISingleton pullUISingleton = new PullUISingleton();
	private UserSessionSingleton session = UserSessionSingleton.getUsersession();
	
	private JLabel lbPullTitle = new JLabel("Welcome " + session.getNombre());
	private TextArea txtPulls = new TextArea();
	private TextField txtMsg = new TextField();
	private Button btnMsg = new Button("Enviar");
	
	private Container c = getContentPane();
	
	private Color whiteColor = new Color(255, 255, 255);
	
    /**
     * Contructor privado que evita crear nuevas
     * instancias de esta clase.
     */
	private PullUISingleton() {
		super.setTitle("Welcome to Pulls");
		super.setSize(320, 550);
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MyPullsUISingleton myPullsUISingleton = MyPullsUISingleton.getInstance();
				myPullsUISingleton.setVisible(false);
				
				LoginUISingleton loginUI = LoginUISingleton.getInstance();
				loginUI.setVisible(true);
				
				session.setSession(false);
			}
		});
		
		controllersUI();
	}

	/**
     * Contructor privado que evita crear nuevas
     * instancias de esta clase.
     */
	private void controllersUI() {
		c.setLayout(null);
		c.setBackground(whiteColor);
		
		lbPullTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbPullTitle.setOpaque(true);
		lbPullTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbPullTitle.setBounds(0, 0, 320, 52);
		
		txtPulls.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		txtPulls.setBounds(0, 52, 320, 420);
		
		txtMsg.setBounds(10, 480, 230, 35);
		btnMsg.setBounds(250, 476, 60, 40);
		
		c.add(txtPulls);
		c.add(lbPullTitle);
		c.add(btnMsg);
		c.add(txtMsg);
		
		btnMsg.addActionListener(this);
	}
	
	/**
	 * Setter que permite cargar los mensajes
	 * de todos los usuarios en la vista
	 */
	public void setLoadData() {
		UserDao userDao = new UserDaoImp();
		List<User> users = userDao.findAll();
		
		MessageDao msgDao = new MessageDaoImp();
		List<Message> msgs = msgDao.findAll();
		
		String userName = "";
		String mensajes = "";
		for (Message message : msgs) {
			
			for (User user : users) {
				if (user.getId() == message.getUserId())
					userName = user.getNombre();
			}
			mensajes += "  By " + userName + " - " + message.getFecha() +"\n  "+ message.getMsg() + "\n\n\n";
		}
		txtPulls.setText(mensajes);
	}

	/**
	 * Método que permite obtener la instancia
	 * unica de la clase PullUISingleton
	 * @return pullUISingleton
	 */
	public static PullUISingleton getInstance() {
		return pullUISingleton;
	}
	
	/**
	 * Setter para cambiar el nombre de
	 * lbPullTitle mediante texto.
	 * @param s String
	 */
	public void setlbPullTitleText(String s) {
		this.lbPullTitle.setText(s);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMsg && !txtMsg.getText().equals("")) {
			Timestamp ts = new Timestamp(new Date().getTime());
			
			MessageDao msgDao = new MessageDaoImp();
			Message msg = new Message();
			
			msg.setFecha(ts);
			msg.setMsg(txtMsg.getText());
			msg.setUserId(session.getId());
			
			if (msgDao.save(msg)) {
				txtMsg.setText("");
				setLoadData();
				
				MyPullsUISingleton myPullsUISingleton = MyPullsUISingleton.getInstance();
				myPullsUISingleton.setLoadData();
				
			} else {
				System.out.println("Paso un error :(");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, escribe algo genial! :) ");
		}
	}
	
}
