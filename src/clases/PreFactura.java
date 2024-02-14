package clases;

import java.util.Date;

public class PreFactura {
	
	  private int id; // serial NOT NULL,
	  private int id_caja; // integer NOT NULL,
	  private double numero; // numeric(20,0) DEFAULT 0,
	  private int auxiliar; // integer,
	  private int id_almacen; // integer,
	  private Date fecha; // timestamp without time zone,
	  private int id_cajero; // integer,
	  private int id_vendedor; // integer,
	  private String id_usuario_vendedor; // character varying(30),
	  private int id_cliente; // integer NOT NULL DEFAULT 0,
	  private int id_agente_externo; // integer NOT NULL DEFAULT 0,
	  private double valor_prefactura; // numeric(20,2),
	  private double valor_descuento; // numeric(20,2) DEFAULT 0,
	  private double valor_iva; // numeric(30,10),
	  private String comentario; // text DEFAULT ''::text,
	  private String estado; // character varying(30),
	  private Date dg_fecha_accion; // timestamp without time zone,
	  private String dg_accion; // character varying(30),
	  
	public PreFactura() {
		super();
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
	public double getNumero() {
		return numero;
	}
	public void setNumero(double numero) {
		this.numero = numero;
	}
	public int getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(int auxiliar) {
		this.auxiliar = auxiliar;
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
	public int getId_cajero() {
		return id_cajero;
	}
	public void setId_cajero(int id_cajero) {
		this.id_cajero = id_cajero;
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
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_agente_externo() {
		return id_agente_externo;
	}
	public void setId_agente_externo(int id_agente_externo) {
		this.id_agente_externo = id_agente_externo;
	}
	public double getValor_prefactura() {
		return valor_prefactura;
	}
	public void setValor_prefactura(double valor_prefactura) {
		this.valor_prefactura = valor_prefactura;
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
