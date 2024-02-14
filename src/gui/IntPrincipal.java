package gui;

import javax.swing.JPanel;

import controlador.ConPrincipal;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridLayout;

public class IntPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConPrincipal conPrincipal;
	private static final int tLetra = 20;
	
	private JButton btnFactura;
	private JButton btnPagos;
	private JButton btnReporte;
	private JButton btnClave;
	private JButton btnConfiguracion;
	private JButton btnDevolución;
	private JButton btnRetiro;
	private JButton btnSalida;

	public IntPrincipal() {
		setLayout(new GridLayout(1, 0, 2, 5));
		btnFactura = new JButton("Factura");
		btnFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnFactura.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 20)));
		add(btnFactura);
		
		btnPagos = new JButton("Pagos");
		btnPagos.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnPagos.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnPagos);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnReporte.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnReporte);
		
		/*btnClave = new JButton("");
		btnClave.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnClave.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnClave);*/
		
		btnConfiguracion = new JButton("Configuración");
		btnConfiguracion.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnConfiguracion.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnConfiguracion);
		
		btnDevolución = new JButton("Devolución");
		btnDevolución.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnDevolución.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnDevolución);
		
		/*btnRetiro = new JButton("");
		btnRetiro.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnRetiro.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnRetiro);*/
		
		btnSalida = new JButton("Salida");
		btnSalida.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		btnSalida.setBorder(BorderFactory.createCompoundBorder(
	               BorderFactory.createLineBorder(null, 1),
	               BorderFactory.createEmptyBorder(5, 5, 10, 10)));
		add(btnSalida);

	}

	public ConPrincipal getConPrincipal() {
		return conPrincipal;
	}

	public void setConPrincipal(ConPrincipal conPrincipal) {
		this.conPrincipal = conPrincipal;
	}

	public JButton getBtnFactura() {
		return btnFactura;
	}

	public void setBtnFactura(JButton btnFactura) {
		this.btnFactura = btnFactura;
	}

	public JButton getBtnPagos() {
		return btnPagos;
	}

	public void setBtnPagos(JButton btnPagos) {
		this.btnPagos = btnPagos;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public void setBtnReporte(JButton btnReporte) {
		this.btnReporte = btnReporte;
	}

	public JButton getBtnClave() {
		return btnClave;
	}

	public void setBtnClave(JButton btnClave) {
		this.btnClave = btnClave;
	}

	public JButton getBtnConfiguracion() {
		return btnConfiguracion;
	}

	public void setBtnConfiguracion(JButton btnConfiguracion) {
		this.btnConfiguracion = btnConfiguracion;
	}

	public JButton getBtnDevolución() {
		return btnDevolución;
	}

	public void setBtnDevolución(JButton btnDevolución) {
		this.btnDevolución = btnDevolución;
	}

	public JButton getBtnRetiro() {
		return btnRetiro;
	}

	public void setBtnRetiro(JButton btnRetiro) {
		this.btnRetiro = btnRetiro;
	}

	public JButton getBtnSalida() {
		return btnSalida;
	}

	public void setBtnSalida(JButton btnSalida) {
		this.btnSalida = btnSalida;
	}
	
}
