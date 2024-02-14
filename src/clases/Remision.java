package clases;

import java.sql.Date;

public class Remision {

	private int id_caja; // integer NOT NULL,
	private int id_almacen; // integer,
	private int numero_remision; // integer NOT NULL,
	private Date fecha; // timestamp without time zone,
	private int id_cajero; // integer,
	private int id_vendedor; // integer,
	private String id_usuario_vendedor; // character varying(30),
	private double documento; // numeric(20,0),
	private String nombre; // character varying(100),
	private String apellido; // character varying(100),
	private String direccion; // character varying(100),
	private String telefono; // character varying(100),
	private String proveedor; // character varying(30),
	private double valor_remision; // numeric(20,2),
	private double valor_descuento; // numeric(20,2) DEFAULT 0,
	private double valor_iva; // numeric(30,10),
	private int id_prefactura; // integer NOT NULL DEFAULT 0,
	private int numero_impresiones; // integer,
	private String comentario; // text DEFAULT ''::text,
	private String estado; // character varying(30),
	private Date fecha_exportacion; // timestamp without time zone,
	private String estado_exportacion; // character varying(30),
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30)
	private int id_cliente_proveedor; // integer

	public Remision(int id_caja, int id_almacen, int numero_remision, Date fecha, int id_cajero, int id_vendedor,
			String id_usuario_vendedor, double documento, String nombre, String apellido, String direccion,
			String telefono, String proveedor, double valor_remision, double valor_descuento, double valor_iva,
			int id_prefactura, int numero_impresiones, String comentario, String estado, Date fecha_exportacion,
			String estado_exportacion, Date dg_fecha_accion, String dg_accion, int id_cliente_proveedor) {
		super();
		this.id_caja = id_caja;
		this.id_almacen = id_almacen;
		this.numero_remision = numero_remision;
		this.fecha = fecha;
		this.id_cajero = id_cajero;
		this.id_vendedor = id_vendedor;
		this.id_usuario_vendedor = id_usuario_vendedor;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.proveedor = proveedor;
		this.valor_remision = valor_remision;
		this.valor_descuento = valor_descuento;
		this.valor_iva = valor_iva;
		this.id_prefactura = id_prefactura;
		this.numero_impresiones = numero_impresiones;
		this.comentario = comentario;
		this.estado = estado;
		this.fecha_exportacion = fecha_exportacion;
		this.estado_exportacion = estado_exportacion;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
		this.id_cliente_proveedor = id_cliente_proveedor;
	}
	public int getId_caja() {
		return id_caja;
	}
	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}
	public int getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}
	public int getNumero_remision() {
		return numero_remision;
	}
	public void setNumero_remision(int numero_remision) {
		this.numero_remision = numero_remision;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getId_cajero() {
		return id_cajero;
	}
	public void setId_cajero(int id_cajero) {
		this.id_cajero = id_cajero;
	}
	public int getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public String getId_usuario_vendedor() {
		return id_usuario_vendedor;
	}
	public void setId_usuario_vendedor(String id_usuario_vendedor) {
		this.id_usuario_vendedor = id_usuario_vendedor;
	}
	public double getDocumento() {
		return documento;
	}
	public void setDocumento(double documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public double getValor_remision() {
		return valor_remision;
	}
	public void setValor_remision(double valor_remision) {
		this.valor_remision = valor_remision;
	}
	public double getValor_descuento() {
		return valor_descuento;
	}
	public void setValor_descuento(double valor_descuento) {
		this.valor_descuento = valor_descuento;
	}
	public double getValor_iva() {
		return valor_iva;
	}
	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}
	public int getId_prefactura() {
		return id_prefactura;
	}
	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
	}
	public int getNumero_impresiones() {
		return numero_impresiones;
	}
	public void setNumero_impresiones(int numero_impresiones) {
		this.numero_impresiones = numero_impresiones;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha_exportacion() {
		return fecha_exportacion;
	}
	public void setFecha_exportacion(Date fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
	}
	public String getEstado_exportacion() {
		return estado_exportacion;
	}
	public void setEstado_exportacion(String estado_exportacion) {
		this.estado_exportacion = estado_exportacion;
	}
	public Date getDg_fecha_accion() {
		return dg_fecha_accion;
	}
	public void setDg_fecha_accion(Date dg_fecha_accion) {
		this.dg_fecha_accion = dg_fecha_accion;
	}
	public String getDg_accion() {
		return dg_accion;
	}
	public void setDg_accion(String dg_accion) {
		this.dg_accion = dg_accion;
	}
	public int getId_cliente_proveedor() {
		return id_cliente_proveedor;
	}
	public void setId_cliente_proveedor(int id_cliente_proveedor) {
		this.id_cliente_proveedor = id_cliente_proveedor;
	}


}
