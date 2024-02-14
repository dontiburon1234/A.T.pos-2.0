package clases;

import java.util.Date;

public class Factura {

	private int id_caja; // integer NOT NULL,
	private double numero; // numeric(20,0) NOT NULL,
	private int id_numeracion_autorizada; // integer,
	private int id_almacen; // integer,
	private Date fecha; // date,
	private String hora; // character varying(10),
	private int id_cajero; // integer,
	private int id_tercero; // integer,
	private int id_vendedor; // integer,
	private String id_usuario_vendedor; // character varying(30),
	private double valor_factura; // numeric(20,2),
	private double valor_descuento; // numeric(20,2) DEFAULT 0,
	private double valor_iva; // numeric(30,10),
	private double costo; // numeric(20,2) NOT NULL DEFAULT 0,
	private double valor_devolucion; // numeric(20,2) DEFAULT 0,
	private int id_cliente; // integer NOT NULL DEFAULT 0,
	private String comentario; // text DEFAULT ''::text,
	private String estado; // character varying(30),
	private int cantidad_bolsa_inc; // integer NOT NULL DEFAULT 0,
	private int valor_bolsa_inc; // integer NOT NULL DEFAULT 0,
	private Date fecha_exportacion; // timestamp without time zone,
	private String estado_exportacion; // character varying(30),
	private long identificador_externo; // bigint,
	private String id_tipo_comprobante_contable; // character varying(10),
	private int numero_comrpobante_contable; // integer,
	private int id_prefactura; // integer NOT NULL DEFAULT 0,
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),

	public Factura(int id_caja, double numero, int id_numeracion_autorizada, int id_almacen, Date fecha, String hora,
			int id_cajero, int id_tercero, int id_vendedor, String id_usuario_vendedor, double valor_factura,
			double valor_descuento, double valor_iva, double costo, double valor_devolucion, int id_cliente,
			String comentario, String estado, int cantidad_bolsa_inc, int valor_bolsa_inc, Date fecha_exportacion,
			String estado_exportacion, long identificador_externo, String id_tipo_comprobante_contable,
			int numero_comrpobante_contable, int id_prefactura, Date dg_fecha_accion, String dg_accion) {
		super();
		this.id_caja = id_caja;
		this.numero = numero;
		this.id_numeracion_autorizada = id_numeracion_autorizada;
		this.id_almacen = id_almacen;
		this.fecha = fecha;
		this.hora = hora;
		this.id_cajero = id_cajero;
		this.id_tercero = id_tercero;
		this.id_vendedor = id_vendedor;
		this.id_usuario_vendedor = id_usuario_vendedor;
		this.valor_factura = valor_factura;
		this.valor_descuento = valor_descuento;
		this.valor_iva = valor_iva;
		this.costo = costo;
		this.valor_devolucion = valor_devolucion;
		this.id_cliente = id_cliente;
		this.comentario = comentario;
		this.estado = estado;
		this.cantidad_bolsa_inc = cantidad_bolsa_inc;
		this.valor_bolsa_inc = valor_bolsa_inc;
		this.fecha_exportacion = fecha_exportacion;
		this.estado_exportacion = estado_exportacion;
		this.identificador_externo = identificador_externo;
		this.id_tipo_comprobante_contable = id_tipo_comprobante_contable;
		this.numero_comrpobante_contable = numero_comrpobante_contable;
		this.id_prefactura = id_prefactura;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}

	public int getId_caja() {
		return id_caja;
	}

	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public int getId_numeracion_autorizada() {
		return id_numeracion_autorizada;
	}

	public void setId_numeracion_autorizada(int id_numeracion_autorizada) {
		this.id_numeracion_autorizada = id_numeracion_autorizada;
	}

	public int getId_almacen() {
		return id_almacen;
	}

	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getId_cajero() {
		return id_cajero;
	}

	public void setId_cajero(int id_cajero) {
		this.id_cajero = id_cajero;
	}

	public int getId_tercero() {
		return id_tercero;
	}

	public void setId_tercero(int id_tercero) {
		this.id_tercero = id_tercero;
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

	public double getValor_factura() {
		return valor_factura;
	}

	public void setValor_factura(double valor_factura) {
		this.valor_factura = valor_factura;
	}

	public double getValor_descuento() {
		return valor_descuento;
	}

	public void setValor_descuento(double valor_descuento) {
		this.valor_descuento = valor_descuento;
	}

	public double getValor_iva() {
		return valor_iva;
	}

	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getValor_devolucion() {
		return valor_devolucion;
	}

	public void setValor_devolucion(double valor_devolucion) {
		this.valor_devolucion = valor_devolucion;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCantidad_bolsa_inc() {
		return cantidad_bolsa_inc;
	}

	public void setCantidad_bolsa_inc(int cantidad_bolsa_inc) {
		this.cantidad_bolsa_inc = cantidad_bolsa_inc;
	}

	public int getValor_bolsa_inc() {
		return valor_bolsa_inc;
	}

	public void setValor_bolsa_inc(int valor_bolsa_inc) {
		this.valor_bolsa_inc = valor_bolsa_inc;
	}

	public Date getFecha_exportacion() {
		return fecha_exportacion;
	}

	public void setFecha_exportacion(Date fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
	}

	public String getEstado_exportacion() {
		return estado_exportacion;
	}

	public void setEstado_exportacion(String estado_exportacion) {
		this.estado_exportacion = estado_exportacion;
	}

	public long getIdentificador_externo() {
		return identificador_externo;
	}

	public void setIdentificador_externo(long identificador_externo) {
		this.identificador_externo = identificador_externo;
	}

	public String getId_tipo_comprobante_contable() {
		return id_tipo_comprobante_contable;
	}

	public void setId_tipo_comprobante_contable(String id_tipo_comprobante_contable) {
		this.id_tipo_comprobante_contable = id_tipo_comprobante_contable;
	}

	public int getNumero_comrpobante_contable() {
		return numero_comrpobante_contable;
	}

	public void setNumero_comrpobante_contable(int numero_comrpobante_contable) {
		this.numero_comrpobante_contable = numero_comrpobante_contable;
	}

	public int getId_prefactura() {
		return id_prefactura;
	}

	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
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


}
