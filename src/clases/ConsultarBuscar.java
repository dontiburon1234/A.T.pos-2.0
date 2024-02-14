package clases;

public class ConsultarBuscar {
	
	private String codigo;
	private String nombreLargo;
	
	public ConsultarBuscar(String codigo, String nombreLargo) {
		super();
		this.codigo = codigo;
		this.nombreLargo = nombreLargo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	
	

}
