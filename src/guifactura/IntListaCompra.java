package guifactura;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import controlador.ConFactura;
import util.TablaFacturaModelo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class IntListaCompra extends JPanel {
	
	private ConFactura conFactura;
	private TablaFacturaModelo tablaFacturaModelo;

	private static final long serialVersionUID = 1L;
	private JTextField txtSubTotal;
	private JTextField txtImpuesto;
	private JLabel lblDescuento;
	private JLabel lblNombrecajero;
	private JTextField txtDescuento;
	private JTextField txtTotalfactura;
	private JButton btnBorraItem;
	private JButton btnAutorizacion;
	private JButton btnBuscar;
	private JButton btnPagarfactura;
	private JTable table;

	public IntListaCompra(TablaFacturaModelo tablaFacturaModelo) {
		this.tablaFacturaModelo = tablaFacturaModelo;
		setLayout(null);
		
		Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
		setBorder(paneEdge);
		
		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setBounds(14, 14, 108, 28);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblSubtotal);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubTotal.setBounds(14, 53, 124, 28);
		txtSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JLabel lblImpuesto = new JLabel("Impuesto");
		lblImpuesto.setBounds(152, 14, 143, 28);
		lblImpuesto.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblImpuesto);
		
		txtImpuesto = new JTextField();
		txtImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImpuesto.setBounds(152, 53, 143, 28);
		txtImpuesto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(txtImpuesto);
		txtImpuesto.setColumns(10);
		
		lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(311, 14, 129, 28);
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblDescuento);
		
		txtDescuento = new JTextField();
		txtDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDescuento.setBounds(311, 53, 129, 28);
		txtDescuento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(txtDescuento);
		txtDescuento.setColumns(10);
		
		txtTotalfactura = new JTextField();
		txtTotalfactura.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalfactura.setBounds(450, 14, 318, 67);
		txtTotalfactura.setFont(new Font("Tahoma", Font.PLAIN, 44));
		add(txtTotalfactura);
		txtTotalfactura.setColumns(10);
		
		btnBorraItem = new JButton("Borra Item");
		btnBorraItem.setBounds(12, 534, 190, 41);
		btnBorraItem.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(btnBorraItem);
		
		btnAutorizacion = new JButton("Autorizaci\u00F3n");
		btnAutorizacion.setBounds(416, 534, 190, 41);
		btnAutorizacion.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(btnAutorizacion);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(214, 534, 190, 41);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN,26));
		add(btnBuscar);
		
		btnPagarfactura = new JButton("PagarFactura");
		btnPagarfactura.setBounds(618, 534, 190, 41);
		btnPagarfactura.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(btnPagarfactura);
		
		table = new JTable(tablaFacturaModelo);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 92, 754, 424);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		
		definirAnchoColmunas();
		
	/*	//Definir ancho de columnas
		TableColumn column = null;
		int i = 0;
		column = table.getColumnModel().getColumn(i); // VENDEDOR
		//column.setPreferredWidth(7);
		column.setMaxWidth(0);
		i = 1;
		column = table.getColumnModel().getColumn(i); // CODIGO
		column.setPreferredWidth(7);
		//column.setMinWidth(10);
		i = 2;
		column = table.getColumnModel().getColumn(i); // ARTICULO
		column.setMinWidth(230);
		column.setMaxWidth(230);
		i = 3;
		column = table.getColumnModel().getColumn(i); // UNIDAD
		//column.setPreferredWidth(0);
		column.setMaxWidth(0);
		i = 4;
		column = table.getColumnModel().getColumn(i); // CANTIDAD
		column.setPreferredWidth(20);
		i = 5;
		column = table.getColumnModel().getColumn(i); // VALOR UNITARIO
		column.setPreferredWidth(20);
		i = 6;
		column = table.getColumnModel().getColumn(i); // DESCUENTO
		column.setPreferredWidth(20);
		i = 7;
		column = table.getColumnModel().getColumn(i); // VALOR TOTAL
		column.setPreferredWidth(20);*/

		scrollPane.setViewportView(table);
		add(scrollPane);
		
		JLabel lblCajero = new JLabel("Cajero");
		lblCajero.setBounds(17, 519, 46, 14);
		add(lblCajero);
		
		lblNombrecajero = new JLabel("NombreCajero");
		lblNombrecajero.setBounds(57, 519, 198, 14);
		add(lblNombrecajero);
	}
	
	public void definirAnchoColmunas() {
		//Definir ancho de columnas
		TableColumn column = null;
		int i = 0;
		column = table.getColumnModel().getColumn(i); // VENDEDOR
		//column.setPreferredWidth(7);
		column.setMaxWidth(0);
		i = 1;
		column = table.getColumnModel().getColumn(i); // CODIGO
		column.setPreferredWidth(7);
		//column.setMinWidth(10);
		i = 2;
		column = table.getColumnModel().getColumn(i); // ARTICULO
		column.setMinWidth(230);
		column.setMaxWidth(230);
		i = 3;
		column = table.getColumnModel().getColumn(i); // UNIDAD
		//column.setPreferredWidth(0);
		column.setMaxWidth(0);
		i = 4;
		column = table.getColumnModel().getColumn(i); // CANTIDAD
		column.setPreferredWidth(20);
		i = 5;
		column = table.getColumnModel().getColumn(i); // VALOR UNITARIO
		column.setPreferredWidth(20);
		i = 6;
		column = table.getColumnModel().getColumn(i); // DESCUENTO
		column.setPreferredWidth(20);
		i = 7;
		column = table.getColumnModel().getColumn(i); // VALOR TOTAL
		column.setPreferredWidth(20);
	}
	
	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}
	
	public TablaFacturaModelo getTablaFacturaModelo() {
		return tablaFacturaModelo;
	}

	public void setTablaFacturaModelo(TablaFacturaModelo tablaFacturaModelo) {
		this.tablaFacturaModelo = tablaFacturaModelo;
	}

	public JTextField getTxtSubTotal() {
		return txtSubTotal;
	}

	public void setTxtSubTotal(JTextField txtSubTotal) {
		this.txtSubTotal = txtSubTotal;
	}

	public JTextField getTxtImpuesto() {
		return txtImpuesto;
	}

	public void setTxtImpuesto(JTextField txtImpuesto) {
		this.txtImpuesto = txtImpuesto;
	}

	public JLabel getLblDescuento() {
		return lblDescuento;
	}

	public void setLblDescuento(JLabel lblDescuento) {
		this.lblDescuento = lblDescuento;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JTextField txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JTextField getTxtTotalfactura() {
		return txtTotalfactura;
	}

	public void setTxtTotalfactura(JTextField txtTotalfactura) {
		this.txtTotalfactura = txtTotalfactura;
	}

	public JButton getBtnBorraItem() {
		return btnBorraItem;
	}

	public void setBtnBorraItem(JButton btnBorraItem) {
		this.btnBorraItem = btnBorraItem;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnAutorizacion() {
		return btnAutorizacion;
	}

	public void setBtnAutorizacion(JButton btnAutorizacion) {
		this.btnAutorizacion = btnAutorizacion;
	}

	public JLabel getLblNombrecajero() {
		return lblNombrecajero;
	}

	public void setLblNombrecajero(JLabel lblNombrecajero) {
		this.lblNombrecajero = lblNombrecajero;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnPagarfactura() {
		return btnPagarfactura;
	}

	public void setBtnPagarfactura(JButton btnPagarfactura) {
		this.btnPagarfactura = btnPagarfactura;
	}
	
	
	
}
