package clases;

public class TablaArticulos {

	private int vendedor;
	private String codigoArticulo;
	private String NombreArticulo;
	private int unidades;
	private double cantidad;
	private double valorUnitario;
	private double descuento;
	private double valorTotal;
	
	public TablaArticulos(int vendedor, String codigoArticulo, String nombreArticulo, int unidades, double cantidad,
			double valorUnitario, double descuento, double valorTotal) {
		super();
		this.vendedor = vendedor;
		this.codigoArticulo = codigoArticulo;
		this.NombreArticulo = nombreArticulo;
		this.unidades = unidades;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.descuento = descuento;
		this.valorTotal = valorTotal;
	}
	public int getVendedor() {
		return vendedor;
	}
	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}
	public String getCodigoArticulo() {
		return codigoArticulo;
	}
	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	public String getNombreArticulo() {
		return NombreArticulo;
	}
	public void setNombreArticulo(String nombreArticulo) {
		NombreArticulo = nombreArticulo;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
