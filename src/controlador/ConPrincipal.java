package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.IntPrincipal;
import main.Principal;
import modelo.ModPrincipal;
import util.G;

public class ConPrincipal implements ActionListener,FocusListener,KeyListener {

	private IntPrincipal intPrincipal;
	private ModPrincipal modPrincipal;
	private Principal principal;

	public ConPrincipal(IntPrincipal intPrincipal, ModPrincipal modPrincipal, Principal principal) {
		super();
		this.intPrincipal = intPrincipal;
		this.modPrincipal = modPrincipal;
		this.principal = principal;

		this.intPrincipal.getBtnFactura().addActionListener(this);
		this.intPrincipal.getBtnPagos().addActionListener(this);
		this.intPrincipal.getBtnReporte().addActionListener(this);
		//this.intPrincipal.getBtnClave().addActionListener(this);
		this.intPrincipal.getBtnConfiguracion().addActionListener(this);
		this.intPrincipal.getBtnDevolución().addActionListener(this);
		//this.intPrincipal.getBtnRetiro().addActionListener(this);
		this.intPrincipal.getBtnSalida().addActionListener(this);

		this.intPrincipal.getBtnFactura().addKeyListener(this);
		this.intPrincipal.getBtnPagos().addKeyListener(this);
		this.intPrincipal.getBtnReporte().addKeyListener(this);
		//this.intPrincipal.getBtnClave().addKeyListener(this);
		this.intPrincipal.getBtnConfiguracion().addKeyListener(this);
		this.intPrincipal.getBtnDevolución().addKeyListener(this);
		//this.intPrincipal.getBtnRetiro().addKeyListener(this);
		this.intPrincipal.getBtnSalida().addKeyListener(this);

		System.out.println("ConPrincipal.ConPrincipal() SE CREO EN LA CONPRINCIPAL");
		modPrincipal.iniciarPrincipal(principal);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource()==intPrincipal.getBtnFactura()){
			// Cuando el boton factura tiene el foco y se le puede dar enter
			//TODO colocar una de las F12 para iniciar una nueva factura
			//modPrincipal.iniciarPrincipal(principal);

			principal.setContentPane(modPrincipal.getConFactura().getIntFactura());
			principal.setVisible(true);
			
			try {
				modPrincipal.getConFactura().getModFactura().BorrarTablaCapturaArticulo(
						modPrincipal.getConFactura().getTablaFacturaModelo());
				G.getInstance().idPreFacturaActual=0;
				modPrincipal.getConFactura().getIntPendientes().getListModel().removeAllElements();
				modPrincipal.getConFactura().setAlFacturasPendientes(modPrincipal.getConFactura().getMaestroDB().facturasPendientes());
				String nombreDomicilio = "";
				if(modPrincipal.getConFactura().getAlFacturasPendientes() != null) {
					for (int j = 0; j < modPrincipal.getConFactura().getAlFacturasPendientes().size(); j++) {
						nombreDomicilio = modPrincipal.getConFactura().getMaestroDB().consultarDomicilio(
								modPrincipal.getConFactura().getAlFacturasPendientes().get(j).toString());
						if(nombreDomicilio == null)nombreDomicilio = "";
						modPrincipal.getConFactura().getIntPendientes().getListModel().addElement(
								""+modPrincipal.getConFactura().getAlFacturasPendientes().get(j).toString()+"-"+nombreDomicilio);
					}
				}
				modPrincipal.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==intPrincipal.getBtnPagos()){
			System.out.println("ConPrincipal.keyTyped() getBtnPagos");
		}else if(e.getSource()==intPrincipal.getBtnReporte()){
			System.out.println("ConPrincipal.keyTyped() getBtnReporte");
		}else if(e.getSource()==intPrincipal.getBtnClave()){
			System.out.println("ConPrincipal.keyTyped() getBtnClave");
		}else if(e.getSource()==intPrincipal.getBtnConfiguracion()){
			System.out.println("ConPrincipal.keyTyped() getBtnConfiguracion");
		}else if(e.getSource()==intPrincipal.getBtnDevolución()) {
			System.out.println("ConPrincipal.keyTyped() getBtnAutorizacion");
		}else if(e.getSource()==intPrincipal.getBtnRetiro()){
			System.out.println("ConPrincipal.keyTyped() getBtnRetiro");
		}else if(e.getSource()==intPrincipal.getBtnSalida()){
			System.out.println("ConPrincipal.keyTyped() getBtnSalida");
			System.exit(0);
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==intPrincipal.getBtnFactura()){
			System.out.println("ConPrincipal.actionPerformed() getBtnFactura");

			principal.setContentPane(modPrincipal.getConFactura().getIntFactura());
			principal.setVisible(true);
			
			try {
				modPrincipal.getConFactura().getModFactura().BorrarTablaCapturaArticulo(
						modPrincipal.getConFactura().getTablaFacturaModelo());
				G.getInstance().idPreFacturaActual=0;
				modPrincipal.getConFactura().getIntPendientes().getListModel().removeAllElements();
				modPrincipal.getConFactura().setAlFacturasPendientes(
						modPrincipal.getConFactura().getMaestroDB().facturasPendientes());
				String nombreDomicilio = "";
				if(modPrincipal.getConFactura().getAlFacturasPendientes() != null) {
					for (int j = 0; j < modPrincipal.getConFactura().getAlFacturasPendientes().size(); j++) {
						nombreDomicilio = modPrincipal.getConFactura().getMaestroDB().consultarDomicilio(
								modPrincipal.getConFactura().getAlFacturasPendientes().get(j).toString());
						if(nombreDomicilio == null)nombreDomicilio = "";
						modPrincipal.getConFactura().getIntPendientes().getListModel().addElement(
								""+modPrincipal.getConFactura().getAlFacturasPendientes().get(j).toString()+"-"+nombreDomicilio);
					}
				}
				modPrincipal.getConFactura().getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==intPrincipal.getBtnPagos()){
			System.out.println("ConPrincipal actionPerformed getBtnPagos");

			modPrincipal.pagos(principal);

		}else if(e.getSource()==intPrincipal.getBtnReporte()){
			System.out.println("ConPrincipal actionPerformed getBtnReporte");
			modPrincipal.reportes(principal);
		}else if(e.getSource()==intPrincipal.getBtnClave()){
			System.out.println("ConPrincipal actionPerformed getBtnClave");
		}else if(e.getSource()==intPrincipal.getBtnConfiguracion()){
			System.out.println("ConPrincipal actionPerformed getBtnConfiguracion");

			//Login frame = new Login();
			//frame.setVisible(true);
			
			//JOptionPane.showMessageDialog(null, "Debe tener autorización o perfil de administrador", "Autorización cambio de configuración", JOptionPane.ERROR_MESSAGE);
			
			modPrincipal.iniciarConfiguracionBotonesRapidos(principal);

		}else if(e.getSource()==intPrincipal.getBtnDevolución()) {
			System.out.println("ConPrincipal.actionPerformed() getBtnDevolución");
//*********************************************************************************************************************************************************
			modPrincipal.inciarDevoluciones(principal);
//*********************************************************************************************************************************************************
		}else if(e.getSource()==intPrincipal.getBtnRetiro()){
			System.out.println("ConPrincipal actionPerformed getBtnRetiro");
		}else if(e.getSource()==intPrincipal.getBtnSalida()){
			System.out.println("ConPrincipal actionPerformed getBtnSalida");
			System.exit(0);
		}
	}

	public IntPrincipal getIntPrincipal() {
		return intPrincipal;
	}

	public void setIntPrincipal(IntPrincipal intPrincipal) {
		this.intPrincipal = intPrincipal;
	}

	public ModPrincipal getModPrincipal() {
		return modPrincipal;
	}

	public void setModPrincipal(ModPrincipal modPrincipal) {
		this.modPrincipal = modPrincipal;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

}
