package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Cliente;
import clases.DevolucionVenta;
import clases.Factura;
import clases.ItemDevolucionVenta;
import clases.ItemFactura;
import clases.ItemRemision;
import clases.NumeracionAutorizada;
import clases.Proveedor;
import clases.Remision;
import clases.TablaDevoluciones;
import database.MaestroDB;
import gui.IntDevoluciones;
import main.Principal;
import modelo.ModeloDevoluciones;
import util.EntradaTeclado;
import util.FormatoNumero;
import util.G;
import util.TableDevolucionesModelo;

public class ConDevoluciones implements ActionListener, FocusListener, KeyListener {

	private IntDevoluciones intDevoluciones;
	private ModeloDevoluciones modeloDevoluciones;
	private Principal principal;
	private TableDevolucionesModelo tableDevolucionesModelo; 
	private MaestroDB maestroDB;
	private JButton boton;
	private JTextField txtCampoGained;
	private int tipoCampo;
	private int id_caja;
	private int id_almacen;
	private ListSelectionModel listSelectionModel;
	private int devolucionSeleccionada = -1;
	private int indexSeleccionadoJlistArticulo = -1;
	private int indexSeleccionadoJlistFactura = -1;
	private Factura factura;
	private Remision remision;
	private Cliente cliente;
	private Proveedor proveedor;
	private ArrayList<ItemFactura> alItemFactura;
	private ArrayList<ItemRemision> alItemRemision;
	private ArrayList<ItemFactura> alItemFacturaDevolucion = new ArrayList<ItemFactura>();
	private ArrayList<ItemRemision> alItemRemisionDevolucion = new ArrayList<ItemRemision>();
	private Set<Integer> setFilasSeleccionadas = new HashSet<Integer>(64);
	private Set<String> setArticulosSeleccionados = new HashSet<String>(64);
	//private Set<ItemFactura> setItemFacturaDevolucion = new HashSet<ItemFactura>(64);
	private ArrayList<DevolucionVenta> alDevolucionVenta;
	private String prefijo;
	private double numero_factura;
	private String detalle;
	private double numeroFactura;
	private int numeroRemision;
	private String tipoDocumento;

	private String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

	private java.sql.Timestamp fechaInicial;
	private java.sql.Timestamp fechaFinal;

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

	private String justificacion;
	private boolean proveedorRemision;
	private String sListFacturasPendientes;
	private String sListDevolucionArticulo;

	public ConDevoluciones(IntDevoluciones intDevoluciones, ModeloDevoluciones modeloDevoluciones, Principal principal,
			TableDevolucionesModelo tableDevolucionesModelo, MaestroDB maestroDB) {
		super();
		this.intDevoluciones = intDevoluciones;
		this.modeloDevoluciones = modeloDevoluciones;
		this.principal = principal;
		this.tableDevolucionesModelo = tableDevolucionesModelo;
		this.maestroDB = maestroDB;

		intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setFocusable(false);
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().addFocusListener(this);
		intDevoluciones.getIntDevolucionesEncabezado().getTxtFecha().setFocusable(false);
		intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setFocusable(false);
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setFocusable(false);

		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().addKeyListener(this);

		intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setName("prefijo");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().setName("numero");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtFecha().setName("fecha");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setName("documentocliente");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setName("nombrecliente");

		intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeDevolucion().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeReporte().addActionListener(this); //OK
		intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnDevolver().addActionListener(this); //OK
		intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnRetirarArticulo().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeFactura().addActionListener(this);


		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSiete().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnOcho().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnNueve().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCuatro().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCinco().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSeis().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnUno().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnDos().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnTres().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCero().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnPor().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnPunto().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnMenos().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSuprimir().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter().addActionListener(this);

		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSiete().setName("siete");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnOcho().setName("ocho");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnNueve().setName("nueve");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCuatro().setName("cuatro");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCinco().setName("cinco");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSeis().setName("seis");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnUno().setName("uno");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnDos().setName("dos");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnTres().setName("tres");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnCero().setName("cero");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnPor().setName("por");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnPunto().setName("punto");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnMenos().setName("menos");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSuprimir().setName("suprimir");
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter().setName("enter");

		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnQ().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnW().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnE().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnR().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnT().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnY().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnU().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnI().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnO().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnP().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnA().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnS().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnD().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnF().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnG().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnH().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnJ().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnK().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnL().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnEnne().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnZ().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnX().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnC().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnV().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnB().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnN().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnM().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnEspacio().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnPunto().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnQuion().addActionListener(this);
		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyLetras().getBtnPor().addActionListener(this);

		periodoDevoluciones(1,1); //(id_caja, id_almacen);
		id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
		id_caja = G.getInstance().licenciaGlobal.getIdCaja(); 
		NumeracionAutorizada numeracionAutorizada = maestroDB.traeNumeracionAutorizada(id_caja);
		prefijo = numeracionAutorizada.getPrefijo();
		intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setText(prefijo);

		listSelectionModel = intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			// este es el escucha de la factura a devolver
			@Override
			public void valueChanged(ListSelectionEvent e) { // TABLA ARTICULOS
				if(e.getValueIsAdjusting()) { 

					if(tipoDocumento.equals("factura")) {
						System.out.println("ConDevoluciones.ConDevoluciones ESTA SELECCIONANDO LOS DATOS DE UNA FACTURA");

						try {
							ListSelectionModel lsm = (ListSelectionModel) e.getSource();
							if (!lsm.isSelectionEmpty()) {						
								// Find out which indexes are selected.
								int minIndex = lsm.getMinSelectionIndex();
								int maxIndex = lsm.getMaxSelectionIndex();
								for (int i = minIndex; i <= maxIndex; i++) {
									if (lsm.isSelectedIndex(i)) {
										devolucionSeleccionada = i;
										String valorItem = FormatoNumero.formatoCero(alItemFactura.get(devolucionSeleccionada).getValor_item());
										String xyz = i+" "+alItemFactura.get(devolucionSeleccionada).getCodigo_articulo()+" - "+valorItem;

										// valida que al articulo no se le haya realizado devolución
										ArrayList<DevolucionVenta> alDevolucionVenta = maestroDB.traeIdDevolucion(numeroFactura,"factura");
										int idDevolucionNumero = 0;
										ArrayList<ItemDevolucionVenta> alItemDevolucionVenta;
										boolean bArticuloDevuelto = false;
										for (int j = 0; j < alDevolucionVenta.size(); j++) {
											idDevolucionNumero = alDevolucionVenta.get(j).getNumero();									
											alItemDevolucionVenta = maestroDB.consultaArticuloSiFueDevuelto(idDevolucionNumero, 
													alItemFactura.get(devolucionSeleccionada).getCodigo_articulo());
											for (int k = 0; k < alItemDevolucionVenta.size(); k++) {
												if(alItemDevolucionVenta.get(k).getCodigo_articulo().equals(alItemFactura
														.get(devolucionSeleccionada).getCodigo_articulo())) {
													bArticuloDevuelto = true;
												}
											}
										}

										if(!bArticuloDevuelto) {
											if(!setFilasSeleccionadas.contains(devolucionSeleccionada)) {
												setArticulosSeleccionados.add(xyz);
												setFilasSeleccionadas.add(devolucionSeleccionada);

												intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
												String[] arrayArticulos = setArticulosSeleccionados.toArray(new String[setArticulosSeleccionados.size()]);
												for (int j = 0; j < arrayArticulos.length; j++) {
													intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().addElement(arrayArticulos[j].toString());
												}
												intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().setSelectionBackground(Color.YELLOW);

												alItemFacturaDevolucion.add(alItemFactura.get(devolucionSeleccionada));

											}else {
												setFilasSeleccionadas.remove(devolucionSeleccionada);
												setArticulosSeleccionados.remove(xyz);
												intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
												String[] arrayArticulos = setArticulosSeleccionados.toArray(new String[setArticulosSeleccionados.size()]);
												for (int j = 0; j < arrayArticulos.length; j++) {
													intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().addElement(arrayArticulos[j].toString());
												}
												for (int k = 0; k < alItemFacturaDevolucion.size(); k++) {
													if(alItemFactura.get(devolucionSeleccionada)==alItemFacturaDevolucion.get(k)) {
														alItemFacturaDevolucion.remove(k);
													}
												}
												for (int j = 0; j < alItemFacturaDevolucion.size(); j++) {
													System.out.println("YYYYY ConDevoluciones.remove setFilasSeleccionadas "+alItemFacturaDevolucion.get(j).getCodigo_articulo());
												}
											}
											devolucionSeleccionada =-1;
										}else {
											JOptionPane.showMessageDialog(null, "Articulo con esta factura ya fue devuelto");
										}
									}
								}
							}
						} catch (Exception e1) {
							System.out.println("ConDevoluciones..valueChanged() ERROR DEVOLUCIONES FACTURA ERROR ListSelectionModel ERROR"+e);
							e1.printStackTrace();
						}


					}else { // AQUI ES CUANDO SELECCIONA LA REMISION
						System.out.println("ConDevoluciones.ConDevoluciones AHORA SELECCIONA LOS DE UNA REMISION");
						try {
							ListSelectionModel lsm = (ListSelectionModel) e.getSource();
							if (!lsm.isSelectionEmpty()) {						
								// Find out which indexes are selected.
								int minIndex = lsm.getMinSelectionIndex();
								int maxIndex = lsm.getMaxSelectionIndex();
								for (int i = minIndex; i <= maxIndex; i++) {
									if (lsm.isSelectedIndex(i)) {
										devolucionSeleccionada = i;
										String valorItem = FormatoNumero.formatoCero(alItemRemision.get(devolucionSeleccionada).getValor_item());
										String xyz = i+" "+alItemRemision.get(devolucionSeleccionada).getCodigo_articulo()+" - "+valorItem;

										// valida que al articulo no se le haya realizado devolución
										ArrayList<DevolucionVenta> alDevolucionVenta = maestroDB.traeIdDevolucion(numeroRemision, "remision");
										int idDevolucionNumero = 0;
										ArrayList<ItemDevolucionVenta> alItemDevolucionVenta;
										boolean bArticuloDevuelto = false;
										for (int j = 0; j < alDevolucionVenta.size(); j++) {
											idDevolucionNumero = alDevolucionVenta.get(j).getNumero();
											alItemDevolucionVenta = maestroDB.consultaArticuloSiFueDevuelto(idDevolucionNumero, 
													alItemRemision.get(devolucionSeleccionada).getCodigo_articulo());
											for (int k = 0; k < alItemDevolucionVenta.size(); k++) {
												if(alItemDevolucionVenta.get(k).getCodigo_articulo().equals(alItemRemision
														.get(devolucionSeleccionada).getCodigo_articulo())) {
													bArticuloDevuelto = true;
												}
											}
										}

										if(!bArticuloDevuelto) {
											if(!setFilasSeleccionadas.contains(devolucionSeleccionada)) {
												setArticulosSeleccionados.add(xyz);
												setFilasSeleccionadas.add(devolucionSeleccionada);

												intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
												String[] arrayArticulos = setArticulosSeleccionados.toArray(new String[setArticulosSeleccionados.size()]);
												for (int j = 0; j < arrayArticulos.length; j++) {
													intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().addElement(arrayArticulos[j].toString());
												}
												intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().setSelectionBackground(Color.YELLOW);
												//Integer[] array = setFilasSeleccionadas.toArray(new Integer[setFilasSeleccionadas.size()]);

												alItemRemisionDevolucion.add(alItemRemision.get(devolucionSeleccionada));

												for (int j = 0; j < alItemRemisionDevolucion.size(); j++) {
													System.out.println("XXXXX ConDevoluciones.add setFilasSeleccionadas "+alItemRemisionDevolucion.get(j).getCodigo_articulo());
												}


											}else {
												setFilasSeleccionadas.remove(devolucionSeleccionada);
												setArticulosSeleccionados.remove(xyz);
												intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
												String[] arrayArticulos = setArticulosSeleccionados.toArray(new String[setArticulosSeleccionados.size()]);
												for (int j = 0; j < arrayArticulos.length; j++) {
													intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().addElement(arrayArticulos[j].toString());
												}
												for (int k = 0; k < alItemRemisionDevolucion.size(); k++) {
													if(alItemRemision.get(devolucionSeleccionada)==alItemRemisionDevolucion.get(k)) {
														alItemRemisionDevolucion.remove(k);
													}
												}
												for (int j = 0; j < alItemRemisionDevolucion.size(); j++) {
													System.out.println("YYYYY ConDevoluciones.remove setFilasSeleccionadas "+alItemRemisionDevolucion.get(j).getCodigo_articulo());
												}
											}
											devolucionSeleccionada =-1;
										}else {
											JOptionPane.showMessageDialog(null, "Articulo con esta remisión ya fue devuelto");
										}
									}
								}
							}
						} catch (Exception e1) {
							System.out.println("ConDevoluciones..valueChanged() ERROR DEVOLUCIONES REMISION ERROR ListSelectionModel ERROR"+e);
							e1.printStackTrace();
						}
					}
				}
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().setSelectionModel(listSelectionModel);

		intDevoluciones.getIntDevolucionesEncabezado().getListDevolucionArticulo()
		.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) { 
					@SuppressWarnings("unchecked")
					JList<String> listFacturasPendientes = (JList<String>) e.getSource();					
					indexSeleccionadoJlistArticulo = listFacturasPendientes.getSelectedIndex();
					sListDevolucionArticulo = listFacturasPendientes.getSelectedValue();
					System.out.println(	"ConDevoluciones. indexSeleccionadoJlistArticulo QXQXQX "+indexSeleccionadoJlistArticulo+" value "+listFacturasPendientes.getSelectedValue());
				}
			}
		});

		intDevoluciones.getIntDevolucionesEncabezado().getListDevolucionFacturas().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<String> listFacturasPendientes = (JList<String>) e.getSource();					
				indexSeleccionadoJlistFactura = listFacturasPendientes.getSelectedIndex();
				sListFacturasPendientes = listFacturasPendientes.getSelectedValue();
				System.out.println("ConDevoluciones. indexSeleccionadoJlistFactura "+indexSeleccionadoJlistFactura+" value "+sListFacturasPendientes);

			}
		});

		intDevoluciones.getIntDevolucionesEncabezado().getRdbtnFactura().setSelected(true);
		intDevoluciones.getIntDevolucionesEncabezado().getRdbtnFactura().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ConDevoluciones.ConDevoluciones LE PEGO A LA FACTURA");
				intDevoluciones.getIntDevolucionesEncabezado().getLblPrefijo().setVisible(true);
				intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setVisible(true);
			}
		});

		intDevoluciones.getIntDevolucionesEncabezado().getRdbtnRemision().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ConDevoluciones.ConDevoluciones LE DIO A LA REMISION");
				intDevoluciones.getIntDevolucionesEncabezado().getLblPrefijo().setVisible(false);
				intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setVisible(false);
			}
		});

		actualizaDevoluciones();

		txtCampoGained = intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero();
		tipoCampo = 2;
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().setBackground(Color.YELLOW);
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().requestFocus();
		/*principal.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().requestFocusInWindow();
			}
		});*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("ConDevoluciones.actionPerformed() intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter()");
		try {
			boton = (JButton) e.getSource();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter()) {
			//System.out.println("ConDevoluciones.actionPerformed() LE DIO EN LA PANTALLA  XXXX");
			txtCampoGained.transferFocus();
		}
		else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnSuprimir()) {
			txtCampoGained.setText("");
		}else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnDevolver()) {

			System.out.println("ConDevoluciones.actionPerformed() factura "+factura+" remision "+remision);

			if(factura!=null) {
				String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
				java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

				double valor=0;
				for (int i = 0; i < alItemFacturaDevolucion.size(); i++) {
					valor = valor +alItemFacturaDevolucion.get(i).getValor_item();
				}
				//id_almacen
				int numero = maestroDB.traeConsecutivoDevolucionVenta()+1; 
				//fecha, 
				java.sql.Timestamp fecha_caducidad = fecha;
				int id_usuario = G.getInstance().getIdCajero; 
				String origen = ""; //???????????????????????????? 
				int id_caja_factura = factura.getId_caja();  
				numero_factura = factura.getNumero(); 
				int id_cliente = factura.getId_cliente();
				long documento_cliente = cliente.getDocumento(); 
				String apellido_cliente = cliente.getApellido(); 
				String nombre_cliente = cliente.getNombre(); 
				String email_cliente = cliente.getEmail(); 
				String telefono_cliente = cliente.getTelefono(); 
				//String detalle = intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().get ""; 
				//valor 
				double saldo_pendiente = 0; 
				String documento_caja_generado=""; 
				int id_documento_caja = 2; 
				int numero_documento_caja = 1; 
				int id_caja_emision = 1; 
				String estado = "activo"; 
				String estado_exportacion = ""; 
				//fecha_exportacion, 
				java.sql.Timestamp fecha_contabilizacion = fecha;  
				long identificador_externo = 0;
				String id_tipo_comprobante_contable = ""; 
				int numero_comrpobante_contable = 0;
				java.sql.Timestamp dg_fecha_accion = fecha; 
				String dg_accion = "update";			

				// valida que se tenga artículos a devolver, si no ha seleccionado el artículo no hace nada.
				if(alItemFacturaDevolucion.size()!=0) {
					maestroDB.agregarDevolucionVenta(id_caja, numero, fecha, fecha_caducidad, id_usuario, origen, 
							id_caja_factura, numero_factura, id_cliente, documento_cliente, 
							apellido_cliente, nombre_cliente, email_cliente, telefono_cliente, 
							detalle, valor, saldo_pendiente, documento_caja_generado, id_documento_caja, 
							numero_documento_caja, id_caja_emision, estado, estado_exportacion, 
							fecha_contabilizacion, identificador_externo, 
							id_tipo_comprobante_contable, numero_comrpobante_contable, dg_fecha_accion, 
							dg_accion, "factura");

					int item = 0;
					for (int i = 0; i < alItemFacturaDevolucion.size(); i++) {
						//id_almacen, 
						//numero, 
						item = item+1; 
						int item_factura = alItemFacturaDevolucion.get(i).getItem(); 
						String codigo_articulo = alItemFacturaDevolucion.get(i).getCodigo_articulo(); 
						int id_articulo = alItemFacturaDevolucion.get(i).getId_articulo();
						int id_base_iva = alItemFacturaDevolucion.get(i).getId_base_iva(); 
						int id_presentacion = alItemFacturaDevolucion.get(i).getId_presentacion(); 
						double cantidad_presentacion = alItemFacturaDevolucion.get(i).getCantidad_presentacion();
						double cantidad_unidad_medida = alItemFacturaDevolucion.get(i).getCantidad_unidad_medida();
						double valorItem = alItemFacturaDevolucion.get(i).getValor_item();
						double valor_iva = alItemFacturaDevolucion.get(i).getValor_iva(); 
						String estado_exportacion2 = "proceso"; 
						java.sql.Timestamp dg_fecha_accion2 = fecha; 
						String dg_accion2 = "insert";

						maestroDB.agregarItemDevolucionVenta(id_almacen, numero, item, item_factura, codigo_articulo, id_articulo, 
								id_base_iva, id_presentacion, cantidad_presentacion, cantidad_unidad_medida, 
								valorItem, valor_iva, estado_exportacion2, dg_fecha_accion2, dg_accion2);
					}

					//id serial NOT NULL,
					int id_host_origen = G.getInstance().licenciaGlobal.getIdHost(); // integer,
					int id_host_padre =  G.getInstance().licenciaGlobal.getIdHostPadre(); //integer,
					String tabla = "devolucion_venta";// character varying(30),
					String campo_id = "id_almacen;numero"; //character varying(50),
					String tipo_id = "int;int"; //character varying(50),
					String id_tabla = G.getInstance().licenciaGlobal.getIdCaja()+";"+numero;// caja + consecutivo devolucion //character varying(50),
					java.sql.Timestamp fecha_evento = fecha; // timestamp without time zone,
					//fecha_exportacion timestamp without time zone,
					String accion = "update"; //character varying(30),
					//estado character varying(30),
					String observacion = ""; // text,
					java.sql.Timestamp dg_fecha_accion1 = fecha; // timestamp without time zone,
					String dg_accion1 = "insert"; //character varying(30),

					maestroDB.agregarPendienteExporteacionDevolucion(id_host_origen, id_host_padre, tabla, campo_id, tipo_id, 
							id_tabla, fecha_evento, accion, observacion, dg_fecha_accion1, dg_accion1);

					actualizaDevoluciones();

					String sFechaFactura = factura.getFecha()+" "+factura.getHora();
					java.sql.Timestamp fechaFactura = java.sql.Timestamp.valueOf(sFechaFactura);

					alDevolucionVenta.get(0).setFecha(fecha);
					modeloDevoluciones.imprimeDevolucion(alItemFacturaDevolucion,alDevolucionVenta,prefijo,numero_factura,maestroDB,fechaFactura);

				}

			}else if(remision!=null) {
				System.out.println("ConDevoluciones.actionPerformed() BOTON DEVOLVER UNA REMISION");

				String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
				java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

				double valor=0;
				for (int i = 0; i < alItemRemisionDevolucion.size(); i++) {
					valor = valor +alItemRemisionDevolucion.get(i).getValor_item();
				}
				//id_almacen
				int numero = maestroDB.traeConsecutivoDevolucionVenta()+1; 
				//fecha, 
				java.sql.Timestamp fecha_caducidad = fecha;
				int id_usuario = G.getInstance().getIdCajero; 
				String origen = ""; //???????????????????????????? 
				int id_caja_factura = remision.getId_caja();  
				numero_factura = remision.getNumero_remision();

				int id_cliente=0;
				long documento_cliente = 0; 
				String apellido_cliente = ""; 
				String nombre_cliente = ""; 
				String email_cliente = ""; 
				String telefono_cliente = ""; 

				//Str  ing documentoClienteProveedor =  intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().getText();
				if(proveedorRemision) {
					long proveedorDocumento = (long) remision.getDocumento();
					proveedor = maestroDB.traeProveedor(proveedorDocumento);

					id_cliente = proveedor.getId();                                                   //TODO remision.getid factura.getId_cliente();
					documento_cliente = proveedor.getDocumento(); //cliente.getDocumento(); 
					apellido_cliente = ""; //cliente.getApellido(); 
					nombre_cliente = proveedor.getNombre(); // cliente.getNombre(); 
					email_cliente = proveedor.getEmail(); // cliente.getEmail(); 
					telefono_cliente = proveedor.getTelefono(); // cliente.getTelefono(); 

				}else{
					cliente = maestroDB.consultarCliente(""+remision.getDocumento());

					id_cliente = cliente.getId();
					documento_cliente = cliente.getDocumento(); 
					apellido_cliente = cliente.getApellido(); 
					nombre_cliente = cliente.getNombre(); 
					email_cliente = cliente.getEmail(); 
					telefono_cliente = cliente.getTelefono(); 
				}

				double saldo_pendiente = 0; 
				String documento_caja_generado=""; 
				int id_documento_caja = 2; 
				int numero_documento_caja = 1; 
				int id_caja_emision = 1; 
				String estado = "activo"; 
				String estado_exportacion = ""; 
				//fecha_exportacion, 
				java.sql.Timestamp fecha_contabilizacion = fecha;  
				long identificador_externo = 0;
				String id_tipo_comprobante_contable = ""; 
				int numero_comrpobante_contable = 0;
				java.sql.Timestamp dg_fecha_accion = fecha; 
				String dg_accion = "update";			

				if(alItemRemisionDevolucion.size()!=0) {
					maestroDB.agregarDevolucionVenta(id_caja, numero, fecha, fecha_caducidad, id_usuario, origen, 
							id_caja_factura, numero_factura, id_cliente, documento_cliente, 
							apellido_cliente, nombre_cliente, email_cliente, telefono_cliente, 
							detalle, valor, saldo_pendiente, documento_caja_generado, id_documento_caja, 
							numero_documento_caja, id_caja_emision, estado, estado_exportacion, 
							fecha_contabilizacion, identificador_externo, 
							id_tipo_comprobante_contable, numero_comrpobante_contable, dg_fecha_accion, 
							dg_accion, "remision");

					int item = 0;
					for (int i = 0; i < alItemRemisionDevolucion.size(); i++) {
						//id_almacen, 
						//numero, 
						item = item+1; 
						int item_factura = alItemRemisionDevolucion.get(i).getItem(); 
						String codigo_articulo = alItemRemisionDevolucion.get(i).getCodigo_articulo(); 
						int id_articulo = alItemRemisionDevolucion.get(i).getId_articulo();
						int id_base_iva = alItemRemisionDevolucion.get(i).getId_base_iva(); 
						int id_presentacion = alItemRemisionDevolucion.get(i).getId_presentacion(); 
						double cantidad_presentacion = alItemRemisionDevolucion.get(i).getCantidad_presentacion();
						double cantidad_unidad_medida = alItemRemisionDevolucion.get(i).getCantidad_unidad_medida();
						double valorItem = alItemRemisionDevolucion.get(i).getValor_item();
						double valor_iva = alItemRemisionDevolucion.get(i).getValor_iva(); 
						String estado_exportacion2 = "proceso"; 
						java.sql.Timestamp dg_fecha_accion2 = fecha; 
						String dg_accion2 = "insert";

						maestroDB.agregarItemDevolucionVenta(id_almacen, numero, item, item_factura, codigo_articulo, id_articulo, 
								id_base_iva, id_presentacion, cantidad_presentacion, cantidad_unidad_medida, 
								valorItem, valor_iva, estado_exportacion2, dg_fecha_accion2, dg_accion2);
					}

					//id serial NOT NULL,
					int id_host_origen = G.getInstance().licenciaGlobal.getIdHost(); // integer,
					int id_host_padre =  G.getInstance().licenciaGlobal.getIdHostPadre(); //integer,
					String tabla = "devolucion_venta";// character varying(30),
					String campo_id = "id_almacen;numero"; //character varying(50),
					String tipo_id = "int;int"; //character varying(50),
					String id_tabla = G.getInstance().licenciaGlobal.getIdCaja()+";"+numero;// caja + consecutivo devolucion //character varying(50),
					java.sql.Timestamp fecha_evento = fecha; // timestamp without time zone,
					//fecha_exportacion timestamp without time zone,
					String accion = "update"; //character varying(30),
					//estado character varying(30),
					String observacion = ""; // text,
					java.sql.Timestamp dg_fecha_accion1 = fecha; // timestamp without time zone,
					String dg_accion1 = "insert"; //character varying(30),

					maestroDB.agregarPendienteExporteacionDevolucion(id_host_origen, id_host_padre, tabla, campo_id, tipo_id, 
							id_tabla, fecha_evento, accion, observacion, dg_fecha_accion1, dg_accion1);

					actualizaDevoluciones();
					
					modeloDevoluciones.imprimeDevolucionRemision(alItemRemisionDevolucion,alDevolucionVenta,prefijo,numero_factura,maestroDB, remision.getFecha());
				}
			}
			limpiarDatosDevolucionFacturaRemision();

		}
		else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeReporte()) {
			modeloDevoluciones.imprimeReporte(alDevolucionVenta, prefijo, maestroDB,fechaInicial,fechaFinal);
		}
		else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeDevolucion()) {

			//int numeroDevolucion=0;
			if(indexSeleccionadoJlistFactura!=-1) {
				int numeroDevolucionFacturaRemision = Integer.valueOf(sListFacturasPendientes.substring(0, sListFacturasPendientes.indexOf(" ")));
				String origenRemisionFactura = sListFacturasPendientes.substring(sListFacturasPendientes.indexOf(" ")+1, sListFacturasPendientes.indexOf(" ")+2);
				modeloDevoluciones.imprimeCopiaDevolucionFacturaRemision(origenRemisionFactura, numeroDevolucionFacturaRemision, maestroDB);				
			}

		}else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnRetirarArticulo()) {
			limpiarDatosDevolucionFacturaRemision();

		}else if(boton == intDevoluciones.getIntDevolucionesPiepagina().getIntDevolucionesBotones().getBtnImprimeFactura()) {
			boolean impresionLogo = Boolean.valueOf(maestroDB.cargarUnParametro("impresionConLogo"));
			if(impresionLogo) {
				int numeroCopias = maestroDB.actualizaNumeroCopias(numeroFactura);
				modeloDevoluciones.imprimeFactura2(numeroFactura, maestroDB,numeroCopias);
				//new	ImpresionConLogo(datosImprimirFactura,this, encabezadoFactura);
			}else {
				int numeroCopias = maestroDB.actualizaNumeroCopias(numeroFactura);
				modeloDevoluciones.imprimeFactura(numeroFactura, maestroDB,numeroCopias);
			}
		}

		txtCampoGained.setText(modeloDevoluciones.dato(boton.getText(), txtCampoGained.getText(), tipoCampo));

		if(e.getSource()==intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter()) {

			if(intDevoluciones.getIntDevolucionesEncabezado().getRdbtnFactura().isSelected()) {

				tipoDocumento="factura";

				System.out.println("ConDevoluciones.actionPerformed() FACTURA ESTA SELECCIONADA");
				if(intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().getText().length()>0) {
					String sNumero = intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().getText();
					sNumero = sNumero.replace(",", "");
					numeroFactura = Double.valueOf(sNumero);
					factura = maestroDB.traeFactura(id_caja,numeroFactura);

					if(factura!=null) {
						intDevoluciones.getIntDevolucionesEncabezado().getTxtFecha().setText(""+factura.getFecha());

						if(factura.getId_cliente()!=0) {
							cliente = maestroDB.consultarCliente(factura.getId_cliente());
							intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setText(""+cliente.getDocumento());
							intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
						}else {
							cliente = maestroDB.consultarCliente(1);
							intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setText(""+cliente.getDocumento());
							intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
						}

						int cantidadRow = intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().getRowCount();
						if(cantidadRow>0) {
							for (int i = cantidadRow; i >= 0; i--) {
								//System.out.println("ModFactura.BorrarTablaCapturaArticulo() cantidadRow "+cantidadRow+" i "+i);
								tableDevolucionesModelo.borraArticulo(i-1);
							}
						}

						detalle = "";
						intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().setSelectedIndex(0); 
						intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
						intDevoluciones.getIntDevolucionesEncabezado().definirAnchoColmunas();

						alItemFactura = maestroDB.traerItemFactura(id_caja, numeroFactura);
						for (int i = 0; i < alItemFactura.size(); i++) {
							String nombreArticulo = maestroDB.traeNombreArticulo(alItemFactura.get(i).getCodigo_articulo());
							//System.out.println("ConDevoluciones.actionPerformed() "+alItemFactura.get(i).getCodigo_articulo()+" nombreArticulo "+nombreArticulo);

							// vendedor, codigo, articulo, unidades, cantidad,valor, descuento, total
							TablaDevoluciones tablaDevoluciones = new TablaDevoluciones(
									alItemFactura.get(i).getId_vendedor(),
									alItemFactura.get(i).getCodigo_articulo(),
									nombreArticulo,
									0,
									alItemFactura.get(i).getCantidad_unidad_medida(),
									alItemFactura.get(i).getValor_unidad(),
									0,
									alItemFactura.get(i).getValor_item()
									);
							tableDevolucionesModelo.adicionaArticulo(tablaDevoluciones);

						}
					}else {
						JOptionPane.showMessageDialog(null, "N\u00FAmero de factura no existe!");
					}

				}else {
					System.out.println("ConDevoluciones.actionPerformed() REQUIERE EL NUMERO DE LA FACTURA");
				}
			}else {
				System.out.println("ConDevoluciones.actionPerformed() LA REMISION TIENE PUNTO NEGRO");

				tipoDocumento="remision";

				if(intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().getText().length()>0) {
					String sNumero = intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().getText();
					sNumero = sNumero.replace(",", "");
					numeroRemision = Integer.valueOf(sNumero);
					remision = maestroDB.traeRemision(id_caja,numeroRemision);

					if(remision!=null) {
						intDevoluciones.getIntDevolucionesEncabezado().getTxtFecha().setText(""+remision.getFecha());

						//						boolean proveedorRemision = Boolean.parseBoolean(remision.getProveedor());
						proveedorRemision = Boolean.parseBoolean(remision.getProveedor());
						if(proveedorRemision) {
							long proveedorDocumento = (long) remision.getDocumento();
							proveedor = maestroDB.traeProveedor(proveedorDocumento);
						}else{
							cliente = maestroDB.consultarCliente(""+remision.getDocumento());							
						}


						String sDocumento = FormatoNumero.formatoCero(remision.getDocumento());
						intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setText(sDocumento);
						intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setText(remision.getNombre()+" "+remision.getApellido());

						int cantidadRow = intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().getRowCount();
						if(cantidadRow>0) {
							for (int i = cantidadRow; i >= 0; i--) {
								//System.out.println("ModFactura.BorrarTablaCapturaArticulo() cantidadRow "+cantidadRow+" i "+i);
								tableDevolucionesModelo.borraArticulo(i-1);
							}
						}

						detalle = "";
						intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().setSelectedIndex(0); 
						intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
						intDevoluciones.getIntDevolucionesEncabezado().definirAnchoColmunas();

						alItemRemision = maestroDB.traerItemRemision(id_caja, numeroRemision);
						for (int i = 0; i < alItemRemision.size(); i++) {
							String nombreArticulo = maestroDB.traeNombreArticulo(alItemRemision.get(i).getCodigo_articulo());
							//System.out.println("ConDevoluciones.actionPerformed() "+alItemFactura.get(i).getCodigo_articulo()+" nombreArticulo "+nombreArticulo);

							// vendedor, codigo, articulo, unidades, cantidad,valor, descuento, total
							TablaDevoluciones tablaDevoluciones = new TablaDevoluciones(
									alItemRemision.get(i).getId_vendedor(),
									alItemRemision.get(i).getCodigo_articulo(),
									nombreArticulo,
									0,
									alItemRemision.get(i).getCantidad_unidad_medida(),
									alItemRemision.get(i).getValor_unidad(),
									0,
									alItemRemision.get(i).getValor_item()
									);
							tableDevolucionesModelo.adicionaArticulo(tablaDevoluciones);

						}
					}else {
						JOptionPane.showMessageDialog(null, "N\u00FAmero de remisión no existe!");
					}

				}else {
					System.out.println("ConDevoluciones.actionPerformed() REQUIERE EL NUMERO DE LA FACTURA");
				}

			}

		}
	}

	private void limpiarDatosDevolucionFacturaRemision() {
		factura=null;
		remision=null;
		intDevoluciones.getIntDevolucionesEncabezado().getTxtPrefijo().setText("");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().setText("");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtFecha().setText("");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtDocumentoCliente().setText("");
		intDevoluciones.getIntDevolucionesEncabezado().getTxtNombreCliente().setText("");
		intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionArticulo().removeAllElements();
		intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().removeAll();
		int cantidadArticulos = intDevoluciones.getIntDevolucionesEncabezado().getTableDevoluciones().getRowCount();
		for (int i = (cantidadArticulos-1); i >= 0; i--) {
			tableDevolucionesModelo.borraArticulo(i);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

		txtCampoGained = (JTextField) e.getSource();

		if(e.getSource()==intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero()) {
			intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().selectAll();
			intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero().setBackground(Color.YELLOW);
			//txtCampoGained.requestFocus();
			tipoCampo = 2;
		}/*else if(e.getSource()==intPagos.getIntPagosEncabezado().getTxtNombreProveedor()) {
			intPagos.getIntPagosEncabezado().getTxtNombreProveedor().setBackground(Color.YELLOW);
			tipoCampo = 3;
		}*/
	}

	@Override
	public void focusLost(FocusEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {

		intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getRootPane().setDefaultButton(intDevoluciones.getIntDevolucionesPiepagina().getIntKeyNumeros().getBtnEnter());
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			txtCampoGained.transferFocus();
		}

		if (e.getSource() == intDevoluciones.getIntDevolucionesEncabezado().getTxtNumero()) {
			EntradaTeclado.validarNumero(e); // Se asegura de que ingrese solo números
		} 

	}

	private void actualizaDevoluciones() {
		intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionFacturas().removeAllElements();

		String fechaDedes = annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00.000";
		fechaInicial = java.sql.Timestamp.valueOf( fechaDedes );

		String fechaHasta = annoHasta+"-"+mesHasta+"-"+diaHasta+" "+horaHasta+":"+minHasta+":00.000";
		fechaFinal = java.sql.Timestamp.valueOf( fechaHasta );

		alDevolucionVenta = maestroDB.traeDevolucionesPeriodo(id_caja, fechaInicial, fechaFinal);

		for (int j = 0; j < alDevolucionVenta.size(); j++) {
			String origen = "";
			if(alDevolucionVenta.get(j).getOrigen_devolucion().equals("remision")) {
				origen = "R";
			}else {
				origen = "F";
			}

			int numeroFactura = (int) alDevolucionVenta.get(j).getNumero_factura();
			String valorDevolucion = FormatoNumero.formatoCero(alDevolucionVenta.get(j).getValor());
			intDevoluciones.getIntDevolucionesEncabezado().getListModelDevolucionFacturas().addElement(alDevolucionVenta.get(j).getNumero()+" " +origen+" "+numeroFactura+" $"+valorDevolucion);
		}
	}

	private void periodoDevoluciones(int id_caja, int id_almacen) {
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

		intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoDesde().setSelectedItem(annoDesde);

		intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoDesde().setSelectedItem(annoDesde);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMesDesde().setSelectedItem(mesDesde);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaDesde().setSelectedItem(diaDesde);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraDesde().setSelectedItem(horaDesde);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMinDesde().setSelectedItem(minDesde);

		intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoHasta().setSelectedItem(annoHasta);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMesHasta().setSelectedItem(mesHasta);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaHasta().setSelectedItem(diaHasta);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraHasta().setSelectedItem(horaHasta);
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMinHasta().setSelectedItem(minHasta);

		intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().setSelectedItem(justificacion);

		intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoDesde = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoDesde().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMesDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesDesde = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbMesDesde().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaDesde = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaDesde().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				horaDesde = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraDesde().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMinDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minDesde = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbMinDesde().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoHasta = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbAnnoHasta().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMesHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesHasta = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbMesHasta().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaHasta = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbDiaHasta().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				horaHasta = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbHoraHasta().getSelectedItem();
				actualizaDevoluciones();
			}
		});
		intDevoluciones.getIntDevolucionesEncabezado().getJcbMinHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minHasta = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbMinHasta().getSelectedItem();
				actualizaDevoluciones();
			}
		});

		intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				detalle = (String) intDevoluciones.getIntDevolucionesEncabezado().getJcbJustificacion().getSelectedItem();
			}
		});

	}

	public IntDevoluciones getIntDevoluciones() {
		return intDevoluciones;
	}
	public void setIntDevoluciones(IntDevoluciones intDevoluciones) {
		this.intDevoluciones = intDevoluciones;
	}
	public ModeloDevoluciones getModeloDevoluciones() {
		return modeloDevoluciones;
	}
	public void setModeloDevoluciones(ModeloDevoluciones modeloDevoluciones) {
		this.modeloDevoluciones = modeloDevoluciones;
	}
	public Principal getPrincipal() {
		return principal;
	}
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	public TableDevolucionesModelo getTableDevolucionesModelo() {
		return tableDevolucionesModelo;
	}
	public void setTableDevolucionesModelo(TableDevolucionesModelo tableDevolucionesModelo) {
		this.tableDevolucionesModelo = tableDevolucionesModelo;
	}
	public MaestroDB getMaestroDB() {
		return maestroDB;
	}
	public void setMaestroDB(MaestroDB maestroDB) {
		this.maestroDB = maestroDB;
	}

}
