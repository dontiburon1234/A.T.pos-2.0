package clases;

import java.util.Date;

public class TablaPagos {

	private int id_pagos;
	private Date fecha;
	private String nombreComercial;
	private double valorFactura;
	private double valorDescuento;
	private double valorPagado;
	
	public TablaPagos(int id_pagos, Date fecha, String nombreComercial, double valorFactura, double valorDescuento,
			double valorPagado) {
		super();
		this.id_pagos = id_pagos;
		this.fecha = fecha;
		this.nombreComercial = nombreComercial;
		this.valorFactura = valorFactura;
		this.valorDescuento = valorDescuento;
		this.valorPagado = valorPagado;
	}
	
	public int getId_pagos() {
		return id_pagos;
	}
	public void setId_pagos(int id_pagos) {
		this.id_pagos = id_pagos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public double getValorFactura() {
		return valorFactura;
	}
	public void setValorFactura(double valorFactura) {
		this.valorFactura = valorFactura;
	}
	public double getValorDescuento() {
		return valorDescuento;
	}
	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}
	public double getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(double valorPagado) {
		this.valorPagado = valorPagado;
	}
	
}
