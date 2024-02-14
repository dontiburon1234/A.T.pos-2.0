package gui;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class IntPagosBotones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnGuardar;
	private JButton btnReporte;
	private JButton btnImprimirPago;
	private JButton btnEliminarPago;
	private JButton btnBuscarProveedor;
	private static final int tLetra = 17;
	
	public IntPagosBotones() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Border paneEdge = BorderFactory.createLineBorder(Color.GRAY);
		
		btnBuscarProveedor = new JButton("BuscarProveedor");
		btnBuscarProveedor.setBorder(paneEdge);
		btnBuscarProveedor.setPreferredSize(new Dimension(150,50));
		btnBuscarProveedor.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnBuscarProveedor);
		
		btnEliminarPago = new JButton("EliminarPago");
		btnEliminarPago.setBorder(paneEdge);
		btnEliminarPago.setPreferredSize(new Dimension(150,50));
		btnEliminarPago.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnEliminarPago);
		
		btnImprimirPago = new JButton("ImprimirPago");
		btnImprimirPago.setBorder(paneEdge);
		btnImprimirPago.setPreferredSize(new Dimension(150,50));
		btnImprimirPago.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnImprimirPago);
		
		btnReporte = new JButton("ImprimirReporte");
		btnReporte.setBorder(paneEdge);
		btnReporte.setPreferredSize(new Dimension(150,50));
		btnReporte.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnReporte);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBorder(paneEdge);
		btnGuardar.setPreferredSize(new Dimension(150,50));
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnGuardar);
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public void setBtnReporte(JButton btnReporte) {
		this.btnReporte = btnReporte;
	}

	public JButton getBtnImprimirPago() {
		return btnImprimirPago;
	}

	public void setBtnImprimirPago(JButton btnImprimirPago) {
		this.btnImprimirPago = btnImprimirPago;
	}

	public JButton getBtnEliminarPago() {
		return btnEliminarPago;
	}

	public void setBtnEliminarPago(JButton btnEliminarPago) {
		this.btnEliminarPago = btnEliminarPago;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}

}
