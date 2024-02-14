package clases;

import java.sql.Timestamp;

public class Articulo {

	private int id; // serial NOT NULL,
	private String nombre; // character varying(200),
	private String nombre_largo; // text,
	private int id_categoria; // integer,
	private int id_unidad_medida; // integer,
	private int id_empaque; // integer,
	private int id_base_iva; // integer,
	private String codigo; // character varying(20),
	private String estado; // character varying(30),
	private Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public Articulo(int id, String nombre, String nombre_largo, int id_categoria, int id_unidad_medida, int id_empaque,
			int id_base_iva, String codigo, String estado, Timestamp dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombre_largo = nombre_largo;
		this.id_categoria = id_categoria;
		this.id_unidad_medida = id_unidad_medida;
		this.id_empaque = id_empaque;
		this.id_base_iva = id_base_iva;
		this.codigo = codigo;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_largo() {
		return nombre_largo;
	}
	public void setNombre_largo(String nombre_largo) {
		this.nombre_largo = nombre_largo;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public int getId_unidad_medida() {
		return id_unidad_medida;
	}
	public void setId_unidad_medida(int id_unidad_medida) {
		this.id_unidad_medida = id_unidad_medida;
	}
	public int getId_empaque() {
		return id_empaque;
	}
	public void setId_empaque(int id_empaque) {
		this.id_empaque = id_empaque;
	}
	public int getId_base_iva() {
		return id_base_iva;
	}
	public void setId_base_iva(int id_base_iva) {
		this.id_base_iva = id_base_iva;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
