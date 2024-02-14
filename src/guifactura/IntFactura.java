package guifactura;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import controlador.ConFactura;

public class IntFactura extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConFactura conFactura;

	public IntFactura(IntContenedor intContenedor,JPanel intCapturaArticulo,JPanel intNumeros) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_intContenedor = new GridBagConstraints();
		gbc_intContenedor.insets = new Insets(0, 5, 0, 5);
		gbc_intContenedor.fill = GridBagConstraints.BOTH;
		gbc_intContenedor.gridx = 0;
		gbc_intContenedor.gridy = 0;
		add(intContenedor, gbc_intContenedor);
		
		GridBagConstraints gbc_intCapturaArticulo = new GridBagConstraints();
		gbc_intCapturaArticulo.insets = new Insets(0, 0, 0, 5);
		gbc_intCapturaArticulo.gridx = 1;
		gbc_intCapturaArticulo.gridy = 0;
		add(intCapturaArticulo, gbc_intCapturaArticulo);
		
		GridBagConstraints gbc_intNumeros = new GridBagConstraints();
		gbc_intNumeros.insets = new Insets(0, 0, 0, 5);
		gbc_intNumeros.gridx = 2;
		gbc_intNumeros.gridy = 0;
		add(intNumeros, gbc_intNumeros);
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}	
}
