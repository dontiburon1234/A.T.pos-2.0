package clases;

public class ItemPreFactura {
	private int id; // serial NOT NULL,
	private int id_caja; // integer NOT NULL,
	private int id_prefactura; // integer,
	private int item; // integer,
	private int id_vendedor; // integer,
	private String id_usuario_vendedor; // character varying(30),
	private int id_articulo; // integer,
	private String codigo_articulo; // character varying(20),
	private String codigo_articulo_venta; // character varying(20),
	private String nombre_provisional; // character varying(60),
	private double cantidad_unidad_medida; // numeric(10,2),
	private double valor_unidad; // numeric(30,4),
	private double valor_anterior; // numeric(30,4) NOT NULL DEFAULT 0,
	private int id_presentacion; // integer,
	private double cantidad_presentacion; // numeric(10,2),
	private double valor_presentacion; // numeric(30,4),
	private double valor_item; // numeric(20,2),
	private int id_base_iva; // integer,
	private double valor_iva; // numeric(30,10),
	private double porcentaje_descuento; // numeric(5,2),
	private double valor_descuento; // numeric(20,2),
	private int item_promocion;// integer,
	
	public ItemPreFactura() {
		super();
	}
	
	public ItemPreFactura(int id_vendedor, String codigo_articulo,
			double cantidad_unidad_medida, double valor_unidad, double valor_descuento,int id_articulo,double valor_iva) {
		super();
		this.id_vendedor = id_vendedor;
		this.codigo_articulo = codigo_articulo;
		this.cantidad_unidad_medida = cantidad_unidad_medida;
		this.valor_unidad = valor_unidad;
		this.valor_descuento = valor_descuento;
		this.id_articulo = id_articulo;
		this.valor_iva = valor_iva;
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
	public int getId_prefactura() {
		return id_prefactura;
	}
	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public String getId_usuario_vendedor() {
		return id_usuario_vendedor;
	}
	public void setId_usuario_vendedor(String id_usuario_vendedor) {
		this.id_usuario_vendedor = id_usuario_vendedor;
	}
	public int getId_articulo() {
		return id_articulo;
	}
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}
	public String getCodigo_articulo() {
		return codigo_articulo;
	}
	public void setCodigo_articulo(String codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}
	public String getCodigo_articulo_venta() {
		return codigo_articulo_venta;
	}
	public void setCodigo_articulo_venta(String codigo_articulo_venta) {
		this.codigo_articulo_venta = codigo_articulo_venta;
	}
	public String getNombre_provisional() {
		return nombre_provisional;
	}
	public void setNombre_provisional(String nombre_provisional) {
		this.nombre_provisional = nombre_provisional;
	}
	public double getCantidad_unidad_medida() {
		return cantidad_unidad_medida;
	}
	public void setCantidad_unidad_medida(double cantidad_unidad_medida) {
		this.cantidad_unidad_medida = cantidad_unidad_medida;
	}
	public double getValor_unidad() {
		return valor_unidad;
	}
	public void setValor_unidad(double valor_unidad) {
		this.valor_unidad = valor_unidad;
	}
	public double getValor_anterior() {
		return valor_anterior;
	}
	public void setValor_anterior(double valor_anterior) {
		this.valor_anterior = valor_anterior;
	}
	public int getId_presentacion() {
		return id_presentacion;
	}
	public void setId_presentacion(int id_presentacion) {
		this.id_presentacion = id_presentacion;
	}
	public double getCantidad_presentacion() {
		return cantidad_presentacion;
	}
	public void setCantidad_presentacion(double cantidad_presentacion) {
		this.cantidad_presentacion = cantidad_presentacion;
	}
	public double getValor_presentacion() {
		return valor_presentacion;
	}
	public void setValor_presentacion(double valor_presentacion) {
		this.valor_presentacion = valor_presentacion;
	}
	public double getValor_item() {
		return valor_item;
	}
	public void setValor_item(double valor_item) {
		this.valor_item = valor_item;
	}
	public int getId_base_iva() {
		return id_base_iva;
	}
	public void setId_base_iva(int id_base_iva) {
		this.id_base_iva = id_base_iva;
	}
	public double getValor_iva() {
		return valor_iva;
	}
	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}
	public double getPorcentaje_descuento() {
		return porcentaje_descuento;
	}
	public void setPorcentaje_descuento(double porcentaje_descuento) {
		this.porcentaje_descuento = porcentaje_descuento;
	}
	public double getValor_descuento() {
		return valor_descuento;
	}
	public void setValor_descuento(double valor_descuento) {
		this.valor_descuento = valor_descuento;
	}
	public int getItem_promocion() {
		return item_promocion;
	}
	public void setItem_promocion(int item_promocion) {
		this.item_promocion = item_promocion;
	}
	
	
}
