package modelo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Proveedor;
import controlador.ConPagos;
import database.MaestroDB;
import gui.IntKeyLetras;
import gui.IntKeyNumeros;
import main.Principal;
import util.FormatoNumero;

public class ModeloBuscarProveedor implements ActionListener,KeyListener {

	//private ConFactura conFactura;

	private JDialog dialogBuscarProveedor;
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private ArrayList<Proveedor> alProveedor;
	private String proveedorSeleccionado;
	private IntKeyNumeros intKeyNumeros = new IntKeyNumeros();
	private IntKeyLetras intKeyLetras = new IntKeyLetras();
	private JTextField txtBuscar = new JTextField();
	private MaestroDB maestroDB;
	private ConPagos conPagos;
	private ModeloFacturaFormaPago modeloFacturaFormaPago;
	private String medioLLegada = "";

	/*ModeloDomicilio modeloDomicilio = new ModeloDomicilio(principal, this, modFactura, maestroDB);
	modeloDomicilio.registrarDomicilio(principal);*/
	
	public ModeloBuscarProveedor(ConPagos conPagos,MaestroDB maestroDB) {
		super();
		this.maestroDB = maestroDB;
		this.conPagos = conPagos;

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
		this.intKeyLetras.getBtnEspacio().addActionListener(this);
		this.intKeyLetras.getBtnPunto().addActionListener(this);
		this.intKeyLetras.getBtnQuion().addActionListener(this);
		this.intKeyLetras.getBtnPor().addActionListener(this);

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
		
		medioLLegada = "conPagos";
	}

	/*ModeloDomicilio modeloDomicilio = new ModeloDomicilio(principal, this, modFactura, maestroDB);
	modeloDomicilio.registrarDomicilio(principal);
*/
	
	// para usar en la busqueda del proveedor para generarle una remisión que le devueve la mercancía.
	public ModeloBuscarProveedor(ModeloFacturaFormaPago modeloFacturaFormaPago, MaestroDB maestroDB) {
		super();
		this.maestroDB = maestroDB;
		this.modeloFacturaFormaPago = modeloFacturaFormaPago;

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
		this.intKeyLetras.getBtnEspacio().addActionListener(this);
		this.intKeyLetras.getBtnPunto().addActionListener(this);
		this.intKeyLetras.getBtnQuion().addActionListener(this);
		this.intKeyLetras.getBtnPor().addActionListener(this);

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
		
		medioLLegada = "modeloFacturaFormaPago";
	}
	
	
	public void buscarProveedor(Principal principal) {
		dialogBuscarProveedor = new JDialog(principal,"ATpos - Administraci\u00F3n Total del punto de venta - Buscar Proveedor",Dialog.ModalityType.DOCUMENT_MODAL);
		Toolkit tamano = Toolkit.getDefaultToolkit();
		int ancho =  (int) ((tamano.getScreenSize().getWidth()-900)/2);
		int alto = (int) ((tamano.getScreenSize().getHeight()-570)/2);
		dialogBuscarProveedor.setBounds(ancho, alto, 900, 570);
		ModeloBuscarProveedor.addEscapeListenerWindowDialog(dialogBuscarProveedor);

		txtBuscar.setText("");
		txtBuscar.setFont(new Font("Tahoma",Font.PLAIN,25));
		txtBuscar.addKeyListener(this);

		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setFont(new Font("Tahoma",Font.PLAIN,25));
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				proveedorSeleccionado = list.getSelectedValue();
				String buscado = "";
				
				if(medioLLegada.equals("conPagos")) {
					for (int i = 0; i < alProveedor.size(); i++) {
						buscado = alProveedor.get(i).getNombre()+" "+alProveedor.get(i).getNombre_comercial();
						if((buscado.equals(proveedorSeleccionado))) {
							conPagos.getIntPagos().getIntPagosEncabezado().getTxtDocumentoProveedor().setText(""+alProveedor.get(i).getDocumento());
							conPagos.getIntPagos().getIntPagosEncabezado().getTxtNombreProveedor().setText(alProveedor.get(i).getNombre());
							conPagos.getIntPagos().getIntPagosEncabezado().getTxtNombreComercial().setText(alProveedor.get(i).getNombre_comercial());
						}
					}
				}else {
					System.out.println("ModeloBuscarProveedor.buscarProveedor ENVIA LOS DATOS DEL PROVEDDOR A LA BASE DE DATOS medioLLegada "+medioLLegada);
					
					
					for (int i = 0; i < alProveedor.size(); i++) {
						buscado = alProveedor.get(i).getNombre()+" "+alProveedor.get(i).getNombre_comercial();
						if((buscado.equals(proveedorSeleccionado))) {
							
							String sDocumento = FormatoNumero.formatoCero(alProveedor.get(i).getDocumento());							
							modeloFacturaFormaPago.getIntFormaPago().getTxtDocumento().setText(sDocumento);//(""+alProveedor.get(i).getDocumento());
							modeloFacturaFormaPago.getIntFormaPago().getTxtNombre().setText(alProveedor.get(i).getNombre());
						}
					}
				}

				dialogBuscarProveedor.dispose();
			}
		});
		list.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));

		JPanel jpBuscar = new JPanel();
		jpBuscar.setLayout(new BorderLayout());
		jpBuscar.add(txtBuscar,BorderLayout.PAGE_START);
		jpBuscar.add(listScrollPane, BorderLayout.CENTER);

		Container container = dialogBuscarProveedor.getContentPane();
		container.setLayout(new BorderLayout(0,0));
		container.add(jpBuscar, BorderLayout.CENTER);
		container.add(intKeyNumeros,BorderLayout.LINE_END);
		container.add(intKeyLetras, BorderLayout.PAGE_END);
		dialogBuscarProveedor.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		if(jButton.getText().equals("Supr")) {
			txtBuscar.setText("");
		}else if(jButton.getText().equals("Intro")) {
			dialogBuscarProveedor.dispose();
		}else {
			//TODO buscar por cliente y apellido en una sola linea
			String sTxtBuscar = "";
			if(jButton.getText().equals("Espacio")) {
				sTxtBuscar = txtBuscar.getText()+" ";
			}else {
				sTxtBuscar = txtBuscar.getText()+jButton.getText();
			}
			txtBuscar.setText(sTxtBuscar);
			listModel.removeAllElements();
			if(txtBuscar.getText().length()>1) {
				alProveedor = maestroDB.consultarProveedorPagos(sTxtBuscar);
				//posibles = maestroDB.consultarClienteDomicilio(sTxtBuscar);//(txtBuscar.getText());
				if(alProveedor != null) {
					for (int j = 0; j < alProveedor.size(); j++) {
						listModel.addElement(alProveedor.get(j).getNombre()+" "+alProveedor.get(j).getNombre_comercial());
					}
				} 
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		listModel.removeAllElements();
		if(txtBuscar.getText().length()>1) {
			alProveedor = maestroDB.consultarProveedorPagos(txtBuscar.getText());
			if(alProveedor != null) {
				for (int j = 0; j < alProveedor.size(); j++) {
					listModel.addElement(alProveedor.get(j).getNombre()+" "+alProveedor.get(j).getNombre_comercial());
				}
			} 
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {
		//TODO se quiere con la flecha bajar hasta seleccionar el producto deseado
		/*char cKey = e.getKeyChar();
		System.out.println("ModeloBuscarCliente.keyTyped() 1 "+cKey);

		if(cKey == KeyEvent.VK_DOWN) {
			System.out.println("ModeloBuscarCliente.keyTyped() 2 VK_DOWN "+cKey);
		}*/
	}

	public static void addEscapeListenerWindowDialog(final JDialog windowDialog) {
		ActionListener escAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				windowDialog.dispose();
			}
		};
		windowDialog.getRootPane().registerKeyboardAction(escAction,KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
}
