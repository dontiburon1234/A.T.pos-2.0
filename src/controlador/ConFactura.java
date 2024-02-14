package controlador;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import balanza.Peso;
import clases.ArticuloFactura;
import clases.Cliente;
import clases.DatosBRArray;
import clases.ItemPreFactura;
import clases.TablaArticulos;
import clases.Tercero;
import database.MaestroDB;
import gui.IntKeyNumeros;
import guifactura.IntBotonesRapidos1;
import guifactura.IntBotonesRapidos2;
import guifactura.IntBotonesRapidos3;
import guifactura.IntCapturaArticulo;
import guifactura.IntContenedor;
import guifactura.IntFactura;
import guifactura.IntListaCompra;
import guifactura.IntNumeros;
import guifactura.IntPedientes;
import main.Principal;
import modelo.ModFactura;
import modelo.ModPrincipal;
import modelo.ModeloDomicilio;
import modelo.ModeloFacturaBuscar;
import modelo.ModeloFacturaFormaPago;
import poledisplay.OperacionPoleDisplay;
import poledisplay.PoleDisplay;
import util.EntradaTeclado;
import util.FormatoNumero;
import util.G;
import util.TablaFacturaModelo;

public class ConFactura implements ActionListener, FocusListener, KeyListener,ChangeListener { //,ListSelectionListener {

	private IntFactura intFactura; 
	private IntBotonesRapidos1 intBotonesRapidos1;
	private IntBotonesRapidos2 intBotonesRapidos2;	
	private IntBotonesRapidos3 intBotonesRapidos3; 
	private IntCapturaArticulo intCapturaArticulo; 
	private IntContenedor intContenedor;
	private IntListaCompra intListaCompra;
	private IntPedientes intPendientes;
	private IntKeyNumeros intKeyNumeros;
	private IntNumeros intNumeros;
	private ModFactura modFactura;
	private TablaFacturaModelo tablaFacturaModelo;
	private Principal principal;
	private ModPrincipal modPrincipal;

	private JTextField txtCampoGained = null;
	private ArticuloFactura articuloFactura;
	private int tipoCampo;
	private MaestroDB maestroDB;
	private int articuloSeleccionado = -1;

	private int idPreFacturaSeleccionada = -1;
	private int indexSeleccionadoJlist = -1;

	private ArrayList<ItemPreFactura> itemsPreFactura = new ArrayList<ItemPreFactura>();
	private int tableta = 0;
	private JButton[] boton;
	private DatosBRArray datosBRArray;
	private Gson gson1 = new Gson();
	int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
	private int listaPrecioClienteTercero = 0;
	private int idVendedor=0;

	private ListSelectionModel listSelectionModel;
	private ArrayList<String> alFacturasPendientes;

	private OperacionPoleDisplay operacionPoleDisplay;
	//private PoleDisplay poleDisplay;

	public ConFactura(IntFactura intFactura, IntBotonesRapidos1 intBotonesRapidos1,
			IntBotonesRapidos2 intBotonesRapidos2, IntBotonesRapidos3 intBotonesRapidos3,
			IntCapturaArticulo intCapturaArticulo, IntContenedor intContenedor, IntListaCompra intListaCompra,
			IntPedientes intPendientes,IntKeyNumeros intKeyNumeros,
			IntNumeros intNumeros, ModFactura modFactura, 
			TablaFacturaModelo tablaFacturaModelo, Principal principal,ModPrincipal modPrincipal, MaestroDB maestroDB) {
		super();
		this.intFactura = intFactura;
		this.intBotonesRapidos1 = intBotonesRapidos1;
		this.intBotonesRapidos2 = intBotonesRapidos2;
		this.intBotonesRapidos3 = intBotonesRapidos3;
		this.intCapturaArticulo = intCapturaArticulo;
		this.intContenedor = intContenedor;
		this.intListaCompra = intListaCompra;
		this.intPendientes = intPendientes;
		this.intKeyNumeros = intKeyNumeros;
		this.intNumeros = intNumeros;
		this.modFactura = modFactura;
		this.tablaFacturaModelo = tablaFacturaModelo;
		this.principal = principal;
		this.modPrincipal = modPrincipal;
		this.maestroDB = maestroDB;

		this.intContenedor.getTabbedPane().addChangeListener(this);

		this.intPendientes.getBtnEliminar().addActionListener(this);
		this.intPendientes.getBtnPeso().addActionListener(this);

		int k=0;
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 5; i++) {
				this.intBotonesRapidos1.getBotonesRapidos()[k].addActionListener(this);
				this.intBotonesRapidos2.getBotonesRapidos()[k].addActionListener(this);
				this.intBotonesRapidos3.getBotonesRapidos()[k].addActionListener(this);
				k=k+1;
			}
		}

		this.intCapturaArticulo.getTxtDocumentoCliente().addFocusListener(this);
		this.intCapturaArticulo.getTxtNombreCliente().setFocusable(false);
		this.intCapturaArticulo.getTxtCodigoVendedor().addFocusListener(this);
		this.intCapturaArticulo.getTxtNombreVendedor().setFocusable(false);
		this.intCapturaArticulo.getTxtCodigoArticulo().addFocusListener(this);
		this.intCapturaArticulo.getTxtNombreArticulo().setFocusable(false);  //.addFocusListener(this);
		this.intCapturaArticulo.getTxtMedida().setFocusable(false);
		this.intCapturaArticulo.getTxtUnidades().setFocusable(false);
		this.intCapturaArticulo.getTxtCantidad().addFocusListener(this);
		this.intCapturaArticulo.getTxtValorUnitario().addFocusListener(this);
		this.intCapturaArticulo.getTxtValorUnitario().setFocusable(false); //addFocusListener(this);
		this.intCapturaArticulo.getTxtValorTotal().setFocusable(false);
		this.intCapturaArticulo.getTxtTotalFactura().setFocusable(false);

		this.intCapturaArticulo.getTxtDocumentoCliente().addKeyListener(this);
		this.intCapturaArticulo.getTxtCodigoVendedor().addKeyListener(this);
		this.intCapturaArticulo.getTxtCodigoArticulo().addKeyListener(this);
		this.intCapturaArticulo.getTxtNombreArticulo().addKeyListener(this);
		this.intCapturaArticulo.getTxtUnidades().addKeyListener(this);
		this.intCapturaArticulo.getTxtCantidad().addKeyListener(this);
		this.intCapturaArticulo.getTxtValorUnitario().addKeyListener(this);

		this.intCapturaArticulo.getTxtDocumentoCliente().setName("DocumentoCliente");
		this.intCapturaArticulo.getTxtCodigoVendedor().setName("CodigoVendedor");
		this.intCapturaArticulo.getTxtCodigoArticulo().setName("CodigoArticulo");
		this.intCapturaArticulo.getTxtNombreArticulo().setName("NombreArticulo()");
		this.intCapturaArticulo.getTxtUnidades().setName("Unidades");
		this.intCapturaArticulo.getTxtCantidad().setName("Cantidad");
		this.intCapturaArticulo.getTxtValorUnitario().setName("ValorUnitario");

		this.intListaCompra.getTxtSubTotal().setFocusable(false);
		this.intListaCompra.getTxtImpuesto().setFocusable(false);
		this.intListaCompra.getTxtDescuento().setFocusable(false);
		this.intListaCompra.getTxtTotalfactura().setFocusable(false);
		this.intListaCompra.getBtnBorraItem().addActionListener(this);
		this.intListaCompra.getBtnAutorizacion().addActionListener(this);
		this.intListaCompra.getBtnBuscar().addActionListener(this);
		this.intListaCompra.getBtnPagarfactura().addActionListener(this);
		this.intCapturaArticulo.getBtnGuardarItem().addActionListener(this);
		this.intCapturaArticulo.getBtnDomicilio().addActionListener(this);

		this.intKeyNumeros.getBtnSiete().addActionListener(this);
		this.intKeyNumeros.getBtnOcho().addActionListener(this);
		this.intKeyNumeros.getBtnNueve().addActionListener(this);
		this.intKeyNumeros.getBtnCuatro().addActionListener(this);
		this.intKeyNumeros.getBtnCinco().addActionListener(this);
		this.intKeyNumeros.getBtnSeis().addActionListener(this);
		this.intKeyNumeros.getBtnUno().addActionListener(this);
		this.intKeyNumeros.getBtnDos().addActionListener(this);
		this.intKeyNumeros.getBtnTres().addActionListener(this);
		this.intKeyNumeros.getBtnCero().addActionListener(this);
		this.intKeyNumeros.getBtnPor().addActionListener(this);
		this.intKeyNumeros.getBtnPunto().addActionListener(this);
		this.intKeyNumeros.getBtnMenos().addActionListener(this);
		this.intKeyNumeros.getBtnSuprimir().addActionListener(this);
		this.intKeyNumeros.getBtnEnter().addActionListener(this);

		this.intKeyNumeros.getBtnSiete().setName("siete");
		this.intKeyNumeros.getBtnOcho().setName("ocho");
		this.intKeyNumeros.getBtnNueve().setName("nueve");
		this.intKeyNumeros.getBtnCuatro().setName("cuatro");
		this.intKeyNumeros.getBtnCinco().setName("cinco");
		this.intKeyNumeros.getBtnSeis().setName("seis");
		this.intKeyNumeros.getBtnUno().setName("uno");
		this.intKeyNumeros.getBtnDos().setName("dos");
		this.intKeyNumeros.getBtnTres().setName("tres");
		this.intKeyNumeros.getBtnCero().setName("cero");
		this.intKeyNumeros.getBtnPor().setName("por");
		this.intKeyNumeros.getBtnPunto().setName("punto");
		this.intKeyNumeros.getBtnMenos().setName("menos");
		this.intKeyNumeros.getBtnSuprimir().setName("suprimir");
		this.intKeyNumeros.getBtnEnter().setName("enter");

		this.intCapturaArticulo.getBtnGuardarItem().setName("guardaritem");
		this.intCapturaArticulo.getBtnDomicilio().setName("domicilio");
		this.intListaCompra.getBtnBorraItem().setName("borraritem");
		this.intListaCompra.getBtnAutorizacion().setName("autorizacion");
		this.intListaCompra.getBtnBuscar().setName("buscar");
		this.intListaCompra.getBtnPagarfactura().setName("pagarfactura");
		this.intPendientes.getBtnEliminar().setName("eliminar");
		this.intPendientes.getBtnPeso().setName("peso");

		//****************************************************************************************************************************
		alFacturasPendientes = maestroDB.facturasPendientes();
		String nombreDomicilio = "";
		if(alFacturasPendientes != null) {
			for (int j = 0; j < alFacturasPendientes.size(); j++) {
				nombreDomicilio = maestroDB.consultarDomicilio(alFacturasPendientes.get(j).toString());
				if(nombreDomicilio == null)nombreDomicilio = "";
				this.intPendientes.getListModel().addElement(""+alFacturasPendientes.get(j).toString()+"-"+nombreDomicilio);
			}
		}

		this.intPendientes.getListFacturasPendientes().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) { 
					@SuppressWarnings("unchecked")
					JList<String> listFacturasPendientes = (JList<String>) e.getSource();					
					indexSeleccionadoJlist = listFacturasPendientes.getSelectedIndex();
					String sPreFacturaSeleccionada = intPendientes.getListModel().get(indexSeleccionadoJlist);
					sPreFacturaSeleccionada = sPreFacturaSeleccionada.substring(0, sPreFacturaSeleccionada.indexOf("-"));
					idPreFacturaSeleccionada = Integer.valueOf(sPreFacturaSeleccionada);

					try {
						G.getInstance().idPreFacturaActual =  idPreFacturaSeleccionada;
						//carga la prefactura que se va a pagar o anular			
						modFactura.BorrarTablaCapturaArticulo(tablaFacturaModelo);
						int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
						itemsPreFactura = maestroDB.traerItemPreFactura(id_caja, idPreFacturaSeleccionada);
						String nombre = "";
						double totalFactura = 0;
						for (int i = 0; i < itemsPreFactura.size(); i++) {
							nombre = maestroDB.traeNombre(itemsPreFactura.get(i).getId_articulo());
							tablaFacturaModelo.adicionaArticulo(new TablaArticulos(itemsPreFactura.get(i).getId_vendedor(), 
									itemsPreFactura.get(i).getCodigo_articulo(),nombre, 0, 
									itemsPreFactura.get(i).getCantidad_unidad_medida(), itemsPreFactura.get(i).getValor_unidad(), 
									itemsPreFactura.get(i).getValor_descuento(), 
									(itemsPreFactura.get(i).getCantidad_unidad_medida()* itemsPreFactura.get(i).getValor_unidad())));
						}

						//trae el id_cliente y su nombre de la tabla preFactura
						int id_cliente =  maestroDB.traeDocumento(G.getInstance().idPreFacturaActual,id_caja);	
						String documentoCliente;
						if(id_cliente == 0) {
							getIntCapturaArticulo().getTxtDocumentoCliente().setText("");
							intCapturaArticulo.getTxtDocumentoCliente().setFocusable(true);
							G.getInstance().documentoClienteTercero = "";
						}else {
							Tercero tercero = new Tercero();
							Cliente cliente = new Cliente();
							tercero = maestroDB.consultarTercero(id_cliente);
							if (tercero == null) {
								cliente = maestroDB.consultarCliente(id_cliente);
								if(cliente == null) {
									JOptionPane.showMessageDialog(null, "Documento equivocado del cliente.");
									intCapturaArticulo.getTxtDocumentoCliente().setText("");
									intCapturaArticulo.getTxtNombreCliente().setText("");
									listaPrecioClienteTercero = 0;
									intCapturaArticulo.getTxtDocumentoCliente().requestFocus();
								}else {
									documentoCliente = FormatoNumero.formatoCero(cliente.getDocumento());
									intCapturaArticulo.getTxtDocumentoCliente().setText(documentoCliente);
									intCapturaArticulo.getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
									intCapturaArticulo.getTxtDocumentoCliente().setFocusable(false);
									listaPrecioClienteTercero = cliente.getId_lista_precio();
									G.getInstance().documentoClienteTercero = ""+cliente.getDocumento();
									//System.out.println("ConFactura.valueChanged() cliente.getDocumento() "+cliente.getDocumento());
								}
							}else {
								documentoCliente = FormatoNumero.formatoCero(tercero.getDocumento());
								intCapturaArticulo.getTxtDocumentoCliente().setText(documentoCliente);
								intCapturaArticulo.getTxtNombreCliente().setText(tercero.getNombre());
								intCapturaArticulo.getTxtDocumentoCliente().setFocusable(false);
								listaPrecioClienteTercero = tercero.getId_lista_precio();
								G.getInstance().documentoClienteTercero = ""+tercero.getDocumento();
								//System.out.println("ConFactura.valueChanged() cliente.getDocumento() "+tercero.getDocumento());
							}
						}

						double subTotal = maestroDB.subTotalSinImpuestos(idPreFacturaSeleccionada,id_caja);
						String sSubTotal = FormatoNumero.formatoCero(subTotal);
						double impuestos = maestroDB.impuestos(idPreFacturaSeleccionada,id_caja);
						String sImpuestos = FormatoNumero.formatoCero(impuestos);
						totalFactura = maestroDB.totalFactura(idPreFacturaSeleccionada,id_caja);
						String totalFactura11 = FormatoNumero.formatoCero(totalFactura);

						getIntCapturaArticulo().getTxtTotalFactura().setText(totalFactura11);
						getIntListaCompra().getTxtTotalfactura().setText(totalFactura11);
						getIntListaCompra().getTxtSubTotal().setText(sSubTotal);
						getIntListaCompra().getTxtImpuesto().setText(sImpuestos);

						txtCampoGained = intCapturaArticulo.getTxtCodigoArticulo();
						intCapturaArticulo.getTxtCodigoArticulo().setBackground(Color.YELLOW);
						tipoCampo = 3;
						intCapturaArticulo.getTxtCodigoArticulo().requestFocus();

						intListaCompra.definirAnchoColmunas();

						return;
					} catch (Exception e2) {
						System.out
						.println("ConFactura.ConFactura(...).new ListSelectionListener() {...}.valueChanged() ERROR ERROR JList lista de preFactura ERROR ERROR "+e2);
						e2.printStackTrace();
					}
				}
			}
		});

		//****************************************************************************************************************************
		listSelectionModel = this.intListaCompra.getTable().getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			// este es el escucha de la lista de compras
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					if (!lsm.isSelectionEmpty()) {
						System.out.println("ConFactura.valueChanged() SELECCIONO LA LINEA DE LA LISTA DE COMPRAS");
						// Find out which indexes are selected.
						int minIndex = lsm.getMinSelectionIndex();
						int maxIndex = lsm.getMaxSelectionIndex();
						for (int i = minIndex; i <= maxIndex; i++) {
							if (lsm.isSelectedIndex(i)) {
								articuloSeleccionado = i;
							}
						}
					}
				} catch (Exception e1) {
					System.out.println("ConFactura.valueChanged() ERROR LISTA DE COMPRAS ERROR ListSelectionModel ERROR ERROR");
					e1.printStackTrace();
				}
			}
		});
		this.intListaCompra.getTable().setSelectionModel(listSelectionModel);
		//****************************************************************************************************************************

		String sistemaOperativo = System.getProperty("os.name").toUpperCase();
		try {
			String nombreArchivoLicencia;
			if (sistemaOperativo.indexOf("WINDOWS") != -1) {
				File dirWindows = new File("C:/tmp/lic/serv/");
				nombreArchivoLicencia = dirWindows.getAbsolutePath() + "/BotonesRapidos.bin";
			} else {
				nombreArchivoLicencia = "/var/lic/serv/BotonesRapidos.bin";
			}
			BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoLicencia));
			String currentLine = reader.readLine();
			reader.close();
			if(!currentLine.equals("")){
				datosBRArray = gson1.fromJson(currentLine, DatosBRArray.class);
			}else{
				datosBRArray = null;
			}
			intContenedor.getTabbedPane().setTitleAt(1, datosBRArray.getTitulo1());
			intContenedor.getTabbedPane().setTitleAt(2, datosBRArray.getTitulo2());
			intContenedor.getTabbedPane().setTitleAt(3, datosBRArray.getTitulo3());

		} catch (Exception e) {
			datosBRArray = null;
			e.printStackTrace();
		}

		G.getInstance().idPreFacturaActual = 0;

		Toolkit tamano = Toolkit.getDefaultToolkit();
		int ancho =  (int) (tamano.getScreenSize().getWidth());
		int alto = (int) (tamano.getScreenSize().getHeight());
		intListaCompra.getLblNombrecajero().setText(G.getInstance().cajero+"     "+ancho+"X"+alto);

		if(!maestroDB.cargarLicencia()){
			JOptionPane.showMessageDialog(null, "Error al cargar la licencia, ConFactura 292");
		}

		//cada vez que se crea una factura se define el campo donde se va a trabajar
		txtCampoGained = intCapturaArticulo.getTxtCodigoArticulo();
		tipoCampo = 3;

		intCapturaArticulo.getTxtCodigoArticulo().setBackground(Color.YELLOW);
		principal.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				intCapturaArticulo.getTxtCodigoArticulo().requestFocusInWindow();
			}
		});

		String defaultPort = maestroDB.cargarUnParametro("poleDisplay");
		G.getInstance().defaultPort = defaultPort;
		System.out.println("ConFactura.ConFactura() 4defaultPort 5defaultPort 6defaultPort "+defaultPort);
		
		System.out.println("ConFactura.ConFactura() inicia poleDisplay");
		/*if(G.getInstance().defaultPort != null) {
			String sCajero = G.getInstance().cajero;
			sCajero = sCajero.substring(0, sCajero.indexOf(" "));
			int iCajero = sCajero.length();
			int x = 0;
			if (iCajero < 20) {x = (int) (20 - iCajero) / 2;}
			String espacio = "";
			for (int i = 0; i < x; i++) {espacio = espacio + " ";}
			sCajero = espacio + sCajero;

			Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB, "     Buenos días    ", sCajero, true);
			Thread t = new Thread(r);
			t.start();
		}else {
			System.out.println("ConFactura.ConFactura() NO PUDO INICIAR EL POLEDISPLAY");
		}*/

	}

	@Override
	public void actionPerformed(ActionEvent e) { // solo JButton
		if(tableta == 0){
			boton = this.intBotonesRapidos1.getBotonesRapidos();
		}else if(tableta == 1){
			boton = this.intBotonesRapidos2.getBotonesRapidos();
		}else if(tableta == 2){
			boton = this.intBotonesRapidos3.getBotonesRapidos();
		}else {
			boton = null;
		}

		// los botones rápidos solo traen el producto a la captura de articulos.
		if(boton !=null) {
			for (int i = 0; i < boton.length; i++) {
				if(e.getSource()==boton[i]){
					for (int j = 0; j < datosBRArray.getArrayDatosBR().size(); j++) {

						if((tableta+1) == datosBRArray.getArrayDatosBR().get(j).getTabbed()){
							if(datosBRArray.getArrayDatosBR().get(j).getPosicionCelda()==i){
								//TODO primero revisar que lista de precio le corresponde al cliente, tercero si ya ha sido registrado en la interfas o almacen.

								articuloFactura = maestroDB.buscarArticulo(datosBRArray.getArrayDatosBR().get(j).getCodigo(),
										listaPrecioClienteTercero,idVendedor, intCapturaArticulo.getTxtCantidad().getText());

								if(articuloFactura.getId_articulo()!=0){
									intCapturaArticulo.getTxtCodigoArticulo().setText(datosBRArray.getArrayDatosBR().get(j).getCodigo());
									intCapturaArticulo.getTxtNombreArticulo().setText(articuloFactura.getNombreArticulo());
									intCapturaArticulo.getTxtMedida().setText(articuloFactura.getNombreUnidadMedida());
									intCapturaArticulo.getTxtValorUnitario().setText(""+articuloFactura.getValorUnitario());

									/*System.err.println("11111 ConFactura.actionPerformed() 325 codigo "+datosBRArray.getArrayDatosBR().get(j).getCodigo()+" listaPrecio "+
											listaPrecioClienteTercero+" idVendedor "+idVendedor+" cantidad "+ intCapturaArticulo.getTxtCantidad().getText());*/

									modFactura.ItemEnListaDeCompras(articuloFactura,tablaFacturaModelo);

									//if(G.getInstance().defaultPortFounf == true && !G.getInstance().defaultPort.equals("") && articuloFactura!=null){// operacionPoleDisplay!=null) {
									if(G.getInstance().defaultPort != null) {
										String primeraLinea = "";
										String segundaLinea = "";
										String articulo = articuloFactura.getNombreArticulo();


										double totalPreFacturaDisplay = maestroDB.traeTotalPrefactura(G.getInstance().idPreFacturaActual, id_caja);

										System.out.println("X1X1X ConFactura.actionPerformed() totalPreFacturaDisplay "+totalPreFacturaDisplay+" idPreFacturaActual "+G.getInstance().idPreFacturaActual);

										String sTotalPreFacturaDisplay = FormatoNumero.formatoCero(totalPreFacturaDisplay);
										int iTotalPreFacturaDisplay = sTotalPreFacturaDisplay.length();
										articulo = articuloFactura.getNombreArticulo().substring(0, (20-iTotalPreFacturaDisplay-1));
										int iArticulo = articulo.length();
										int iPrimeraLinea = 20 - (iArticulo + iTotalPreFacturaDisplay); //iUnitario);
										String espacioPrimera = "";
										for (int k = 0; k < iPrimeraLinea; k++) {
											espacioPrimera = espacioPrimera +" ";
										}
										primeraLinea = articulo+espacioPrimera+sTotalPreFacturaDisplay; //sUnitario;
										double dCantidad = articuloFactura.getCantidad();
										String sCantidad = ""+dCantidad; //FormatoNumero.formatoCero(dCantidad);
										int iCantidad = sCantidad.length();
										double precio = articuloFactura.getTotalPrecio();
										String sPrecio = FormatoNumero.formatoCero(precio);
										int iPrecio = sPrecio.length();
										int espacioSegunda = (int) (20-(iCantidad + iPrecio));
										String sEspacioSegunda = "";
										for (int l = 0; l < espacioSegunda; l++) {
											sEspacioSegunda = sEspacioSegunda+" ";
										}
										segundaLinea = sCantidad + sEspacioSegunda + sPrecio;

										Runnable r = new PoleDisplay(operacionPoleDisplay,maestroDB, primeraLinea, segundaLinea,false);
										Thread t = new Thread(r);
										t.start();
									}else {
										System.out.println("ConFactura.focusGained() el artículo no existe no muestra nada en el poleDisplay ");
									}

									articuloFactura = null;
									intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
								}
							}
						}
					}
				}
			}
		}

		/*Gson gson = new Gson();
		String sGsonArticuloFactura = gson.toJson(articuloFactura);
		System.out.println("ConFactura.actionPerformed() 519 sGsonArticuloFactura GSON "+sGsonArticuloFactura);*/

		try {
			if (e.getSource() == intCapturaArticulo.getBtnGuardarItem()) {
				String pCantidad = intCapturaArticulo.getTxtCantidad().getText();
				if(pCantidad.equals("")) {
					intCapturaArticulo.getTxtCantidad().setText("1");
					//JOptionPane.showMessageDialog(null, "Cantidad no puede estar vacia.");
				}else{
					if(articuloFactura.getValorUnitario()!=0) {
						String sValorUnitario = intCapturaArticulo.getTxtValorUnitario().getText();
						if(intCapturaArticulo.getTxtValorUnitario().getText().length()>0) {
							sValorUnitario = sValorUnitario.replace(",", "");
							double dValorUnitario = Double.valueOf(sValorUnitario);
							articuloFactura.setValorUnitario(dValorUnitario);
							double dCantidad = Double.valueOf(pCantidad);
							articuloFactura.setCantidad(dCantidad);
							modFactura.ItemEnListaDeCompras(articuloFactura,tablaFacturaModelo);
							modFactura.BorrarCapturaArticulo();
						}
					}
				}
				intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
			} 
			else if (e.getSource() == intPendientes.getBtnPeso()) {
				Peso peso = new Peso();
				peso.pesoBalanza(2,intCapturaArticulo);
			}
			else if (e.getSource() == intKeyNumeros.getBtnEnter()) {
				if(articuloFactura != null) {
					if(articuloFactura.getValorUnitario()==0 && articuloFactura.getId_articulo()!=0) {
						intCapturaArticulo.getTxtValorUnitario().requestFocus();
					}else {
						if(articuloFactura.getId_articulo()!=0) {
							//System.err.println("ConFactura.actionPerformed() getBtnEnter EL CODIGO EXISTE PASE A LA LISTA DE COMPRAS getId_articulo "+articuloFactura.getId_articulo());
							txtCampoGained.transferFocus();
							return;
						}
						if(intCapturaArticulo.getTxtCantidad().getText().length()!=0 && intCapturaArticulo.getTxtCodigoArticulo().getText().length()>0) {
							//System.err.println("ConFactura.actionPerformed() UNIDADES Y CODIGO TIENE VALOR ENTER LO MANDO A CANTIDAD");
							txtCampoGained.transferFocus();
							return;
						}
					}
				}
				else if(articuloFactura == null && txtCampoGained == intCapturaArticulo.getTxtCantidad()){
					intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
				}
				else {
					boolean existeCodigoarticulo = maestroDB.buscarArticuloBtnEnter(intCapturaArticulo.getTxtCodigoArticulo().getText().toUpperCase());
					if(existeCodigoarticulo) {
						txtCampoGained.transferFocus();
					}else {
						JOptionPane.showMessageDialog(null, "Articulo no existe");
						intCapturaArticulo.getTxtCodigoArticulo().setText("");
						intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
					}

					//intCapturaArticulo.getTxtCodigoArticulo().setText("");
					//intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
					//txtCampoGained.transferFocus();
				}
			} 
			else if(e.getSource() == intListaCompra.getBtnBorraItem()){
				tablaFacturaModelo.borraArticulo(articuloSeleccionado);
				modFactura.borrarArticuloDB(G.getInstance().idPreFacturaActual,articuloSeleccionado,id_caja);

				System.err.println("ConFactura.actionPerformed() articuloSeleccionado "+articuloSeleccionado
						+" idPreFacturaActual "+G.getInstance().idPreFacturaActual);

				articuloSeleccionado = -1;

				double subTotal = maestroDB.subTotalSinImpuestos(G.getInstance().idPreFacturaActual ,id_caja);
				String sSubTotal = FormatoNumero.formatoCero(subTotal);
				double impuestos = maestroDB.impuestos(G.getInstance().idPreFacturaActual,id_caja);
				String sImpuestos = FormatoNumero.formatoCero(impuestos);
				double totalFactura = maestroDB.totalFactura(G.getInstance().idPreFacturaActual,id_caja);
				String sTotalFactura = FormatoNumero.formatoCero(totalFactura);

				getIntCapturaArticulo().getTxtTotalFactura().setText(sTotalFactura);
				getIntListaCompra().getTxtTotalfactura().setText(sTotalFactura);
				getIntListaCompra().getTxtSubTotal().setText(sSubTotal);
				getIntListaCompra().getTxtImpuesto().setText(sImpuestos);
			} 
			else if(e.getSource() == intPendientes.getBtnEliminar()) {
				try {
					if(idPreFacturaSeleccionada !=-1) {
						maestroDB.anulaPreFactura(idPreFacturaSeleccionada,id_caja);
						modFactura.BorrarTablaCapturaArticulo(tablaFacturaModelo);
						G.getInstance().idPreFacturaActual=0;
						this.intPendientes.getListModel().removeAllElements();
						alFacturasPendientes = maestroDB.facturasPendientes();
						String nombreDomicilio = "";
						if(alFacturasPendientes != null) {
							for (int j = 0; j < alFacturasPendientes.size(); j++) {				
								nombreDomicilio = maestroDB.consultarDomicilio(alFacturasPendientes.get(j).toString());
								if(nombreDomicilio == null)nombreDomicilio = "";
								this.intPendientes.getListModel().addElement(""+alFacturasPendientes.get(j).toString()+"-"+nombreDomicilio);
							}
						}
						intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} 
			else if(e.getSource() == intListaCompra.getBtnBuscar()) {
				ModeloFacturaBuscar modeloFacturaBuscar = new ModeloFacturaBuscar(this, maestroDB);
				modeloFacturaBuscar.buscarArticulo(principal);
			} 
			else if(e.getSource() == intCapturaArticulo.getBtnDomicilio()){
				ModeloDomicilio modeloDomicilio = new ModeloDomicilio(principal, this, modFactura, maestroDB);
				modeloDomicilio.registrarDomicilio(principal);

				//validar si sigue el domicilio o fue cambiado
				refrescaPendientes();
			} 
			else if(e.getSource() == intListaCompra.getBtnAutorizacion()){
				System.out.println("ConFactura.actionPerformed() getBtnAutorizacion articuloSeleccionado "+articuloSeleccionado);
			} 
			else if(e.getSource() == intListaCompra.getBtnPagarfactura()){

				if(!maestroDB.impresoReporteZ(G.getInstance().licenciaGlobal.getIdCaja())) {
					System.out.println("ConFactura.actionPerformed() getBtnPagarfactura");
					double totalFactura = maestroDB.totalFactura(G.getInstance().idPreFacturaActual,id_caja);

					String sTotalFactura = FormatoNumero.formatoCero(totalFactura);

					int iTotalFactura = (int) (20 - sTotalFactura.length())/2;
					String espacioTotalFactura = "";
					for (int i = 0; i < iTotalFactura; i++) {
						espacioTotalFactura = espacioTotalFactura + " ";
					}

					if(G.getInstance().defaultPort != null) { //if(G.getInstance().defaultPortFounf && !G.getInstance().defaultPort.equals("")) {
						System.out.println("ConFactura.actionPerformed() en el pago se esta mostrando el total de la factuar");
						Runnable r = new PoleDisplay(operacionPoleDisplay, maestroDB, "    Total factura   ",
								espacioTotalFactura+sTotalFactura, false);
						Thread t = new Thread(r);
						t.start();
					}else {
						System.out.println("ConFactura.actionPerformed() en el pago no lo muestra ");
					}

					if(totalFactura>0) {
						ModeloFacturaFormaPago modeloFacturaFormaPago = new ModeloFacturaFormaPago(modFactura,G.getInstance().idPreFacturaActual,modPrincipal, maestroDB, principal, operacionPoleDisplay);
						modeloFacturaFormaPago.formaPago(principal);
					}
				}else {
					JOptionPane.showMessageDialog(null, "¡Ya se imprimió el Comprobante de Informe Diario del día de hoy, "
							+ "no puede facturar, mañana puede continuar!", "Impresión Comprobante de Informe Diario", 
							JOptionPane.ERROR_MESSAGE);
				}
			} 
			else if (e.getSource() == intKeyNumeros.getBtnSuprimir()) {
				txtCampoGained.setText("");
			} 
			else if (e.getSource() == intKeyNumeros.getBtnUno()) {
				txtCampoGained.setText(modFactura.dato("1", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnDos()) {
				txtCampoGained.setText(modFactura.dato("2", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnTres()) {
				txtCampoGained.setText(modFactura.dato("3", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnCero()) {
				txtCampoGained.setText(modFactura.dato("0", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnPunto()) {
				txtCampoGained.setText(modFactura.dato(".", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnMenos()) {
				txtCampoGained.setText(modFactura.dato("-", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnSiete()) {
				txtCampoGained.setText(modFactura.dato("7", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnOcho()) {
				txtCampoGained.setText(modFactura.dato("8", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnNueve()) {
				txtCampoGained.setText(modFactura.dato("9", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnCuatro()) {
				txtCampoGained.setText(modFactura.dato("4", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnCinco()) {
				txtCampoGained.setText(modFactura.dato("5", txtCampoGained.getText(), tipoCampo));
			} 
			else if (e.getSource() == intKeyNumeros.getBtnSeis()) {
				txtCampoGained.setText(modFactura.dato("6", txtCampoGained.getText(), tipoCampo));
			}
		} catch (Exception e1) {
			intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
			e1.printStackTrace();
		}
	}

	private void traeTotalPrefactura(int idPreFacturaActual, int id_caja2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {  // solo JTextField
		/*Primera parte de dos en focusGained: 
		 * Ejecuta lo que se requiere cuando se pierde el foco de un JTextField
		 * Cuando un JTextField gana el foco el que lo pierde hace algo*/
		// TODO cuando el campo cantidad pierde el foco debe modificar el total del producto o por medio de enter.
		// TODO cuando se coloca el documento del cliente y pierde el focus debe actualizar la tabla preFactura.

		if(txtCampoGained == intCapturaArticulo.getTxtDocumentoCliente()) {
			listaPrecioClienteTercero = modFactura.procedimientoSaleDeDocumentoCliente();
		}
		else if (txtCampoGained == intCapturaArticulo.getTxtCodigoVendedor()) {
			modFactura.procedimientoSaleDeCodigoVendedor();

		}
		else if (txtCampoGained == intCapturaArticulo.getTxtCodigoArticulo()) {
			System.out.println("ConFactura.focusGained() salio de getTxtCodigoArticulo y su valor es "
					+intCapturaArticulo.getTxtCodigoArticulo().getText());
			articuloFactura = null;
			if(!intCapturaArticulo.getTxtCodigoArticulo().getText().equals("")) {
				articuloFactura = modFactura.procedimientoSaleDeCodigoArticulo(listaPrecioClienteTercero, idVendedor);

				//if(G.getInstance().defaultPortFounf == true && !G.getInstance().defaultPort.equals("") && articuloFactura!=null){// operacionPoleDisplay!=null) {
				if(G.getInstance().defaultPort != null && articuloFactura!=null) {	
					String primeraLinea = "";
					String segundaLinea = "";
					String articulo = articuloFactura.getNombreArticulo();
					
					double totalPreFacturaDisplay = maestroDB.traeTotalPrefactura(G.getInstance().idPreFacturaActual, id_caja);

					System.out.println("X1X1X ConFactura.actionPerformed() totalPreFacturaDisplay "+totalPreFacturaDisplay+" idPreFacturaActual "+G.getInstance().idPreFacturaActual);

					String sTotalPreFacturaDisplay = FormatoNumero.formatoCero(totalPreFacturaDisplay);
					int iTotalPreFacturaDisplay = sTotalPreFacturaDisplay.length();
					
					int anchoPago = (20-iTotalPreFacturaDisplay);
					int iArticulo = articulo.length();
					articulo = articuloFactura.getNombreArticulo();
					
					if(iArticulo > anchoPago) {
						articulo = articulo.substring(0, (anchoPago-1));
					}else {
						articulo = articuloFactura.getNombreArticulo();
					}
					iArticulo = articulo.length();
					int iPrimeraLinea = 20 - (iArticulo + iTotalPreFacturaDisplay); //iUnitario);
					String espacioPrimera = "";
					for (int k = 0; k < iPrimeraLinea; k++) {
						espacioPrimera = espacioPrimera +" ";
					}
					
					primeraLinea = articulo+espacioPrimera+sTotalPreFacturaDisplay; //sUnitario;
					double dCantidad = articuloFactura.getCantidad();
					String sCantidad = ""+dCantidad; //FormatoNumero.formatoCero(dCantidad);
					int iCantidad = sCantidad.length();
					double precio = articuloFactura.getTotalPrecio();
					String sPrecio = FormatoNumero.formatoCero(precio);
					int iPrecio = sPrecio.length();
					int espacioSegunda = (int) (20-(iCantidad + iPrecio));
					String sEspacioSegunda = "";
					for (int i = 0; i < espacioSegunda; i++) {
						sEspacioSegunda = sEspacioSegunda+" ";
					}
					segundaLinea = sCantidad + sEspacioSegunda + sPrecio;

					Runnable r = new PoleDisplay(operacionPoleDisplay,maestroDB, primeraLinea, segundaLinea, false);
					Thread t = new Thread(r);
					t.start();
				}else {
					System.out.println("ConFactura.focusGained() el artículo no existe no muestra nada en el poleDisplay ");
				}

				String autorizaCambioPrecio = maestroDB.cargarUnParametro("autorizaCambioPrecio");
				boolean bAutorizaCambioPrecio = Boolean.valueOf(autorizaCambioPrecio);

				if(articuloFactura != null) {
					if(articuloFactura.getValorUnitario()==0) {
						Gson gson = new Gson();
						String sGsonArticuloFactura = gson.toJson(articuloFactura);
						System.out.println("ConFactura.focusGained() 691 sGsonArticuloFactura GSON "+sGsonArticuloFactura);
						intCapturaArticulo.getTxtValorUnitario().setFocusable(true);
						intCapturaArticulo.getTxtValorUnitario().requestFocus();
					}else if(bAutorizaCambioPrecio) {
						Gson gson = new Gson();
						String sGsonArticuloFactura = gson.toJson(articuloFactura);
						System.out.println("ConFactura.focusGained() 697 sGsonArticuloFactura GSON "+sGsonArticuloFactura);
						intCapturaArticulo.getTxtValorUnitario().setText(""+articuloFactura.getValorUnitario());
						intCapturaArticulo.getTxtValorUnitario().setFocusable(true);
						intCapturaArticulo.getTxtValorUnitario().requestFocus();
					}
					/*if(articuloFactura.getValorUnitario()!=0) {
						articuloFactura = null;
					}else {

					}*/

				}else {
					System.err.println("ConFactura.focusGained() 798 getTxtCodigoArticulo (articuloFactura == null");
					//articuloFactura = null;
					//txtCampoGained = intCapturaArticulo.getTxtCantidad();

					//intCapturaArticulo.getTxtCantidad().requestFocus();
					//return;
				}
			}
		}

		else if (txtCampoGained == intCapturaArticulo.getTxtUnidades()) {
			//System.out.println("ConFactura.focusGained() Salió de Unidades y su valor es " + txtCampoGained.getText());
			//modFactura.procedimientoSaleDeUnidades();
		}

		else if (txtCampoGained == intCapturaArticulo.getTxtCantidad()) {
			//System.out.println("ConFactura.focusGained() 725 Salió de Cantidad y su valor es " + txtCampoGained.getText()+" getTxtCodigoArticulo "+intCapturaArticulo.getTxtCodigoArticulo().getText()+"-X");
			modFactura.procedimientoSaleDeCantidad(intCapturaArticulo.getTxtCantidad());
		}

		else if (txtCampoGained == intCapturaArticulo.getTxtValorUnitario()) {
			//System.out.println("ConFactura.focusGained() 735 Salió de Valorunitario y su valor es " + txtCampoGained.getText()+" SALIO DE VALOR UNITARIO Y SU VALOR ES "+intCapturaArticulo.getTxtValorUnitario().getText());

			/*Gson gson = new Gson();
			String sGsonArticuloFactura = gson.toJson(articuloFactura);
			System.out.println("ConFactura.focusGained() 734 sGsonArticuloFactura GSON "+sGsonArticuloFactura);
			articuloFactura = modFactura.procedimientoSaleDeCodigoArticulo(listaPrecioClienteTercero, idVendedor);*/

			if(articuloFactura==null) {
				System.out.println("ConFactura.focusGained() articuloFactura ES NULL PAILA");
			}
			
			System.out.println("ConFactura.focusGained() listaPrecioClienteTercero "+listaPrecioClienteTercero+" idVendedor "+idVendedor);
			modFactura.procedimientoSaleDeValorUnitario(articuloFactura,listaPrecioClienteTercero, idVendedor);		
		}
		//************************************************************************************************
		//Segunda parte de dos de focusGained: Ejectuta lo que se requiere cuando se gana el foco.
		intCapturaArticulo.getTxtDocumentoCliente().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtNombreCliente().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtCodigoVendedor().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtNombreVendedor().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtCodigoArticulo().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtNombreArticulo().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtMedida().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtUnidades().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtCantidad().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtValorUnitario().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtValorTotal().setBackground(Color.WHITE);
		intCapturaArticulo.getTxtTotalFactura().setBackground(Color.WHITE);
		try {
			txtCampoGained = (JTextField) e.getSource();
		} catch (Exception e2) {
			System.out.println("ConFactura.focusGained() 756 ERROR "+e2);
			e2.printStackTrace();
		}
		if(e.getSource() == intCapturaArticulo.getTxtDocumentoCliente()) {
			intCapturaArticulo.getTxtDocumentoCliente().setBackground(Color.YELLOW);
			tipoCampo = 2;
		}else if(e.getSource() == intCapturaArticulo.getTxtCodigoVendedor()){
			intCapturaArticulo.getTxtCodigoVendedor().setBackground(Color.YELLOW);
			tipoCampo = 0;
		}else if(e.getSource() == intCapturaArticulo.getTxtCodigoArticulo()){
			intCapturaArticulo.getTxtCodigoArticulo().setBackground(Color.YELLOW);
			tipoCampo = 3;
		}else if(e.getSource() == intCapturaArticulo.getTxtNombreArticulo()) {
			intCapturaArticulo.getTxtNombreArticulo().setBackground(Color.YELLOW);
			tipoCampo = 3;
		}else if(e.getSource() == intCapturaArticulo.getTxtUnidades()) {
			intCapturaArticulo.getTxtUnidades().setBackground(Color.YELLOW);
			tipoCampo = 0;
		}else if(e.getSource() == intCapturaArticulo.getTxtCantidad()) {
			intCapturaArticulo.getTxtCantidad().setBackground(Color.YELLOW);
			System.out.println("ConFactura.focusGained() 726 intCapturaArticulo.getTxtCantidad() ");
			tipoCampo = 1;			
		}else if(e.getSource() == intCapturaArticulo.getTxtValorUnitario()) {
			intCapturaArticulo.getTxtValorUnitario().setBackground(Color.YELLOW);
			tipoCampo = 1;
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
		intKeyNumeros.getRootPane().setDefaultButton(intKeyNumeros.getBtnEnter());
		if(e.getKeyChar() ==KeyEvent.VK_ENTER) {
			txtCampoGained.transferFocus();
		}
		if (e.getSource() == intCapturaArticulo.getTxtCodigoVendedor()) {
			EntradaTeclado.validarNumero(e);
		} 
		else if (e.getSource() == intCapturaArticulo.getTxtDocumentoCliente()) {
			EntradaTeclado.validarNumero(e); // Se asegura de que ingrese solo números
		} 
		else if (e.getSource() == intCapturaArticulo.getTxtUnidades()) {
			EntradaTeclado.validarNumero(e);
		} 
		else if (e.getSource() == intCapturaArticulo.getTxtCantidad()) {
			EntradaTeclado.validarDecimal(e, txtCampoGained.getText());
		} 
		else if (e.getSource() == intCapturaArticulo.getTxtValorUnitario()) {
			EntradaTeclado.validarDecimal(e, txtCampoGained.getText());
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		//Se le resta 1 para que las tabletas definidas en configuración correspondan a las de facturación.
		tableta = this.intContenedor.getTabbedPane().getSelectedIndex()-1;
	}

	public void refrescaPendientes() {
		System.out.println("ConFactura ENTRO EN REFRESCAR PENDIENTES ListModelFacturasPendientes "+intPendientes.getListModel().getSize());
		alFacturasPendientes = maestroDB.facturasPendientes();
		this.intPendientes.getListModel().removeAllElements();
		String nombreDomicilio = "";
		if(alFacturasPendientes != null) {
			for (int j = 0; j < alFacturasPendientes.size(); j++) {				
				nombreDomicilio = maestroDB.consultarDomicilio(alFacturasPendientes.get(j).toString());
				if(nombreDomicilio == null)nombreDomicilio = "";
				this.intPendientes.getListModel().addElement(""+alFacturasPendientes.get(j).toString()+"-"+nombreDomicilio);
			}
		}
	}

	public IntFactura getIntFactura() {
		return intFactura;
	}
	public void setIntFactura(IntFactura intFactura) {
		this.intFactura = intFactura;
	}
	public IntBotonesRapidos1 getIntBotonesRapidos1() {
		return intBotonesRapidos1;
	}
	public void setIntBotonesRapidos1(IntBotonesRapidos1 intBotonesRapidos1) {
		this.intBotonesRapidos1 = intBotonesRapidos1;
	}
	public IntBotonesRapidos2 getIntBotonesRapidos2() {
		return intBotonesRapidos2;
	}
	public void setIntBotonesRapidos2(IntBotonesRapidos2 intBotonesRapidos2) {
		this.intBotonesRapidos2 = intBotonesRapidos2;
	}
	public IntBotonesRapidos3 getIntBotonesRapidos3() {
		return intBotonesRapidos3;
	}
	public void setIntBotonesRapidos3(IntBotonesRapidos3 intBotonesRapidos3) {
		this.intBotonesRapidos3 = intBotonesRapidos3;
	}
	public IntCapturaArticulo getIntCapturaArticulo() {
		return intCapturaArticulo;
	}
	public void setIntCapturaArticulo(IntCapturaArticulo intCapturaArticulo) {
		this.intCapturaArticulo = intCapturaArticulo;
	}
	public IntContenedor getIntContenedor() {
		return intContenedor;
	}
	public void setIntContenedor(IntContenedor intContenedor) {
		this.intContenedor = intContenedor;
	}
	public IntListaCompra getIntListaCompra() {
		return intListaCompra;
	}
	public void setIntListaCompra(IntListaCompra intListaCompra) {
		this.intListaCompra = intListaCompra;
	}
	public IntNumeros getIntNumeros() {
		return intNumeros;
	}
	public void setIntNumeros(IntNumeros intNumeros) {
		this.intNumeros = intNumeros;
	}
	public ModFactura getModFactura() {
		return modFactura;
	}
	public void setModFactura(ModFactura modFactura) {
		this.modFactura = modFactura;
	}
	public TablaFacturaModelo getTablaFacturaModelo() {
		return tablaFacturaModelo;
	}
	public void setTablaFacturaModelo(TablaFacturaModelo tablaFacturaModelo) {
		this.tablaFacturaModelo = tablaFacturaModelo;
	}

	public IntPedientes getIntPendientes() {
		return intPendientes;
	}

	public void setIntPendientes(IntPedientes intPendientes) {
		this.intPendientes = intPendientes;
	}

	public ArrayList<String> getAlFacturasPendientes() {
		return alFacturasPendientes;
	}

	public void setAlFacturasPendientes(ArrayList<String> alFacturasPendientes) {
		this.alFacturasPendientes = alFacturasPendientes;
	}

	public MaestroDB getMaestroDB() {
		return maestroDB;
	}

	public void setMaestroDB(MaestroDB maestroDB) {
		this.maestroDB = maestroDB;
	}
}
