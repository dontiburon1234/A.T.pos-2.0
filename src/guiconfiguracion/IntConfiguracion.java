package guiconfiguracion;

import javax.swing.JPanel;
import controlador.ConConfiguracion;
import guifactura.IntContenedor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class IntConfiguracion extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConConfiguracion conConfiguracion;
	private IntContenedor intContenedor;
	private IntExplicacion intExplicacion;
	
	public IntConfiguracion(IntContenedor intContenedor, IntExplicacion intExplicacion) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//intContenedor = (IntContenedor) new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(intContenedor, gbc_panel);
		
		//intExplicacion = (IntExplicacion) new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(intExplicacion, gbc_panel_1);
	}

	public ConConfiguracion getConConfiguracion() {
		return conConfiguracion;
	}

	public void setConConfiguracion(ConConfiguracion conConfiguracion) {
		this.conConfiguracion = conConfiguracion;
	}

	public IntContenedor getIntContenedor() {
		return intContenedor;
	}

	public void setIntContenedor(IntContenedor intContenedor) {
		this.intContenedor = intContenedor;
	}

	public IntExplicacion getIntExplicacion() {
		return intExplicacion;
	}

	public void setIntExplicacion(IntExplicacion intExplicacion) {
		this.intExplicacion = intExplicacion;
	}

}
