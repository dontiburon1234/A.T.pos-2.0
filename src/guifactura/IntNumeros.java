package guifactura;

import javax.swing.JPanel;

import controlador.ConFactura;
import gui.IntKeyNumeros;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntNumeros extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConFactura conFactura;
	private IntPedientes intPendientes;
	private IntKeyNumeros intKeyNumeros;

	public IntNumeros(IntPedientes intPendientes,IntKeyNumeros intKeyNumeros) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_intPendientes = new GridBagConstraints();
		gbc_intPendientes.insets = new Insets(0, 0, 5, 0);
		gbc_intPendientes.gridx = 0;
		gbc_intPendientes.gridy = 0;
		gbc_intPendientes.ipadx = 10;
		gbc_intPendientes.ipady = 200;
		gbc_intPendientes.fill = GridBagConstraints.BOTH;
		add(intPendientes, gbc_intPendientes);
		
		GridBagConstraints gbc_intKeyNumeros = new GridBagConstraints();
		gbc_intKeyNumeros.gridx = 0;
		gbc_intKeyNumeros.gridy = 1;
		gbc_intKeyNumeros.fill = GridBagConstraints.BOTH;
		add(intKeyNumeros, gbc_intKeyNumeros);
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}

	public IntPedientes getIntPendientes() {
		return intPendientes;
	}

	public void setIntPendientes(IntPedientes intPendientes) {
		this.intPendientes = intPendientes;
	}

	public IntKeyNumeros getIntKeyNumeros() {
		return intKeyNumeros;
	}

	public void setIntKeyNumeros(IntKeyNumeros intKeyNumeros) {
		this.intKeyNumeros = intKeyNumeros;
	}
	
	
}
