package guiconfiguracion;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class IntConfiguracionCaja extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtConfiguracinCaja;

	/**
	 * Create the panel.
	 */
	public IntConfiguracionCaja() {
		setLayout(null);
		
		txtConfiguracinCaja = new JTextField();
		txtConfiguracinCaja.setText("Configuraci\u00F3n Caja");
		txtConfiguracinCaja.setBounds(158, 149, 86, 20);
		add(txtConfiguracinCaja);
		txtConfiguracinCaja.setColumns(10);

	}
}
