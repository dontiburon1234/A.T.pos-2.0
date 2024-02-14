package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import util.TableDevolucionesModelo;

public class IntDevolucionesEncabezado extends JPanel {

	private static final long serialVersionUID = 1L;

	private int tLetra = 14;

	private JRadioButton rdbtnFactura;
	private JRadioButton rdbtnRemision;
	private ButtonGroup group;

	private JLabel lblPrefijo;
	private JTextField txtPrefijo;
	private JTextField txtNumero;
	private JTextField txtFecha;

	private JTextField txtDocumentoCliente;
	private JTextField txtNombreCliente;

	private JComboBox<String> jcbJustificacion;

	private JList<String> listDevolucionArticulo;
	private DefaultListModel<String> listModelDevolucionArticulo = new DefaultListModel<String>();

	private JList<String> listDevolucionFacturas;
	private DefaultListModel<String> listModelDevolucionFacturas = new DefaultListModel<String>();

	private JTable tableDevoluciones;

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

	public IntDevolucionesEncabezado(TableDevolucionesModelo tableDevolucionesModelo) {
		setLayout(null);

		rdbtnFactura = new JRadioButton("Factura");
		rdbtnFactura.setBounds(68, 11, 109, 23);
		rdbtnFactura.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(rdbtnFactura);

		rdbtnRemision = new JRadioButton("Remisi\u00F3n");
		rdbtnRemision.setBounds(191, 11, 109, 23);
		rdbtnRemision.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(rdbtnRemision);

		// Group the radio buttons.
		group = new ButtonGroup();
		group.add(rdbtnFactura);
		group.add(rdbtnRemision);

		lblPrefijo = new JLabel("Prefijo");
		lblPrefijo.setBounds(68, 48, 100, 30);
		lblPrefijo.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblPrefijo);

		txtPrefijo = new JTextField();
		txtPrefijo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrefijo.setBounds(68, 83, 100, 30);
		txtPrefijo.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtPrefijo);
		txtPrefijo.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(178, 48, 100, 30);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumero.setBounds(178, 83, 122, 30);
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setBounds(312, 48, 100, 30);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(312, 83, 100, 30);
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtFecha);
		txtFecha.setColumns(10);

		txtDocumentoCliente = new JTextField();
		txtDocumentoCliente.setText("Documento");
		txtDocumentoCliente.setBounds(425, 48, 150, 30);
		txtDocumentoCliente.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtDocumentoCliente);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setText("Nombre");
		txtNombreCliente.setBounds(425, 83, 200, 30);
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(txtNombreCliente);

		JLabel lblJustificacion = new JLabel("Justificacion");
		lblJustificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblJustificacion.setBounds(710, 48, 150, 30);
		lblJustificacion.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblJustificacion);

		String[] sJustificacion = { "", "No lo necesita", "No era el que necesitaba", "No le alcanzó el dinero",
				"No era el solicitado", "Cambio de forma de pago", "Otros" };

		jcbJustificacion = new JComboBox<String>(sJustificacion);
		jcbJustificacion.setBounds(680, 83, 200, 30);
		jcbJustificacion.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbJustificacion);

		listDevolucionArticulo = new JList<String>(listModelDevolucionArticulo);
		listDevolucionArticulo.setBounds(750, 125, 250, 180);
		listDevolucionArticulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDevolucionArticulo.setSelectedIndex(0);
		listDevolucionArticulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		listDevolucionArticulo.setVisibleRowCount(8);

		JScrollPane listScrollPaneArticulo = new JScrollPane(listDevolucionArticulo);
		listScrollPaneArticulo.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		listScrollPaneArticulo.setBounds(750, 125, 250, 180);
		add(listScrollPaneArticulo);

		listDevolucionFacturas = new JList<String>(listModelDevolucionFacturas);
		listDevolucionFacturas.setBounds(1020, 125, 250, 180);
		listDevolucionFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDevolucionFacturas.setSelectedIndex(0);
		listDevolucionFacturas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		listDevolucionFacturas.setVisibleRowCount(8);
		add(listDevolucionFacturas);

		JScrollPane listScrollPaneFacturas = new JScrollPane(listDevolucionFacturas);
		listScrollPaneFacturas.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		listScrollPaneFacturas.setBounds(1020, 125, 250, 180);
		add(listScrollPaneFacturas);

		JScrollPane scrollPane = new JScrollPane();
		tableDevoluciones = new JTable(tableDevolucionesModelo);
		tableDevoluciones.setRowHeight(30);
		tableDevoluciones.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		scrollPane.setBounds(68, 125, 670, 180);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		tableDevoluciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		definirAnchoColmunas();

		// Definir ancho de columnas
		/*
		 * TableColumn column = null; int i = 0; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // VENDEDOR
		 * //column.setPreferredWidth(7); column.setMaxWidth(0); i = 1; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // CODIGO
		 * column.setPreferredWidth(7); //column.setMinWidth(10); i = 2; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // ARTICULO
		 * column.setMinWidth(230); column.setMaxWidth(230); i = 3; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // UNIDAD
		 * //column.setPreferredWidth(0); column.setMaxWidth(0); i = 4; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // CANTIDAD
		 * column.setPreferredWidth(20); i = 5; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // VALOR UNITARIO
		 * column.setPreferredWidth(20); i = 6; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // DESCUENTO
		 * column.setPreferredWidth(20); i = 7; column =
		 * tableDevoluciones.getColumnModel().getColumn(i); // VALOR TOTAL
		 * column.setPreferredWidth(20);
		 */

		scrollPane.setViewportView(tableDevoluciones);
		add(scrollPane);

		String[] anno = { "2023", "2024", "2025", "2026", "2027", "2018", "2019", "2020", "2021", "2022" };
		String[] mes = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String[] dia = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		String[] hora = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23" };
		String[] min = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
				"50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

		JLabel tituloAnno = new JLabel("Año");
		tituloAnno.setBounds(960, 35, 46, 22);
		tituloAnno.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloAnno);

		JLabel tituloMes = new JLabel("Mes");
		tituloMes.setBounds(1030, 35, 46, 22);
		tituloMes.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMes);

		JLabel tituloDia = new JLabel("Dia");
		tituloDia.setBounds(1090, 35, 46, 22);
		tituloDia.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloDia);

		JLabel tituloHora = new JLabel("Hora");
		tituloHora.setBounds(1150, 35, 46, 22);
		tituloHora.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloHora);

		JLabel tituloMin = new JLabel("Minuto");
		tituloMin.setBounds(1210, 35, 46, 22);
		tituloMin.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMin);

		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(900, 54, 46, 22);
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblDesde);

		jcbAnnoDesde = new JComboBox<String>(anno);
		jcbAnnoDesde.setBounds(960, 54, 60, 22);
		jcbAnnoDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoDesde);

		jcbMesDesde = new JComboBox<String>(mes);
		jcbMesDesde.setBounds(1030, 54, 50, 22);
		jcbMesDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesDesde);

		jcbDiaDesde = new JComboBox<String>(dia);
		jcbDiaDesde.setBounds(1090, 54, 50, 22);
		jcbDiaDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaDesde);

		jcbHoraDesde = new JComboBox<String>(hora);
		jcbHoraDesde.setBounds(1150, 54, 50, 22);
		jcbHoraDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbHoraDesde);

		jcbMinDesde = new JComboBox<String>(min);
		jcbMinDesde.setBounds(1210, 54, 50, 22);
		jcbMinDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMinDesde);

		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(900, 85, 46, 22);
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(lblHasta);

		jcbAnnoHasta = new JComboBox<String>(anno);
		jcbAnnoHasta.setBounds(960, 85, 60, 22);
		jcbAnnoHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoHasta);

		jcbMesHasta = new JComboBox<String>(mes);
		jcbMesHasta.setBounds(1030, 85, 50, 22);
		jcbMesHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesHasta);

		jcbDiaHasta = new JComboBox<String>(dia);
		jcbDiaHasta.setBounds(1090, 85, 50, 22);
		jcbDiaHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaHasta);

		jcbHoraHasta = new JComboBox<String>(hora);
		jcbHoraHasta.setBounds(1150, 85, 50, 22);
		jcbHoraHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbHoraHasta);

		jcbMinHasta = new JComboBox<String>(min);
		jcbMinHasta.setBounds(1210, 85, 50, 22);
		jcbMinHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMinHasta);

	}

	public void definirAnchoColmunas() {
		TableColumn column = null;
		int i = 0;
		column = tableDevoluciones.getColumnModel().getColumn(i); // VENDEDOR
		// column.setPreferredWidth(7);
		column.setMaxWidth(0);
		i = 1;
		column = tableDevoluciones.getColumnModel().getColumn(i); // CODIGO
		column.setPreferredWidth(7);
		// column.setMinWidth(10);
		i = 2;
		column = tableDevoluciones.getColumnModel().getColumn(i); // ARTICULO
		column.setMinWidth(230);
		column.setMaxWidth(230);
		i = 3;
		column = tableDevoluciones.getColumnModel().getColumn(i); // UNIDAD
		// column.setPreferredWidth(0);
		column.setMaxWidth(0);
		i = 4;
		column = tableDevoluciones.getColumnModel().getColumn(i); // CANTIDAD
		column.setPreferredWidth(20);
		i = 5;
		column = tableDevoluciones.getColumnModel().getColumn(i); // VALOR UNITARIO
		column.setPreferredWidth(20);
		i = 6;
		column = tableDevoluciones.getColumnModel().getColumn(i); // DESCUENTO
		column.setPreferredWidth(20);
		i = 7;
		column = tableDevoluciones.getColumnModel().getColumn(i); // VALOR TOTAL
		column.setPreferredWidth(20);
	}

	public JTextField getTxtPrefijo() {
		return txtPrefijo;
	}

	public void setTxtPrefijo(JTextField txtPrefijo) {
		this.txtPrefijo = txtPrefijo;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
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

	public JComboBox<String> getJcbJustificacion() {
		return jcbJustificacion;
	}

	public void setJcbJustificacion(JComboBox<String> jcbJustificacion) {
		this.jcbJustificacion = jcbJustificacion;
	}

	public void setTxtNombreCliente(JTextField txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}

	public JList<String> getListDevolucionArticulo() {
		return listDevolucionArticulo;
	}

	public void setListDevolucionArticulo(JList<String> listDevolucionArticulo) {
		this.listDevolucionArticulo = listDevolucionArticulo;
	}

	public DefaultListModel<String> getListModelDevolucionArticulo() {
		return listModelDevolucionArticulo;
	}

	public void setListModelDevolucionArticulo(DefaultListModel<String> listModelDevolucionArticulo) {
		this.listModelDevolucionArticulo = listModelDevolucionArticulo;
	}

	public JList<String> getListDevolucionFacturas() {
		return listDevolucionFacturas;
	}

	public void setListDevolucionFacturas(JList<String> listDevolucionFacturas) {
		this.listDevolucionFacturas = listDevolucionFacturas;
	}

	public DefaultListModel<String> getListModelDevolucionFacturas() {
		return listModelDevolucionFacturas;
	}

	public void setListModelDevolucionFacturas(DefaultListModel<String> listModelDevolucionFacturas) {
		this.listModelDevolucionFacturas = listModelDevolucionFacturas;
	}

	public JTable getTableDevoluciones() {
		return tableDevoluciones;
	}

	public void setTableDevoluciones(JTable tableDevoluciones) {
		this.tableDevoluciones = tableDevoluciones;
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

	public JRadioButton getRdbtnFactura() {
		return rdbtnFactura;
	}

	public void setRdbtnFactura(JRadioButton rdbtnFactura) {
		this.rdbtnFactura = rdbtnFactura;
	}

	public JRadioButton getRdbtnRemision() {
		return rdbtnRemision;
	}

	public void setRdbtnRemision(JRadioButton rdbtnRemision) {
		this.rdbtnRemision = rdbtnRemision;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public JLabel getLblPrefijo() {
		return lblPrefijo;
	}

	public void setLblPrefijo(JLabel lblPrefijo) {
		this.lblPrefijo = lblPrefijo;
	}

}
