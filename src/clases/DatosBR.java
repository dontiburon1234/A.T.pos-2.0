package clases;

public class DatosBR {
	
	//Esta clase permite almacenar los datos de cada boton rápido
	
	private int tabbed;
	private int posicionCelda;
	private String codigo;
	private String articulo;
	private int red;
	private int green;
	private int blue;
	
	public DatosBR(int tabbed, int posicionCelda, String codigo, String articulo, int red, int green, int blue) {
		super();
		this.tabbed = tabbed;
		this.posicionCelda = posicionCelda;
		this.codigo = codigo;
		this.articulo = articulo;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public int getTabbed() {
		return tabbed;
	}
	public void setTabbed(int tabbed) {
		this.tabbed = tabbed;
	}
	public int getPosicionCelda() {
		return posicionCelda;
	}
	public void setPosicionCelda(int posicionCelda) {
		this.posicionCelda = posicionCelda;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	

}
