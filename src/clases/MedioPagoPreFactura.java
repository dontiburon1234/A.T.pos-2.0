package clases;

import java.util.Date;

public class MedioPagoPreFactura {

	private int id; // serial NOT NULL,
	private int id_caja; // integer NOT NULL,
	private int id_prefactura; // integer,
	private int id_medio_pago; // integer NOT NULL,
	private int item; // integer,
	private int id_entidad_bancaria; // integer,
	private int tarjeta; // bigint,
	private int autorizacion; // bigint,
	private int numero_recibo; // integer,
	private double valor_pago; // numeric(20,2),
	private double porcentaje_comision; // numeric(5,2),
	private double valor_comision; // numeric(20,2),
	private double valor_iva; // numeric(30,10),
	private double efectivo_recibido; // numeric(20,2),
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30),
	
	public MedioPagoPreFactura() {
		super();
	}
	
	public MedioPagoPreFactura(int id, int id_caja, int id_prefactura, int id_medio_pago, int item,
			int id_entidad_bancaria, int tarjeta, int autorizacion, int numero_recibo, double valor_pago,
			double porcentaje_comision, double valor_comision, double valor_iva, double efectivo_recibido,
			Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_caja = id_caja;
		this.id_prefactura = id_prefactura;
		this.id_medio_pago = id_medio_pago;
		this.item = item;
		this.id_entidad_bancaria = id_entidad_bancaria;
		this.tarjeta = tarjeta;
		this.autorizacion = autorizacion;
		this.numero_recibo = numero_recibo;
		this.valor_pago = valor_pago;
		this.porcentaje_comision = porcentaje_comision;
		this.valor_comision = valor_comision;
		this.valor_iva = valor_iva;
		this.efectivo_recibido = efectivo_recibido;
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

	public int getId_prefactura() {
		return id_prefactura;
	}

	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
	}

	public int getId_medio_pago() {
		return id_medio_pago;
	}

	public void setId_medio_pago(int id_medio_pago) {
		this.id_medio_pago = id_medio_pago;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getId_entidad_bancaria() {
		return id_entidad_bancaria;
	}

	public void setId_entidad_bancaria(int id_entidad_bancaria) {
		this.id_entidad_bancaria = id_entidad_bancaria;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(int autorizacion) {
		this.autorizacion = autorizacion;
	}

	public int getNumero_recibo() {
		return numero_recibo;
	}

	public void setNumero_recibo(int numero_recibo) {
		this.numero_recibo = numero_recibo;
	}

	public double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public double getPorcentaje_comision() {
		return porcentaje_comision;
	}

	public void setPorcentaje_comision(double porcentaje_comision) {
		this.porcentaje_comision = porcentaje_comision;
	}

	public double getValor_comision() {
		return valor_comision;
	}

	public void setValor_comision(double valor_comision) {
		this.valor_comision = valor_comision;
	}

	public double getValor_iva() {
		return valor_iva;
	}

	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}

	public double getEfectivo_recibido() {
		return efectivo_recibido;
	}

	public void setEfectivo_recibido(double efectivo_recibido) {
		this.efectivo_recibido = efectivo_recibido;
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
