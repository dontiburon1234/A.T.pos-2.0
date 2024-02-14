package clases;

import java.sql.Timestamp;

public class DevolucionVenta {

	private int id_caja; // integer NOT NULL,
	private int numero; // integer NOT NULL,
	private java.sql.Timestamp fecha; // timestamp without time zone,
	private java.sql.Timestamp fecha_caducidad; // date,
	private String id_usuario; // character varying(30),
	private String origen; // character varying(30),
	private int id_caja_factura; // integer,
	private double numero_factura; // numeric(20,0),
	private int id_cliente; // integer NOT NULL DEFAULT 0,
	private double documento_cliente; // numeric(20,0),
	private String apellido_cliente; // character varying(100),
	private String nombre_cliente; // character varying(100),
	private String email_cliente; // character varying(200),
	private String telefono_cliente; // character varying(100),
	private String detalle; // text,
	private double valor; // numeric(20,2),
	private double saldo_pendiente; // numeric(20,2),
	private String documento_caja_generado; // character varying(30),
	private int id_documento_caja; // integer,
	private int numero_documento_caja; // integer,
	private int id_caja_emision; // integer,
	private String estado; // character varying(30),
	private String estado_exportacion; // character varying(30),
	private java.sql.Timestamp fecha_exportacion; // timestamp without time zone,
	private java.sql.Timestamp fecha_contabilizacion; // timestamp without time zone,
	private long identificador_externo; // bigint,
	private String id_tipo_comprobante_contable; // character varying(10),
	private int numero_comrpobante_contable; // integer,
	private java.sql.Timestamp dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	private int numero_impresiones; // integer,
	private String origen_devolucion; // character varying(30),
	
	
	public DevolucionVenta(int id_caja, int numero, Timestamp fecha, Timestamp fecha_caducidad, String id_usuario,
			String origen, int id_caja_factura, double numero_factura, int id_cliente, double documento_cliente,
			String apellido_cliente, String nombre_cliente, String email_cliente, String telefono_cliente,
			String detalle, double valor, double saldo_pendiente, String documento_caja_generado, int id_documento_caja,
			int numero_documento_caja, int id_caja_emision, String estado, String estado_exportacion,
			Timestamp fecha_exportacion, Timestamp fecha_contabilizacion, long identificador_externo,
			String id_tipo_comprobante_contable, int numero_comrpobante_contable, Timestamp dg_fecha_accion,
			String dg_accion,int numero_impresiones, String origen_devolucion) {
		super();
		this.id_caja = id_caja;
		this.numero = numero;
		this.fecha = fecha;
		this.fecha_caducidad = fecha_caducidad;
		this.id_usuario = id_usuario;
		this.origen = origen;
		this.id_caja_factura = id_caja_factura;
		this.numero_factura = numero_factura;
		this.id_cliente = id_cliente;
		this.documento_cliente = documento_cliente;
		this.apellido_cliente = apellido_cliente;
		this.nombre_cliente = nombre_cliente;
		this.email_cliente = email_cliente;
		this.telefono_cliente = telefono_cliente;
		this.detalle = detalle;
		this.valor = valor;
		this.saldo_pendiente = saldo_pendiente;
		this.documento_caja_generado = documento_caja_generado;
		this.id_documento_caja = id_documento_caja;
		this.numero_documento_caja = numero_documento_caja;
		this.id_caja_emision = id_caja_emision;
		this.estado = estado;
		this.estado_exportacion = estado_exportacion;
		this.fecha_exportacion = fecha_exportacion;
		this.fecha_contabilizacion = fecha_contabilizacion;
		this.identificador_externo = identificador_externo;
		this.id_tipo_comprobante_contable = id_tipo_comprobante_contable;
		this.numero_comrpobante_contable = numero_comrpobante_contable;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
		this.numero_impresiones = numero_impresiones;
		this.origen_devolucion = origen_devolucion;
	}
	public int getId_caja() {
		return id_caja;
	}
	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public java.sql.Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Timestamp fecha) {
		this.fecha = fecha;
	}
	public java.sql.Timestamp getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(java.sql.Timestamp fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public int getId_caja_factura() {
		return id_caja_factura;
	}
	public void setId_caja_factura(int id_caja_factura) {
		this.id_caja_factura = id_caja_factura;
	}
	public double getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(double numero_factura) {
		this.numero_factura = numero_factura;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public double getDocumento_cliente() {
		return documento_cliente;
	}
	public void setDocumento_cliente(double documento_cliente) {
		this.documento_cliente = documento_cliente;
	}
	public String getApellido_cliente() {
		return apellido_cliente;
	}
	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getEmail_cliente() {
		return email_cliente;
	}
	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getSaldo_pendiente() {
		return saldo_pendiente;
	}
	public void setSaldo_pendiente(double saldo_pendiente) {
		this.saldo_pendiente = saldo_pendiente;
	}
	public String getDocumento_caja_generado() {
		return documento_caja_generado;
	}
	public void setDocumento_caja_generado(String documento_caja_generado) {
		this.documento_caja_generado = documento_caja_generado;
	}
	public int getId_documento_caja() {
		return id_documento_caja;
	}
	public void setId_documento_caja(int id_documento_caja) {
		this.id_documento_caja = id_documento_caja;
	}
	public int getNumero_documento_caja() {
		return numero_documento_caja;
	}
	public void setNumero_documento_caja(int numero_documento_caja) {
		this.numero_documento_caja = numero_documento_caja;
	}
	public int getId_caja_emision() {
		return id_caja_emision;
	}
	public void setId_caja_emision(int id_caja_emision) {
		this.id_caja_emision = id_caja_emision;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado_exportacion() {
		return estado_exportacion;
	}
	public void setEstado_exportacion(String estado_exportacion) {
		this.estado_exportacion = estado_exportacion;
	}
	public java.sql.Timestamp getFecha_exportacion() {
		return fecha_exportacion;
	}
	public void setFecha_exportacion(java.sql.Timestamp fecha_exportacion) {
		this.fecha_exportacion = fecha_exportacion;
	}
	public java.sql.Timestamp getFecha_contabilizacion() {
		return fecha_contabilizacion;
	}
	public void setFecha_contabilizacion(java.sql.Timestamp fecha_contabilizacion) {
		this.fecha_contabilizacion = fecha_contabilizacion;
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
	public java.sql.Timestamp getDg_fecha_accion() {
		return dg_fecha_accion;
	}
	public void setDg_fecha_accion(java.sql.Timestamp dg_fecha_accion) {
		this.dg_fecha_accion = dg_fecha_accion;
	}
	public String getDg_accion() {
		return dg_accion;
	}
	public void setDg_accion(String dg_accion) {
		this.dg_accion = dg_accion;
	}
	public int getNumero_impresiones() {
		return numero_impresiones;
	}
	public void setNumero_impresiones(int numero_impresiones) {
		this.numero_impresiones = numero_impresiones;
	}
	public String getOrigen_devolucion() {
		return origen_devolucion;
	}
	public void setOrigen_devolucion(String origen_devolucion) {
		this.origen_devolucion = origen_devolucion;
	}

}
