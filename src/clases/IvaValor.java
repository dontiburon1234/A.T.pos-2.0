package clases;

public class IvaValor {
	private int idIva;
	private double valorIva;
	private double totalxIva;
	public IvaValor(int idIva, double valorIva, double totalxIva) {
		super();
		this.idIva = idIva;
		this.valorIva = valorIva;
		this.totalxIva = totalxIva;
	}
	public int getIdIva() {
		return idIva;
	}
	public void setIdIva(int idIva) {
		this.idIva = idIva;
	}
	public double getValorIva() {
		return valorIva;
	}
	public void setValorIva(double valorIva) {
		this.valorIva = valorIva;
	}
	public double getTotalxIva() {
		return totalxIva;
	}
	public void setTotalxIva(double totalxIva) {
		this.totalxIva = totalxIva;
	}
}