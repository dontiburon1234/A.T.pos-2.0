package clases;

import java.util.Date;

public class Empresa {

	private int id; // integer NOT NULL,
	private int id_regimen; // integer,
	private String nombre; // character varying(50),
	private String nit; // character varying(20),
	private String representante_legal; // character varying(200),
	private String direccion; // character varying(200),
	private int id_ciudad; // integer NOT NULL DEFAULT 0,
	private String telefono; // character varying(100),
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	public Empresa(int id, int id_regimen, String nombre, String nit, String representante_legal, String direccion,
			int id_ciudad, String telefono, Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_regimen = id_regimen;
		this.nombre = nombre;
		this.nit = nit;
		this.representante_legal = representante_legal;
		this.direccion = direccion;
		this.id_ciudad = id_ciudad;
		this.telefono = telefono;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_regimen() {
		return id_regimen;
	}
	public void setId_regimen(int id_regimen) {
		this.id_regimen = id_regimen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
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
