package clases;

public class ItemPreFormaPago {
	
	private int id; // serial NOT NULL,
	private int id_caja; // integer NOT NULL,
	private int id_prefactura; // integer,
	private int id_medio_pago; // integer NOT NULL,
	private int item; // integer,
	private int id_entidad_bancaria; // integer,
	private long tarjeta; // bigint,
	private long autorizacion; // bigint,
	private int numero_recibo; // integer,
	private double valor_pago; // numeric(20,2),
	private double porcentaje_comision; // numeric(5,2),
	private double valor_comision; // numeric(20,2),
	private double valor_iva; // numeric(30,10),
	private double efectivo_recibido; // numeric(20,2),
	
	public ItemPreFormaPago(int id, int id_caja, int id_prefactura, int id_medio_pago, int item,
			int id_entidad_bancaria, long tarjeta, long autorizacion, int numero_recibo, double valor_pago,
			double porcentaje_comision, double valor_comision, double valor_iva, double efectivo_recibido) {
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
	public long getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(long tarjeta) {
		this.tarjeta = tarjeta;
	}
	public long getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(long autorizacion) {
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
	
	
	  
}
