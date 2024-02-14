package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import util.TablaPagosModelo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class IntPagosEncabezado extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDocumentoProveedor;
	private JTextField txtNombreProveedor;
	private JTextField txtNombreComercial;
	private JTextField txtNumeroFactura;
	private int tLetra = 14;
	private JLabel lblNombreCaja;
	private JLabel lblNombreAlmacen;
	private JLabel lblNombreCajero;
	private JLabel lblFechaPago;
	private JLabel lblValorPagado;
	private JTextField txtValorPagado;
	private JLabel lblTipoPago;
	private JComboBox<String> jcbTipoPago;
	private JTextField txtComentario;
	private JTextField txtValorfactura;
	private JTextField txtValordescuento;
	private JTable tablePagos;
	
	private JComboBox<String> jcbAnnoDesde;
	private JComboBox<String> jcbMesDesde;
	private JComboBox<String> jcbDiaDesde;
	private JComboBox<String> jcbHoraDesde;
	private JComboBox<String> jcbMinDesde;
	
	private JComboBox<String> jcbAnnoHasta;
	private JComboBox<String> jcbMesHasta;
	private JComboBox<String> jcbDiaHasta;
	private JComboBox<String> jcbHoraHasta;
	private JComboBox<String> jcbMinHasta;
	

	public IntPagosEncabezado(TablaPagosModelo tablaPagosModelo) {
		setLayout(null);
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(47, 11, 60, 30);
		lblAlmacen.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblAlmacen);
		
		lblNombreAlmacen = new JLabel("");
		lblNombreAlmacen.setBounds(112, 11, 132, 30);
		lblNombreAlmacen.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNombreAlmacen);
		
		JLabel lblCaja = new JLabel("Caja");
		lblCaja.setBounds(254, 11, 169, 30);
		lblCaja.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblCaja);
		
		lblNombreCaja = new JLabel("NombreCaja");
		lblNombreCaja.setBounds(299, 11, 124, 30);
		lblNombreCaja.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNombreCaja);
		
		JLabel lblCajero = new JLabel("Cajero");
		lblCajero.setBounds(446, 11, 95, 30);
		lblCajero.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblCajero);
		
		lblNombreCajero = new JLabel("NombreCajero");
		lblNombreCajero.setBounds(551, 11, 225, 30);
		lblNombreCajero.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNombreCajero);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(445, 47, 47, 30);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblFecha);
		
		lblFechaPago = new JLabel("FechaPago");
		lblFechaPago.setBounds(520, 47, 241, 30);
		lblFechaPago.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblFechaPago);
		
		JLabel lblDocumentoProveedor = new JLabel("DocumentoProveedor");
		lblDocumentoProveedor.setBounds(47, 83, 148, 30);
		lblDocumentoProveedor.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblDocumentoProveedor);
		
		txtDocumentoProveedor = new JTextField();
		txtDocumentoProveedor.setBounds(195, 83, 208, 30);
		txtDocumentoProveedor.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtDocumentoProveedor);
		txtDocumentoProveedor.setColumns(10);
		
		JLabel lblNombreProveedor = new JLabel("Proveedor");
		lblNombreProveedor.setBounds(47, 136, 76, 30);
		lblNombreProveedor.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNombreProveedor);
		
		txtNombreProveedor = new JTextField();
		txtNombreProveedor.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreProveedor.setBounds(119, 136, 283, 30);
		txtNombreProveedor.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtNombreProveedor);
		txtNombreProveedor.setColumns(10);
		
		JLabel lblNombreComercial = new JLabel("Comercial");
		lblNombreComercial.setBounds(47, 189, 69, 30);
		lblNombreComercial.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNombreComercial);
		
		txtNombreComercial = new JTextField();
		txtNombreComercial.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreComercial.setBounds(112, 189, 291, 30);
		txtNombreComercial.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtNombreComercial);
		txtNombreComercial.setColumns(10);
		
		JLabel lblNumeroFactura = new JLabel("N\u00FAmeroFactura");
		lblNumeroFactura.setBounds(47, 242, 109, 30);
		lblNumeroFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNumeroFactura);
		
		txtNumeroFactura = new JTextField();
		txtNumeroFactura.setBounds(162, 242, 241, 30);
		txtNumeroFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtNumeroFactura);
	
		lblValorPagado = new JLabel("ValorPagado");
		lblValorPagado.setBounds(416, 192, 109, 30);
		lblValorPagado.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblValorPagado);
		
		txtValorPagado = new JTextField();
		txtValorPagado.setBounds(520, 192, 152, 30);
		txtValorPagado.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtValorPagado);
		txtValorPagado.setColumns(10);
		
		lblTipoPago = new JLabel("TipoPago");
		lblTipoPago.setBounds(413, 240, 101, 30);
		lblTipoPago.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblTipoPago);
		
		jcbTipoPago = new JComboBox<String>();
		jcbTipoPago.setBounds(522, 240, 152, 30);
		jcbTipoPago.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbTipoPago);
		
		JLabel lblComentario = new JLabel("Comentario");
		lblComentario.setBounds(47, 294, 76, 30);
		lblComentario.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblComentario);
		
		txtComentario = new JTextField();
		txtComentario.setBounds(133, 295, 539, 30);
		txtComentario.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtComentario);
		
		JLabel lblValorfactura = new JLabel("ValorFactura");
		lblValorfactura.setBounds(416, 83, 132, 30);
		lblValorfactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblValorfactura);
		
		txtValorfactura = new JTextField();
		txtValorfactura.setBounds(520, 83, 152, 30);
		txtValorfactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtValorfactura);
		txtValorfactura.setColumns(10);
		
		JLabel lblValordescuento = new JLabel("ValorDescuento");
		lblValordescuento.setBounds(416, 142, 101, 30);
		lblValordescuento.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblValordescuento);
		
		txtValordescuento = new JTextField();
		txtValordescuento.setBounds(520, 141, 152, 30);
		txtValordescuento.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtValordescuento);
		txtValordescuento.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(700, 81, 600, 250);
		
		tablePagos = new JTable(tablaPagosModelo);
		tablePagos.setRowHeight(30);
		tablePagos.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		scrollPane.setBounds(700, 92, 600, 232);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		
		//Definir ancho de columnas
		TableColumn column = null;
		int i = 0;
		column = tablePagos.getColumnModel().getColumn(i);
		//column.setPreferredWidth(7);
		column.setMaxWidth(30);
		i = 1;
		column = tablePagos.getColumnModel().getColumn(i);
		column.setPreferredWidth(7);
		i = 2;
		column = tablePagos.getColumnModel().getColumn(i);
		column.setPreferredWidth(50);
		i = 3;
		column = tablePagos.getColumnModel().getColumn(i);
		column.setPreferredWidth(25);
		i = 4;
		column = tablePagos.getColumnModel().getColumn(i);
		column.setPreferredWidth(25);
		i = 5;
		column = tablePagos.getColumnModel().getColumn(i);
		column.setPreferredWidth(25);

		scrollPane.setViewportView(tablePagos);
		add(scrollPane);
		
		String[] anno = {"2018","2019","2020","2021","2022"};
		String[] mes = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		String[] dia = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31"};
		String[] hora = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
			"21","22","23"};
		String[] min = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
				"41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
		
		JLabel tituloAnno = new JLabel("Año");
		tituloAnno.setBounds(960, 2, 46, 22);
		tituloAnno.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloAnno);
		
		JLabel tituloMes = new JLabel("Mes");
		tituloMes.setBounds(1030, 2, 46, 22);
		tituloMes.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMes);
		
		JLabel tituloDia = new JLabel("Dia");
		tituloDia.setBounds(1090, 2, 46, 22);
		tituloDia.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloDia);
		
		JLabel tituloHora = new JLabel("Hora");
		tituloHora.setBounds(1150, 2, 46, 22);
		tituloHora.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloHora);
		
		JLabel tituloMin = new JLabel("Minuto");
		tituloMin.setBounds(1210, 2, 46, 22);
		tituloMin.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMin);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(900, 21, 46, 22);
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblDesde);
		
		jcbAnnoDesde = new JComboBox<String>(anno);
		jcbAnnoDesde.setBounds(960, 21, 60, 22);
		jcbAnnoDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoDesde);
		
		jcbMesDesde = new JComboBox<String>(mes);
		jcbMesDesde.setBounds(1030, 21, 50, 22);
		jcbMesDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesDesde);
		
		jcbDiaDesde = new JComboBox<String>(dia);
		jcbDiaDesde.setBounds(1090, 21, 50, 22);
		jcbDiaDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaDesde);
		
		jcbHoraDesde = new JComboBox<String>(hora);
		jcbHoraDesde.setBounds(1150, 21, 50, 22);
		jcbHoraDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbHoraDesde);
		
		jcbMinDesde = new JComboBox<String>(min);
		jcbMinDesde.setBounds(1210, 21, 50, 22);
		jcbMinDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMinDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(900, 52, 46, 22);
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblHasta);
		
		jcbAnnoHasta = new JComboBox<String>(anno);
		jcbAnnoHasta.setBounds(960, 52, 60, 22);
		jcbAnnoHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoHasta);
		
		jcbMesHasta = new JComboBox<String>(mes);
		jcbMesHasta.setBounds(1030, 52, 50, 22);
		jcbMesHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesHasta);
		
		jcbDiaHasta = new JComboBox<String>(dia);
		jcbDiaHasta.setBounds(1090, 52, 50, 22);
		jcbDiaHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaHasta);
		
		jcbHoraHasta = new JComboBox<String>(hora);
		jcbHoraHasta.setBounds(1150, 52, 50, 22);
		jcbHoraHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbHoraHasta);
		
		jcbMinHasta = new JComboBox<String>(min);
		jcbMinHasta.setBounds(1210, 52, 50, 22);
		jcbMinHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMinHasta);
	}

	public JTextField getTxtDocumentoProveedor() {
		return txtDocumentoProveedor;
	}

	public void setTxtDocumentoProveedor(JTextField txtDocumentoProveedor) {
		this.txtDocumentoProveedor = txtDocumentoProveedor;
	}

	public JTextField getTxtNombreProveedor() {
		return txtNombreProveedor;
	}

	public void setTxtNombreProveedor(JTextField txtNombreProveedor) {
		this.txtNombreProveedor = txtNombreProveedor;
	}

	public JTextField getTxtNumeroFactura() {
		return txtNumeroFactura;
	}

	public void setTxtNumeroFactura(JTextField txtNumeroFactura) {
		this.txtNumeroFactura = txtNumeroFactura;
	}

	public JLabel getLblNombreCaja() {
		return lblNombreCaja;
	}

	public void setLblNombreCaja(JLabel lblNombreCaja) {
		this.lblNombreCaja = lblNombreCaja;
	}

	public JLabel getLblNombreAlmacen() {
		return lblNombreAlmacen;
	}

	public void setLblNombreAlmacen(JLabel lblNombreAlmacen) {
		this.lblNombreAlmacen = lblNombreAlmacen;
	}

	public JLabel getLblNombreCajero() {
		return lblNombreCajero;
	}

	public void setLblNombreCajero(JLabel lblNombreCajero) {
		this.lblNombreCajero = lblNombreCajero;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public void setLblFechaPago(JLabel lblFechaPago) {
		this.lblFechaPago = lblFechaPago;
	}

	public JTextField getTxtNombreComercial() {
		return txtNombreComercial;
	}

	public void setTxtNombreComercial(JTextField txtNombreComercial) {
		this.txtNombreComercial = txtNombreComercial;
	}

	public JLabel getLblValorPagado() {
		return lblValorPagado;
	}

	public void setLblValorPagado(JLabel lblValorPagado) {
		this.lblValorPagado = lblValorPagado;
	}

	public JTextField getTxtValorPagado() {
		return txtValorPagado;
	}

	public void setTxtValorPagado(JTextField txtValorPagado) {
		this.txtValorPagado = txtValorPagado;
	}

	public JLabel getLblTipoPago() {
		return lblTipoPago;
	}

	public void setLblTipoPago(JLabel lblTipoPago) {
		this.lblTipoPago = lblTipoPago;
	}

	public JComboBox<String> getJcbTipoPago() {
		return jcbTipoPago;
	}

	public void setJcbTipoPago(JComboBox<String> jcbTipoPago) {
		this.jcbTipoPago = jcbTipoPago;
	}

	public JTextField getTxtComentario() {
		return txtComentario;
	}

	public void setTxtComentario(JTextField txtComentario) {
		this.txtComentario = txtComentario;
	}

	public JTextField getTxtValorfactura() {
		return txtValorfactura;
	}

	public void setTxtValorfactura(JTextField txtValorfactura) {
		this.txtValorfactura = txtValorfactura;
	}

	public JTextField getTxtValordescuento() {
		return txtValordescuento;
	}

	public void setTxtValordescuento(JTextField txtValordescuento) {
		this.txtValordescuento = txtValordescuento;
	}

	public JTable getTablePagos() {
		return tablePagos;
	}

	public void setTablePagos(JTable tablePagos) {
		this.tablePagos = tablePagos;
	}

	public JComboBox<String> getJcbAnnoDesde() {
		return jcbAnnoDesde;
	}

	public void setJcbAnnoDesde(JComboBox<String> jcbAnnoDesde) {
		this.jcbAnnoDesde = jcbAnnoDesde;
	}

	public JComboBox<String> getJcbMesDesde() {
		return jcbMesDesde;
	}

	public void setJcbMesDesde(JComboBox<String> jcbMesDesde) {
		this.jcbMesDesde = jcbMesDesde;
	}

	public JComboBox<String> getJcbDiaDesde() {
		return jcbDiaDesde;
	}

	public void setJcbDiaDesde(JComboBox<String> jcbDiaDesde) {
		this.jcbDiaDesde = jcbDiaDesde;
	}

	public JComboBox<String> getJcbHoraDesde() {
		return jcbHoraDesde;
	}

	public void setJcbHoraDesde(JComboBox<String> jcbHoraDesde) {
		this.jcbHoraDesde = jcbHoraDesde;
	}

	public JComboBox<String> getJcbMinDesde() {
		return jcbMinDesde;
	}

	public void setJcbMinDesde(JComboBox<String> jcbMinDesde) {
		this.jcbMinDesde = jcbMinDesde;
	}

	public JComboBox<String> getJcbAnnoHasta() {
		return jcbAnnoHasta;
	}

	public void setJcbAnnoHasta(JComboBox<String> jcbAnnoHasta) {
		this.jcbAnnoHasta = jcbAnnoHasta;
	}

	public JComboBox<String> getJcbMesHasta() {
		return jcbMesHasta;
	}

	public void setJcbMesHasta(JComboBox<String> jcbMesHasta) {
		this.jcbMesHasta = jcbMesHasta;
	}

	public JComboBox<String> getJcbDiaHasta() {
		return jcbDiaHasta;
	}

	public void setJcbDiaHasta(JComboBox<String> jcbDiaHasta) {
		this.jcbDiaHasta = jcbDiaHasta;
	}

	public JComboBox<String> getJcbHoraHasta() {
		return jcbHoraHasta;
	}

	public void setJcbHoraHasta(JComboBox<String> jcbHoraHasta) {
		this.jcbHoraHasta = jcbHoraHasta;
	}

	public JComboBox<String> getJcbMinHasta() {
		return jcbMinHasta;
	}

	public void setJcbMinHasta(JComboBox<String> jcbMinHasta) {
		this.jcbMinHasta = jcbMinHasta;
	}
	
}
