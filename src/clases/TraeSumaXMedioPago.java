package clases;

public class TraeSumaXMedioPago {

	private int id_medio_pago;
	private String nombre;
	private double valorPagado;
	
	public TraeSumaXMedioPago(int id_medio_pago, String nombre, double valorPagado) {
		super();
		this.id_medio_pago = id_medio_pago;
		this.nombre = nombre;
		this.valorPagado = valorPagado;
	}

	public int getId_medio_pago() {
		return id_medio_pago;
	}

	public void setId_medio_pago(int id_medio_pago) {
		this.id_medio_pago = id_medio_pago;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(double valorPagado) {
		this.valorPagado = valorPagado;
	}
}
