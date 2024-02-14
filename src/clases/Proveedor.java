package clases;

import java.util.Date;

public class Proveedor {

	private int id; // serial NOT NULL,
	private String codigo; // character varying(30),
	private String id_tipo_documento; // character varying(10) NOT NULL,
	private long documento; // bigint NOT NULL,
	private int digito_verificacion; // integer NOT NULL DEFAULT 0,
	private String nombre; // character varying(100),
	private String nombre_comercial; // character varying(200),
	private String representante_legal; // character varying(200),
	private String direccion; // character varying(200),
	private int id_ciudad; // integer NOT NULL DEFAULT 0,
	private String telefono; // character varying(100),
	private String email; // character varying(200),
	private String web; // character varying(200),
	private int id_regimen; // integer,
	private double valor_minimo_compra; // bigint,
	private String descripcion; // text,
	private String estado; // character varying(30),
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public Proveedor() {}
	
	public Proveedor(int id, String codigo, String id_tipo_documento, long documento, int digito_verificacion,
			String nombre, String nombre_comercial, String representante_legal, String direccion, int id_ciudad,
			String telefono, String email, String web, int id_regimen, double valor_minimo_compra, String descripcion,
			String estado, Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;								// obligatorio automático
		this.codigo = codigo;
		this.id_tipo_documento = id_tipo_documento; // obligatorio
		this.documento = documento;					// obligatorio
		this.digito_verificacion = digito_verificacion;
		this.nombre = nombre;
		this.nombre_comercial = nombre_comercial;
		this.representante_legal = representante_legal;
		this.direccion = direccion;
		this.id_ciudad = id_ciudad;					// obligatorio
		this.telefono = telefono;
		this.email = email;
		this.web = web;
		this.id_regimen = id_regimen;
		this.valor_minimo_compra = valor_minimo_compra;
		this.descripcion = descripcion;
		this.estado = estado;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getId_tipo_documento() {
		return id_tipo_documento;
	}
	public void setId_tipo_documento(String id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}
	public long getDocumento() {
		return documento;
	}
	public void setDocumento(long documento) {
		this.documento = documento;
	}
	public int getDigito_verificacion() {
		return digito_verificacion;
	}
	public void setDigito_verificacion(int digito_verificacion) {
		this.digito_verificacion = digito_verificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_comercial() {
		return nombre_comercial;
	}
	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}
	public String getRepresentante_legal() {
		return representante_legal;
	}
	public void setRepresentante_legal(String representante_legal) {
		this.representante_legal = representante_legal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getId_ciudad() {
		return id_ciudad;
	}
	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public int getId_regimen() {
		return id_regimen;
	}
	public void setId_regimen(int id_regimen) {
		this.id_regimen = id_regimen;
	}
	public double getValor_minimo_compra() {
		return valor_minimo_compra;
	}
	public void setValor_minimo_compra(double valor_minimo_compra) {
		this.valor_minimo_compra = valor_minimo_compra;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	
}
