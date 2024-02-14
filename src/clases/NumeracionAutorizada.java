package clases;

import java.sql.Timestamp;
import java.util.Date;

public class NumeracionAutorizada {
	
	private int id; // serial NOT NULL,
	private int id_caja; // integer,
	private String resolucion; // character varying(40),
	private Date fecha_expedicion; // date,
	private String tipo_facturacion; // character varying(30),
	private String prefijo; // character varying(10),
	private double rango_inicial; // numeric(20,0),
	private double rango_final; // numeric(20,0),
	private double numero_actual; // numeric(20,0),
	private Date fecha_caducidad; // date,
	private Timestamp fecha_exportacion; // timestamp without time zone,
	private String estado; // character varying(30),
	private Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public NumeracionAutorizada(int id, int id_caja, String resolucion, Date fecha_expedicion, String tipo_facturacion,
			String prefijo, double rango_inicial, double rango_final, double numero_actual, Date fecha_caducidad,
			Timestamp fecha_exportacion, String estado, Timestamp dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_caja = id_caja;
		this.resolucion = resolucion;
		this.fecha_expedicion = fecha_expedicion;
		this.tipo_facturacion = tipo_facturacion;
		this.prefijo = prefijo;
		this.rango_inicial = rango_inicial;
		this.rango_final = rango_final;
		this.numero_actual = numero_actual;
		this.fecha_caducidad = fecha_caducidad;
		this.fecha_exportacion = fecha_exportacion;
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

	public int getId_caja() {
		return id_caja;
	}

	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public Date getFecha_expedicion() {
		return fecha_expedicion;
	}

	public void setFecha_expedicion(Date fecha_expedicion) {
		this.fecha_expedicion = fecha_expedicion;
	}

	public String getTipo_facturacion() {
		return tipo_facturacion;
	}

	public void setTipo_facturacion(String tipo_facturacion) {
		this.tipo_facturacion = tipo_facturacion;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public double getRango_inicial() {
		return rango_inicial;
	}

	public void setRango_inicial(double rango_inicial) {
		this.rango_inicial = rango_inicial;
	}

	public double getRango_final() {
		return rango_final;
	}

	public void setRango_final(double rango_final) {
		this.rango_final = rango_final;
	}

	public double getNumero_actual() {
		return numero_actual;
	}

	public void setNumero_actual(double numero_actual) {
		this.numero_actual = numero_actual;
	}

	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}

	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public Timestamp getFecha_exportacion() {
		return fecha_exportacion;
	}

	public void setFecha_exportacion(Timestamp fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
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
