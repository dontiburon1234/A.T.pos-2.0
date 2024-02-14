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

import clases.ConsultarBuscarConPrecio;
import controlador.ConFactura;
import database.MaestroDB;
import gui.IntKeyLetras;
import gui.IntKeyNumeros;
import main.Principal;
import util.FormatoNumero;

public class ModeloFacturaBuscar implements ActionListener,KeyListener {

	private ConFactura conFactura;

	private JDialog dialogBuscar;
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	//private ArrayList<ConsultarBuscar> posibles;
	private ArrayList<ConsultarBuscarConPrecio> aLConsultarArticuloConPrecio;
	private String articuloSeleccionado;
	private IntKeyNumeros intKeyNumeros = new IntKeyNumeros();
	private IntKeyLetras intKeyLetras = new IntKeyLetras();
	private JTextField txtBuscar = new JTextField();
	private MaestroDB maestroDB; // = new MaestroDB();

	public ModeloFacturaBuscar(ConFactura conFactura, MaestroDB maestroDB) {
		super();
		this.conFactura = conFactura;
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
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void buscarArticulo(Principal principal) {
		dialogBuscar = new JDialog(principal,"ATpos - Administraci\u00F3n Total del punto de venta - Buscar",Dialog.ModalityType.DOCUMENT_MODAL);
		Toolkit tamano = Toolkit.getDefaultToolkit();
		int ancho =  (int) ((tamano.getScreenSize().getWidth()-1048)/2);
		int alto = (int) ((tamano.getScreenSize().getHeight()-570)/2);
		//dialogBuscar.setBounds(ancho, alto, 541, 346);
		dialogBuscar.setBounds(ancho, alto, 1048, 570);
		ModeloFacturaBuscar.addEscapeListenerWindowDialog(dialogBuscar);

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
				articuloSeleccionado = list.getSelectedValue().substring(list.getSelectedValue().indexOf("-")+1, list.getSelectedValue().indexOf(" $"));
				for (int i = 0; i < aLConsultarArticuloConPrecio.size(); i++) {
					if(aLConsultarArticuloConPrecio.get(i).getNombre_largo().toString().equals(articuloSeleccionado)) {
						System.out.println(
								"ModFactura..valueChanged() articuloSeleccionado "+articuloSeleccionado+" i "+i+" codigo "+
										aLConsultarArticuloConPrecio.get(i).getCodigo().toString());
						conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText(aLConsultarArticuloConPrecio.get(i).getCodigo().toString());
						conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
					}
				}
				dialogBuscar.dispose();
			}
		});
		list.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));

		JPanel jpBuscar = new JPanel();
		jpBuscar.setLayout(new BorderLayout());
		jpBuscar.add(txtBuscar,BorderLayout.PAGE_START);
		jpBuscar.add(listScrollPane, BorderLayout.CENTER);

		Container container = dialogBuscar.getContentPane();
		container.setLayout(new BorderLayout(0,0));
		container.add(jpBuscar, BorderLayout.CENTER);
		container.add(intKeyNumeros,BorderLayout.LINE_END);
		container.add(intKeyLetras, BorderLayout.PAGE_END);
		dialogBuscar.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		if(jButton.getText().equals("Supr")) {
			//txtBuscar.setText("");
			int tamanoCampo = txtBuscar.getText().length();
			if(tamanoCampo!=0) {
				String textoCampo = txtBuscar.getText();
				textoCampo = textoCampo.substring(0, tamanoCampo-1);
				txtBuscar.setText(textoCampo);
			}
		}else if(jButton.getText().equals("Intro")) {
			System.out.println("ModFactura.actionPerformed() Intro "+jButton.getText());
			dialogBuscar.dispose();
		}else {
			if(jButton.getText().equals("Espacio")) {
				txtBuscar.setText(txtBuscar.getText()+" ");
			}else {
				txtBuscar.setText(txtBuscar.getText()+jButton.getText());
			}
			listModel.removeAllElements();
			if(txtBuscar.getText().length()>1) {
				aLConsultarArticuloConPrecio = maestroDB.consultarArticuloConPrecio(txtBuscar.getText());
				if(aLConsultarArticuloConPrecio != null) {
					for (int j = 0; j < aLConsultarArticuloConPrecio.size(); j++) {

						double precioBase = aLConsultarArticuloConPrecio.get(j).getPrecio_base();
						String sPrecioBase = FormatoNumero.formatoCero(precioBase);

						listModel.addElement(aLConsultarArticuloConPrecio.get(j).getCodigo()+"-"+aLConsultarArticuloConPrecio.get(j).getNombre_largo().toString()+" $"+sPrecioBase);
					}
				} 
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		listModel.removeAllElements();
		if(txtBuscar.getText().length()>1) {
			//posibles = maestroDB.consultarArticulo(txtBuscar.getText());
			aLConsultarArticuloConPrecio = maestroDB.consultarArticuloConPrecio(txtBuscar.getText());
			if(aLConsultarArticuloConPrecio != null) {
				for (int j = 0; j < aLConsultarArticuloConPrecio.size(); j++) {
					double precioBase = aLConsultarArticuloConPrecio.get(j).getPrecio_base();
					String sPrecioBase = FormatoNumero.formatoCero(precioBase);
					listModel.addElement(aLConsultarArticuloConPrecio.get(j).getCodigo()+"-"+aLConsultarArticuloConPrecio.get(j).getNombre_largo().toString()+" $"+sPrecioBase);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//TODO se quiere con la flecha bajar hasta seleccionar el producto deseado
		char cKey = e.getKeyChar();
		//System.out.println("ModFactura.keyPressed() 1 cKey "+cKey);

		if(cKey == KeyEvent.VK_DOWN) {
			//System.out.println("ModFactura.keyPressed() 2 "+cKey);
		}

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
