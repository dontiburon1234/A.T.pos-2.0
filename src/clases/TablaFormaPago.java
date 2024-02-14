package clases;

public class TablaFormaPago {
	
	private double recibido;
	private String descripcion;
	private double valorBase;
	private double iva; 
	private double total;
	private double cambio;
	
	public TablaFormaPago(double recibido, String descripcion, double valorBase, double iva, double total,
			double cambio) {
		super();
		this.recibido = recibido;
		this.descripcion = descripcion;
		this.valorBase = valorBase;
		this.iva = iva;
		this.total = total;
		this.cambio = cambio;
	}
	
	public double getRecibido() {
		return recibido;
	}
	public void setRecibido(double recibido) {
		this.recibido = recibido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getValorBase() {
		return valorBase;
	}
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getCambio() {
		return cambio;
	}
	public void setCambio(double cambio) {
		this.cambio = cambio;
	}
}
