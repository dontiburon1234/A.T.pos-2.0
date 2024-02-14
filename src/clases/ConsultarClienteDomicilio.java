package clases;

public class ConsultarClienteDomicilio {

	private int id; // serial NOT NULL,
	private double documento; // numeric(20,0) NOT NULL,
	private String nombre; // character varying(100) DEFAULT ''::character varying,
	private String apellido; // character varying(100) DEFAULT ''::character varying,
	private String direccion; // character varying(200) DEFAULT ''::character varying,
	private int id_ciudad; // integer NOT NULL DEFAULT 0,
	private String telefono; // character varying(100) DEFAULT ''::character varying,
	private String email; // character varying(200) DEFAULT ''::character varying,
	private String cumpleanos; // character varying(10) DEFAULT ''::character varying,
	private String comentario; // text DEFAULT ''::text,
	private String fecha_ingreso; // date,
	private String estado; // character varying(30),
	private int id_lista_precio; // integer,
	
	public ConsultarClienteDomicilio(int id, double documento, String nombre, String apellido, String direccion,
			int id_ciudad, String telefono, String email, String cumpleanos, String comentario, String fecha_ingreso,
			String estado, int id_lista_precio) {
		super();
		this.id = id;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.id_ciudad = id_ciudad;
		this.telefono = telefono;
		this.email = email;
		this.cumpleanos = cumpleanos;
		this.comentario = comentario;
		this.fecha_ingreso = fecha_ingreso;
		this.estado = estado;
		this.id_lista_precio = id_lista_precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCumpleanos() {
		return cumpleanos;
	}
	public void setCumpleanos(String cumpleanos) {
		this.cumpleanos = cumpleanos;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(String fecha_ingreso) {
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
