package clases;

public class EntidadBancaria {

	
	private int id; // serial NOT NULL,
	private String nombre; // character varying(50),
	private double comision; // numeric(5,2),
	private int id_medio_pago; // integer,
	private String estado; // character varying(30),
	
	public EntidadBancaria(int id, String nombre, double comision, int id_medio_pago, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.comision = comision;
		this.id_medio_pago = id_medio_pago;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public int getId_medio_pago() {
		return id_medio_pago;
	}

	public void setId_medio_pago(int id_medio_pago) {
		this.id_medio_pago = id_medio_pago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
