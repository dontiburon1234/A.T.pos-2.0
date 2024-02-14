package modelo;

import javax.swing.JOptionPane;

import controlador.ConLogin;
import database.MaestroDB;
import main.Principal;
import util.CargarLogo;
import util.G;

public class ModLogin {

	private ConLogin conLogin;
	private MaestroDB maestroDB;
	private Principal principal;

	public ModLogin() {
		super();
	}

	public void iniciarLicencia() {
		maestroDB = new MaestroDB();
		if(maestroDB.cargarLicencia()){
			conLogin.traerResultadoLicencia(true);
			new CargarLogo(maestroDB);
			CargarLogo.logo();
		}else{
			// enviar mensaje al usuario del problema
			conLogin.traerResultadoLicencia(false);
		}
	}
	public void consultarUsuario(String usuario, char[] password) {
		if(maestroDB.consultaCajero(usuario, password)){
			conLogin.cerrarIntLogin();
			principal = new Principal();
			principal.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Usuario y/o password erroneos.");
			//System.out.println("el usuario no es cajero y/o con clave equivocada");
		}
	}
	
	public ConLogin getConLogin() {
		return conLogin;
	}

	public void setConLogin(ConLogin conLogin) {
		this.conLogin = conLogin;
	}
	
}
