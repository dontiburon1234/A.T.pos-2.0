package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;

public class IntDevolucionesBotones extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int tLetra = 17;
	private JButton btnImprimeDevolucion; 
	private JButton btnImprimeReporte;
	private JButton btnDevolver; 
	private JButton btnRetirarArticulo;
	private JButton btnImprimeFactura;

	public IntDevolucionesBotones() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnImprimeFactura = new JButton("ImpFactura");
		btnImprimeFactura.setPreferredSize(new Dimension(150,50));
		btnImprimeFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnImprimeFactura);
		
		btnRetirarArticulo = new JButton("Limpiar"); // TODO cambiarlo por limpiar pantalla 
		btnRetirarArticulo.setPreferredSize(new Dimension(150,50));
		btnRetirarArticulo.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		//add(btnRetirarArticulo);
		
		btnImprimeDevolucion = new JButton("ImpDevoluci\u00F3n");
		btnImprimeDevolucion.setPreferredSize(new Dimension(150,50));
		btnImprimeDevolucion.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		//add(btnImprimeDevolucion);
		
		btnImprimeReporte = new JButton("ImpReporte");
		btnImprimeReporte.setPreferredSize(new Dimension(150,50));
		btnImprimeReporte.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		//add(btnImprimeReporte);
		
		btnDevolver = new JButton("Devolver"); 
		btnDevolver.setPreferredSize(new Dimension(150,50)); 
		btnDevolver.setFont(new Font("Tahoma", Font.PLAIN,tLetra)); 
		//add(btnDevolver);
		 

	}

	public JButton getBtnImprimeDevolucion() { 
		return btnImprimeDevolucion; 
	}
	
	public void setBtnImprimeDevolucion(JButton btnImprimeDevolucion) {
		this.btnImprimeDevolucion = btnImprimeDevolucion; 
	}
	
	public JButton getBtnImprimeReporte() {
		return btnImprimeReporte; 
	}
	
	public void setBtnImprimeReporte(JButton btnImprimeReporte) {
		this.btnImprimeReporte = btnImprimeReporte; 
	}
	
	public JButton getBtnDevolver() { 
		return btnDevolver; 
	}
	
	public void setBtnDevolver(JButton btnDevolver) { 
		this.btnDevolver = btnDevolver; 
	}
	
	public JButton getBtnRetirarArticulo() { 
		return btnRetirarArticulo; 
	}
	
	public void setBtnRetirarArticulo(JButton btnRetirarArticulo) {
		this.btnRetirarArticulo = btnRetirarArticulo; 
	}
	 
	public JButton getBtnImprimeFactura() {
		return btnImprimeFactura;
	}

	public void setBtnImprimeFactura(JButton btnImprimeFactura) {
		this.btnImprimeFactura = btnImprimeFactura;
	}
}
