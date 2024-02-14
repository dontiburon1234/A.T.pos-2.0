package modelo;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Cliente;
import clases.Domicilio;
import clases.EntidadBancaria;
import clases.ItemPreFactura;
import clases.ItemPreFormaPago;
import clases.Proveedor;
import clases.TablaFormaPago;
import database.MaestroDB;
import guifactura.IntFormaPago;
import main.Principal;
import poledisplay.OperacionPoleDisplay;
import poledisplay.PoleDisplay;
import util.EntradaTeclado;
import util.FormatoNumero;
import util.G;
import util.TablaFormaPagoModelo;

public class ModeloFacturaFormaPago implements ActionListener, KeyListener, FocusListener, ListSelectionListener {

	private JDialog dialogFormaPago;

	private JTextField txtCampoGained = null;
	private MaestroDB maestroDB;
	private ArrayList<EntidadBancaria> alEntidadBancaria = new ArrayList<EntidadBancaria>();
	private ModFactura modFactura;
	private ListSelectionModel listSelectionModel;
	private int rowDeleteFormaPago = -1;
	private TablaFormaPagoModelo tablaFormaPagoModelo = new TablaFormaPagoModelo();
	private IntFormaPago intFormaPago = new IntFormaPago(tablaFormaPagoModelo);

	// TABLA medio_pago_prefactura
	// id serial NOT NULL,
	private int id_caja = G.getInstance().licenciaGlobal.getIdCaja(); // id_caja integer NOT NULL,
	private int id_prefactura; // id_prefactura integer,
	private int id_medio_pago = 0; // id_medio_pago integer NOT NULL,
	private int item = 0; // item integer,
	private int id_entidad_bancaria = 0; // id_entidad_bancaria integer,
	private int tarjeta = 0; // tarjeta bigint,
	private int autorizacion = 0; // autorizacion bigint,
	private int numero_recibo = 0; // numero_recibo integer,
	private double valor_pago; // valor_pago numeric(20,2),
	private double porcentaje_comision = 0; // porcentaje_comision numeric(5,2),
	private double valor_comision = 0; // valor_comision numeric(20,2),
	private double valor_iva; // valor_iva numeric(30,10),
	private double efectivo_recibido = 0; // efectivo_recibido numeric(20,2),

	private double dTotalFactura;
	private double saldo;
	private int idEntidadBancaria;
	private String nombreEntidadBancaria;
	private double valorIvaMedioPago = 0;
	private String sSaldo;
	private String sTarjeta;
	private String sRecibido;
	private double recibido;
	private TablaFormaPago tablaFormaPago;
	private Domicilio domicilio;
	// private ModeloFacturaFormaPago modeloFacturaFormaPago;

	private String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp ts = java.sql.Timestamp.valueOf(input);
	private Date fecha = ts;
	private boolean agregaPagoRemision = true;
	private OperacionPoleDisplay operacionPoleDisplay;

	public ModeloFacturaFormaPago(ModFactura modFactura, int id_prefactura, ModPrincipal modPrincipal,
			MaestroDB maestroDB, Principal principal, OperacionPoleDisplay operacionPoleDisplay) {
		super();
		this.modFactura = modFactura;
		this.id_prefactura = id_prefactura;
		this.maestroDB = maestroDB;
		this.operacionPoleDisplay = operacionPoleDisplay;

		this.intFormaPago.getIntKeyNumeros().getBtnSiete().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnOcho().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnNueve().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnCuatro().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnCinco().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnSeis().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnUno().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnDos().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnTres().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnCero().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnPunto().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnMenos().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnPor().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnSuprimir().addActionListener(this);
		this.intFormaPago.getIntKeyNumeros().getBtnEnter().addActionListener(this);

		this.intFormaPago.getBtnEfectivo().addActionListener(this);
		this.intFormaPago.getBtnTarjetaCredito().addActionListener(this);
		this.intFormaPago.getBtnTarjetaDebito().addActionListener(this);
		this.intFormaPago.getBtnCredito().addActionListener(this);
		this.intFormaPago.getBtnRemision().addActionListener(this);
		this.intFormaPago.getBtnBorrarItem().addActionListener(this);
		this.intFormaPago.getBtnCancelar().addActionListener(this);
		this.intFormaPago.getBtnMoneda().addActionListener(this);
		// this.intFormaPago.getBtnImprimir().addActionListener(this);

		this.intFormaPago.getTxtCambio().addFocusListener(this);
		this.intFormaPago.getTxtRecibido().addFocusListener(this);
		this.intFormaPago.getTxtValor().addFocusListener(this);
		this.intFormaPago.getTxtNumeroTarjeta().addFocusListener(this);
		this.intFormaPago.getTxtExtrajeraRecibido().addFocusListener(this);

		this.intFormaPago.getTxtCambio().addKeyListener(this);
		this.intFormaPago.getTxtRecibido().addKeyListener(this);
		this.intFormaPago.getTxtValor().addKeyListener(this);
		this.intFormaPago.getTxtNumeroTarjeta().addKeyListener(this);
		this.intFormaPago.getTxtExtrajeraRecibido().addKeyListener(this);

		this.intFormaPago.getTxtDocumento().setFocusable(false);
		this.intFormaPago.getTxtNombre().setFocusable(false);

		listSelectionModel = this.intFormaPago.getTable().getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		this.intFormaPago.getTable().setSelectionModel(listSelectionModel);

		int id_caja = G.getInstance().licenciaGlobal.getIdCaja();

		intFormaPago.getBtnEfectivo().setBackground(Color.YELLOW);

		String sTotalFactura;
		dTotalFactura = maestroDB.totalFactura(id_prefactura, id_caja);
		sTotalFactura = FormatoNumero.formatoCero(dTotalFactura);
		intFormaPago.getTxtTotalfactura().setText(sTotalFactura);

		saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
		sSaldo = FormatoNumero.formatoCero(saldo);
		intFormaPago.getTxtValor().setText(sSaldo);

		valor_iva = 0;
		ArrayList<ItemPreFactura> alItemPreFactura = new ArrayList<ItemPreFactura>();
		alItemPreFactura = maestroDB.traerItemPreFactura(id_caja, id_prefactura);
		for (int i = 0; i < alItemPreFactura.size(); i++) {
			valor_iva = valor_iva + alItemPreFactura.get(i).getValor_iva();

			/*
			 * System.out.println("ModeloFacturaFormaPago.actionPerformed() cantidad "+
			 * alItemPreFactura.get(i).getCantidad_unidad_medida()
			 * +" IVA "+alItemPreFactura.get(i).getValor_iva()+
			 * " unitario "+alItemPreFactura.get(i).getValor_unidad()+" valor_iva "
			 * +valor_iva);
			 */

		}

		id_medio_pago = 1;
		mostrar(1);
		alEntidadBancaria = maestroDB.traeEntidadBancaria();
		this.intFormaPago.getCbEntidad().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nombreEntidadBancaria = (String) intFormaPago.getCbEntidad().getSelectedItem();
				for (int i = 0; i < alEntidadBancaria.size(); i++) {
					if (alEntidadBancaria.get(i).getNombre().equals(nombreEntidadBancaria)) {
						idEntidadBancaria = alEntidadBancaria.get(i).getId();
						porcentaje_comision = alEntidadBancaria.get(i).getComision();

						System.out.println(
								"ModeloFacturaFormaPago.ModeloFacturaFormaPago(...).new ActionListener() {...}.actionPerformed() nombreEntidadBancaria "
										+ nombreEntidadBancaria + " porcentaje_comision " + porcentaje_comision);

					}
				}
			}
		});

		ArrayList<ItemPreFormaPago> alItemPreFormaPago = new ArrayList<ItemPreFormaPago>();
		alItemPreFormaPago = maestroDB.traerFormaPago(id_caja, id_prefactura);
		String formaPago = "";
		for (int i = 0; i < alItemPreFormaPago.size(); i++) {
			formaPago = maestroDB.nombreMedioPago(alItemPreFormaPago.get(i).getId_medio_pago());
			double valorPago;
			if (alItemPreFormaPago.get(i).getId_medio_pago() == 1) {
				valorPago = alItemPreFormaPago.get(i).getEfectivo_recibido();
			} else {
				valorPago = alItemPreFormaPago.get(i).getValor_pago();
			}
			TablaFormaPago tablaFormaPago = new TablaFormaPago(alItemPreFormaPago.get(i).getEfectivo_recibido(),
					formaPago, alItemPreFormaPago.get(i).getValor_pago() - alItemPreFormaPago.get(i).getValor_iva(),
					alItemPreFormaPago.get(i).getValor_iva(), alItemPreFormaPago.get(i).getValor_pago(),
					valorPago - alItemPreFormaPago.get(i).getValor_pago());
			tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);
		}

		// averiguar si ya está registrado el cliente en la factura mediante doimicilo
		// para credito o remisión
		int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
		domicilio = maestroDB.traerDomicilio(id_prefactura, id_almacen);

		if (domicilio != null) {
			String sDocumento = FormatoNumero.formatoCero(domicilio.getDocumento());
			intFormaPago.getTxtDocumento().setText(sDocumento);
			intFormaPago.getTxtNombre().setText(domicilio.getNombre() + " " + domicilio.getApellido());
		}

		ModeloBuscarProveedor modeloBuscarProveedor = new ModeloBuscarProveedor(this, maestroDB);
		intFormaPago.getChckbxProveedor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (intFormaPago.getChckbxProveedor().isSelected()) {
					modeloBuscarProveedor.buscarProveedor(principal);
				} else {
					intFormaPago.getTxtDocumento().setText("");
					intFormaPago.getTxtNombre().setText("");
				}
			}
		});
	}

	public void formaPago(Principal principal) {

		// this.principal = principal;
		System.out.println("ModeloFacturaFormaPago.formaPago() ANTES DE id_prefactura != 0 id_prefactura "
				+ id_prefactura + " G.getInstance().idPreFacturaActual " + G.getInstance().idPreFacturaActual);
		if (id_prefactura != 0) {
			// System.out.println("ModeloFacturaFormaPago.formaPago() id_prefactura != 0
			// id_prefactura "+id_prefactura);
			dialogFormaPago = new JDialog(principal,
					"ATpos - Administraci\u00F3n Total del punto de venta - Forma de pago",
					Dialog.ModalityType.DOCUMENT_MODAL);
			Toolkit tamano = Toolkit.getDefaultToolkit();
			int ancho = (int) ((tamano.getScreenSize().getWidth() - 1050) / 2);
			int alto = (int) ((tamano.getScreenSize().getHeight() - 600) / 2);
			dialogFormaPago.setBounds(ancho, alto, 1050, 600);
			ModeloFacturaFormaPago.addEscapeListenerWindowDialog(dialogFormaPago);

			dialogFormaPago.addWindowFocusListener(new WindowAdapter() {
				public void windowGainedFocus(WindowEvent e) {
					intFormaPago.getTxtRecibido().requestFocusInWindow();
				}
			});

			JPanel jpFormaPago = new JPanel();
			jpFormaPago.add(intFormaPago);
			intFormaPago.getTxtRecibido().requestFocus();
			dialogFormaPago.getContentPane().add(jpFormaPago);
			dialogFormaPago.setVisible(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		item = maestroDB.traerItemFormaPgo(id_caja, G.getInstance().idPreFacturaActual);
		JButton jButton = (JButton) e.getSource();
		if (jButton.getText().equals("Efectivo")) {
			System.out.println("ModFactura.actionPerformed() Efectivo");
			id_medio_pago = 1;
			mostrar(1);
		} else if (jButton.getText().equals("TarjetaCr\u00E9dito")) {
			System.out.println("ModFactura.actionPerformed() TarjetaCredito");
			intFormaPago.getCbEntidad().removeAllItems();
			for (int i = 0; i < alEntidadBancaria.size(); i++) {
				if (alEntidadBancaria.get(i).getId_medio_pago() == 2) {
					intFormaPago.getCbEntidad().addItem(alEntidadBancaria.get(i).getNombre());
				}
			}
			id_medio_pago = 2;
			mostrar(2);
		} else if (jButton.getText().equals("TarjetaD\u00E9bito")) {
			System.out.println("ModFactura.actionPerformed() TarjetaDebito");
			intFormaPago.getCbEntidad().removeAllItems();
			for (int i = 0; i < alEntidadBancaria.size(); i++) {
				if (alEntidadBancaria.get(i).getId_medio_pago() == 3) {
					intFormaPago.getCbEntidad().addItem(alEntidadBancaria.get(i).getNombre());
				}
			}
			id_medio_pago = 3;
			mostrar(3);
		} else if (jButton.getText().equals("Cr\u00E9dito")) {
			System.out.println("ModFactura.actionPerformed Crédito");
			id_medio_pago = 7;
			mostrar(7);

			if (domicilio == null) {
				JOptionPane.showMessageDialog(null, "3 Debe seleccionar un cliente.", "Requerimiento de pago",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (jButton.getText().equals("Remisi\u00F3n")) {
			System.out.println("ModFactura.actionPerformed() Remisión");
			id_medio_pago = 8;
			mostrar(8);

			if (domicilio == null) {
				JOptionPane.showMessageDialog(null,
						"Debe seleccionar un cliente o proveedor para REMISIÓN de la mercancía.",
						"Requerimiento de pago", JOptionPane.ERROR_MESSAGE);
			}
		} else if (jButton.getText().equals("Dolar")) {
			System.out.println(
					"ModeloFacturaFormaPago.actionPerformed()Seleccionó forma de pago Dolar----------------------------");

			intFormaPago.getCbEntidad().removeAllItems();
			for (int i = 0; i < alEntidadBancaria.size(); i++) {
				if (alEntidadBancaria.get(i).getId_medio_pago() == 9) {
					intFormaPago.getCbEntidad().addItem(alEntidadBancaria.get(i).getNombre());
				}
			}
			id_medio_pago = 9;
			mostrar(9);

		} else if (jButton.getText().equals("BorrarItem")) {
			System.out.println("ModeloFacturaFormaPago.actionPerformed() BorrarItem");
			tablaFormaPagoModelo.borraFormaPago(rowDeleteFormaPago);

			maestroDB.borraItemFormaPago(id_prefactura, rowDeleteFormaPago, id_caja);
			rowDeleteFormaPago = -1;
			saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);

			sSaldo = FormatoNumero.formatoCero(saldo);
			intFormaPago.getTxtValor().setText(sSaldo);
			intFormaPago.getTxtCambio().setText("");
			intFormaPago.getTxtExtrajeraRecibido().setText("");
		} else if (jButton.getText().equals("Cancelar")) {
			System.out.println("ModFactura.actionPerformed() Cancelar");
			dialogFormaPago.dispose();
		} else if (jButton.getText().equals("Supr")) {
			txtCampoGained.setText("");
			txtCampoGained.requestFocus();
		} else if (jButton.getText().equals("Intro")) {
			System.out.println("ModeloFacturaFormaPago.actionPerformed() Intro Intro Intro Intro ");
			saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
			if (saldo <= 1)
				saldo = 0;
			String sValor_pago = intFormaPago.getTxtValor().getText();
			sValor_pago = sValor_pago.replace(",", "");

			if (sValor_pago.equals("")) {
				valor_pago = 0;
			} else {
				valor_pago = Double.valueOf(sValor_pago); // valor_pago numeric(20,2),
			}

			if (saldo > 0) {
				switch (id_medio_pago) {
				case 1: // EFECTIVO
					String sEfectivo_recibido = intFormaPago.getTxtRecibido().getText();
					if (intFormaPago.getTxtRecibido().getText().equals("")) {
						sEfectivo_recibido = "0";
					} else {
						sEfectivo_recibido = sEfectivo_recibido.replace(",", "");
						efectivo_recibido = Double.valueOf(sEfectivo_recibido); // efectivo_recibido numeric(20,2),
					}

					if (valor_pago > dTotalFactura) {
						System.out.println("ModeloFacturaFormaPago.actionPerformed() EFECTIVO valor_pago " + valor_pago
								+ " dTotalFactura " + dTotalFactura + " sSaldo " + sSaldo + " Saldo " + saldo);
						JOptionPane.showMessageDialog(null,
								"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
						saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
						sSaldo = FormatoNumero.formatoCero(saldo);
						intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());
						
					} else {

						String sCambio = "";
						if (valor_pago <= efectivo_recibido && (valor_pago != 0 && efectivo_recibido != 0)) {
							item++; // item integer,
							double cambio = efectivo_recibido - valor_pago;
							sCambio = FormatoNumero.formatoCero(cambio);
							intFormaPago.getTxtCambio().setText(sCambio);

							tarjeta = 0; // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = (porcentaje_comision / 100) * valor_pago; // valor_comision numeric(20,2),
							porcentaje_comision = 0;
							valor_comision = 0;
							numero_recibo = 0;
							id_entidad_bancaria = 0;
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;

							TablaFormaPago tablaFormaPago = new TablaFormaPago(efectivo_recibido, "EFECTIVO",
									valor_pago - valorIvaMedioPago, valorIvaMedioPago, valor_pago, cambio);
							tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);
							maestroDB.agragarFormaPago(id_caja, id_prefactura, id_medio_pago, item, id_entidad_bancaria,
									tarjeta, autorizacion, numero_recibo, valor_pago, porcentaje_comision,
									valor_comision, valorIvaMedioPago, efectivo_recibido, fecha, "insert");

							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							if (saldo == 0) {
								intFormaPago.getTxtValor().setText("");
								intFormaPago.getTxtRecibido().setText("");
							} else {
								intFormaPago.getTxtRecibido().requestFocus();
							}
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);
							intFormaPago.getTxtRecibido().setText("");

							if (G.getInstance().defaultPort != null) {
								sEfectivo_recibido = FormatoNumero.formatoCero(efectivo_recibido);
								int iEfectivo_recibido = sEfectivo_recibido.length();
								String espacioEfectivo = "";
								for (int i = 0; i < (12 - iEfectivo_recibido); i++) {
									espacioEfectivo = espacioEfectivo + " ";
								}
								String svalor_pago = FormatoNumero.formatoCero(valor_pago);
								int ivalor_pago = svalor_pago.length();
								int totalSL = ivalor_pago + sCambio.length();
								String espacioEfectivoSL = "";
								for (int i = 0; i < (20 - totalSL); i++) {
									espacioEfectivoSL = espacioEfectivoSL + " ";
								}

								Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
										"Efectivo" + espacioEfectivo + sEfectivo_recibido,
										svalor_pago + espacioEfectivoSL + sCambio, false);
								Thread t = new Thread(r);
								t.start();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Recibido debe ser mayor que con este medio de pago");
							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);
							intFormaPago.getTxtRecibido().setText("");
						}
					}
					break;
				case 2: // TARJETA DE CREDITO
					item++; // item integer,
					id_entidad_bancaria = idEntidadBancaria; // id_entidad_bancaria integer,

					sTarjeta = intFormaPago.getTxtNumeroTarjeta().getText();
					sTarjeta = sTarjeta.replace(",", "");

					if (valor_pago > dTotalFactura) {
						System.out.println("ModeloFacturaFormaPago.actionPerformed() TARJETA DE CREDITO valor_pago "
								+ valor_pago + " dTotalFactura " + dTotalFactura);
						JOptionPane.showMessageDialog(null,
								"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
						saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
						sSaldo = FormatoNumero.formatoCero(saldo);
						intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());

					} else {

						if (sTarjeta.equals("") || intFormaPago.getTxtValor().getText().equals("")) {
							if (sTarjeta.equals("")) {
								JOptionPane.showMessageDialog(null, "Coloque los 4 últimos números de la tarjeta");
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe colocar el valor que va a cancelar con la tarjeta en 'Con este medio de pago'");
							}
						} else {
							tarjeta = Integer.valueOf(sTarjeta); // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = (porcentaje_comision / 100) * valor_pago; // valor_comision numeric(20,2),
							efectivo_recibido = 0; // efectivo_recibido numeric(20,2),

							sRecibido = intFormaPago.getTxtValor().getText();
							sRecibido = sRecibido.replace(",", "");
							if (sRecibido.equals("")) {
								recibido = 0;
							} else {
								recibido = Double.valueOf(sRecibido);
							}
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;

							tablaFormaPago = new TablaFormaPago(recibido, "TARJ CREDITO",
									valor_pago - valorIvaMedioPago, valorIvaMedioPago, valor_pago, 0);
							tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);

							maestroDB.agragarFormaPago(id_caja, id_prefactura, id_medio_pago, item, id_entidad_bancaria,
									tarjeta, autorizacion, numero_recibo, valor_pago, porcentaje_comision,
									valor_comision, valorIvaMedioPago, efectivo_recibido, fecha, "insert");

							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							if (saldo == 0) {
								intFormaPago.getTxtValor().setText("");
								intFormaPago.getTxtRecibido().setText("");
							}
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);
							intFormaPago.getTxtRecibido().setText("");
							intFormaPago.getTxtNumeroTarjeta().setText("");
							intFormaPago.getTxtRecibido().requestFocus();

							if (G.getInstance().defaultPort != null) {
								String sRecibido = FormatoNumero.formatoCero(recibido);
								int iRecibido = sRecibido.length();
								String espacioTC_PL = "";
								for (int i = 0; i < (8 - iRecibido); i++) {
									espacioTC_PL = espacioTC_PL + " ";
								}
								String svalor_pago = FormatoNumero.formatoCero(valor_pago);
								int ivalor_pago = svalor_pago.length();
								int totalSL = ivalor_pago + 1; // sCambio.length();
								String espacioTC_SL = "";
								for (int i = 0; i < (20 - totalSL); i++) {
									espacioTC_SL = espacioTC_SL + " ";
								}

								Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
										"Tarj Credito" + espacioTC_PL + sRecibido, svalor_pago + espacioTC_SL + 0,
										false);
								Thread t = new Thread(r);
								t.start();
							}
						}
					}
					break;

				case 3: // TARJETA DEBITO
					item++; // item integer,
					id_entidad_bancaria = idEntidadBancaria; // id_entidad_bancaria integer,

					sTarjeta = intFormaPago.getTxtNumeroTarjeta().getText();
					sTarjeta = sTarjeta.replace(",", "");

					if (valor_pago > dTotalFactura) {
						System.out.println("ModeloFacturaFormaPago.actionPerformed() TARJETA DEBITO valor_pago "
								+ valor_pago + " dTotalFactura " + dTotalFactura);
						JOptionPane.showMessageDialog(null,
								"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
						saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
						sSaldo = FormatoNumero.formatoCero(saldo);
						intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());
					} else {

						if (sTarjeta.equals("") || intFormaPago.getTxtValor().getText().equals("")) {
							if (sTarjeta.equals("")) {
								JOptionPane.showMessageDialog(null, "Coloque los 4 últimos números de la tarjeta");
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe colocar el valor que va a cancelar con la tarjeta en 'Con este medio de pago'");
							}

						} else {
							tarjeta = Integer.valueOf(sTarjeta); // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = (porcentaje_comision / 100) * valor_pago; // valor_comision numeric(20,2),
							efectivo_recibido = 0; // efectivo_recibido numeric(20,2),

							sRecibido = intFormaPago.getTxtValor().getText();
							sRecibido = sRecibido.replace(",", "");
							recibido = Double.valueOf(sRecibido);
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;

							tablaFormaPago = new TablaFormaPago(recibido, "TARJ DEBITO", valor_pago - valorIvaMedioPago,
									valorIvaMedioPago, valor_pago, 0);
							tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);

							maestroDB.agragarFormaPago(id_caja, id_prefactura, id_medio_pago, item, id_entidad_bancaria,
									tarjeta, autorizacion, numero_recibo, valor_pago, porcentaje_comision,
									valor_comision, valorIvaMedioPago, efectivo_recibido, fecha, "insert");

							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							if (saldo == 0) {
								intFormaPago.getTxtValor().setText("");
								intFormaPago.getTxtRecibido().setText("");
							} else {
								intFormaPago.getTxtRecibido().requestFocus();
							}
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);
							intFormaPago.getTxtRecibido().setText("");
							intFormaPago.getTxtNumeroTarjeta().setText("");

							if (G.getInstance().defaultPort != null) {
								String sRecibido = FormatoNumero.formatoCero(recibido);
								int iRecibido = sRecibido.length();
								String espacioTC_PL = "";
								for (int i = 0; i < (9 - iRecibido); i++) {
									espacioTC_PL = espacioTC_PL + " ";
								}
								String svalor_pago = FormatoNumero.formatoCero(valor_pago);
								int ivalor_pago = svalor_pago.length();
								int totalSL = ivalor_pago + 1; // sCambio.length();
								String espacioTC_SL = "";
								for (int i = 0; i < (20 - totalSL); i++) {
									espacioTC_SL = espacioTC_SL + " ";
								}

								Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
										"Tarj Debito" + espacioTC_PL + sRecibido, svalor_pago + espacioTC_SL + 0,
										false);
								Thread t = new Thread(r);
								t.start();
							}

						}
					}
					break;

				case 4: // REDENCION BONO
					break;
				case 5: // CONSIGNACION
					break;
				case 6: // CAMBIO MERCANCIA
					break;
				case 7: // CREDITO
					if (domicilio != null) {

						if (valor_pago > dTotalFactura) {
							System.out.println("ModeloFacturaFormaPago.actionPerformed() CREDITO valor_pago "
									+ valor_pago + " dTotalFactura " + dTotalFactura);
							JOptionPane.showMessageDialog(null,
									"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());
						} else {
							item++; // item integer,
							tarjeta = 0; // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = (porcentaje_comision / 100) * valor_pago; // valor_comision numeric(20,2),
							porcentaje_comision = 0;
							valor_comision = 0;
							numero_recibo = 0;
							id_entidad_bancaria = 0;
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;
							efectivo_recibido = 0;

							TablaFormaPago tablaFormaPago = new TablaFormaPago(efectivo_recibido, "CREDITO",
									valor_pago - valorIvaMedioPago, valorIvaMedioPago, valor_pago, 0);
							tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);
							maestroDB.agragarFormaPago(id_caja, id_prefactura, id_medio_pago, item, id_entidad_bancaria,
									tarjeta, autorizacion, numero_recibo, valor_pago, porcentaje_comision,
									valor_comision, valorIvaMedioPago, efectivo_recibido, fecha, "insert");

							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							if (saldo == 0) {
								intFormaPago.getTxtValor().setText("");
								intFormaPago.getTxtRecibido().setText("");
							} else {
								intFormaPago.getTxtRecibido().requestFocus();
							}
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);
							intFormaPago.getTxtRecibido().setText("");

							if (G.getInstance().defaultPort != null) {
								sEfectivo_recibido = FormatoNumero.formatoCero(efectivo_recibido);
								int iEfectivo_recibido = sEfectivo_recibido.length();
								String espacioEfectivo = "";
								for (int i = 0; i < (13 - iEfectivo_recibido); i++) {
									espacioEfectivo = espacioEfectivo + " ";
								}
								String svalor_pago = FormatoNumero.formatoCero(valor_pago);
								int ivalor_pago = svalor_pago.length();
								int totalSL = ivalor_pago + 1;
								String espacioEfectivoSL = "";
								for (int i = 0; i < (20 - totalSL); i++) {
									espacioEfectivoSL = espacioEfectivoSL + " ";
								}

								Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
										"Credito" + espacioEfectivo + sEfectivo_recibido,
										svalor_pago + espacioEfectivoSL + 0, false);
								Thread t = new Thread(r);
								t.start();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar primero el cliente.",
								"Requerimiento de pago", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 8: // REMISION
					System.out.println("ModeloFacturaFormaPago.actionPerformed() ingresó a case 8: // REMISION");
					// no puede haber pagos parciales en una remisión
					saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);

					// Debe haber seleccionado cliente o proveedor para realizar la remisión
					if (intFormaPago.getTxtDocumento().getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Debe seleccionar un cliente o proveedor para REMISIÓN de la mercancía.",
								"Requerimiento de pago", JOptionPane.ERROR_MESSAGE);
					} else if (dTotalFactura == saldo) {
						System.out.println("ModeloFacturaFormaPago.actionPerformed() PUEDE HACER LA REMISION");

						if (valor_pago > dTotalFactura) {
							System.out.println("ModeloFacturaFormaPago.actionPerformed() REMISION valor_pago "
									+ valor_pago + " dTotalFactura " + dTotalFactura);
							JOptionPane.showMessageDialog(null,
									"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
							saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
							sSaldo = FormatoNumero.formatoCero(saldo);
							intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());
						} else {

							item++; // item integer,
							tarjeta = 0; // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = (porcentaje_comision / 100) * valor_pago; // valor_comision numeric(20,2),
							porcentaje_comision = 0;
							valor_comision = 0;
							numero_recibo = 0;
							id_entidad_bancaria = 0;
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;
							efectivo_recibido = 0;

							int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
							int id_cajero = G.getInstance().getIdCajero;
							int id_vendedor = maestroDB.traeIdVendedorParaRemision(id_prefactura);
							String id_usuario_vendedor = maestroDB.traeIdUsuarioVendedorParaRemision(id_prefactura);
							double valor_remision = dTotalFactura;
							// String proveedor = "";

							if (agregaPagoRemision) {
								// agrega a la tabla de formas de pago la remisión
								tablaFormaPago = new TablaFormaPago(efectivo_recibido, "REMISIÓN",
										valor_pago - valorIvaMedioPago, valorIvaMedioPago, valor_pago, 0);
								tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);
								agregaPagoRemision = false;

								if (G.getInstance().defaultPort != null) {
									sEfectivo_recibido = FormatoNumero.formatoCero(efectivo_recibido);
									int iEfectivo_recibido = sEfectivo_recibido.length();
									String espacioEfectivo = "";
									for (int i = 0; i < (12 - iEfectivo_recibido); i++) {
										espacioEfectivo = espacioEfectivo + " ";
									}
									String svalor_pago = FormatoNumero.formatoCero(valor_pago);
									int ivalor_pago = svalor_pago.length();
									int totalSL = ivalor_pago + 1;
									String espacioEfectivoSL = "";
									for (int i = 0; i < (20 - totalSL); i++) {
										espacioEfectivoSL = espacioEfectivoSL + " ";
									}

									Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
											"Remision" + espacioEfectivo + sEfectivo_recibido,
											svalor_pago + espacioEfectivoSL + 0, false);
									Thread t = new Thread(r);
									t.start();
								}
							} else

							// si ya agregó una forma de pago de remisión puede completar el proceso.
							if (intFormaPago.getTable().getRowCount() == 1) {
								System.out.println(
										"ModeloFacturaFormaPago.actionPerformed() En este momento pasa a remisión e imprime  ");
								int numero_remision = maestroDB.maximoRemision() + 1;
								String sDocumento = intFormaPago.getTxtDocumento().getText();
								sDocumento = sDocumento.replace(",", "");
								long lDocumento = Long.parseLong(sDocumento);
								boolean proveedorSeleccionado = intFormaPago.getChckbxProveedor().isSelected();
								System.out.println("ModeloFacturaFormaPago.actionPerformed() numero_remision = "
										+ numero_remision + " sDocumento = " + sDocumento + " proveedorSeleccionado ="
										+ proveedorSeleccionado);

								Proveedor proveedorClase;
								Cliente cliente;
								if (proveedorSeleccionado) {
									proveedorClase = maestroDB.traeProveedor(lDocumento);
									maestroDB.agregarRemision(id_caja, id_almacen, numero_remision, id_cajero,
											id_vendedor, id_usuario_vendedor, proveedorClase.getDocumento(),
											proveedorClase.getNombre(), "", proveedorClase.getDireccion(),
											proveedorClase.getTelefono(), "true", valor_remision, valor_iva,
											id_prefactura, proveedorClase.getId());

									int id_prefactura = G.getInstance().idPreFacturaActual;

									maestroDB.pasarItemPrefacturaAItemRemision(id_prefactura, numero_remision, id_caja,
											valor_remision, proveedorClase);

									maestroDB.actualizaPrefacturaConRemision(numero_remision, id_prefactura, id_caja);
									dialogFormaPago.dispose();

									modFactura.BorrarTablaCapturaArticulo(
											modFactura.getConFactura().getTablaFacturaModelo());

									G.getInstance().idPreFacturaActual = 0;
									try {
										modFactura.getConFactura().getIntPendientes().getListModel()
												.removeAllElements();
										modFactura.getConFactura()
												.setAlFacturasPendientes(maestroDB.facturasPendientes());
										String nombreDomicilio = "";
										if (modFactura.getConFactura().getAlFacturasPendientes() != null) {
											for (int j = 0; j < modFactura.getConFactura().getAlFacturasPendientes()
													.size(); j++) {
												nombreDomicilio = maestroDB.consultarDomicilio(modFactura
														.getConFactura().getAlFacturasPendientes().get(j).toString());
												if (nombreDomicilio == null)
													nombreDomicilio = "";
												modFactura.getConFactura().getIntPendientes().getListModel()
														.addElement("" + modFactura.getConFactura()
																.getAlFacturasPendientes().get(j).toString() + "-"
																+ nombreDomicilio);
											}

											modFactura.getConFactura().getIntListaCompra().definirAnchoColmunas();
											modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo()
													.requestFocus();
										}
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo()
											.requestFocus();
									System.out.println(
											"ModeloFacturaFormaPago.actionPerformed() REMISION PROVEEDOR REMISION PROVEEDOR REMISION PROVEEDOR REMISION PROVEEDOR ");

								} else {
									System.out.println(
											"ModeloFacturaFormaPago.actionPerformed() SELECCIONO REMISIÓN A CLIENTE");

									cliente = maestroDB.consultarCliente(sDocumento);
									maestroDB.agregarRemision(id_caja, id_almacen, numero_remision, id_cajero,
											id_vendedor, id_usuario_vendedor, cliente.getDocumento(),
											cliente.getNombre(), cliente.getApellido(), cliente.getDireccion(),
											cliente.getTelefono(), "false", valor_remision, valor_iva, id_prefactura,
											cliente.getId());

									int id_prefactura = G.getInstance().idPreFacturaActual;

									maestroDB.pasarItemPrefacturaAItemRemision(id_prefactura, numero_remision, id_caja,
											valor_remision, null);

									maestroDB.actualizaPrefacturaConRemision(numero_remision, id_prefactura, id_caja);
									dialogFormaPago.dispose();

									modFactura.BorrarTablaCapturaArticulo(
											modFactura.getConFactura().getTablaFacturaModelo());
									G.getInstance().idPreFacturaActual = 0;
									try {
										modFactura.getConFactura().getIntPendientes().getListModel()
												.removeAllElements();
										modFactura.getConFactura()
												.setAlFacturasPendientes(maestroDB.facturasPendientes());
										String nombreDomicilio = "";
										if (modFactura.getConFactura().getAlFacturasPendientes() != null) {
											for (int j = 0; j < modFactura.getConFactura().getAlFacturasPendientes()
													.size(); j++) {
												nombreDomicilio = maestroDB.consultarDomicilio(modFactura
														.getConFactura().getAlFacturasPendientes().get(j).toString());
												if (nombreDomicilio == null)
													nombreDomicilio = "";
												modFactura.getConFactura().getIntPendientes().getListModel()
														.addElement("" + modFactura.getConFactura()
																.getAlFacturasPendientes().get(j).toString() + "-"
																+ nombreDomicilio);
											}

											modFactura.getConFactura().getIntListaCompra().definirAnchoColmunas();

											modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo()
													.requestFocus();
										}
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo()
											.requestFocus();
									System.out.println(
											"ModeloFacturaFormaPago.actionPerformed() REMISION CLIENTE REMISION CLIENTE REMISION CLIENTE REMISION CLIENTE ");
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "No puede combinar Formas de pago con REMISIÓN");
					}

					// *******************************************************************************************************************
					break;
				case 9:
					System.out.println("ModeloFacturaFormaPago.actionPerformed() Moneda Dolar o Euro case 9:");
					// *****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro
					item++; // item integer,
					id_entidad_bancaria = idEntidadBancaria;

					sTarjeta = intFormaPago.getTxtExtrajeraRecibido().getText();

					if (valor_pago > dTotalFactura) {
						System.out.println("ModeloFacturaFormaPago.actionPerformed() MONEDA EXTRANJERA valor_pago "
								+ valor_pago + " dTotalFactura " + dTotalFactura);
						JOptionPane.showMessageDialog(null,
								"El valor a pagar \"Con este medio de Pago\" debe ser menor o igual al saldo total de la factura");
						saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
						sSaldo = FormatoNumero.formatoCero(saldo);
						intFormaPago.getTxtValor().setText(sSaldo);//intFormaPago.getTxtTotalfactura().getText());
					} else {

						if (sTarjeta.equals("") || intFormaPago.getTxtValor().getText().equals("")) {
							if (sTarjeta.equals("")) {
								JOptionPane.showMessageDialog(null,
										"Coloque la cantidad de moneda extranjera que recibe");
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe colocar el valor que va a cancelar, con la moneda extranjera en 'Con este medio de pago'");
							}

						} else {
							tarjeta = 0; // Integer.valueOf(sTarjeta); // tarjeta bigint,
							autorizacion = 0; // autorizacion bigint,
							numero_recibo = 0; // numero_recibo integer,
							valor_comision = porcentaje_comision; // porcentaje_comision * tarjeta; // valor_comision
																	// numeric(20,2),
							efectivo_recibido = 0; // efectivo_recibido numeric(20,2),

							sRecibido = intFormaPago.getTxtValor().getText();
							sRecibido = sRecibido.replace(",", "");
							recibido = Double.valueOf(sRecibido);
							valorIvaMedioPago = valor_iva * valor_pago / dTotalFactura;

							recibido = porcentaje_comision * Double.valueOf(sTarjeta);

							if (valor_pago <= recibido) {
								System.out.println("ModeloFacturaFormaPago.actionPerformed() 1 valor_pago " + valor_pago
										+ " recibido " + recibido);
								tablaFormaPago = new TablaFormaPago(recibido, "EXTRANJERA",
										valor_pago - valorIvaMedioPago, valorIvaMedioPago, valor_pago,
										recibido - valor_pago);
								tablaFormaPagoModelo.adicionaFormaPago(tablaFormaPago);

								System.out.println("ModeloFacturaFormaPago.actionPerformed() sRecibido " + sRecibido
										+ " id_caja " + id_caja + " id_prefactura " + id_prefactura + " id_medio_pago "
										+ id_medio_pago + " item " + item + " id_entidad_bancaria "
										+ id_entidad_bancaria + " tarjeta " + tarjeta + " autorizacion " + autorizacion
										+ " numero_recibo " + numero_recibo + " valor_pago " + valor_pago
										+ " porcentaje_comision " + porcentaje_comision + " valor_comision "
										+ valor_comision + " valorIvaMedioPago " + valorIvaMedioPago
										+ " efectivo_recibido " + efectivo_recibido + " fecha " + fecha + " insert");

								efectivo_recibido = recibido;

								maestroDB.agragarFormaPago(id_caja, id_prefactura, id_medio_pago, item,
										id_entidad_bancaria, tarjeta, autorizacion, numero_recibo, valor_pago,
										porcentaje_comision, valor_comision, valorIvaMedioPago, efectivo_recibido,
										fecha, "insert");

								String xxx = String.valueOf(efectivo_recibido / porcentaje_comision);
								String comentarioDomicilio = "PAGO EN MONEDA EXTRANJERA " + xxx;

								maestroDB.actualizaComentarioPreFactura(comentarioDomicilio,
										G.getInstance().idPreFacturaActual, id_caja);

								saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
								if (saldo == 0) {
									intFormaPago.getTxtValor().setText("");
									intFormaPago.getTxtRecibido().setText("");
								} else {
									intFormaPago.getTxtRecibido().requestFocus();
								}
								sSaldo = FormatoNumero.formatoCero(saldo);
								intFormaPago.getTxtValor().setText(sSaldo);
								intFormaPago.getTxtRecibido().setText("");
								intFormaPago.getTxtExtrajeraRecibido().setText("");

								if (G.getInstance().defaultPort != null) {
									String sRecibido = FormatoNumero.formatoCero(recibido);
									int iRecibido = sRecibido.length();
									String espacioTC_PL = "";
									for (int i = 0; i < (9 - iRecibido); i++) {
										espacioTC_PL = espacioTC_PL + " ";
									}
									String svalor_pago = FormatoNumero.formatoCero(valor_pago);
									int ivalor_pago = svalor_pago.length();
									int totalSL = ivalor_pago + 1; // sCambio.length();
									String espacioTC_SL = "";
									for (int i = 0; i < (20 - totalSL); i++) {
										espacioTC_SL = espacioTC_SL + " ";
									}

									Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB,
											"Moneda" + espacioTC_PL + sRecibido, svalor_pago + espacioTC_SL + 0, false);
									Thread t = new Thread(r);
									t.start();
								}
							} else {
								System.out.println("ModeloFacturaFormaPago.actionPerformed() 2 valor_pago " + valor_pago
										+ " recibido " + recibido);
								JOptionPane.showMessageDialog(null,
										"Recibido debe ser mayor que con este medio de pago");
								/*
								 * saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
								 * sSaldo = FormatoNumero.formatoCero(saldo);
								 * intFormaPago.getTxtValor().setText(sSaldo);
								 * intFormaPago.getTxtRecibido().setText("");
								 */
							}
						}
					}
					// *****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro*****Moneda*Dolar*Euro
					break;

				default:
					break;
				}

			} else {

				if (id_medio_pago != 8) { // si no es una remisión pasa como facturación y la imprime
					// JOptionPane.showMessageDialog(null, "El pago de la factura está completo");
					// ****************************************************************************************************************
					System.out.println(
							"ModFactura.actionPerformed() Imprimir Imprimir Imprimir Imprimir Imprimir Imprimir Imprimir Imprimir");
					boolean existeFactura = maestroDB.existeFactura(id_prefactura, id_caja);
					System.out.println("ModeloFacturaFormaPago.actionPerformed() existeFactura " + existeFactura
							+ " id_prefactura " + id_prefactura);

					maestroDB.pasarPreAPos(id_prefactura, id_caja);

					/*
					 * if (!existeFactura) { if (id_prefactura == 0) {
					 * JOptionPane.showMessageDialog(null,
					 * "Comuniquese con el administrador, error al registrar la factura.\n Prefactura "
					 * + id_prefactura); } else {
					 * 
					 * System.out.println("ModeloFacturaFormaPago.actionPerformed() id_prefactura "
					 * +id_prefactura+" id_caja "+id_caja);
					 * 
					 * maestroDB.pasarPreAPos(id_prefactura, id_caja); } } else {
					 * JOptionPane.showMessageDialog(null, "Compra ya facturada"); }
					 */
					dialogFormaPago.dispose();
					modFactura.BorrarTablaCapturaArticulo(modFactura.getConFactura().getTablaFacturaModelo());
					G.getInstance().idPreFacturaActual = 0;
					try {
						modFactura.getConFactura().getIntPendientes().getListModel().removeAllElements();
						modFactura.getConFactura().setAlFacturasPendientes(maestroDB.facturasPendientes());
						String nombreDomicilio = "";
						if (modFactura.getConFactura().getAlFacturasPendientes() != null) {
							for (int j = 0; j < modFactura.getConFactura().getAlFacturasPendientes().size(); j++) {
								nombreDomicilio = maestroDB.consultarDomicilio(
										modFactura.getConFactura().getAlFacturasPendientes().get(j).toString());
								if (nombreDomicilio == null)
									nombreDomicilio = "";
								modFactura.getConFactura().getIntPendientes().getListModel()
										.addElement(""
												+ modFactura.getConFactura().getAlFacturasPendientes().get(j).toString()
												+ "-" + nombreDomicilio);
							}

							modFactura.getConFactura().getIntListaCompra().definirAnchoColmunas();
							modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					modFactura.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
					System.out.println(
							"ModeloFacturaFormaPago.actionPerformed() Imprimió Imprimió Imprimió Imprimió Imprimió Imprimió Imprimió");
				}
			}

		} else {
			if (txtCampoGained == this.intFormaPago.getTxtNumeroTarjeta()) {
				txtCampoGained.setText(modFactura.dato(jButton.getText(), txtCampoGained.getText(), 3));
				txtCampoGained.requestFocus();
			} else {
				txtCampoGained.setText(modFactura.dato(jButton.getText(), txtCampoGained.getText(), 1));
				txtCampoGained.requestFocus();
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		txtCampoGained = (JTextField) e.getSource();
		intFormaPago.getTxtCambio().setBackground(Color.WHITE);
		intFormaPago.getTxtRecibido().setBackground(Color.WHITE);
		intFormaPago.getTxtValor().setBackground(Color.WHITE);
		intFormaPago.getTxtNumeroTarjeta().setBackground(Color.WHITE);
		intFormaPago.getTxtExtrajeraRecibido().setBackground(Color.WHITE);
		if (e.getSource() == intFormaPago.getTxtCambio()) {
			intFormaPago.getTxtCambio().setBackground(Color.YELLOW);
		} else if (e.getSource() == intFormaPago.getTxtRecibido()) {
			intFormaPago.getTxtRecibido().setBackground(Color.YELLOW);
		} else if (e.getSource() == intFormaPago.getTxtValor()) {
			intFormaPago.getTxtValor().setBackground(Color.YELLOW);
		} else if (e.getSource() == intFormaPago.getTxtNumeroTarjeta()) {
			intFormaPago.getTxtNumeroTarjeta().setBackground(Color.YELLOW);
		} else if (e.getSource() == intFormaPago.getTxtExtrajeraRecibido()) {
			intFormaPago.getTxtExtrajeraRecibido().setBackground(Color.YELLOW);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == intFormaPago.getTxtExtrajeraRecibido()) {

			String sExtrajera = intFormaPago.getTxtExtrajeraRecibido().getText();
			if (sExtrajera.equals("")) {
				this.intFormaPago.getTxtExtranjera().setText("");
			} else {
				double enExtrajera = Double.valueOf(intFormaPago.getTxtExtrajeraRecibido().getText())
						* porcentaje_comision;
				sExtrajera = FormatoNumero.formatoCero(enExtrajera);
				this.intFormaPago.getTxtExtranjera().setText(sExtrajera);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		intFormaPago.getIntKeyNumeros().getRootPane().setDefaultButton(intFormaPago.getIntKeyNumeros().getBtnEnter());
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			// txtCampoGained.transferFocus();

			/*
			 * principal.getRootPane().setDefaultButton(intFormaPago.getBtnImprimir());
			 * intFormaPago.getBtnImprimir().requestFocus();
			 */

		} else if (e.getSource() == intFormaPago.getTxtValor()) {
			EntradaTeclado.validarNumero(e);
		} else if (e.getSource() == intFormaPago.getTxtRecibido()) {
			EntradaTeclado.validarNumero(e);
		} else if (e.getSource() == intFormaPago.getTxtNumeroTarjeta()) {
			EntradaTeclado.validarNumero(e);
		} else if (e.getSource() == intFormaPago.getTxtExtrajeraRecibido()) {
			EntradaTeclado.validarDecimal(e, intFormaPago.getTxtExtrajeraRecibido().getText());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (lsm.isSelectionEmpty()) {
				// System.out.println("ConFactura.valueChanged() TABLA VACIA NO BORRA NO HACE
				// NADA");
			} else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						rowDeleteFormaPago = i;
					}
				}
			}
		} catch (Exception e1) {
			System.out.println("ModeloFacturaFormaPago.valueChanged() ERROR " + e1);
			e1.printStackTrace();
		}
	}

	private void mostrar(int id_medio_pago) {
		switch (id_medio_pago) {
		case 1: // Efectivo
			intFormaPago.getTxtCambio().setFocusable(false);
			intFormaPago.getBtnEfectivo().setBackground(Color.YELLOW);
			intFormaPago.getBtnTarjetaCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaDebito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnRemision().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnMoneda().setBackground(new Color(240, 240, 240));
			intFormaPago.getLblEntidad().setVisible(false);
			intFormaPago.getLblMoneda().setVisible(false);
			intFormaPago.getLblNumeroTarjeta().setVisible(false);
			intFormaPago.getCbEntidad().setVisible(false);
			intFormaPago.getTxtNumeroTarjeta().setVisible(false);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(false);
			intFormaPago.getLblRecibido().setVisible(true);
			intFormaPago.getTxtRecibido().setVisible(true);
			intFormaPago.getLblCambio().setVisible(true);
			intFormaPago.getTxtExtranjera().setVisible(false);
			intFormaPago.getTxtCambio().setVisible(true);
			intFormaPago.getTxtDocumento().setVisible(false);
			intFormaPago.getTxtNombre().setVisible(false);
			intFormaPago.getChckbxProveedor().setVisible(false);
			break;
		case 2: // Tarjeta Crédito
			intFormaPago.getTxtNumeroTarjeta().setFocusable(true);
			intFormaPago.getBtnEfectivo().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaCredito().setBackground(Color.YELLOW);
			intFormaPago.getBtnTarjetaDebito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnRemision().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnMoneda().setBackground(new Color(240, 240, 240));
			intFormaPago.getLblEntidad().setVisible(true);
			intFormaPago.getLblMoneda().setVisible(false);
			intFormaPago.getLblNumeroTarjeta().setVisible(true);
			intFormaPago.getCbEntidad().setVisible(true);
			intFormaPago.getTxtNumeroTarjeta().setVisible(true);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(false);
			intFormaPago.getLblRecibido().setVisible(false);
			intFormaPago.getTxtRecibido().setVisible(false);
			intFormaPago.getLblCambio().setVisible(false);
			intFormaPago.getTxtExtranjera().setVisible(false);
			intFormaPago.getTxtCambio().setVisible(false);
			intFormaPago.getTxtDocumento().setVisible(false);
			intFormaPago.getTxtNombre().setVisible(false);
			intFormaPago.getChckbxProveedor().setVisible(false);
			break;
		case 3: // Tarjeta Debito
			intFormaPago.getTxtNumeroTarjeta().setFocusable(true);
			intFormaPago.getBtnEfectivo().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaDebito().setBackground(Color.YELLOW);
			intFormaPago.getBtnCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnRemision().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnMoneda().setBackground(new Color(240, 240, 240));
			intFormaPago.getLblEntidad().setVisible(true);
			intFormaPago.getLblMoneda().setVisible(false);
			intFormaPago.getLblNumeroTarjeta().setVisible(true);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(false);
			intFormaPago.getCbEntidad().setVisible(true);
			intFormaPago.getTxtNumeroTarjeta().setVisible(true);
			intFormaPago.getLblRecibido().setVisible(false);
			intFormaPago.getTxtRecibido().setVisible(false);
			intFormaPago.getLblCambio().setVisible(false);
			intFormaPago.getTxtExtranjera().setVisible(false);
			intFormaPago.getTxtCambio().setVisible(false);
			intFormaPago.getTxtDocumento().setVisible(false);
			intFormaPago.getTxtNombre().setVisible(false);
			intFormaPago.getChckbxProveedor().setVisible(false);
			break;
		case 7: // Crédito
			intFormaPago.getTxtNumeroTarjeta().setFocusable(true);
			intFormaPago.getBtnEfectivo().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaDebito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnCredito().setBackground(Color.YELLOW);
			intFormaPago.getBtnRemision().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnMoneda().setBackground(new Color(240, 240, 240));
			intFormaPago.getLblEntidad().setVisible(false);
			intFormaPago.getLblMoneda().setVisible(false);
			intFormaPago.getLblNumeroTarjeta().setVisible(false);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(false);
			intFormaPago.getCbEntidad().setVisible(false);
			intFormaPago.getTxtNumeroTarjeta().setVisible(false);
			intFormaPago.getLblRecibido().setVisible(false);
			intFormaPago.getTxtRecibido().setVisible(false);
			intFormaPago.getLblCambio().setVisible(false);
			intFormaPago.getTxtExtranjera().setVisible(false);
			intFormaPago.getTxtCambio().setVisible(false);
			intFormaPago.getTxtDocumento().setVisible(true);
			intFormaPago.getTxtNombre().setVisible(true);
			intFormaPago.getChckbxProveedor().setVisible(false);
			break;
		case 8: // Remisión
			intFormaPago.getTxtNumeroTarjeta().setFocusable(false);
			intFormaPago.getBtnEfectivo().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaDebito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnRemision().setBackground(Color.YELLOW);
			intFormaPago.getBtnMoneda().setBackground(new Color(240, 240, 240));
			intFormaPago.getLblEntidad().setVisible(false);
			intFormaPago.getLblMoneda().setVisible(false);
			intFormaPago.getLblNumeroTarjeta().setVisible(false);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(false);
			intFormaPago.getCbEntidad().setVisible(false);
			intFormaPago.getTxtNumeroTarjeta().setVisible(false);
			intFormaPago.getLblRecibido().setVisible(false);
			intFormaPago.getTxtRecibido().setVisible(false);
			intFormaPago.getLblCambio().setVisible(false);
			intFormaPago.getTxtExtranjera().setVisible(false);
			intFormaPago.getTxtCambio().setVisible(false);
			intFormaPago.getTxtDocumento().setVisible(true);
			intFormaPago.getTxtNombre().setVisible(true);
			intFormaPago.getChckbxProveedor().setVisible(true);
			break;
		case 9: // Moneda Extranjera
			intFormaPago.getTxtNumeroTarjeta().setFocusable(false);
			intFormaPago.getBtnEfectivo().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnTarjetaDebito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnCredito().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnRemision().setBackground(new Color(240, 240, 240));
			intFormaPago.getBtnMoneda().setBackground(Color.YELLOW);
			intFormaPago.getLblEntidad().setVisible(false);
			intFormaPago.getLblMoneda().setVisible(true);
			intFormaPago.getLblNumeroTarjeta().setVisible(false);
			intFormaPago.getCbEntidad().setVisible(true);
			intFormaPago.getTxtNumeroTarjeta().setVisible(false);
			intFormaPago.getTxtExtrajeraRecibido().setVisible(true);
			intFormaPago.getLblRecibido().setVisible(false);
			intFormaPago.getTxtRecibido().setVisible(false);
			intFormaPago.getTxtExtranjera().setEditable(false);
			intFormaPago.getLblCambio().setVisible(false);
			intFormaPago.getTxtExtranjera().setVisible(true);
			intFormaPago.getTxtCambio().setVisible(false);
			intFormaPago.getTxtDocumento().setVisible(false);
			intFormaPago.getTxtNombre().setVisible(false);
			intFormaPago.getChckbxProveedor().setVisible(false);

		default:
			break;
		}
	}

	public IntFormaPago getIntFormaPago() {
		return intFormaPago;
	}

	public void setIntFormaPago(IntFormaPago intFormaPago) {
		this.intFormaPago = intFormaPago;
	}

	public static void addEscapeListenerWindowDialog(final JDialog windowDialog) {
		ActionListener escAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				windowDialog.dispose();
			}
		};
		windowDialog.getRootPane().registerKeyboardAction(escAction, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

}
