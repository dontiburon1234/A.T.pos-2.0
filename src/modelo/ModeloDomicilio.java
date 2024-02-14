package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.google.gson.Gson;

import clases.Cliente;
import clases.Domicilio;
import clases.Tercero;
import controlador.ConFactura;
import database.MaestroDB;
import gui.IntKeyLetras;
import gui.IntKeyNumeros;
import guifactura.IntDomicilio;
import main.Principal;
import util.EntradaTeclado;
import util.FormatoNumero;
import util.G;

public class ModeloDomicilio implements ActionListener,KeyListener,FocusListener  {

	private ConFactura conFactura;
	private Principal principal;
	private ModFactura modFactura;

	private JDialog dialogDomicilio;
	private IntDomicilio intDomicilio = new IntDomicilio();
	private IntKeyNumeros intKeyNumeros = new IntKeyNumeros();
	private IntKeyLetras intKeyLetras = new IntKeyLetras();
	private MaestroDB maestroDB; 

	private JTextField txtCampoGained = null;
	private JTextArea taCampoGained;
	private int idPreFacturaActual = 0;

	private int id_cliente; // integer,
	private int id_caja; // integer,
	private int id_factura; // integer,
	private int id_prefactura; // integer NOT NULL DEFAULT 0,
	private double documento; // numeric(20,0),
	private String nombre; // character varying(100),
	private String apellido; // character varying(100),
	private String direccion; // character varying(100),
	private String telefono; // character varying(100),
	private String listadoPedido; // text

	public ModeloDomicilio(Principal principal,ConFactura conFactura,ModFactura modFactura, MaestroDB maestroDB) {
		super();
		this.conFactura = conFactura;
		this.principal = principal;
		this.modFactura = modFactura;
		this.maestroDB = maestroDB;

		this.intKeyLetras.getBtnQ().addActionListener(this);
		this.intKeyLetras.getBtnW().addActionListener(this);
		this.intKeyLetras.getBtnE().addActionListener(this);
		this.intKeyLetras.getBtnR().addActionListener(this);
		this.intKeyLetras.getBtnT().addActionListener(this);
		this.intKeyLetras.getBtnY().addActionListener(this);
		this.intKeyLetras.getBtnU().addActionListener(this);
		this.intKeyLetras.getBtnI().addActionListener(this);
		this.intKeyLetras.getBtnO().addActionListener(this);
		this.intKeyLetras.getBtnP().addActionListener(this);
		this.intKeyLetras.getBtnA().addActionListener(this);
		this.intKeyLetras.getBtnS().addActionListener(this);
		this.intKeyLetras.getBtnD().addActionListener(this);
		this.intKeyLetras.getBtnF().addActionListener(this);
		this.intKeyLetras.getBtnG().addActionListener(this);
		this.intKeyLetras.getBtnH().addActionListener(this);
		this.intKeyLetras.getBtnJ().addActionListener(this);
		this.intKeyLetras.getBtnK().addActionListener(this);
		this.intKeyLetras.getBtnL().addActionListener(this);
		this.intKeyLetras.getBtnEnne().addActionListener(this);
		this.intKeyLetras.getBtnZ().addActionListener(this);
		this.intKeyLetras.getBtnX().addActionListener(this);
		this.intKeyLetras.getBtnC().addActionListener(this);
		this.intKeyLetras.getBtnV().addActionListener(this);
		this.intKeyLetras.getBtnB().addActionListener(this);
		this.intKeyLetras.getBtnN().addActionListener(this);
		this.intKeyLetras.getBtnM().addActionListener(this);
		this.intKeyLetras.getBtnPunto().addActionListener(this);
		this.intKeyLetras.getBtnQuion().addActionListener(this);
		this.intKeyLetras.getBtnPor().addActionListener(this);
		this.intKeyLetras.getBtnEspacio().addActionListener(this);

		this.intKeyNumeros.getBtnSiete().addActionListener(this);
		this.intKeyNumeros.getBtnOcho().addActionListener(this);
		this.intKeyNumeros.getBtnNueve().addActionListener(this);
		this.intKeyNumeros.getBtnCuatro().addActionListener(this);
		this.intKeyNumeros.getBtnCinco().addActionListener(this);
		this.intKeyNumeros.getBtnSeis().addActionListener(this);
		this.intKeyNumeros.getBtnUno().addActionListener(this);
		this.intKeyNumeros.getBtnDos().addActionListener(this);
		this.intKeyNumeros.getBtnTres().addActionListener(this);
		this.intKeyNumeros.getBtnPunto().addActionListener(this);
		this.intKeyNumeros.getBtnMenos().addActionListener(this);
		this.intKeyNumeros.getBtnEnter().addActionListener(this);
		this.intKeyNumeros.getBtnPor().addActionListener(this);
		this.intKeyNumeros.getBtnSuprimir().addActionListener(this);
		this.intKeyNumeros.getBtnCero().addActionListener(this);

		this.intDomicilio.getTxtId().setFocusable(false);
		this.intDomicilio.getTxtDocumento().addFocusListener(this);
		this.intDomicilio.getTxtNombre().addFocusListener(this);
		this.intDomicilio.getTxtApellido().addFocusListener(this);
		this.intDomicilio.getTxtDireccion().addFocusListener(this);
		this.intDomicilio.getTxtTelefono().addFocusListener(this);
		
		this.intDomicilio.getTxtDocumento().addKeyListener(this);
		this.intDomicilio.getTxtNombre().addKeyListener(this);
		this.intDomicilio.getTxtApellido().addKeyListener(this);
		this.intDomicilio.getTxtDireccion().addKeyListener(this);
		this.intDomicilio.getTxtTelefono().addKeyListener(this);

		this.intDomicilio.getTaListadoPedido().addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}

			@Override
			public void focusGained(FocusEvent e) {
				intDomicilio.getTxtDocumento().setBackground(Color.WHITE);
				intDomicilio.getTxtNombre().setBackground(Color.WHITE);
				intDomicilio.getTxtApellido().setBackground(Color.WHITE);
				intDomicilio.getTxtDireccion().setBackground(Color.WHITE);
				intDomicilio.getTxtTelefono().setBackground(Color.WHITE);
				intDomicilio.getTaListadoPedido().setBackground(Color.YELLOW);
				taCampoGained = (JTextArea) e.getSource();
				txtCampoGained = null;
			}
		});

		txtCampoGained = intDomicilio.getTxtDocumento();
		
		this.intDomicilio.getBtnCancelar().addActionListener(this);
		this.intDomicilio.getBtnBuscar().addActionListener(this);
		this.intDomicilio.getBtnLimpiar().addActionListener(this);

		this.intDomicilio.getTxtId().setName("id");
		this.intDomicilio.getTxtDocumento().setName("documento");
		this.intDomicilio.getTxtNombre().setName("nombre");
		this.intDomicilio.getTxtApellido().setName("apellido");
		this.intDomicilio.getTxtDireccion().setName("direccion");
		this.intDomicilio.getTxtTelefono().setName("telefono");
		this.intDomicilio.getTaListadoPedido().setName("listadopedido");
		this.intDomicilio.getBtnCancelar().setName("cancelar");
		this.intDomicilio.getBtnBuscar().setName("buscar");
		this.intDomicilio.getBtnLimpiar().setName("limpiar");

	}

	public void registrarDomicilio(Principal principal) {
		dialogDomicilio = new JDialog(principal,"ATpos - Administraci\u00F3n Total del punto de venta - Domicilio",Dialog.ModalityType.DOCUMENT_MODAL);
		Toolkit tamano = Toolkit.getDefaultToolkit();
		int ancho =  (int) ((tamano.getScreenSize().getWidth()-1100)/2);
		int alto = (int) ((tamano.getScreenSize().getHeight()-650)/2);
		dialogDomicilio.setBounds(ancho, alto, 1100, 650);
		ModeloFacturaBuscar.addEscapeListenerWindowDialog(dialogDomicilio);

		JPanel jpTecleado = new JPanel();
		jpTecleado.add(intKeyLetras);
		jpTecleado.add(intKeyNumeros);

		id_caja = G.getInstance().licenciaGlobal.getIdCaja();
		id_prefactura = G.getInstance().idPreFacturaActual;
		//System.out.println("44444 ModeloDomicilio.registrarDomicilio() id_prefactura "+id_prefactura);

		// tiene prefactura y tiene cliente 
		Domicilio domicilio;

		/*double totalFactura = maestroDB.totalFactura(id_prefactura, id_caja);
		System.out.println("ModeloDomicilio.registrarDomicilio() totalFactura "+totalFactura);*/

		if(id_prefactura != 0) {
			domicilio = maestroDB.traerDomicilio(id_prefactura,G.getInstance().licenciaGlobal.getIdAlmacen());
			// si está registrado el domicilio en la tabla domicilio lo trae
			if(domicilio != null) {
				System.out.println("ModeloDomicilio.registrarDomicilio() DOMICILIO NO ES NULL DOMICILIO NO ES NULL DOMICILIO NO ES NULL DOMICILIO NO ES NULL ");
				this.intDomicilio.getTxtId().setText(""+domicilio.getId());
				double dDocumento = domicilio.getDocumento();
				String sDocumento = FormatoNumero.formatoCero(dDocumento);
				this.intDomicilio.getTxtDocumento().setText(sDocumento);
				this.intDomicilio.getTxtNombre().setText(domicilio.getNombre());
				this.intDomicilio.getTxtApellido().setText(domicilio.getApellido());
				this.intDomicilio.getTxtDireccion().setText(domicilio.getDireccion());
				this.intDomicilio.getTxtTelefono().setText(domicilio.getTelefono());
				this.intDomicilio.getTaListadoPedido().setText(domicilio.getListadoPedido());
			}else {
				// si hay una prefactura pero no se ha seleccionado cliente borra la ventana
				this.intDomicilio.getTxtId().setText("");
				this.intDomicilio.getTxtDocumento().setText("");
				this.intDomicilio.getTxtNombre().setText("");
				this.intDomicilio.getTxtApellido().setText("");
				this.intDomicilio.getTxtDireccion().setText("");
				this.intDomicilio.getTxtTelefono().setText("");
				this.intDomicilio.getTaListadoPedido().setText(".");
			}

			if(G.getInstance().documentoClienteTercero!=null) {
				// si no está en la tabla domicilio pero ya existe una prefactura y ya se seleccionó al cliente o tercero, 
				// trae los datos del cliente para colocarlos en el domicilio.
				id_caja = G.getInstance().licenciaGlobal.getIdCaja();
				String numeroDocumentoCliente = "";
				numeroDocumentoCliente = G.getInstance().documentoClienteTercero.replace(",","");				
				Tercero tercero = new Tercero();
				Cliente cliente = new Cliente();
				tercero = maestroDB.consultarTercero(numeroDocumentoCliente);
				if (tercero == null) {
					cliente = maestroDB.consultarCliente(numeroDocumentoCliente);
					if(cliente == null) {
						if(!numeroDocumentoCliente.equals("")) {
							JOptionPane.showMessageDialog(null, "Documento equivocado del cliente.");
						}
					}else {
						this.intDomicilio.getTxtId().setText(""+cliente.getId());
						double dDocumento = cliente.getDocumento();
						String sDocumento = FormatoNumero.formatoCero(dDocumento);
						this.intDomicilio.getTxtDocumento().setText(sDocumento);
						this.intDomicilio.getTxtNombre().setText(cliente.getNombre());
						this.intDomicilio.getTxtApellido().setText(cliente.getApellido());
						this.intDomicilio.getTxtDireccion().setText(cliente.getDireccion());
						this.intDomicilio.getTxtTelefono().setText(cliente.getTelefono());
						//this.intDomicilio.getPnListadoPedido().setText(cliente.getListadoPedido());
					}
				}else {
					this.intDomicilio.getTxtId().setText(""+tercero.getId());
					double dDocumento = tercero.getDocumento();
					String sDocumento = FormatoNumero.formatoCero(dDocumento);
					this.intDomicilio.getTxtDocumento().setText(sDocumento);
					this.intDomicilio.getTxtNombre().setText(tercero.getNombre());
					this.intDomicilio.getTxtApellido().setText(tercero.getApellido());
					this.intDomicilio.getTxtDireccion().setText(tercero.getDireccion());
					this.intDomicilio.getTxtTelefono().setText(tercero.getTelefono());
					//this.intDomicilio.getPnListadoPedido().setText(tercero.getListadoPedido());
				}
			}
		}

		Container container = dialogDomicilio.getContentPane();
		container.setLayout(new BorderLayout(0,0));
		container.add(intDomicilio, BorderLayout.CENTER);
		container.add(jpTecleado,BorderLayout.PAGE_END);

		dialogDomicilio.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = null;
		try {
			jButton = (JButton) e.getSource();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(jButton.getText().equals("Supr")) {
			//txtCampoGained.setText("");
			if(txtCampoGained!=null) {
				int tamanoCampo = txtCampoGained.getText().length();
				if(tamanoCampo!=0) {
					String textoCampo = txtCampoGained.getText();
					textoCampo = textoCampo.substring(0, tamanoCampo-1);
					txtCampoGained.setText(textoCampo);
				}
			}else if(taCampoGained!=null) {
				int tamanoCampo = taCampoGained.getText().length();
				if(tamanoCampo!=0) {
					String textoCampo = taCampoGained.getText();
					textoCampo = textoCampo.substring(0, tamanoCampo-1);
					taCampoGained.setText(textoCampo);
				}
			}
		}
		else if(jButton.getText().equals("Limpiar")) {
			intDomicilio.getTxtId().setText("");
			intDomicilio.getTxtDocumento().setText("");
			intDomicilio.getTxtNombre().setText("");
			intDomicilio.getTxtApellido().setText("");
			intDomicilio.getTxtDireccion().setText("");
			intDomicilio.getTxtTelefono().setText("");
			intDomicilio.getTaListadoPedido().setText("");
			maestroDB.borrarDocimcilio(id_prefactura);
		}

		else if(jButton.getText().equals("Intro")) {
			// crear o actualizar los datos si tuvieron modificacion

			int id = 0;			
			documento = 0;
			String sDocumento = intDomicilio.getTxtDocumento().getText();
			String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input );
			id_caja = G.getInstance().licenciaGlobal.getIdCaja();

			// TODO que el usuario pueda configurar los datos mínimos solicitados en el domicilio
			if( intDomicilio.getTxtNombre().getText().equals("") || intDomicilio.getTxtDireccion().getText().equals("") ) {
				JOptionPane.showMessageDialog(null, "Debe tener mínimo nombre y dirección");
			}else{
				sDocumento = intDomicilio.getTxtDocumento().getText().replace(",", "");
				documento = Double.valueOf(sDocumento);
				nombre = intDomicilio.getTxtNombre().getText().toUpperCase();
				apellido = intDomicilio.getTxtApellido().getText().toUpperCase();
				direccion = intDomicilio.getTxtDireccion().getText().toUpperCase();
				telefono = intDomicilio.getTxtTelefono().getText();
				listadoPedido = intDomicilio.getTaListadoPedido().getText();		
				if(intDomicilio.getTxtId().getText().equals("")) { // se crea el cliente
					//TODO informarle al usuario que el numero de registro del documento ya está creado y mostrale los datos
					id_cliente = maestroDB.actualizarOCrearCliente(0,documento,nombre,apellido,direccion,telefono,listadoPedido);
				}else { // se actualiza el cliente
					id = Integer.valueOf(intDomicilio.getTxtId().getText());
					id_cliente = maestroDB.actualizarOCrearCliente(id,documento,nombre,apellido,direccion,telefono,listadoPedido);
				}
				intDomicilio.getTxtId().setText(""+id_cliente);
				dialogDomicilio.dispose();
			}

			System.out.println("ModeloDomicilio.actionPerformed() id "+id+" documento "+documento+" nombre "+nombre+
					" apellido "+apellido+" direccion "+direccion+" telefono "+telefono+" listadoPedido "+listadoPedido);

			// crear la prefactura y tomar el id_preFactura para colocarla en la tabla domicilio
			// si la preFactura ya fue creada no es ncesario la creación

			idPreFacturaActual = G.getInstance().idPreFacturaActual;

			// no tiene prefactura y no se ha creado el domicilio, para crear un domicilio es necesario tener una prefactura
			if(idPreFacturaActual==0){
				double numero = 0;
				int auxiliar = 0;
				int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
				Date fecha = ts;				
				int id_cajero = G.getInstance().getIdCajero;

				int id_vendedor;
				String sId_vendedor = conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText();
				if(sId_vendedor.equals("")) {
					id_vendedor=1;
				}else {
					id_vendedor = Integer.valueOf(sId_vendedor);
				}				
				String id_usuario_vendedor = maestroDB.consultarIdVendedor(id_vendedor);

				int id_agente_externo = 0;
				double valor_prefactura = 0;
				double valor_descuento = 0;
				double valor_iva = 0;
				String comentario = "";
				String estado = "proceso";

				int idResultadoPreFactura = maestroDB.agregarPreFactura (id_caja, numero, auxiliar, id_almacen,
						fecha, id_cajero, id_vendedor, id_usuario_vendedor,
						id_cliente, id_agente_externo, valor_prefactura, valor_descuento,
						valor_iva, comentario, estado);
				
				System.out.println("ModeloDomicilio.actionPerformed() dvldhkvlwhdkjvhsdkj id_cliente "+id_cliente);
				Cliente cliente = maestroDB.consultarCliente(id_cliente);
				conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setText(""+cliente.getDocumento());
				conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());

				String comentarioDomicilio = "Domicilio "+ maestroDB.agregarDomicilio(id_cliente, id_caja, G.getInstance().licenciaGlobal.getIdAlmacen(), 
						id_factura, idResultadoPreFactura,documento, nombre, apellido, direccion, telefono, listadoPedido, 1, "");

				G.getInstance().idPreFacturaActual = idResultadoPreFactura;
				maestroDB.actualizaComentarioPreFactura(comentarioDomicilio, G.getInstance().idPreFacturaActual,id_caja);

				System.out.println("22222-1 ModeloDomicilio.actionPerformed() idResultadoPreFactura "+idResultadoPreFactura+
						" id_cliente "+id_cliente+" comentarioDomicilio "+comentarioDomicilio);
				//dialogDomicilio.dispose();
			}else {
				if(id_cliente!=0) {
					Domicilio domicilio = maestroDB.traerDomicilio(G.getInstance().idPreFacturaActual,G.getInstance().licenciaGlobal.getIdAlmacen());
					Cliente cliente = maestroDB.consultarCliente(id_cliente);
					conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setText(""+cliente.getDocumento());
					conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
					if(domicilio == null) { // si el domicilio ya está creado no hace nada

						String comentarioDomicilio = "Domicilio "+ maestroDB.agregarDomicilio(id_cliente, id_caja, G.getInstance().licenciaGlobal.getIdAlmacen(), 
								id_factura, G.getInstance().idPreFacturaActual,documento, nombre, apellido, direccion, telefono, listadoPedido, 1, "");

						maestroDB.agregarClienteTercero(id_cliente, G.getInstance().idPreFacturaActual, id_caja);
						maestroDB.actualizaComentarioPreFactura(comentarioDomicilio, G.getInstance().idPreFacturaActual,id_caja);
					}
				}
			}

		}else if(jButton.getText().equals("Buscar")){
			ModeloBuscarCliente modeloBuscarCliente = new ModeloBuscarCliente(conFactura,maestroDB);
			modeloBuscarCliente.buscarCliente(principal,this);

		}else if(jButton.getText().equals("Cancelar")){
			dialogDomicilio.dispose();
		}else if(taCampoGained!=null){
			if(jButton.getText().equals("Espacio")) {
				taCampoGained.append(" ");
			}else {
				if(taCampoGained.getText().length()!=0) {
					taCampoGained.append(jButton.getText());
				}
			}
		}else{
			if(txtCampoGained == intDomicilio.getTxtDocumento()) {
				txtCampoGained.setText(modFactura.dato(jButton.getText(), txtCampoGained.getText(), 2));
			}else{
				if(jButton.getText().equals("Espacio")) {
					txtCampoGained.setText(txtCampoGained.getText()+" ");
				}else {
					txtCampoGained.setText(txtCampoGained.getText()+jButton.getText());
				}
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		intDomicilio.getTxtDocumento().setBackground(Color.WHITE);
		intDomicilio.getTxtNombre().setBackground(Color.WHITE);
		intDomicilio.getTxtApellido().setBackground(Color.WHITE);
		intDomicilio.getTxtDireccion().setBackground(Color.WHITE);
		intDomicilio.getTxtTelefono().setBackground(Color.WHITE);

		if(txtCampoGained == intDomicilio.getTxtDocumento()) {
			Cliente cliente = new Cliente();
			String sDocumento = intDomicilio.getTxtDocumento().getText().toString();
			sDocumento = sDocumento.replace(",", "");
			cliente = maestroDB.consultarCliente(sDocumento);
			
			Gson gson = new Gson();
			String sGsonCliente = gson.toJson(cliente);
			System.out.println("ModeloDomicilio.focusGained() sGsonCliente GSON "+sGsonCliente);

			if(cliente!=null) {
				if(cliente.getId()!=0) {
					id_cliente = cliente.getId();
					intDomicilio.getTxtId().setText(""+cliente.getId());
					intDomicilio.getTxtNombre().setText(cliente.getNombre());
					intDomicilio.getTxtApellido().setText(cliente.getApellido());
					intDomicilio.getTxtDireccion().setText(cliente.getDireccion());
					intDomicilio.getTxtTelefono().setText(cliente.getTelefono());

					intDomicilio.getTxtDocumento().setFocusable(false);

					System.out.println("ModeloDomicilio.focusGained() PRIMERO PASO POR AQUI PRIMERO PASO POR AQUI PRIMERO PASO POR AQUI PRIMERO PASO POR AQUI PRIMERO PASO POR AQUI ");
				}else {
					System.out.println("ModeloDomicilio.focusGained() EL CLIENTE NO EXISTE EL CLIENTE NO EXISTE EL CLIENTE NO EXISTE EL CLIENTE NO EXISTE EL CLIENTE NO EXISTE ");
				}
			}
		}
		try {
			txtCampoGained = (JTextField) e.getSource();
			taCampoGained = null;
			intDomicilio.getTaListadoPedido().setBackground(Color.WHITE);
		} catch (Exception e1) {
			System.err.println("ModeloDomicilio.focusGained() ERROR DE CAST ");
			e1.printStackTrace();
		}

		if(e.getSource() == intDomicilio.getTxtDocumento()) {
			intDomicilio.getTxtDocumento().setBackground(Color.YELLOW);
		}else if(e.getSource() == intDomicilio.getTxtNombre()) {
			intDomicilio.getTxtNombre().setBackground(Color.YELLOW);
		}else if(e.getSource() == intDomicilio.getTxtApellido()){
			intDomicilio.getTxtApellido().setBackground(Color.YELLOW);
		}else if(e.getSource() == intDomicilio.getTxtDireccion()) {
			intDomicilio.getTxtDireccion().setBackground(Color.YELLOW);
		}else if(e.getSource() == intDomicilio.getTxtTelefono()) {
			intDomicilio.getTxtTelefono().setBackground(Color.YELLOW);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("ModeloDomicilio.keyTyped() getRootPanegetRootPanegetRootPanegetRootPanegetRootPanegetRootPanegetRootPane");
		intKeyNumeros.getBtnEnter().getRootPane().setDefaultButton(intKeyNumeros.getBtnEnter());
		if(e.getKeyChar() ==KeyEvent.VK_ENTER) {
			txtCampoGained.transferFocus();

		}else if(txtCampoGained == intDomicilio.getTxtDocumento()) {
			EntradaTeclado.validarNumero(e);
		}

		/*else if (e.getSource() == intDomicilio.getTxtDocumento()) {
			EntradaTeclado.validarNumero(e);
		}else if(e.getSource() == intFormaPago.getTxtNumeroTarjeta()) {
			EntradaTeclado.validarNumero(e);
		}*/
	}



	public JDialog getDialogDomicilio() {
		return dialogDomicilio;
	}

	public void setDialogDomicilio(JDialog dialogDomicilio) {
		this.dialogDomicilio = dialogDomicilio;
	}

	public IntDomicilio getIntDomicilio() {
		return intDomicilio;
	}

	public void setIntDomicilio(IntDomicilio intDomicilio) {
		this.intDomicilio = intDomicilio;
	}

	public static void addEscapeListenerWindowDialog(final JDialog windowDialog) {
		ActionListener escAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				windowDialog.dispose();
			}
		};
		windowDialog.getRootPane().registerKeyboardAction(escAction,KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

}
