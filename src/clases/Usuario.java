package clases;

import java.sql.Timestamp;
import java.util.Date;

public class Usuario {

	private String id; // character varying(30) NOT NULL,
	private String id_tipo_documento; // character varying(10) NOT NULL,
	private double documento; // numeric(20,0) NOT NULL,
	private String nombre; // character varying(100) NOT NULL,
	private String apellido; // character varying(100) NOT NULL,
	private String password; // character varying(100),
	private Date fecha_nacimiento; // date,
	private String direccion; // character varying(200),
	private int id_ciudad; // integer NOT NULL DEFAULT 0,
	private String telefono; // character varying(100),
	private String email; // character varying(200),
	private Date fecha_ingreso; // date,
	private int id_almacen; // integer NOT NULL DEFAULT 0,
	private String estado; // character varying(30),
	private Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public Usuario(String id, String id_tipo_documento, double documento, String nombre, String apellido, String password,
			Date fecha_nacimiento, String direccion, int id_ciudad, String telefono, String email, Date fecha_ingreso,
			int id_almacen, String estado, Timestamp dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_tipo_documento = id_tipo_documento;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.id_ciudad = id_ciudad;
		this.telefono = telefono;
		this.email = email;
		this.fecha_ingreso = fecha_ingreso;
		this.id_almacen = id_almacen;
		this.estado = estado;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_tipo_documento() {
		return id_tipo_documento;
	}
	public void setId_tipo_documento(String id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public int getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
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
