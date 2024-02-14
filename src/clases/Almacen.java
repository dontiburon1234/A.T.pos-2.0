package clases;

import java.util.Date;

public class Almacen {
	
	private int id; // serial NOT NULL,
	private int id_host; // integer DEFAULT 0,
	private String nombre; // character varying(50),
	private String prefijo; // character varying(5),
	private String prefijo_secundario; // character varying(5),
	private int id_ciudad; // integer NOT NULL DEFAULT 0,
	private String direccion; // character varying(200),
	private String telefono; // character varying(100),
	private String estado; // character varying(10),
	private String division; // character varying(10),
	private Date fecha_exportacion; // timestamp without time zone,
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public Almacen() {}
	
	public Almacen(int id, int id_host, String nombre, String prefijo, String prefijo_secundario, int id_ciudad,
			String direccion, String telefono, String estado, String division, Date fecha_exportacion,
			Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_host = id_host;
		this.nombre = nombre;
		this.prefijo = prefijo;
		this.prefijo_secundario = prefijo_secundario;
		this.id_ciudad = id_ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = estado;
		this.division = division;
		this.fecha_exportacion = fecha_exportacion;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_host() {
		return id_host;
	}
	public void setId_host(int id_host) {
		this.id_host = id_host;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	public String getPrefijo_secundario() {
		return prefijo_secundario;
	}
	public void setPrefijo_secundario(String prefijo_secundario) {
		this.prefijo_secundario = prefijo_secundario;
	}
	public int getId_ciudad() {
		return id_ciudad;
	}
	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Date getFecha_exportacion() {
		return fecha_exportacion;
	}
	public void setFecha_exportacion(Date fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
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
