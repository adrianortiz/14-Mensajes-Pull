

import com.codizer.view.LoginUISingleton;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class Init {

	/**
	 * Aplicación de pull
	 * @param args
	 */
	public static void main(String[] args) {
		LoginUISingleton loginUI = LoginUISingleton.getInstance();
		loginUI.setVisible(true);
	}
}