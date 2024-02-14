package clases;

public class Licencia {

	private String empresa;
	private int idHost;
	private int idCaja;
	private int idHostPadre;
	private String macAutorizada;
	private int idAlmacen;
	private String IP;
	private int puerto;
	private String conexionCaja;
	private String tipoBD;
	private String nombreBD;
	private String hostBD;
	private String usuarioBD;
	private String passwordBD;
	private int puertoBD;
	private String hostPadreWS;
	private int puertoPadreWS;
	private int idCajaPadre;
	private String hostCajaPadreWS;
	private int puertoCajaPadreWS;
	private boolean impresoraDefecto;
	private String impresora;
	private String directorioPDF;
	private String logoPDF;
	private String hostDatafono;
	private String puertoDatafono;
	private String puertoBascula;
	private String estado;
	private boolean resultado;

	public Licencia () {
		empresa = "";
		idHost = 0;
		idCaja = 0;
		idHostPadre = 0;
		macAutorizada = "";
		idAlmacen = 0;
		IP = "";
		puerto = 0;
		conexionCaja = "";
		tipoBD = "";
		nombreBD = "";
		usuarioBD = "";
		passwordBD = "";
		hostPadreWS = "";
		puertoPadreWS = 0;
		idCajaPadre = 0;
		hostCajaPadreWS = "";
		puertoCajaPadreWS = 0;
		impresoraDefecto = false;
		impresora = "";
		directorioPDF = "";
		logoPDF = "";
		hostDatafono = "";
		puertoDatafono = "";
		puertoBascula = "";
		estado = "";
		resultado = false;
	}

	public boolean getResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getIdHost() {
		return idHost;
	}

	public void setIdHost(int idHost) {
		this.idHost = idHost;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getIdHostPadre() {
		return idHostPadre;
	}

	public void setIdHostPadre(int idHostPadre) {
		this.idHostPadre = idHostPadre;
	}

	public String getMacAutorizada() {
		return macAutorizada;
	}

	public void setMacAutorizada(String macAutorizada) {
		this.macAutorizada = macAutorizada;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getConexionCaja() {
		return conexionCaja;
	}

	public void setConexionCaja(String conexionCaja) {
		this.conexionCaja = conexionCaja;
	}

	public String getTipoBD() {
		return tipoBD;
	}

	public void setTipoBD(String tipoBD) {
		this.tipoBD = tipoBD;
	}

	public String getNombreBD() {
		return nombreBD;
	}

	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}

	public String getHostBD() {
		return hostBD;
	}

	public void setHostBD(String hostBD) {
		this.hostBD = hostBD;
	}

	public String getUsuarioBD() {
		return usuarioBD;
	}

	public void setUsuarioBD(String usuarioBD) {
		this.usuarioBD = usuarioBD;
	}

	public String getPasswordBD() {
		return passwordBD;
	}

	public void setPasswordBD(String passwordBD) {
		this.passwordBD = passwordBD;
	}

	public int getPuertoBD() {
		return puertoBD;
	}

	public void setPuertoBD(int puertoBD) {
		this.puertoBD = puertoBD;
	}

	public String getHostPadreWS() {
		return hostPadreWS;
	}

	public void setHostPadreWS(String hostPadreWS) {
		this.hostPadreWS = hostPadreWS;
	}

	public int getPuertoPadreWS() {
		return puertoPadreWS;
	}

	public void setPuertoPadreWS(int puertoPadreWS) {
		this.puertoPadreWS = puertoPadreWS;
	}

	public int getIdCajaPadre() {
		return idCajaPadre;
	}

	public void setIdCajaPadre(int idCajaPadre) {
		this.idCajaPadre = idCajaPadre;
	}

	public String getHostCajaPadreWS() {
		return hostCajaPadreWS;
	}

	public void setHostCajaPadreWS(String hostCajaPadreWS) {
		this.hostCajaPadreWS = hostCajaPadreWS;
	}

	public int getPuertoCajaPadreWS() {
		return puertoCajaPadreWS;
	}

	public void setPuertoCajaPadreWS(int puertoCajaPadreWS) {
		this.puertoCajaPadreWS = puertoCajaPadreWS;
	}

	public boolean isImpresoraDefecto() {
		return impresoraDefecto;
	}

	public void setImpresoraDefecto(boolean impresoraDefecto) {
		this.impresoraDefecto = impresoraDefecto;
	}

	public String getImpresora() {
		return impresora;
	}

	public void setImpresora(String impresora) {
		this.impresora = impresora;
	}

	public String getDirectorioPDF() {
		return directorioPDF;
	}

	public void setDirectorioPDF(String directorioPDF) {
		this.directorioPDF = directorioPDF;
	}

	public String getLogoPDF() {
		return logoPDF;
	}

	public void setLogoPDF(String logoPDF) {
		this.logoPDF = logoPDF;
	}

	public String getHostDatafono() {
		return hostDatafono;
	}

	public void setHostDatafono(String hostDatafono) {
		this.hostDatafono = hostDatafono;
	}

	public String getPuertoDatafono() {
		return puertoDatafono;
	}

	public void setPuertoDatafono(String puertoDatafono) {
		this.puertoDatafono = puertoDatafono;
	}

	public String getPuertoBascula() {
		return puertoBascula;
	}

	public void setPuertoBascula(String puertoBascula) {
		this.puertoBascula = puertoBascula;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
