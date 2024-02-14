package guifactura;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import controlador.ConFactura;
import gui.IntKeyNumeros;
import util.TablaFormaPagoModelo;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import java.awt.ComponentOrientation;
import javax.swing.JCheckBox;

public class IntFormaPago extends JPanel {
	
	private ConFactura conFactura;

	private static final long serialVersionUID = 1L;
	private JButton btnEfectivo;
	private JButton btnTarjetaCredito;
	private JButton btnTarjetaDebito;
	private JButton btnCredito;
	private JButton btnRemision;
	private JButton btnBorrarItem;
	private JButton btnCancelar;
	private JButton btnMoneda;
	//private JButton btnImprimir;

	private JTextField txtValor;
	private JTextField txtRecibido;
	private JTextField txtCambio;
	private JTextField txtExtranjera;
	private JTable table;
	private JLabel lblRecibido;
	private JLabel lblCambio;
	private IntKeyNumeros intKeyNumeros;
	private JTextField txtTotalfactura;

	private JComboBox<String> cbEntidad;
	private JTextField txtNumeroTarjeta;
	private JTextField txtExtrajeraRecibido;
	private JLabel lblEntidad;
	private JLabel lblMoneda;
	private JLabel lblNumeroTarjeta;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	
	private JCheckBox chckbxProveedor;

	public IntFormaPago(TablaFormaPagoModelo tablaFormaPagoModelo) {
		
		setLayout(new BorderLayout(0, 0));
		Dimension size = new Dimension(166, 50);
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar,BorderLayout.PAGE_START);
		
		btnEfectivo = new JButton("Efectivo");
		btnEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnEfectivo.setPreferredSize(size);
		btnEfectivo.setMinimumSize(size);
		btnEfectivo.setMaximumSize(size);
		menuBar.add(btnEfectivo);

		btnTarjetaCredito = new JButton("TarjetaCr\u00E9dito");
		btnTarjetaCredito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTarjetaCredito.setPreferredSize(size);
		btnTarjetaCredito.setMinimumSize(size);
		btnTarjetaCredito.setMaximumSize(size);
		menuBar.add(btnTarjetaCredito);

		btnTarjetaDebito = new JButton("TarjetaD\u00E9bito");
		btnTarjetaDebito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTarjetaDebito.setPreferredSize(size);
		btnTarjetaDebito.setMinimumSize(size);
		btnTarjetaDebito.setMaximumSize(size);
		menuBar.add(btnTarjetaDebito);

		btnCredito = new JButton("Cr\u00E9dito");
		btnCredito.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCredito.setPreferredSize(size);
		btnCredito.setMinimumSize(size);
		btnCredito.setMaximumSize(size);
		menuBar.add(btnCredito);

		btnRemision = new JButton("Remisi\u00F3n");
		btnRemision.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnRemision.setPreferredSize(size);
		btnRemision.setMinimumSize(size);
		btnRemision.setMaximumSize(size);
		menuBar.add(btnRemision);
		
		btnMoneda = new JButton("Dolar");
		btnMoneda.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnMoneda.setPreferredSize(size);
		btnMoneda.setMinimumSize(size);
		btnMoneda.setMaximumSize(size);
		menuBar.add(btnMoneda);

		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);

		txtTotalfactura = new JTextField();
		txtTotalfactura.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalfactura.setBounds(10, 11, 186, 46);
		txtTotalfactura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotalfactura.setFocusable(false);
		panelCenter.add(txtTotalfactura);
		txtTotalfactura.setColumns(10);
		
		JLabel lblConEsteMedio = new JLabel("Con este medio de Pago");
		lblConEsteMedio.setHorizontalAlignment(SwingConstants.CENTER);
		lblConEsteMedio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConEsteMedio.setBounds(10, 60, 186, 23);
		panelCenter.add(lblConEsteMedio);
		
		txtValor = new JTextField();
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setBounds(10, 83, 186, 46);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtValor);
		txtValor.setColumns(10);

		lblRecibido = new JLabel("Recibido");
		lblRecibido.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecibido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRecibido.setBounds(206, 60, 186, 23);
		panelCenter.add(lblRecibido);
		
		txtRecibido = new JTextField();
		txtRecibido.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRecibido.setBounds(206, 83, 186, 46);
		txtRecibido.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtRecibido);
		txtRecibido.setColumns(10);
		
		lblEntidad = new JLabel("Entidad");
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEntidad.setBounds(206, 60, 186, 23);
		panelCenter.add(lblEntidad);
		
		lblMoneda = new JLabel("Moneda");
		lblMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoneda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoneda.setBounds(206, 60, 186, 23);
		panelCenter.add(lblMoneda);
		
		cbEntidad = new JComboBox<String>();
		cbEntidad.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cbEntidad.setBounds(206, 83, 186, 46);
		cbEntidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCenter.add(cbEntidad);
		
		lblCambio = new JLabel("Cambio");
		lblCambio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambio.setLabelFor(txtCambio);
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCambio.setBounds(402, 60, 186, 23);
		panelCenter.add(lblCambio);
		
		txtCambio = new JTextField();
		txtCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCambio.setBounds(402, 83, 186, 46);
		txtCambio.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtCambio);
		txtCambio.setColumns(10);
		
		txtExtranjera = new JTextField();
		txtExtranjera.setHorizontalAlignment(SwingConstants.RIGHT);
		txtExtranjera.setBounds(402, 11, 186, 46);
		txtExtranjera.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtExtranjera);
		txtExtranjera.setColumns(10);
		
		lblNumeroTarjeta = new JLabel("Numero Tarjeta");
		lblNumeroTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroTarjeta.setLabelFor(txtCambio);
		lblNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroTarjeta.setBounds(402, 60, 186, 23);
		panelCenter.add(lblNumeroTarjeta);
		
		txtNumeroTarjeta = new JTextField();
		txtNumeroTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumeroTarjeta.setBounds(402, 83, 186, 46);
		txtNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtNumeroTarjeta.setFocusable(false);
		panelCenter.add(txtNumeroTarjeta);
		txtNumeroTarjeta.setColumns(10);
		
		txtExtrajeraRecibido = new JTextField();
		txtExtrajeraRecibido.setHorizontalAlignment(SwingConstants.RIGHT);
		txtExtrajeraRecibido.setBounds(402, 83, 186, 46);
		txtExtrajeraRecibido.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtExtrajeraRecibido.setFocusable(true);
		panelCenter.add(txtExtrajeraRecibido);
		txtExtrajeraRecibido.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 650, 196);
		panelCenter.add(scrollPane);
		
		table = new JTable(tablaFormaPagoModelo);
		scrollPane.setViewportView(table);
		
		add(panelCenter,BorderLayout.CENTER);
		
		txtDocumento = new JTextField();
		txtDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDocumento.setBounds(206, 11, 186, 46);
		txtDocumento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtDocumento);
		txtDocumento.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(206, 83, 382, 46);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelCenter.add(txtNombre);
		txtNombre.setColumns(10);
		
		chckbxProveedor = new JCheckBox("Proveedor");
		chckbxProveedor.setBounds(402, 25, 97, 23);
		panelCenter.add(chckbxProveedor);
	
		intKeyNumeros = new IntKeyNumeros();
		add(intKeyNumeros,BorderLayout.LINE_END);
		
		JPanel panelAbajo = new JPanel();
		
		btnBorrarItem = new JButton("BorrarItem");
		btnBorrarItem.setFont((new Font("Tahoma", Font.PLAIN, 26)));
		panelAbajo.add(btnBorrarItem);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelAbajo.add(btnCancelar);
		
		/*btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelAbajo.add(btnImprimir);*/
		
		add(panelAbajo,BorderLayout.PAGE_END);
		
	}

	public JButton getBtnMoneda() {
		return btnMoneda;
	}

	public void setBtnMoneda(JButton btnMoneda) {
		this.btnMoneda = btnMoneda;
	}

	public IntKeyNumeros getIntKeyNumeros() {
		return intKeyNumeros;
	}

	public void setIntKeyNumeros(IntKeyNumeros intKeyNumeros) {
		this.intKeyNumeros = intKeyNumeros;
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}

	public JButton getBtnEfectivo() {
		return btnEfectivo;
	}

	public void setBtnEfectivo(JButton btnEfectivo) {
		this.btnEfectivo = btnEfectivo;
	}

	public JButton getBtnTarjetaCredito() {
		return btnTarjetaCredito;
	}

	public void setBtnTarjetaCredito(JButton btnTarjetaCredito) {
		this.btnTarjetaCredito = btnTarjetaCredito;
	}

	public JButton getBtnTarjetaDebito() {
		return btnTarjetaDebito;
	}

	public void setBtnTarjetaDebito(JButton btnTarjetaDebito) {
		this.btnTarjetaDebito = btnTarjetaDebito;
	}

	public JButton getBtnCredito() {
		return btnCredito;
	}

	public void setBtnCredito(JButton btnCredito) {
		this.btnCredito = btnCredito;
	}

	public JButton getBtnRemision() {
		return btnRemision;
	}

	public void setBtnRemision(JButton btnRemision) {
		this.btnRemision = btnRemision;
	}

	public JButton getBtnBorrarItem() {
		return btnBorrarItem;
	}

	public void setBtnBorrarItem(JButton btnBorrarItem) {
		this.btnBorrarItem = btnBorrarItem;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	/*public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}*/

	public JTextField getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}
	public JTextField getTxtRecibido() {
		return txtRecibido;
	}
	public void setTxtRecibido(JTextField txtRecibido) {
		this.txtRecibido = txtRecibido;
	}
	public JTextField getTxtCambio() {
		return txtCambio;
	}
	public void setTxtCambio(JTextField txtCambio) {
		this.txtCambio = txtCambio;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JTextField getTxtTotalfactura() {
		return txtTotalfactura;
	}
	public void setTxtTotalfactura(JTextField txtTotalfactura) {
		this.txtTotalfactura = txtTotalfactura;
	}
	public JLabel getLblRecibido() {
		return lblRecibido;
	}
	public void setLblRecibido(JLabel lblRecibido) {
		this.lblRecibido = lblRecibido;
	}
	public JLabel getLblCambio() {
		return lblCambio;
	}
	public void setLblCambio(JLabel lblCambio) {
		this.lblCambio = lblCambio;
	}
	public JComboBox<String> getCbEntidad() {
		return cbEntidad;
	}
	public void setCbEntidad(JComboBox<String> cbEntidad) {
		this.cbEntidad = cbEntidad;
	}
	public JLabel getLblMoneda() {
		return lblMoneda;
	}
	public void setLblMoneda(JLabel lblMoneda) {
		this.lblMoneda = lblMoneda;
	}
	public JTextField getTxtNumeroTarjeta() {
		return txtNumeroTarjeta;
	}
	public void setTxtNumeroTarjeta(JTextField txtNumeroTarjeta) {
		this.txtNumeroTarjeta = txtNumeroTarjeta;
	}
	public JLabel getLblEntidad() {
		return lblEntidad;
	}
	public void setLblEntidad(JLabel lblEntidad) {
		this.lblEntidad = lblEntidad;
	}
	public JLabel getLblNumeroTarjeta() {
		return lblNumeroTarjeta;
	}
	public void setLblNumeroTarjeta(JLabel lblNumeroTarjeta) {
		this.lblNumeroTarjeta = lblNumeroTarjeta;
	}
	public JTextField getTxtDocumento() {
		return txtDocumento;
	}
	public void setTxtDocumento(JTextField txtDocumento) {
		this.txtDocumento = txtDocumento;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	public JCheckBox getChckbxProveedor() {
		return chckbxProveedor;
	}
	public void setChckbxProveedor(JCheckBox chckbxProveedor) {
		this.chckbxProveedor = chckbxProveedor;
	}

	public JTextField getTxtExtranjera() {
		return txtExtranjera;
	}

	public void setTxtExtranjera(JTextField txtExtranjera) {
		this.txtExtranjera = txtExtranjera;
	}

	public JTextField getTxtExtrajeraRecibido() {
		return txtExtrajeraRecibido;
	}

	public void setTxtExtrajeraRecibido(JTextField txtExtrajeraRecibido) {
		this.txtExtrajeraRecibido = txtExtrajeraRecibido;
	}
	
}