package clases;

import java.sql.Date;

public class MensajeDocumento {
	
	private int id; // serial NOT NULL,
	private int id_documento_pos; // integer,
	private String mensaje; // text,
	private String estado; // character varying(30),
	private Date fecha_exportacion; // timestamp without time zone,
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	private String ubicacion; // character varying(30),
	
	public MensajeDocumento(int id, int id_documento_pos, String mensaje, String estado, Date fecha_exportacion,
			Date dg_fecha_accion, String dg_accion, String ubicacion) {
		super();
		this.id = id;
		this.id_documento_pos = id_documento_pos;
		this.mensaje = mensaje;
		this.estado = estado;
		this.fecha_exportacion = fecha_exportacion;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
		this.ubicacion = ubicacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_documento_pos() {
		return id_documento_pos;
	}
	public void setId_documento_pos(int id_documento_pos) {
		this.id_documento_pos = id_documento_pos;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
