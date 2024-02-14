package clases;

import java.sql.Timestamp;

public class MedioPago {

	 private int id; // serial NOT NULL,
	 private String nombre; // character varying(30),
	 private String estado; // character varying(30),
	 private Timestamp fecha_exportacion; // timestamp without time zone,
	 private Timestamp dg_fecha_accion; // timestamp without time zone,
	 private String dg_accion; // character varying(30),
	 
	public MedioPago(int id, String nombre, String estado, Timestamp fecha_exportacion, Timestamp dg_fecha_accion,
			String dg_accion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getFecha_exportacion() {
		return fecha_exportacion;
	}
	public void setFecha_exportacion(Timestamp fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
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
