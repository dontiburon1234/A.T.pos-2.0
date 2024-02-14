package clases;

import java.util.Date;

public class Pagos {
	
	private int id; // serial NOT NULL,
	private int id_caja; // integer,
	private int id_almacen; // integer,
	private int id_proveedor; // integer,
	private int id_cajero; // integer,
	private int id_tipo_pago; // integer,
	private double valor_factura; // numeric(20,2),
	private double valor_descuento; // numeric(20,2),
	private double valor_pagado; // numeric(20,2),
	private String numero_factura; // text,
	private Date fechapago; // timestamp without time zone,
	private int numero_impresiones; // integer
	private String comentario; // text,
	private String estado; // text,
	private Date dg_fecha_accion; // timestamp without time zone,
	private String dg_accion; // character varying(30)
	
	public Pagos(int id, int id_caja, int id_almacen, int id_proveedor, int id_cajero, int id_tipo_pago,
			double valor_factura, double valor_descuento, double valor_pagado, String numero_factura, Date fechapago,
			int numero_impresiones, String comentario, String estado, Date dg_fecha_accion, String dg_accion) {
		super();
		this.id = id;
		this.id_caja = id_caja;
		this.id_almacen = id_almacen;
		this.id_proveedor = id_proveedor;
		this.id_cajero = id_cajero;
		this.id_tipo_pago = id_tipo_pago;
		this.valor_factura = valor_factura;
		this.valor_descuento = valor_descuento;
		this.valor_pagado = valor_pagado;
		this.numero_factura = numero_factura;
		this.fechapago = fechapago;
		this.numero_impresiones = numero_impresiones;
		this.comentario = comentario;
		this.estado = estado;
		this.dg_fecha_accion = dg_fecha_accion;
		this.dg_accion = dg_accion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_caja() {
		return id_caja;
	}

	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}

	public int getId_almacen() {
		return id_almacen;
	}

	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public int getId_cajero() {
		return id_cajero;
	}

	public void setId_cajero(int id_cajero) {
		this.id_cajero = id_cajero;
	}

	public int getId_tipo_pago() {
		return id_tipo_pago;
	}

	public void setId_tipo_pago(int id_tipo_pago) {
		this.id_tipo_pago = id_tipo_pago;
	}

	public double getValor_factura() {
		return valor_factura;
	}

	public void setValor_factura(double valor_factura) {
		this.valor_factura = valor_factura;
	}

	public double getValor_descuento() {
		return valor_descuento;
	}

	public void setValor_descuento(double valor_descuento) {
		this.valor_descuento = valor_descuento;
	}

	public double getValor_pagado() {
		return valor_pagado;
	}

	public void setValor_pagado(double valor_pagado) {
		this.valor_pagado = valor_pagado;
	}

	public String getNumero_factura() {
		return numero_factura;
	}

	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public int getNumero_impresiones() {
		return numero_impresiones;
	}

	public void setNumero_impresiones(int numero_impresiones) {
		this.numero_impresiones = numero_impresiones;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDg_fecha_accion() {
		return dg_fecha_accion;
	}

	public void setDg_fecha_accion(Date dg_fecha_accion) {
		this.dg_fecha_accion = dg_fecha_accion;
	}

	public String getDg_accion() {
		return dg_accion;
	}

	public void setDg_accion(String dg_accion) {
		this.dg_accion = dg_accion;
	}

}
