package clases;

public class ConsultarBuscarConPrecio{
	private int id_articulo;
	private String codigo;
	private String nombre_largo;
	private double precio_base;
	public ConsultarBuscarConPrecio(int id_articulo, String codigo, String nombre_largo, double precio_base) {
		super();
		this.id_articulo = id_articulo;
		this.codigo = codigo;
		this.nombre_largo = nombre_largo;
		this.precio_base = precio_base;
	}
	public int getId_articulo() {
		return id_articulo;
	}
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre_largo() {
		return nombre_largo;
	}
	public void setNombre_largo(String nombre_largo) {
		this.nombre_largo = nombre_largo;
	}
	public double getPrecio_base() {
		return precio_base;
	}
	public void setPrecio_base(double precio_base) {
		this.precio_base = precio_base;
	}	
}