package clases;

import java.util.Date;

public class Domicilio {

	private int id;
	private int id_cliente; // integer,
	private int id_caja; // integer,
	private int id_almacen; //integer
	private int id_factura; // integer,
	private int id_prefactura; // integer NOT NULL DEFAULT 0,
	private double documento; // numeric(20,0),
	private String nombre; // character varying(100),
	private String apellido; // character varying(100),
	private String direccion; // character varying(100),
	private String telefono; // character varying(100),
	private String listadoPedido; // text
	private int id_domiciliario; // integer,
	private String nombre_domiciliario; // text,
	private Date dg_fecha_accion; // timestamp without time zone,
	
	public Domicilio(int id, int id_cliente, int id_caja, int id_almacen, int id_factura, int id_prefactura,
			double documento, String nombre, String apellido, String direccion, String telefono, String listadoPedido,
			int id_domiciliario, String nombre_domiciliario, Date dg_fecha_accion) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_caja = id_caja;
		this.id_almacen = id_almacen;
		this.id_factura = id_factura;
		this.id_prefactura = id_prefactura;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.listadoPedido = listadoPedido;
		this.id_domiciliario = id_domiciliario;
		this.nombre_domiciliario = nombre_domiciliario;
		this.dg_fecha_accion = dg_fecha_accion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
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
	public int getId_factura() {
		return id_factura;
	}
	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	public int getId_prefactura() {
		return id_prefactura;
	}
	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
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
	public String getListadoPedido() {
		return listadoPedido;
	}
	public void setListadoPedido(String listadoPedido) {
		this.listadoPedido = listadoPedido;
	}
	public int getId_domiciliario() {
		return id_domiciliario;
	}
	public void setId_domiciliario(int id_domiciliario) {
		this.id_domiciliario = id_domiciliario;
	}
	public String getNombre_domiciliario() {
		return nombre_domiciliario;
	}
	public void setNombre_domiciliario(String nombre_domiciliario) {
		this.nombre_domiciliario = nombre_domiciliario;
	}
	public Date getDg_fecha_accion() {
		return dg_fecha_accion;
	}
	public void setDg_fecha_accion(Date dg_fecha_accion) {
		this.dg_fecha_accion = dg_fecha_accion;
	}
}
