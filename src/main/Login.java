package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.pos.apl.Exportador;
import com.pos.apl.SincronizacionGral;
import com.pos.apl.SincronizadorArticulo;

import controlador.ConLogin;
import gui.IntKeyLetras;
import gui.IntKeyNumeros;
import gui.IntLogin;
import modelo.ModLogin;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	SincronizacionGral sincronizacionGral;
	SincronizadorArticulo sincronizadorArticulo;
	Exportador exportador;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 */
	public Login() {
		Toolkit tamano = Toolkit.getDefaultToolkit();
		int ancho = (int) ((tamano.getScreenSize().getWidth() - 718) / 2);
		int alto = (int) ((tamano.getScreenSize().getHeight() - 570) / 2);

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\tmp\\lic\\serv\\Logo-ATpos-200px.png"));

		setTitle("A.T.pos - Administraci\u00F3n Total del punto de venta");
		setBounds((ancho), alto, 718, 570);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // TODO (WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(true);

		// Declaramos las clases y las instanciamos
		IntLogin intLogin = new IntLogin();
		IntKeyLetras intKeyLetras = new IntKeyLetras();
		IntKeyNumeros intKeyNumeros = new IntKeyNumeros();
		ModLogin modLogin = new ModLogin();
		ConLogin conLogin = new ConLogin(intLogin, intKeyLetras, intKeyNumeros, modLogin, this);

		// Establecemos las relaciones entre clases
		intLogin.setConLogin(conLogin);
		intKeyLetras.setConLogin(conLogin);
		intKeyNumeros.setConLogin(conLogin);
		modLogin.setConLogin(conLogin);

		// Enviarle una instancia de cada clases al coordinador
		conLogin.setIntLogin(intLogin);
		conLogin.setIntKeyLetras(intKeyLetras);
		conLogin.setIntKeyNumeros(intKeyNumeros);
		conLogin.setModLogin(modLogin);

		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(intLogin, BorderLayout.CENTER);
		getContentPane().add(intKeyNumeros, BorderLayout.LINE_END);
		getContentPane().add(intKeyLetras, BorderLayout.PAGE_END);

		// Inicia sincronizador
//		sincronizacionGral = new SincronizacionGral();
//		sincronizacionGral.start();
//		sincronizadorArticulo = new SincronizadorArticulo();
//		sincronizadorArticulo.start();
//		exportador = new Exportador();
//		exportador.start();
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = Login.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.out.println("Login.createImageIcon()" + "Couldn't find file: " + path);
			return null;
		}
	}

}
