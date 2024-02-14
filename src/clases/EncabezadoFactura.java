package clases;

import java.util.Date;
import java.util.List;

public class EncabezadoFactura {

	//tabla empresa
	private int idEmpresa; //id integer NOT NULL,
	private int idRegimen; //  id_regimen integer,
	private String nombreEmpresa; //  nombre character varying(50),
	private String nit; //  nit character varying(20),
	private String representanteLegal; //  representante_legal character varying(200),
	private String direccionEmpresa; //direccion character varying(200),
	private int idCiudadEmpresa; //  id_ciudad integer NOT NULL DEFAULT 0,
	private String telefonoEmpresa; //  telefono character varying(100),
	
	// tabla almacen
	private int idAlmacen; // id serial NOT NULL,
	private String nombreAlmacen; // nombre character varying(50),
	private String prefijoAlmacen; // prefijo character varying(5),
	private int idCiudadAlmacen; // id_ciudad integer NOT NULL DEFAULT 0,
	private String direccionAlmacen; // direccion character varying(200),
	private String telefonoAlmacen; // telefono character varying(100),
	
	// tabla regimen
	private String nombreRegimen; //  nombre character varying(30),
	
	// tabla numeracion_autorizada
	private int idNumeracionAutorizada; // id serial NOT NULL,
	private int idCajaNumeracionAutorizada; // id_caja integer,
	private String resolucion; // resolucion character varying(40),
	private Date fechaExpedicionNumeracionAutorizada; // fecha_expedicion date,
	private String tipoFacturacion; // tipo_facturacion character varying(30),
	private String prefijoNumeracionAutorizada; // prefijo character varying(10),
	private double rangoInicial; // rango_inicial numeric(20,0),
	private double rangoFinal; // rango_final numeric(20,0),
	private double numeroActual; // numero_actual numeric(20,0),
	private Date fechaCaducidad; // fecha_caducidad date,
	private String estadoNumeracionAutorizada; //  estado character varying(30),
	
	// tabla almacen_mensaje_documento
	private int idMensajeDocumento; // id_mensaje_documento integer NOT NULL,
	
	// tabla caja
	private int idCaja; // id serial NOT NULL,
	private int idHostCaja; // id_host integer DEFAULT 0,
	private int idCajaPadre; // id_caja_padre integer DEFAULT 0,
	private String nombreCaja; // nombre character varying(200),
	private String prefijoCaja; // prefijo character varying(10),
	private String impresora; // impresora character varying(50),
	private String estadoCaja; // estado character varying(30),
	
	// base_iva
	private List<BaseIva> baseIva = null;
	
	// cliente
	private String nombreCliente;
	
	// articulo
	private List<ArticuloFactura> articuloFactura = null;

	//cajero
	private String nombreCajero;

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdRegimen() {
		return idRegimen;
	}

	public void setIdRegimen(int idRegimen) {
		this.idRegimen = idRegimen;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public int getIdCiudadEmpresa() {
		return idCiudadEmpresa;
	}

	public void setIdCiudadEmpresa(int idCiudadEmpresa) {
		this.idCiudadEmpresa = idCiudadEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	public String getPrefijoAlmacen() {
		return prefijoAlmacen;
	}

	public void setPrefijoAlmacen(String prefijoAlmacen) {
		this.prefijoAlmacen = prefijoAlmacen;
	}

	public int getIdCiudadAlmacen() {
		return idCiudadAlmacen;
	}

	public void setIdCiudadAlmacen(int idCiudadAlmacen) {
		this.idCiudadAlmacen = idCiudadAlmacen;
	}

	public String getDireccionAlmacen() {
		return direccionAlmacen;
	}

	public void setDireccionAlmacen(String direccionAlmacen) {
		this.direccionAlmacen = direccionAlmacen;
	}

	public String getTelefonoAlmacen() {
		return telefonoAlmacen;
	}

	public void setTelefonoAlmacen(String telefonoAlmacen) {
		this.telefonoAlmacen = telefonoAlmacen;
	}

	public String getNombreRegimen() {
		return nombreRegimen;
	}

	public void setNombreRegimen(String nombreRegimen) {
		this.nombreRegimen = nombreRegimen;
	}

	public int getIdNumeracionAutorizada() {
		return idNumeracionAutorizada;
	}

	public void setIdNumeracionAutorizada(int idNumeracionAutorizada) {
		this.idNumeracionAutorizada = idNumeracionAutorizada;
	}

	public int getIdCajaNumeracionAutorizada() {
		return idCajaNumeracionAutorizada;
	}

	public void setIdCajaNumeracionAutorizada(int idCajaNumeracionAutorizada) {
		this.idCajaNumeracionAutorizada = idCajaNumeracionAutorizada;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public Date getFechaExpedicionNumeracionAutorizada() {
		return fechaExpedicionNumeracionAutorizada;
	}

	public void setFechaExpedicionNumeracionAutorizada(Date fechaExpedicionNumeracionAutorizada) {
		this.fechaExpedicionNumeracionAutorizada = fechaExpedicionNumeracionAutorizada;
	}

	public String getTipoFacturacion() {
		return tipoFacturacion;
	}

	public void setTipoFacturacion(String tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}

	public String getPrefijoNumeracionAutorizada() {
		return prefijoNumeracionAutorizada;
	}

	public void setPrefijoNumeracionAutorizada(String prefijoNumeracionAutorizada) {
		this.prefijoNumeracionAutorizada = prefijoNumeracionAutorizada;
	}

	public double getRangoInicial() {
		return rangoInicial;
	}

	public void setRangoInicial(double rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	public double getRangoFinal() {
		return rangoFinal;
	}

	public void setRangoFinal(double rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	public double getNumeroActual() {
		return numeroActual;
	}

	public void setNumeroActual(double numeroActual) {
		this.numeroActual = numeroActual;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getEstadoNumeracionAutorizada() {
		return estadoNumeracionAutorizada;
	}

	public void setEstadoNumeracionAutorizada(String estadoNumeracionAutorizada) {
		this.estadoNumeracionAutorizada = estadoNumeracionAutorizada;
	}

	public int getIdMensajeDocumento() {
		return idMensajeDocumento;
	}

	public void setIdMensajeDocumento(int idMensajeDocumento) {
		this.idMensajeDocumento = idMensajeDocumento;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getIdHostCaja() {
		return idHostCaja;
	}

	public void setIdHostCaja(int idHostCaja) {
		this.idHostCaja = idHostCaja;
	}

	public int getIdCajaPadre() {
		return idCajaPadre;
	}

	public void setIdCajaPadre(int idCajaPadre) {
		this.idCajaPadre = idCajaPadre;
	}

	public String getNombreCaja() {
		return nombreCaja;
	}

	public void setNombreCaja(String nombreCaja) {
		this.nombreCaja = nombreCaja;
	}

	public String getPrefijoCaja() {
		return prefijoCaja;
	}

	public void setPrefijoCaja(String prefijoCaja) {
		this.prefijoCaja = prefijoCaja;
	}

	public String getImpresora() {
		return impresora;
	}

	public void setImpresora(String impresora) {
		this.impresora = impresora;
	}

	public String getEstadoCaja() {
		return estadoCaja;
	}

	public void setEstadoCaja(String estadoCaja) {
		this.estadoCaja = estadoCaja;
	}

	public List<BaseIva> getBaseIva() {
		return baseIva;
	}

	public void setBaseIva(List<BaseIva> baseIva) {
		this.baseIva = baseIva;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<ArticuloFactura> getArticuloFactura() {
		return articuloFactura;
	}

	public void setArticuloFactura(List<ArticuloFactura> articuloFactura) {
		this.articuloFactura = articuloFactura;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}
	
}
