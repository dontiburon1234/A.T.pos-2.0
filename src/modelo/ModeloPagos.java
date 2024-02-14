package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import clases.Almacen;
import clases.Caja;
import clases.Empresa;
import clases.Pagos;
import clases.Proveedor;
import clases.TipoPago;
import controlador.ConPagos;
import database.MaestroDB;
import impresiontouch.ImpresionFactura;
import util.FormatoNumero;
import util.G;

public class ModeloPagos {

	private ConPagos conPagos;
	
	private String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

	public ModeloPagos() {}

	public void imprimirReportePagos(ArrayList<Pagos> alPagos,Date fechaInicial,Date FechaFinal,MaestroDB maestroDB) {

		ImpresionFactura impresionFactura = new ImpresionFactura();
		Empresa empresa = maestroDB.traeEmpresa();
		impresionFactura.AddCabecera(centrado(empresa.getNombre()+" NIT "+empresa.getNit()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
		Almacen almacen = maestroDB.almacen(id_almacen);
		int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
		Caja caja = maestroDB.caja(id_Caja);
		impresionFactura.AddCabecera(centrado("Almacen "+almacen.getNombre()+" Caja "+caja.getNombre()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Cajero "+G.getInstance().cajero));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Fecha Inicial "+fechaInicial));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Fecha Final "+FechaFinal));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		ArrayList<TipoPago> alTipoPago = maestroDB.tipoPago();

		double totalValorFactura = 0;
		double totalDescuentos = 0;
		double totalValorPagado = 0;
		
		for (int j = 0; j < alTipoPago.size(); j++) {
			impresionFactura.AddCabecera(alTipoPago.get(j).getNombre());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("V.FACTURA      V.DESCUENTO      V.PAGADO");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());			
			String nombreComercialProveedor = "";
			double dValorFactura = 0;
			double dValorDescuento = 0;
			double dValorPagado = 0;
			for (int i = 0; i < alPagos.size(); i++) {
				if(alPagos.get(i).getId_tipo_pago()==alTipoPago.get(j).getId()) {
					nombreComercialProveedor = maestroDB.traeNombreComercialProveedor(alPagos.get(i).getId_proveedor());
					
					if(!(nombreComercialProveedor.length()<40))nombreComercialProveedor = nombreComercialProveedor.substring(0, 40);

					impresionFactura.AddCabecera(nombreComercialProveedor);
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					
					double valorFactura = alPagos.get(i).getValor_factura();
					String sValorFactura = FormatoNumero.formatoCero(valorFactura);
					
					double valorDescuento = alPagos.get(i).getValor_descuento();
					String sValorDescuento = FormatoNumero.formatoCero(valorDescuento);
					
					double valorPagado = alPagos.get(i).getValor_pagado();
					String sValorPagado = FormatoNumero.formatoCero(valorPagado);
					String espacio = "";
					int iEspacio = (40-(sValorFactura.length()+sValorDescuento.length()+sValorPagado.length()))/2;
					for (int k = 0; k < iEspacio; k++) {espacio = espacio + " ";}
					impresionFactura.AddCabecera(sValorFactura+espacio+sValorDescuento+espacio+sValorPagado);
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					if(alPagos.get(i).getComentario().length()>0) {
						impresionFactura.AddCabecera(alPagos.get(i).getComentario());
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					}
					dValorFactura = dValorFactura+alPagos.get(i).getValor_factura();
					dValorDescuento = dValorDescuento+alPagos.get(i).getValor_descuento();
					dValorPagado = dValorPagado+alPagos.get(i).getValor_pagado();
				}
			}
			String espacio = "";
			
			
			//double ssValorFactura = 
			String sdValorFactura = FormatoNumero.formatoCero(dValorFactura);
			String sdValorDescuento = FormatoNumero.formatoCero(dValorDescuento);
			String sdValorPagado = FormatoNumero.formatoCero(dValorPagado);
			int iEspacio = (40-(sdValorFactura.length()+sdValorDescuento.length()+sdValorPagado.length()))/2;
			for (int k = 0; k < iEspacio; k++) {espacio = espacio + " ";}
			impresionFactura.AddCabecera("----------------------------------------");			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera(sdValorFactura+espacio+sdValorDescuento+espacio+sdValorPagado);
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
			totalValorFactura = totalValorFactura + dValorFactura;
			totalDescuentos = totalDescuentos +dValorDescuento;
			totalValorPagado = totalValorPagado+dValorPagado;
		}
		
		String sTotalValorFactura = FormatoNumero.formatoCero(totalValorFactura);
		String sTotalDescuentos = FormatoNumero.formatoCero(totalDescuentos);
		String sTotalValorPagado = FormatoNumero.formatoCero(totalValorPagado);
		
		impresionFactura.AddCabecera("========================================");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		String espacio = "";
		int iEspacio = (40-(sTotalValorFactura.length()+sTotalDescuentos.length()+sTotalValorPagado.length()))/2;
		for (int k = 0; k < iEspacio; k++) {espacio = espacio + " ";}
		
		
		impresionFactura.AddCabecera(sTotalValorFactura+espacio+sTotalDescuentos+espacio+sTotalValorPagado);			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("========================================");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(centrado(" FIN"));
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.ImprimirDocumento(false);
	}
	
	public void imprimirPagoFirma(int id_pago, int id_caja, int id_almacen, MaestroDB maestroDB) {
		ImpresionFactura impresionFactura = new ImpresionFactura();
		Empresa empresa = maestroDB.traeEmpresa();
		impresionFactura.AddCabecera(centrado(empresa.getNombre()+" NIT "+empresa.getNit()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
		Almacen almacen = maestroDB.almacen(id_almacen);
		int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
		Caja caja = maestroDB.caja(id_Caja);
		impresionFactura.AddCabecera(centrado("Almacen "+almacen.getNombre()+" Caja "+caja.getNombre()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Cajero "+G.getInstance().cajero));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Fecha "+fecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		int id_cajero = G.getInstance().getIdCajero;
		ArrayList<TipoPago> alTipoPago = maestroDB.tipoPago();
		Pagos pagos = maestroDB.traerPagoConId(id_pago, id_caja, id_almacen, id_cajero);
		
		System.err.println("ModeloPagos.imprimirPagoFirma() Nombre tipo_pago() "+alTipoPago.get(pagos.getId_tipo_pago()-1).getNombre());
		
		impresionFactura.AddCabecera(centrado(alTipoPago.get(pagos.getId_tipo_pago()-1).getNombre()));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		Proveedor proveedor = maestroDB.traeProveedorConId(pagos.getId_proveedor());
		
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(""+proveedor.getDocumento());			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(proveedor.getNombre());	
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("Fecha de pago "+pagos.getFechapago());	
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("Valor Facturado "+pagos.getValor_factura());	
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		if(pagos.getValor_descuento()>0) {
			impresionFactura.AddCabecera("Valor Devoluci\u00F3n "+pagos.getValor_descuento());	
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		impresionFactura.AddCabecera("Valor Pagado "+pagos.getValor_pagado());	
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		maestroDB.actualizaCantidadImpresionesPagos(pagos.getId(), id_caja, id_almacen, pagos.getNumero_impresiones()+1);
		
		impresionFactura.AddCabecera(centrado("Copia "+pagos.getNumero_impresiones()));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("========================================");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(centrado(" FIN"));
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.ImprimirDocumento(false);
		
	}

	public String dato(String num,String numero, int tipoCampo){
		//System.out.println("ModeloFactura dato: tipoCampo "+tipoCampo);
		// num = numero que ingresa
		// numero = número que estaba
		// tipoCampo = caracteristica que se quiere en el JTextField
		// tipoCampo = 0 solo números sin formato
		// tipoCampo = 1 numeros con formato y punto
		// tipoCampo = 2 numero con formato sin punto
		// tipoCampo = 3 acepta cualquier tipo de caracter

		if(tipoCampo == 0){ // solo números sin formato
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num.equals("0")){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}
			return numero;
		}
		else if(tipoCampo == 1){ //numeros con formato y punto
			numero = numero.replace(",", "");
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="0"){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="."){
				if(numero.indexOf(".")==-1){
					if(numero.length()==0){
						numero="0.";
					}else{
						String dato1 = numero;
						numero = dato1+num;
					}
				}
			}
			return FormatoNumero.formato(numero);
		}else if(tipoCampo==2){ // numero con formato sin punto
			numero = numero.replace(",", "");
			if(num.equals("1")|| num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")||
					num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
				if(numero.equals("0")){
					numero = num;
				}else{
					String dato1 = numero;
					numero = dato1+num;
				}
			}if(num=="0"){
				if(numero.length()==0){
					numero=num;
				}
				else if(numero.equals("0")){
					numero = num;
				}
				else if(numero.length()>=1){
					String dato1 = numero;
					numero = dato1+num;
				}
			}
			return FormatoNumero.formato(numero);
		}
		else if(tipoCampo==3){  // 3 todos los caracteres
			return numero + num;
		}else{
			return numero + num;
		}
	}

	public ConPagos getConPagos() {
		return conPagos;
	}

	public void setConPagos(ConPagos conPagos) {
		this.conPagos = conPagos;
	}

	private String centrado(String sTitulo) {
		int titulo = (44 - sTitulo.length())/2;
		String sTituloEspacio = "";
		for (int i = 0; i < titulo; i++) {
			sTituloEspacio = sTituloEspacio+" ";
		}
		sTituloEspacio = sTituloEspacio +sTitulo;
		return sTituloEspacio;
	}

}
