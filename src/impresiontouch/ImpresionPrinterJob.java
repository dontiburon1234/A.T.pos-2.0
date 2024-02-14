package impresiontouch;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import com.google.gson.Gson;

import clases.DatosImprimirFactura;
import clases.EncabezadoFactura;
import clases.MensajeDocumento;
import database.MaestroDB;
import util.FormatoNumero;
import util.G;

public class ImpresionPrinterJob {
	static MaestroDB maestroDB;
	static DatosImprimirFactura datosImprimirFactura;
	static EncabezadoFactura encabezadoFactura;
	static int numeroCopias;

	static Graphics pagina;
	private static int anchoPapel = G.getInstance().anchoPapel; // 158;
	private static int espacioEntreRenglones; // inicial con el alto del logo y va incrementando por cada fila del texto.
	private static int pasoRenglon= G.getInstance().pasoRenglon; // 6;

	static Font fontTitulo; // = new Font("courier", Font.PLAIN,9); //Font.ITALIC, 7);
	FontMetrics fmText;

	public ImpresionPrinterJob(DatosImprimirFactura datosImprimirFactura, MaestroDB maestroDB,
			EncabezadoFactura encabezadoFactura, int numeroCopias) {

		this.datosImprimirFactura = datosImprimirFactura;
		this.maestroDB = maestroDB;
		this.encabezadoFactura = encabezadoFactura;
		this.numeroCopias = numeroCopias;

		String sAnchoPapel = maestroDB.cargarUnParametro("anchoPapel");
		G.getInstance().anchoPapel = Integer.valueOf(sAnchoPapel);
		anchoPapel = G.getInstance().anchoPapel;

		String sPasoRenglon = maestroDB.cargarUnParametro("pasoRenglon");
		G.getInstance().pasoRenglon = Integer.valueOf(sPasoRenglon);
		pasoRenglon = G.getInstance().pasoRenglon;

		String sSizeFont = maestroDB.cargarUnParametro("sizeFont");
		G.getInstance().sizeFont = Integer.valueOf(sSizeFont);
		fontTitulo = new Font("SansSerif", Font.TRUETYPE_FONT,G.getInstance().sizeFont); //Font.ITALIC, 7);

		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.defaultPage();
		Paper paper = pf.getPaper();

		double width = G.getInstance().anchoPapel; //210.0; //3d * 72d; //2.3d * 72d; // 8d * 72d;

		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() datosImprimirFactura.getItemPreFactura().size() "+ datosImprimirFactura.getItemPreFactura().size());
		double height = (datosImprimirFactura.getItemPreFactura().size()*pasoRenglon) * 72d;
		double margin = 0.01d * 72d;
		paper.setSize(width, height);

		paper.setImageableArea(	margin,	margin,	width - (margin * 2), height - (margin * 2));

		System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() Before- "+ dump(paper));
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);
		System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() After- " + dump(paper));
		System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() After- " + dump(pf));
		dump(pf);
		PageFormat validatePage = pj.validatePage(pf);
		System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() Valid- " + dump(validatePage));

		Book pBook = new Book();
		pBook.append(new Page(), pf);
		pj.setPageable(pBook);

		try {
			pj.print();
		} catch (PrinterException ex) {
			ex.printStackTrace();
		}
	}

	public static class Page implements Printable {

		public int print(Graphics pagina, PageFormat pageFormat, int pageIndex) {
			if (pageIndex >= 1) {
				return Printable.NO_SUCH_PAGE;
			}

			Graphics2D g2d = (Graphics2D) pagina;
			// Be careful of clips...
			//            g2d.setClip(0, 0, (int) pageFormat.getWidth(), (int) pageFormat.getHeight());
			g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

			double width = pageFormat.getImageableWidth();
			double height = pageFormat.getImageableHeight();

			//Inicio Inicio Inicio Inicio Inicio Inicio Inicio Inicio Inicio Inicio Inicio 
			String sGsonImprimirFactura = "";
			Gson gson = new Gson();
			sGsonImprimirFactura = gson.toJson(datosImprimirFactura);
			System.out.println("ImpresionPrinterJob.Page.print() sGsonImprimirFactura GSON "+sGsonImprimirFactura);

			String sGsonLicencia = gson.toJson(G.getInstance().licenciaGlobal);
			System.out.println("ImpresionPrinterJob.Page.print() LICENCIA "+sGsonLicencia);

			ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
			alMensajesFactura = maestroDB.MensajesFactura(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());

			try {
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

				if( datosImprimirFactura.getDomicilio()!=null) {
					pagina.drawString("CLIENTE: "+datosImprimirFactura.getDomicilio().getNombre()+" "+datosImprimirFactura.getDomicilio().getApellido(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					pagina.drawString("DIRECCION "+datosImprimirFactura.getDomicilio().getDireccion(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					pagina.drawString("TELEFONO "+datosImprimirFactura.getDomicilio().getTelefono(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				}		

				String sFecha = ""+datosImprimirFactura.getPreFactura().getFecha();
				sFecha = sFecha.substring(0, 16);

				centrarTexto("FECHA: "+sFecha, espacioEntreRenglones, fontTitulo, pagina);

				//centrarTexto("FECHA: "+datosImprimirFactura.getPreFactura().getFecha(), espacioEntreRenglones, fontTitulo, pagina);
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
					//int iLineaPrecio = lineaPrecio.length();
					//int iLineaPrecioParcial = fPrecioParcia.length();
					//int operacionLinea = 40-(iLineaPrecio+iLineaPrecioParcial);
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

				int impresionCredito=0;
				for (int i = 0; i < datosImprimirFactura.getMedioPagoPreFactura().size(); i++) {
					int medioPago = datosImprimirFactura.getMedioPagoPreFactura().get(i).getId_medio_pago();	
					impresionCredito = medioPago;
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
						// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo que se crea
						double recibidoCredito = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sRecibidoCredito = FormatoNumero.formatoCero(recibidoCredito);
						pagina.drawString("CREDITO........"+sRecibidoCredito,  12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						break;
					case 8: // REMISION
						
						break;
					case 9:
						// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo que se crea
						double efectivoExtranjera = datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sEfectivoExtranjera = FormatoNumero.formatoCero(efectivoExtranjera);
						pagina.drawString("EFECTIVO EXTRANJERA.............."+sEfectivoExtranjera, 12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						
						double recibidoEfectivoExtranjera = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
						String sRecibidoEfectivoExtranjera = FormatoNumero.formatoCero(recibidoEfectivoExtranjera);
						pagina.drawString("RECIBIDO EXTRANJERA.............."+sRecibidoEfectivoExtranjera, 12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						
						double cambioEfectivoExtrajera = datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sCambioEfectivoExtrajera = FormatoNumero.formatoCero(cambioEfectivoExtrajera);
						pagina.drawString("CAMBIO EXTRAJERA................"+sCambioEfectivoExtrajera, 12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						break;
					default:
						break;
					}
				}

				if(impresionCredito==7) {
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					pagina.drawString("Recibido:-------------------------------",  12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

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

				System.out.println("ImpresionPrinterJob.Page.print() numeroCopias "+numeroCopias+" COPIAS COPIAS COPIAS COPIAS ");
				if( numeroCopias == 2) {
					centrarTexto("COPIA 1", espacioEntreRenglones, fontTitulo, pagina);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				}

				try{
					System.out.println("ImpresionPrinterJob.Page.print() ESTA COMENTANDO CORTAR PAPEL");
					//FuncionesImpresora.cortarPapel(true, G.getInstance().licenciaGlobal.getImpresora());
					if(true) {
						System.out.println("ImpresionPrinterJob.Page.print() ESTA COMENTADO LA APERTURA DEL CAJON");
						//FuncionesImpresora.abrirCajon(true, G.getInstance().licenciaGlobal.getImpresora());
					}
				}catch(Exception e){ 
					e.printStackTrace();
				}
				pagina.dispose();

			} catch (Exception e) {
				System.out.println("ImpresionPrinterJob.Page.print() ERROR DE IMPRESION ");
				e.printStackTrace();
			}
			return Printable.PAGE_EXISTS;
		}
	}

	protected static String dump(Paper paper) {
		StringBuilder sb = new StringBuilder(64);
		sb.append(paper.getWidth()).append("x").append(paper.getHeight())
		.append("/").append(paper.getImageableX()).append("x").
		append(paper.getImageableY()).append(" - ").append(paper
				.getImageableWidth()).append("x").append(paper.getImageableHeight());
		return sb.toString();
	}

	protected static String dump(PageFormat pf) {
		Paper paper = pf.getPaper();
		return dump(paper);
	}

	private static void centrarTexto (String strTexto, int intY, Font fontText, Graphics g) {
		FontMetrics fmText = g.getFontMetrics(fontText);		
		g.setFont(fontText);
		int intXText = (anchoPapel - fmText.stringWidth(strTexto)) / 2;
		/*if(intXText<0) {
			strTexto = "TEXTO MUY LARGO "+strTexto;
			intXText = 10;
		}*/
		g.drawString(strTexto, intXText, intY);
	}

	private static int tamanoTexto (String strTexto, Font fontText, Graphics g) {
		FontMetrics fmText = g.getFontMetrics(fontText);		
		g.setFont(fontText);
		return fmText.stringWidth(strTexto);
	}

}
