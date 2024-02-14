package impresiontouch;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.google.gson.Gson;

import clases.DatosImprimirFactura;
import clases.EncabezadoFactura;
import clases.MensajeDocumento;
import database.MaestroDB;
import util.FormatoNumero;
import util.G;

public class ImpresionConLogo {

	MaestroDB maestroDB;
	DatosImprimirFactura datosImprimirFactura;
	EncabezadoFactura encabezadoFactura;

	PrintJob printJob;
	Graphics pagina;
	private int anchoPapel;
	private int espacioEntreRenglones;
	private int pasoRenglon=6;

	Font fontTitulo = new Font("courier", Font.PLAIN,7); //Font.ITALIC, 7);
	FontMetrics fmText;

	public ImpresionConLogo(DatosImprimirFactura datosImprimirFactura, MaestroDB maestroDB,EncabezadoFactura encabezadoFactura) {
		super();
		this.datosImprimirFactura = datosImprimirFactura;
		this.maestroDB = maestroDB;
		this.encabezadoFactura = encabezadoFactura;
				
		String sAnchoPapel = maestroDB.cargarUnParametro("anchoPapel");
		anchoPapel = Integer.valueOf(sAnchoPapel);
		
		/*String sGsonImprimirFactura = "";
		Gson gson = new Gson();
		sGsonImprimirFactura = gson.toJson(datosImprimirFactura);
		System.out.println("ImpresionConLogo.ImpresionConLogo() sGsonImprimirFactura GSON "+sGsonImprimirFactura);*/

		ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
		alMensajesFactura = maestroDB.MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());

		try {
			printJob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "ATpos", null);
			
			pagina = printJob.getGraphics();
			pagina.setColor(Color.BLACK);

			pagina.drawImage(G.getInstance().imgLogo, 5, 0, anchoPapel-5,G.getInstance().alto,null);
			
			espacioEntreRenglones = G.getInstance().alto; // alto;
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			for (int i = 0; i < alMensajesFactura.size(); i++) {
				if(alMensajesFactura.get(i).getUbicacion().equals("encabezado")) {
					centrarTexto(alMensajesFactura.get(i).getMensaje(), espacioEntreRenglones, fontTitulo, pagina);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				}
			}

			String numeroResolucion = "RESOL. DIAN No "+datosImprimirFactura.getEncabezadoFactura().getResolucion()+" "+
					datosImprimirFactura.getEncabezadoFactura().getFechaExpedicionNumeracionAutorizada();
			numeroResolucion = numeroResolucion.substring(0, numeroResolucion.indexOf("00:00:00"));

			centrarTexto(numeroResolucion, espacioEntreRenglones, fontTitulo, pagina);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			centrarTexto("HABILITADA: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoNumeracionAutorizada()+" del "+
					(int)datosImprimirFactura.getEncabezadoFactura().getRangoInicial()+" al "+(int)datosImprimirFactura.getEncabezadoFactura().getRangoFinal(), espacioEntreRenglones, fontTitulo, pagina);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon; //				encabezadoFactura.setPrefijoNumeracionAutorizada(rs.getString(6));
			centrarTexto("FACTURA DE VENTA No.: "+datosImprimirFactura.getEncabezadoFactura().getPrefijoNumeracionAutorizada()+"-"+(int)datosImprimirFactura.getPreFactura().getNumero(), espacioEntreRenglones, fontTitulo, pagina);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			pagina.setFont(fontTitulo);

			// domicilio impremi dos facturas la segunda debe decir copia uno
			int imprimeDos = 0;
			if( datosImprimirFactura.getDomicilio()!=null) {
				imprimeDos = 2;

				pagina.drawString("CLIENTE: "+datosImprimirFactura.getDomicilio().getNombre()+" "+datosImprimirFactura.getDomicilio().getApellido(), 12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				pagina.drawString("DIRECCION "+datosImprimirFactura.getDomicilio().getDireccion(), 12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				pagina.drawString("TELEFONO "+datosImprimirFactura.getDomicilio().getTelefono(), 12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			}		

			centrarTexto("FECHA: "+datosImprimirFactura.getPreFactura().getFecha(), espacioEntreRenglones, fontTitulo, pagina);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			centrarTexto("CAJERO: "+encabezadoFactura.getNombreCajero(), espacioEntreRenglones, fontTitulo, pagina);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			pagina.drawString("ITEM         DESCRIPCION", 10, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			pagina.drawString("IMP    CANT    VALOR    TOTAL", 10, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			// lista de articulos
			String sIva = "";
			for (int i = 0; i < datosImprimirFactura.getItemPreFactura().size(); i++) {
				int idBaseIvaProducto = datosImprimirFactura.getItemPreFactura().get(i).getId_base_iva();
				for (int j = 0; j < datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
					if(datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==idBaseIvaProducto) {
						sIva = datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getEstadoBaseIva();
					}
				}

				pagina.drawString(datosImprimirFactura.getItemPreFactura().get(i).getCodigo_articulo()+" "+
				datosImprimirFactura.getEncabezadoFactura().getArticuloFactura().get(i).getNombreArticulo(), 
				12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				double cantidad = datosImprimirFactura.getItemPreFactura().get(i).getCantidad_unidad_medida();
				double precioUnitario = datosImprimirFactura.getItemPreFactura().get(i).getValor_unidad();
				String sPrecioUnitario = FormatoNumero.formatoCero(precioUnitario);//  FormatoNumero.formato(precioUnitario);
				double precioParcial = datosImprimirFactura.getItemPreFactura().get(i).getValor_item();
				String fPrecioParcia = FormatoNumero.formatoCero(precioParcial);
				String lineaPrecio = sIva+" - "+cantidad+" - "+sPrecioUnitario;
				int iLineaPrecio = lineaPrecio.length();
				int iLineaPrecioParcial = fPrecioParcia.length();
				int operacionLinea = 40-(iLineaPrecio+iLineaPrecioParcial);
				/*for (int k = 0; k < operacionLinea; k++) {
					lineaPrecio = lineaPrecio +" ";
				}*/
				lineaPrecio = lineaPrecio+" - "+fPrecioParcia;

				pagina.drawString(lineaPrecio, 12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			}

			// Resumen de impuestos
			pagina.drawString("========================================", 12, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			pagina.drawString("IMP%    - BASE  -  V.IMP  -  TOTAL", 12, espacioEntreRenglones);
			
			centrarTexto("IMP%    - BASE  -  V.IMP  -  TOTAL", 0, fontTitulo, pagina);
			
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			String sNombreIva = "";
			for (int i = 0; i < datosImprimirFactura.getIvaValor().size(); i++) {
				int nombreIva = datosImprimirFactura.getIvaValor().get(i).getIdIva();
				for (int j = 0; j < datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
					if(datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==nombreIva) {
						sNombreIva = datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getEstadoBaseIva();
					}
				}
				double baseImpuesto = datosImprimirFactura.getIvaValor().get(i).getTotalxIva() - datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sBaseImpuesto = FormatoNumero.formatoCero(baseImpuesto);
				double ivas = datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sIvas = FormatoNumero.formatoCero(ivas);
				double totalImpuesto = baseImpuesto = datosImprimirFactura.getIvaValor().get(i).getTotalxIva();
				String sTotalImpuesto = FormatoNumero.formatoCero(totalImpuesto);

				int ttNombreIva = tamanoTexto (sNombreIva, fontTitulo, pagina);
				int ttBaseImpuesto = tamanoTexto (sBaseImpuesto, fontTitulo, pagina);
				int ttIvas = tamanoTexto (sIvas, fontTitulo, pagina);
				int ttTotalImpuesto = tamanoTexto (sTotalImpuesto, fontTitulo, pagina);

				int espacio1 = ((anchoPapel-2) - (ttNombreIva+ttBaseImpuesto+ttIvas+ttTotalImpuesto))/3; //(40 - (iNombreIva+iBaseImpuesto+iIvas+iTotalImpuesto))/3;
				
				pagina.drawString(sNombreIva, (10), espacioEntreRenglones);
				pagina.drawString(sBaseImpuesto, (ttNombreIva+espacio1), espacioEntreRenglones);
				pagina.drawString(sIvas, (ttNombreIva+espacio1+ttBaseImpuesto+espacio1), espacioEntreRenglones);
				pagina.drawString(sTotalImpuesto, (ttNombreIva+espacio1+ttBaseImpuesto+espacio1+ttIvas+espacio1), espacioEntreRenglones);
				
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			}

			// resumen de la forma de pago

			/*pagina.drawString(iterator,  12, espacio);
			espacio = espacio+pasoRenglon;*/

			pagina.drawString("----------------------------------------", 12, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			//impresionFactura.AddCabecera("CANTIDAD DE ARTICULOS......."+datosImprimirFactura.getPreFactura().get);

			double valorFactura = datosImprimirFactura.getPreFactura().getValor_prefactura();
			String sValorFactura = FormatoNumero.formatoCero(valorFactura);

			pagina.drawString("VALOR FACTURA........."+sValorFactura, 12, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			for (int i = 0; i < datosImprimirFactura.getMedioPagoPreFactura().size(); i++) {
				int medioPago = datosImprimirFactura.getMedioPagoPreFactura().get(i).getId_medio_pago();	
				switch (medioPago) {
				case 1:
					double efectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
					String sEfectivo = FormatoNumero.formatoCero(efectivo);
					pagina.drawString("EFECTIVO.............."+sEfectivo, 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					double recibidoEfectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
					String sRecibidoEfectivo = FormatoNumero.formatoCero(recibidoEfectivo);
					pagina.drawString("RECIBIDO.............."+sRecibidoEfectivo, 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					double cambioEfectivo = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
					String sCambioEfectivo = FormatoNumero.formatoCero(cambioEfectivo);
					pagina.drawString("CAMBIO................"+sCambioEfectivo, 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					break;
				case 2:
					double recibido = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
					String sRecibido = FormatoNumero.formatoCero(recibido);
					pagina.drawString("TARJETA CREDITO......."+sRecibido,  12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					break;
				case 3:
					double recibidoDebito = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
					String sRecibidoDebito = FormatoNumero.formatoCero(recibidoDebito);
					pagina.drawString("TARJETA DEBITO........"+sRecibidoDebito,  12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					break;
				case 7:// CREDITO
					// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo qie se crea
					double recibidoCredito = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
					String sRecibidoCredito = FormatoNumero.formatoCero(recibidoCredito);
					pagina.drawString("CREDITO........"+sRecibidoCredito,  12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					break;
				case 8: // REMISION



					break;
				default:
					break;
				}
			}

			pagina.drawString("----------------------------------------",  12, espacioEntreRenglones);
			espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

			// Mensajes factura
			//			ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
			//			alMensajesFactura = MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());
			for (int i = 0; i < alMensajesFactura.size(); i++) {
				if(alMensajesFactura.get(i).getUbicacion().equals("piedepagina")) {
					centrarTexto(alMensajesFactura.get(i).getMensaje(), espacioEntreRenglones, fontTitulo, pagina);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				}
			}

			if(imprimeDos==2) {
				centrarTexto("COPIA 1", espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
			}else {
				//impresionFactura.ImprimirDocumento(true);
			}
			imprimeDos=0;

			pagina.dispose();
			
			printJob.end();
			
			//getPageDimension java.awt.Dimension[width=595,height=841]
			//getPageResolution 72
			
		} catch (Exception e) {
			System.out.println("ImpresionConLogo.ImpresionConLogo() ERROR DE IMPRESION ");
			e.printStackTrace();
		}

	}

	private void centrarTexto (String strTexto, int intY, Font fontText, Graphics g) {
		FontMetrics fmText = g.getFontMetrics(fontText);		
		g.setFont(fontText);

		int intXText = (anchoPapel - fmText.stringWidth(strTexto)) / 2; //getBounds().x + (getBounds().width - fmText.stringWidth(strTexto)) / 2;

		//System.out.println("MyCanvas.centrarTexto() intXText "+intXText+" fmText "+fmText.stringWidth(strTexto)+" "+strTexto);
		if(intXText<0) {
			strTexto = "TEXTO MUY LARGO "+strTexto;
			intXText = 10;
		}
		g.drawString(strTexto, intXText, intY);
		
		//System.out.println("ImpresionConLogo.centrarTexto() fmText.stringWidth(strTexto) "+fmText.stringWidth(strTexto)+" intXText X"+intXText+"X"+" "+strTexto);
	}
	
	private int tamanoTexto (String strTexto, Font fontText, Graphics g) {
		FontMetrics fmText = g.getFontMetrics(fontText);		
		g.setFont(fontText);
		return fmText.stringWidth(strTexto);
	}

}
