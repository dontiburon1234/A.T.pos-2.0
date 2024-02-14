package clases;

import java.util.Date;

public class Regimen {

	private int id; // serial NOT NULL,
	private String nombre; // character varying(30),
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	

	public Regimen(int id, String nombre, Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
