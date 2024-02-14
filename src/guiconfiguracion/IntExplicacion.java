package guiconfiguracion;

import javax.swing.JPanel;

import controlador.ConConfiguracion;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntExplicacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConConfiguracion conConfiguracion;
	
	public IntExplicacion() {
		
		setBackground(new Color(255,255,0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{321, 0};
		gridBagLayout.rowHeights = new int[]{229, 56, 152, 58, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton button = new JButton("Pendiente");
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBackground(new Color(255, 51, 153));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		add(button, gbc_button);
		
		JTextPane txtpnSeleccioneEl = new JTextPane();
		txtpnSeleccioneEl.setText("1.- Seleccione el botton que va  a configurar");
		GridBagConstraints gbc_txtpnSeleccioneEl = new GridBagConstraints();
		gbc_txtpnSeleccioneEl.fill = GridBagConstraints.BOTH;
		gbc_txtpnSeleccioneEl.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnSeleccioneEl.gridx = 0;
		gbc_txtpnSeleccioneEl.gridy = 2;
		add(txtpnSeleccioneEl, gbc_txtpnSeleccioneEl);
		
		JButton btnRelleno = new JButton("Relleno");
		GridBagConstraints gbc_btnRelleno = new GridBagConstraints();
		gbc_btnRelleno.anchor = GridBagConstraints.NORTH;
		gbc_btnRelleno.gridx = 0;
		gbc_btnRelleno.gridy = 4;
		add(btnRelleno, gbc_btnRelleno);

	}

	public ConConfiguracion getConConfiguracion() {
		return conConfiguracion;
	}

	public void setConConfiguracion(ConConfiguracion conConfiguracion) {
		this.conConfiguracion = conConfiguracion;
	}
}
