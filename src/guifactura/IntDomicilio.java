package guifactura;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class IntDomicilio extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtId;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextArea taListadoPedido;
	//private JTextPane pnListadoPedido;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JButton btnLimpiar;

	public IntDomicilio() {
		setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 46, 35);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(66, 11, 118, 35);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(194, 11, 148, 35);
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblDocumento);
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(344, 10, 350, 35);
		txtDocumento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(txtDocumento);
		txtDocumento.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 57, 178, 35);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(194, 56, 500, 35);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(6, 103, 178, 35);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(194,102,500, 35);
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(txtApellido);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(6, 149, 178, 35);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(194, 148, 500, 35);
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(6, 195, 178, 35);
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(194, 194, 500, 35);
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		taListadoPedido = new JTextArea(".");
		taListadoPedido.setFont(new Font("Tahoma", Font.PLAIN, 26));// .setFont(new Font("Serif", Font.ITALIC, 16));
		taListadoPedido.setBounds(720, 11, 350, 204);
		taListadoPedido.setLineWrap(true);
		taListadoPedido.setWrapStyleWord(true);
		
		JScrollPane areaScrollPane = new JScrollPane(taListadoPedido);
		areaScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		areaScrollPane.setBounds(704, 11, 370, 219);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Lista de compras"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						areaScrollPane.getBorder()));
		add(areaScrollPane);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(638, 249, 142, 41);
		add(btnCancelar);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(378, 249, 142, 41);
		add(btnBuscar);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(118, 249, 142, 41);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(btnLimpiar);
		
	}

	public JTextField getTxtId() {
		return txtId;
	}
	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
	public JTextField getTxtDocumento() {
		return txtDocumento;
	}
	public void setTxtDocumento(JTextField txtDocumento) {
		this.txtDocumento = txtDocumento;
	}
	
	public JTextArea getTaListadoPedido() {
		return taListadoPedido;
	}

	public void setTaListadoPedido(JTextArea taListadoPedido) {
		this.taListadoPedido = taListadoPedido;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	
}
