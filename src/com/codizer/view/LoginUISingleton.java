package com.codizer.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.codizer.model.UserDao;
import com.codizer.model.UserDaoImp;
import com.codizer.model.UserSessionSingleton;
import com.codizer.pojo.User;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class LoginUISingleton extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	private static final LoginUISingleton loginUISingleton = new LoginUISingleton();
	private UserSessionSingleton session = UserSessionSingleton.getUsersession();
	
	private ImageIcon image = new ImageIcon("../14-Mensajes-Pull/src/com/codizer/media/codizer.png");
	private JLabel imgLogin = new JLabel(image);
	
	private JLabel lbLogin = new JLabel("LOGIN");
	private JLabel lbUserHover = new JLabel("Nombre de usuario");
	private JTextField txtUser = new JTextField();
	private JButton btnOK = new JButton("OK");

	private Container c = getContentPane();
	
	private Color whiteColor = new Color(255, 255, 255);
	
    /**
     * Contructor privado que evitar la creación
     * de nuevas instancias de esta clase.
     */
	private LoginUISingleton() {
		super.setTitle("Login");
		super.setSize(320, 550);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame.HIDE_ON_CLOSE
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		controllersUI();
	}
	
	/**
	 * Controles pricipales de la interface de usuario
	 */
	private void controllersUI() {
		c.setLayout(null);
		c.setBackground(whiteColor);
		
		imgLogin.setBounds(60, 70, 185, 185);
		
		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogin.setOpaque(true);
		lbLogin.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbLogin.setBounds(0, 0, 320, 52);
		
		txtUser.setBounds(14, 300, 290, 35);
		lbUserHover.setBounds(22, 300, 290, 35);
		
		btnOK.setBounds(14, 350, 290, 35);
		
		c.add(imgLogin);
		c.add(lbLogin);
		c.add(lbUserHover);
		c.add(txtUser);
		c.add(btnOK);
		
		txtUser.addFocusListener(this);
		btnOK.addActionListener(this);
		
	}
	
	/**
	 * Controller para el login de un usuario
	 * existente en la base de datos
	 * de lo contrario lo registra.
	 * @param userName
	 */
	public void login(String userName) {
		UserDao userDao = new UserDaoImp();
		User user = new User();
		
		user.setNombre(userName);
		
		if (userDao.login(user)) {
			System.out.println("Usuario volvio :) ");
			session.setSession(true);
			createSession(userName);
		} else {
			if (userDao.save(user)) {
				System.out.println("Nuevo usuario :) ");
				session.setSession(true);
				createSession(userName);
			} else {
				session.setSession(false);
				System.out.println("Algo salio mal :( ");
			}
		}
	}
	
	/**
	 * Controller que permite cargar la interfaz de usuario
	 * de mensajes y los mensajes que pertenecen
	 * al mismo.
	 * @param userName
	 */
	public void createSession(String userName) {
		if ( session.isSession() ) {
			UserDao userDao = new UserDaoImp();
			User user = userDao.findByName(userName);
			
			if (user != null) {
				System.out.println(user.toString());
				loginUISingleton.setVisible(false);
				session.setId(user.getId());
				session.setNombre(user.getNombre());
				
				PullUISingleton pullUISingleton = PullUISingleton.getInstance();
				pullUISingleton.setlbPullTitleText("Welcome " + user.getNombre());
				pullUISingleton.setLoadData();
				pullUISingleton.setVisible(true);
				
				MyPullsUISingleton myPullsUISingleton = MyPullsUISingleton.getInstance();
				myPullsUISingleton.setLoadData();
				myPullsUISingleton.setVisible(true);
				
			}
			
			System.out.println("Session activa");
		} else {
			System.out.println("Sessión inactiva :( ");
		}
	}
	
	/**
	 * Método que permite obtener la instancia
	 * unica de la clase LoginUISingleton
	 * @return loginUISingleton
	 */
	public static LoginUISingleton getInstance() {
		return loginUISingleton;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String userName = txtUser.getText();
		
		if (e.getSource() == btnOK) {
			
			if(userName.equals(null) || userName.equals("")) {
				userName = "Anónimo";
				login(userName);
			} else {
				login(userName);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == txtUser && txtUser.getText().isEmpty())
			lbUserHover.setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == txtUser && txtUser.getText().isEmpty())
			lbUserHover.setVisible(true);
	}
}
