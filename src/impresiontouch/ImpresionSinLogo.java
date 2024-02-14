package impresiontouch;

import java.util.ArrayList;

import clases.DatosImprimirFactura;
import clases.Empresa;
import clases.EncabezadoFactura;
import clases.MensajeDocumento;
import database.MaestroDB;
import util.FormatoNumero;

public class ImpresionSinLogo {
	DatosImprimirFactura datosImprimirFactura;
	EncabezadoFactura encabezadoFactura;

	public ImpresionSinLogo(DatosImprimirFactura datosImprimirFactura, MaestroDB maestroDB,EncabezadoFactura encabezadoFactura, int numeroCopias) {
		this.datosImprimirFactura = datosImprimirFactura;
		this.encabezadoFactura = encabezadoFactura;

		ImpresionFactura impresionFactura = new ImpresionFactura();

		/*impresionFactura.AddCabecera(centrado(datosImprimirFactura.getEncabezadoFactura().getNombreEmpresa()+" NIT "+datosImprimirFactura.getEncabezadoFactura().getNit()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("IVA REGIMEN "+datosImprimirFactura.getEncabezadoFactura().getNombreRegimen()+" - ACT. ICA 4722"));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("NO SOMOS GRANDES CONTRIBUYENTES"));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());*/

		ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
		alMensajesFactura = maestroDB.MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());
		
		System.out.println("ImpresionSinLogo.ImpresionSinLogo() alMensajesFactura.size() "+alMensajesFactura.size());
		
		for (int i = 0; i < alMensajesFactura.size(); i++) {
			
			System.out.println("ImpresionSinLogo.ImpresionSinLogo() (alMensajesFactura.get(i).getUbicacion() "+alMensajesFactura.get(i).getUbicacion());
			
			if(alMensajesFactura.get(i).getUbicacion().replace(" ","").equals("encabezado")) {
				System.out.println("ImpresionSinLogo.ImpresionSinLogo() alMensajesFactura.get(i).getMensaje() "+alMensajesFactura.get(i).getMensaje());
				impresionFactura.AddCabecera(centrado(alMensajesFactura.get(i).getMensaje()));
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}
		
		if(maestroDB.traeEmpresa().getId_regimen()!=2) {
			String numeroResolucion = "RESOL. DIAN No "+datosImprimirFactura.getEncabezadoFactura().getResolucion()+" "+
					datosImprimirFactura.getEncabezadoFactura().getFechaExpedicionNumeracionAutorizada();
			numeroResolucion = numeroResolucion.substring(0, numeroResolucion.indexOf("00:00:00"));
			impresionFactura.AddCabecera(centrado(numeroResolucion));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera(centrado( "HABILITADA: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoNumeracionAutorizada()+" del "+
					(int)datosImprimirFactura.getEncabezadoFactura().getRangoInicial()+" al "+(int)datosImprimirFactura.getEncabezadoFactura().getRangoFinal()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		
		impresionFactura.AddCabecera(centrado("FACTURA DE VENTA No.: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoNumeracionAutorizada()+"-"+(int)datosImprimirFactura.getPreFactura().getNumero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		// domicilio impremi dos facturas la segunda debe decir copia uno
		int imprimeDos = 0;
		if( datosImprimirFactura.getDomicilio()!=null) {
			imprimeDos = 2;
			impresionFactura.AddCabecera("CLIENTE: "+datosImprimirFactura.getDomicilio().getNombre()+" "+datosImprimirFactura.getDomicilio().getApellido());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("DIRECCION "+datosImprimirFactura.getDomicilio().getDireccion());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("TELEFONO "+datosImprimirFactura.getDomicilio().getTelefono());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		
		String sFecha = ""+datosImprimirFactura.getPreFactura().getFecha();
		sFecha = sFecha.substring(0, sFecha.indexOf("."));
		
		impresionFactura.AddCabecera(centrado("FECHA: "+sFecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera(centrado("CAJERO: "+encabezadoFactura.getNombreCajero()));
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("ITEM             DESCRIPCION");
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("IMP    CANT          VALOR         TOTAL");
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());


		// lista de articulos
		String sIva = "";
		for (int i = 0; i < datosImprimirFactura.getItemPreFactura().size(); i++) {
			int idBaseIvaProducto = datosImprimirFactura.getItemPreFactura().get(i).getId_base_iva();
			for (int j = 0; j < datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
				if(datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==idBaseIvaProducto) {
					sIva = datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getNombreBaseIva();// .getEstadoBaseIva();
				}
			}
			impresionFactura.AddCabecera(datosImprimirFactura.getItemPreFactura().get(i).getCodigo_articulo()+" "+datosImprimirFactura.getEncabezadoFactura().getArticuloFactura().get(i).getNombreArticulo());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			double cantidad = datosImprimirFactura.getItemPreFactura().get(i).getCantidad_unidad_medida();
			double precioUnitario = datosImprimirFactura.getItemPreFactura().get(i).getValor_unidad();
			String sPrecioUnitario = FormatoNumero.formatoCero(precioUnitario);//  FormatoNumero.formato(precioUnitario);
			double precioParcial = datosImprimirFactura.getItemPreFactura().get(i).getValor_item();
			String fPrecioParcia = FormatoNumero.formatoCero(precioParcial);
			
			if(maestroDB.traeEmpresa().getId_regimen()==2) {
				sIva = "       ";
			}
			
			
			String lineaPrecio = sIva+"    "+cantidad+"      "+sPrecioUnitario;
			int iLineaPrecio = lineaPrecio.length();
			int iLineaPrecioParcial = fPrecioParcia.length();
			int operacionLinea = 40-(iLineaPrecio+iLineaPrecioParcial);
			for (int k = 0; k < operacionLinea; k++) {
				lineaPrecio = lineaPrecio +" ";
			}
			lineaPrecio = lineaPrecio+fPrecioParcia;
			impresionFactura.AddCabecera(lineaPrecio);
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}

		Empresa empresa = maestroDB.traeEmpresa();
		
		if(empresa.getId_regimen()==1) {
			// Resumen de impuestos
			//impresionFactura.AddCabecera("========================================");
			//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("IMP%        BASE       V.IMP       TOTAL");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
	
			String sNombreIva = "";
			for (int i = 0; i < datosImprimirFactura.getIvaValor().size(); i++) {
				int nombreIva = datosImprimirFactura.getIvaValor().get(i).getIdIva();
				for (int j = 0; j < datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
					if(datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==nombreIva) {
						sNombreIva = datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getNombreBaseIva();//   .getEstadoBaseIva();
					}
				}
				double baseImpuesto = datosImprimirFactura.getIvaValor().get(i).getTotalxIva() - datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sBaseImpuesto = FormatoNumero.formatoCero(baseImpuesto);
				double ivas = datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sIvas = FormatoNumero.formatoCero(ivas);
				double totalImpuesto = baseImpuesto = datosImprimirFactura.getIvaValor().get(i).getTotalxIva();
				String sTotalImpuesto = FormatoNumero.formatoCero(totalImpuesto);
	
				int iNombreIva = sNombreIva.length();
				int iBaseImpuesto = sBaseImpuesto.length();
				int iIvas = sIvas.length();
				int iTotalImpuesto = sTotalImpuesto.length();
	
				int espacio = (40 - (iNombreIva+iBaseImpuesto+iIvas+iTotalImpuesto))/3;
	
				String sPrimerEspacio = "";
				for (int j = 0; j < espacio; j++) {
					sPrimerEspacio = sPrimerEspacio + " ";
				}
	
				String lineaImpuestos = ""+sNombreIva+"    "+sBaseImpuesto+"      "+sIvas;
				String finalLinea = " "+sTotalImpuesto;
	
				int lineaImpu = 40-(lineaImpuestos.length()+finalLinea.length());
				for (int j = 0; j < lineaImpu; j++) {
					lineaImpuestos = lineaImpuestos+" ";
				}
				lineaImpuestos = sNombreIva+sPrimerEspacio+sBaseImpuesto+sPrimerEspacio+sIvas+sPrimerEspacio+sTotalImpuesto; //= lineaImpuestos + finalLinea;
				impresionFactura.AddCabecera(lineaImpuestos);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}

		// resumen de la forma de pago
		//impresionFactura.AddCabecera("----------------------------------------");
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera("CANTIDAD DE ARTICULOS......."+datosImprimirFactura.getPreFactura().get);

		double valorFactura = datosImprimirFactura.getPreFactura().getValor_prefactura();
		String sValorFactura = FormatoNumero.formatoCero(valorFactura);
		impresionFactura.AddCabecera("VALOR FACTURA........."+sValorFactura);

		for (int i = 0; i < datosImprimirFactura.getMedioPagoPreFactura().size(); i++) {
			int medioPago = datosImprimirFactura.getMedioPagoPreFactura().get(i).getId_medio_pago();	
			switch (medioPago) {
			case 1:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double efectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sEfectivo = FormatoNumero.formatoCero(efectivo);
				impresionFactura.AddCabecera("EFECTIVO.............."+sEfectivo);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoEfectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
				String sRecibidoEfectivo = FormatoNumero.formatoCero(recibidoEfectivo);
				impresionFactura.AddCabecera("RECIBIDO.............."+sRecibidoEfectivo);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double cambioEfectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sCambioEfectivo = FormatoNumero.formatoCero(cambioEfectivo);
				impresionFactura.AddCabecera("CAMBIO................"+sCambioEfectivo);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 2:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibido = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibido = FormatoNumero.formatoCero(recibido);
				impresionFactura.AddCabecera("TARJETA CREDITO......."+sRecibido);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 3:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoDebito = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibidoDebito = FormatoNumero.formatoCero(recibidoDebito);
				impresionFactura.AddCabecera("TARJETA DEBITO........"+sRecibidoDebito);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 7:// CREDITO
				// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo qie se crea
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoCredito = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibidoCredito = FormatoNumero.formatoCero(recibidoCredito);
				impresionFactura.AddCabecera("CREDITO........"+sRecibidoCredito);
				break;
			case 8: // REMISION
				break;
			case 9:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double efectivoExtranjera = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sEfectivoExtranjera = FormatoNumero.formatoCero(efectivoExtranjera);
				impresionFactura.AddCabecera("EFECTIVO EXTRANJERA.............."+sEfectivoExtranjera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				double recibidoEfectivoExtranjera = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
				String sRecibidoEfectivoExtranjera = FormatoNumero.formatoCero(recibidoEfectivoExtranjera);
				
				double retornoaExtranjera = recibidoEfectivoExtranjera/datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_comision();
				String sRetornoaExtranjera = FormatoNumero.formatoCero(retornoaExtranjera);
				
				impresionFactura.AddCabecera("RECIBIDO EXTRANJERA......"+ sRetornoaExtranjera +"......"+sRecibidoEfectivoExtranjera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				double cambioEfectivoExtrajera = (datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-
						datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago());
				String sCambioEfectivoExtrajera = FormatoNumero.formatoCero(cambioEfectivoExtrajera);
				impresionFactura.AddCabecera("CAMBIO EXTRAJERA................."+sCambioEfectivoExtrajera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				break;
			default:
				break;
			}
		}
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera("----------------------------------------");
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		// Mensajes factura
		/*ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
	alMensajesFactura = MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());*/
		for (int i = 0; i < alMensajesFactura.size(); i++) {
			if(alMensajesFactura.get(i).getUbicacion().equals("piedepagina")) {
				impresionFactura.AddCabecera(centrado(alMensajesFactura.get(i).getMensaje()));
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}

		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
		impresionFactura.AddPieLinea(impresionFactura.DarEspacio());

		if(imprimeDos==2) {
			impresionFactura.ImprimirDocumento(true);
			impresionFactura.AddCabecera(centrado("COPIA 1"));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.ImprimirDocumento(true);

		}else {
			impresionFactura.ImprimirDocumento(true);
		}
		imprimeDos=0;
		
		System.out.println("ImpresionSinLogo.ImpresionSinLogo() LLEGO AL FINAL LLEGO AL FINAL LLEGO AL FINAL LLEGO AL FINAL LLEGO AL FINAL ");
		
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
