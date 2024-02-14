package clases;

import java.sql.Timestamp;

public class Cajero {

	private int id; // serial NOT NULL,
	private int id_almacen; // integer,
	private String id_usuario; // character varying(30),
	private String estado; // character varying(30),
	private Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public Cajero(int id, int id_almacen, String id_usuario, String estado, Timestamp dg_fecha_accion,
			String dg_accion) {
		super();
		this.id = id;
		this.id_almacen = id_almacen;
		this.id_usuario = id_usuario;
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
	public int getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
