package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.IntKeyLetras;
import gui.IntKeyNumeros;
import gui.IntLogin;
import main.Login;
import modelo.ModLogin;

public class ConLogin implements ActionListener,FocusListener,KeyListener {

	private IntLogin intLogin;
	private IntKeyLetras intKeyLetras;
	private IntKeyNumeros intKeyNumeros;
	private ModLogin modLogin;
	private Login login;
	private JTextField txtCampoGained = null;

	public ConLogin(IntLogin intLogin, IntKeyLetras intKeyLetras, IntKeyNumeros intKeyNumeros, ModLogin modLogin, Login login) {
		super();
		this.intLogin = intLogin;
		this.intKeyLetras = intKeyLetras;
		this.intKeyNumeros = intKeyNumeros;
		this.modLogin = modLogin;
		this.login = login;

		this.intLogin.getTxtUsuario().addFocusListener(this);
		this.intLogin.getPwdPassword().addFocusListener(this);

		this.intLogin.getTxtUsuario().addKeyListener(this);
		this.intLogin.getPwdPassword().addKeyListener(this);

		this.intLogin.getTxtUsuario().addActionListener(this);
		this.intLogin.getPwdPassword().addActionListener(this);

		this.intKeyLetras.getBtnQ().addActionListener(this);
		this.intKeyLetras.getBtnW().addActionListener(this);
		this.intKeyLetras.getBtnE().addActionListener(this);
		this.intKeyLetras.getBtnR().addActionListener(this);
		this.intKeyLetras.getBtnT().addActionListener(this);
		this.intKeyLetras.getBtnY().addActionListener(this);
		this.intKeyLetras.getBtnU().addActionListener(this);
		this.intKeyLetras.getBtnI().addActionListener(this);
		this.intKeyLetras.getBtnO().addActionListener(this);
		this.intKeyLetras.getBtnP().addActionListener(this);
		this.intKeyLetras.getBtnA().addActionListener(this);
		this.intKeyLetras.getBtnS().addActionListener(this);
		this.intKeyLetras.getBtnD().addActionListener(this);
		this.intKeyLetras.getBtnF().addActionListener(this);
		this.intKeyLetras.getBtnG().addActionListener(this);
		this.intKeyLetras.getBtnH().addActionListener(this);
		this.intKeyLetras.getBtnJ().addActionListener(this);
		this.intKeyLetras.getBtnK().addActionListener(this);
		this.intKeyLetras.getBtnL().addActionListener(this);
		this.intKeyLetras.getBtnEnne().addActionListener(this);
		this.intKeyLetras.getBtnZ().addActionListener(this);
		this.intKeyLetras.getBtnX().addActionListener(this);
		this.intKeyLetras.getBtnC().addActionListener(this);
		this.intKeyLetras.getBtnV().addActionListener(this);
		this.intKeyLetras.getBtnB().addActionListener(this);
		this.intKeyLetras.getBtnN().addActionListener(this);
		this.intKeyLetras.getBtnM().addActionListener(this);
		this.intKeyLetras.getBtnEspacio().addActionListener(this);
		this.intKeyLetras.getBtnPunto().addActionListener(this);
		this.intKeyLetras.getBtnQuion().addActionListener(this);
		this.intKeyLetras.getBtnPor().addActionListener(this);

		this.intKeyNumeros.getBtnSiete().addActionListener(this);
		this.intKeyNumeros.getBtnOcho().addActionListener(this);
		this.intKeyNumeros.getBtnNueve().addActionListener(this);
		this.intKeyNumeros.getBtnCuatro().addActionListener(this);
		this.intKeyNumeros.getBtnCinco().addActionListener(this);
		this.intKeyNumeros.getBtnSeis().addActionListener(this);
		this.intKeyNumeros.getBtnUno().addActionListener(this);
		this.intKeyNumeros.getBtnDos().addActionListener(this);
		this.intKeyNumeros.getBtnTres().addActionListener(this);
		this.intKeyNumeros.getBtnPunto().addActionListener(this);
		this.intKeyNumeros.getBtnMenos().addActionListener(this);
		this.intKeyNumeros.getBtnEnter().addActionListener(this);
		this.intKeyNumeros.getBtnPor().addActionListener(this);
		this.intKeyNumeros.getBtnSuprimir().addActionListener(this);
		this.intKeyNumeros.getBtnCero().addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==intKeyLetras.getBtnQ()){
			txtCampoGained.setText(txtCampoGained.getText()+"Q");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnW()) {
			txtCampoGained.setText(txtCampoGained.getText()+"W");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnE()) {
			txtCampoGained.setText(txtCampoGained.getText()+"E");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnR()) {
			txtCampoGained.setText(txtCampoGained.getText()+"R");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnT()) {
			txtCampoGained.setText(txtCampoGained.getText()+"T");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnY()) {
			txtCampoGained.setText(txtCampoGained.getText()+"Y");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnU()) {
			txtCampoGained.setText(txtCampoGained.getText()+"U");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnI()) {
			txtCampoGained.setText(txtCampoGained.getText()+"I");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnO()) {
			txtCampoGained.setText(txtCampoGained.getText()+"O");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnP()) {
			txtCampoGained.setText(txtCampoGained.getText()+"P");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnA()) {
			txtCampoGained.setText(txtCampoGained.getText()+"A");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnS()) {
			txtCampoGained.setText(txtCampoGained.getText()+"S");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnD()) {
			txtCampoGained.setText(txtCampoGained.getText()+"D");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnF()) {
			txtCampoGained.setText(txtCampoGained.getText()+"F");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnG()) {
			txtCampoGained.setText(txtCampoGained.getText()+"G");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnH()) {
			txtCampoGained.setText(txtCampoGained.getText()+"H");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnJ()) {
			txtCampoGained.setText(txtCampoGained.getText()+"J");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnK()) {
			txtCampoGained.setText(txtCampoGained.getText()+"K");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnL()) {
			txtCampoGained.setText(txtCampoGained.getText()+"L");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnEnne()) {
			txtCampoGained.setText(txtCampoGained.getText()+"Ñ");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnZ()) {
			txtCampoGained.setText(txtCampoGained.getText()+"Z");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnX()) {
			txtCampoGained.setText(txtCampoGained.getText()+"X");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnC()) {
			txtCampoGained.setText(txtCampoGained.getText()+"C");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnV()) {
			txtCampoGained.setText(txtCampoGained.getText()+"V");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnB()) {
			txtCampoGained.setText(txtCampoGained.getText()+"B");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnN()) {
			txtCampoGained.setText(txtCampoGained.getText()+"N");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnM()) {
			txtCampoGained.setText(txtCampoGained.getText()+"M");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnEspacio()) {
			txtCampoGained.setText(txtCampoGained.getText()+" ");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnPunto()) {
			txtCampoGained.setText(txtCampoGained.getText()+".");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnQuion()) {
			txtCampoGained.setText(txtCampoGained.getText()+"-");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyLetras.getBtnPor()) {
			txtCampoGained.setText(txtCampoGained.getText()+"_");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnSiete()) {
			txtCampoGained.setText(txtCampoGained.getText()+"7");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnOcho()) {
			txtCampoGained.setText(txtCampoGained.getText()+"8");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnNueve()) {
			txtCampoGained.setText(txtCampoGained.getText()+"9");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnCuatro()) {
			txtCampoGained.setText(txtCampoGained.getText()+"4");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnCinco()) {
			txtCampoGained.setText(txtCampoGained.getText()+"5");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnSeis()) {
			txtCampoGained.setText(txtCampoGained.getText()+"6");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnUno()) {
			txtCampoGained.setText(txtCampoGained.getText()+"1");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnDos()) {
			txtCampoGained.setText(txtCampoGained.getText()+"2");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnTres()) {
			txtCampoGained.setText(txtCampoGained.getText()+"3");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnPunto()) {
			txtCampoGained.setText(txtCampoGained.getText()+".");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnCero()) {
			txtCampoGained.setText(txtCampoGained.getText()+"0");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnMenos()) {
			txtCampoGained.setText(txtCampoGained.getText()+"-");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnPor()) {
			txtCampoGained.setText(txtCampoGained.getText()+"*");
			txtCampoGained.requestFocus();
		}else if(e.getSource()==intKeyNumeros.getBtnEnter()) {
			iniciarPrograma();					
						
		}else if(e.getSource()==intKeyNumeros.getBtnSuprimir()) {
			//txtCampoGained.setText("");
			int tamanoCampo = txtCampoGained.getText().length();
			if(tamanoCampo!=0) {
				String textoCampo = txtCampoGained.getText();
				textoCampo = textoCampo.substring(0, tamanoCampo-1);
				txtCampoGained.setText(textoCampo);
			}
			txtCampoGained.requestFocus();
		}

	}
	
	private void iniciarPrograma() {
		//System.out.println("ConLogin.actionPerformed() le dio a aceptar");
		String sPassword = new String(intLogin.getPwdPassword().getPassword());
		if(intLogin.getTxtUsuario().getText().equals("")||sPassword.equals("")){
			JOptionPane.showMessageDialog(null, "Usuario y/o password erroneos.");
		}else{
			establecerBD();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			iniciarPrograma();
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		txtCampoGained = (JTextField) e.getSource();
		intLogin.getTxtUsuario().setBackground(Color.WHITE);
		intLogin.getPwdPassword().setBackground(Color.WHITE);
		if(e.getSource() == intLogin.getTxtUsuario()){
			intLogin.getTxtUsuario().setBackground(Color.YELLOW);
		}else if(e.getSource() == intLogin.getPwdPassword()){
			intLogin.getPwdPassword().setBackground(Color.YELLOW);
		}
	}
	
	@Override
	public void focusLost(FocusEvent arg0) {}

	public void establecerBD() {
		modLogin.iniciarLicencia();
	}
	public void traerResultadoLicencia(boolean estadoLicencia) {
		if(estadoLicencia){
			modLogin.consultarUsuario(intLogin.getTxtUsuario().getText(),intLogin.getPwdPassword().getPassword());
		}else{
			JOptionPane.showMessageDialog(null, "Error en la licencia, comuniquese con el administrador. \nNo tiene concexión a la base de datos");
		}
	}
	
	public void cerrarIntLogin() {
		login.dispose();
	}
	
	public IntLogin getIntLogin() {
		return intLogin;
	}
	public void setIntLogin(IntLogin intLogin) {
		this.intLogin = intLogin;
	}
	public IntKeyLetras getIntKeyLetras() {
		return intKeyLetras;
	}
	public void setIntKeyLetras(IntKeyLetras intKeyLetras) {
		this.intKeyLetras = intKeyLetras;
	}
	public IntKeyNumeros getIntKeyNumeros() {
		return intKeyNumeros;
	}
	public void setIntKeyNumeros(IntKeyNumeros intKeyNumeros) {
		this.intKeyNumeros = intKeyNumeros;
	}
	public ModLogin getModLogin() {
		return modLogin;
	}
	public void setModLogin(ModLogin modLogin) {
		this.modLogin = modLogin;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}	
}
