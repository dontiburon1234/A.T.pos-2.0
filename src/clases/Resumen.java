package clases;

public class Resumen {
	
	private String codigoArticulo;
	private String nombreArticulo;
	private double unitario;
	private double cantidad;
	private double total;
	
	public Resumen(String codigoArticulo, String nombreArticulo, double unitario, double cantidad, double total) {
		super();
		this.codigoArticulo = codigoArticulo;
		this.nombreArticulo = nombreArticulo;
		this.unitario = unitario;
		this.cantidad = cantidad;
		this.total = total;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public double getUnitario() {
		return unitario;
	}

	public void setUnitario(double unitario) {
		this.unitario = unitario;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
