package database;

import java.awt.Graphics;
import java.awt.PrintJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import clases.Almacen;
import clases.Articulo;
import clases.ArticuloFactura;
import clases.BaseIva;
import clases.Caja;
import clases.Cajero;
import clases.Cliente;
import clases.ConsultarBuscar;
import clases.ConsultarBuscarConPrecio;
import clases.ConsultarClienteDomicilio;
import clases.Domicilio;
import clases.Empresa;
import clases.EncabezadoFactura;
import clases.EntidadBancaria;
import clases.Factura;
import clases.ItemDevolucionVenta;
import clases.ItemFactura;
import clases.DatosImprimirFactura;
import clases.DevolucionVenta;
import clases.DevolucionesBaseIva;
import clases.ItemPreFactura;
import clases.ItemPreFormaPago;
import clases.ItemRemision;
import clases.IvaValor;
import clases.Licencia;
import clases.MedioPago;
import clases.MedioPagoFactura;
import clases.MedioPagoPreFactura;
import clases.MensajeDocumento;
import clases.NumeracionAutorizada;
import clases.Pagos;
import clases.PreFactura;
import clases.Proveedor;
import clases.Regimen;
import clases.Remision;
import clases.Resumen;
import clases.Tercero;
import clases.TipoPago;
import clases.Usuario;
import impresiontouch.ImpresionFactura;
import impresiontouch.ImpresionPrinterJob;
import impresiontouch.ImpresionSinLogo;
import util.CryptCadena;
import util.FormatoNumero;
import util.G;


public class MaestroDB {
	private Licencia licencia;
	//private Logger LOG = FileLog.testLoger("MaestroBD2");
	private Connection conexionDB;
	private String connectionString;
	private String driver;
	private boolean estado;
	/*private Statement sttmDBConsulta =null;
	private Statement sttmDBAccion=null;*/

	PrintJob pj;
	Graphics pagina;

	public void pasarPreAPos(int id_prefactura, int id_caja) {
		// select * from prefactura where id = 151 and id_caja = 1
		double maximoFactura = 0;
		double valor_iva = 0;
		//		double valor_base = 0;
		double valor_pago = 0;

		String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

		/*String inputHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp hora = java.sql.Timestamp.valueOf( inputHora );*/

		String sHora = fecha.toString();
		sHora = sHora.substring(sHora.indexOf(" ")+1, sHora.length());
		sHora = sHora.substring(0,sHora.indexOf("."));

		System.out.println("MaestroDB.pasarPreAPos() id_prefactura "+id_prefactura+" id_caja "+id_caja);
		
		// se traen los datos de la preFactura
		PreFactura preFactura = new PreFactura();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from prefactura where id = ? and id_caja = ?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				preFactura.setId(rs.getInt("id")); // serial NOT NULL,
				preFactura.setId_caja(rs.getInt("id_caja")); // integer NOT NULL,
				preFactura.setNumero(rs.getDouble("numero")); // numeric(20,0) DEFAULT 0,
				preFactura.setAuxiliar(rs.getInt("auxiliar")); // integer,
				preFactura.setId_almacen(rs.getInt("id_almacen")); // integer,
				preFactura.setFecha(rs.getTimestamp("fecha")); // timestamp without time zone,
				preFactura.setId_cajero(rs.getInt("id_cajero")); // integer,
				preFactura.setId_vendedor(rs.getInt("id_vendedor")); // integer,
				preFactura.setId_usuario_vendedor(rs.getString("id_usuario_vendedor")); // character varying(30),
				preFactura.setId_cliente(rs.getInt("id_cliente")); // integer NOT NULL DEFAULT 0,
				preFactura.setId_agente_externo(rs.getInt("id_agente_externo")); // integer NOT NULL DEFAULT 0,
				preFactura.setValor_prefactura(rs.getDouble("valor_prefactura")); // numeric(20,2),
				preFactura.setValor_descuento(rs.getDouble("valor_descuento")); // numeric(20,2) DEFAULT 0,
				preFactura.setValor_iva(rs.getDouble("valor_iva")); // numeric(30,10),
				preFactura.setComentario(rs.getString("comentario")); // text DEFAULT ''::text,
				preFactura.setEstado(rs.getString("estado")); // character varying(30),
				preFactura.setDg_fecha_accion(rs.getTimestamp("dg_fecha_accion")); // timestamp without time zone,
				preFactura.setDg_accion(rs.getString("dg_accion")); // character varying(30),

				rs.close();
				st.close();
			}else{
				// de momento nada
			}
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarPreAPos() NumberFormatException ERROR preFactura");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() SQLException ERROR preFactura");
			e.printStackTrace();
		}

		String sGsonPreFactura = "";
		Gson gson = new Gson();
		sGsonPreFactura = gson.toJson(preFactura);
		System.out.println("MaestroDB.pasarPreAPos() PREFACTURA GSON "+sGsonPreFactura);

		// se llenan los datos a la factura
		try {
			String query = "INSERT INTO factura(id_caja, numero, id_numeracion_autorizada, id_almacen, fecha, "
					+ "hora, id_cajero, id_tercero, id_vendedor, id_usuario_vendedor, valor_factura, "
					+ "valor_descuento, valor_iva, costo, valor_devolucion, id_cliente, comentario, estado, "
					+ "fecha_exportacion, estado_exportacion, id_tipo_comprobante_contable, "
					+ "numero_comrpobante_contable, dg_fecha_accion, dg_accion, id_prefactura, cantidad_bolsa_inc, "
					+ "valor_bolsa_inc, numero_impresiones) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_caja); // id_caja
			maximoFactura = maximoFactura()+1; 
			st.setDouble(2, maximoFactura); // numero
			st.setInt(3, idNumeracionAutorizada(id_caja)); //id_numeracion_autorizada
			st.setInt(4, G.getInstance().licenciaGlobal.getIdAlmacen()); // id_almacen
			st.setTimestamp(5, (Timestamp) fecha); // fecha
			
			// se modifica la fecha en la prefactura para que al momento de imprimirla salga con la fecha y hora en que se imprime.
			preFactura.setFecha(fecha); //preFactura.setFecha(rs.getTimestamp("fecha")); // timestamp without time zone,
			
			// System.out.println("MaestroDB.pasarPreAPos() FECHA FECHA FECHA FECHA FECHA "+preFactura.getFecha());
			
			st.setString(6,sHora); // hora
			st.setInt(7, G.getInstance().getIdCajero); // id_cajero
			st.setInt(8, 1); // id_tercero
			st.setInt(9, preFactura.getId_vendedor()); // id_vendedor
			st.setString(10, consultarIdVendedor(preFactura.getId_vendedor())); // id_usuario_vendedor

			valor_pago = preFactura.getValor_prefactura();

			st.setDouble(11, preFactura.getValor_prefactura()); // valor_factura
			st.setDouble(12, 0); // valor_descuento

			valor_iva = preFactura.getValor_iva();

			st.setDouble(13, preFactura.getValor_iva()); // valor_iva
			st.setDouble(14, 0); // TODO costo **************************************
			st.setDouble(15, 0); // valor_devolucion
			st.setInt(16, preFactura.getId_cliente()); // id_cliente // TODO no está quedando almacenada en la prefactura
			st.setString(17, preFactura.getComentario()); // comentario
			st.setString(18, "liquidada"); // estado
			st.setTimestamp(19, (Timestamp) null); // fecha_exportacion
			st.setString(20, ""); // estado_exportacion
			//st.setInt(21,0); // identificador_externo
			st.setString(21, ""); // id_tipo_comprobante_contable
			st.setInt(22,0); // numero_comrpobante_contable
			st.setTimestamp(23, (Timestamp) fecha); // dg_fecha_accion
			st.setString(24, "update"); // dg_accion
			st.setInt(25, preFactura.getId()); // id_prefactura
			st.setInt(26, 0); // cantidad_bolsa_inc
			st.setDouble(27, 0); // valor_bolsa_inc

			if(preFactura.getId_cliente()>0) {
				st.setInt(28, 2); //numero_impresiones				
			}else {
				st.setInt(28, 1); //numero_impresiones
			}

			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			int k=0;
			int idCaja=0;
			double numero=0; 
			while(rsKey.next()) {
				k++;
				idCaja = rsKey.getInt(1);
				numero = rsKey.getDouble(2);
				System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
			}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR factura "+e);
			e.printStackTrace();
		}

		// se da por liquidada la preFactura para que no cargue en las facturas en proceso
		try {
			//UPDATE prefactura SET estado = 'liqidada' WHERE id=169 and id_caja = 1	
			String query = "UPDATE prefactura SET numero = ?, estado = 'liquidada' WHERE id = ? and id_caja = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, maximoFactura);
			st.setInt(2, id_prefactura);
			st.setInt(3, id_caja);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR "+e);
			e.printStackTrace();
		}

		// si la factura tiene domicilio se actualiza en la tabla domicilio con el número de la factura en el id_factura
		// para el domicilio primero se consulta en la tabla domicilio si existe. 
		//verdedaro trae los datos y  falso clase vacia.
		Domicilio domicilio = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from domicilio where id_prefactura=? and id_caja=?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				domicilio = new Domicilio(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
						rs.getDouble(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getInt(13),rs.getString(14),rs.getTimestamp(15));
			}
			rs.close();
			st.close();
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarPreAPos() NumberFormatException ERROR domicilio");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() SQLException ERROR domicilio");
			e.printStackTrace();
		}

		if(domicilio != null) {
			try {
				//UPDATE domicilio SET id_factura=247 WHERE id_prefactura=130 and id_caja=1
				String query = "UPDATE domicilio SET id_factura=? WHERE id_prefactura=? and id_caja=?;";
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setDouble(1, maximoFactura);
				st.setInt(2, id_prefactura);
				st.setInt(3, id_caja);
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
					System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				st.close();
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.pasarPreAPos() ERROR "+e);
				e.printStackTrace();
			}
		}

		/*String sGsonDomicilio = "";
		gson = new Gson();
		sGsonDomicilio = gson.toJson(domicilio);
		System.out.println("MaestroDB.pasarPreAPos() domicilio GSON "+sGsonDomicilio);*/

		// se traen los datos de la item_preFactura 
		ItemPreFactura itemPreFactura;
		ArrayList<ItemPreFactura> alItemPreFactura = new ArrayList<ItemPreFactura>(); 
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_prefactura where id_prefactura = ? and id_caja = ?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				itemPreFactura = new ItemPreFactura();
				itemPreFactura.setId(rs.getInt(1)); // id serial NOT NULL
				itemPreFactura.setId_caja(rs.getInt(2)); // id_caja integer NOT NULL
				itemPreFactura.setId_prefactura(rs.getInt(3)); // id_prefactura integer
				itemPreFactura.setItem(rs.getInt(4)); //  item integer
				itemPreFactura.setId_vendedor(rs.getInt(5)); // id_vendedor integer
				itemPreFactura.setId_usuario_vendedor(rs.getString(6)); // id_usuario_vendedor character varying(30)
				itemPreFactura.setId_articulo(rs.getInt(7)); // id_articulo integer,
				itemPreFactura.setCodigo_articulo(rs.getString(8)); // codigo_articulo character varying(20),
				itemPreFactura.setCodigo_articulo_venta(rs.getString(9)); // codigo_articulo_venta character varying(20),
				itemPreFactura.setNombre_provisional(rs.getString(10)); // nombre_provisional character varying(60),
				itemPreFactura.setCantidad_unidad_medida(rs.getDouble(11)); // cantidad_unidad_medida numeric(10,2),
				itemPreFactura.setValor_unidad(rs.getDouble(12)); // valor_unidad numeric(30,4),
				itemPreFactura.setValor_anterior(rs.getDouble(13)); // valor_anterior numeric(30,4) NOT NULL DEFAULT 0,
				itemPreFactura.setId_presentacion(rs.getInt(14)); // id_presentacion integer,
				itemPreFactura.setCantidad_presentacion(rs.getDouble(15)); // cantidad_presentacion numeric(10,2),
				itemPreFactura.setValor_presentacion(rs.getDouble(16)); // valor_presentacion numeric(30,4),
				itemPreFactura.setValor_item(rs.getDouble(17)); // valor_item numeric(20,2),
				itemPreFactura.setId_base_iva(rs.getInt(18)); // id_base_iva integer,
				itemPreFactura.setValor_iva(rs.getDouble(19)); // valor_iva numeric(30,10),
				itemPreFactura.setPorcentaje_descuento(rs.getDouble(20)); // porcentaje_descuento numeric(5,2),
				itemPreFactura.setValor_descuento(rs.getDouble(21)); // valor_descuento numeric(20,2),
				itemPreFactura.setItem_promocion(rs.getInt(22)); // item_promocion integer,
				alItemPreFactura.add(itemPreFactura);
			}
			rs.close();
			st.close();
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarPreAPos() NumberFormatException ERROR itemPreFactura");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() SQLException ERROR itemPreFactura");
			e.printStackTrace();
		}

		/*String sGsonItemPreFactura = "";
		gson = new Gson();
		sGsonItemPreFactura = gson.toJson(alItemPreFactura);
		System.out.println("MaestroDB.pasarPreAPos() alItemPreFactura GSON "+sGsonItemPreFactura);*/	

		// se llenan los datos de item_factura

		try {
			String query = "INSERT INTO item_factura(id_caja, numero, item, id_vendedor, id_usuario_vendedor, id_articulo, "
					+ "codigo_articulo, codigo_articulo_venta, nombre_provisional, cantidad_unidad_medida, valor_unidad, "
					+ "valor_anterior, id_presentacion, cantidad_presentacion, valor_presentacion, valor_item, id_base_iva, "
					+ "valor_iva, porcentaje_descuento, valor_descuento, costo, costo_item, item_promocion, cantidad_um_devuelta, "
					+ "cantidad_pres_devuelta, estado_exportacion, dg_fecha_accion, dg_accion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			for (int j = 0; j < alItemPreFactura.size(); j++){
				//System.out.println("MaestroDB.pasarPreAPos() alItemPreFactura.size() "+alItemPreFactura.size()+" id_caja "+id_caja+" numero "+maximoFactura);
				st.setInt(1, id_caja); //id_caja integer NOT NULL
				st.setDouble(2, maximoFactura); // numero numeric(20,0) NOT NULL
				st.setInt(3, alItemPreFactura.get(j).getItem()); // item integer NOT NULL
				st.setInt(4, alItemPreFactura.get(j).getId_vendedor()); // id_vendedor integer,
				st.setString(5, alItemPreFactura.get(j).getId_usuario_vendedor()); // id_usuario_vendedor character varying(30),
				st.setInt(6, alItemPreFactura.get(j).getId_articulo()); // id_articulo integer,
				st.setString(7, alItemPreFactura.get(j).getCodigo_articulo()); // codigo_articulo character varying(20),
				st.setString(8, alItemPreFactura.get(j).getCodigo_articulo_venta()); // codigo_articulo_venta character varying(20),
				st.setString(9, alItemPreFactura.get(j).getNombre_provisional()); // nombre_provisional character varying(60),
				st.setDouble(10, alItemPreFactura.get(j).getCantidad_unidad_medida()); // cantidad_unidad_medida numeric(10,2),
				st.setDouble(11, alItemPreFactura.get(j).getValor_unidad()); // valor_unidad numeric(30,4),
				st.setDouble(12, alItemPreFactura.get(j).getValor_anterior()); // valor_anterior numeric(30,4) NOT NULL DEFAULT 0,
				st.setInt(13, alItemPreFactura.get(j).getId_presentacion()); // id_presentacion integer,
				st.setDouble(14, alItemPreFactura.get(j).getCantidad_presentacion()); // cantidad_presentacion numeric(10,2),
				st.setDouble(15, alItemPreFactura.get(j).getValor_presentacion()); // valor_presentacion numeric(30,4),
				st.setDouble(16, alItemPreFactura.get(j).getValor_item()); // valor_item numeric(20,2),
				st.setInt(17, alItemPreFactura.get(j).getId_base_iva()); // id_base_iva integer,
				st.setDouble(18, alItemPreFactura.get(j).getValor_iva()); // valor_iva numeric(30,10),
				st.setDouble(19, alItemPreFactura.get(j).getPorcentaje_descuento()); // porcentaje_descuento numeric(5,2),
				st.setDouble(20, alItemPreFactura.get(j).getValor_descuento()); // valor_descuento numeric(20,2),
				st.setDouble(21, 0); // costo numeric(20,2),
				st.setDouble(22, 0); // costo_item numeric(20,2) NOT NULL DEFAULT 0,
				st.setInt(23, 0); // item_promocion integer,
				st.setDouble(24, 0); // cantidad_um_devuelta numeric(10,2),
				st.setDouble(25, 0); // cantidad_pres_devuelta numeric(10,2),
				st.setString(26, ""); // estado_exportacion character varying(30),
				st.setTimestamp(27, (Timestamp) fecha); // dg_fecha_accion timestamp without time zone,
				st.setString(28, "insert"); // dg_accion character varying(30),
				st.executeUpdate();

				/*ResultSet rsKey = st.getGeneratedKeys();
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}*/
			}
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR factura "+e);
			e.printStackTrace();
		}

		// se traen datos de medio_pago_prefactura
		MedioPagoPreFactura medioPagoPreFactura; 
		ArrayList<MedioPagoPreFactura> alMedioPagoPreFactura = new ArrayList<MedioPagoPreFactura>(); 
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from medio_pago_prefactura where id_prefactura = ? and id_caja = ?;");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				medioPagoPreFactura = new MedioPagoPreFactura();
				medioPagoPreFactura.setId(rs.getInt(1)); // id serial NOT NULL,
				medioPagoPreFactura.setId_caja(rs.getInt(2)); // integer NOT NULL,
				medioPagoPreFactura.setId_prefactura(rs.getInt(3)); // integer,
				medioPagoPreFactura.setId_medio_pago(rs.getInt(4)); // integer NOT NULL,
				medioPagoPreFactura.setItem(rs.getInt(5)); // integer,
				medioPagoPreFactura.setId_entidad_bancaria(rs.getInt(6)); // integer,
				medioPagoPreFactura.setTarjeta(rs.getInt(7)); // bigint,
				medioPagoPreFactura.setAutorizacion(rs.getInt(8)); // bigint,
				medioPagoPreFactura.setNumero_recibo(rs.getInt(9)); // integer,
				medioPagoPreFactura.setValor_pago(rs.getDouble(10)); // numeric(20,2),
				medioPagoPreFactura.setPorcentaje_comision(rs.getDouble(11)); // numeric(5,2),
				medioPagoPreFactura.setValor_comision(rs.getDouble(12)); // numeric(20,2),
				medioPagoPreFactura.setValor_iva(rs.getDouble(13)); // numeric(30,10),
				medioPagoPreFactura.setEfectivo_recibido(rs.getDouble(14)); // numeric(20,2),
				medioPagoPreFactura.setDg_fecha_accion(rs.getTimestamp(15)); // timestamp without time zone,
				medioPagoPreFactura.setDg_accion(rs.getString(16)); // character varying(30),

				/*System.out.println("MaestroDB.pasarPreAPos() 1 datos que se traen de medio_pago_prefactura item "+medioPagoPreFactura.getItem());
				System.out.println("MaestroDB.pasarPreAPos() 1 datos que se traen de medio_pago_prefactura Id_medio_pago"+medioPagoPreFactura.getId_medio_pago());*/

				alMedioPagoPreFactura.add(medioPagoPreFactura);
			}
			rs.close();
			st.close();
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarPreAPos() NumberFormatException ERROR medioPagoPreFactura");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() SQLException ERROR medioPagoPreFactura");
			e.printStackTrace();
		}

		/*System.out.println("MaestroDB.pasarPreAPos() item de MedioPagoPreFactura debito "+alMedioPagoPreFactura.get(1).getItem()); 
		System.out.println("MaestroDB.pasarPreAPos() Id_medio_pago de MedioPagoPreFactura debito "+alMedioPagoPreFactura.get(1).getId_medio_pago()); 
		System.out.println("MaestroDB.pasarPreAPos() item de MedioPagoPreFactura efectivo "+alMedioPagoPreFactura.get(2).getItem()); 
		System.out.println("MaestroDB.pasarPreAPos() Id_medio_pago de MedioPagoPreFactura debito "+alMedioPagoPreFactura.get(2).getId_medio_pago());*/

		/*String sGsonMedioPagoPreFactura = "";
		Gson gson = new Gson();
		sGsonMedioPagoPreFactura = gson.toJson(alMedioPagoPreFactura);
		System.out.println("MaestroDB.pasarPreAPos() alMedioPagoPreFactura GSON "+sGsonMedioPagoPreFactura);*/	

		// pasar a medio_pago_factura
		try {
			String query = "INSERT INTO medio_pago_factura(id_caja, numero, id_medio_pago, item, id_entidad_bancaria, "
					+ "tarjeta, autorizacion, numero_recibo, valor_pago, porcentaje_comision, valor_comision, valor_iva, "
					+ "efectivo_recibido, dg_fecha_accion, dg_accion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			for (int j = 0; j < alMedioPagoPreFactura.size(); j++){
				st.setInt(1, id_caja); // id_caja integer NOT NULL,
				st.setDouble(2, maximoFactura); // numero numeric(20,0) NOT NULL,
				st.setInt(3, alMedioPagoPreFactura.get(j).getId_medio_pago()); // id_medio_pago integer NOT NULL,
				st.setInt(4, alMedioPagoPreFactura.get(j).getItem()); // item integer NOT NULL,
				st.setInt(5, alMedioPagoPreFactura.get(j).getId_entidad_bancaria()); // id_entidad_bancaria integer,
				st.setInt(6, alMedioPagoPreFactura.get(j).getTarjeta()); // tarjeta bigint,
				st.setInt(7, alMedioPagoPreFactura.get(j).getAutorizacion()); // autorizacion bigint,
				st.setInt(8, alMedioPagoPreFactura.get(j).getNumero_recibo()); // numero_recibo integer,
				st.setDouble(9, alMedioPagoPreFactura.get(j).getValor_pago()); // valor_pago numeric(20,2),
				st.setDouble(10, alMedioPagoPreFactura.get(j).getPorcentaje_comision()); // porcentaje_comision numeric(5,2),
				st.setDouble(11, alMedioPagoPreFactura.get(j).getValor_comision()); // valor_comision numeric(20,2),
				st.setDouble(12, alMedioPagoPreFactura.get(j).getValor_iva()); // valor_iva numeric(30,10),
				st.setDouble(13, alMedioPagoPreFactura.get(j).getEfectivo_recibido()); // efectivo_recibido numeric(20,2),
				st.setTimestamp(14, (Timestamp) alMedioPagoPreFactura.get(j).getDg_fecha_accion()); // dg_fecha_accion timestamp without time zone,
				st.setString(15, alMedioPagoPreFactura.get(j).getDg_accion()); // dg_accion character varying(30),

				/*System.out.println("MaestroDB.pasarPreAPos() 2 INSERT medio_pago_factura item "+alMedioPagoPreFactura.get(j).getItem());
				System.out.println("MaestroDB.pasarPreAPos() 2 INSERT medio_pago_factura Id_medio_pago "+alMedioPagoPreFactura.get(j).getId_medio_pago());*/

				st.executeUpdate();

				/*ResultSet rsKey = st.getGeneratedKeys();
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}*/
			}
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR factura "+e);
			e.printStackTrace();
		}

		// se calculan el total de los ivas por cada una de las tarifas referidas a si id IVA.
		IvaValor ivaValor; //(int idIva, double valorIva)
		ArrayList<IvaValor> alIvaValor = new ArrayList<IvaValor>();
		try {
			//select id_base_iva, sum(valor_iva) as valor_iva, sum(valor_item) as totalXiva from item_factura where numero = 191 and id_caja = 1 group by id_base_iva;
			PreparedStatement st = conexionDB.prepareStatement("select id_base_iva, sum(valor_iva), sum(valor_item) from item_factura where numero = ? and id_caja = ? group by id_base_iva;");
			st.setDouble(1, maximoFactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ivaValor = new IvaValor(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
				alIvaValor.add(ivaValor);
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*String sGsonIvaValor = "";
		gson = new Gson();
		sGsonIvaValor = gson.toJson(alIvaValor);
		System.out.println("MaestroDB.pasarPreAPos() alIvaValor GSON "+sGsonIvaValor);	*/

		// se calcula el valor de todas las formas de pago por cada id del medio de pago
		TotalMedioPago totalMedioPago; //(int idIva, double valorIva)
		ArrayList<TotalMedioPago> alTotalMedioPago = new ArrayList<TotalMedioPago>();
		try {
			//select id_medio_pago, sum(valor_iva) from medio_pago_factura where numero = 191 and id_caja = 1 group by id_medio_pago
			PreparedStatement st = conexionDB.prepareStatement("select id_medio_pago, sum(valor_iva), sum(valor_pago) from medio_pago_factura where numero = ? and id_caja = ? group by id_medio_pago;;");
			st.setDouble(1, maximoFactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				totalMedioPago = new TotalMedioPago(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
				alTotalMedioPago.add(totalMedioPago);
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// alIvaValor = select id_base_iva, sum(valor_iva) from item_factura where numero = ? and id_caja = ? group by id_base_iva;
		// alTotalMedioPago = select id_medio_pago, sum(valor_iva) from medio_pago_factura where numero = ? and id_caja = ? group by id_medio_pago;
		//valor_base = valor_pago - valor_iva;
		double proValor_iva = 0; //valor_iva
		double proValor_base = 0; // valor_pago
		double proValor_pago = 0; // valor_base

		try {
			String query = "INSERT INTO iva_medio_pago_factura(id_caja, numero, id_medio_pago, item, orden, id_base_iva, valor_iva,"
					+ "valor_pago, dg_fecha_accion, dg_accion, valor_base) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int orden = 0;
			/*for (int i = 0; i < alTotalMedioPago.size(); i++) {
				System.out.println("MaestroDB.pasarPreAPos() 3 INSERT iva_medio_pago_factura id_medio_pago "+alMedioPagoPreFactura.get(i).getId_medio_pago());
				System.out.println("MaestroDB.pasarPreAPos() 3 INSERT iva_medio_pago_factura item "+alMedioPagoPreFactura.get(i).getItem());
			}*/

			for (int i = 0; i < alTotalMedioPago.size(); i++) {
				for (int j = 0; j < alIvaValor.size(); j++) {
					orden++;

					proValor_iva = (alIvaValor.get(j).getValorIva()/valor_iva)*alTotalMedioPago.get(i).getValorMedioPago();
					proValor_pago = (alIvaValor.get(j).getTotalxIva()/valor_pago)*alTotalMedioPago.get(i).getTotaIvaMedioPago();
					proValor_base = proValor_pago - proValor_iva ; 

					/*System.out.println("MaestroDB.pasarPreAPos() id_caja "+id_caja); 
					System.out.println("MaestroDB.pasarPreAPos() numero"+maximoFactura);
					System.out.println("MaestroDB.pasarPreAPos() id_medio_pago "+alTotalMedioPago.get(i).getIdMedioPago());
					System.out.println("MaestroDB.pasarPreAPos() item "+alMedioPagoPreFactura.get(i).getItem());*/

					st.setInt(1, id_caja); // id_caja
					st.setDouble(2, maximoFactura); // numero
					st.setInt(3, alMedioPagoPreFactura.get(i).getId_medio_pago()); // alTotalMedioPago.get(i).getIdMedioPago()); // id_medio_pago
					st.setInt(4,alMedioPagoPreFactura.get(i).getItem()); // item
					st.setInt(5,orden); // orden
					st.setDouble(6, alIvaValor.get(j).getIdIva()); // id_base_iva
					st.setDouble(7, proValor_iva);
					st.setDouble(8,proValor_base);
					st.setTimestamp(9, fecha);
					st.setString(10, "insert");
					st.setDouble(11, proValor_pago);
					st.executeUpdate();
					conexionDB.commit();
				}
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR "+e);
			e.printStackTrace();
		}

		// registrar en pendiente_exportacion para sincronizar con el servidor
		try {
			String query = "INSERT INTO pendiente_exportacion(id_host_origen, id_host_padre, tabla, campo_id, "
					+ "tipo_id, id_tabla, fecha_evento, accion, estado, observacion, dg_fecha_accion, "
					+ "dg_accion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			//st.setInt(1, 0); // id serial NOT NULL,
			st.setInt(1, 2); // id_host_origen integer,
			st.setInt(2, 1); // id_host_padre integer,
			st.setString(3, "factura"); // tabla character varying(30),
			st.setString(4, "id_caja;numero"); // campo_id character varying(50),
			st.setString(5, "int;int"); // tipo_id character varying(50),
			String idTable = id_caja+";"+ (int)maximoFactura;
			st.setString(6, idTable); // id_tabla character varying(50),
			st.setTimestamp(7, fecha); // fecha_evento timestamp without time zone,
			//st.setTimestamp(8, ); // fecha_exportacion timestamp without time zone,
			st.setString(8, "update"); // accion character varying(30),
			st.setString(9, ""); // estado character varying(30),
			st.setString(10, ""); // observacion text,
			st.setTimestamp(11, fecha); // dg_fecha_accion timestamp without time zone,
			st.setString(12, "insert"); // dg_accion character varying(30),
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
				while(rsKey.next()) {
					itemIdPrefactura = rsKey.getInt(1);
					blnResultado = true;
				}*/
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR pendiente_exportacion "+e);
			e.printStackTrace();
		}

		//se termina de crear el JSON con toda la información de la factura para imprimirla
		// con los datos propios de la empresa y las condiciones de facturación

		/*DATOS QUE HACEN FALTA
		NOMBRE DE LA EMPRESA
		NIT
		NRO. AUTORIZACION
		RANGO DE IMPRESION
		REGIMEN
		FACTURA DE VENTA
		FECHA Y HORA
		 */

		EncabezadoFactura encabezadoFactura = new EncabezadoFactura();		
		try {// EMPRESA
			//select * from empresa
			PreparedStatement st = conexionDB.prepareStatement("select * from empresa;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdEmpresa(rs.getInt(1)); //id integer NOT NULL,
				encabezadoFactura.setIdRegimen(rs.getInt(2)); //  id_regimen integer,
				encabezadoFactura.setNombreEmpresa(rs.getString(3)); //  nombre character varying(50),
				encabezadoFactura.setNit(rs.getString(4)); //  nit character varying(20),
				encabezadoFactura.setRepresentanteLegal(rs.getString(5)); //  representante_legal character varying(200),
				encabezadoFactura.setDireccionEmpresa(rs.getString(6)); //direccion character varying(200),
				encabezadoFactura.setIdCiudadEmpresa(rs.getInt(7)); //  id_ciudad integer NOT NULL DEFAULT 0,
				encabezadoFactura.setTelefonoEmpresa(rs.getString(8)); //  telefono character varying(100),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// ALMACEN
			//select * from almacen where id = 1 and estado = 'activo'
			PreparedStatement st = conexionDB.prepareStatement("select * from almacen where id = ? and estado = 'activo';");
			st.setDouble(1, G.getInstance().licenciaGlobal.getIdAlmacen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdAlmacen(rs.getInt(1)); // id serial NOT NULL,
				encabezadoFactura.setNombreAlmacen(rs.getString(3)); // nombre character varying(50),
				encabezadoFactura.setPrefijoAlmacen(rs.getString(4)); //  prefijo character varying(5),
				encabezadoFactura.setIdCiudadAlmacen(rs.getInt(6)); // id_ciudad integer NOT NULL DEFAULT 0,
				encabezadoFactura.setDireccionAlmacen(rs.getString(7)); //  direccion character varying(200),
				encabezadoFactura.setTelefonoAlmacen(rs.getString(8)); //  telefono character varying(100),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// REGIMEN
			//select * from regimen where id=1;
			PreparedStatement st = conexionDB.prepareStatement("select * from regimen where id=?;");
			st.setDouble(1,encabezadoFactura.getIdRegimen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setNombreRegimen(rs.getString(2)); // nombre character varying(30),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// NUMERACION AUTORIZADA
			//SELECT * FROM numeracion_autorizada where id_caja = 1 and estado = 'activo'
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM numeracion_autorizada where id_caja = ? and estado = 'activo';");
			st.setDouble(1,G.getInstance().licenciaGlobal.getIdCaja());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdNumeracionAutorizada(rs.getInt(1)); // id serial NOT NULL,
				encabezadoFactura.setIdCajaNumeracionAutorizada(rs.getInt(2)); // id_caja integer,
				encabezadoFactura.setResolucion(rs.getString(3)); // resolucion character varying(40),				
				encabezadoFactura.setFechaExpedicionNumeracionAutorizada(rs.getTimestamp(4)); // fecha_expedicion date,
				encabezadoFactura.setTipoFacturacion(rs.getString(5)); // tipo_facturacion character varying(30),
				encabezadoFactura.setPrefijoNumeracionAutorizada(rs.getString(6)); // prefijo character varying(10),
				encabezadoFactura.setRangoInicial(rs.getDouble(7)); // rango_inicial numeric(20,0),
				encabezadoFactura.setRangoFinal(rs.getDouble(8)); // rango_final numeric(20,0),
				encabezadoFactura.setNumeroActual(rs.getDouble(9)); // numero_actual numeric(20,0),
				encabezadoFactura.setFechaCaducidad(rs.getTimestamp(10)); // fecha_caducidad date,
				encabezadoFactura.setEstadoNumeracionAutorizada(rs.getString(12)); //  estado character varying(30),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {// ALMACEN MENSAJE DOCUMENTO TODO TABLE mensaje_documento ????
			// select * from almacen_mensaje_documento where id_almacen = 1;
			PreparedStatement st = conexionDB.prepareStatement("select * from almacen_mensaje_documento where id_almacen = ?;");
			st.setDouble(1,encabezadoFactura.getIdAlmacen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdMensajeDocumento(rs.getInt(1)); // integer NOT NULL,			
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// CAJA
			// select * from caja where id = 1 and id_almacen = 1;;
			PreparedStatement st = conexionDB.prepareStatement("select * from caja where id = ? and id_almacen = ?;");
			st.setInt(1,G.getInstance().licenciaGlobal.getIdCaja());
			st.setInt(2, G.getInstance().licenciaGlobal.getIdAlmacen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {				
				encabezadoFactura.setIdCaja(rs.getInt(1)); // id serial NOT NULL,
				encabezadoFactura.setIdHostCaja(rs.getInt(3)); // id_host integer DEFAULT 0,
				encabezadoFactura.setIdCajaPadre(rs.getInt(4)); // id_caja_padre integer DEFAULT 0,
				encabezadoFactura.setNombreCaja(rs.getString(5)); // nombre character varying(200),
				encabezadoFactura.setPrefijoCaja(rs.getString(6)); // prefijo character varying(10),
				encabezadoFactura.setImpresora(rs.getString(7)); // impresora character varying(50),
				encabezadoFactura.setEstadoCaja(rs.getString(8)); // estado character varying(30),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// BASE_IVA
			// select * from base_iva
			BaseIva baseIva = null;
			ArrayList<BaseIva> alBaseIva = new ArrayList<BaseIva>();
			PreparedStatement st = conexionDB.prepareStatement("select * from base_iva;");
			/*st.setInt(1, G.getInstance().licenciaGlobal.getIdCaja());
			st.setInt(2, G.getInstance().licenciaGlobal.getIdAlmacen());*/
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				baseIva = new BaseIva();
				baseIva.setIdBaseIva(rs.getInt("id")); // id serial NOT NULL,
				baseIva.setBaseIva(rs.getDouble("base")); // base numeric(4,2),
				baseIva.setEstadoBaseIva(rs.getString("estado")); // estado character varying(30),
				System.out.println("MaestroDB.pasarPreAPos()+EstadoBaseIva=>"+rs.getString(3));
				baseIva.setNombreBaseIva(rs.getString("nombre")); // nombre character varying(30),
				System.out.println("MaestroDB.pasarPreAPos() NombreBaseIva=>"+rs.getString(7));
				alBaseIva.add(baseIva);
			}
			encabezadoFactura.setBaseIva(alBaseIva);
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}				  
		// traer nombre del cliente tenga o no tenga domicilio
		//select * from cliente where id=3 and estado = 'activo'

		try {// CLIENTE
			// select * from base_iva
			PreparedStatement st = conexionDB.prepareStatement("select * from cliente where id=? and estado = 'activo';");
			st.setInt(1, preFactura.getId_cliente());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setNombreCliente(rs.getString(3)+" "+rs.getString(4));
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		// Trae el nombre de los artículos
		ArticuloFactura articuloFactura = null;
		ArrayList<ArticuloFactura> alArticuloFactura = new ArrayList<ArticuloFactura>();
		for (int i = 0; i < alItemPreFactura.size(); i++) {
			articuloFactura = new ArticuloFactura();
			articuloFactura = buscarArticulo(alItemPreFactura.get(i).getCodigo_articulo(), 1, 1, "0");
			alArticuloFactura.add(articuloFactura);
			encabezadoFactura.setArticuloFactura(alArticuloFactura);
		}

		//trae el nombre del cajero
		encabezadoFactura.setNombreCajero(G.getInstance().cajero);

		DatosImprimirFactura datosImprimirFactura = new DatosImprimirFactura();
		datosImprimirFactura.setEncabezadoFactura(encabezadoFactura);
		datosImprimirFactura.setPreFactura(preFactura);
		// agrega el número de la factura
		datosImprimirFactura.getPreFactura().setNumero(maximoFactura);
		datosImprimirFactura.setDomicilio(domicilio);
		datosImprimirFactura.setItemPreFactura(alItemPreFactura);
		datosImprimirFactura.setMedioPagoPreFactura(alMedioPagoPreFactura);
		datosImprimirFactura.setIvaValor(alIvaValor);
		//datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getNombreBaseIva();
		boolean impresionLogo = Boolean.valueOf(cargarUnParametro("impresionConLogo"));

		if(datosImprimirFactura.getDomicilio()!=null) {

			if(impresionLogo) {
				System.out.println("MaestroDB.pasarPreAPos() 1");
				new ImpresionPrinterJob(datosImprimirFactura, this, encabezadoFactura,1);
				new ImpresionPrinterJob(datosImprimirFactura, this, encabezadoFactura,2);
				//new	ImpresionConLogo(datosImprimirFactura,this, encabezadoFactura); pide la ventana de enviar impresion
			}else {
				System.out.println("MaestroDB.pasarPreAPos() 2");
				//new ImpresionSinLogo(datosImprimirFactura, this,encabezadoFactura,1);
				new ImpresionSinLogo(datosImprimirFactura, this,encabezadoFactura,2);
			}
		}else {

			if(impresionLogo) {
				System.out.println("MaestroDB.pasarPreAPos() 3");
				new ImpresionPrinterJob(datosImprimirFactura, this, encabezadoFactura,1);
				//new	ImpresionConLogo(datosImprimirFactura,this, encabezadoFactura);
			}else {
				System.out.println("MaestroDB.pasarPreAPos() 4 No impresion con Logo");
				
				String sNumeroImpresiones = cargarUnParametro("cantidadCopiasFactura");
				int numeroImpresiones = Integer.valueOf(sNumeroImpresiones);
				System.out.println("MaestroDB.pasarPreAPos() numeroImpresiones ="+numeroImpresiones+" cccccccccccccccccccccccccccccccccc");
				for (int i = 0; i < numeroImpresiones; i++) {
					new ImpresionSinLogo(datosImprimirFactura, this,encabezadoFactura,1);
				}
			}
		}
	}

	public Empresa traeEmpresa() {
		try {// EMPRESA
			//select * from empresa
			Empresa empresa = null; 
			PreparedStatement st = conexionDB.prepareStatement("select * from empresa;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				empresa = new Empresa(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getInt(7),rs.getString(8),rs.getTimestamp(9),rs.getString(10));
			}
			st.close();
			rs.close();
			return empresa;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int agregarPago(int id_caja, int id_almacen, int id_proveedor, int id_cajero, int id_tipo_pago,
			double valor_factura, double valor_descuento, double valor_pagado, String numero_factura, Date fechaPago, String comentario, String estado2,
			Date dg_fecha_accion, String dg_accion) {

		try {
			String query = "INSERT INTO pagos(id_caja, id_almacen, id_proveedor, id_cajero, id_tipo_pago, valor_factura, valor_descuento, "
					+ "valor_pagado, numero_factura, fechapago, comentario, estado, dg_fecha_accion, dg_accion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja); 
			st.setInt(2,id_almacen); 
			st.setInt(3, id_proveedor); 
			st.setInt(4, id_cajero); 
			st.setInt(5, id_tipo_pago);			
			st.setDouble(6, valor_factura);
			st.setDouble(7, valor_descuento);
			st.setDouble(8, valor_pagado);
			st.setString(9, numero_factura); 
			st.setTimestamp(10, (Timestamp) fechaPago); 
			st.setString(11, comentario); 
			st.setString(12, estado2);
			st.setTimestamp(13, (Timestamp) dg_fecha_accion); 
			st.setString(14, dg_accion);
			st.executeUpdate();
			ResultSet rsKey = st.getGeneratedKeys();
			int idProveedor = 0;
			while(rsKey.next()) {
				idProveedor = rsKey.getInt(1);
			}
			System.out.println("MaestroDB.agregarPago() idProveedor "+idProveedor);
			conexionDB.commit();
			st.close();
			return idProveedor;
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarPago() ERROR "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Regimen> regimen() {
		try {// REGIMEN
			// select * from regimen
			Regimen regimen;
			ArrayList<Regimen> alRegimen = new ArrayList<Regimen>(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from regimen;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				regimen = new Regimen(rs.getInt("id"), rs.getString("nombre"), rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion"));
				alRegimen.add(regimen);
			}
			st.close();
			rs.close();
			return alRegimen;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<TipoPago> tipoPago() {
		try {// TIPOPAGO
			// select * from regimen
			TipoPago tipoPago;
			ArrayList<TipoPago> alTipoPago = new ArrayList<TipoPago>(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from tipo_pago;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				tipoPago = new TipoPago(rs.getInt("id"), rs.getString("nombre"), rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion"));
				alTipoPago.add(tipoPago);
			}
			st.close();
			rs.close();
			return alTipoPago;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Proveedor traeProveedor(long documento) {
		try {// PROVEEDOR
			//select * from proveedor where id = 1 and estado = 'activo'
			Proveedor proveedor = new Proveedor(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from proveedor where documento = ? and estado = 'activo';");
			st.setLong(1, documento);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				proveedor.setId(rs.getInt("id"));
				proveedor.setCodigo(rs.getString("codigo"));
				proveedor.setId_tipo_documento(rs.getString("id_tipo_documento"));
				proveedor.setDocumento(rs.getLong("documento"));
				proveedor.setDigito_verificacion(rs.getInt("digito_verificacion"));
				proveedor.setNombre(rs.getString("nombre"));
				proveedor.setNombre_comercial(rs.getString("nombre_comercial"));
				proveedor.setRepresentante_legal(rs.getString("representante_legal"));
				proveedor.setDireccion(rs.getString("direccion"));
				proveedor.setId_ciudad(rs.getInt("id_ciudad"));
				proveedor.setTelefono(rs.getString("telefono"));
				proveedor.setEmail(rs.getString("email"));
				proveedor.setWeb(rs.getString("web"));
				proveedor.setId_regimen(rs.getInt("id_regimen"));
				proveedor.setValor_minimo_compra(rs.getDouble("valor_minimo_compra"));
				proveedor.setDescripcion(rs.getString("descripcion"));
				proveedor.setEstado(rs.getString("estado"));
				proveedor.setDg_fecha_accion(rs.getTimestamp("dg_fecha_accion"));
				proveedor.setDg_accion(rs.getString("dg_accion"));
			}
			st.close();
			rs.close();
			return proveedor;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Proveedor traeProveedorConId(int id_proveedor) {
		try {// PROVEEDOR
			//select * from proveedor where id = 1 and estado = 'activo'
			Proveedor proveedor = new Proveedor(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from proveedor where id = ? and estado = 'activo';");
			st.setInt(1, id_proveedor);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				proveedor.setId(rs.getInt("id"));
				proveedor.setCodigo(rs.getString("codigo"));
				proveedor.setId_tipo_documento(rs.getString("id_tipo_documento"));
				proveedor.setDocumento(rs.getLong("documento"));
				proveedor.setDigito_verificacion(rs.getInt("digito_verificacion"));
				proveedor.setNombre(rs.getString("nombre"));
				proveedor.setNombre_comercial(rs.getString("nombre_comercial"));
				proveedor.setRepresentante_legal(rs.getString("representante_legal"));
				proveedor.setDireccion(rs.getString("direccion"));
				proveedor.setId_ciudad(rs.getInt("id_ciudad"));
				proveedor.setTelefono(rs.getString("telefono"));
				proveedor.setEmail(rs.getString("email"));
				proveedor.setWeb(rs.getString("web"));
				proveedor.setId_regimen(rs.getInt("id_regimen"));
				proveedor.setValor_minimo_compra(rs.getDouble("valor_minimo_compra"));
				proveedor.setDescripcion(rs.getString("descripcion"));
				proveedor.setEstado(rs.getString("estado"));
				proveedor.setDg_fecha_accion(rs.getTimestamp("dg_fecha_accion"));
				proveedor.setDg_accion(rs.getString("dg_accion"));
			}
			st.close();
			rs.close();
			return proveedor;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Almacen almacen(int id_almacen) {
		try {// ALMACEN
			//select * from almacen where id = 1 and estado = 'activo'
			Almacen almacen = new Almacen(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from almacen where id = ? and estado = 'activo';");
			st.setDouble(1, G.getInstance().licenciaGlobal.getIdAlmacen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				almacen.setId(rs.getInt("id"));
				almacen.setId_host(rs.getInt("id_host"));
				almacen.setNombre(rs.getString("nombre"));
				almacen.setPrefijo(rs.getString("prefijo"));
				almacen.setPrefijo_secundario(rs.getString("prefijo_secundario"));
				almacen.setId_ciudad(rs.getInt("id_ciudad"));
				almacen.setDireccion(rs.getString("direccion"));
				almacen.setTelefono(rs.getString("telefono"));
				almacen.setEstado(rs.getString("estado"));
				almacen.setDivision(rs.getString("division"));
				almacen.setFecha_exportacion(rs.getTimestamp("fecha_exportacion"));
				almacen.setDg_fecha_accion(rs.getTimestamp("dg_fecha_accion"));
				almacen.setDg_accion(rs.getString("dg_accion"));
			}
			st.close();
			rs.close();
			return almacen;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Caja caja(int id_Caja) {
		try {// CAJA
			//select * from caja where id = 1 and estado = 'activo'
			Caja caja = new Caja(); 
			PreparedStatement st = conexionDB.prepareStatement("select * from caja where id = ? and estado = 'activo';");
			st.setDouble(1,id_Caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				caja.setId_caja_padre(rs.getInt("id_caja_padre"));
				caja.setId_almacen(rs.getInt("id_almacen"));
				caja.setId_host(rs.getInt("id_host"));
				caja.setId_caja_padre(rs.getInt("id_caja_padre"));
				caja.setNombre(rs.getString("nombre"));
				caja.setPrefijo(rs.getString("prefijo"));
				caja.setImpresora(rs.getString("impresora"));
				caja.setEstado(rs.getString("estado"));
				caja.setFecha_exportacion(rs.getTimestamp("fecha_exportacion"));
				caja.setDg_fecha_accion(rs.getTimestamp("dg_fecha_accion"));
				caja.setDg_accion(rs.getString("dg_accion"));
			}
			st.close();
			rs.close();
			return caja;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	//TODO pasar este método al metodo preapor para no repetir código
	public ArrayList<BaseIva> traeTablaIva(){
		try {// BASE_IVA
			// select * from base_iva
			BaseIva baseIva = null;
			ArrayList<BaseIva> alBaseIva = new ArrayList<BaseIva>();
			PreparedStatement st = conexionDB.prepareStatement("select * from base_iva;");
			/*st.setInt(1, G.getInstance().licenciaGlobal.getIdCaja());
			st.setInt(2, G.getInstance().licenciaGlobal.getIdAlmacen());*/
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				baseIva = new BaseIva();
				baseIva.setIdBaseIva(rs.getInt("id")); // id serial NOT NULL,
				baseIva.setBaseIva(rs.getDouble("base")); // base numeric(4,2),
				baseIva.setEstadoBaseIva(rs.getString("estado")); // estado character varying(30),
				baseIva.setNombreBaseIva(rs.getString("nombre")); // nombre character varying(30),
				alBaseIva.add(baseIva);
			}
			st.close();
			rs.close();
			return alBaseIva;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<MensajeDocumento> MensajesFactura(int id_almacen){
		//select * from almacen_mensaje_documento where id_almacen = ?;
		ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
		ArrayList<Integer> alNumeroMensaje = new ArrayList<Integer>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from almacen_mensaje_documento where id_almacen = ?;");
			st.setInt(1, id_almacen);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				alNumeroMensaje.add(rs.getInt(1));
			}			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from mensaje_documento order by id;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				for (int j = 0; j < alNumeroMensaje.size(); j++) {
					if(rs.getInt(1) == alNumeroMensaje.get(j)) {
						alMensajesFactura.add(new MensajeDocumento(rs.getInt("id"), rs.getInt("id_documento_pos"), rs.getString("mensaje"), 
								rs.getString("estado"), rs.getDate("fecha_exportacion"), rs.getDate("dg_fecha_accion"), 
								rs.getString("dg_accion"), rs.getString("ubicacion")));
					}
				}
			}
			return alMensajesFactura;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String centrado(String sTitulo) {
		int titulo = (44 - sTitulo.length())/2;
		String sTituloEspacio = "";
		for (int i = 0; i < titulo; i++) {
			sTituloEspacio = sTituloEspacio+" ";
		}
		sTituloEspacio = sTituloEspacio +sTitulo;
		return sTituloEspacio;
	}

	public boolean existeFactura(int id_prefactura, int id_caja) {
		//select id_prefactura from factura where id_prefactura = 127 and id_caja = 1
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_prefactura from factura where id_prefactura = ? and id_caja = ?;");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			//System.out.println("MaestroDB.existeFactura() 1 (rs.getInt(1) "+rs.getInt(1));
			while(rs.next()) {
				if(rs.getInt(1) == id_prefactura) {
					System.out.println("MaestroDB.existeFactura() 2 rs.getInt(1) "+rs.getInt(1)+" id_prefactura "+id_prefactura);
					return true;
				}else {
					return false;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/*private int idNumeracionAutorizada1(int id_caja) {
		//select id from numeracion_autorizada where id_caja = 1 and estado = 'activo'
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id from numeracion_autorizada where id_caja = ? and estado = 'activo' order by id DESC;");
			st.setInt(1, id_caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return id_caja;
	}*/


	private int idNumeracionAutorizada(int id_caja) {
		//select id from numeracion_autorizada where id_caja = ? and estado = 'activo' order by numeracion_autorizada DESC;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id from numeracion_autorizada where id_caja = ? and estado = 'activo' order by numeracion_autorizada DESC;");
			st.setInt(1, id_caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return id_caja;
	}

	/*private int idNumeracionAutorizada(int id_caja) {
		// El sistema cambia automáticamente de resolución, cuando se agota la numeración,
		// se puede colocar la siguiente si que se haya agotado la primera,
		// si no hay resolución de reemplazo continúa con el último idNumeracionAutorizada,
		// y con el consecutivo.
		// Se debe solicitar en consecutivo las resoluciones no se pueden repetir números.

		try {
			int idNumeracionAutorizada = 0;
			double rangoFinal = 0.0;

			PreparedStatement st = conexionDB.prepareStatement("select * from numeracion_autorizada where id_caja=? and estado = 'activo' and fecha_caducidad > now() order by fecha_caducidad",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setInt(1, id_caja);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				idNumeracionAutorizada = rs.getInt("id");
				rangoFinal = rs.getDouble("rango_final");

				PreparedStatement st1 = conexionDB.prepareStatement("select id_numeracion_autorizada, max(numero) as maximoId from factura where id_caja=? and id_numeracion_autorizada=? group by id_numeracion_autorizada;",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				st1.setInt(1, id_caja);
				st1.setInt(2, idNumeracionAutorizada);
				ResultSet rs1 = st1.executeQuery();

				if(rs1.getRow() != 0) {
					while(rs1.next()) { // probar si rel ResultSet.absolute (1) está vacio.
						double maximoConscutivofactura = rs1.getDouble("maximoId");
						System.out.println("MaestroDB.idNumeracionAutorizada() antes del IF idNumeracionAutorizada "+idNumeracionAutorizada+" factura "+maximoConscutivofactura+" numeracion_autorizada rango_final "+rangoFinal+" rs1.getRow() "+rs1.getRow());
						if(maximoConscutivofactura >= rangoFinal) {
							System.out.println("MaestroDB.idNumeracionAutorizada() CON ESTE NO PUEDE FACTURAR idNumeracionAutorizada "+idNumeracionAutorizada+" factura "+maximoConscutivofactura+" numeracion_autorizada rango_final "+rangoFinal);
						}else {
							System.out.println("MaestroDB.idNumeracionAutorizada() SI SE PUEDE SI SE PUEDE FACTURAR idNumeracionAutorizada "+idNumeracionAutorizada+" factura "+maximoConscutivofactura+" numeracion_autorizada rango_final "+rangoFinal);
							return idNumeracionAutorizada;
						}
					}
				}else {
					System.out.println("MaestroDB.idNumeracionAutorizada() NO HAY VALORES EN LA idNumeracionAutorizada "+idNumeracionAutorizada+" rs1.getRow() "+rs1.getRow());
					return idNumeracionAutorizada;
				}
			}
			return idNumeracionAutorizada;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}*/

	public ArrayList<ConsultarBuscar> consultarArticulo(String text) {
		ArrayList<ConsultarBuscar> posibles = new ArrayList<ConsultarBuscar>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select codigo, nombre_largo from articulo where nombre_largo like ?  and estado = 'activo'  order by nombre_largo");
			text = text.toUpperCase();
			text = "%"+text+"%";
			st.setString(1, text);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				posibles.add(new ConsultarBuscar(rs.getString(1),rs.getString(2)));
			}
			rs.close();
			st.close();
			return posibles;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ConsultarBuscarConPrecio> consultarArticuloConPrecio(String text) {
		ArrayList<ConsultarBuscarConPrecio> alConsultarBuscarConPrecio = new ArrayList<ConsultarBuscarConPrecio>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id, codigo, nombre_largo from articulo where nombre_largo like ? OR codigo like ?  and estado = 'activo'  order by nombre_largo");
			text = text.toUpperCase();
			text = "%"+text+"%";
			st.setString(1, text);
			st.setString(2, text);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				alConsultarBuscarConPrecio.add(new ConsultarBuscarConPrecio(rs.getInt(1), rs.getString(2),rs.getString(3),0));
			}
			for (int i = 0; i < alConsultarBuscarConPrecio.size(); i++) {
				st = conexionDB.prepareStatement("select precio from lista_precio_articulo where id_articulo = ?");
				st.setInt(1, alConsultarBuscarConPrecio.get(i).getId_articulo());
				rs = st.executeQuery();
				while(rs.next()) {
					alConsultarBuscarConPrecio.get(i).setPrecio_base(rs.getDouble(1));
				}
			}
			rs.close();
			st.close();
			return alConsultarBuscarConPrecio;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ConsultarClienteDomicilio> consultarClienteDomicilio(String text) {
		//select * from cliente where nombre like '%300%' or apellido like '%300%' or telefono like '%300%'
		ArrayList<ConsultarClienteDomicilio> alConsultarClienteDomicilio = new ArrayList<ConsultarClienteDomicilio>();		
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from cliente where nombre like ? or apellido like ? or telefono like ?");
			text = text.toUpperCase();
			text = "%"+text+"%";
			st.setString(1, text);
			st.setString(2, text);
			st.setString(3, text);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				alConsultarClienteDomicilio.add(new ConsultarClienteDomicilio(rs.getInt(1),rs.getDouble(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(15)));
			}
			rs.close();
			st.close();
			return alConsultarClienteDomicilio;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Proveedor> consultarProveedorPagos(String text) {
		//select * from proveedor where (nombre like '%AL%' or nombre_comercial like '%AL%') AND
		ArrayList<Proveedor> alProveedor = new ArrayList<Proveedor>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM proveedor WHERE (nombre LIKE ? or nombre_comercial LIKE ?) AND estado = 'activo';");
			text = text.toUpperCase();
			text = "%"+text+"%";
			st.setString(1, text);
			st.setString(2, text);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				alProveedor.add(new Proveedor(rs.getInt("id"), rs.getString("codigo"), rs.getString("id_tipo_documento"), rs.getLong("documento"),
						rs.getInt("digito_verificacion"), rs.getString("nombre"), rs.getString("nombre_comercial"), rs.getString("representante_legal"),
						rs.getString("direccion"), rs.getInt("id_ciudad"), rs.getString("telefono"), rs.getString("email"), rs.getString("web"),
						rs.getInt("id_regimen"), rs.getDouble("valor_minimo_compra"), rs.getString("descripcion"),rs.getString("estado"), 
						rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion")));
			}
			rs.close();
			st.close();
			return alProveedor;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Tercero consultarTercero(String documentoTercero) {
		Tercero tercero = new Tercero();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from tercero where documento = ? and estado = 'activo'");
			documentoTercero = documentoTercero.replace(",", "");
			if(documentoTercero.equals(""))documentoTercero="0";

			System.out.println("MaestroDB.consultarTercero() documentoTercero "+documentoTercero);

			long lDocumento = Long.parseLong(documentoTercero);
			st.setLong(1, lDocumento);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				tercero.setId(rs.getInt(1));
				tercero.setId_tipo_documento(rs.getString(2));
				tercero.setDocumento(rs.getLong(3));
				tercero.setNombre(rs.getString(4));
				tercero.setApellido(rs.getString(5));
				tercero.setDireccion(rs.getString(6));
				tercero.setId_ciudad(rs.getInt(7));
				tercero.setTelefono(rs.getString(8));
				tercero.setEmail(rs.getString(9));
				tercero.setId_regimen(rs.getInt(10));
				tercero.setComentario(rs.getString(11));
				tercero.setFecha_ingreso(rs.getDate(12));
				tercero.setEstado(rs.getString(13));
				tercero.setId_lista_precio(rs.getInt(16));
				rs.close();
				st.close();
				return tercero;
			}else{
				rs.close();
				st.close();
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarTercero() NumberFormatException");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarTercero() SQLException");
			e.printStackTrace();
			return null;
		}		
	}

	public Tercero consultarTercero(int id_cliente) {
		Tercero tercero = new Tercero();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from tercero where id = ? and estado = 'activo'");
			st.setInt(1, id_cliente);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				tercero.setId(rs.getInt(1));
				tercero.setId_tipo_documento(rs.getString(2));
				tercero.setDocumento(rs.getLong(3));
				tercero.setNombre(rs.getString(4));
				tercero.setApellido(rs.getString(5));
				tercero.setDireccion(rs.getString(6));
				tercero.setId_ciudad(rs.getInt(7));
				tercero.setTelefono(rs.getString(8));
				tercero.setEmail(rs.getString(9));
				tercero.setId_regimen(rs.getInt(10));
				tercero.setComentario(rs.getString(11));
				tercero.setFecha_ingreso(rs.getDate(12));
				tercero.setEstado(rs.getString(13));
				tercero.setId_lista_precio(rs.getInt(16));
				rs.close();
				st.close();
				return tercero;
			}else{
				rs.close();
				st.close();
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarTercero() NumberFormatException");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarTercero() SQLException");
			e.printStackTrace();
			return null;
		}		
	}

	public Cliente consultarCliente(String documentoCliente) {
		Cliente cliente = new Cliente();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from cliente where documento = ? and estado = 'activo'");
			if(documentoCliente.equals("")) {
				documentoCliente="0";
			}else {
				if(documentoCliente.indexOf(".")!=-1)
					documentoCliente = documentoCliente.substring(0, documentoCliente.indexOf("."));
			}
			System.out.println("MaestroDB.consultarCliente() documentoCliente "+documentoCliente);

			long lDocumento = Long.parseLong(documentoCliente);
			st.setLong(1, lDocumento);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				cliente.setId(rs.getInt(1));
				cliente.setDocumento(lDocumento);
				cliente.setNombre(rs.getString(3));
				cliente.setApellido(rs.getString(4));
				cliente.setDireccion(rs.getString(5));
				cliente.setId_ciudad(rs.getInt(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));
				cliente.setCumpleanos(rs.getString(9));
				cliente.setComentario(rs.getString(10));
				cliente.setFecha_ingreso(rs.getDate(11));
				cliente.setEstado(rs.getString(12));
				cliente.setId_lista_precio(rs.getInt(15));
				rs.close();
				st.close();
				return cliente;
			}else{
				rs.close();
				st.close();
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarCliente() NumberFormatException");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarCliente() SQLException");
			e.printStackTrace();
			return null;
		}
	}

	public Cliente consultarCliente(int id_cliente) {
		Cliente cliente = new Cliente();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from cliente where id = ? and estado = 'activo'");
			st.setInt(1, id_cliente);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				cliente.setId(rs.getInt(1));
				cliente.setDocumento((long)rs.getDouble(2));
				cliente.setNombre(rs.getString(3));
				cliente.setApellido(rs.getString(4));
				cliente.setDireccion(rs.getString(5));
				cliente.setId_ciudad(rs.getInt(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));
				cliente.setCumpleanos(rs.getString(9));
				cliente.setComentario(rs.getString(10));
				cliente.setFecha_ingreso(rs.getDate(11));
				cliente.setEstado(rs.getString(12));
				cliente.setId_lista_precio(rs.getInt(15));
				rs.close();
				st.close();
				return cliente;
			}else{
				rs.close();
				st.close();
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarCliente() NumberFormatException");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarCliente() SQLException");
			e.printStackTrace();
			return null;
		}
	}

	public void agregarClienteTercero(int id_cliente,int idPreFacturaActual,int id_caja) {
		try {
			String query = "UPDATE prefactura SET id_cliente=? WHERE id = ? and id_caja = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_cliente);
			st.setInt(2, idPreFacturaActual);
			st.setInt(3, id_caja); 
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			int itemIdPrefactura = 0;
			boolean blnResultado = false;
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}*/
			System.out.println("MaestroDB.agregarClienteTercero() id_cliente "+id_cliente+" idPreFacturaActual "+idPreFacturaActual+" id_caja "+id_caja);
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarClienteTercero() ERROR "+e);
			e.printStackTrace();
		}
	}

	public void actualizaClienteoTerceroEnPreFactura(int id_prefactura, int id_cliente, int id_caja) {
		// actualiza cliente o tercero en la preFactura cunado previamente se a seleccionado el cliente o tercero
		try {
			//UPDATE prefactura SET estado = 'liqidada' WHERE id=169 and id_caja = 1	
			String query = "UPDATE prefactura SET id_cliente=? WHERE id=? and id_caja = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_cliente);
			st.setInt(2, id_prefactura);
			st.setInt(3, id_caja);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
						System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR "+e);
			e.printStackTrace();
		}
	}

	public int traeDocumento(int idPreFacturaActual,int id_caja) {
		try {
			// trae el id_cliente registrado en la prefactura
			int id_cliente = 0;
			PreparedStatement st = conexionDB.prepareStatement("Select id_cliente from preFactura where id = ? and id_caja = ?");
			st.setInt(1, idPreFacturaActual);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				id_cliente = rs.getInt(1);
			}
			rs.close();
			st.close();
			return id_cliente;

		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.traeDocumento() ERROR NumberFormatException "+e);
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			System.out.println("MaestroDB.traeDocumento() SQLException ERROR "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public String consultarIdVendedor(int codigo) {
		try {
			// Validar si el vendedor existe y está activo
			// Select * from vendedor where codigo = 51 and estado = 'activo'
			String id_usuario = "";
			PreparedStatement st = conexionDB.prepareStatement("Select * from vendedor where codigo = ? and estado = 'activo'");
			st.setInt(1, codigo);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				id_usuario = rs.getString(4);
			}
			rs.close();
			st.close();
			return id_usuario;

		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarVendedor() NumberFormatException "+e);
			return "bad";
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarVendedor() SQLException "+e);
			return "bad";
		}
	}

	public String consultarNombreVendedor(int codigo){
		//if(!conectarDB()) conectarDB();
		try {
			// Validar si el vendedor existe y está activo
			// Select * from vendedor where codigo = 51 and estado = 'activo'
			String id_usuario = "";
			PreparedStatement st = conexionDB.prepareStatement("Select * from vendedor where codigo = ? and estado = 'activo'");
			st.setInt(1, codigo);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				id_usuario = rs.getString(4);
				// Tomar nombre y apellido para mostrar 
				// Select * from usuario where id = 'CGRANADA'
				String nombreVendedor = "";
				st = conexionDB.prepareStatement("Select * from usuario where id = ?");
				st.setString(1, id_usuario);
				rs = st.executeQuery();
				if(rs.next()) {
					nombreVendedor = rs.getString(4)+" "+rs.getString(5);
					rs.close();
					st.close();
					return nombreVendedor;
				}
				rs.close();
				st.close();
				return "bad";
			}else{
				System.out.println("MaestroDB.consultarVendedor() NO ENCONTRÓ NADA");
				rs.close();
				st.close();
				return "bad";
			}

		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.consultarVendedor() NumberFormatException "+e);
			return "bad";
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarVendedor() SQLException "+e);
			return "bad";
		}
	}

	public String cargarUnParametro(String parametro) {
		// Permite leer el parametro de la base de datos y devuelve su valor.
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from parametro where parametro = ? ");
			st.setString(1, parametro);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){			
				return rs.getString(3);
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	public double totalFactura(int id_prefactura,int id_caja) {
		//select * from item_prefactura where id_prefactura = 270
		//if(!conectarDB()) conectarDB();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_prefactura where id_prefactura = ? and id_caja = ?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			double totalFactura = 0;
			while (rs.next() ){
				totalFactura = totalFactura + rs.getDouble(11)*rs.getDouble(12);
			}

			try {
				//UPDATE item_prefactura SET item = 3 WHERE id_caja = 1 and id_prefactura = 81
				String query = "UPDATE prefactura SET valor_prefactura = ? WHERE id = ?;";
				st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setDouble(1,totalFactura);
				st.setInt(2, id_prefactura);
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.totalFactura() ERROR "+e);
				e.printStackTrace();
			}
			rs.close();
			st.close();
			return totalFactura;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double subTotalSinImpuestos(int id_prefactura, int id_caja) {
		//select id_prefactura ,valor_unidad,valor_iva, valor_unidad/(1+(valor_iva/100)) as subtotal from item_prefactura where id_prefactura = 151
		//if(!conectarDB()) conectarDB();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select sum(valor_item) from item_prefactura where id_prefactura = ? and id_caja = ? group by id_prefactura");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			double totalSinImpuestos = 0;
			while (rs.next()){
				totalSinImpuestos = rs.getDouble(1) - impuestos(id_prefactura, id_caja);
			}
			rs.close();
			st.close();
			return totalSinImpuestos;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double impuestos(int id_prefactura, int id_caja) {
		//select id_prefactura ,valor_unidad,valor_iva, valor_unidad/(1+(valor_iva/100)) as subtotal from item_prefactura where id_prefactura = 151
		//if(!conectarDB()) conectarDB();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select sum(valor_iva) from item_prefactura where id_prefactura = ? and id_caja = ? group by id_prefactura");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			double totalImpuestos = 0;
			while (rs.next()){
				totalImpuestos = rs.getDouble(1);
			}

			try {
				//UPDATE prefactura SET valor_iva=15008.40 WHERE id = 154;
				String query = "UPDATE prefactura SET valor_iva = ? WHERE id = ?;";
				st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setDouble(1,totalImpuestos);
				st.setInt(2, id_prefactura);
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.impuestos() ERROR "+e);
				e.printStackTrace();
			}
			rs.close();
			st.close();
			return totalImpuestos;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String traeNombre(int id_articulo) {
		try {
			String nombre = "";
			PreparedStatement st = conexionDB.prepareStatement("select nombre from articulo where id = ?;");
			st.setInt(1, id_articulo);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				nombre = rs.getString(1);
			}
			rs.close();
			st.close();
			return nombre;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int traerItem(int id_caja, int idPreFacturaActual) {
		//select MAX(item) from item_prefactura where id_caja = 1 and id_prefactura = 85
		//TODO se debe tener en cuenta cuando se borra la base de datos para iniciar facturacion
		try {
			int item = 0;
			//PreparedStatement st = conexionDB.prepareStatement("select MAX(item) from item_prefactura where id_caja = ? and id_prefactura = ?;");
			PreparedStatement st = conexionDB.prepareStatement("select COUNT(*) from item_prefactura where id_caja = ? and id_prefactura = ?;");
			st.setInt(1, id_caja);
			st.setInt(2, idPreFacturaActual);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				item = rs.getInt(1);
			}
			rs.close();
			st.close();
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<ItemPreFactura> traerItemPreFactura(int id_caja,int id_prefactura) {
		//select * from item_prefactura where id_prefactura = 68 and id_caja = 1
		ArrayList<ItemPreFactura> itemsPreFactura = new ArrayList<ItemPreFactura>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_prefactura where id_prefactura = ? and id_caja = ?;");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				itemsPreFactura.add(new ItemPreFactura(rs.getInt(5),rs.getString(8),rs.getDouble(11),rs.getDouble(12),rs.getDouble(22),rs.getInt(7),rs.getDouble(19)));
			}
			rs.close();
			st.close();
			return itemsPreFactura;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean agregarItemPreFactura(int id_caja,int id_prefactura,int item, int id_vendedor,String id_usuario_vendedor, 
			int id_articulo,String codigo_articulo,String codigo_articulo_venta, String nombre_provisional, 
			double cantidad_unidad_medida, double valor_unidad,double valor_anterior,int id_presentacion, 
			double cantidad_presentacion, double valor_presentacion,double valor_item,int id_base_iva, 
			double valor_iva,double porcentaje_descuento,double valor_descuento,int item_promocion){

		try {
			String query = "INSERT INTO item_prefactura(id_caja, id_prefactura, item, id_vendedor, id_usuario_vendedor,"
					+ "id_articulo, codigo_articulo, codigo_articulo_venta, nombre_provisional,cantidad_unidad_medida, "
					+ "valor_unidad, valor_anterior, id_presentacion,cantidad_presentacion, valor_presentacion, "
					+ "valor_item, id_base_iva,valor_iva, porcentaje_descuento, valor_descuento, item_promocion)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_caja);
			st.setInt(2, id_prefactura);
			st.setInt(3, item); 
			st.setInt(4, id_vendedor);
			st.setString(5, id_usuario_vendedor); 
			st.setInt(6, id_articulo);
			st.setString(7,codigo_articulo);
			st.setString(8, codigo_articulo_venta); 
			st.setString(9, nombre_provisional); 
			st.setDouble(10, cantidad_unidad_medida);
			st.setDouble(11, valor_unidad);
			st.setDouble(12, valor_anterior);
			st.setInt(13, id_presentacion); 
			st.setDouble(14, cantidad_presentacion);
			st.setDouble(15, valor_presentacion);
			st.setDouble(16, valor_item);
			st.setInt(17, id_base_iva); 
			st.setDouble(18, valor_iva);
			st.setDouble(19, porcentaje_descuento);
			st.setDouble(20, valor_descuento);
			st.setInt(21, item_promocion);
			System.out.print("MaetroDB ******************");
			st.executeUpdate();
			ResultSet rsKey = st.getGeneratedKeys();
			int itemIdPrefactura = 0;
			boolean blnResultado = false;
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}
			System.out.println("MaestroDB.agregarItemPreFactura() itemIdPrefactura "+itemIdPrefactura);
			conexionDB.commit();
			st.close();
			return blnResultado;
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarItemPreFactura() ERROR "+e);
			e.printStackTrace();
			return false;
		}
	}

	public void borraItemPreFactura(int borraId_PreFactura, int rowDelete, int id_caja) {
		int numeroRegistros = traerItem(id_caja, borraId_PreFactura);
		System.err.println("MaestroDB.borraItemPreFactura()"+" borraId_PreFactura "+borraId_PreFactura+" rowDelete "
				+ rowDelete+" id_caja "+id_caja+" cantidadRegistros "+numeroRegistros);
		//borraId_PreFactura 81 rowDelete 1 id_caja 1 cantidadRegistros 3
		try {
			//DELETE FROM item_prefactura WHERE id_caja = 1 and id_prefactura = 68 and item = 3
			rowDelete++;
			String query = "DELETE FROM item_prefactura WHERE id_caja = ? and id_prefactura = ? and item = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja);
			st.setInt(2, borraId_PreFactura);
			st.setInt(3, rowDelete);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			System.out.println("MaestroDB.borraItemPreFactura() DELETE ResultSet "+rsKey);*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.borraItemPreFactura() ERROR "+e);
			e.printStackTrace();
		}

		while (rowDelete <= numeroRegistros) {
			try {
				//UPDATE item_prefactura SET item = 3 WHERE id_caja = 1 and id_prefactura = 81
				rowDelete++;
				String query = "UPDATE item_prefactura SET item = ? WHERE id_caja = ? and id_prefactura = ? and item = ?;";
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, (rowDelete-1));
				st.setInt(2, id_caja);
				st.setInt(3, borraId_PreFactura);
				st.setInt(4, (rowDelete));
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.borraItemPreFactura() ERROR "+e);
				e.printStackTrace();
			}
		}		
	}

	public ArrayList<ItemPreFormaPago> traerFormaPago(int id_caja, int id_prefactura) {
		//select * from medio_pago_prefactura where id_caja = 1 and id_prefactura = 105 order by id DESC
		//if(!conectarDB()) conectarDB();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from medio_pago_prefactura where id_caja = ? and id_prefactura = ?");
			st.setInt(1, id_caja);
			st.setInt(2, id_prefactura);
			ResultSet rs = st.executeQuery();
			ArrayList<ItemPreFormaPago> mediosPagos = new ArrayList<ItemPreFormaPago>();
			while (rs.next() ){
				mediosPagos.add(new ItemPreFormaPago(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),
						rs.getInt(5),rs.getInt(6),rs.getLong(7),rs.getLong(8),
						rs.getInt(9),rs.getDouble(10),rs.getDouble(11),rs.getDouble(12),
						rs.getDouble(13),rs.getDouble(14)));
			}
			rs.close();
			st.close();
			return mediosPagos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void borraItemFormaPago(int borraId_PreFormaPago, int rowDeleteFormaPago, int id_caja) {
		int numeroRegistros = traerItemFormaPgo(id_caja, borraId_PreFormaPago);
		System.out.println("MaestroDB.borraItemFormaPago()"+" borraId_PreFactura "+borraId_PreFormaPago+" rowDeleteFormaPago "
				+ rowDeleteFormaPago+" id_caja "+id_caja+" cantidadRegistros "+numeroRegistros);
		//borraId_PreFactura 81 rowDelete 1 id_caja 1 cantidadRegistros 3
		try {
			//DELETE FROM medio_pago_prefactura WHERE id_caja = 1 and id_prefactura = 151 and item = 1;
			rowDeleteFormaPago++;
			String query = "DELETE FROM medio_pago_prefactura WHERE id_caja = ? and id_prefactura = ? and item = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja);
			st.setInt(2, borraId_PreFormaPago);
			st.setInt(3, rowDeleteFormaPago);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			System.out.println("MaestroDB.borraItemPreFactura() DELETE ResultSet "+rsKey);*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.borraItemPreFactura() ERROR "+e);
			e.printStackTrace();
		}

		while (rowDeleteFormaPago <= numeroRegistros) {
			try {
				//UPDATE item_prefactura SET item = 3 WHERE id_caja = 1 and id_prefactura = 81
				rowDeleteFormaPago++;
				String query = "UPDATE medio_pago_prefactura SET item = ? WHERE id_caja = ? and id_prefactura = ? and item = ?;";
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, (rowDeleteFormaPago-1));
				st.setInt(2, id_caja);
				st.setInt(3, borraId_PreFormaPago);
				st.setInt(4, (rowDeleteFormaPago));
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.borraItemPreFactura() ERROR "+e);
				e.printStackTrace();
			}
		}		
	}

	public int traerItemFormaPgo(int id_caja, int idPreFacturaActual) {
		//select MAX(item) from medio_pago_prefactura where id_caja = 1 and id_prefactura = 105
		try {
			int item = 0;
			PreparedStatement st = conexionDB.prepareStatement("select MAX(item) from medio_pago_prefactura where id_caja = ? and id_prefactura = ?;");
			st.setInt(1, id_caja);
			st.setInt(2, idPreFacturaActual);

			//System.out.println("MaestroDB.traerItemFormaPgo() id_caja "+id_caja+" idPreFacturaActual "+idPreFacturaActual);

			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				item = rs.getInt(1);
			}
			rs.close();
			st.close();
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean agragarFormaPago(int id_caja,int id_prefactura,int id_medio_pago,int item,int id_entidad_bancaria,
			int tarjeta,int autorizacion,int numero_recibo,double valor_pago,double porcentaje_comision,
			double valor_comision,double valor_iva,double efectivo_recibido,Date fecha,String accion ) {
		try {
			String query = "INSERT INTO medio_pago_prefactura(id_caja, id_prefactura, id_medio_pago, "
					+ "item, id_entidad_bancaria,tarjeta, autorizacion, numero_recibo, valor_pago, "
					+ "porcentaje_comision,valor_comision, valor_iva, efectivo_recibido, dg_fecha_accion, dg_accion) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja);
			st.setInt(2, id_prefactura);
			st.setInt(3,id_medio_pago);
			st.setInt(4,item);
			st.setInt(5,id_entidad_bancaria);
			st.setInt(6,tarjeta);
			st.setInt(7,autorizacion);
			st.setInt(8,numero_recibo);
			st.setDouble(9, valor_pago);
			st.setDouble(10,porcentaje_comision);
			st.setDouble(11,valor_comision);
			st.setDouble(12,valor_iva);
			st.setDouble(13,efectivo_recibido);
			st.setTimestamp(14, (Timestamp) fecha);
			st.setString(15, accion);
			st.executeUpdate();
			ResultSet rsKey = st.getGeneratedKeys();
			int itemIdPrefactura = 0;
			boolean blnResultado = false;
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}
			System.out.println("MaestroDB.agragarFormaPago() itemIdPrefactura "+itemIdPrefactura);
			conexionDB.commit();
			st.close();
			return blnResultado;
		} catch (SQLException e) {
			System.out.println("MaestroDB.agragarFormaPago() ERROR "+e);
			e.printStackTrace();
			return false;
		}
	}

	public String nombreMedioPago(int id_medio_pago) {
		// select * from medio_pago where estado = 'activo'
		////if(!conectarDB()) conectarDB();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select nombre from medio_pago where id = ? and estado = 'activo';");
			st.setInt(1, id_medio_pago);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				return rs.getString(1);
			}else{
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public int maximoPreFactura() {
		// select MAX(id) from prefactura
		try {
			PreparedStatement st = conexionDB.prepareStatement("select MAX(id) from prefactura");
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next() ){
				id = rs.getInt(1);
				System.out.println("MaestroDB.maximoPreFactura() id "+id);
				rs.close();
				st.close();
				return id;
			}else{
				rs.close();
				st.close();
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double maximoFactura() {
		// select MAX(id) from prefactura
		try {
			PreparedStatement st = conexionDB.prepareStatement("select MAX(numero) from factura");
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				return rs.getDouble(1);
			}else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void anulaPreFactura(int borraId_PreFactura, int id_caja) {
		try {
			String query = "UPDATE prefactura SET estado='anulado' WHERE id=? and id_caja=?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, borraId_PreFactura);
			st.setInt(2, id_caja);
			st.executeUpdate();
			st.getGeneratedKeys();
			//ResultSet rsKey = st.getGeneratedKeys();
			/*int itemIdPrefactura = 0;
			boolean blnResultado = false;
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}
			System.out.println("MaestroDB.anulaPreFactura() borraId_PreFactura "+borraId_PreFactura+" id_caja "+id_caja+" itemIdPrefactura "+itemIdPrefactura+" blnResultado "+blnResultado);*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.anulaPreFactura() ERROR "+e);
			e.printStackTrace();
		}		
	}

	public int agregarPreFactura (int id_caja,double numero, int auxiliar,int id_almacen,
			Date fecha,int id_cajero,int id_vendedor, String id_usuario_vendedor,
			int id_cliente, int id_agente_externo, double valor_prefactura,double valor_descuento,
			double valor_iva, String comentario, String estado) {

		try {
			String query = "INSERT INTO prefactura(id_caja, numero, auxiliar, id_almacen, fecha, id_cajero, id_vendedor, "
					+ "id_usuario_vendedor, id_cliente, id_agente_externo, valor_prefactura, valor_descuento, valor_iva, "
					+ "comentario, estado) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja);
			st.setDouble(2, numero);
			st.setInt(3, auxiliar);
			st.setInt(4, id_almacen);
			st.setTimestamp(5, (Timestamp) fecha);
			st.setInt(6, id_cajero);
			st.setInt(7, id_vendedor); 
			st.setString(8, id_usuario_vendedor);
			st.setInt(9,id_cliente); 
			st.setInt(10, id_agente_externo); 
			st.setDouble(11, valor_prefactura);
			st.setDouble(12, valor_descuento);
			st.setDouble(13, valor_iva); 
			st.setString(14, comentario); 
			st.setString(15, estado);

			st.executeUpdate();
			ResultSet rsKey = st.getGeneratedKeys();
			int idPrefactura = 0;
			while(rsKey.next()) {
				idPrefactura = rsKey.getInt(1);
			}
			//System.out.println("MaestroDB.agregarPreFactura()Id generado: " + idPrefactura);
			conexionDB.commit();
			st.close();
			return idPrefactura;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MaestroDB.agregarPreFactura() ERROR " + e);
			return 0;
		}
	}

	public ArticuloFactura buscarArticulo(String codigoArticulo, int listaPrecioClienteTercero, int codigoVendedor, String cantidad) {
		ArticuloFactura articuloFactura = new ArticuloFactura();
		try {
			PreparedStatement st;
			ResultSet rs;
			int idArticulo = 0;

			articuloFactura.setCodigoVendedor(codigoVendedor);

			// Seleciona el id del almacen
			int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen(); // id_almacen = 1
			if (id_almacen != 0){
				articuloFactura.setId_almacen(id_almacen);
			}else{
				articuloFactura.setId_almacen(1);
			}

			// Si no trae lista de precios del cliente o tercero, establece la lista de precio del almacen
			// Si no hay lista de precios del almacen toma la BASE con id = 1
			// select id_lista_precio from lista_precio_almacen where id_almacen = 1 and estado = 'activo'
			if(listaPrecioClienteTercero == 0) {
				st = conexionDB.prepareStatement("select id_lista_precio from lista_precio_almacen where id_almacen = ? and estado = 'activo'");
				st.setInt(1, id_almacen);
				rs = st.executeQuery();
				if (rs.next()){
					articuloFactura.setId_lista_precio(rs.getInt(1)); // Id_lista_precio = 2
				}else{
					articuloFactura.setId_lista_precio(1);
				}
			}else {
				articuloFactura.setId_lista_precio(listaPrecioClienteTercero);
			}

			//Valida si existe el producto trayendo el id del artículo
			st = conexionDB.prepareStatement("select id_articulo from codigo_articulo where codigo = ? and estado = 'activo'");
			st.setString(1, codigoArticulo);
			rs = st.executeQuery();
			if (rs.next()){
				articuloFactura.setId_articulo(rs.getInt(1));
			}else{
				return articuloFactura = null;
				//articuloFactura.setId_articulo(0);
			}
			articuloFactura.setCodigoArticuloPedido(codigoArticulo);
			idArticulo = articuloFactura.getId_articulo();

			// Con el id del artículo verifica cuantos códigos alternos tiene
			//select COUNT(*) from codigo_articulo where id_articulo = 18
			st = conexionDB.prepareStatement("select COUNT(*) from codigo_articulo where id_articulo = ? and estado = 'activo'");
			st.setInt(1, idArticulo);
			rs = st.executeQuery();
			int cantidadCodigoAlterno = 0;
			while(rs.next()){
				cantidadCodigoAlterno = rs.getInt(1);
			}
			String[] alternos = new String[cantidadCodigoAlterno];

			// Con el id del artículo verifica si tiene códigos alternos y cuales son.
			st = conexionDB.prepareStatement("select * from codigo_articulo where id_articulo = ? and estado = 'activo' order by posicion");
			st.setInt(1, idArticulo);
			rs = st.executeQuery();
			int i = 0;
			while(rs.next()){
				alternos[i] = rs.getString(1);
				i=i+1;
			}
			articuloFactura.setAlternos(alternos);

			// Devuelve todos los datos para facturar
			// select * from articulo where codigo = '134106' and estado = 'activo'
			st = conexionDB.prepareStatement("select * from articulo where id = ? and estado = 'activo'");
			st.setInt(1, idArticulo);
			rs = st.executeQuery();
			if (rs.next()){
				articuloFactura.setId_articulo(rs.getInt(1));
				articuloFactura.setNombreArticulo(rs.getString(2));
				articuloFactura.setNombreArticuloLargo(rs.getString(3));
				articuloFactura.setId_Unidad_Medida(rs.getInt(5));
				articuloFactura.setId_base_iva(rs.getInt(7));
				articuloFactura.setCodigoArticulo(rs.getString(8));
			}
			

			/* verifico cual lista de precio se debe usar 
- select id_lista_precio from tercero where id = 3 and documento = 900123456 and estado='activo' /*con el documento del tercero me da el id_lista_precio 
si no tiene lista de precio en tercero lo busca en cliente 
- select id_lista_precio from cliente where id = 11 and documento = 108 and estado='activo' /* con el dcumento del cliente me da el id_lista_precio
si no tiene lista de precio en cliente toma la del almacen el id_almacen se encuentra en la licencia que está en C:\tmp\lic\serv
- select id_lista_precio from lista_precio_almacen where id_almacen = 1 and estado = 'activo'
si no tiene lista de precios en el almacen toma la lista BASE
- select * from lista_precio /* La lista de precios BASE siempre es la 1
Con la lista de precios establecida se va a la lista_precio_articulo
El caso de los huevos un códgo por unidad y un código por empaque de 30, maneja descuentos por cantidad
- select * from articulo where codigo = '1272.01'
- select * from codigo_articulo where id_articulo = 211
verifico que el producto tenga precio en la lista de precios seleccionada
- select COUNT(*) from lista_precio_articulo where id_articulo = 211 and id_lista_precio = 2
si la respuesta es 0 se dirige a la base o sea la 1, el producto no tiene descuento por cantidad en esa lista de precios
si es >=1, con la lista de precios y el id sel articulo se conoce si tiene descuentos por cantidad
- select * from lista_precio_articulo where id_articulo = 211 and id_lista_precio = 1 order by cantidad DESC
Tomo la cantidad que compra el cliente y la comparo con la cantidad mayor
select * from lista_precio_articulo where id_articulo = 211 and id_lista_precio = 1*/

			//toma la cantidad
			double dCantidad = 0;
			if(cantidad.equals("")) {
				articuloFactura.setCantidad(1);
			}else {
				dCantidad = Double.valueOf(cantidad);
				articuloFactura.setCantidad(dCantidad);
			}

			// Toma el precio correspondiente a la lista de precio
			// select * from lista_precio_articulo where id_articulo = 137 and id_lista_precio = 2 and estado = 'activo'
			st = conexionDB.prepareStatement("select * from lista_precio_articulo where id_articulo = ? and id_lista_precio = ? and estado = 'activo' order by cantidad DESC");
			st.setInt(1, articuloFactura.getId_articulo());
			st.setInt(2, articuloFactura.getId_lista_precio());
			rs = st.executeQuery();
			boolean condicionDeParada = true;
			while (rs.next()) {
				if(articuloFactura.getCantidad()>=rs.getDouble(4) && condicionDeParada) {
					//System.err.println("MaestroDB.buscarArticulo() 1 DESCUENTO POR CANTIDAD condicionDeParada "+condicionDeParada);
					articuloFactura.setValorUnitario(rs.getDouble(5));
					condicionDeParada = false;
				}
				// si la cantidad del artículo es menor a 1 como lo vendido en KG
				if(articuloFactura.getCantidad()>0 && articuloFactura.getCantidad()<1) {
					articuloFactura.setValorUnitario(rs.getDouble(5));
					condicionDeParada = false;
				}	
			}
			// Si el artículo no existe en la lista de precio del tercero, cliente o almacén repite la busqueda con la lista de precios 1 = BASE
			if(articuloFactura.getValorUnitario()==0) {
				st = conexionDB.prepareStatement("select * from lista_precio_articulo where id_articulo = ? and id_lista_precio = ? and estado = 'activo' order by cantidad DESC");
				st.setInt(1, articuloFactura.getId_articulo());
				st.setInt(2, 1);
				rs = st.executeQuery();
				while (rs.next()) {
					if(articuloFactura.getCantidad()>=rs.getDouble(4) && condicionDeParada) {
						//System.err.println("MaestroDB.buscarArticulo() 3 DESCUENTO POR CANTIDAD condicionDeParada "+condicionDeParada);
						articuloFactura.setValorUnitario(rs.getDouble(5));
						condicionDeParada = false;
					}
					// si la cantidad del artículo es menor a 1 como lo vendido en KG
					if(articuloFactura.getCantidad()>0 && articuloFactura.getCantidad()<1) {
						articuloFactura.setValorUnitario(rs.getDouble(5));
						condicionDeParada = false;
					}	
				}
			}

			//Coloca el valor total del artículo su precio por la cantidad
			articuloFactura.setTotalPrecio(articuloFactura.getCantidad()*articuloFactura.getValorUnitario());

			// Toma el nombre de la unidad de medida
			// select nombre from unidad_medida where id = 2 and estado = 'activo'
			st = conexionDB.prepareStatement("select * from unidad_medida where id = ? and estado = 'activo'");
			st.setInt(1, articuloFactura.getId_Unidad_Medida());
			rs = st.executeQuery();
			if (rs.next()){
				articuloFactura.setNombreUnidadMedida(rs.getString(3));
			}

			// Toma el valor del IVA
			st = conexionDB.prepareStatement("select * from base_iva where id = ? and estado = 'activo'");
			st.setInt(1, articuloFactura.getId_base_iva());
			rs = st.executeQuery();
			if (rs.next()){
				//System.out.println("MaestroDB.buscarArticulo() LINE 2105 BASE "+rs.getDouble(2)+" VALOR UNITARIO "+articuloFactura.getValorUnitario()+" CANTIDAD "+articuloFactura.getCantidad()+" IVA2 IVA2 IVA2 IVA2 IVA2 IVA2");
				articuloFactura.setIva((rs.getDouble(2)/100)* articuloFactura.getValorUnitario()/(1+(rs.getDouble(2)/100)) * articuloFactura.getCantidad());
			}

			//TODO presentación es como compra - empaque como vende
			articuloFactura.setId_presentacion(1);

			rs.close();
			st.close();
			return articuloFactura;
		} catch (SQLException e) {
			System.out.println("MaestroDB.buscarArticulo() 3 ERROR "+e);
			//LOG.severe("MaestroBD buscarArticulo ERROR "+e);
			e.printStackTrace();
			return articuloFactura;
		}
	}

	public double precioUnitario(int listaPrecioClienteTercero, int id_articulo, double dCantidad, ArticuloFactura articuloFactura) {
		// Toma el precio correspondiente a la lista de precio
		// select * from lista_precio_articulo where id_articulo = 137 and id_lista_precio = 2 and estado = 'activo'
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from lista_precio_articulo where id_articulo = ? and id_lista_precio = ? and estado = 'activo' order by cantidad DESC");
			st.setInt(1, id_articulo);
			st.setInt(2, listaPrecioClienteTercero);
			ResultSet rs = st.executeQuery();
			rs = st.executeQuery();
			boolean condicionDeParada = true;
			while (rs.next()) {
				if(articuloFactura.getCantidad()>=rs.getDouble(4) && condicionDeParada) {
					condicionDeParada = false;
					return rs.getDouble(5);
				}	
			}
			// Si el artículo no existe en la lista de precio del tercero, cliente o almacén repite la busqueda con la lista de precios 1 = BASE
			if(articuloFactura.getValorUnitario()==0) {
				st = conexionDB.prepareStatement("select * from lista_precio_articulo where id_articulo = ? and id_lista_precio = ? and estado = 'activo' order by cantidad DESC");
				st.setInt(1, articuloFactura.getId_articulo());
				st.setInt(2, 1);
				rs = st.executeQuery();
				while (rs.next()) {
					if(articuloFactura.getCantidad()>=rs.getDouble(4) && condicionDeParada) {
						condicionDeParada = false;
						return rs.getDouble(5);
					}	
				}
			}
			return 0;
		} catch (SQLException e) {
			System.out.println("MaestroDB.precioUnitario() ERROR "+e);
			e.printStackTrace();
			return 0;	
		}
	}

	public boolean consultaCajero(String idUsuario, char[] password){
		try {
			String strPassword = new String(password);
			idUsuario = idUsuario.toUpperCase();
			CryptCadena cryptCadena = new CryptCadena(idUsuario);
			String strPasswordEnc = cryptCadena.encrypt(strPassword);

			// Validar la existencia del cajero en la tabla usuario
			String validarUsuario = "";
			PreparedStatement st = conexionDB.prepareStatement("select * from usuario where id = ? and password = ?");
			st.setString(1, idUsuario);
			st.setString(2, strPasswordEnc);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				validarUsuario = rs.getString(1);
				G.getInstance().cajero = rs.getString(4)+" "+rs.getString(5);
			}

			// validar la existencia del cajero y esté activo en la tabla cajero
			String validarCajero = "";
			st = conexionDB.prepareStatement("Select * from cajero where id_usuario = ? and estado = ?");
			st.setString(1, idUsuario);
			st.setString(2, "activo");
			rs = st.executeQuery();
			if(rs.next()){
				G.getInstance().getIdCajero = rs.getInt(1);
				validarCajero = rs.getString(3);
			}
			rs.close();
			st.close();

			if(validarUsuario.equals(idUsuario) && validarCajero.equals(idUsuario)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println("MaestroDB.consultaCajero() "+e);
			//LOG.severe("MaestroBD consultaCajero ERROR "+e);
			e.printStackTrace();
			return false;
		}
	}

	public boolean consultarAdministrador(String idUsuario, char[] password){
		try {
			String strPassword = new String(password);
			idUsuario = idUsuario.toUpperCase();
			CryptCadena cryptCadena = new CryptCadena(idUsuario);
			String strPasswordEnc = cryptCadena.encrypt(strPassword);

			// Validar la existencia del cajero en la tabla usuario
			String validarUsuario = "";
			PreparedStatement st = conexionDB.prepareStatement("select * from usuario where id = ? and password = ?");
			st.setString(1, idUsuario);
			st.setString(2, strPasswordEnc);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				validarUsuario = rs.getString(1);
				G.getInstance().cajero = rs.getString(4)+" "+rs.getString(5);
			}

			// validar la existencia del cajero y esté activo en la tabla cajero
			String validarCajero = "";
			st = conexionDB.prepareStatement("Select * from cajero where id_usuario = ? and estado = ?");
			st.setString(1, idUsuario);
			st.setString(2, "activo");
			rs = st.executeQuery();
			if(rs.next()){
				G.getInstance().getIdCajero = rs.getInt(1);
				validarCajero = rs.getString(3);
			}
			rs.close();
			st.close();

			if(validarUsuario.equals(idUsuario) && validarCajero.equals(idUsuario)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println("MaestroDB.consultaCajero() "+e);
			//LOG.severe("MaestroBD consultaCajero ERROR "+e);
			e.printStackTrace();
			return false;
		}
	}








	public ArrayList<String> medioPago() {
		// select * from medio_pago where estado = 'activo'
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from medio_pago where estado = 'activo'");
			ResultSet rs = st.executeQuery();
			ArrayList<String> mediosPagos = new ArrayList<String>();
			while (rs.next() ){
				mediosPagos.add(rs.getString(2));
			}
			rs.close();
			st.close();
			return mediosPagos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<EntidadBancaria> traeEntidadBancaria(){
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from entidad_bancaria where estado = 'activo'");
			ResultSet rs = st.executeQuery();
			ArrayList<EntidadBancaria> alEntidadBancaria = new ArrayList<EntidadBancaria>();
			while (rs.next() ){
				alEntidadBancaria.add(new EntidadBancaria(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5)));
			}
			rs.close();
			st.close();
			return alEntidadBancaria;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> facturasPendientes() {
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from prefactura where estado = 'proceso' order by id DESC");
			ResultSet rs = st.executeQuery();
			ArrayList<String> listFacturasPendientes = new ArrayList<String>();
			while (rs.next() ){
				listFacturasPendientes.add(""+rs.getInt(1));
			}
			rs.close();
			st.close();
			return listFacturasPendientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public double pagosRecibido(int id_prefactura, int id_caja) {
		// select SUM(valor_pago) from medio_pago_prefactura where id_prefactura = 151 group by valor_pago
		try {
			PreparedStatement st = conexionDB.prepareStatement("select SUM(valor_pago) from medio_pago_prefactura where id_prefactura = ? and id_caja = ? group by valor_pago;");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			double pagosRecibidos = 0;
			while (rs.next() ){
				pagosRecibidos = pagosRecibidos + rs.getDouble(1);
			}
			rs.close();
			st.close();
			return pagosRecibidos;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int actualizarOCrearCliente(int id, double documento, String nombre, String apellido, String direccion,String telefono,String listadoPedido) {
		// Actualiza o crea un cliente
		int idResultado = 0;

		String input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input );
		Date fecha_ingreso = ts;

		// antes de tratar de insertar un cliente o tercero verificar que no esté creado

		/*Cliente cliente = new Cliente(); 
		cliente = consultarCliente(""+documento);
		if(cliente==null) {

		}
		if(cliente.getId()!=0 ) return cliente.getId();

		Tercero tercero = new Tercero();
		tercero = consultarTercero(""+documento);
		if(tercero.getId()!=0)return tercero.getId(); */

		if(id == 0) { // se crea el cliente
			try {
				String query = "INSERT INTO cliente(documento, nombre, apellido, direccion, telefono,fecha_ingreso, estado) VALUES (?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				nombre = nombre.toUpperCase();
				apellido = apellido.toUpperCase();
				st.setDouble(1, documento); 
				st.setString(2, nombre);  
				st.setString(3, apellido); 
				st.setString(4, direccion); 
				st.setString(5, telefono);
				st.setTimestamp(6, (Timestamp) fecha_ingreso);
				st.setString(7, "activo");
				st.executeUpdate();
				ResultSet rsKey = st.getGeneratedKeys();
				while(rsKey.next()) {
					idResultado = rsKey.getInt(1);
					System.out.println("MaestroDB.actualizarOCrearCliente()  xxxxxxxyyyyy rsKey.getInt(1) "+rsKey.getInt(1));
				}
				conexionDB.commit();

				// llena la tabla pendiente_exportacion para que se sincronice con el servidor
				query = "INSERT INTO pendiente_exportacion(id_host_origen, id_host_padre, tabla, campo_id, tipo_id, id_tabla, fecha_evento, "
						+ "accion, dg_fecha_accion, dg_accion) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
				st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, 2);
				st.setInt(2, 1);
				st.setString(3, "cliente");
				st.setString(4, "id");
				st.setString(5, "int");
				st.setInt(6, idResultado);
				st.setTimestamp(7, (Timestamp) fecha_ingreso);
				st.setString(8, "update");
				st.setTimestamp(9, (Timestamp) fecha_ingreso);
				st.setString(10, "insert");
				st.executeUpdate();
				conexionDB.commit();
				st.close();
				return idResultado;
			} catch (SQLException e) {
				System.out.println("MaestroDB.actualizarOCrearCliente() ERROR "+e);
				e.printStackTrace();
				return 0;
			}

		}else { // se acualiza el cliente
			try {
				// UPDATE cliente SET id=?, documento=?, nombre=?, apellido=?, direccion=?, id_ciudad=?, 
				// telefono=?, email=?, cumpleanos=?, comentario=?, fecha_ingreso=?,       
				// estado=?, dg_fecha_accion=?, dg_accion=?, id_lista_precio=? WHERE <condition>;
				String query = "UPDATE cliente SET documento=?, nombre=?, apellido=?, direccion=?, telefono=?, estado=? WHERE id = ?;";				
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				nombre = nombre.toUpperCase();
				apellido = apellido.toUpperCase();
				st.setDouble(1, documento); 
				st.setString(2, nombre);  
				st.setString(3, apellido); 
				st.setString(4, direccion); 
				st.setString(5, telefono);
				st.setString(6, "activo");
				st.setInt(7, id);
				st.executeUpdate();
				ResultSet rsKey = st.getGeneratedKeys();
				while(rsKey.next()) {
					idResultado = rsKey.getInt(1);
					System.out.println("MaestroDB.actualizarOCrearCliente()  id_cliente rsKey.getInt(1) "+rsKey.getInt(1));
				}
				conexionDB.commit();

				// llena la tabla pendiente_exportacion para que se sincronice con el servidor
				query = "INSERT INTO pendiente_exportacion(id_host_origen, id_host_padre, tabla, campo_id, tipo_id, id_tabla, fecha_evento, "
						+ "accion, dg_fecha_accion, dg_accion) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
				st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, 2);
				st.setInt(2, 1);
				st.setString(3, "cliente");
				st.setString(4, "id");
				st.setString(5, "int");
				st.setInt(6, idResultado);
				st.setTimestamp(7, (Timestamp) fecha_ingreso);
				st.setString(8, "update");
				st.setTimestamp(9, (Timestamp) fecha_ingreso);
				st.setString(10, "insert");
				st.executeUpdate();
				conexionDB.commit();

				st.close();
				return idResultado;
			} catch (SQLException e) {
				System.out.println("MaestroDB.actualizarOCrearCliente() ERROR "+e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	public String agregarDomicilio(int id_cliente, int id_caja,int id_almacen, int id_factura, int id_prefactura, double documento,
			String nombre, String apellido, String direccion, String telefono, String listadoPedido,int id_domiciliario, String nombre_domiciliario ) {

		String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );
		try {
			String query = "INSERT INTO domicilio(id_cliente, id_caja,id_almacen ,id_factura, id_prefactura, documento, "
					+ "nombre, apellido, direccion, telefono, listado_pedido,id_domiciliario,nombre_domiciliario, dg_fecha_accion, dg_accion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_cliente);
			st.setInt(2, id_caja);
			st.setInt(3, id_almacen);
			st.setInt(4, id_factura);
			st.setInt(5, id_prefactura);
			st.setDouble(6, documento);
			st.setString(7, nombre);
			st.setString(8, apellido);
			st.setString(9, direccion);
			st.setString(10, telefono);
			st.setString(11, listadoPedido);
			st.setInt(12, id_domiciliario);	//id_domiciliario integer,
			st.setString(13, nombre_domiciliario); //  nombre_domiciliario text,
			st.setTimestamp(14, (Timestamp) fecha); //  dg_fecha_accion timestamp without time zone,
			st.setString(15, "insert"); // dg_accion character varying(30)
			st.executeUpdate();
			ResultSet rsKey = st.getGeneratedKeys();
			String comentarioDomicilio = "";
			while(rsKey.next()) {
				comentarioDomicilio = " "+rsKey.getString(8)+" "+rsKey.getString(9)+" "+rsKey.getString(10)+" "+rsKey.getString(11);
				System.out.println("DDDDD MaestroDB.agregarDomicilio() "+"-8-"+rsKey.getString(8)+"-9-"+rsKey.getString(9)+"-10-"+rsKey.getString(10)+"-11-"+rsKey.getString(11));
			}
			System.out.println("MaestroDB.agregarDomicilio() comentarioDomicilio "+comentarioDomicilio);
			conexionDB.commit();
			st.close();
			return comentarioDomicilio;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MaestroDB.agregarDomicilio() ERROR "+e);
			return "";
		}
	}

	public Domicilio traerDomicilio(int id_prefactura, int id_almacen) {
		Domicilio domicilio = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from domicilio where id_prefactura = ? and id_almacen = ?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_almacen);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				domicilio = new Domicilio(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
						rs.getDouble(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getInt(13),rs.getString(14),rs.getTimestamp(15));
			}
			rs.close();
			st.close();
			return domicilio;
		} catch (SQLException e) {
			System.out.println("MaestroDB.traerDomicilio( 7ene2021-7ene2021-7ene2021-7ene2021-7ene2021)");
			System.out.println("MaestroDB.traerDomicilio() ERROR "+e);
			e.printStackTrace();
			return null;
		}
	}

	public Domicilio traerDomicilioFacturado(double numeroFactura,int id_caja) {
		Domicilio domicilio = null;

		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from domicilio where id_factura = ? and id_caja = ?");
			st.setDouble(1, numeroFactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				domicilio = new Domicilio(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
						rs.getDouble(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getInt(13),rs.getString(14),rs.getTimestamp(15));
			}
			rs.close();
			st.close();
			return domicilio;
		} catch (SQLException e) {
			System.out.println("MaestroDB.traerDomicilioFacturado() ERROR "+e);
			e.printStackTrace();
			return null;
		}
	}

	public String consultarDomicilio(String sId_prefactura) {
		String nombreDomicilio = null;
		int id_prefactura = Integer.valueOf(sId_prefactura);
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from domicilio where id_prefactura = ?;");
			st.setInt(1, id_prefactura);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				nombreDomicilio = rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(11);
				if(rs.getString(8)==null) {
					nombreDomicilio="";
				}
			}
			rs.close();
			st.close();
			return nombreDomicilio;
		} catch (SQLException e) {
			System.out.println("MaestroDB.consultarDomicilio() consultarDomicilio consultarDomicilio consultarDomicilio");
			System.out.println("MaestroDB.consultarDomicilio() ERROR "+e);
			e.printStackTrace();
			return null;
		}
	}

	public void borrarDocimcilio(int id_prefactura) {
		try {
			//DELETE FROM domicilio WHERE id_prefactura = 999;
			String query = "DELETE FROM domicilio WHERE id_prefactura = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_prefactura);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			System.out.println("MaestroDB.borraItemPreFactura() DELETE ResultSet "+rsKey);*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.borrarDocimcilio() ERROR DELETE "+e);
			e.printStackTrace();
		}

		try {
			// UPDATE prefactura SET id_cliente=0, comentario='' WHERE id=491;
			String query = "UPDATE prefactura SET id_cliente=0, comentario='' WHERE id=?;";				
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_prefactura);   
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			while(rsKey.next()) {
				idResultado = rsKey.getInt(1);
			}*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.borrarDocimcilio() ERROR UPDATE "+e);
			e.printStackTrace();
		}







	}

	public void actualizaComentarioPreFactura(String comentarioDomicilio, int id_prefactura, int id_caja) {
		try {
			// UPDATE prefactura SET comentario=? WHERE id=? and id_caja=?
			String query = "UPDATE prefactura SET comentario=? WHERE id=? and id_caja=?;";				
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, comentarioDomicilio);  
			st.setInt(2, id_prefactura); 
			st.setInt(3, id_caja); 
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			while(rsKey.next()) {
				idResultado = rsKey.getInt(1);
			}*/
			conexionDB.commit();
			st.close();
		} catch (SQLException e) {
			System.out.println("MaestroDB.actualizaComentarioPreFactura() ERROR "+e);
			e.printStackTrace();
		}
	}

	public String traeNombreArticulo(String codigo) {
		//select id_articulo from codigo_articulo where codigo = '606'
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_articulo from codigo_articulo where codigo = ?");
			st.setString(1, codigo);
			ResultSet rs = st.executeQuery();
			int id = 0;
			if (rs.next() ){
				id = Integer.parseInt(rs.getString(1));
				//System.out.println("MaestroDB.traeNombreArticulo() ID "+id);
			}else{
				return "Bad";
			}
			st = conexionDB.prepareStatement("select nombre from articulo where id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next() ){
				//System.out.println("MaestroDB.traeNombreArticulo() articulo "+rs.getString(1));
				return rs.getString(1);
			}else{
				return "Bad";
			}
		} catch (SQLException e) {
			return "Bad";
		}
	}

	public String traeNombreComercialProveedor(int id_proveedor) {
		//select nombre_comercial from proveedor where id=16
		String nombreComercialProveedor="";
		try {
			PreparedStatement st = conexionDB.prepareStatement("select nombre_comercial from proveedor where id=?");
			st.setInt(1, id_proveedor);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				nombreComercialProveedor = rs.getString(1);
			}
			return nombreComercialProveedor;
		} catch (SQLException e) {
			return null;
		}
	}

	public ArrayList<Pagos> traerPagos(int id_caja, int id_almacen, int id_cajero, Date fechaInicial, Date fechaFinal) {
		// select * from pagos where fechapago > '2018-02-05 00:00:00' and fechapago < '2018-02-05 23:00:00' and estado = 'pagado' order by id
		try {
			ArrayList<Pagos> alPagos = new ArrayList<Pagos>();
			Pagos pagos;
			PreparedStatement st = conexionDB.prepareStatement("select * from pagos where id_caja = ? and id_almacen = ? and id_cajero = ? and fechapago > ? and fechapago < ? and estado = 'pagado' order by id");
			st.setInt(1, id_caja);
			st.setInt(2, id_almacen);
			st.setInt(3, id_cajero);
			st.setTimestamp(4, (Timestamp) fechaInicial);
			st.setTimestamp(5, (Timestamp) fechaFinal);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				pagos = new Pagos(rs.getInt("id"), rs.getInt("id_caja"), rs.getInt("id_almacen"), rs.getInt("id_proveedor"), rs.getInt("id_cajero"), rs.getInt("id_tipo_pago"),
						rs.getDouble("valor_factura"), rs.getDouble("valor_descuento"), rs.getDouble("valor_pagado"), rs.getString("numero_factura"), rs.getTimestamp("fechapago"),
						rs.getInt("numero_impresiones"),rs.getString("comentario"), rs.getString("estado"), rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion"));
				alPagos.add(pagos);
			}
			rs.close();
			st.close();
			return alPagos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Pagos traerPagoConId(int id_pago, int id_caja, int id_almacen, int id_cajero) {
		// select * from pagos where id = 5 and id_caja = 1 and id_almacen = 1 and id_cajero=1 and estado = 'pagado'
		try {
			System.out.println("MaestroDB.traerPagoConId() id_pago "+id_pago+" id_caja "+id_caja+" "+id_almacen+" "+id_cajero);
			Pagos pagos = null;
			PreparedStatement st = conexionDB.prepareStatement("select * from pagos where id = ? and id_caja = ? and id_almacen = ? and id_cajero = ? and estado = 'pagado';");
			st.setInt(1, id_pago);
			st.setInt(2, id_caja);
			st.setInt(3, id_almacen);
			st.setInt(4, id_cajero);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				pagos = new Pagos(rs.getInt("id"), rs.getInt("id_caja"), rs.getInt("id_almacen"), rs.getInt("id_proveedor"), rs.getInt("id_cajero"), rs.getInt("id_tipo_pago"),
						rs.getDouble("valor_factura"), rs.getDouble("valor_descuento"), rs.getDouble("valor_pagado"), rs.getString("numero_factura"), rs.getTimestamp("fechapago"),
						rs.getInt("numero_impresiones"),rs.getString("comentario"), rs.getString("estado"), rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return pagos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void actualizaCantidadImpresionesPagos(int id_pagos, int id_caja, int id_almacen, int numero_impresiones) {
		/*UPDATE pagos SET numero_impresiones=? WHERE id=?, id_caja=?, id_almacen=?*/
		try {
			String query = "UPDATE pagos SET numero_impresiones=? WHERE id=? and id_caja=? and id_almacen=?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, numero_impresiones);
			st.setInt(2, id_pagos);
			st.setInt(3, id_caja);
			st.setInt(4, id_almacen);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() ERROR "+e);
			e.printStackTrace();
		}
	}

	public int maximoIDPagos() {
		//select nombre_comercial from proveedor where id=16
		int maximoIDPagos = 0;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select MAX(id) from pagos");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				maximoIDPagos = rs.getInt(1);
			}
			return maximoIDPagos;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int borraPago(int id_pago, int id_caja, int id_almacen, int id_cajero) {
		try {
			//UPDATE pagos SET estado='anulado' WHERE id = 13 and id_caja = 1 and id_almacen = 1 and id_cajero = 1
			System.out.println("MaestroDB.borraPago() id_pago "+id_pago+" id_caja "+id_caja+" id_almacen "+id_almacen+" id_cajero "+id_cajero);

			String query = "UPDATE pagos SET estado='anulado' WHERE id = ? and id_caja = ? and id_almacen = ? and id_cajero = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_pago);
			st.setInt(2, id_caja);
			st.setInt(3, id_almacen);
			st.setInt(4, id_cajero);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			while (rsKey.next()) {
				System.out.println("MaestroDB.borraPago() DELETE ResultSet rsKey.getInt(1) "+rsKey.getInt(1));
			}*/
			conexionDB.commit();
			st.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("MaestroDB.borraPago() ERROR "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Factura> traerFacturaSegunRangoFechas(int id_almacen, int id_Caja, int id_cajero, Timestamp fechaInicial,Timestamp fechaFinal) {
		// SELECT * FROM factura WHERE fecha >= '2018-02-14' AND fecha <= '2018-02-14' AND estado='liquidada' AND id_almacen = 1 AND id_caja=1 
		// AND id_cajero=1 AND estado='liquidada' ORDER BY numero ASC
		Factura factura;
		ArrayList<Factura> alFactura = new ArrayList<Factura>();
		ArrayList<Factura> alFacturaSalida = new ArrayList<Factura>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM factura WHERE fecha >= ? AND fecha <= ? "
					+ "AND estado='liquidada' AND id_almacen = ? AND id_caja = ? AND estado='liquidada' ORDER BY numero ASC;");
			st.setTimestamp(1, fechaInicial);
			st.setTimestamp(2, fechaFinal);
			st.setInt(3, id_almacen);
			st.setInt(4, id_Caja);
			//st.setInt(5, id_cajero);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				factura = new Factura(rs.getInt("id_Caja"),rs.getDouble("numero"),rs.getInt("id_numeracion_autorizada"),rs.getInt("id_almacen"),
						rs.getDate("fecha"),rs.getString("hora"),rs.getInt("id_cajero"),rs.getInt("id_tercero"),rs.getInt("id_vendedor"),
						rs.getString("id_usuario_vendedor"),rs.getDouble("valor_factura"),rs.getDouble("valor_descuento"),rs.getDouble("valor_iva"),
						rs.getDouble("costo"),rs.getDouble("valor_devolucion"),rs.getInt("id_cliente"),rs.getString("comentario"),rs.getString("estado"),
						rs.getInt("cantidad_bolsa_inc"),rs.getInt("valor_bolsa_inc"),rs.getTimestamp("fecha_exportacion"),rs.getString("estado_exportacion"),
						rs.getLong("identificador_externo"),rs.getString("id_tipo_comprobante_contable"),rs.getInt("numero_comrpobante_contable"),
						rs.getInt("id_prefactura"),rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"));
				alFactura.add(factura);
			}
			rs.close();
			st.close();

			String sFechaFactura="";
			for (int i = 0; i < alFactura.size(); i++) {
				sFechaFactura = alFactura.get(i).getFecha()+" "+alFactura.get(i).getHora()+".0";
				java.sql.Timestamp fechaFactura = java.sql.Timestamp.valueOf( sFechaFactura );
				if(fechaFactura.after(fechaInicial) && fechaFactura.before(fechaFinal)) {
					//System.out.println("MaestroDB.traerPrimeraUltimaFactura() i "+i+" numero "+alFactura.get(i).getNumero()+" rangoFecha "+fechaFactura);
					alFacturaSalida.add(alFactura.get(i));
				}
			}

			return alFacturaSalida;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Resumen> traerResumenSegunRangoFechas(int id_almacen, int id_Caja, int id_cajero, Timestamp fechaInicial,Timestamp fechaFinal) {
		// SELECT * FROM factura WHERE fecha >= '2018-02-14' AND fecha <= '2018-02-14' AND estado='liquidada' AND id_almacen = 1 AND id_caja=1 
		// AND id_cajero=1 AND estado='liquidada' ORDER BY numero ASC
		Resumen resumen;
		ArrayList<Resumen> alResumen = new ArrayList<Resumen>();
		//ArrayList<Resumen> alResumenSalida = new ArrayList<Resumen>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select i.codigo_articulo, a.nombre, i.valor_presentacion as unitario, sum(i.cantidad_unidad_medida) as cantidad, sum(i.valor_item) as total \r\n" + 
					"from factura f, item_factura i, articulo a \r\n" + 
					"WHERE f.numero = i.numero and i.id_articulo = a.id and f.fecha BETWEEN ? and ? \r\n" + 
					"and f.id_almacen = ? and f.id_caja = ? \r\n" + 
					"group by i.codigo_articulo,a.nombre, i.valor_presentacion \r\n" + 
					"order by a.nombre\r\n" + 
					"");
			
			st.setTimestamp(1, fechaInicial);
			st.setTimestamp(2, fechaFinal);
			st.setInt(3, id_almacen);
			st.setInt(4, id_Caja);
			//st.setInt(5, id_cajero);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				//System.out.println("Codigo "+rs.getString("codigo_articulo"));
				resumen = new Resumen(rs.getString("codigo_articulo"), rs.getString("nombre"), rs.getDouble("unitario"), rs.getDouble("cantidad"), rs.getDouble("total"));
				alResumen.add(resumen);
			}
			rs.close();
			st.close();

			/*String sFechaFactura="";
			for (int i = 0; i < alFactura.size(); i++) {
				sFechaFactura = alFactura.get(i).getFecha()+" "+alFactura.get(i).getHora()+".0";
				java.sql.Timestamp fechaFactura = java.sql.Timestamp.valueOf( sFechaFactura );
				if(fechaFactura.after(fechaInicial) && fechaFactura.before(fechaFinal)) {
					//System.out.println("MaestroDB.traerPrimeraUltimaFactura() i "+i+" numero "+alFactura.get(i).getNumero()+" rangoFecha "+fechaFactura);
					alFacturaSalida.add(alFactura.get(i));
				}
			}*/

			return alResumen;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String traePrefijo(int id_Caja) {
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM numeracion_autorizada WHERE id_caja = ? and estado = 'activo';");
			st.setInt(1, id_Caja);
			ResultSet rs = st.executeQuery();
			String prefijo = "";

			while (rs.next() ){
				prefijo = rs.getString("prefijo");

			}
			rs.close();
			st.close();
			return prefijo;
		}catch (Exception e) {
			System.out.println("MaestroDB.traePrefijo() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<IvaValor> traeTotalIvaPeriodo(int numeroFacturaInicial, int numeroFacturaFinal, int id_Caja) {
		// SELECT id_base_iva, SUM(valor_item) AS VENTA, SUM(valor_iva) AS IVA FROM item_factura 
		// WHERE numero>=388 AND numero<=399 AND id_caja = 1 GROUP BY id_base_iva
		try {
			IvaValor ivaValor = null;
			ArrayList<IvaValor> alIvaValor = new ArrayList<IvaValor>();
			PreparedStatement st = conexionDB.prepareStatement("SELECT id_base_iva, SUM(valor_item) AS VENTA, SUM(valor_iva) AS IVA "
					+ "FROM item_factura WHERE numero>=? AND numero<=? AND id_caja = ? GROUP BY id_base_iva;");
			st.setDouble(1, numeroFacturaInicial);
			st.setDouble(2, numeroFacturaFinal);
			st.setInt(3, id_Caja);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				ivaValor = new IvaValor(rs.getInt("id_base_iva"),rs.getDouble("VENTA"),rs.getDouble("IVA"));
				alIvaValor.add(ivaValor);
			}
			rs.close();
			st.close();
			return alIvaValor;
		}catch (Exception e) {
			System.out.println("MaestroDB.traePrefijo() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<IvaValor> traeSumatoriaIvasFactura(double numeroFactura, int id_caja) {
		// se calculan el total de los ivas por cada una de las tarifas referidas a si id IVA.
		IvaValor ivaValor; //(int idIva, double valorIva)
		ArrayList<IvaValor> alIvaValor = new ArrayList<IvaValor>();
		try {
			//select id_base_iva, sum(valor_iva) as valor_iva, sum(valor_item) as totalXiva from item_factura where numero = 191 and id_caja = 1 group by id_base_iva;
			PreparedStatement st = conexionDB.prepareStatement("select id_base_iva, sum(valor_iva), sum(valor_item) from item_factura where numero = ? and id_caja = ? group by id_base_iva;");
			st.setDouble(1, numeroFactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ivaValor = new IvaValor(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
				alIvaValor.add(ivaValor);
			}
			st.close();
			rs.close();
			return alIvaValor;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*public ArrayList<TraeSumaXMedioPago> traerSumaXMedioPago(int numeroFacturaInicial, int numeroFacturaFinal,int id_Caja) {
		// SELECT medio_pago_factura.id_medio_pago, medio_pago.nombre ,SUM(valor_pago) AS valorPagado
		// FROM medio_pago_factura LEFT JOIN medio_pago ON medio_pago_factura.id_medio_pago = medio_pago.id
		// WHERE numero>=388 AND numero<=399 AND id_caja = 1 
		// GROUP BY medio_pago_factura.id_medio_pago,medio_pago.nombre
		try {
			TraeSumaXMedioPago traeSumaXMedioPago;
			ArrayList<TraeSumaXMedioPago> alTraeSumaXMedioPago = new ArrayList<TraeSumaXMedioPago>();
			PreparedStatement st = conexionDB.prepareStatement("SELECT medio_pago_factura.id_medio_pago, medio_pago.nombre ,SUM(valor_pago) AS valorpagado"
					+ "FROM medio_pago_factura LEFT JOIN medio_pago ON medio_pago_factura.id_medio_pago = medio_pago.id "
					+ "WHERE numero>=? AND numero<=? AND id_caja = ? "
					+ "GROUP BY medio_pago_factura.id_medio_pago,medio_pago.nombre;");
			st.setDouble(1, numeroFacturaInicial);
			st.setDouble(2, numeroFacturaFinal);
			st.setInt(3, id_Caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){				
				traeSumaXMedioPago = new TraeSumaXMedioPago(rs.getInt("id_medio_pago"),rs.getString("nombre"),rs.getDouble("valorpagado"));
				alTraeSumaXMedioPago.add(traeSumaXMedioPago);
			}
			rs.close();
			st.close();
			return alTraeSumaXMedioPago;
		}catch (Exception e) {
			System.out.println("MaestroDB.traerSumaXMedioPago() "+e);
			e.printStackTrace();
			return null;
		}
	}*/

	public ArrayList<MedioPagoFactura> traerFormasDePagoPeriodo(int numeroFacturaInicial, int numeroFacturaFinal,int id_Caja) {
		//SELECT * FROM medio_pago_factura WHERE numero>=388 AND numero<=399 AND id_caja = 1 ORDER BY numero ASC
		try {
			MedioPagoFactura medioPagoFactura;
			ArrayList<MedioPagoFactura> alMedioPagoFactura = new ArrayList<MedioPagoFactura>();
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM medio_pago_factura WHERE numero>=? AND numero<=? AND id_caja = ? ORDER BY numero ASC;");
			st.setDouble(1, numeroFacturaInicial);
			st.setDouble(2, numeroFacturaFinal);
			st.setInt(3, id_Caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				medioPagoFactura = new MedioPagoFactura(rs.getInt("id_caja"), rs.getDouble("numero"), rs.getInt("id_medio_pago"),rs.getInt("item"),rs.getInt("id_entidad_bancaria"),
						rs.getLong("tarjeta"),rs.getLong("autorizacion"),rs.getInt("numero_recibo"),rs.getDouble("valor_pago"),rs.getDouble("porcentaje_comision"),
						rs.getDouble("valor_comision"),rs.getDouble("valor_iva"),rs.getDouble("efectivo_recibido"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));
				alMedioPagoFactura.add(medioPagoFactura);
			}
			rs.close();
			st.close();
			return alMedioPagoFactura;
		}catch (Exception e) {
			System.out.println("MaestroDB.traerFormasDePagoPeriodo() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<MedioPagoFactura> traerMedioPagoFacturaFacturado(double numeroFactura, int id_caja) {
		//SELECT * FROM medio_pago_factura WHERE numero=388 AND id_caja = 1 ORDER BY numero ASC
		try {
			MedioPagoFactura medioPagoFactura;
			ArrayList<MedioPagoFactura> alMedioPagoFactura = new ArrayList<MedioPagoFactura>();
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM medio_pago_factura WHERE numero=? AND id_caja = ? ORDER BY numero ASC;");
			st.setDouble(1, numeroFactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				medioPagoFactura = new MedioPagoFactura(rs.getInt("id_caja"), rs.getDouble("numero"), rs.getInt("id_medio_pago"),rs.getInt("item"),rs.getInt("id_entidad_bancaria"),
						rs.getLong("tarjeta"),rs.getLong("autorizacion"),rs.getInt("numero_recibo"),rs.getDouble("valor_pago"),rs.getDouble("porcentaje_comision"),
						rs.getDouble("valor_comision"),rs.getDouble("valor_iva"),rs.getDouble("efectivo_recibido"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));
				alMedioPagoFactura.add(medioPagoFactura);
			}
			rs.close();
			st.close();
			return alMedioPagoFactura;
		}catch (Exception e) {
			System.out.println("MaestroDB.traerMedioPagoFacturaFacturado() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<MedioPago> alMedioPago(){
		//select * from medio_pago;
		try {
			MedioPago medioPago;
			ArrayList<MedioPago> alMedioPago = new ArrayList<MedioPago>();
			PreparedStatement st = conexionDB.prepareStatement("select * from medio_pago;");
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				medioPago = new MedioPago(rs.getInt("id"), rs.getString("nombre"),rs.getString("estado"),
						rs.getTimestamp("fecha_exportacion"),rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"));
				alMedioPago.add(medioPago);
			}
			rs.close();
			st.close();
			return alMedioPago;
		}catch (Exception e) {
			System.out.println("MaestroDB.alMedioPago() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public NumeracionAutorizada traeNumeracionAutorizada(int id_caja) {
		// select * from Numeracion_autorizada where id_caja = 1
		try {
			NumeracionAutorizada numeracionAutorizada=null;
			PreparedStatement st = conexionDB.prepareStatement("select * from Numeracion_autorizada where id_caja = ? and estado = 'activo';");
			st.setInt(1, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				numeracionAutorizada = new NumeracionAutorizada(
						rs.getInt("id"),rs.getInt("id_caja"),rs.getString("resolucion"),rs.getDate("fecha_expedicion"),
						rs.getString("tipo_facturacion"),rs.getString("prefijo"),rs.getDouble("rango_inicial"),
						rs.getDouble("rango_final"), rs.getDouble("numero_actual"),rs.getDate("fecha_caducidad"),
						rs.getTimestamp("fecha_exportacion"),rs.getString("estado"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return numeracionAutorizada;
		}catch (Exception e) {
			System.out.println("MaestroDB.traePrefijoAlmacen() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public Factura traeFactura(int id_caja, double numero) {
		// select * from factura where id_caja = 1 and numero = 300 and estado = 'liquidada'
		Factura factura = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from factura where id_caja = ? and numero = ? and estado = 'liquidada';");
			st.setInt(1, id_caja);
			st.setDouble(2, numero);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				factura = new Factura(rs.getInt("id_Caja"),rs.getDouble("numero"),rs.getInt("id_numeracion_autorizada"),rs.getInt("id_almacen"),
						rs.getDate("fecha"),rs.getString("hora"),rs.getInt("id_cajero"),rs.getInt("id_tercero"),rs.getInt("id_vendedor"),
						rs.getString("id_usuario_vendedor"),rs.getDouble("valor_factura"),rs.getDouble("valor_descuento"),rs.getDouble("valor_iva"),
						rs.getDouble("costo"),rs.getDouble("valor_devolucion"),rs.getInt("id_cliente"),rs.getString("comentario"),rs.getString("estado"),
						rs.getInt("cantidad_bolsa_inc"),rs.getInt("valor_bolsa_inc"),rs.getTimestamp("fecha_exportacion"),rs.getString("estado_exportacion"),
						rs.getLong("identificador_externo"),rs.getString("id_tipo_comprobante_contable"),rs.getInt("numero_comrpobante_contable"),
						rs.getInt("id_prefactura"),rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return factura;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeFactura() "+e);
			e.printStackTrace();
			return null;
		}
	}


	public ArrayList<ItemFactura> traerItemFactura(int id_caja, double numero) {
		// select * from item_factura where id_caja = 1 and numero = 300 
		ItemFactura itemFactura = null;
		ArrayList<ItemFactura> alItemFactura = new ArrayList<ItemFactura>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_factura where id_caja = ? and numero = ?;");
			st.setInt(1, id_caja);
			st.setDouble(2, numero);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				itemFactura = new ItemFactura(rs.getInt("id_caja"), rs.getDouble("numero"),rs.getInt("item"),rs.getInt("id_vendedor"),
						rs.getString("id_usuario_vendedor"),rs.getInt("id_articulo"), rs.getString("codigo_articulo"), 
						rs.getString("codigo_articulo_venta"), rs.getString("nombre_provisional"),rs.getDouble("cantidad_unidad_medida"),
						rs.getDouble("valor_unidad"), rs.getDouble("valor_anterior"), rs.getInt("id_presentacion"),
						rs.getDouble("cantidad_presentacion"), rs.getDouble("valor_presentacion"), rs.getDouble("valor_item"),
						rs.getInt("id_base_iva"),rs.getDouble("valor_iva"), rs.getDouble("porcentaje_descuento"), rs.getDouble("valor_descuento"),
						rs.getDouble("costo"), rs.getDouble("costo_item"),rs.getInt("item_promocion"), 
						rs.getDouble("cantidad_um_devuelta"), rs.getDouble("cantidad_pres_devuelta"), rs.getString("estado_exportacion"),
						rs.getTimestamp("dg_fecha_accion"), rs.getString("dg_accion"));
				alItemFactura.add(itemFactura);
			}
			rs.close();
			st.close();
			return alItemFactura;
		}catch (Exception e) {
			System.out.println("MaestroDB.traerItemFactura() "+e);
			e.printStackTrace();
			return null;
		}
	}



	public boolean cargarLicencia() {
		String sistemaOperativo = System.getProperty("os.name").toUpperCase();
		try {
			String nombreArchivoLicencia;
			if (sistemaOperativo.indexOf("WINDOWS") != -1) {
				File dirWindows = new File("C:/tmp/lic/serv/");
				nombreArchivoLicencia = dirWindows.getAbsolutePath() + "/host.bin";
			} else {
				nombreArchivoLicencia = "/var/lic/serv/host.bin";
			}

			BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoLicencia));
			String currentLine = reader.readLine();
			reader.close();

			if(currentLine.equals("")){
				return false;
			}else{
				Gson gsonLicencia = new Gson();
				licencia = gsonLicencia.fromJson(currentLine, Licencia.class);
				G.getInstance().licenciaGlobal = licencia;
				if(G.getInstance().licenciaGlobal.getResultado()){
					System.out.println("MaestroDB.cargarLicencia() G.getInstance().licenciaGlobal.getResultado() "+currentLine);
					return conectarDB();
				}else{
					System.out.println("MaestroDB.cargarLicencia() Datos invalidos en la licencia "+licencia);
					//LOG.severe("Datos invalidos en la licencia");
					return false;
				}
			}
		} catch (JsonSyntaxException e) {
			System.err.println("MaestroDB.cargarLicencia() JsonSyntaxException ");
			e.printStackTrace();
			//LOG.severe("Error en JSON licencia "+e);
			return false;
		} catch (FileNotFoundException e) {
			System.err.println("MaestroDB.cargarLicencia() FileNotFoundException ");
			e.printStackTrace();
			//LOG.severe("Error archivo licencia no existe "+e);
			return false;
		} catch (IOException e) {
			System.err.println("MaestroDB.cargarLicencia() IOException ");
			e.printStackTrace();
			//LOG.severe("Error indeterminado licencia "+e);
			return false;
		}
	}

	public boolean conectarDB() {
		// Verifique primero si en Maven se adjuntó org.postgresql
		switch (G.getInstance().licenciaGlobal.getTipoBD()) {
		case "pgsql":
			this.driver = "org.postgresql.Driver";
			this.connectionString = "jdbc:postgresql://" + G.getInstance().licenciaGlobal.getHostBD() + ":" + G.getInstance().licenciaGlobal.getPuertoBD() + "/" + G.getInstance().licenciaGlobal.getNombreBD();
			//System.out.println("MaestroBD conectarDB connectionString "+connectionString); //jdbc:postgresql://localhost:5432/caja_demo
			break;
		case "sqlserver":
			this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			this.connectionString = "jdbc:sqlserver://" + G.getInstance().licenciaGlobal.getHostBD() + ":" + G.getInstance().licenciaGlobal.getPuertoBD() + ";databaseName=" + G.getInstance().licenciaGlobal.getNombreBD() +
					";user=" + G.getInstance().licenciaGlobal.getUsuarioBD() + ";password=" + G.getInstance().licenciaGlobal.getPasswordBD();
		default:
			break;
		}

		try {
			Class.forName(driver);
			this.conexionDB = DriverManager.getConnection(this.connectionString, G.getInstance().licenciaGlobal.getUsuarioBD(),G.getInstance().licenciaGlobal.getPasswordBD());
			this.conexionDB.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
			this.conexionDB.setAutoCommit(false);
			//this.sttmDBConsulta = this.conexionDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//this.sttmDBAccion = this.conexionDB.createStatement();
			if (this.conexionDB == null) {
				this.estado = false;
				return estado;
			}else{
				this.estado = true;
				return estado;
			}
		} catch (Exception e) {
			System.out.println("MaestroDB.conectarDB() ERROR "+e);
			e.printStackTrace();
			//LOG.severe("Problema en la conexion a: " + this.driver + e.getMessage() + " - codigo Error: " + e.toString());
			return estado;
		}
	}

	public int traeConsecutivoDevolucionVenta() {
		try {
			PreparedStatement st = conexionDB.prepareStatement("select max(numero) from devolucion_venta;");
			ResultSet rs = st.executeQuery();
			int consecutivoDevolucionVenta = 0;
			while (rs.next() ){
				consecutivoDevolucionVenta = rs.getInt(1);
			}
			rs.close();
			st.close();
			return consecutivoDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeConsecutivoDevolucionVenta() "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public void agregarDevolucionVenta(int id_caja, int numero, Timestamp fecha, Timestamp fecha_caducidad,
			int id_usuario, String origen, int id_caja_factura, double numero_factura, int id_cliente,
			long documento_cliente, String apellido_cliente, String nombre_cliente, String email_cliente,
			String telefono_cliente, String detalle, double valor, double saldo_pendiente,
			String documento_caja_generado, int id_documento_caja, int numero_documento_caja, int id_caja_emision,
			String estado2, String estado_exportacion, Timestamp fecha_contabilizacion, long identificador_externo,
			String id_tipo_comprobante_contable, int numero_comrpobante_contable, Timestamp dg_fecha_accion,
			String dg_accion, String origen_devolucion) {
		try {
			String query = "INSERT INTO devolucion_venta(id_caja, numero, fecha, fecha_caducidad, id_usuario, origen,"
					+ "id_caja_factura, numero_factura, id_cliente, documento_cliente,apellido_cliente, nombre_cliente, "
					+ "email_cliente, telefono_cliente, detalle, valor, saldo_pendiente, documento_caja_generado, id_documento_caja,"
					+ "numero_documento_caja, id_caja_emision, estado, estado_exportacion, fecha_contabilizacion, "
					+ "identificador_externo, id_tipo_comprobante_contable, numero_comrpobante_contable, dg_fecha_accion,dg_accion,"
					+ "numero_impresiones, origen_devolucion)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_caja);
			st.setInt(2, numero);
			st.setTimestamp(3, fecha);
			st.setTimestamp(4, fecha_caducidad);
			st.setInt(5, id_usuario);
			st.setString(6, origen);
			st.setInt(7, id_caja_factura);
			st.setDouble(8, numero_factura);
			st.setInt(9, id_cliente);
			st.setLong(10, documento_cliente);
			st.setString(11, apellido_cliente);
			st.setString(12, nombre_cliente);
			st.setString(13, email_cliente);
			st.setString(14, telefono_cliente);
			st.setString(15, detalle);
			st.setDouble(16, valor);
			st.setDouble(17, saldo_pendiente);
			st.setString(18, documento_caja_generado);
			st.setInt(19, id_documento_caja);
			st.setInt(20, numero_documento_caja);
			st.setInt(21, id_caja_emision);
			st.setString(22, estado2);
			st.setString(23, estado_exportacion);
			st.setTimestamp(24, fecha_contabilizacion);
			st.setLong(25, identificador_externo);
			st.setString(26, id_tipo_comprobante_contable);
			st.setInt(27, numero_comrpobante_contable);
			st.setTimestamp(28, dg_fecha_accion);
			st.setString(29, dg_accion);
			st.setInt(30, 1);
			st.setString(31, origen_devolucion);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			int k=0;
			int idCaja=0;
			double numero=0; 
			while(rsKey.next()) {
				k++;
				idCaja = rsKey.getInt(1);
				numero = rsKey.getDouble(2);
				System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
			}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarDevolucionVenta() "+e);
			e.printStackTrace();
		}
	}

	public void agregarItemDevolucionVenta(int id_caja, int numero, int item, int item_factura,
			String codigo_articulo, int id_articulo, int id_base_iva, int id_presentacion, double cantidad_presentacion,
			double cantidad_unidad_medida, double valorItem, double valor_iva, String estado_exportacion2,
			Timestamp dg_fecha_accion2, String dg_accion2) {

		try {
			String query = "INSERT INTO item_devolucion_venta(id_caja, numero, item, item_factura, codigo_articulo, id_articulo, "
					+ "id_base_iva, id_presentacion, cantidad_presentacion, cantidad_unidad_medida,valor, valor_iva, estado_exportacion, "
					+ "dg_fecha_accion, dg_accion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_caja);
			st.setInt(2, numero);
			st.setInt(3,item);
			st.setInt(4, item_factura);
			st.setString(5, codigo_articulo); 
			st.setInt(6, id_articulo);
			st.setInt(7, id_base_iva); 
			st.setInt(8, id_presentacion);
			st.setDouble(9, cantidad_presentacion);
			st.setDouble(10, cantidad_unidad_medida);
			st.setDouble(11,valorItem);
			st.setDouble(12, valor_iva); 
			st.setString(13,estado_exportacion2); 
			st.setTimestamp(14, dg_fecha_accion2); 
			st.setString(15,dg_accion2);


			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			int k=0;
			int idCaja=0;
			double numero=0; 
			while(rsKey.next()) {
				k++;
				idCaja = rsKey.getInt(1);
				numero = rsKey.getDouble(2);
				System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
			}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarDevolucionVenta() "+e);
			e.printStackTrace();
		}

	}

	public void agregarPendienteExporteacionDevolucion(int id_host_origen, int id_host_padre, String tabla, String campo_id,
			String tipo_id, String id_tabla, Timestamp fecha_evento, String accion, String observacion,
			Timestamp dg_fecha_accion1, String dg_accion1) {
		try {
			String query = "INSERT INTO pendiente_exportacion(id_host_origen, id_host_padre, tabla, campo_id, tipo_id,"
					+ "id_tabla, fecha_evento, accion, observacion, dg_fecha_accion, dg_accion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_host_origen);
			st.setInt(2,id_host_padre);
			st.setString(3, tabla);
			st.setString(4, campo_id);
			st.setString(5, tipo_id);
			st.setString(6, id_tabla);
			st.setTimestamp(7, fecha_evento); 
			st.setString(8, accion);
			st.setString(9, observacion);
			st.setTimestamp(10, dg_fecha_accion1);
			st.setString(11, dg_accion1);		
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
			int k=0;
			int idCaja=0;
			double numero=0; 
			while(rsKey.next()) {
				k++;
				idCaja = rsKey.getInt(1);
				numero = rsKey.getDouble(2);
				System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
			}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarPendienteExporteacion() "+e);
			e.printStackTrace();
		}
	}

	public ArrayList<DevolucionVenta> traeDevolucionesPeriodo(int id_caja,Timestamp fechaInicial,Timestamp fechaFinal) {
		//select * from devolucion_venta where id_almacen=1 AND fecha > '2018-02-16 13:00:00' AND fecha < '2018-02-19 13:10:00'
		ArrayList<DevolucionVenta> alDevolucionVenta = new ArrayList<DevolucionVenta>(); 
		DevolucionVenta devolucionVenta;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from devolucion_venta where id_caja=? AND fecha > ? AND fecha < ?;");
			st.setInt(1, id_caja);
			st.setTimestamp(2, fechaInicial);
			st.setTimestamp(3, fechaFinal);
			ResultSet rs = st.executeQuery();
//origen_devolucion
			while (rs.next() ){
				devolucionVenta = new DevolucionVenta(
						rs.getInt("id_caja"),rs.getInt("numero"),rs.getTimestamp("fecha"),rs.getTimestamp("fecha_caducidad"),
						rs.getString("id_usuario"),rs.getString("origen"), rs.getInt("id_caja_factura"),rs.getDouble("numero_factura"),
						rs.getInt("id_cliente"),rs.getDouble("documento_cliente"), rs.getString("apellido_cliente"), rs.getString("nombre_cliente"),
						rs.getString("email_cliente"), rs.getString("telefono_cliente"), rs.getString("detalle"),rs.getDouble("valor"),
						rs.getDouble("saldo_pendiente"), rs.getString("documento_caja_generado"), rs.getInt("id_documento_caja"), rs.getInt("numero_documento_caja"),
						rs.getInt("id_caja_emision"), rs.getString("estado"), rs.getString("estado_exportacion"),rs.getTimestamp("fecha_exportacion"),
						rs.getTimestamp("fecha_contabilizacion"),rs.getLong("identificador_externo"),rs.getString("id_tipo_comprobante_contable"),
						rs.getInt("numero_comrpobante_contable"), rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"),rs.getInt("numero_impresiones"),
						rs.getString("origen_devolucion"));

				alDevolucionVenta.add(devolucionVenta);
			}
			rs.close();
			st.close();
			return alDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeDevolucionesPeriodo() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ItemDevolucionVenta> traeItemDevolucio(int numero) {
		//select * from item_devolucion_venta where numero = 6;
		ArrayList<ItemDevolucionVenta> alItemDevolucionVenta = new ArrayList<ItemDevolucionVenta>(); 
		ItemDevolucionVenta itemDevolucionVenta;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_devolucion_venta where numero = ?;");
			st.setInt(1, numero);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				itemDevolucionVenta = new ItemDevolucionVenta(
						rs.getInt("id_caja"),rs.getInt("numero"),rs.getInt("item"),rs.getInt("item_factura"),
						rs.getString("codigo_articulo"),rs.getInt("id_articulo"),rs.getInt("id_base_iva"),
						rs.getInt("id_presentacion"),rs.getInt("cantidad_presentacion"),rs.getDouble("cantidad_unidad_medida"),
						rs.getDouble("valor"),rs.getDouble("valor_iva"),rs.getString("estado_exportacion"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));				
				alItemDevolucionVenta.add(itemDevolucionVenta);
			}
			rs.close();
			st.close();
			return alItemDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeItemDevolucio() "+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<ItemDevolucionVenta> consultaArticuloSiFueDevuelto(int idDevolucionNumero, String codigo_articulo) {
		//select * from item_devolucion_venta where numero = 8 and codigo_articulo = '1010.01'
		ArrayList<ItemDevolucionVenta> alItemDevolucionVenta = new ArrayList<ItemDevolucionVenta>(); 
		ItemDevolucionVenta itemDevolucionVenta;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_devolucion_venta where numero = ? and codigo_articulo = ?;");
			st.setInt(1, idDevolucionNumero);
			st.setString(2, codigo_articulo);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				itemDevolucionVenta = new ItemDevolucionVenta(
						rs.getInt("id_caja"),rs.getInt("numero"),rs.getInt("item"),rs.getInt("item_factura"),
						rs.getString("codigo_articulo"),rs.getInt("id_articulo"),rs.getInt("id_base_iva"),
						rs.getInt("id_presentacion"),rs.getInt("cantidad_presentacion"),rs.getDouble("cantidad_unidad_medida"),
						rs.getDouble("valor"),rs.getDouble("valor_iva"),rs.getString("estado_exportacion"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));				
				alItemDevolucionVenta.add(itemDevolucionVenta);
			}
			rs.close();
			st.close();
			return alItemDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeItemDevolucio() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<DevolucionesBaseIva> traeDevolucionesBaseIva(int id_caja,Timestamp fechaInicial,Timestamp fechaFinal) {
		//select * from devolucion_venta where id_almacen=1 AND fecha > '2018-02-16 13:00:00' AND fecha < '2018-02-19 13:10:00'
		ArrayList<DevolucionesBaseIva> alDevolucionesBaseIva = new ArrayList<DevolucionesBaseIva>(); 
		DevolucionesBaseIva devolucionesBaseIva;
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT id_base_iva, SUM(valor_iva) as iva, SUM(valor) as total "
					+ "FROM item_devolucion_venta where id_caja=? and dg_fecha_accion >= ? and dg_fecha_accion <= ? "
					+ "group by id_base_iva order by id_base_iva;");
			st.setInt(1, id_caja);
			st.setTimestamp(2, fechaInicial);
			st.setTimestamp(3, fechaFinal);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				devolucionesBaseIva = new DevolucionesBaseIva(
						rs.getInt("id_base_iva"), rs.getDouble("iva"), rs.getDouble("total"));
				alDevolucionesBaseIva.add(devolucionesBaseIva);
			}
			rs.close();
			st.close();
			return alDevolucionesBaseIva;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeDevolucionesBaseIva() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public Cajero traeCajero(int id_cajero) {
		//select * from cajero where id=1;
		Cajero cajero = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from cajero where id=?;");
			st.setInt(1, id_cajero);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				cajero = new Cajero(rs.getInt("id"),rs.getInt("id_almacen"),rs.getString("id_usuario"),rs.getString("estado"),
						rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return cajero;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeItemDevolucio() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public Usuario traeUsuario(String id_usuario) {
		//select * from usuario where id = 'G.CONDE'
		Usuario usuario = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from usuario where id = ?");
			st.setString(1, id_usuario);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				usuario = new Usuario(
						rs.getString("id"),rs.getString("id_tipo_documento"),rs.getDouble("documento"),rs.getString("nombre"),
						rs.getString("apellido"),rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getString("direccion"),
						rs.getInt("id_ciudad"),rs.getString("telefono"),rs.getString("email"),rs.getDate("fecha_ingreso"),
						rs.getInt("id_almacen"), rs.getString("estado"), rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return usuario;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeUsuario() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public Articulo traeArticulo(int id_articulo) {
		//select * from articulo where id = 88
		Articulo articulo = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from articulo where id = ?;");
			st.setInt(1, id_articulo);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				articulo = new Articulo(
						rs.getInt("id"),rs.getString("nombre"),rs.getString("nombre_largo"),
						rs.getInt("id_categoria"),rs.getInt("id_unidad_medida"),rs.getInt("id_empaque"),
						rs.getInt("id_base_iva"),rs.getString("codigo"),rs.getString("estado"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return articulo;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeArticulo() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<DevolucionVenta> traeIdDevolucion(double numeroFactura, String origen_devolucion) {
		//select * from devolucion_venta where numero_factura = 445
		ArrayList<DevolucionVenta> alDevolucionVenta = new ArrayList<DevolucionVenta>(); 
		DevolucionVenta devolucionVenta;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from devolucion_venta where numero_factura = ? and origen_devolucion = ?;");
			st.setDouble(1, numeroFactura);
			st.setString(2, origen_devolucion);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				devolucionVenta = new DevolucionVenta(
						rs.getInt("id_caja"),rs.getInt("numero"),rs.getTimestamp("fecha"),rs.getTimestamp("fecha_caducidad"),
						rs.getString("id_usuario"),rs.getString("origen"), rs.getInt("id_caja_factura"),rs.getDouble("numero_factura"),
						rs.getInt("id_cliente"),rs.getDouble("documento_cliente"), rs.getString("apellido_cliente"), rs.getString("nombre_cliente"),
						rs.getString("email_cliente"), rs.getString("telefono_cliente"), rs.getString("detalle"),rs.getDouble("valor"),
						rs.getDouble("saldo_pendiente"), rs.getString("documento_caja_generado"), rs.getInt("id_documento_caja"), rs.getInt("numero_documento_caja"),
						rs.getInt("id_caja_emision"), rs.getString("estado"), rs.getString("estado_exportacion"),rs.getTimestamp("fecha_exportacion"),
						rs.getTimestamp("fecha_contabilizacion"),rs.getLong("identificador_externo"),rs.getString("id_tipo_comprobante_contable"),
						rs.getInt("numero_comrpobante_contable"), rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"),rs.getInt("numero_impresiones"),
						rs.getString("origen_devolucion"));
				alDevolucionVenta.add(devolucionVenta);
			}
			rs.close();
			st.close();
			return alDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeIdDevolucion() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<DevolucionVenta> traeDevolucionParaCopia(int numeroDevolucionFacturaRemision) {
		//select * from devolucion_venta where numero_factura = 445
		ArrayList<DevolucionVenta> alDevolucionVenta = new ArrayList<DevolucionVenta>(); 
		DevolucionVenta devolucionVenta;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from devolucion_venta where numero=?;");
			st.setInt(1, numeroDevolucionFacturaRemision);
			ResultSet rs = st.executeQuery();

			while (rs.next() ){
				devolucionVenta = new DevolucionVenta(
						rs.getInt("id_caja"),rs.getInt("numero"),rs.getTimestamp("fecha"),rs.getTimestamp("fecha_caducidad"),
						rs.getString("id_usuario"),rs.getString("origen"), rs.getInt("id_caja_factura"),rs.getDouble("numero_factura"),
						rs.getInt("id_cliente"),rs.getDouble("documento_cliente"), rs.getString("apellido_cliente"), rs.getString("nombre_cliente"),
						rs.getString("email_cliente"), rs.getString("telefono_cliente"), rs.getString("detalle"),rs.getDouble("valor"),
						rs.getDouble("saldo_pendiente"), rs.getString("documento_caja_generado"), rs.getInt("id_documento_caja"), rs.getInt("numero_documento_caja"),
						rs.getInt("id_caja_emision"), rs.getString("estado"), rs.getString("estado_exportacion"),rs.getTimestamp("fecha_exportacion"),
						rs.getTimestamp("fecha_contabilizacion"),rs.getLong("identificador_externo"),rs.getString("id_tipo_comprobante_contable"),
						rs.getInt("numero_comrpobante_contable"), rs.getTimestamp("dg_fecha_accion"),rs.getString("dg_accion"),rs.getInt("numero_impresiones"),
						rs.getString("origen_devolucion"));
				alDevolucionVenta.add(devolucionVenta);
			}
			rs.close();
			st.close();
			return alDevolucionVenta;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeIdDevolucion() "+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	public boolean buscarArticuloBtnEnter(String codigoArticulo) {
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_articulo from codigo_articulo where codigo = ? and estado = 'activo'");
			st.setString(1, codigoArticulo);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				return true;
			}else{
				System.out.println("MaestroDB.buscarArticuloBtnEnter() no existe el articulo");
				//return false;
				//articuloFactura.setId_articulo(0);
			}

			rs.close();
			st.close();
			return false;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeIdDevolucion() "+e);
			e.printStackTrace();
			return false;
		}
	}


	public void agregarRemision(int id_caja, int id_almacen, int numero_remision, int id_cajero,int id_vendedor, String id_usuario_vendedor, 
			double documento, String nombre, String apellido, String direccion, String telefono, String proveedor, double valor_remision,
			double valor_iva, int id_prefactura, int id_cliente_proveedor ) {

		String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

		// se llenan los datos a la remisión
		try {
			String query = "INSERT INTO remision(id_caja, id_almacen, numero_remision, fecha, id_cajero, id_vendedor, id_usuario_vendedor, "
					+ "documento, nombre, apellido, direccion, telefono, proveedor, valor_remision, valor_descuento, valor_iva, id_prefactura, "
					+ "numero_impresiones, comentario, estado, dg_fecha_accion, dg_accion, id_cliente_proveedor) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id_caja); // integer NOT NULL,
			st.setInt(2, id_almacen); // integer,
			st.setInt(3, numero_remision); // integer NOT NULL,
			st.setTimestamp(4, (Timestamp) fecha); // fecha timestamp without time zone,
			st.setInt(5, id_cajero); // integer,
			st.setInt(6, id_vendedor); // integer,
			st.setString(7, id_usuario_vendedor); // character varying(30),
			st.setDouble(8, documento); // numeric(20,0),
			st.setString(9, nombre); // character varying(100),
			st.setString(10, apellido); // character varying(100),
			st.setString(11, direccion); // character varying(100),
			st.setString(12, telefono); // character varying(100),
			st.setString(13, proveedor); // character varying(30),
			st.setDouble(14, valor_remision); // numeric(20,2),
			st.setDouble(15, 0); // valor_descuento numeric(20,2) DEFAULT 0,
			st.setDouble(16, valor_iva); // numeric(30,10),
			st.setInt(17, id_prefactura); // integer NOT NULL DEFAULT 0,
			st.setInt(18, 1); //numero_impresiones integer,
			st.setString(19, "Remisión"); // text DEFAULT ''::text,
			st.setString(20, "activo"); // character varying(30),
			st.setTimestamp(21, (Timestamp) fecha); // timestamp without time zone,
			st.setString(22, "insert"); // character varying(30)
			st.setInt(23, id_cliente_proveedor);

			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
					int k=0;
					int idCaja=0;
					double numero=0; 
					while(rsKey.next()) {
						k++;
						idCaja = rsKey.getInt(1);
						numero = rsKey.getDouble(2);
						System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
					}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarRemision() ERROR remision "+e);
			e.printStackTrace();
		}


		// registrar en pendiente_exportacion para sincronizar con el servidor remision
		try {
			String query = "INSERT INTO pendiente_exportacion(id_host_origen, id_host_padre, tabla, campo_id, "
					+ "tipo_id, id_tabla, fecha_evento, accion, estado, observacion, dg_fecha_accion, "
					+ "dg_accion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			//st.setInt(1, 0); // id serial NOT NULL,
			st.setInt(1, 2); // id_host_origen integer,
			st.setInt(2, 1); // id_host_padre integer,
			st.setString(3, "remision"); // tabla character varying(30),
			st.setString(4, "id_caja;numero_remision"); // campo_id character varying(50),
			st.setString(5, "int;int"); // tipo_id character varying(50),
			String idTable = id_caja+";"+ maximoRemision();
			st.setString(6, idTable); // id_tabla character varying(50),
			st.setTimestamp(7, fecha); // fecha_evento timestamp without time zone,
			//st.setTimestamp(8, ); // fecha_exportacion timestamp without time zone,
			st.setString(8, "update"); // accion character varying(30),
			st.setString(9, ""); // estado character varying(30),
			st.setString(10, ""); // observacion text,
			st.setTimestamp(11, fecha); // dg_fecha_accion timestamp without time zone,
			st.setString(12, "insert"); // dg_accion character varying(30),
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
						while(rsKey.next()) {
							itemIdPrefactura = rsKey.getInt(1);
							blnResultado = true;
						}*/
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.agregarRemision() ERROR pendiente_exportacion "+e);
			e.printStackTrace();
		}
	}


	public void pasarItemPrefacturaAItemRemision(int id_prefactura, int numero_remision, int id_caja, double valor_remision, Proveedor proveedorClase) {
		// se traen los datos de la item_preFactura 
		ItemPreFactura itemPreFactura;
		ArrayList<ItemPreFactura> alItemPreFactura = new ArrayList<ItemPreFactura>(); 
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_prefactura where id_prefactura = ? and id_caja = ?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				itemPreFactura = new ItemPreFactura();
				itemPreFactura.setId(rs.getInt(1)); // id serial NOT NULL
				itemPreFactura.setId_caja(rs.getInt(2)); // id_caja integer NOT NULL
				itemPreFactura.setId_prefactura(rs.getInt(3)); // id_prefactura integer
				itemPreFactura.setItem(rs.getInt(4)); //  item integer
				itemPreFactura.setId_vendedor(rs.getInt(5)); // id_vendedor integer
				itemPreFactura.setId_usuario_vendedor(rs.getString(6)); // id_usuario_vendedor character varying(30)
				itemPreFactura.setId_articulo(rs.getInt(7)); // id_articulo integer,
				itemPreFactura.setCodigo_articulo(rs.getString(8)); // codigo_articulo character varying(20),
				itemPreFactura.setCodigo_articulo_venta(rs.getString(9)); // codigo_articulo_venta character varying(20),
				itemPreFactura.setNombre_provisional(rs.getString(10)); // nombre_provisional character varying(60),
				itemPreFactura.setCantidad_unidad_medida(rs.getDouble(11)); // cantidad_unidad_medida numeric(10,2),
				itemPreFactura.setValor_unidad(rs.getDouble(12)); // valor_unidad numeric(30,4),
				itemPreFactura.setValor_anterior(rs.getDouble(13)); // valor_anterior numeric(30,4) NOT NULL DEFAULT 0,
				itemPreFactura.setId_presentacion(rs.getInt(14)); // id_presentacion integer,
				itemPreFactura.setCantidad_presentacion(rs.getDouble(15)); // cantidad_presentacion numeric(10,2),
				itemPreFactura.setValor_presentacion(rs.getDouble(16)); // valor_presentacion numeric(30,4),
				itemPreFactura.setValor_item(rs.getDouble(17)); // valor_item numeric(20,2),
				itemPreFactura.setId_base_iva(rs.getInt(18)); // id_base_iva integer,
				itemPreFactura.setValor_iva(rs.getDouble(19)); // valor_iva numeric(30,10),
				itemPreFactura.setPorcentaje_descuento(rs.getDouble(20)); // porcentaje_descuento numeric(5,2),
				itemPreFactura.setValor_descuento(rs.getDouble(21)); // valor_descuento numeric(20,2),
				itemPreFactura.setItem_promocion(rs.getInt(22)); // item_promocion integer,
				alItemPreFactura.add(itemPreFactura);
			}
			rs.close();
			st.close();
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() NumberFormatException ERROR "+e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() SQLException ERROR "+e);
			e.printStackTrace();
		}

		/*String sGsonItemPreFactura = "";
		gson = new Gson();
		sGsonItemPreFactura = gson.toJson(alItemPreFactura);
		System.out.println("MaestroDB.pasarPreAPos() alItemPreFactura GSON "+sGsonItemPreFactura);*/	

		// se llenan los datos de item_remisión

		String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );
		try {
			String query = "INSERT INTO item_remision(id_caja, numero_remision, item, id_vendedor, id_usuario_vendedor, id_articulo, "
					+ "codigo_articulo, codigo_articulo_venta, nombre_provisional, cantidad_unidad_medida, valor_unidad, "
					+ "valor_anterior, id_presentacion, cantidad_presentacion, valor_presentacion, valor_item, id_base_iva, "
					+ "valor_iva, porcentaje_descuento, valor_descuento, costo, costo_item, item_promocion, cantidad_um_devuelta, "
					+ "cantidad_pres_devuelta, estado_exportacion, dg_fecha_accion, dg_accion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			for (int j = 0; j < alItemPreFactura.size(); j++){
				//System.out.println("MaestroDB.pasarPreAPos() alItemPreFactura.size() "+alItemPreFactura.size()+" id_caja "+id_caja+" numero "+maximoFactura);
				st.setInt(1, id_caja); //id_caja integer NOT NULL
				st.setDouble(2, numero_remision); // numero numeric(20,0) NOT NULL
				st.setInt(3, alItemPreFactura.get(j).getItem()); // item integer NOT NULL
				st.setInt(4, alItemPreFactura.get(j).getId_vendedor()); // id_vendedor integer,
				st.setString(5, alItemPreFactura.get(j).getId_usuario_vendedor()); // id_usuario_vendedor character varying(30),
				st.setInt(6, alItemPreFactura.get(j).getId_articulo()); // id_articulo integer,
				st.setString(7, alItemPreFactura.get(j).getCodigo_articulo()); // codigo_articulo character varying(20),
				st.setString(8, alItemPreFactura.get(j).getCodigo_articulo_venta()); // codigo_articulo_venta character varying(20),
				st.setString(9, alItemPreFactura.get(j).getNombre_provisional()); // nombre_provisional character varying(60),
				st.setDouble(10, alItemPreFactura.get(j).getCantidad_unidad_medida()); // cantidad_unidad_medida numeric(10,2),
				st.setDouble(11, alItemPreFactura.get(j).getValor_unidad()); // valor_unidad numeric(30,4),
				st.setDouble(12, alItemPreFactura.get(j).getValor_anterior()); // valor_anterior numeric(30,4) NOT NULL DEFAULT 0,
				st.setInt(13, alItemPreFactura.get(j).getId_presentacion()); // id_presentacion integer,
				st.setDouble(14, alItemPreFactura.get(j).getCantidad_presentacion()); // cantidad_presentacion numeric(10,2),
				st.setDouble(15, alItemPreFactura.get(j).getValor_presentacion()); // valor_presentacion numeric(30,4),
				st.setDouble(16, alItemPreFactura.get(j).getValor_item()); // valor_item numeric(20,2),
				st.setInt(17, alItemPreFactura.get(j).getId_base_iva()); // id_base_iva integer,
				st.setDouble(18, alItemPreFactura.get(j).getValor_iva()); // valor_iva numeric(30,10),
				st.setDouble(19, alItemPreFactura.get(j).getPorcentaje_descuento()); // porcentaje_descuento numeric(5,2),
				st.setDouble(20, alItemPreFactura.get(j).getValor_descuento()); // valor_descuento numeric(20,2),
				st.setDouble(21, 0); // costo numeric(20,2),
				st.setDouble(22, 0); // costo_item numeric(20,2) NOT NULL DEFAULT 0,
				st.setInt(23, 0); // item_promocion integer,
				st.setDouble(24, 0); // cantidad_um_devuelta numeric(10,2),
				st.setDouble(25, 0); // cantidad_pres_devuelta numeric(10,2),
				st.setString(26, ""); // estado_exportacion character varying(30),
				st.setTimestamp(27, (Timestamp) fecha); // dg_fecha_accion timestamp without time zone,
				st.setString(28, "insert"); // dg_accion character varying(30),
				st.executeUpdate();

				/*ResultSet rsKey = st.getGeneratedKeys();
			while(rsKey.next()) {
				itemIdPrefactura = rsKey.getInt(1);
				blnResultado = true;
			}*/
			}
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() SQLException "+e);
			e.printStackTrace();
		}

		//TODO se hace la impresión de 2 recibos de la remision
		EncabezadoFactura encabezadoFactura = new EncabezadoFactura();		
		try {// EMPRESA
			//select * from empresa
			PreparedStatement st = conexionDB.prepareStatement("select * from empresa;");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdEmpresa(rs.getInt(1)); //id integer NOT NULL,
				encabezadoFactura.setIdRegimen(rs.getInt(2)); //  id_regimen integer,
				encabezadoFactura.setNombreEmpresa(rs.getString(3)); //  nombre character varying(50),
				encabezadoFactura.setNit(rs.getString(4)); //  nit character varying(20),
				encabezadoFactura.setRepresentanteLegal(rs.getString(5)); //  representante_legal character varying(200),
				encabezadoFactura.setDireccionEmpresa(rs.getString(6)); //direccion character varying(200),
				encabezadoFactura.setIdCiudadEmpresa(rs.getInt(7)); //  id_ciudad integer NOT NULL DEFAULT 0,
				encabezadoFactura.setTelefonoEmpresa(rs.getString(8)); //  telefono character varying(100),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {// ALMACEN
			//select * from almacen where id = 1 and estado = 'activo'
			PreparedStatement st = conexionDB.prepareStatement("select * from almacen where id = ? and estado = 'activo';");
			st.setDouble(1, G.getInstance().licenciaGlobal.getIdAlmacen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setIdAlmacen(rs.getInt(1)); // id serial NOT NULL,
				encabezadoFactura.setNombreAlmacen(rs.getString(3)); // nombre character varying(50),
				encabezadoFactura.setPrefijoAlmacen(rs.getString(4)); //  prefijo character varying(5),
				encabezadoFactura.setIdCiudadAlmacen(rs.getInt(6)); // id_ciudad integer NOT NULL DEFAULT 0,
				encabezadoFactura.setDireccionAlmacen(rs.getString(7)); //  direccion character varying(200),
				encabezadoFactura.setTelefonoAlmacen(rs.getString(8)); //  telefono character varying(100),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {// REGIMEN
			//select * from regimen where id=1;
			PreparedStatement st = conexionDB.prepareStatement("select * from regimen where id=?;");
			st.setDouble(1,encabezadoFactura.getIdRegimen());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				encabezadoFactura.setNombreRegimen(rs.getString(2)); // nombre character varying(30),
			}
			st.close();
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// si la factura tiene domicilio se actualiza en la tabla domicilio con el número de la factura en el id_factura
		// para el domicilio primero se consulta en la tabla domicilio si existe. 
		//verdedaro trae los datos y  falso clase vacia.
		Domicilio domicilio = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from domicilio where id_prefactura=? and id_caja=?");
			st.setInt(1, id_prefactura);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				domicilio = new Domicilio(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
						rs.getDouble(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getInt(13),rs.getString(14),rs.getTimestamp(15));
			}
			rs.close();
			st.close();
		} catch (NumberFormatException e) {
			System.out.println("MaestroDB.pasarPreAPos() NumberFormatException ERROR domicilio");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MaestroDB.pasarPreAPos() SQLException ERROR domicilio");
			e.printStackTrace();
		}

		if(domicilio != null) {
			try {
				//UPDATE domicilio SET id_factura=247 WHERE id_prefactura=130 and id_caja=1
				String query = "UPDATE domicilio SET id_factura=? WHERE id_prefactura=? and id_caja=?;";
				PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setDouble(1, numero_remision);
				st.setInt(2, id_prefactura);
				st.setInt(3, id_caja);
				st.executeUpdate();
				/*ResultSet rsKey = st.getGeneratedKeys();
							System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
				st.close();
				conexionDB.commit();
			} catch (SQLException e) {
				System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() ERROR "+e);
				e.printStackTrace();
			}
		}

		DatosImprimirFactura datosImprimirFactura = new DatosImprimirFactura();
		datosImprimirFactura.setEncabezadoFactura(encabezadoFactura);
		//datosImprimirFactura.setPreFactura(preFactura);
		// agrega el número de la factura

		System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() numero_remision "+numero_remision+" id_prefactura "+id_prefactura+" id_caja "+id_caja);
		//datosImprimirFactura.getPreFactura().setNumero(numero_remision);
		datosImprimirFactura.setDomicilio(domicilio);
		datosImprimirFactura.setItemPreFactura(alItemPreFactura);
		//datosImprimirFactura.setMedioPagoPreFactura(alMedioPagoPreFactura);
		//datosImprimirFactura.setIvaValor(alIvaValor);

		ImpresionFactura impresionFactura = new ImpresionFactura();

		//impresionFactura.AddCabecera(centrado(datosImprimirFactura.getEncabezadoFactura().getNombreEmpresa()+" NIT "+datosImprimirFactura.getEncabezadoFactura().getNit()));
		impresionFactura.AddCabecera(centrado(encabezadoFactura.getNombreEmpresa()+" Nit"+encabezadoFactura.getNit()));		
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera(centrado("IVA REGIMEN "+datosImprimirFactura.getEncabezadoFactura().getNombreRegimen()+" - ACT. ICA 4722"));
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("NO VALIDO COMO FACTURA"));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		/*String numeroResolucion = "RESOL. DIAN No "+datosImprimirFactura.getEncabezadoFactura().getResolucion()+" "+
				datosImprimirFactura.getEncabezadoFactura().getFechaExpedicionNumeracionAutorizada();
		numeroResolucion = numeroResolucion.substring(0, numeroResolucion.indexOf("00:00:00"));
		impresionFactura.AddCabecera(centrado(numeroResolucion));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado( "HABILITADA: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoCaja()+" del "+
				(int)datosImprimirFactura.getEncabezadoFactura().getRangoInicial()+" al "+(int)datosImprimirFactura.getEncabezadoFactura().getRangoFinal()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("FACTURA DE VENTA No.: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoCaja()+"-"+(int)datosImprimirFactura.getPreFactura().getNumero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());*/

		impresionFactura.AddCabecera(centrado("REMISION No.: "+numero_remision));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		// domicilio impremi dos facturas la segunda debe decir copia uno
		int imprimeDos = 0;
		if( datosImprimirFactura.getDomicilio()!=null) {
			imprimeDos = 2;
			String sDocumento = FormatoNumero.formatoCero(datosImprimirFactura.getDomicilio().getDocumento());
			impresionFactura.AddCabecera("NIT "+ sDocumento);
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("CLIENTE: "+datosImprimirFactura.getDomicilio().getNombre()+" "+datosImprimirFactura.getDomicilio().getApellido());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("DIRECCION "+datosImprimirFactura.getDomicilio().getDireccion());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("TELEFONO "+datosImprimirFactura.getDomicilio().getTelefono());
			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}else {
			//imprime los datos del proveedor
			imprimeDos = 2;
			String sDocumento = FormatoNumero.formatoCero(proveedorClase.getDocumento());
			impresionFactura.AddCabecera("NIT: "+sDocumento);
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("PROVEEDOR: "+proveedorClase.getNombre());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("DIRECCION "+proveedorClase.getDireccion());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("TELEFONO "+proveedorClase.getTelefono());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}

		encabezadoFactura.setNombreCajero(G.getInstance().cajero);

		String sFecha = ""+fecha;
		sFecha = sFecha.substring(0, sFecha.indexOf("."));
		impresionFactura.AddCabecera(centrado("FECHA: "+sFecha)); //datosImprimirFactura.getPreFactura().getFecha()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("CAJERO: "+encabezadoFactura.getNombreCajero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		
		if (datosImprimirFactura.getDomicilio()!=null) { //(proveedorClase.getNombre().equals("")) {
			
			impresionFactura.AddCabecera("ITEM             DESCRIPCION");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("IMP    CANT          VALOR         TOTAL");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
			try {// BASE_IVA
				// select * from base_iva
				BaseIva baseIva = null;
				ArrayList<BaseIva> alBaseIva = new ArrayList<BaseIva>();
				PreparedStatement st = conexionDB.prepareStatement("select * from base_iva;");
				/*st.setInt(1, G.getInstance().licenciaGlobal.getIdCaja());
				st.setInt(2, G.getInstance().licenciaGlobal.getIdAlmacen());*/
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					baseIva = new BaseIva();
					baseIva.setIdBaseIva(rs.getInt(1)); // id serial NOT NULL,
					baseIva.setBaseIva(rs.getDouble(2)); // base numeric(4,2),
					baseIva.setEstadoBaseIva(rs.getString(3)); // estado character varying(30),
					baseIva.setNombreBaseIva(rs.getString(7)); // nombre character varying(30),
					alBaseIva.add(baseIva);
				}
				encabezadoFactura.setBaseIva(alBaseIva);
				st.close();
				rs.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	
			// lista de articulos
	
			datosImprimirFactura.setItemPreFactura(alItemPreFactura);
	
			// Trae el nombre de los artículos
			ArticuloFactura articuloFactura = null;
			ArrayList<ArticuloFactura> alArticuloFactura = new ArrayList<ArticuloFactura>();
			for (int i = 0; i < alItemPreFactura.size(); i++) {
				articuloFactura = new ArticuloFactura();
				articuloFactura = buscarArticulo(alItemPreFactura.get(i).getCodigo_articulo(), 1, 1, "0");
				alArticuloFactura.add(articuloFactura);
				encabezadoFactura.setArticuloFactura(alArticuloFactura);
			}
	
			String sIva = "";
			for (int i = 0; i < datosImprimirFactura.getItemPreFactura().size(); i++) {
				int idBaseIvaProducto = datosImprimirFactura.getItemPreFactura().get(i).getId_base_iva();
				for (int j = 0; j < datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
					if(datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==idBaseIvaProducto) {
						sIva = datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getEstadoBaseIva();
					}
				}
	
				System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() codigo "+datosImprimirFactura.getItemPreFactura().get(i).getCodigo_articulo());
				System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() articulo "+datosImprimirFactura.getEncabezadoFactura().getArticuloFactura().get(i).getNombreArticulo());
	
				impresionFactura.AddCabecera(datosImprimirFactura.getItemPreFactura().get(i).getCodigo_articulo()+" "+datosImprimirFactura.getEncabezadoFactura().getArticuloFactura().get(i).getNombreArticulo());
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
	
				double cantidad = datosImprimirFactura.getItemPreFactura().get(i).getCantidad_unidad_medida();
				double precioUnitario = datosImprimirFactura.getItemPreFactura().get(i).getValor_unidad();
				String sPrecioUnitario = FormatoNumero.formatoCero(precioUnitario);//  FormatoNumero.formato(precioUnitario);
				double precioParcial = datosImprimirFactura.getItemPreFactura().get(i).getValor_item();
				String fPrecioParcia = FormatoNumero.formatoCero(precioParcial);
				
				String lineaPrecio = sIva+"    "+cantidad+"      "+sPrecioUnitario;
				int iLineaPrecio = lineaPrecio.length();
				int iLineaPrecioParcial = fPrecioParcia.length();
				int operacionLinea = 40-(iLineaPrecio+iLineaPrecioParcial);
				for (int k = 0; k < operacionLinea; k++) {
					lineaPrecio = lineaPrecio +" ";
				}
				lineaPrecio = lineaPrecio+fPrecioParcia;
				impresionFactura.AddCabecera(lineaPrecio);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
	
			impresionFactura.AddCabecera("========================================");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			String sValorFactura = FormatoNumero.formatoCero(valor_remision);
			impresionFactura.AddCabecera("VALOR REMISION........."+sValorFactura);
			
		} else {
			
			// Impresión sin precio, se devuleve mercancía a los proveedores
			impresionFactura.AddCabecera("ITEM             DESCRIPCION         CANT");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
			// lista de articulos
	
			datosImprimirFactura.setItemPreFactura(alItemPreFactura);
	
			// Trae el nombre de los artículos
			ArticuloFactura articuloFactura = null;
			ArrayList<ArticuloFactura> alArticuloFactura = new ArrayList<ArticuloFactura>();
			for (int i = 0; i < alItemPreFactura.size(); i++) {
				articuloFactura = new ArticuloFactura();
				articuloFactura = buscarArticulo(alItemPreFactura.get(i).getCodigo_articulo(), 1, 1, "0");
				alArticuloFactura.add(articuloFactura);
				encabezadoFactura.setArticuloFactura(alArticuloFactura);
			}
	
			//String sIva = "";
			for (int i = 0; i < datosImprimirFactura.getItemPreFactura().size(); i++) {
				
				double cantidad = datosImprimirFactura.getItemPreFactura().get(i).getCantidad_unidad_medida();
				
				String codigoNombre = datosImprimirFactura.getItemPreFactura().get(i).getCodigo_articulo()+" "+datosImprimirFactura.getEncabezadoFactura().getArticuloFactura().get(i).getNombreArticulo();
				int iCodigoNombre = 35 - codigoNombre.length();

				for (int k = 0; k < iCodigoNombre; k++) {
					codigoNombre = codigoNombre + " ";
				}
				impresionFactura.AddCabecera(codigoNombre+cantidad);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}
		
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		impresionFactura.AddCabecera("Recibido:-------------------------------");
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		// Mensajes factura
		ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
		alMensajesFactura = MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());
		for (int i = 0; i < alMensajesFactura.size(); i++) {
			if(alMensajesFactura.get(i).getUbicacion().equals("piedepagina")) {
				impresionFactura.AddCabecera(centrado(alMensajesFactura.get(i).getMensaje()));
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}

		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());

		if(imprimeDos==2) {
			impresionFactura.ImprimirDocumento(false);
			impresionFactura.AddCabecera(centrado("COPIA 1"));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.ImprimirDocumento(false);

		}else {
			System.out.println("MaestroDB.pasarItemPrefacturaAItemRemision() XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX UNOX");
			impresionFactura.ImprimirDocumento(true);
		}
		imprimeDos=0;
	}


	public void actualizaPrefacturaConRemision(int numero_remision, int id_prefactura, int id_caja){
		// se da por remitida la preFactura para que no cargue en las facturas en proceso
		try {
			//UPDATE prefactura SET estado = 'liqidada' WHERE id=169 and id_caja = 1	
			String query = "UPDATE prefactura SET numero = ?, estado = 'remision' WHERE id = ? and id_caja = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, numero_remision);
			st.setInt(2, id_prefactura);
			st.setInt(3, id_caja);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
						System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.actualizaPrefacturaConRemision() ERROR "+e);
			e.printStackTrace();
		}
	}





	public int maximoRemision() {
		// select MAX(numero_remision) from remision
		try {
			PreparedStatement st = conexionDB.prepareStatement("select MAX(numero_remision) from remision");
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				return rs.getInt(1);
			}else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int traeIdVendedorParaRemision(int id_prefactura) {
		// select id_vendedor from prefactura where id = 29
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_vendedor from prefactura where id=?;");
			st.setInt(1, id_prefactura);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				return rs.getInt(1);
			}else{
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public String traeIdUsuarioVendedorParaRemision(int id_prefactura) {
		// select id_usuario_vendedor from prefactura where id=29;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_usuario_vendedor from prefactura where id=?;");
			st.setInt(1, id_prefactura);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){
				return rs.getString(1);
			}else{
				return " ";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return " ";
		}
	}

	public Remision traeRemision(int id_caja, double numeroRemision) {
		// select * from remision where id_caja = 1 and numero_remision = 5;
		Remision remision = null;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from remision where id_caja = ? and numero_remision = ?;");
			st.setInt(1, id_caja);
			st.setDouble(2, numeroRemision);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				remision = new Remision(rs.getInt("id_caja"), rs.getInt("id_almacen"), rs.getInt("numero_remision"), rs.getDate("fecha"), rs.getInt("id_cajero"), rs.getInt("id_vendedor"),
						rs.getString("id_usuario_vendedor"), rs.getDouble("documento"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"),
						rs.getString("telefono"), rs.getString("proveedor"), rs.getDouble("valor_remision"), rs.getDouble("valor_descuento"), rs.getDouble("valor_iva"),
						rs.getInt("id_prefactura"), rs.getInt("numero_impresiones"), rs.getString("comentario"), rs.getString("estado"), rs.getDate("fecha_exportacion"),
						rs.getString("estado_exportacion"), rs.getDate("dg_fecha_accion"), rs.getString("dg_accion"), rs.getInt("id_cliente_proveedor"));
			}
			rs.close();
			st.close();
			return remision;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeFactura() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Remision> traeRemisiones(int id_caja, Timestamp fechaInicial, Timestamp fechaFinal) {
		// select * from remision where id_caja = 1 and fecha>='2018-07-31 00:00:00' and fecha<='2018-07-31 23:59:59'
		Remision remision = null;
		ArrayList<Remision> alRemision = new ArrayList<Remision>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from remision where id_caja = ? and fecha>=? and fecha<=?;");
			st.setInt(1, id_caja);
			st.setTimestamp(2, fechaInicial);
			st.setTimestamp(3, fechaFinal);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				remision = new Remision(rs.getInt("id_caja"), rs.getInt("id_almacen"), rs.getInt("numero_remision"), rs.getDate("fecha"), rs.getInt("id_cajero"), rs.getInt("id_vendedor"),
						rs.getString("id_usuario_vendedor"), rs.getDouble("documento"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"),
						rs.getString("telefono"), rs.getString("proveedor"), rs.getDouble("valor_remision"), rs.getDouble("valor_descuento"), rs.getDouble("valor_iva"),
						rs.getInt("id_prefactura"), rs.getInt("numero_impresiones"), rs.getString("comentario"), rs.getString("estado"), rs.getDate("fecha_exportacion"),
						rs.getString("estado_exportacion"), rs.getDate("dg_fecha_accion"), rs.getString("dg_accion"), rs.getInt("id_cliente_proveedor"));
				alRemision.add(remision);
			}
			rs.close();
			st.close();
			return alRemision;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeRemisiones() ERROR "+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ArrayList<ItemRemision> traerItemRemision(int id_caja, int numeroRemision) {
		// select * from item_remision where id_caja = 1 and numero_remision = 8;
		ItemRemision itemRemision = null;
		ArrayList<ItemRemision> alItemRemision = new ArrayList<ItemRemision>();
		try {
			PreparedStatement st = conexionDB.prepareStatement("select * from item_remision where id_caja = ? and numero_remision = ?;");
			st.setInt(1, id_caja);
			st.setDouble(2, numeroRemision);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				itemRemision = new ItemRemision(rs.getInt("id_caja"), rs.getDouble("numero_remision"), rs.getInt("item"), rs.getInt("id_vendedor"), rs.getString("id_usuario_vendedor"),
						rs.getInt("id_articulo"), rs.getString("codigo_articulo"), rs.getString("codigo_articulo_venta"), rs.getString("nombre_provisional"),
						rs.getDouble("cantidad_unidad_medida"), rs.getDouble("valor_unidad"), rs.getDouble("valor_anterior"), rs.getInt("id_presentacion"),
						rs.getDouble("cantidad_presentacion"), rs.getDouble("valor_presentacion"), rs.getDouble("valor_item"), rs.getInt("id_base_iva"),
						rs.getDouble("valor_iva"), rs.getDouble("porcentaje_descuento"), rs.getDouble("valor_descuento"), rs.getDouble("costo"), rs.getDouble("costo_item"),
						rs.getInt("item_promocion"), rs.getDouble("cantidad_um_devuelta"), rs.getDouble("cantidad_pres_devuelta"), rs.getString("estado_exportacion"),
						rs.getDate("dg_fecha_accion"), rs.getString("dg_accion"));
				alItemRemision.add(itemRemision);
			}
			rs.close();
			st.close();
			return alItemRemision;
		}catch (Exception e) {
			System.out.println("MaestroDB.traerItemFactura() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public boolean comprobanteDiarioCaja(int id_caja, int id_usuario) {

		try {
			String query = "INSERT INTO comprobante_diario_caja(id_caja, numero, fecha, id_usuario, fecha_impresion, "
					+ "dg_fecha_accion, dg_accion) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id_caja); // id_caja
			int numero = maximoComprobanteDiarioCaja()+1;
			st.setInt(2, numero); // numero 
			String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );
			st.setTimestamp(3, (Timestamp) fecha); // timestamp without time zone,
			st.setInt(4, id_usuario);
			st.setTimestamp(5, fecha);
			st.setTimestamp(6, fecha); // dg_fecha_accion
			st.setString(7, "insert"); // dg_accion
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
					int k=0;
					int idCaja=0;
					double numero=0; 
					while(rsKey.next()) {
						k++;
						idCaja = rsKey.getInt(1);
						numero = rsKey.getDouble(2);
						System.out.println("MaestroDB.pasarPreAPos() k="+k+" idCaja="+idCaja+" numero="+numero);
					}*/
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.comprobanteDiarioCaja() "+e);
			e.printStackTrace();
		}
		return false;
	}

	private int maximoComprobanteDiarioCaja() {
		int maximoComprobanteDiarioCaja=0;
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT max(numero) FROM comprobante_diario_caja;");
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				maximoComprobanteDiarioCaja = rs.getInt(1);
			}
			rs.close();
			st.close();
			return maximoComprobanteDiarioCaja;
		}catch (Exception e) {
			System.out.println("MaestroDB.maximoComprobanteDiarioCaja() "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public boolean impresoReporteZ(int id_caja) {
		try {
			String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

			PreparedStatement st = conexionDB.prepareStatement("SELECT id_caja FROM comprobante_diario_caja WHERE id_caja=? AND fecha=?;");
			st.setInt(1, id_caja);
			st.setTimestamp(2, (Timestamp) fecha);

			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				if(id_caja == rs.getInt("id_caja")) {
					System.out.println("MaestroDB.impresoReporteZ() fecha "+fecha+" id_cajaRS "+rs.getInt("id_caja")+" id_caja "+id_caja);
					return true;
				}else {
					return false;
				}
			}
			rs.close();
			st.close();
			return false;
		}catch (Exception e) {
			System.out.println("MaestroDB.impresoReporteZ() "+e);
			e.printStackTrace();
			return false;
		}
	}

	public NumeracionAutorizada traeResolucionCopiaFactura(double numeroFactura) {
		NumeracionAutorizada numeracionAutorizada = null;
		int idNumeracionAutorizada = 0;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select id_numeracion_autorizada from factura where numero = ?;");
			st.setDouble(1, numeroFactura);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				idNumeracionAutorizada = rs.getInt("id_numeracion_autorizada");
			}
		}catch (Exception e) {
			System.out.println("MaestroDB.traeResolucionCopiaFactura() "+e);
			e.printStackTrace();
		}

		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT * FROM numeracion_autorizada WHERE id=?;");
			st.setDouble(1, idNumeracionAutorizada);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				numeracionAutorizada = new NumeracionAutorizada(
						rs.getInt("id"),rs.getInt("id_caja"),rs.getString("resolucion"),rs.getDate("fecha_expedicion"),
						rs.getString("tipo_facturacion"),rs.getString("prefijo"),rs.getDouble("rango_inicial"),
						rs.getDouble("rango_final"), rs.getDouble("numero_actual"),rs.getDate("fecha_caducidad"),
						rs.getTimestamp("fecha_exportacion"),rs.getString("estado"),rs.getTimestamp("dg_fecha_accion"),
						rs.getString("dg_accion"));
			}
			rs.close();
			st.close();
			return numeracionAutorizada;
		}catch (Exception e) {
			System.out.println("MaestroDB.traeResolucionCopiaFactura() "+e);
			e.printStackTrace();
			return null;
		}
	}

	public int actualizaNumeroCopias(double numeroFactura) {
		int numeroCopias = 0;
		try {
			PreparedStatement st = conexionDB.prepareStatement("select numero_impresiones from factura where numero = ?;");
			st.setDouble(1, numeroFactura);
			ResultSet rs = st.executeQuery();
			while (rs.next() ){
				numeroCopias = rs.getInt("numero_impresiones");
			}
		}catch (Exception e) {
			System.out.println("MaestroDB.actualizaNumeroCopias() "+e);
			e.printStackTrace();
		}

		try {
			if(numeroCopias==0) {
				numeroCopias=2;
			} else {
				numeroCopias++;
			}
			String query = "UPDATE factura SET numero_impresiones=? WHERE numero = ?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, numeroCopias);
			st.setDouble(2, numeroFactura);
			st.executeUpdate();
			/*ResultSet rsKey = st.getGeneratedKeys();
				System.out.println("MaestroDB.borraItemPreFactura() UPDATE ResultSet "+rsKey.getCursorName());*/
			st.close();
			conexionDB.commit();
			return numeroCopias;
		} catch (SQLException e) {
			System.out.println("MaestroDB.actualizaNumeroCopias() "+e);
			e.printStackTrace();
			return 2;
		}
	}

	public double traeTotalPrefactura(int idPreFacturaActual, int id_caja) {
		// Trae el total facturado de la prefactura para mostrar en el display
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT sum(valor_item) FROM item_prefactura WHERE id_prefactura = ? AND id_caja=?;");
			st.setInt(1, idPreFacturaActual);
			st.setInt(2, id_caja);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){			
				return rs.getDouble(1);
			}else{
				return 0;
			}
		} catch (SQLException e) {
			return 0;
		}
	}

	public Timestamp traeFechayHoraRemision(int numero_remision) {
		// devuleve la fecha y hora en que se realizó la remisión para colocarla en la devolución
		//select fecha from remision where numero_remision = 80
		try {
			PreparedStatement st = conexionDB.prepareStatement("select fecha from remision where numero_remision = ?;");
			st.setInt(1, numero_remision);
			ResultSet rs = st.executeQuery();
			if (rs.next() ){			
				return rs.getTimestamp(1);
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Timestamp traeFechayHoraDevolucionRemision() {
		// devuleve la fecha y hora en que se realizó la devolucion de la remisión para colocarla en la devolución
		//SELECT fecha FROM devolucion_venta order by numero DESC;
		try {
			PreparedStatement st = conexionDB.prepareStatement("SELECT fecha FROM devolucion_venta order by numero DESC;");
			ResultSet rs = st.executeQuery();
			if (rs.next() ){			
				return rs.getTimestamp(1);
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	public void actualizaExtranjera(double extranjera) {
		try {
			String query = "UPDATE entidad_bancaria SET comision=? WHERE nombre=?;";
			PreparedStatement st = conexionDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, extranjera);
			st.setString(2, "DOLAR AMERICANO");
			st.executeUpdate();
			st.close();
			conexionDB.commit();
		} catch (SQLException e) {
			System.out.println("MaestroDB.actualizaExtranjera() "+e);
			e.printStackTrace();
		}
	}

}

class TotalMedioPago{
	private int idMedioPago;
	private double valorMedioPago;
	private double totaIvaMedioPago;
	public TotalMedioPago(int idMedioPago, double valorMedioPago, double totaIvaMedioPago) {
		super();
		this.idMedioPago = idMedioPago;
		this.valorMedioPago = valorMedioPago;
		this.totaIvaMedioPago = totaIvaMedioPago;
	}
	public int getIdMedioPago() {
		return idMedioPago;
	}
	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	public double getValorMedioPago() {
		return valorMedioPago;
	}
	public void setValorMedioPago(double valorMedioPago) {
		this.valorMedioPago = valorMedioPago;
	}
	public double getTotaIvaMedioPago() {
		return totaIvaMedioPago;
	}
	public void setTotaIvaMedioPago(double totaIvaMedioPago) {
		this.totaIvaMedioPago = totaIvaMedioPago;
	}
}

