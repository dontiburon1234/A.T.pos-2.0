package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.gson.Gson;

import clases.ArticuloFactura;
import clases.BaseIva;
import clases.Cliente;
import clases.TablaArticulos;
import clases.Tercero;
import controlador.ConFactura;
import database.MaestroDB;
import util.FormatoNumero;
import util.G;
import util.TablaFacturaModelo;

public class ModFactura {

	private ConFactura conFactura;
	private MaestroDB maestroDB;

	private int item = 0;
	private int idPreFacturaActual = 0;
	private double valorTotal = 0.0;

	public ModFactura(MaestroDB maestroDB) {
		super();
		this.maestroDB = maestroDB; 
	}

	public ArticuloFactura procedimientoSaleDeCodigoArticulo(int listaPrecioClienteTercero,int idVendedor ) {
		ArticuloFactura articuloFactura = new ArticuloFactura();
		String autorizaCambioPrecio = maestroDB.cargarUnParametro("autorizaCambioPrecio");
		boolean bAutorizaCambioPrecio = Boolean.valueOf(autorizaCambioPrecio);

		double cantidad;
		if(!conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().getText().equals("")) { // el campo codigoArticulo esta lleno
			String sCantidad = conFactura.getIntCapturaArticulo().getTxtCantidad().getText();
			System.out.println("ModFactura.procedimientoSaleDeCodigoArticulo() sCantidad "+sCantidad);

			if(sCantidad.equals("")) sCantidad = "1";
			cantidad = Double.valueOf(sCantidad);
			if(cantidad == 0) sCantidad = "1";

			articuloFactura = maestroDB.buscarArticulo(conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().getText().toUpperCase(),
					listaPrecioClienteTercero,idVendedor,sCantidad);

			Gson gson = new Gson();
			String sGsonArticuloFactura = gson.toJson(articuloFactura);
			System.out.println("ModFactura.procedimientoSaleDeCodigoArticulo() 48 sGsonArticuloFactura GSON "+sGsonArticuloFactura+" sCantidad "+sCantidad+"X");

			if(articuloFactura != null) {
				if(articuloFactura.getId_articulo() != 0) { // el producto existe
					if(articuloFactura.getValorUnitario()>0) { // tiene precio se le agrega a la lista de precios
						// consulta si el cajero puede modificar el precio
						if(!bAutorizaCambioPrecio) { // NO, puede cambiar precio
							ItemEnListaDeCompras(articuloFactura, conFactura.getTablaFacturaModelo());
							BorrarCapturaArticulo();
							//conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
							conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
							/*}else { // SI, el cajero está autorizado para realizar cambio de precio
							System.out.println("ModFactura.procedimientoSaleDeCodigoArticulo() SI, puede cambiar precio ");
							conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText(articuloFactura.getNombreArticulo());
							conFactura.getIntCapturaArticulo().getTxtMedida().setText(articuloFactura.getNombreUnidadMedida());
							conFactura.getIntCapturaArticulo().getTxtValorUnitario().setFocusable(true);
							conFactura.getIntCapturaArticulo().getTxtValorUnitario().requestFocus();*/
						}else {

							if(conFactura.getIntCapturaArticulo().getTxtCantidad().getText().equals("")||cantidad == 0) {
								conFactura.getIntCapturaArticulo().getTxtCantidad().setText("1");
							}

							conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText(articuloFactura.getNombreArticulo());
							conFactura.getIntCapturaArticulo().getTxtMedida().setText(articuloFactura.getNombreUnidadMedida());
						}
					}else { //su precio es cero
						System.out.println("ModFactura.procedimientoSaleDeCodigoArticulo() EL VENDEDOR NO ESTA AUTORIZADO PARA CAMBIAR PRECIO PERO ESTE TIENE PRECIO CERO");
						conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText(articuloFactura.getNombreArticulo());
						conFactura.getIntCapturaArticulo().getTxtMedida().setText(articuloFactura.getNombreUnidadMedida());
						/*conFactura.getIntCapturaArticulo().getTxtValorUnitario().setFocusable(true);
						conFactura.getIntCapturaArticulo().getTxtValorUnitario().requestFocus();*/
					}
				}
			}else {// el producto no existe
				conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
				JOptionPane.showMessageDialog(null, "Código del artículo no existe!");
			}

			/*else { 
				conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
				articuloFactura = null;
				JOptionPane.showMessageDialog(null, "Código del artículo no existe!");
			}*/

		}else { // si sale de codigoArticulo y está vacio no haga nada
			//System.out.println("ModFactura.procedimientoSaleDeCodigoArticulo()EL CAMPO codigoArticulo está vacio DEJELO IR");
		}
		return articuloFactura;
	}

	public void procedimientoSaleDeValorUnitario(ArticuloFactura articuloFactura,int listaPrecioClienteTercero,int codigoVendedor) {

		/*Gson gson = new Gson();
		String sGsonArticuloFactura = gson.toJson(articuloFactura);
		System.out.println("ModFactura.procedimientoSaleDeValorUnitario() 82  sGsonArticuloFactura GSON "+sGsonArticuloFactura+" sCantidad ");*/

		String sValorUnitario = conFactura.getIntCapturaArticulo().getTxtValorUnitario().getText();
		if(!sValorUnitario.equals("")) {
			sValorUnitario = sValorUnitario.replace(",", "");
			double valorUnitario = Double.valueOf(sValorUnitario);
			double cantidad = 0;
			String sCantidad = conFactura.getIntCapturaArticulo().getTxtCantidad().getText();
			sCantidad = sCantidad.replace(",", "");
			if(sCantidad.equals("")) {
				cantidad = 1;
			}else{
				cantidad = Double.valueOf(sCantidad);
			}

			System.err.println("ModFactura.procedimientoSaleDeValorUnitario() cantidad "+cantidad);
			String codigoArticulo = conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().getText();

			articuloFactura = maestroDB.buscarArticulo(codigoArticulo, listaPrecioClienteTercero, codigoVendedor, sCantidad);

			//articuloFactura.setCantidad(cantidad);
			if(valorUnitario>0) {
				System.out.println("ModFactura.procedimientoSaleDeValorUnitario() valorUnitario "+valorUnitario);
				articuloFactura.setValorUnitario(valorUnitario);

				//liquida el valor del iva de acuerdo al precio que ha colocado el cajero
				ArrayList<BaseIva> alBaseIva = maestroDB.traeTablaIva();
				for (int i = 0; i < alBaseIva.size(); i++) {
					if(alBaseIva.get(i).getIdBaseIva() == +articuloFactura.getId_base_iva()) {
						articuloFactura.setIva((alBaseIva.get(i).getBaseIva()/100)*(articuloFactura.getValorUnitario()/(1+(alBaseIva.get(i).getBaseIva()/100))));
					}
				}

				ItemEnListaDeCompras(articuloFactura, conFactura.getTablaFacturaModelo());
				BorrarCapturaArticulo();
				conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
				conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();
			}

		}else {
			//si valor unitario es vacio no haga nada...por el momento
			//JOptionPane.showMessageDialog(null, "Debe ingresar un valor para el artículo");
		}
	}

	public int procedimientoSaleDeDocumentoCliente() {
		int listaPrecioClienteTercero = 0;
		int id_caja = G.getInstance().licenciaGlobal.getIdCaja();

		String numeroDocumentoCliente = "";
		if (!conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().getText().equals("")) { //txtCampoGained
			numeroDocumentoCliente = conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().getText().replace(",",""); // txtCampoGained
			Tercero tercero = new Tercero();
			Cliente cliente = new Cliente();
			tercero = maestroDB.consultarTercero(numeroDocumentoCliente);
			if (tercero == null) {
				cliente = maestroDB.consultarCliente(numeroDocumentoCliente);
				if(cliente == null) {
					JOptionPane.showMessageDialog(null, "Documento equivocado del cliente.");
					conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setText("");
					conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText("");
					listaPrecioClienteTercero = 0;
					conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().requestFocus();
				}else {
					conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
					conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setFocusable(false);
					listaPrecioClienteTercero = cliente.getId_lista_precio();
					int iNumeroDocumentoCliente = Integer.valueOf(numeroDocumentoCliente);
					if(G.getInstance().idPreFacturaActual == 0) {
						//TODO crea la prefactura y agraga el cliente en la tabla preFactura
						conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
						conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setFocusable(false);
						listaPrecioClienteTercero = cliente.getId_lista_precio();
						iNumeroDocumentoCliente = Integer.valueOf(numeroDocumentoCliente);
						if(G.getInstance().idPreFacturaActual == 0) {
							//TODO 1 - crea la prefactura y agrega el id_cliente en la tabla preFactura
							//TODO 2 - el cliente debe existir en caso de que no exista lo puede crear por docimicilio si es tercero debe ser en la web
							double numero = 0;
							int auxiliar = 0;
							int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
							String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
							java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input );
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

							int id_cliente = cliente.getId();
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

							G.getInstance().idPreFacturaActual = idResultadoPreFactura;
							System.out.println("ConFactura.focusGained() idResultadoPreFactura "+idResultadoPreFactura);
						}else {
							// agrega el id_cliente a la prefactura actual en la tabla preFactura
							maestroDB.agregarClienteTercero(iNumeroDocumentoCliente,G.getInstance().idPreFacturaActual,id_caja);
						}
					}else {
						// agrega el cliente a la prefactura actual en la tabla preFactura
						maestroDB.agregarClienteTercero(cliente.getId(),G.getInstance().idPreFacturaActual,id_caja);
					}
				}
			}else {
				conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText(tercero.getNombre()+" "+tercero.getApellido());
				conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setFocusable(false);
				listaPrecioClienteTercero = tercero.getId_lista_precio();
				//int iNumeroDocumentoCliente = Integer.valueOf(numeroDocumentoCliente);
				if(G.getInstance().idPreFacturaActual == 0) {
					//TODO 1 - crea la prefactura y agraga el id_cliente en la tabla preFactura
					//TODO 2 - el cliente debe existir en caso de que no exista lo puede crear por docimicilio si es tercero debe ser en la web

					double numero = 0;
					int auxiliar = 0;
					int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
					String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
					java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input );
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

					int id_cliente = tercero.getId();
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
					System.out.println("ConFactura.focusGained() idResultadoPreFactura "+idResultadoPreFactura);
					G.getInstance().idPreFacturaActual = idResultadoPreFactura;

				}else {
					// agrega el id_cliente a la prefactura actual en la tabla preFactura
					maestroDB.agregarClienteTercero(tercero.getId(),G.getInstance().idPreFacturaActual,id_caja);
				}
			}
		}
		G.getInstance().documentoClienteTercero = numeroDocumentoCliente;

		System.out.println("ModFactura.procedimientoSaleDeDocumentoCliente() ListaDePrecio " +
				listaPrecioClienteTercero+" numeroDocumentoCliente "+numeroDocumentoCliente+
				" G.getInstance().idPreFacturaActual " +G.getInstance().idPreFacturaActual);

		return listaPrecioClienteTercero;
	}

	public int procedimientoSaleDeCodigoVendedor() {
		int idVendedor = 0;
		String nombreVendedor = "";
		if (! conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText().equals("")) {
			idVendedor = Integer.parseInt(conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText());
			nombreVendedor = maestroDB.consultarNombreVendedor(idVendedor);
			if (nombreVendedor.equals("bad")) {
				conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().setText("");
				conFactura.getIntCapturaArticulo().getTxtNombreVendedor().setText("");
				JOptionPane.showMessageDialog(null, "C\u00F3digo equivocado del vendedor.");
				conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().requestFocus();
			} else {
				conFactura.getIntCapturaArticulo().getTxtNombreVendedor().setText(nombreVendedor);
			}
		}
		return idVendedor;
	}

	//Borra solo lo correspondiente al artículo que se desea facturar en caso de enviar un error por el código 
	public void BorrarCapturaArticulo() {

		conFactura.getIntCapturaArticulo().getTxtValorUnitario().setFocusable(false);

		conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
		conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText("");
		conFactura.getIntCapturaArticulo().getTxtUnidades().setText("");
		conFactura.getIntCapturaArticulo().getTxtCantidad().setText("");
		conFactura.getIntCapturaArticulo().getTxtMedida().setText("");
		conFactura.getIntCapturaArticulo().getTxtValorUnitario().setText("");
		conFactura.getIntCapturaArticulo().getTxtValorTotal().setText("");
		conFactura.getIntCapturaArticulo().getTxtTotalFactura().setText("");
	}

	//Borra todo en pantalla para poder cargar una factura en espera o preFactura
	public void BorrarTablaCapturaArticulo(TablaFacturaModelo tablaFacturaModelo) {
		conFactura.getIntListaCompra().getTxtSubTotal().setText("");
		conFactura.getIntListaCompra().getTxtImpuesto().setText("");
		conFactura.getIntListaCompra().getTxtDescuento().setText("");
		conFactura.getIntListaCompra().getTxtTotalfactura().setText("");
		int cantidadRow = conFactura.getIntListaCompra().getTable().getRowCount();
		if(cantidadRow>0) {
			for (int i = cantidadRow; i >= 0; i--) {
				//System.out.println("ModFactura.BorrarTablaCapturaArticulo() cantidadRow "+cantidadRow+" i "+i);
				tablaFacturaModelo.borraArticulo(i-1); 
			}
		}
		conFactura.getIntCapturaArticulo().getTxtDocumentoCliente().setText("");
		conFactura.getIntCapturaArticulo().getTxtNombreCliente().setText("");
		conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().setText("");
		conFactura.getIntCapturaArticulo().getTxtNombreVendedor().setText("");
		conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
		conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText("");
		conFactura.getIntCapturaArticulo().getTxtUnidades().setText("");
		conFactura.getIntCapturaArticulo().getTxtCantidad().setText("");
		conFactura.getIntCapturaArticulo().getTxtMedida().setText("");
		conFactura.getIntCapturaArticulo().getTxtValorUnitario().setText("");
		conFactura.getIntCapturaArticulo().getTxtValorTotal().setText("");
		conFactura.getIntCapturaArticulo().getTxtTotalFactura().setText("");
		
		//operacionesPuerto.
	}

	public void borrarArticuloDB(int borraId_PreFactura, int rowDelete, int id_caja) {

		System.err.println("ModFactura.borrarArticuloDB() borraId_PreFactura "+borraId_PreFactura+" rowDelete "+rowDelete+" id_caja "+id_caja);


		if(rowDelete!=-1) maestroDB.borraItemPreFactura(borraId_PreFactura, rowDelete, id_caja);
	}

	public void ItemEnListaDeCompras(ArticuloFactura articuloFactura,TablaFacturaModelo tablaFacturaModelo) {
		//System.out.println("ModFactura.ItemEnListaDeCompras() 309 articuloFactura.getId_articulo() "+articuloFactura.getId_articulo()+" cantidad "+articuloFactura.getCantidad());
		/*Gson gson = new Gson();
		String sGsonarticuloFactura = gson.toJson(articuloFactura);
		System.err.println("ModFactura.ItemEnListaDeCompras() 312 sGsonarticuloFactura "+sGsonarticuloFactura);*/

		if(articuloFactura.getId_articulo() != 0) {
			int vendedor = 0;
			if(articuloFactura.getCodigoVendedor() == 0) {
				vendedor = 1;
			}else{
				vendedor = articuloFactura.getCodigoVendedor();
			}
			String codigo = articuloFactura.getCodigoArticulo();
			String articulo = articuloFactura.getNombreArticulo();
			int unidades = 0;
			double cantidad = articuloFactura.getCantidad();
			double valor = articuloFactura.getValorUnitario();
			double descuento = 0.0;
			double total = cantidad * valor; // Double.parseDouble(intCapturaArticulo.getTxtValortotal().getText().toString());

			/*System.out.println("ModFactura.ItemEnListaDeCompras()total "+total+" cantidad "+cantidad+" valor"+valor+" TOTAL TOTAL TOTAL TOTAL TOTAL TOTAL ");
			System.out.println("ModFactura.ItemEnListaDeCompras() V " + vendedor + " C " + codigo + " A " + articulo + " U "+ unidades + " CANT " + cantidad + " V " + valor + " D " + descuento + " T " + total+" CANTIDAD CANTIDAD CANTIDAD CANTIDAD ");
			System.out.println("ModFactura.ItemEnListaDeCompras() articuloFactura.getCantidad() "+articuloFactura.getCantidad());*/

			TablaArticulos tablaArticulos = new TablaArticulos(vendedor, codigo, articulo, unidades, cantidad,valor, descuento, total);
			tablaFacturaModelo.adicionaArticulo(tablaArticulos);

			int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
			int id_vendedor = 0;
			String id_usuario_vendedor = "";
			double valor_iva = 0;
			double valor_descuento = 0;

			//agrego a la tabla preFactura el encabezado de la factura
			if(G.getInstance().idPreFacturaActual == 0) {

				idPreFacturaActual = maestroDB.maximoPreFactura()+1;
				G.getInstance().idPreFacturaActual = idPreFacturaActual;
				int numero = 0;
				int auxiliar = 0;
				int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();

				String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
				java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input ) ;
				Date fecha = ts;

				int id_cajero = G.getInstance().getIdCajero;
				if(conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText().equals("")) {
					id_vendedor = 1;
				}else {
					id_vendedor = Integer.parseInt(conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText()); 
				}
				id_usuario_vendedor = maestroDB.consultarIdVendedor(id_vendedor); //conFactura.getIntCapturaArticulo().getTxtNombreVendedor().getText();

				int id_cliente = 0; //TODO definir en pantalla como se captura el vendedor
				int id_agente_externo = 0; 
				double valor_prefactura = valorTotal;
				valor_descuento = 0; //TODO cambiar cuando se defina la metodología de descuento o corrección de precio.


				item = 0;


				valor_iva = articuloFactura.getIva() ;

				String comentario = ""; //TODO falta agregar el campo comentario en la interfas de pago
				String estado = "proceso";

				int idResultadoPreFactura = maestroDB.agregarPreFactura(id_caja,numero,  auxiliar, id_almacen, fecha, id_cajero, id_vendedor,  
						id_usuario_vendedor, id_cliente,  id_agente_externo,  valor_prefactura, valor_descuento, valor_iva,  
						comentario,  estado);

				G.getInstance().idPreFacturaActual = idResultadoPreFactura;

				conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().requestFocus();

			}else {
				idPreFacturaActual = G.getInstance().idPreFacturaActual;
				item = maestroDB.traerItem(id_caja, idPreFacturaActual);
			}

			int id_prefactura = idPreFacturaActual;

			//Agrego a la tabla item_prefactura el artículo
			id_caja = G.getInstance().licenciaGlobal.getIdCaja();

			if(conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText().equals("")) {
				id_vendedor = 1;
			}else {
				id_vendedor = Integer.parseInt(conFactura.getIntCapturaArticulo().getTxtCodigoVendedor().getText()); 
			}
			//id_usuario_vendedor = conFactura.getIntCapturaArticulo().getTxtNombreVendedor().getText();
			if(conFactura.getIntCapturaArticulo().getTxtNombreVendedor().getText().equals("")) {
				id_usuario_vendedor = "GENERICO";
			}else {
				id_usuario_vendedor = maestroDB.consultarIdVendedor(id_vendedor);
			}

			int id_articulo = articuloFactura.getId_articulo();

			//Si la consulta se realiza con códigos alternos coloca el consultado si es alterno en codigo_articulo_venta
			String codigo_articulo = null;
			String codigo_articulo_venta = null; 
			if(!codigo.equals(articuloFactura.getAlternos()[0])){
				codigo_articulo = articuloFactura.getAlternos()[0];
			}else{
				codigo_articulo = codigo;
			}

			codigo_articulo_venta = articuloFactura.getCodigoArticuloPedido();             //articuloFactura.setCodigoArticuloPedido(codigoArticulo);

			String nombre_provisional = "";
			double dCantidad = 0.0;
			if(conFactura.getIntCapturaArticulo().getTxtCantidad().getText().equals("")) {
				dCantidad = 1.0;
			}else{
				String sCantidad = conFactura.getIntCapturaArticulo().getTxtCantidad().getText();
				sCantidad = sCantidad.replace(",", "");
				dCantidad = Double.parseDouble(sCantidad);
			}

			double cantidad_unidad_medida = dCantidad;

			double valor_unidad = articuloFactura.getValorUnitario();
			double valor_anterior = 0;
			int id_presentacion = articuloFactura.getId_presentacion();
			double cantidad_presentacion = dCantidad; //articuloFactura.getCantidad();

			System.out.println("ModFactura.ItemEnListaDeCompras 434 cantidad_unidad_medida "+cantidad_unidad_medida+" cantidad_presentacion "+cantidad_presentacion+" CANTIDADES A LA TABLA PREFACTURA CANTIDADES A LA TABLA PREFACTURA CANTIDADES A LA TABLA PREFACTURA CANTIDADES A LA TABLA PREFACTURA");

			double valor_presentacion = articuloFactura.getValorUnitario();
			double valor_item = articuloFactura.getValorUnitario()*dCantidad; //articuloFactura.getTotalPrecio();
			int id_base_iva = articuloFactura.getId_base_iva();
			valor_iva = articuloFactura.getIva();
			double porcentaje_descuento = 0;
			valor_descuento = 0;
			int item_promocion = 0;

			item = item + 1;
			boolean resultadoItemPreFactura = maestroDB.agregarItemPreFactura(id_caja, id_prefactura, item, id_vendedor, id_usuario_vendedor, 
					id_articulo, codigo_articulo, codigo_articulo_venta, nombre_provisional, 
					cantidad_unidad_medida, valor_unidad, valor_anterior, id_presentacion, 
					cantidad_presentacion, valor_presentacion, valor_item, id_base_iva, 
					valor_iva, porcentaje_descuento, valor_descuento, item_promocion);

			if(!resultadoItemPreFactura){
				JOptionPane.showMessageDialog(null, "Comuníquese con el administrador BD2");
			}

			//TODO cambiar el formato

			/*saldo = dTotalFactura - maestroDB.pagosRecibido(id_prefactura, id_caja);
			sSaldo = FormatoNumero.formato(saldo);
			intFormaPago.getTxtValor().setText(sSaldo);*/

			double subTotal = maestroDB.subTotalSinImpuestos(id_prefactura,id_caja);
			String sSubTotal = FormatoNumero.formatoCero(subTotal); 
			double impuestos = maestroDB.impuestos(id_prefactura,id_caja);
			String sImpuestos = FormatoNumero.formatoCero(impuestos);
			double totalFactura = maestroDB.totalFactura(id_prefactura,id_caja);
			String totalFactura11 = FormatoNumero.formatoCero(totalFactura);

			conFactura.getIntListaCompra().getTxtSubTotal().setText(sSubTotal);
			conFactura.getIntCapturaArticulo().getTxtTotalFactura().setText(totalFactura11);
			conFactura.getIntListaCompra().getTxtTotalfactura().setText(totalFactura11);
			conFactura.getIntListaCompra().getTxtImpuesto().setText(sImpuestos);

			conFactura.getIntCapturaArticulo().getTxtCodigoArticulo().setText("");
			conFactura.getIntCapturaArticulo().getTxtNombreArticulo().setText("");
			conFactura.getIntCapturaArticulo().getTxtMedida().setText("");
			conFactura.getIntCapturaArticulo().getTxtValorUnitario().setText("");
			conFactura.getIntCapturaArticulo().getTxtCantidad().setText("");
			conFactura.getIntCapturaArticulo().getTxtValorTotal().setText("");
		}
	}

	public String dato(String num,String numero, int tipoCampo){
		//System.out.println("ModeloFactura dato: tipoCampo "+tipoCampo);
		// num = numero que ingresa
		// numero = número que estaba
		// tipoCampo = caracteristica que se quiere en el JTextField
		// tipoCampo = 0 solo números sin formato
		// tipoCampo = 1 numeros con formato y punto
		// tipoCampo = 2 numero con formato sin punto

		if(tipoCampo == 0){ // solo números sin formato
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num.equals("0")){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}
			return numero;
		}
		else if(tipoCampo == 1){ //numeros con formato y punto
			numero = numero.replace(",", "");
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="0"){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="."){
				if(numero.indexOf(".")==-1){
					if(numero.length()==0){
						numero="0.";
					}else{
						String dato1 = numero;
						numero = dato1+num;
					}
				}
			}
			return FormatoNumero.formato(numero);
		}else if(tipoCampo==2){ // numero con formato sin punto
			numero = numero.replace(",", "");
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="0"){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}
			return FormatoNumero.formato(numero);
		}
		else{ // 3 todos los caracteres
			return numero + num;
		}
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}


	public void procedimientoSaleDeUnidades() {
		//if(conFactura.getIntCapturaArticulo().getTxtUnidades())
		System.err.println("ModFactura.procedimientoSaleDeUnidades()");
	}

	public void procedimientoSaleDeCantidad(JTextField txtCantidad) {
		try {
			String sCantidad = txtCantidad.getText();
			if(sCantidad.length()>0) {
				double cantidad = Double.valueOf(sCantidad);
				if(cantidad>1000) {
					//System.out.println("ConFactura.focusGained() CANTIDAD MAYOR A MIL 1.000");
					txtCantidad.setText("");
				}else {
					//System.out.println("ConFactura.focusGained() SUPER BIEN PERO NADA QUE VER");
				}
			}
		} catch (Exception e2) {
			//System.out.println("ConFactura.focusGained() "+e2);
			txtCantidad.setText("");
		}
	}


}