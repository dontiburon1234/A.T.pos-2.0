package gui;

import javax.swing.JPanel;

import controlador.ConPagos;
import util.TablaPagosModelo;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntPagos extends JPanel {

	private static final long serialVersionUID = 1L;

	private ConPagos conPagos;
	
	private IntPagosEncabezado intPagosEncabezado;
	private IntPagosPiepagina intPagosPiepagina;

	public IntPagos(TablaPagosModelo tablaPagosModelo) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1150, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		intPagosEncabezado = new IntPagosEncabezado(tablaPagosModelo);
		GridBagConstraints gbc_intPagosEncabezado = new GridBagConstraints();
		gbc_intPagosEncabezado.fill = GridBagConstraints.BOTH;
		gbc_intPagosEncabezado.insets = new Insets(0, 0, 5, 0);
		gbc_intPagosEncabezado.gridx = 0;
		gbc_intPagosEncabezado.gridy = 0;
		add(intPagosEncabezado, gbc_intPagosEncabezado);
		
		intPagosPiepagina = new IntPagosPiepagina();
		GridBagConstraints gbc_intPagosPiepagina = new GridBagConstraints();
		gbc_intPagosPiepagina.fill = GridBagConstraints.HORIZONTAL;
		gbc_intPagosPiepagina.gridx = 0;
		gbc_intPagosPiepagina.gridy = 1;
		add(intPagosPiepagina, gbc_intPagosPiepagina);
	}

	public ConPagos getConPagos() {
		return conPagos;
	}

	public void setConPagos(ConPagos conPagos) {
		this.conPagos = conPagos;
	}

	public IntPagosEncabezado getIntPagosEncabezado() {
		return intPagosEncabezado;
	}

	public void setIntPagosEncabezado(IntPagosEncabezado intPagosEncabezado) {
		this.intPagosEncabezado = intPagosEncabezado;
	}

	public IntPagosPiepagina getIntPagosPiepagina() {
		return intPagosPiepagina;
	}

	public void setIntPagosPiepagina(IntPagosPiepagina intPagosPiepagina) {
		this.intPagosPiepagina = intPagosPiepagina;
	}

}
