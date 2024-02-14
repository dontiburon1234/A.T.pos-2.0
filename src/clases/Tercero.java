package clases;

import java.util.Date;

public class Tercero {
	private int id;
	private String id_tipo_documento;
	private long documento;
	private String nombre;
	private String apellido;
	private String direccion;
	private int id_ciudad;
	private String telefono;
	private String email;
	private int id_regimen;
	private String comentario;
	private Date fecha_ingreso;
	private String estado;
	private int id_lista_precio;
	
	public Tercero() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getId_regimen() {
		return id_regimen;
	}
	public void setId_regimen(int id_regimen) {
		this.id_regimen = id_regimen;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId_lista_precio() {
		return id_lista_precio;
	}
	public void setId_lista_precio(int id_lista_precio) {
		this.id_lista_precio = id_lista_precio;
	}
}
