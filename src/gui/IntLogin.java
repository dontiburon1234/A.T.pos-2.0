package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.SwingConstants;

import controlador.ConLogin;

public class IntLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConLogin conLogin;
	private JTextField txtUsuario;
	private JPasswordField pwdPassword;

	public IntLogin() {
		setLayout(null);
		
		JLabel lblTitulologin = new JLabel("Ingreso");
		lblTitulologin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulologin.setBounds(134, 36, 112, 31);
		lblTitulologin.setFont(new Font("Tahoma",Font.BOLD,25));
		add(lblTitulologin);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 108, 154, 31);
		lblUsuario.setFont(new Font("Tahoma",Font.BOLD,30));
		add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 182, 154, 31);
		lblPassword.setFont(new Font("Tahoma",Font.BOLD,30));
		add(lblPassword);
		
		txtUsuario = new JTextField("g.conde");
		txtUsuario.setBounds(195, 108, 170, 40);
		txtUsuario.setFont(new Font("Tahoma",Font.PLAIN,25));
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pwdPassword = new JPasswordField("123");
		pwdPassword.setBounds(195, 182, 170, 40);
		pwdPassword.setFont(new Font("Tahoma",Font.PLAIN,25));
		add(pwdPassword);
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JPasswordField getPwdPassword() {
		return pwdPassword;
	}

	public void setPwdPassword(JPasswordField pwdPassword) {
		this.pwdPassword = pwdPassword;
	}

	public ConLogin getConLogin() {
		return conLogin;
	}

	public void setConLogin(ConLogin conLogin) {
		this.conLogin = conLogin;
	}
}
