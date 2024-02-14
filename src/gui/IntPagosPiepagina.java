package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntPagosPiepagina extends JPanel {

	private static final long serialVersionUID = 1L;
	private IntKeyNumeros intKeyNumeros;
	private IntKeyLetras intKeyLetras;
	private IntPagosBotones intPagosBotones;

	public IntPagosPiepagina() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		intPagosBotones = new IntPagosBotones();
		GridBagConstraints gbc_intPagosBotones = new GridBagConstraints();
		gbc_intPagosBotones.insets = new Insets(0, 0, 5, 5);
		gbc_intPagosBotones.gridx = 0;
		gbc_intPagosBotones.gridy = 0;
		add(intPagosBotones, gbc_intPagosBotones);
		
		intKeyNumeros = new IntKeyNumeros();
		GridBagConstraints gbc_intKeyNumeros = new GridBagConstraints();
		gbc_intKeyNumeros.anchor = GridBagConstraints.WEST;
		gbc_intKeyNumeros.gridheight = 2;
		gbc_intKeyNumeros.insets = new Insets(0, 0, 5, 0);
		gbc_intKeyNumeros.gridx = 1;
		gbc_intKeyNumeros.gridy = 0;
		add(intKeyNumeros, gbc_intKeyNumeros);
		
		intKeyLetras = new IntKeyLetras();
		GridBagConstraints gbc_intKeyLetras = new GridBagConstraints();
		gbc_intKeyLetras.insets = new Insets(0, 0, 0, 5);
		gbc_intKeyLetras.gridx = 0;
		gbc_intKeyLetras.gridy = 1;
		add(intKeyLetras, gbc_intKeyLetras);
	}

	public IntKeyNumeros getIntKeyNumeros() {
		return intKeyNumeros;
	}

	public void setIntKeyNumeros(IntKeyNumeros intKeyNumeros) {
		this.intKeyNumeros = intKeyNumeros;
	}

	public IntKeyLetras getIntKeyLetras() {
		return intKeyLetras;
	}

	public void setIntKeyLetras(IntKeyLetras intKeyLetras) {
		this.intKeyLetras = intKeyLetras;
	}

	public IntPagosBotones getIntPagosBotones() {
		return intPagosBotones;
	}

	public void setIntPagosBotones(IntPagosBotones intPagosBotones) {
		this.intPagosBotones = intPagosBotones;
	}

	
	
}
