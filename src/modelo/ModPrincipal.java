package modelo;

import controlador.ConConfiguracion;
import controlador.ConDevoluciones;
import controlador.ConFactura;
import controlador.ConPagos;
import controlador.ConPrincipal;
import controlador.ConReportes;
import database.MaestroDB;
import gui.IntDevoluciones;
import gui.IntKeyNumeros;
import gui.IntPagos;
import gui.IntReportes;
import guiconfiguracion.IntConfiguracionBotonesRapidos;
import guiconfiguracion.IntExplicacion;
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
import util.TablaFacturaModelo;
import util.TablaPagosModelo;
import util.TableDevolucionesModelo;

public class ModPrincipal {

	private ConPrincipal conPrincipal;
	private MaestroDB maestroDB;
	private ConFactura conFactura;

	public ModPrincipal() {
		super();
		maestroDB = new MaestroDB();
		maestroDB.conectarDB();
	}

	public void iniciarPrincipal(Principal principal) {
		// Declaramos las clases
		TablaFacturaModelo tablaFacturaModelo;
		IntBotonesRapidos1 intBotonesRapidos1;
		IntBotonesRapidos2 intBotonesRapidos2;
		IntBotonesRapidos3 intBotonesRapidos3;
		IntCapturaArticulo intCapturaArticulo;
		IntContenedor intContenedor;
		IntListaCompra intListaCompra;
		IntPedientes intPendientes;
		IntKeyNumeros intKeyNumeros;
		IntNumeros intNumeros;

		ModFactura modFactura;
		IntFactura intFactura;
		//ConFactura conFactura;

		// Instanciamos las clases (crear los objetos de las clases)
		tablaFacturaModelo = new TablaFacturaModelo();
		intBotonesRapidos1 = new IntBotonesRapidos1();
		intBotonesRapidos2 = new IntBotonesRapidos2();
		intBotonesRapidos3 = new IntBotonesRapidos3();
		intCapturaArticulo = new IntCapturaArticulo();
		intListaCompra = new IntListaCompra(tablaFacturaModelo);

		intPendientes = new IntPedientes();
		intKeyNumeros = new IntKeyNumeros();
		intNumeros = new IntNumeros(intPendientes,intKeyNumeros);
		intContenedor = new IntContenedor(intBotonesRapidos1,
				intBotonesRapidos2,
				intBotonesRapidos3,
				intListaCompra);
		modFactura = new ModFactura(maestroDB);
		intFactura = new IntFactura(intContenedor, intCapturaArticulo, intNumeros);

		conFactura = new ConFactura(intFactura, intBotonesRapidos1,
				intBotonesRapidos2, intBotonesRapidos3,
				intCapturaArticulo, intContenedor, intListaCompra,
				intPendientes, intKeyNumeros,
				intNumeros, modFactura, tablaFacturaModelo, principal,this, maestroDB);

		// Establecemos las relaciones entre clases
		intBotonesRapidos1.setConFactura(conFactura);
		intBotonesRapidos2.setConFactura(conFactura);
		intBotonesRapidos3.setConFactura(conFactura);
		intCapturaArticulo.setConFactura(conFactura);
		intContenedor.setConFactura(conFactura);
		intListaCompra.setConFactura(conFactura);
		intNumeros.setConFactura(conFactura);

		intFactura.setConFactura(conFactura);
		modFactura.setConFactura(conFactura);

		// Enviarle una instancia de cada clases al coordinador
		conFactura.setIntBotonesRapidos1(intBotonesRapidos1);
		conFactura.setIntBotonesRapidos2(intBotonesRapidos2);
		conFactura.setIntBotonesRapidos3(intBotonesRapidos3);
		conFactura.setIntCapturaArticulo(intCapturaArticulo);
		conFactura.setIntContenedor(intContenedor);
		conFactura.setIntListaCompra(intListaCompra);
		conFactura.setIntNumeros(intNumeros);

		conFactura.setIntFactura(intFactura);
		conFactura.setModFactura(modFactura);

		principal.setContentPane(intFactura);
		principal.setVisible(true);	
	}

	public void iniciarConfiguracionBotonesRapidos(Principal principal) {
		// Declaramos las clases
		IntBotonesRapidos1 intBotonesRapidos1;
		IntBotonesRapidos2 intBotonesRapidos2;
		IntBotonesRapidos3 intBotonesRapidos3;
		IntConfiguracionBotonesRapidos intConfiguracionBotonesRapidos;
		IntContenedor intContenedor;
		IntExplicacion intExplicacion;

		ModConfiguracion modConfiguracion;
		IntFactura intFactura;
		ConConfiguracion conConfiguracion;

		// Instanciamos las clases (crear los objetos de las clases)
		intBotonesRapidos1 = new IntBotonesRapidos1();
		intBotonesRapidos2 = new IntBotonesRapidos2();
		intBotonesRapidos3 = new IntBotonesRapidos3();
		intConfiguracionBotonesRapidos = new IntConfiguracionBotonesRapidos();
		intExplicacion = new IntExplicacion();
		intContenedor = new IntContenedor(intBotonesRapidos1,
				intBotonesRapidos2,
				intBotonesRapidos3,null);
		modConfiguracion = new ModConfiguracion();
		intFactura = new IntFactura(intContenedor,intConfiguracionBotonesRapidos, intExplicacion);
		conConfiguracion = new ConConfiguracion(intConfiguracionBotonesRapidos, intExplicacion, 
				intBotonesRapidos1, intBotonesRapidos2, intBotonesRapidos3, 
				intContenedor, modConfiguracion, intFactura,maestroDB);

		// Establecemos las relaciones entre clases
		intBotonesRapidos1.setConConfiguracion(conConfiguracion);
		intBotonesRapidos2.setConConfiguracion(conConfiguracion);
		intBotonesRapidos3.setConConfiguracion(conConfiguracion);
		intConfiguracionBotonesRapidos.setConConfiguracion(conConfiguracion);
		intExplicacion.setConConfiguracion(conConfiguracion);

		// Enviarle una instancia de cada clases al coordinador
		intConfiguracionBotonesRapidos.setConConfiguracion(conConfiguracion);
		intExplicacion.setConConfiguracion(conConfiguracion);
		intBotonesRapidos1.setConConfiguracion(conConfiguracion);
		intBotonesRapidos2.setConConfiguracion(conConfiguracion);
		intBotonesRapidos3.setConConfiguracion(conConfiguracion);
		modConfiguracion.setConConfiguracion(conConfiguracion);
		//intContenedor, intFactura

		principal.setContentPane(intFactura);
		principal.setVisible(true);	
	}

	public void pagos(Principal principal) {
		// Creamos las clases
		TablaPagosModelo tablaPagosModelo;
		IntPagos intPagos;
		ModeloPagos modeloPagos;
		ConPagos conPagos;

		// Instanciamos las clases (crear los objetos de las clases)
		tablaPagosModelo = new TablaPagosModelo();
		intPagos = new IntPagos(tablaPagosModelo);
		modeloPagos = new ModeloPagos();
		conPagos = new ConPagos(intPagos,modeloPagos,principal, tablaPagosModelo,maestroDB);

		// Establecemos las relaciones entre clases
		intPagos.setConPagos(conPagos);
		modeloPagos.setConPagos(conPagos);

		// Enviarle una instancia de cada clases al coordinador
		conPagos.setIntPagos(intPagos);
		conPagos.setModeloPagos(modeloPagos);

		principal.setContentPane(intPagos);
		principal.setVisible(true);
	}	

	public void inciarDevoluciones(Principal principal) {
		// Declaramos las clases	
		TableDevolucionesModelo tableDevolucionesModelo;
		IntDevoluciones intDevoluciones;
		ModeloDevoluciones modeloDevoluciones;
		ConDevoluciones conDevoluciones;

		// Instanciamos las clases (crear los objetos de las clases)
		tableDevolucionesModelo = new TableDevolucionesModelo();
		intDevoluciones = new IntDevoluciones(tableDevolucionesModelo);
		modeloDevoluciones = new ModeloDevoluciones();
		conDevoluciones = new ConDevoluciones(intDevoluciones,modeloDevoluciones,principal,tableDevolucionesModelo,maestroDB);

		// Establecemos las relaciones entre clases
		intDevoluciones.setConDevoluciones(conDevoluciones);
		modeloDevoluciones.setConDevoluciones(conDevoluciones);

		// Enviarle una instancia de cada clases al coordinador
		conDevoluciones.setIntDevoluciones(intDevoluciones);
		conDevoluciones.setModeloDevoluciones(modeloDevoluciones);

		principal.setContentPane(intDevoluciones);
		principal.setVisible(true);	
	}

	public void reportes(Principal principal) {
		
		// Declaramos las clases	
		IntReportes intReportes;
		
		ModeloReportes modeloReportes;
		ConReportes conReportes;

		// Instanciamos las clases (crear los objetos de las clases)
		intReportes = new IntReportes();
		
		modeloReportes = new ModeloReportes();
		conReportes = new ConReportes(intReportes, modeloReportes, principal, maestroDB);

		// Establecemos las relaciones entre clases
		
		intReportes.setConReportes(conReportes);
		modeloReportes.setConReportes(conReportes);

		// Enviarle una instancia de cada clases al coordinador
		conReportes.setIntReportes(intReportes);
		conReportes.setModeloReportes(modeloReportes);
		
		principal.setContentPane(intReportes);
		principal.setVisible(true);	
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}

	public ConPrincipal getConPrincipal() {
		return conPrincipal;
	}

	public void setConPrincipal(ConPrincipal conPrincipal) {
		this.conPrincipal = conPrincipal;
	}


}
