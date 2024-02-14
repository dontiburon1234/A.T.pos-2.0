package guifactura;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ConFactura;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class IntCapturaArticulo extends JPanel {

	private ConFactura conFactura;
	private static final long serialVersionUID = 1L;
	private static final int tLetraText = 15;
	private static final int tLetraBoton = 30;
	private static final int tLetralbl = 15;
	
	private JTextField txtDocumentoCliente;
	private JTextField txtNombreCliente;
	private JTextField txtCodigoArticulo;
	private JTextField txtNombreArticulo;
	private JTextField txtUnidades;
	private JTextField txtCantidad;
	private JTextField txtMedida;
	private JTextField txtValorUnitario;
	private JTextField txtValorTotal;
	private JButton btnDomicilio;
	private JButton btnGuardarItem;
	//private JButton btnPagarFactura;
	private JTextField txtTotalFactura;
	
	//private JTextField txtNombreCajero;
	private JTextField txtCodigoVendedor;
	private JTextField txtNombreVendedor;
	private JTextField txtDescuento;
	
	private JLabel lblCodigo;
	private JLabel lblArticulo;
	private JLabel lblUnidades;
	private JLabel lblCantidad;
	private JLabel lblMedida;
	private JLabel lblValorunitario;
	private JLabel lblValortotal;
	private JLabel lblVendedor;
	private JTextField txtXxxxx;

	public IntCapturaArticulo() {
		//txtNombreCajero = new JTextField();
		
		
		txtDescuento = new JTextField();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCliente = new JLabel("Cliente");
		//lblCliente.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		add(lblCliente, gbc_lblCliente);
		
		txtDocumentoCliente = new JTextField();
		txtDocumentoCliente.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtDocumentocliente = new GridBagConstraints();
		gbc_txtDocumentocliente.ipady = 10;
		gbc_txtDocumentocliente.insets = new Insets(0, 0, 5, 0);
		gbc_txtDocumentocliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDocumentocliente.gridx = 0;
		gbc_txtDocumentocliente.gridy = 1;
		gbc_txtDocumentocliente.gridwidth = 2;
		add(txtDocumentoCliente, gbc_txtDocumentocliente);
		txtDocumentoCliente.setColumns(10);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtNombrecliente = new GridBagConstraints();
		gbc_txtNombrecliente.ipady = 10;
		gbc_txtNombrecliente.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombrecliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombrecliente.gridx = 0;
		gbc_txtNombrecliente.gridy = 2;
		gbc_txtNombrecliente.gridwidth = 2;
		add(txtNombreCliente, gbc_txtNombrecliente);
		txtNombreCliente.setColumns(10);
		
		lblVendedor = new JLabel("Vendedor");
		GridBagConstraints gbc_lblVendedor = new GridBagConstraints();
		gbc_lblVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblVendedor.gridx = 0;
		gbc_lblVendedor.gridy = 3;
		add(lblVendedor, gbc_lblVendedor);
		
		txtCodigoVendedor = new JTextField();
		txtCodigoVendedor.setText("");
		txtCodigoVendedor.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtCodigovendedor = new GridBagConstraints();
		gbc_txtCodigovendedor.ipady = 10;
		gbc_txtCodigovendedor.gridwidth = 2;
		gbc_txtCodigovendedor.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigovendedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigovendedor.gridx = 0;
		gbc_txtCodigovendedor.gridy = 4;
		add(txtCodigoVendedor, gbc_txtCodigovendedor);
		txtCodigoVendedor.setColumns(10);
		
		txtNombreVendedor = new JTextField();
		txtNombreVendedor.setText("");
		txtNombreVendedor.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtNombrevendedor = new GridBagConstraints();
		gbc_txtNombrevendedor.ipady = 10;
		gbc_txtNombrevendedor.gridwidth = 2;
		gbc_txtNombrevendedor.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombrevendedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombrevendedor.gridx = 0;
		gbc_txtNombrevendedor.gridy = 5;
		add(txtNombreVendedor, gbc_txtNombrevendedor);
		txtNombreVendedor.setColumns(10);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 6;
		add(lblCodigo, gbc_lblCodigo);
		
		txtCodigoArticulo = new JTextField();
		txtCodigoArticulo.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtCodigoarticulo = new GridBagConstraints();
		gbc_txtCodigoarticulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigoarticulo.ipady = 10;
		gbc_txtCodigoarticulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigoarticulo.gridx = 0;
		gbc_txtCodigoarticulo.gridy = 7;
		gbc_txtCodigoarticulo.gridwidth = 2;
		add(txtCodigoArticulo, gbc_txtCodigoarticulo);
		txtCodigoArticulo.setColumns(10);
		
		lblArticulo = new JLabel("Articulo");
		lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblArticulo = new GridBagConstraints();
		gbc_lblArticulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticulo.gridx = 0;
		gbc_lblArticulo.gridy = 8;
		add(lblArticulo, gbc_lblArticulo);
		
		txtNombreArticulo = new JTextField();
		txtNombreArticulo.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreArticulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_txtNombreArticulo = new GridBagConstraints();
		gbc_txtNombreArticulo.ipady = 10;
		gbc_txtNombreArticulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombreArticulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreArticulo.gridx = 0;
		gbc_txtNombreArticulo.gridy = 10;
		gbc_txtNombreArticulo.gridwidth = 2;
		add(txtNombreArticulo, gbc_txtNombreArticulo);
		txtNombreArticulo.setColumns(10);
		
		/*lblUnidades = new JLabel("Unidades");
		lblUnidades.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblUnidades = new GridBagConstraints();
		gbc_lblUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidades.gridx = 0;
		gbc_lblUnidades.gridy = 10;
		add(lblUnidades, gbc_lblUnidades);*/
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 0);
		gbc_lblCantidad.gridwidth = 2;
		gbc_lblCantidad.gridx = 0;
		gbc_lblCantidad.gridy = 11;
		add(lblCantidad, gbc_lblCantidad);
		
		txtUnidades = new JTextField();
		/*txtUnidades.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtUnidades_1 = new GridBagConstraints();
		gbc_txtUnidades_1.ipady = 10;
		gbc_txtUnidades_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtUnidades_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUnidades_1.gridx = 0;
		gbc_txtUnidades_1.gridy = 11;
		add(txtUnidades, gbc_txtUnidades_1);
		txtUnidades.setColumns(10);*/
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtCantidad_1 = new GridBagConstraints();
		gbc_txtCantidad_1.ipady = 10;
		gbc_txtCantidad_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtCantidad_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCantidad_1.gridwidth = 2;
		gbc_txtCantidad_1.gridx = 0;
		gbc_txtCantidad_1.gridy = 12;
		add(txtCantidad, gbc_txtCantidad_1);
		txtCantidad.setColumns(10);
		
		lblMedida = new JLabel("Medida");
		lblMedida.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblMedida = new GridBagConstraints();
		gbc_lblMedida.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedida.gridx = 0;
		gbc_lblMedida.gridy = 13;
		add(lblMedida, gbc_lblMedida);
		
		lblValorunitario = new JLabel("ValorUnitario");
		lblValorunitario.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblValorunitario = new GridBagConstraints();
		gbc_lblValorunitario.insets = new Insets(0, 0, 5, 0);
		gbc_lblValorunitario.gridx = 1;
		gbc_lblValorunitario.gridy = 13;
		add(lblValorunitario, gbc_lblValorunitario);
		
		txtMedida = new JTextField();
		txtMedida.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtMedida_1 = new GridBagConstraints();
		gbc_txtMedida_1.ipady = 10;
		gbc_txtMedida_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtMedida_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMedida_1.gridx = 0;
		gbc_txtMedida_1.gridy = 14;
		add(txtMedida, gbc_txtMedida_1);
		txtMedida.setColumns(10);
		
		txtValorUnitario = new JTextField();
		txtValorUnitario.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValorUnitario.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtValorunitario = new GridBagConstraints();
		gbc_txtValorunitario.ipady = 10;
		gbc_txtValorunitario.insets = new Insets(0, 0, 5, 0);
		gbc_txtValorunitario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValorunitario.gridx = 1;
		gbc_txtValorunitario.gridy = 14;
		add(txtValorUnitario, gbc_txtValorunitario);
		txtValorUnitario.setColumns(10);
		
		lblValortotal = new JLabel("ValorTotal");
		lblValortotal.setFont(new Font("Tahoma", Font.PLAIN, tLetralbl));
		GridBagConstraints gbc_lblValortotal = new GridBagConstraints();
		gbc_lblValortotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblValortotal.gridx = 0;
		gbc_lblValortotal.gridy = 15;
		add(lblValortotal, gbc_lblValortotal);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValorTotal.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtValortotal = new GridBagConstraints();
		gbc_txtValortotal.ipady = 10;
		gbc_txtValortotal.insets = new Insets(0, 0, 5, 0);
		gbc_txtValortotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValortotal.gridx = 0;
		gbc_txtValortotal.gridy = 16;
		gbc_txtValortotal.gridwidth = 2;
		add(txtValorTotal, gbc_txtValortotal);
		txtValorTotal.setColumns(10);
		
		btnGuardarItem = new JButton("AgregarItem");
		btnGuardarItem.setFont(new Font("Tahoma", Font.PLAIN, tLetraBoton));
		GridBagConstraints gbc_btnGuardarItem = new GridBagConstraints();
		gbc_btnGuardarItem.insets = new Insets(0, 0, 5, 0);
		gbc_btnGuardarItem.fill = GridBagConstraints.BOTH;
		gbc_btnGuardarItem.gridx = 0;
		gbc_btnGuardarItem.gridy = 17;
		gbc_btnGuardarItem.gridwidth = 2;
		add(btnGuardarItem, gbc_btnGuardarItem);
		
		btnDomicilio = new JButton("Domicilio");
		btnDomicilio.setFont(new Font("Tahoma", Font.PLAIN, tLetraBoton));
		GridBagConstraints gbc_btnDomicilio = new GridBagConstraints();
		gbc_btnDomicilio.insets = new Insets(0, 0, 5, 0);
		gbc_btnDomicilio.fill = GridBagConstraints.BOTH;
		gbc_btnDomicilio.gridx = 0;
		gbc_btnDomicilio.gridy = 18;
		gbc_btnDomicilio.gridwidth = 2;
		add(btnDomicilio, gbc_btnDomicilio);
		
		txtTotalFactura = new JTextField();
		txtTotalFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetraText));
		GridBagConstraints gbc_txtTotalFactura = new GridBagConstraints();
		gbc_txtTotalFactura.ipady = 10;
		gbc_txtTotalFactura.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotalFactura.gridx = 0;
		gbc_txtTotalFactura.gridy = 19;
		gbc_txtTotalFactura.gridwidth = 2;
		add(txtTotalFactura, gbc_txtTotalFactura);
		txtTotalFactura.setColumns(10);
		
		/*btnPagarFactura = new JButton("PagarFactura");
		btnPagarFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetraBoton));
		GridBagConstraints gbc_btnPagarFactura = new GridBagConstraints();
		gbc_btnPagarFactura.insets = new Insets(0, 0, 0, 5);
		gbc_btnPagarFactura.gridx = 0;
		gbc_btnPagarFactura.gridwidth = 2;
		add(btnPagarFactura, gbc_btnPagarFactura);*/
				
	}
	public ConFactura getConFactura() {
		return conFactura;
	}
	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}
	public JTextField getTxtDocumentoCliente() {
		return txtDocumentoCliente;
	}
	public void setTxtDocumentoCliente(JTextField txtDocumentoCliente) {
		this.txtDocumentoCliente = txtDocumentoCliente;
	}
	public JTextField getTxtNombreCliente() {
		return txtNombreCliente;
	}
	public void setTxtNombreCliente(JTextField txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}
	public JTextField getTxtCodigoArticulo() {
		return txtCodigoArticulo;
	}
	public void setTxtCodigoArticulo(JTextField txtCodigoArticulo) {
		this.txtCodigoArticulo = txtCodigoArticulo;
	}
	public JTextField getTxtNombreArticulo() {
		return txtNombreArticulo;
	}
	public void setTxtNombreArticulo(JTextField txtNombreArticulo) {
		this.txtNombreArticulo = txtNombreArticulo;
	}
	public JTextField getTxtUnidades() {
		return txtUnidades;
	}
	public void setTxtUnidades(JTextField txtUnidades) {
		this.txtUnidades = txtUnidades;
	}
	public JTextField getTxtCantidad() {
		return txtCantidad;
	}
	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}
	public JTextField getTxtMedida() {
		return txtMedida;
	}
	public void setTxtMedida(JTextField txtMedida) {
		this.txtMedida = txtMedida;
	}
	public JTextField getTxtValorUnitario() {
		return txtValorUnitario;
	}
	public void setTxtValorUnitario(JTextField txtValorUnitario) {
		this.txtValorUnitario = txtValorUnitario;
	}
	public JTextField getTxtValorTotal() {
		return txtValorTotal;
	}
	public void setTxtValorTotal(JTextField txtValorTotal) {
		this.txtValorTotal = txtValorTotal;
	}
	public JButton getBtnDomicilio() {
		return btnDomicilio;
	}
	public void setBtnDomicilio(JButton btnDomicilio) {
		this.btnDomicilio = btnDomicilio;
	}
	public JButton getBtnGuardarItem() {
		return btnGuardarItem;
	}
	public void setBtnGuardarItem(JButton btnGuardarItem) {
		this.btnGuardarItem = btnGuardarItem;
	}
	/*public JButton getBtnPagarFactura() {
		return btnPagarFactura;
	}
	public void setBtnPagarFactura(JButton btnPagarFactura) {
		this.btnPagarFactura = btnPagarFactura;
	}*/
	public JTextField getTxtTotalFactura() {
		return txtTotalFactura;
	}
	public void setTxtTotalFactura(JTextField txtTotalFactura) {
		this.txtTotalFactura = txtTotalFactura;
	}
	/*public JTextField getTxtNombreCajero() {
		return txtNombreCajero;
	}
	public void setTxtNombreCajero(JTextField txtNombreCajero) {
		this.txtNombreCajero = txtNombreCajero;
	}*/
	public JTextField getTxtCodigoVendedor() {
		return txtCodigoVendedor;
	}
	public void setTxtCodigoVendedor(JTextField txtCodigoVendedor) {
		this.txtCodigoVendedor = txtCodigoVendedor;
	}
	public JTextField getTxtNombreVendedor() {
		return txtNombreVendedor;
	}
	public void setTxtNombreVendedor(JTextField txtNombreVendedor) {
		this.txtNombreVendedor = txtNombreVendedor;
	}
	public JLabel getLblCodigo() {
		return lblCodigo;
	}
	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}
	public JLabel getLblArticulo() {
		return lblArticulo;
	}
	public void setLblArticulo(JLabel lblArticulo) {
		this.lblArticulo = lblArticulo;
	}
	public JLabel getLblUnidades() {
		return lblUnidades;
	}
	public void setLblUnidades(JLabel lblUnidades) {
		this.lblUnidades = lblUnidades;
	}
	public JLabel getLblCantidad() {
		return lblCantidad;
	}
	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}
	public JLabel getLblMedida() {
		return lblMedida;
	}
	public void setLblMedida(JLabel lblMedida) {
		this.lblMedida = lblMedida;
	}
	public JLabel getLblValorunitario() {
		return lblValorunitario;
	}
	public void setLblValorunitario(JLabel lblValorunitario) {
		this.lblValorunitario = lblValorunitario;
	}
	public JLabel getLblValortotal() {
		return lblValortotal;
	}
	public void setLblValortotal(JLabel lblValortotal) {
		this.lblValortotal = lblValortotal;
	}

}
