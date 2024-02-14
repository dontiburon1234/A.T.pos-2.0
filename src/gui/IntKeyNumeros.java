package gui;

import javax.swing.JPanel;
import javax.swing.border.Border;

import controlador.ConLogin;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntKeyNumeros extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConLogin conLogin;
	private Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	private static final int pady = 20; //Specifies the internal padding: how much to add to the size of the component.
	private static final int padx = 45;
	private static final int tLetra = 40;
	private static final int tLetraText = 20;
	private Color colorNumeros = new Color(94,194,67); //Color.decode("#FFA200");
	private JButton btnSiete;
	private JButton btnOcho;
	private JButton btnNueve;
	private JButton btnCuatro;
	private JButton btnCinco;
	private JButton btnSeis;
	private JButton btnUno;
	private JButton btnDos;
	private JButton btnTres;
	private JButton btnCero;
	private JButton btnPunto;
	private JButton btnMenos;
	private JButton btnPor;
	private JButton btnSuprimir;
	private JButton btnEnter;
	
	public IntKeyNumeros() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnSiete = new JButton("7");
		btnSiete.setBackground(colorNumeros);
		btnSiete.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnSiete.setBorder(raisedbevel);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(5, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		gbc_button.ipadx = padx;
		gbc_button.ipady = pady;
		gbc_button.fill = GridBagConstraints.BOTH;
		add(btnSiete, gbc_button);
		
		btnOcho = new JButton("8");
		btnOcho.setBackground(colorNumeros);
		btnOcho.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnOcho.setBorder(raisedbevel);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(5, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		gbc_button_1.ipadx = padx;
		gbc_button_1.ipady = pady;
		gbc_button_1.fill = GridBagConstraints.BOTH;
		add(btnOcho, gbc_button_1);
		
		btnNueve = new JButton("9");
		btnNueve.setBackground(colorNumeros);
		btnNueve.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnNueve.setBorder(raisedbevel);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(5, 0, 5, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 0;
		gbc_button_2.ipadx = padx;
		gbc_button_2.ipady = pady;
		gbc_button_2.fill = GridBagConstraints.BOTH;
		add(btnNueve, gbc_button_2);
		
		btnPor = new JButton("*");
		btnPor.setBackground(colorNumeros);
		btnPor.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnPor.setBorder(raisedbevel);
		GridBagConstraints gbc_button_12 = new GridBagConstraints();
		gbc_button_12.fill = GridBagConstraints.BOTH;
		gbc_button_12.insets = new Insets(5, 0, 5, 5);
		gbc_button_12.gridx = 3;
		gbc_button_12.gridy = 0;
		gbc_button_12.ipadx = padx;
		gbc_button_12.ipady = pady;
		gbc_button_12.fill = GridBagConstraints.BOTH;
		add(btnPor, gbc_button_12);
		
		btnCuatro = new JButton("4");
		btnCuatro.setBackground(colorNumeros);
		btnCuatro.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnCuatro.setBorder(raisedbevel);
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 0;
		gbc_button_3.gridy = 1;
		gbc_button_3.ipadx = padx;
		gbc_button_3.ipady = pady;
		gbc_button_3.fill = GridBagConstraints.BOTH;
		add(btnCuatro, gbc_button_3);
		
		btnCinco = new JButton("5");
		btnCinco.setBackground(colorNumeros);
		btnCinco.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnCinco.setBorder(raisedbevel);
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 1;
		gbc_button_4.gridy = 1;
		gbc_button_4.ipadx = padx;
		gbc_button_4.ipady = pady;
		gbc_button_4.fill = GridBagConstraints.BOTH;
		add(btnCinco, gbc_button_4);
		
		btnSeis = new JButton("6");
		btnSeis.setBackground(colorNumeros);
		btnSeis.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnSeis.setBorder(raisedbevel);
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 2;
		gbc_button_5.gridy = 1;
		gbc_button_5.ipadx = padx;
		gbc_button_5.ipady = pady;
		gbc_button_5.fill = GridBagConstraints.BOTH;
		add(btnSeis, gbc_button_5);
		
		btnSuprimir = new JButton("Supr");
		btnSuprimir.setBackground(new Color(255,0,0));
		btnSuprimir.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		btnSuprimir.setBorder(raisedbevel);
		GridBagConstraints gbc_btnSpr = new GridBagConstraints();
		gbc_btnSpr.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpr.gridx = 3;
		gbc_btnSpr.gridy = 1;
		gbc_btnSpr.ipadx = padx;
		gbc_btnSpr.ipady = pady;
		gbc_btnSpr.fill = GridBagConstraints.BOTH;
		add(btnSuprimir, gbc_btnSpr);
		
		btnUno = new JButton("1");
		btnUno.setBackground(colorNumeros);
		btnUno.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnUno.setBorder(raisedbevel);
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 0;
		gbc_button_6.gridy = 2;
		gbc_button_6.ipadx = padx;
		gbc_button_6.ipady = pady;
		gbc_button_6.fill = GridBagConstraints.BOTH;
		add(btnUno, gbc_button_6);
		
		btnDos = new JButton("2");
		btnDos.setBackground(colorNumeros);
		btnDos.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnDos.setBorder(raisedbevel);
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 1;
		gbc_button_7.gridy = 2;
		gbc_button_7.ipadx = padx;
		gbc_button_7.ipady = pady;
		gbc_button_7.fill = GridBagConstraints.BOTH;
		add(btnDos, gbc_button_7);
		
		btnTres = new JButton("3");
		btnTres.setBackground(colorNumeros);
		btnTres.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnTres.setBorder(raisedbevel);
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 2;
		gbc_button_8.gridy = 2;
		gbc_button_8.ipadx = padx;
		gbc_button_8.ipady = pady;
		gbc_button_8.fill = GridBagConstraints.BOTH;
		add(btnTres, gbc_button_8);
		
		btnEnter = new JButton("Intro");
		//btnEnter.setBackground(new Color(102,204,0));
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		btnEnter.setBorder(raisedbevel);
		GridBagConstraints gbc_btnEnt = new GridBagConstraints();
		gbc_btnEnt.gridheight = 2;
		gbc_btnEnt.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnt.gridx = 3;
		gbc_btnEnt.gridy = 2;
		gbc_btnEnt.ipadx = padx;
		gbc_btnEnt.ipady = pady;
		gbc_btnEnt.fill = GridBagConstraints.BOTH;
		add(btnEnter, gbc_btnEnt);
		
		btnCero = new JButton("0");
		btnCero.setBackground(colorNumeros);
		btnCero.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnCero.setBorder(raisedbevel);
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.insets = new Insets(0, 0, 0, 5);
		gbc_button_9.gridx = 0;
		gbc_button_9.gridy = 3;
		gbc_button_9.ipadx = padx;
		gbc_button_9.ipady = pady;
		gbc_button_9.fill = GridBagConstraints.BOTH;
		add(btnCero, gbc_button_9);
		
		btnPunto = new JButton(".");
		btnPunto.setBackground(colorNumeros);
		btnPunto.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnPunto.setBorder(raisedbevel);
		GridBagConstraints gbc_button_10 = new GridBagConstraints();
		gbc_button_10.insets = new Insets(0, 0, 0, 5);
		gbc_button_10.gridx = 1;
		gbc_button_10.gridy = 3;
		gbc_button_10.ipadx = padx;
		gbc_button_10.ipady = pady;
		gbc_button_10.fill = GridBagConstraints.BOTH;
		add(btnPunto, gbc_button_10);
		
		btnMenos = new JButton("-");
		btnMenos.setBackground(colorNumeros);
		btnMenos.setFont(new Font("Tahoma", Font.BOLD, tLetra));
		btnMenos.setBorder(raisedbevel);
		GridBagConstraints gbc_button_11 = new GridBagConstraints();
		gbc_button_11.insets = new Insets(0, 0, 0, 5);
		gbc_button_11.gridx = 2;
		gbc_button_11.gridy = 3;
		gbc_button_11.ipadx = padx;
		gbc_button_11.ipady = pady;
		gbc_button_11.fill = GridBagConstraints.BOTH;
		add(btnMenos, gbc_button_11);
	}

	public ConLogin getConLogin() {
		return conLogin;
	}

	public void setConLogin(ConLogin conLogin) {
		this.conLogin = conLogin;
	}

	public JButton getBtnSiete() {
		return btnSiete;
	}

	public void setBtnSiete(JButton btnSiete) {
		this.btnSiete = btnSiete;
	}

	public JButton getBtnOcho() {
		return btnOcho;
	}

	public void setBtnOcho(JButton btnOcho) {
		this.btnOcho = btnOcho;
	}

	public JButton getBtnNueve() {
		return btnNueve;
	}

	public void setBtnNueve(JButton btnNueve) {
		this.btnNueve = btnNueve;
	}

	public JButton getBtnCuatro() {
		return btnCuatro;
	}

	public void setBtnCuatro(JButton btnCuatro) {
		this.btnCuatro = btnCuatro;
	}

	public JButton getBtnCinco() {
		return btnCinco;
	}

	public void setBtnCinco(JButton btnCinco) {
		this.btnCinco = btnCinco;
	}

	public JButton getBtnSeis() {
		return btnSeis;
	}

	public void setBtnSeis(JButton btnSeis) {
		this.btnSeis = btnSeis;
	}

	public JButton getBtnUno() {
		return btnUno;
	}

	public void setBtnUno(JButton btnUno) {
		this.btnUno = btnUno;
	}

	public JButton getBtnDos() {
		return btnDos;
	}

	public void setBtnDos(JButton btnDos) {
		this.btnDos = btnDos;
	}

	public JButton getBtnTres() {
		return btnTres;
	}

	public void setBtnTres(JButton btnTres) {
		this.btnTres = btnTres;
	}

	public JButton getBtnCero() {
		return btnCero;
	}

	public void setBtnCero(JButton btnCero) {
		this.btnCero = btnCero;
	}

	public JButton getBtnPunto() {
		return btnPunto;
	}

	public void setBtnPunto(JButton btnPunto) {
		this.btnPunto = btnPunto;
	}

	public JButton getBtnMenos() {
		return btnMenos;
	}

	public void setBtnMenos(JButton btnMenos) {
		this.btnMenos = btnMenos;
	}

	public JButton getBtnPor() {
		return btnPor;
	}

	public void setBtnPor(JButton btnPor) {
		this.btnPor = btnPor;
	}

	public JButton getBtnSuprimir() {
		return btnSuprimir;
	}

	public void setBtnSuprimir(JButton btnSuprimir) {
		this.btnSuprimir = btnSuprimir;
	}

	public JButton getBtnEnter() {
		return btnEnter;
	}

	public void setBtnEnter(JButton btnEnter) {
		this.btnEnter = btnEnter;
	}

	
}
