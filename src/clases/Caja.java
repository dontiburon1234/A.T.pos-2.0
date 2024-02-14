package clases;

import java.util.Date;

public class Caja {

	private int id; // serial NOT NULL,
	private int id_almacen; // integer,
	private int id_host; // integer DEFAULT 0,
	private int id_caja_padre; // integer DEFAULT 0,
	private String nombre; // character varying(200),
	private String prefijo; // character varying(10),
	private String impresora; // character varying(50),
	private String estado; // character varying(30),
	private Date fecha_exportacion; // timestamp without time zone,
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}
	public int getId_host() {
		return id_host;
	}
	public void setId_host(int id_host) {
		this.id_host = id_host;
	}
	public int getId_caja_padre() {
		return id_caja_padre;
	}
	public void setId_caja_padre(int id_caja_padre) {
		this.id_caja_padre = id_caja_padre;
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
	public String getImpresora() {
		return impresora;
	}
	public void setImpresora(String impresora) {
		this.impresora = impresora;
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
