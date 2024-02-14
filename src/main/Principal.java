package main;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controlador.ConPrincipal;
import gui.IntPrincipal;
import modelo.ModPrincipal;

import javax.swing.JMenuBar;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setTitle("ATpos - Administraci\u00F3n Total del punto de venta - Touch 2.010.3");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\tmp\\lic\\serv\\Logo-ATpos-200px.png"));
		//TODO definir diferentes tamaños y resoluciones de pantalla
		//setBounds(0, 0, 1366, 768); 
		// pantalla distribell setBounds(0, 0, 1366, 768); 
		// pantalla carlos setBounds(1366, 0, 1024, 768);  
//		 pantalla televisor SONY setBounds(1366, 0, 1280, 720);
		// pantalla Android Lenovo Pantalla: 7.0 INCH 1024*600
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Declaramos las clases
		ModPrincipal modPrincipal;
		IntPrincipal intPrincipal;
		ConPrincipal conPrincipal;

		// Instanciamos las clases (crear los objetos de las clases)
		modPrincipal = new ModPrincipal();
		intPrincipal = new IntPrincipal();
		conPrincipal = new ConPrincipal(intPrincipal,modPrincipal,this);

		// Establecemos las relaciones entre clases
		modPrincipal.setConPrincipal(conPrincipal);
		intPrincipal.setConPrincipal(conPrincipal);

		// Enviarle una instancia de cada clases al coordinador
		conPrincipal.setModPrincipal(modPrincipal);
		conPrincipal.setIntPrincipal(intPrincipal);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(intPrincipal);
		setJMenuBar(menuBar);
	}

}
