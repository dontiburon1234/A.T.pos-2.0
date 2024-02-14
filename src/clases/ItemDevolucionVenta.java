package clases;

import java.sql.Timestamp;

public class ItemDevolucionVenta {

	private int id_caja; // integer,
	private int numero; // integer,
	private int item; // integer,
	private int item_factura; // integer,
	private String codigo_articulo; // character varying(20),
	private int id_articulo; // integer,
	private int id_base_iva; // integer,
	private int id_presentacion; // integer,
	private int cantidad_presentacion; // integer,
	private double cantidad_unidad_medida; // numeric(20,3),
	private double valor; // numeric(20,2),
	private double valor_iva; // numeric(30,10),
	private String estado_exportacion; // character varying(30),
	private Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30)
	
	public ItemDevolucionVenta(int id_caja, int numero, int item, int item_factura, String codigo_articulo,
			int id_articulo, int id_base_iva, int id_presentacion, int cantidad_presentacion,
			double cantidad_unidad_medida, double valor, double valor_iva, String estado_exportacion,
			Timestamp dg_fecha_accion, String dg_accion) {
		super();
		this.id_caja = id_caja;
		this.numero = numero;
		this.item = item;
		this.item_factura = item_factura;
		this.codigo_articulo = codigo_articulo;
		this.id_articulo = id_articulo;
		this.id_base_iva = id_base_iva;
		this.id_presentacion = id_presentacion;
		this.cantidad_presentacion = cantidad_presentacion;
		this.cantidad_unidad_medida = cantidad_unidad_medida;
		this.valor = valor;
		this.valor_iva = valor_iva;
		this.estado_exportacion = estado_exportacion;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}
	public int getId_caja() {
		return id_caja;
	}
	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getItem_factura() {
		return item_factura;
	}
	public void setItem_factura(int item_factura) {
		this.item_factura = item_factura;
	}
	public String getCodigo_articulo() {
		return codigo_articulo;
	}
	public void setCodigo_articulo(String codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}
	public int getId_articulo() {
		return id_articulo;
	}
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}
	public int getId_base_iva() {
		return id_base_iva;
	}
	public void setId_base_iva(int id_base_iva) {
		this.id_base_iva = id_base_iva;
	}
	public int getId_presentacion() {
		return id_presentacion;
	}
	public void setId_presentacion(int id_presentacion) {
		this.id_presentacion = id_presentacion;
	}
	public int getCantidad_presentacion() {
		return cantidad_presentacion;
	}
	public void setCantidad_presentacion(int cantidad_presentacion) {
		this.cantidad_presentacion = cantidad_presentacion;
	}
	public double getCantidad_unidad_medida() {
		return cantidad_unidad_medida;
	}
	public void setCantidad_unidad_medida(double cantidad_unidad_medida) {
		this.cantidad_unidad_medida = cantidad_unidad_medida;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValor_iva() {
		return valor_iva;
	}
	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}
	public String getEstado_exportacion() {
		return estado_exportacion;
	}
	public void setEstado_exportacion(String estado_exportacion) {
		this.estado_exportacion = estado_exportacion;
	}
	public Timestamp getDg_fecha_accion() {
		return dg_fecha_accion;
	}
	public void setDg_fecha_accion(Timestamp dg_fecha_accion) {
		this.dg_fecha_accion = dg_fecha_accion;
	}
	public String getDg_accion() {
		return dg_accion;
	}
	public void setDg_accion(String dg_accion) {
		this.dg_accion = dg_accion;
	}

	

}
