package clases;

public class DevolucionesBaseIva {
	
	private int id_base_iva;
	private double iva;
	private double total;
	public DevolucionesBaseIva(int id_base_iva, double iva, double total) {
		super();
		this.id_base_iva = id_base_iva;
		this.iva = iva;
		this.total = total;
	}
	public int getId_base_iva() {
		return id_base_iva;
	}
	public void setId_base_iva(int id_base_iva) {
		this.id_base_iva = id_base_iva;
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
}
