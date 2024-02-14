package gui;

import javax.swing.JPanel;

import controlador.ConDevoluciones;
import util.TableDevolucionesModelo;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class IntDevoluciones extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ConDevoluciones conDevoluciones;
	
	private IntDevolucionesPiepagina intDevolucionesPiepagina;
	private IntDevolucionesEncabezado intDevolucionesEncabezado;
	
	public IntDevoluciones(TableDevolucionesModelo tableDevolucionesModelo) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1150, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		intDevolucionesEncabezado = new IntDevolucionesEncabezado(tableDevolucionesModelo);
		GridBagConstraints gbc_intDevolucionesEncabezado = new GridBagConstraints();
		gbc_intDevolucionesEncabezado.fill = GridBagConstraints.BOTH;
		gbc_intDevolucionesEncabezado.insets = new Insets(0, 0, 5, 0);
		gbc_intDevolucionesEncabezado.gridx = 0;
		gbc_intDevolucionesEncabezado.gridy = 0;
		add(intDevolucionesEncabezado, gbc_intDevolucionesEncabezado);
		
		intDevolucionesPiepagina = new IntDevolucionesPiepagina();
		GridBagConstraints gbc_intDevolucionesPiepagina = new GridBagConstraints();
		gbc_intDevolucionesPiepagina.fill = GridBagConstraints.HORIZONTAL;
		gbc_intDevolucionesPiepagina.gridx = 0;
		gbc_intDevolucionesPiepagina.gridy = 1;
		add(intDevolucionesPiepagina, gbc_intDevolucionesPiepagina);
	}

	public ConDevoluciones getConDevoluciones() {
		return conDevoluciones;
	}

	public void setConDevoluciones(ConDevoluciones conDevoluciones) {
		this.conDevoluciones = conDevoluciones;
	}

	public IntDevolucionesPiepagina getIntDevolucionesPiepagina() {
		return intDevolucionesPiepagina;
	}

	public void setIntDevolucionesPiepagina(IntDevolucionesPiepagina intDevolucionesPiepagina) {
		this.intDevolucionesPiepagina = intDevolucionesPiepagina;
	}

	public IntDevolucionesEncabezado getIntDevolucionesEncabezado() {
		return intDevolucionesEncabezado;
	}

	public void setIntDevolucionesEncabezado(IntDevolucionesEncabezado intDevolucionesEncabezado) {
		this.intDevolucionesEncabezado = intDevolucionesEncabezado;
	}
	
}
