package clases;

public class BaseIva {

	private int idBaseIva; // id serial NOT NULL,
	private double baseIva; // base numeric(4,2),
	private String estadoBaseIva; // estado character varying(30),
	private String nombreBaseIva; // nombre character varying(30),
	
	public int getIdBaseIva() {
		return idBaseIva;
	}
	public void setIdBaseIva(int idBaseIva) {
		this.idBaseIva = idBaseIva;
	}
	public double getBaseIva() {
		return baseIva;
	}
	public void setBaseIva(double baseIva) {
		this.baseIva = baseIva;
	}
	public String getEstadoBaseIva() {
		return estadoBaseIva;
	}
	public void setEstadoBaseIva(String estadoBaseIva) {
		this.estadoBaseIva = estadoBaseIva;
	}
	public String getNombreBaseIva() {
		return nombreBaseIva;
	}
	public void setNombreBaseIva(String nombreBaseIva) {
		this.nombreBaseIva = nombreBaseIva;
	}
	
	
}
