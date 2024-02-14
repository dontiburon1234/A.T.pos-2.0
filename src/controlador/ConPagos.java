package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import clases.Almacen;
import clases.Caja;
import clases.Pagos;
import clases.Proveedor;
import clases.TablaPagos;
import clases.TipoPago;
import database.MaestroDB;
import gui.IntPagos;
import main.Principal;
import modelo.ModeloBuscarProveedor;
import modelo.ModeloPagos;
import util.EntradaTeclado;
import util.G;
import util.TablaPagosModelo;

public class ConPagos implements ActionListener, FocusListener, KeyListener,ListSelectionListener {

	private IntPagos intPagos;
	private ModeloPagos modeloPagos;
	private Principal principal;

	private MaestroDB maestroDB;// = new MaestroDB();
	private JTextField txtCampoGained;
	private JButton boton;
	private int tipoCampo;
	private String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );
	private int id_proveedor;
	private Proveedor proveedor = new Proveedor();
	private int id_tipo_pago = 1;
	private TablaPagosModelo tablaPagosModelo;

	private int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
	private int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen(); 

	private String annoDesde;
	private String mesDesde;
	private String diaDesde;
	private String horaDesde;
	private String minDesde;
	private String annoHasta;
	private String mesHasta;
	private String diaHasta;
	private String horaHasta;
	private String minHasta;

	private ListSelectionModel listSelectionModel;
	private int rowDelete = -1;

	public ConPagos(IntPagos intPagos, ModeloPagos modeloPagos,Principal principal, TablaPagosModelo tablaPagosModelo, MaestroDB maestroDB) {
		super();
		this.intPagos = intPagos;
		this.modeloPagos = modeloPagos;
		this.tablaPagosModelo =  tablaPagosModelo;
		this.maestroDB = maestroDB;
		this.principal = principal;

		Almacen almacen = new Almacen();
		almacen = maestroDB.almacen(G.getInstance().licenciaGlobal.getIdAlmacen());
		intPagos.getIntPagosEncabezado().getLblNombreAlmacen().setText(almacen.getNombre());

		intPagos.getIntPagosEncabezado().getLblNombreCajero().setText(G.getInstance().cajero);

		Caja caja = new Caja();
		caja = maestroDB.caja(G.getInstance().licenciaGlobal.getIdCaja());
		intPagos.getIntPagosEncabezado().getLblNombreCaja().setText(caja.getNombre());

		String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );
		String sFecha = ""+fecha;
		sFecha = sFecha.substring(0,sFecha.indexOf("."));

		intPagos.getIntPagosEncabezado().getLblFechaPago().setText(sFecha);

		intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnGuardar().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnReporte().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnImprimirPago().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnEliminarPago().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnBuscarProveedor().addActionListener(this);

		intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().addFocusListener(this);
		//intPagos.getIntPagosEncabezado().getTxtNombreProveedor().addFocusListener(this);
		//intPagos.getIntPagosEncabezado().getTxtNombreComercial().addFocusListener(this);
		intPagos.getIntPagosEncabezado().getTxtNumeroFactura().addFocusListener(this);
		intPagos.getIntPagosEncabezado().getTxtValorfactura().addFocusListener(this);
		intPagos.getIntPagosEncabezado().getTxtValordescuento().addFocusListener(this);
		//intPagos.getIntPagosEncabezado().getTxtValorPagado().addFocusListener(this);
		intPagos.getIntPagosEncabezado().getTxtComentario().addFocusListener(this);

		intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().addKeyListener(this);
		intPagos.getIntPagosEncabezado().getTxtValorfactura().addKeyListener(this);
		intPagos.getIntPagosEncabezado().getTxtValordescuento().addKeyListener(this);

		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSiete().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnOcho().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnNueve().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCuatro().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCinco().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSeis().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnUno().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnDos().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnTres().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCero().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnPor().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnPunto().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnMenos().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSuprimir().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnEnter().addActionListener(this);

		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSiete().setName("siete");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnOcho().setName("ocho");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnNueve().setName("nueve");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCuatro().setName("cuatro");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCinco().setName("cinco");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSeis().setName("seis");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnUno().setName("uno");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnDos().setName("dos");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnTres().setName("tres");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnCero().setName("cero");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnPor().setName("por");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnPunto().setName("punto");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnMenos().setName("menos");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSuprimir().setName("suprimir");
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnEnter().setName("enter");

		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnQ().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnW().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnE().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnR().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnT().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnY().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnU().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnI().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnO().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnP().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnA().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnS().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnD().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnF().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnG().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnH().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnJ().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnK().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnL().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnEnne().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnZ().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnX().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnC().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnV().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnB().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnN().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnM().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnEspacio().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnPunto().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnQuion().addActionListener(this);
		intPagos.getIntPagosPiepagina().getIntKeyLetras().getBtnPor().addActionListener(this);

		ArrayList<TipoPago> alTipoPago = new ArrayList<TipoPago>();
		alTipoPago = maestroDB.tipoPago();
		for (int i = 0; i < alTipoPago.size(); i++) {
			intPagos.getIntPagosEncabezado().getJcbTipoPago().addItem(alTipoPago.get(i).getNombre());
		}

		intPagos.getIntPagosEncabezado().getJcbTipoPago().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id_tipo_pago = intPagos.getIntPagosEncabezado().getJcbTipoPago().getSelectedIndex()+1;
			}
		});

		periodoPagos(id_caja, id_almacen);

		actualizaTablaPagos();

		listSelectionModel = this.intPagos.getIntPagosEncabezado().getTablePagos().getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		this.intPagos.getIntPagosEncabezado().getTablePagos().setSelectionModel(listSelectionModel);

		txtCampoGained = intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor();
		intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().setBackground(Color.YELLOW);
		intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().requestFocus();
		principal.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().requestFocusInWindow();
			}
		});
	}

	@Override
	public void focusGained(FocusEvent e) {

		if(txtCampoGained==intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor()) {
			String sDocumento = intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().getText();
			sDocumento = sDocumento.replace(",", "");
			if(sDocumento.length()==0) {
				sDocumento = "0";
			}
			long documento = Long.valueOf(sDocumento);
			proveedor = maestroDB.traeProveedor(documento);

			System.out.println("ConPagos.focusLost() proveedor.getNombre() "+proveedor.getNombre());

			if(proveedor!=null) {
				intPagos.getIntPagosEncabezado().getTxtNombreProveedor().setText(proveedor.getNombre());
				intPagos.getIntPagosEncabezado().getTxtNombreComercial().setText(proveedor.getNombre_comercial());
				intPagos.getIntPagosEncabezado().getTxtNumeroFactura().requestFocus();
			}else {
				JOptionPane.showMessageDialog(null, "Proveedor inexistente");
			}
		}

		intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtNombreProveedor().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtNombreComercial().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtNumeroFactura().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtValorfactura().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtValordescuento().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtValorPagado().setBackground(Color.WHITE);
		intPagos.getIntPagosEncabezado().getTxtComentario().setBackground(Color.WHITE);

		txtCampoGained = (JTextField) e.getSource();

		if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor()) {
			intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().selectAll();
			intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().setBackground(Color.YELLOW);
			//txtCampoGained.requestFocus();
			tipoCampo = 2;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtNombreProveedor()) {
			intPagos.getIntPagosEncabezado().getTxtNombreProveedor().setBackground(Color.YELLOW);
			tipoCampo = 3;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtNombreComercial()) {
			intPagos.getIntPagosEncabezado().getTxtNombreComercial().setBackground(Color.YELLOW);
			tipoCampo = 3;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtNumeroFactura()) {
			intPagos.getIntPagosEncabezado().getTxtNumeroFactura().setBackground(Color.YELLOW);
			//txtCampoGained.requestFocus();
			tipoCampo = 3;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtValorfactura()) {
			intPagos.getIntPagosEncabezado().getTxtValorfactura().setBackground(Color.YELLOW);
			tipoCampo = 2;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtValordescuento()) {
			intPagos.getIntPagosEncabezado().getTxtValordescuento().setBackground(Color.YELLOW);
			txtCampoGained.requestFocus();
			tipoCampo = 2;
		}else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtComentario()) {
			intPagos.getIntPagosEncabezado().getTxtComentario().setBackground(Color.YELLOW);
			//txtCampoGained.requestFocus();
			tipoCampo = 3;
		}

	}

	@Override
	public void focusLost(FocusEvent e) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent e) {
		intPagos.getIntPagosPiepagina().getIntKeyNumeros().getRootPane().setDefaultButton(intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnEnter());
		if(e.getKeyChar() ==KeyEvent.VK_ENTER) {
			txtCampoGained.transferFocus();
		}

		if (e.getSource() == intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor()) {
			EntradaTeclado.validarNumero(e);
		} else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtValorfactura()) {
			EntradaTeclado.validarNumero(e);
		} else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtValordescuento()) {
			EntradaTeclado.validarNumero(e);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						rowDelete = i;
					}
				}
			}
			System.out.println("ConPagos.ConPagos(...).new ListSelectionListener() {...}.valueChanged() rowDelete "+rowDelete);
		} catch (Exception e1) {
			System.out.println("ConPagos.valueChanged() ERROR TABLA DE PAGOS "+e1);
			e1.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			boton = (JButton) e.getSource();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(boton == intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnSuprimir()) {
			int tamanoCampo = txtCampoGained.getText().length();
			String textoCampo = txtCampoGained.getText();
			if(tamanoCampo!=0) {
				textoCampo = textoCampo.substring(0, tamanoCampo-1);
				txtCampoGained.setText(textoCampo);
			}
		}else if(boton == intPagos.getIntPagosPiepagina().getIntKeyNumeros().getBtnEnter()) {
			System.out.println("ConPagos.actionPerformed() LE DIO AL ENTER DE PANTALLA ");
			txtCampoGained.transferFocus();

		}else if(boton == intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnGuardar()) {
			System.out.println("ConPagos.actionPerformed() getBtnGuardar");

			id_proveedor = proveedor.getId();
			if(id_proveedor>0) {
				int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
				int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();

				int id_cajero = G.getInstance().getIdCajero;

				String sValorFactura = intPagos.getIntPagosEncabezado().getTxtValorfactura().getText();
				double valor_factura = 0;
				if(sValorFactura.length()>0) {
					sValorFactura = sValorFactura.replace(",", "");
					valor_factura = Double.valueOf(sValorFactura);
				}

				String sValorDescuento = intPagos.getIntPagosEncabezado().getTxtValordescuento().getText();
				double valor_descuento = 0;
				if(sValorDescuento.length()>0) {
					sValorDescuento = sValorDescuento.replace(",", "");
					valor_descuento = Double.valueOf(sValorDescuento);
				}

				double valor_pagado = valor_factura - valor_descuento;
				String numero_factura = intPagos.getIntPagosEncabezado().getTxtNumeroFactura().getText();
				Date fechaPago = fecha;
				String comentario = intPagos.getIntPagosEncabezado().getTxtComentario().getText();
				String estado2 = "pagado";
				Date dg_fecha_accion = fecha;
				String dg_accion = "insert";

				if(valor_factura > valor_descuento) {
					if(id_tipo_pago != 0) {
						int id_pago = maestroDB. agregarPago(id_caja, id_almacen, id_proveedor, id_cajero, id_tipo_pago,
								valor_factura, valor_descuento, valor_pagado, numero_factura, fechaPago, comentario, estado2,
								dg_fecha_accion, dg_accion);
						System.out.println("ConPagos.actionPerformed() id_pago "+id_pago);

						int id_pagos = maestroDB.maximoIDPagos();

						TablaPagos tablaPagos = new TablaPagos(id_pagos,fecha, proveedor.getNombre_comercial(), valor_factura, valor_descuento,valor_pagado);
						tablaPagosModelo.adicionaPagos(tablaPagos);

						if(id_pago>0) {
							intPagos.getIntPagosEncabezado().getTxtDocumentoProveedor().setText("");
							intPagos.getIntPagosEncabezado().getTxtNombreProveedor().setText("");
							intPagos.getIntPagosEncabezado().getTxtNombreComercial().setText("");
							intPagos.getIntPagosEncabezado().getTxtNumeroFactura().setText("");
							intPagos.getIntPagosEncabezado().getTxtValorfactura().setText("");
							intPagos.getIntPagosEncabezado().getTxtValordescuento().setText("");
							intPagos.getIntPagosEncabezado().getTxtValorPagado().setText("");
							intPagos.getIntPagosEncabezado().getTxtComentario().setText("");
							id_proveedor = 0;
						}
					}else {
						JOptionPane.showMessageDialog(null, "Selecciones el tipo de pago");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Valor de la factura debe ser mayor que el descuento");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar primero un proveedor");
			}

		}else if(boton == intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnReporte()) {
			System.out.println("ConPagos.actionPerformed()  getBtnReporte ");
			System.out.println("ConPagos.periodoPagos() Desde "+annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00");
			System.out.println("ConPagos.periodoPagos() Hasta "+annoHasta+"-"+mesHasta+"-"+diaHasta+" "+horaHasta+":"+minHasta+":00");			

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

				String fechaDedes = annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00.000";
				Date dFechaInicial = formatter.parse(fechaDedes);
				String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dFechaInicial);
				java.sql.Timestamp fechaInicial = java.sql.Timestamp.valueOf( inputDia );

				String fechaHasta = annoHasta+"-"+mesHasta+"-"+diaHasta+" "+horaHasta+":"+minHasta+":00.000";
				Date dFechaFinal = formatter.parse(fechaHasta);
				String inputDia1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dFechaFinal);
				java.sql.Timestamp fechaFinal = java.sql.Timestamp.valueOf( inputDia1 );

				System.out.println("ConPagos.actionPerformed() fechaInicial "+fechaInicial+" fechaFinal "+fechaFinal);

				int id_cajero = G.getInstance().getIdCajero;
				ArrayList<Pagos> alPagos = maestroDB.traerPagos(id_caja, id_almacen, id_cajero,fechaInicial, fechaFinal);

				modeloPagos.imprimirReportePagos(alPagos,fechaInicial,fechaFinal, maestroDB);

				String sGsonPagos = "";
				Gson gson = new Gson();
				sGsonPagos = gson.toJson(alPagos);
				System.out.println("ConPagos.actionPerformed() TODOS LOS PAGOS EN UN PERIODO DE TIEMPO "+sGsonPagos);

			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		}else if(boton == intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnImprimirPago()) {
			System.out.println("ConPagos.actionPerformed() getBtnImprimirPago rowDelete "+rowDelete);
			if(rowDelete>=0) {
				int id_pago = (int) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 0);
				
				System.out.println("ConPagos.actionPerformed() id_pago "+id_pago+" id_caja "+id_caja+" id_almacen "+id_almacen);
				
				modeloPagos.imprimirPagoFirma(id_pago,id_caja,id_almacen, maestroDB);
			}

		}else if(boton == intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnEliminarPago()) {

			if(rowDelete>0) {
				/*int celdaIdPago = (int) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 0);
				Date celdaFecha = (Date) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 1);
				String celdaNombre = (String) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 2);
				double celdaFactura = (double) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 3);
				double celdaDescuento = (double) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 4);
				double celdaPagado = (double) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 5);

				System.out.println("ConPagos.actionPerformed() rowDelete "+rowDelete+" celdaIdPago "+celdaIdPago+" celdaFecha "+celdaFecha+" celdaNombre "+celdaNombre+
						" celdaFactura "+celdaFactura+" celdaDescuento "+celdaDescuento+" celdaPagado "+celdaPagado);*/

				int id_pago = (int) intPagos.getIntPagosEncabezado().getTablePagos().getValueAt(rowDelete, 0);
				int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
				int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
				int id_cajero = G.getInstance().getIdCajero;
				tablaPagosModelo.borraPago(rowDelete);
				maestroDB.borraPago(id_pago, id_caja, id_almacen, id_cajero);			
				rowDelete= -1;
			}
		}else if(boton == intPagos.getIntPagosPiepagina().getIntPagosBotones().getBtnBuscarProveedor()){
			System.out.println("ConPagos.actionPerformed() getBtnBuscarProveedor ");

			ModeloBuscarProveedor modeloBuscarProveedor = new ModeloBuscarProveedor(this, maestroDB);
			modeloBuscarProveedor.buscarProveedor(principal);

		}else {
			//txtCampoGained.setText(modeloPagos.dato(boton.getText(), txtCampoGained.getText(), tipoCampo));
			//System.err.println("ConPagos.actionPerformed() txtCampoGained.getText() "+txtCampoGained.getText()+" tipoCampo "+tipoCampo);
			if(boton.getText().equals("Espacio")) {
				txtCampoGained.setText(modeloPagos.dato(" ", txtCampoGained.getText(), tipoCampo));
			}
			else {
				txtCampoGained.setText(modeloPagos.dato(boton.getText(), txtCampoGained.getText(), tipoCampo));
			}
		}

	}

	public IntPagos getIntPagos() {
		return intPagos;
	}
	public void setIntPagos(IntPagos intPagos) {
		this.intPagos = intPagos;
	}
	public ModeloPagos getModeloPagos() {
		return modeloPagos;
	}
	public void setModeloPagos(ModeloPagos modeloPagos) {
		this.modeloPagos = modeloPagos;
	}

	private void actualizaTablaPagos() {
		try {
			/*System.err.println("ConPagos.actionPerformed()  getBtnReporte ");
			System.err.println("ConPagos.periodoPagos() Desde "+annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00");
			System.err.println("ConPagos.periodoPagos() Hasta "+annoHasta+"-"+mesHasta+"-"+diaHasta+" "+horaHasta+":"+minHasta+":00");	*/

			int numeroFilas = intPagos.getIntPagosEncabezado().getTablePagos().getRowCount()-1;
			for (int i = 0; i <= numeroFilas; i++) {
				//System.out.println("ConPagos.actualizaTablaPagos() numeroColumnas "+numeroFilas+" i "+i+" filaBorrada "+(numeroFilas-i));
				tablaPagosModelo.borraPago(numeroFilas-i);
			}
			String fechaDedes = annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00.000";
			java.sql.Timestamp fechaInicial = java.sql.Timestamp.valueOf( fechaDedes );

			String fechaHasta = annoHasta+"-"+mesHasta+"-"+diaHasta+" "+horaHasta+":"+minHasta+":00.000";
			java.sql.Timestamp fechaFinal = java.sql.Timestamp.valueOf( fechaHasta );

			ArrayList<Pagos> alPagos = new ArrayList<Pagos>(); 
			String nombreComercialProveedor = "";
			int id_cajero = G.getInstance().getIdCajero;
			alPagos = maestroDB.traerPagos(id_caja, id_almacen, id_cajero,fechaInicial, fechaFinal);			
			for (int i = 0; i < alPagos.size(); i++) {
				nombreComercialProveedor = maestroDB.traeNombreComercialProveedor(alPagos.get(i).getId_proveedor());
				tablaPagosModelo.adicionaPagos(new TablaPagos(alPagos.get(i).getId(),alPagos.get(i).getFechapago(), nombreComercialProveedor, alPagos.get(i).getValor_factura(), alPagos.get(i).getValor_descuento(), alPagos.get(i).getValor_pagado()));
			}
		} catch (Exception e) {
			System.out.println("ConPagos.actualizaTablaPagos() ParseException "+e);
			e.printStackTrace();
		}
	}

	private void periodoPagos(int id_caja, int id_almacen) {
		String sFecha = ""+fecha;
		annoDesde = sFecha.substring(0, 4);
		mesDesde = sFecha.substring(5, 7);
		diaDesde = sFecha.substring(8, 10);
		horaDesde = "00"; //sFecha.substring(11, 13);
		minDesde = "00"; //sFecha.substring(14, 16);

		annoHasta = sFecha.substring(0, 4);
		mesHasta = sFecha.substring(5, 7);
		diaHasta = sFecha.substring(8, 10);
		horaHasta = "23"; //sFecha.substring(11, 13);
		minHasta = "59"; //sFecha.substring(14, 16);
		//System.out.println("ConPagos.periodoPagos() sFecha "+sFecha+"  "+annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00");

		intPagos.getIntPagosEncabezado().getJcbAnnoDesde().setSelectedItem(annoDesde);
		intPagos.getIntPagosEncabezado().getJcbMesDesde().setSelectedItem(mesDesde);
		intPagos.getIntPagosEncabezado().getJcbDiaDesde().setSelectedItem(diaDesde);
		intPagos.getIntPagosEncabezado().getJcbHoraDesde().setSelectedItem(horaDesde);
		intPagos.getIntPagosEncabezado().getJcbMinDesde().setSelectedItem(minDesde);

		intPagos.getIntPagosEncabezado().getJcbAnnoHasta().setSelectedItem(annoHasta);
		intPagos.getIntPagosEncabezado().getJcbMesHasta().setSelectedItem(mesHasta);
		intPagos.getIntPagosEncabezado().getJcbDiaHasta().setSelectedItem(diaHasta);
		intPagos.getIntPagosEncabezado().getJcbHoraHasta().setSelectedItem(horaHasta);
		intPagos.getIntPagosEncabezado().getJcbMinHasta().setSelectedItem(minHasta);

		intPagos.getIntPagosEncabezado().getJcbAnnoDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoDesde = (String) intPagos.getIntPagosEncabezado().getJcbAnnoDesde().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbMesDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesDesde = (String) intPagos.getIntPagosEncabezado().getJcbMesDesde().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbDiaDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaDesde = (String) intPagos.getIntPagosEncabezado().getJcbDiaDesde().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbHoraDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				horaDesde = (String) intPagos.getIntPagosEncabezado().getJcbHoraDesde().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbMinDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minDesde = (String) intPagos.getIntPagosEncabezado().getJcbMinDesde().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbAnnoHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoHasta = (String) intPagos.getIntPagosEncabezado().getJcbAnnoHasta().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbMesHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesHasta = (String) intPagos.getIntPagosEncabezado().getJcbMesHasta().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbDiaHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaHasta = (String) intPagos.getIntPagosEncabezado().getJcbDiaHasta().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbHoraHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				horaHasta = (String) intPagos.getIntPagosEncabezado().getJcbHoraHasta().getSelectedItem();
				actualizaTablaPagos();
			}
		});
		intPagos.getIntPagosEncabezado().getJcbMinHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minHasta = (String) intPagos.getIntPagosEncabezado().getJcbMinHasta().getSelectedItem();
				actualizaTablaPagos();
			}
		});

	}

	/*	private Date componeFecha(String anno, String mes, String dia, String hora, String min) {
		try {
			System.out.println("ConPagos.componeFecha() año "+anno+"-"+mes+"-"+dia+" "+hora+":"+min+":"+"00");
			String dateInString = anno+"-"+mes+"-"+dia+" "+hora+":"+min+":"+"00"; //'2018-02-05 00:00:00'
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date = formatter.parse(dateInString);
			System.out.println("ConPagos.componeFecha() date "+date+" formatter.format(date) "+formatter.format(date));
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}*/

	public Date sumarRestarDiasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}

}
