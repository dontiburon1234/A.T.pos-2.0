package modelo;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.Almacen;
import clases.Articulo;
import clases.BaseIva;
import clases.Caja;
import clases.Cajero;
import clases.DatosImprimirFactura;
import clases.DevolucionVenta;
import clases.Domicilio;
import clases.Empresa;
import clases.EncabezadoFactura;
import clases.Factura;
import clases.ItemDevolucionVenta;
import clases.ItemFactura;
import clases.ItemRemision;
import clases.IvaValor;
import clases.MedioPagoFactura;
import clases.MensajeDocumento;
import clases.NumeracionAutorizada;
import clases.Regimen;
import clases.Remision;
import clases.Usuario;
import controlador.ConDevoluciones;
import database.MaestroDB;
import impresiontouch.ImpresionFactura;
import util.FormatoNumero;
import util.G;

public class ModeloDevoluciones {

	static MaestroDB maestroDB;
	static DatosImprimirFactura datosImprimirFactura;
	static EncabezadoFactura encabezadoFactura;
	static double numeroFactura;
	static int numeroCopias;

	static Graphics pagina;
	
	private static int anchoPapel=G.getInstance().anchoPapel; // 158;
	private static int espacioEntreRenglones;
	private static int pasoRenglon= G.getInstance().pasoRenglon; // 6;

	static Font fontTitulo = new Font("SansSerif", Font.TRUETYPE_FONT,G.getInstance().sizeFont); // new Font("courier", Font.PLAIN,7); //Font.ITALIC, 7);
	FontMetrics fmText;

	private ConDevoluciones conDevoluciones;

	private String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp fecha = java.sql.Timestamp.valueOf( inputDia );

	public ModeloDevoluciones() {}

	public void imprimeDevolucion(ArrayList<ItemFactura> alItemFacturaDevolucion,ArrayList<DevolucionVenta> alDevolucionVenta,
			String prefijo,double numero_factura,MaestroDB maestroDB, java.sql.Timestamp fechaFactura) {

		System.out.println("ModeloDevoluciones.imprimeDevolucion() AQUI ES DONDE IMPRIME DE DEVOLUCION DE UNA FACTURA");
		
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

		String sFecha = ""+alDevolucionVenta.get(0).getFecha(); // fecha;
		sFecha = sFecha.substring(0, sFecha.indexOf("."));

		impresionFactura.AddCabecera(centrado("Fecha "+sFecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		int tamanoAlDevolucionVenta = alDevolucionVenta.size();
		impresionFactura.AddCabecera(centrado("Consecutivo: "+alDevolucionVenta.get(tamanoAlDevolucionVenta-1).getNumero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		int iNumero_factura = (int) numero_factura;

		impresionFactura.AddCabecera(centrado("Factura N\u00FAmero "+prefijo+"-"+iNumero_factura));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		String sFechaFactura = ""+fechaFactura;
		sFechaFactura = sFechaFactura.substring(0, sFechaFactura.indexOf("."));
		impresionFactura.AddCabecera(centrado("Fecha factura "+sFechaFactura));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		impresionFactura.AddCabecera("   CODIGO   CANTIDAD     PRECIO");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		double valorDevolucion = 0;
		for (int i = 0; i < alItemFacturaDevolucion.size(); i++) {

			String codigoArticulo = alItemFacturaDevolucion.get(i).getCodigo_articulo();
			String titulo1 = codigoArticulo;
			int iTitulo1 = 12-titulo1.length();
			for (int k = 0; k < iTitulo1; k++) {
				titulo1 = " "+titulo1;
			}

			String cantidadDevuelta = ""+alItemFacturaDevolucion.get(i).getCantidad_unidad_medida();
			String titulo2 = cantidadDevuelta;
			int iTitulo2 = 8-titulo2.length();
			for (int k = 0; k < iTitulo2; k++) {
				titulo2 = " "+titulo2;
			}

			valorDevolucion = valorDevolucion+alItemFacturaDevolucion.get(i).getValor_item();
			String valorDeLaDevolucion = FormatoNumero.formatoCero(alItemFacturaDevolucion.get(i).getValor_item());
			String titulo3 = valorDeLaDevolucion;
			int iTitulo3 = 10-titulo3.length();
			for (int k = 0; k < iTitulo3; k++) {
				titulo3 = " "+titulo3;
			}

			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+codigoArticulo+" "+cantidadDevuelta+" "+valorDeLaDevolucion);
			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+titulo1+titulo2+titulo3);

			impresionFactura.AddCabecera(titulo1+titulo2+titulo3);			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		impresionFactura.AddCabecera("----------------------------------------");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		String sValorDevolucion = FormatoNumero.formatoCero(valorDevolucion);
		impresionFactura.AddCabecera(centrado("Total devolucion "+sValorDevolucion));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

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

	public void imprimeReporte(ArrayList<DevolucionVenta> alDevolucionVenta,
			String prefijo,MaestroDB maestroDB,java.sql.Timestamp fechaInicial,java.sql.Timestamp fechaFinal) {

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
		
		String sFecha = ""+fecha;
		sFecha = sFecha.substring(0, sFecha.indexOf("."));
		
		impresionFactura.AddCabecera(centrado("Fecha "+sFecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		impresionFactura.AddCabecera(centrado("FechaInicial "+fechaInicial));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("FechaFinal "+fechaFinal));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		double valorDevolucionTotalPeriodo = 0;
		double valorDevolucion = 0;

		for (int j = 0; j < alDevolucionVenta.size(); j++) {

			int iNumeroFactura = (int) alDevolucionVenta.get(j).getNumero_factura();
			
			String facturaRemision = "";
			String guion="";
			if(alDevolucionVenta.get(j).getOrigen_devolucion().equals("remision")) {
				prefijo = "";
				facturaRemision = "Remisión Numero ";
			}else {
				facturaRemision = "Factura Numero ";
				guion = "-";
			}
			
			impresionFactura.AddCabecera(centrado(facturaRemision+prefijo+guion+iNumeroFactura));			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			ArrayList<ItemDevolucionVenta> alItemDevolucionVenta = maestroDB.traeItemDevolucio(alDevolucionVenta.get(j).getNumero());

			System.out.println("ModeloDevoluciones.imprimeReporte() alDevolucionVenta "+alDevolucionVenta.get(j).getNumero()+" alItemFacturaDevolucion "+alItemDevolucionVenta.size());

			for (int i = 0; i < alItemDevolucionVenta.size(); i++) {

				String codigoArticulo = alItemDevolucionVenta.get(i).getCodigo_articulo();
				String titulo1 = codigoArticulo;
				int iTitulo1 = 12-titulo1.length();
				for (int k = 0; k < iTitulo1; k++) {
					titulo1 = " "+titulo1;
				}

				String cantidadDevuelta = ""+alItemDevolucionVenta.get(i).getCantidad_unidad_medida();
				String titulo2 = cantidadDevuelta;
				int iTitulo2 = 8-titulo2.length();
				for (int k = 0; k < iTitulo2; k++) {
					titulo2 = " "+titulo2;
				}

				valorDevolucion = valorDevolucion+alItemDevolucionVenta.get(i).getValor();
				String valorDeLaDevolucion = FormatoNumero.formatoCero(alItemDevolucionVenta.get(i).getValor());
				String titulo3 = valorDeLaDevolucion;
				int iTitulo3 = 10-titulo3.length();
				for (int k = 0; k < iTitulo3; k++) {
					titulo3 = " "+titulo3;
				}

				impresionFactura.AddCabecera(titulo1+titulo2+titulo3);			
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			}
			valorDevolucionTotalPeriodo = valorDevolucionTotalPeriodo+alDevolucionVenta.get(j).getValor();
		}

		impresionFactura.AddCabecera("----------------------------------------");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		String sValorDevolucionTotalPeriodo = FormatoNumero.formatoCero(valorDevolucionTotalPeriodo);

		System.out.println("ModeloDevoluciones.imprimeReporte() sValorDevolucionTotalPeriodo "+sValorDevolucionTotalPeriodo);

		impresionFactura.AddCabecera(centrado("Total devolucion periodo "+sValorDevolucionTotalPeriodo));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

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

	public void imprimeFactura2(double numeroFactura, MaestroDB maestroDB, int numeroCopias) {

		this.datosImprimirFactura = datosImprimirFactura;
		this.maestroDB = maestroDB;
		this.encabezadoFactura = encabezadoFactura;
		this.numeroFactura = numeroFactura;
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
		double width = G.getInstance().anchoPapel; //2.3d * 72d; //8d * 72d;

		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() datosImprimirFactura.getItemPreFactura().size() "+ datosImprimirFactura.getItemPreFactura().size());

		int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
		ArrayList<ItemFactura> alItemFactura = maestroDB.traerItemFactura(id_caja,numeroFactura);
		System.out.println("ModeloDevoluciones.imprimeFactura2() alItemFactura.size() "+alItemFactura.size());

		double height = (alItemFactura.size()*pasoRenglon) * 72d;
		double margin = 0.01d * 72d;
		paper.setSize(width, height);

		paper.setImageableArea(	margin,	margin,	width - (margin * 2), height - (margin * 2));

		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() Before- "+ dump(paper));
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);
		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() After- " + dump(paper));
		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() After- " + dump(pf));
		dump(pf);
		PageFormat validatePage = pj.validatePage(pf);
		//System.out.println("ImpresionPrinterJob.ImpresionPrinterJob() Valid- " + dump(validatePage));

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
			g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

			double width = pageFormat.getImageableWidth();
			double height = pageFormat.getImageableHeight();

			ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
			alMensajesFactura = maestroDB.MensajesFactura(G.getInstance().licenciaGlobal.getIdAlmacen());//(datosImprimirFactura.getEncabezadoFactura().getIdAlmacen());

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

				NumeracionAutorizada numeracionAutorizada =  maestroDB.traeResolucionCopiaFactura(numeroFactura);
				String numeroResolucion = "RESOL. DIAN No "+ numeracionAutorizada.getResolucion() +" "+
						numeracionAutorizada.getFecha_expedicion();

				centrarTexto(numeroResolucion, espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				centrarTexto("HABILITADA: "+numeracionAutorizada.getPrefijo()+" del "+
						(int)numeracionAutorizada.getRango_inicial()+" al "+(int)numeracionAutorizada.getRango_final(), espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon; //				encabezadoFactura.setPrefijoNumeracionAutorizada(rs.getString(6));
				centrarTexto("FACTURA DE VENTA No.: "+numeracionAutorizada.getPrefijo()+"-"+(int)numeroFactura, espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				pagina.setFont(fontTitulo);

				Domicilio domicilio = maestroDB.traerDomicilioFacturado(numeroFactura,G.getInstance().licenciaGlobal.getIdCaja());

				if(domicilio!=null) {
					pagina.drawString("CLIENTE: "+domicilio.getNombre()+" "+domicilio.getApellido(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					pagina.drawString("DIRECCION "+domicilio.getDireccion(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					pagina.drawString("TELEFONO "+domicilio.getTelefono(), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				}		

				Factura factura = maestroDB.traeFactura(G.getInstance().licenciaGlobal.getIdCaja(), numeroFactura);

				String sFecha = factura.getFecha()+" "+factura.getHora();
				sFecha = sFecha.substring(0, 16);
				centrarTexto("FECHA: "+sFecha, espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				Cajero cajero = maestroDB.traeCajero(factura.getId_cajero());
				Usuario usuario = maestroDB.traeUsuario(cajero.getId_usuario());
				centrarTexto("CAJERO: "+usuario.getNombre()+" "+usuario.getApellido(), espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				pagina.drawString("ITEM         DESCRIPCION", 10, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				pagina.drawString("IMP    CANT    VALOR    TOTAL", 10, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				ArrayList<ItemFactura> alItemFactura = maestroDB.traerItemFactura(G.getInstance().licenciaGlobal.getIdCaja(),numeroFactura);
				ArrayList<BaseIva> alBaseIva = maestroDB.traeTablaIva();
				// lista de articulos
				String sIva = "";
				for (int i = 0; i < alItemFactura.size(); i++) {
					int idBaseIvaProducto = alItemFactura.get(i).getId_base_iva();
					for (int j = 0; j <  alBaseIva.size(); j++) {
						if(alBaseIva.get(j).getIdBaseIva() == idBaseIvaProducto) {
							sIva = alBaseIva.get(j).getNombreBaseIva();
						}
					}

					pagina.drawString(alItemFactura.get(i).getCodigo_articulo()+" "+
							maestroDB.traeNombre(alItemFactura.get(i).getId_articulo()), 12, espacioEntreRenglones);
					espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

					double cantidad = alItemFactura.get(i).getCantidad_unidad_medida(); // datosImprimirFactura.getItemPreFactura().get(i).getCantidad_unidad_medida();
					double precioUnitario = alItemFactura.get(i).getValor_unidad(); // datosImprimirFactura.getItemPreFactura().get(i).getValor_unidad();
					String sPrecioUnitario = FormatoNumero.formatoCero(precioUnitario);//  FormatoNumero.formato(precioUnitario);
					double precioParcial = alItemFactura.get(i).getValor_item(); // datosImprimirFactura.getItemPreFactura().get(i).getValor_item();
					String fPrecioParcia = FormatoNumero.formatoCero(precioParcial);
					String lineaPrecio = sIva+" - "+cantidad+" - "+sPrecioUnitario;
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

				ArrayList<IvaValor> alIvaValor = maestroDB.traeSumatoriaIvasFactura(numeroFactura, G.getInstance().licenciaGlobal.getIdCaja());

				String sNombreIva = "";
				for (int i = 0; i < alIvaValor.size(); i++) {
					int nombreIva = alIvaValor.get(i).getIdIva(); //datosImprimirFactura.getIvaValor().get(i).getIdIva();
					for (int j = 0; j < alBaseIva.size(); j++) {
						if(alBaseIva.get(j).getIdBaseIva() == nombreIva) {
							sNombreIva = alBaseIva.get(j).getNombreBaseIva(); // .getEstadoBaseIva(); // datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getEstadoBaseIva();
						}
					}
					double baseImpuesto = alIvaValor.get(i).getTotalxIva() - alIvaValor.get(i).getValorIva();
					String sBaseImpuesto = FormatoNumero.formatoCero(baseImpuesto);
					double ivas = alIvaValor.get(i).getValorIva(); // datosImprimirFactura.getIvaValor().get(i).getValorIva();
					String sIvas = FormatoNumero.formatoCero(ivas);
					double totalImpuesto = baseImpuesto = alIvaValor.get(i).getTotalxIva(); // datosImprimirFactura.getIvaValor().get(i).getTotalxIva();
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
				pagina.drawString("----------------------------------------", 12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				//impresionFactura.AddCabecera("CANTIDAD DE ARTICULOS......."+datosImprimirFactura.getPreFactura().get);

				double valorFactura = factura.getValor_factura(); // datosImprimirFactura.getPreFactura().getValor_prefactura();
				String sValorFactura = FormatoNumero.formatoCero(valorFactura);

				pagina.drawString("VALOR FACTURA........."+sValorFactura, 12, espacioEntreRenglones);
				//espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				ArrayList<MedioPagoFactura> alMedioPagoFactura = maestroDB.traerMedioPagoFacturaFacturado(numeroFactura, G.getInstance().licenciaGlobal.getIdCaja());

				for (int i = 0; i < alMedioPagoFactura.size();i++) { // datosImprimirFactura.getMedioPagoPreFactura().size(); i++) {
					int medioPago = alMedioPagoFactura.get(i).getId_medio_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getId_medio_pago();	
					switch (medioPago) {
					case 1:
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double efectivo = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sEfectivo = FormatoNumero.formatoCero(efectivo);
						pagina.drawString("EFECTIVO.............."+sEfectivo,12,espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double recibidoEfectivo = alMedioPagoFactura.get(i).getEfectivo_recibido(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
						String sRecibidoEfectivo = FormatoNumero.formatoCero(recibidoEfectivo);
						pagina.drawString("RECIBIDO.............."+sRecibidoEfectivo,12,espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double cambioEfectivo = alMedioPagoFactura.get(i).getEfectivo_recibido()-alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sCambioEfectivo = FormatoNumero.formatoCero(cambioEfectivo);
						pagina.drawString("CAMBIO................"+sCambioEfectivo,12,espacioEntreRenglones);
						//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						break;
					case 2:
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double recibido = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sRecibido = FormatoNumero.formatoCero(recibido);
						pagina.drawString("TARJETA CREDITO......."+sRecibido,12,espacioEntreRenglones);
						//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						break;
					case 3:
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double recibidoDebito = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sRecibidoDebito = FormatoNumero.formatoCero(recibidoDebito);
						pagina.drawString("TARJETA DEBITO........"+sRecibidoDebito,12,espacioEntreRenglones);
						//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						break;
					case 7:// CREDITO
						// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo qie se crea
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double recibidoCredito = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
						String sRecibidoCredito = FormatoNumero.formatoCero(recibidoCredito);
						pagina.drawString("CREDITO........"+sRecibidoCredito,12,espacioEntreRenglones);
						break;
					case 8: // REMISION
						break;
					case 9:
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						double efectivoExtranjera = alMedioPagoFactura.get(i).getValor_pago();
						String sEfectivoExtranjera = FormatoNumero.formatoCero(efectivoExtranjera);
						pagina.drawString("EFECTIVO EXTRANJERA.............."+sEfectivoExtranjera, 12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						
						double recibidoEfectivoExtranjera = alMedioPagoFactura.get(i).getEfectivo_recibido();
						String sRecibidoEfectivoExtranjera = FormatoNumero.formatoCero(recibidoEfectivoExtranjera);
						pagina.drawString("RECIBIDO EXTRANJERA.............."+sRecibidoEfectivoExtranjera, 12, espacioEntreRenglones);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						
						double cambioEfectivoExtrajera = alMedioPagoFactura.get(i).getEfectivo_recibido()-alMedioPagoFactura.get(i).getValor_pago();
						String sCambioEfectivoExtrajera = FormatoNumero.formatoCero(cambioEfectivoExtrajera);
						pagina.drawString("CAMBIO EXTRAJERA................"+sCambioEfectivoExtrajera, 12, espacioEntreRenglones);
						//espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
						
						break;
					default:
						break;
					}
				}

				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
				pagina.drawString("----------------------------------------",  12, espacioEntreRenglones);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;

				centrarTexto("COPIA "+numeroCopias, espacioEntreRenglones, fontTitulo, pagina);
				espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
								
				// Mensajes factura
				for (int i = 0; i < alMensajesFactura.size(); i++) {
					if(alMensajesFactura.get(i).getUbicacion().equals("piedepagina")) {
						centrarTexto(alMensajesFactura.get(i).getMensaje(), espacioEntreRenglones, fontTitulo, pagina);
						espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
					}
				}

				pagina.dispose();

			} catch (Exception e) {
				System.out.println("ModeloDevoluciones.Page.print() ERROR DE IMPRESION "+e);
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
		if(intXText<0) {
			strTexto = "TEXTO MUY LARGO "+strTexto;
			intXText = 10;
		}
		g.drawString(strTexto, intXText, intY);
	}

	private static int tamanoTexto (String strTexto, Font fontText, Graphics g) {
		FontMetrics fmText = g.getFontMetrics(fontText);		
		g.setFont(fontText);
		return fmText.stringWidth(strTexto);
	}
	
	
	public void imprimeFactura1(double numeroFactura, MaestroDB maestroDB, int numeroCopias) {
		
	}

	public void imprimeFactura(double numeroFactura, MaestroDB maestroDB, int numeroCopias) {
		System.out.println("ModeloDevoluciones.imprimeFactura() numeroFactura "+numeroFactura);

		ImpresionFactura impresionFactura = new ImpresionFactura();

		Empresa empresa = maestroDB.traeEmpresa();
		impresionFactura.AddCabecera(centrado(empresa.getNombre()+" NIT "+ empresa.getNit()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		ArrayList<Regimen> alRegimen = maestroDB.regimen();
		String regimenEmpresa = "";
		for (int i = 0; i < alRegimen.size(); i++) {
			if(alRegimen.get(i).getId()==empresa.getId_regimen()) {
				regimenEmpresa = alRegimen.get(i).getNombre();
			}
		}
		impresionFactura.AddCabecera(centrado("SOMOS REGIMEN "+regimenEmpresa));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
		NumeracionAutorizada numeracionAutorizada = maestroDB.traeNumeracionAutorizada(id_caja);
		if(empresa.getId_regimen()==1) {
			String numeroResolucion = "Nro.Aut: "+numeracionAutorizada.getResolucion() +" "+
					numeracionAutorizada.getFecha_expedicion();

			System.out.println("ModeloDevoluciones.imprimeFactura() numeroResolucion "+numeroResolucion);
			impresionFactura.AddCabecera(centrado(numeroResolucion));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			impresionFactura.AddCabecera(centrado( "Pref: "+numeracionAutorizada.getPrefijo()+" del "+
					(int)numeracionAutorizada.getRango_inicial()+" al "+ (int)numeracionAutorizada.getRango_final()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
		}
		
		Factura factura = maestroDB.traeFactura(id_caja, numeroFactura);
		impresionFactura.AddCabecera(centrado("FACTURA DE VENTA No.: "+numeracionAutorizada.getPrefijo()+"-"+ (int)numeroFactura));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		// domicilio imprime una facturas por ser copia y registra el número de copia

		Domicilio domicilio = maestroDB.traerDomicilioFacturado(numeroFactura,id_caja);

		int imprimeDos = 0;
		if( domicilio!=null) {
			//imprimeDos = 2; como es una copia no imprime 2 facturas pero registra la nueva copia
			impresionFactura.AddCabecera("CLIENTE: "+domicilio.getNombre()+" "+domicilio.getApellido());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("DIRECCION "+domicilio.getDireccion());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("TELEFONO "+domicilio.getTelefono());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		
		try {
			impresionFactura.AddCabecera(centrado("FECHA: "+factura.getFecha()+" "+factura.getHora()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		} catch (Exception e) {
			System.err.println("ModeloDevoluciones.imprimeFactura() ERROR "+e);
			JOptionPane.showMessageDialog(null, "Debe llamar primero la factura antes de imprimirla.\n Oprima Intro.", "Alerta",imprimeDos, null);
		}		

		Cajero cajero = maestroDB.traeCajero(factura.getId_cajero());
		Usuario usuario = maestroDB.traeUsuario(cajero.getId_usuario());
		//impresionFactura.AddCabecera(centrado("CAJERO: "+ usuario.getNombre()+" "+usuario.getApellido()));
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("ITEM             DESCRIPCION");
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera("IMP    CANT          VALOR         TOTAL");
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		ArrayList<ItemFactura> alItemFactura = maestroDB.traerItemFactura(id_caja,numeroFactura);
		ArrayList<BaseIva> alBaseIva = maestroDB.traeTablaIva();
		// lista de articulos
		String sIva = "";
		for (int i = 0; i < alItemFactura.size(); i++) {
			int idBaseIvaProducto = alItemFactura.get(i).getId_base_iva(); // datosImprimirFactura.getItemPreFactura().get(i).getId_base_iva();
			for (int j = 0; j < alBaseIva.size(); j++) {
				if(alBaseIva.get(j).getIdBaseIva()==idBaseIvaProducto) {
					sIva = alBaseIva.get(j).getNombreBaseIva();// .getEstadoBaseIva();
				}
			}

			Articulo articulo = maestroDB.traeArticulo(alItemFactura.get(i).getId_articulo());
			impresionFactura.AddCabecera(alItemFactura.get(i).getCodigo_articulo()+" "+articulo.getNombre());
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			double cantidad = alItemFactura.get(i).getCantidad_unidad_medida();
			double precioUnitario = alItemFactura.get(i).getValor_unidad();
			String sPrecioUnitario = FormatoNumero.formatoCero(precioUnitario);//  FormatoNumero.formato(precioUnitario);
			double precioParcial = alItemFactura.get(i).getValor_item();
			String fPrecioParcia = FormatoNumero.formatoCero(precioParcial);
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

		if(empresa.getId_regimen()==1) {
			// Resumen de impuestos
			//impresionFactura.AddCabecera("========================================");
			//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera("IMP%         BASE      V.IMP       TOTAL");
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
	
			ArrayList<IvaValor> alIvaValor = maestroDB.traeSumatoriaIvasFactura(numeroFactura, id_caja);
	
			String sNombreIva = "";
			for (int i = 0; i < alIvaValor.size(); i++) {
				int nombreIva = alIvaValor.get(i).getIdIva(); // datosImprimirFactura.getIvaValor().get(i).getIdIva();
				for (int j = 0; j < alBaseIva.size(); j++) { // datosImprimirFactura.getEncabezadoFactura().getBaseIva().size(); j++) {
					if(alBaseIva.get(j).getIdBaseIva()==nombreIva) { //datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getIdBaseIva()==nombreIva) {
						sNombreIva = alBaseIva.get(j).getNombreBaseIva(); // datosImprimirFactura.getEncabezadoFactura().getBaseIva().get(j).getEstadoBaseIva();
	
						//System.out.println("ModeloDevoluciones.imprimeFactura() nombreIva "+nombreIva+" sNombreIva "+sNombreIva);
	
					}
				}
				double baseImpuesto = alIvaValor.get(i).getTotalxIva() - alIvaValor.get(i).getValorIva(); // datosImprimirFactura.getIvaValor().get(i).getTotalxIva() - datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sBaseImpuesto = FormatoNumero.formatoCero(baseImpuesto);
				double ivas = alIvaValor.get(i).getValorIva(); // datosImprimirFactura.getIvaValor().get(i).getValorIva();
				String sIvas = FormatoNumero.formatoCero(ivas);
				double totalImpuesto = baseImpuesto = alIvaValor.get(i).getTotalxIva(); // datosImprimirFactura.getIvaValor().get(i).getTotalxIva();
				String sTotalImpuesto = FormatoNumero.formatoCero(totalImpuesto);
	
				String lineaImpuestos = ""+sNombreIva+"    "+sBaseImpuesto+"      "+sIvas;
				String finalLinea = " "+sTotalImpuesto;
	
				int lineaImpu = 40-(lineaImpuestos.length()+finalLinea.length());
				for (int j = 0; j < lineaImpu; j++) {
					lineaImpuestos = lineaImpuestos+" ";
				}
				lineaImpuestos = lineaImpuestos + finalLinea;
	
				//System.out.println("ModeloDevoluciones.imprimeFactura() lineaImpuestos "+lineaImpuestos);
	
				impresionFactura.AddCabecera(lineaImpuestos);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}
		}
		// resumen de la forma de pago
		//impresionFactura.AddCabecera("----------------------------------------");
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera("CANTIDAD DE ARTICULOS......."+datosImprimirFactura.getPreFactura().get);

		double valorFactura = factura.getValor_factura(); // datosImprimirFactura.getPreFactura().getValor_prefactura();
		String sValorFactura = FormatoNumero.formatoCero(valorFactura);
		impresionFactura.AddCabecera("VALOR FACTURA........."+sValorFactura);

		ArrayList<MedioPagoFactura> alMedioPagoFactura = maestroDB.traerMedioPagoFacturaFacturado(numeroFactura, id_caja);

		for (int i = 0; i < alMedioPagoFactura.size();i++) { // datosImprimirFactura.getMedioPagoPreFactura().size(); i++) {
			int medioPago = alMedioPagoFactura.get(i).getId_medio_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getId_medio_pago();	
			switch (medioPago) {
			case 1:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double efectivo = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sEfectivo = FormatoNumero.formatoCero(efectivo);
				impresionFactura.AddCabecera("EFECTIVO.............."+sEfectivo);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoEfectivo = alMedioPagoFactura.get(i).getEfectivo_recibido(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido();
				String sRecibidoEfectivo = FormatoNumero.formatoCero(recibidoEfectivo);
				impresionFactura.AddCabecera("RECIBIDO.............."+sRecibidoEfectivo);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double cambioEfectivo = alMedioPagoFactura.get(i).getEfectivo_recibido()-alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getEfectivo_recibido()-datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sCambioEfectivo = FormatoNumero.formatoCero(cambioEfectivo);
				impresionFactura.AddCabecera("CAMBIO................"+sCambioEfectivo);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 2:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibido = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibido = FormatoNumero.formatoCero(recibido);
				impresionFactura.AddCabecera("TARJETA CREDITO......."+sRecibido);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 3:
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoDebito = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibidoDebito = FormatoNumero.formatoCero(recibidoDebito);
				impresionFactura.AddCabecera("TARJETA DEBITO........"+sRecibidoDebito);
				//impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				break;
			case 7:// CREDITO
				// TODO cambiar para que sea automatico el tipo de pago de acuerdo a lo qie se crea
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double recibidoCredito = alMedioPagoFactura.get(i).getValor_pago(); // datosImprimirFactura.getMedioPagoPreFactura().get(i).getValor_pago();
				String sRecibidoCredito = FormatoNumero.formatoCero(recibidoCredito);
				impresionFactura.AddCabecera("CREDITO........"+sRecibidoCredito);
				break;
			case 8: // REMISION
				break;
			case 9: // MONEDA EXTRANJERA
				
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				double efectivoExtranjera = alMedioPagoFactura.get(i).getValor_pago();
				String sEfectivoExtranjera = FormatoNumero.formatoCero(efectivoExtranjera);
				impresionFactura.AddCabecera("EFECTIVO EXTRANJERA.............."+sEfectivoExtranjera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				double recibidoEfectivoExtranjera = alMedioPagoFactura.get(i).getEfectivo_recibido();
				String sRecibidoEfectivoExtranjera = FormatoNumero.formatoCero(recibidoEfectivoExtranjera);
				impresionFactura.AddCabecera("RECIBIDO EXTRANJERA.............."+sRecibidoEfectivoExtranjera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				double cambioEfectivoExtrajera = alMedioPagoFactura.get(i).getEfectivo_recibido()-alMedioPagoFactura.get(i).getValor_pago();
				String sCambioEfectivoExtrajera = FormatoNumero.formatoCero(cambioEfectivoExtrajera);
				impresionFactura.AddCabecera("CAMBIO EXTRAJERA................"+sCambioEfectivoExtrajera);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				
				
				break;
				
			default:
				break;
			}
		}
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//impresionFactura.AddCabecera("----------------------------------------");
		//impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		impresionFactura.AddCabecera(centrado("COPIA "+numeroCopias));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		//centrarTexto("COPIA "+numeroCopias, espacioEntreRenglones, fontTitulo, pagina);
		//espacioEntreRenglones = espacioEntreRenglones+pasoRenglon;
		
		
		
		
		
		// Mensajes factura
		ArrayList<MensajeDocumento> alMensajesFactura = new ArrayList<MensajeDocumento>();
		alMensajesFactura = maestroDB.MensajesFactura(factura.getId_almacen());
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
			impresionFactura.AddCabecera(centrado("COPIA SIN VALOR"));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.ImprimirDocumento(true);

		}else {
			impresionFactura.ImprimirDocumento(true);
		}
		imprimeDos=0;
	}

	public void imprimeDevolucionRemision(ArrayList<ItemRemision> alItemRemisionDevolucion,
			ArrayList<DevolucionVenta> alDevolucionVenta, String prefijo, double numero_factura, MaestroDB maestroDB2,
			Date fechaRemision) {
		System.out.println("ModeloDevoluciones.imprimeDevolucionRemision() EN ESTE MOMENTO DEBERIA ESTAR IMPRIMIENDO");
		
		ImpresionFactura impresionFactura = new ImpresionFactura();
		Empresa empresa = maestroDB2.traeEmpresa();
		impresionFactura.AddCabecera(centrado(empresa.getNombre()+" NIT "+empresa.getNit()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
		Almacen almacen = maestroDB2.almacen(id_almacen);
		int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
		Caja caja = maestroDB2.caja(id_Caja);
		impresionFactura.AddCabecera(centrado("Almacen "+almacen.getNombre()+" Caja "+caja.getNombre()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		impresionFactura.AddCabecera(centrado("Cajero "+G.getInstance().cajero));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		Timestamp date =  maestroDB2.traeFechayHoraDevolucionRemision();
		String sFecha = ""+date;
		sFecha = sFecha.substring(0, sFecha.indexOf("."));

		impresionFactura.AddCabecera(centrado("Fecha "+sFecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		int tamanoAlDevolucionVenta = alDevolucionVenta.size();
		impresionFactura.AddCabecera(centrado("Consecutivo: "+alDevolucionVenta.get(tamanoAlDevolucionVenta-1).getNumero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		int iNumero_factura = (int) numero_factura;
		impresionFactura.AddCabecera(centrado("Remisión Número "+iNumero_factura));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		System.out.println("ModeloDevoluciones.imprimeDevolucionRemision() IdCaja "+G.getInstance().licenciaGlobal.getIdCaja()+" numero_factura "+ numero_factura);
		
		Remision remision = maestroDB2.traeRemision(G.getInstance().licenciaGlobal.getIdCaja(), numero_factura);
		java.sql.Timestamp fechaRemision2 = maestroDB2.traeFechayHoraRemision(remision.getNumero_remision());
		String sFechaRemision2 = ""+fechaRemision2;
		sFechaRemision2 = sFechaRemision2.substring(0, sFechaRemision2.indexOf("."));
		
		impresionFactura.AddCabecera(centrado("Fecha remisión "+sFechaRemision2));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		impresionFactura.AddCabecera("   CODIGO   CANTIDAD     PRECIO");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		double valorDevolucion = 0;
		for (int i = 0; i < alItemRemisionDevolucion.size(); i++) {

			String codigoArticulo = alItemRemisionDevolucion.get(i).getCodigo_articulo();
			String titulo1 = codigoArticulo;
			int iTitulo1 = 12-titulo1.length();
			for (int k = 0; k < iTitulo1; k++) {
				titulo1 = " "+titulo1;
			}

			String cantidadDevuelta = ""+alItemRemisionDevolucion.get(i).getCantidad_unidad_medida();
			String titulo2 = cantidadDevuelta;
			int iTitulo2 = 8-titulo2.length();
			for (int k = 0; k < iTitulo2; k++) {
				titulo2 = " "+titulo2;
			}

			valorDevolucion = valorDevolucion+alItemRemisionDevolucion.get(i).getValor_item();
			String valorDeLaDevolucion = FormatoNumero.formatoCero(alItemRemisionDevolucion.get(i).getValor_item());
			String titulo3 = valorDeLaDevolucion;
			int iTitulo3 = 10-titulo3.length();
			for (int k = 0; k < iTitulo3; k++) {
				titulo3 = " "+titulo3;
			}

			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+codigoArticulo+" "+cantidadDevuelta+" "+valorDeLaDevolucion);
			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+titulo1+titulo2+titulo3);

			impresionFactura.AddCabecera(titulo1+titulo2+titulo3);			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		impresionFactura.AddCabecera("----------------------------------------");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		String sValorDevolucion = FormatoNumero.formatoCero(valorDevolucion);
		impresionFactura.AddCabecera(centrado("Total devolucion "+sValorDevolucion));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

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

	public void imprimeCopiaDevolucionFacturaRemision(String origenRemisionFactura,
			int numeroDevolucionFacturaRemision, MaestroDB maestroDB) {
		System.out.println("ModeloDevoluciones.imprimeCopiaDevolucionFacturaRemision() EN ESTE MOMENTO DEBERIA ESTAR IMPRIMIENDO LA COPIA DE LA DEVOLUCION");
		
		ArrayList<DevolucionVenta> alDevolucionVenta = maestroDB.traeDevolucionParaCopia(numeroDevolucionFacturaRemision);
		
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
		
		int iCajero = Integer.valueOf(alDevolucionVenta.get(0).getId_usuario());
		Cajero cajero = maestroDB.traeCajero(iCajero);
		
		Usuario usuario = maestroDB.traeUsuario(cajero.getId_usuario());
		impresionFactura.AddCabecera(centrado("Cajero "+usuario.getNombre()+" "+usuario.getApellido()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		String sFecha = ""+alDevolucionVenta.get(0).getFecha();
		sFecha = sFecha.substring(0, sFecha.indexOf("."));
		impresionFactura.AddCabecera(centrado("Fecha "+sFecha));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		impresionFactura.AddCabecera(centrado("Consecutivo: "+alDevolucionVenta.get(0).getNumero()));
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		
		int iNumero_factura = (int) alDevolucionVenta.get(0).getNumero_factura();
		
		if(origenRemisionFactura.equals("R")) {
			impresionFactura.AddCabecera(centrado("Remisión Número "+iNumero_factura));			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
			Remision remision = maestroDB.traeRemision(G.getInstance().licenciaGlobal.getIdCaja(), alDevolucionVenta.get(0).getNumero_factura());
			java.sql.Timestamp fechaRemision = maestroDB.traeFechayHoraRemision(remision.getNumero_remision());
			String sFechaRemision = ""+fechaRemision;
			sFechaRemision = sFechaRemision.substring(0, sFechaRemision.indexOf("."));
			
			impresionFactura.AddCabecera(centrado("Fecha remisión "+ sFechaRemision));			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}else {
			NumeracionAutorizada nuemracionAutorizada = maestroDB.traeResolucionCopiaFactura(alDevolucionVenta.get(0).getNumero_factura());
			impresionFactura.AddCabecera(centrado("Factura N\u00FAmero "+nuemracionAutorizada.getPrefijo()+"-"+iNumero_factura));			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			
			Factura factura = maestroDB.traeFactura(G.getInstance().licenciaGlobal.getIdCaja(), alDevolucionVenta.get(0).getNumero_factura());
			
			impresionFactura.AddCabecera(centrado("Fecha factura "+factura.getFecha()+" "+factura.getHora()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}

		impresionFactura.AddCabecera("   CODIGO   CANTIDAD     PRECIO");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

		ArrayList<ItemDevolucionVenta> alItemRemisionDevolucion = maestroDB.traeItemDevolucio(alDevolucionVenta.get(0).getNumero());
		
		double valorDevolucion = 0;
		for (int i = 0; i < alItemRemisionDevolucion.size(); i++) {

			String codigoArticulo = alItemRemisionDevolucion.get(i).getCodigo_articulo();
			String titulo1 = codigoArticulo;
			int iTitulo1 = 12-titulo1.length();
			for (int k = 0; k < iTitulo1; k++) {
				titulo1 = " "+titulo1;
			}

			String cantidadDevuelta = ""+alItemRemisionDevolucion.get(i).getCantidad_unidad_medida();
			String titulo2 = cantidadDevuelta;
			int iTitulo2 = 8-titulo2.length();
			for (int k = 0; k < iTitulo2; k++) {
				titulo2 = " "+titulo2;
			}

			valorDevolucion = valorDevolucion+alItemRemisionDevolucion.get(i).getValor();
			String valorDeLaDevolucion = FormatoNumero.formatoCero(alItemRemisionDevolucion.get(i).getValor());
			String titulo3 = valorDeLaDevolucion;
			int iTitulo3 = 10-titulo3.length();
			for (int k = 0; k < iTitulo3; k++) {
				titulo3 = " "+titulo3;
			}

			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+codigoArticulo+" "+cantidadDevuelta+" "+valorDeLaDevolucion);
			System.out.println("ModeloDevoluciones.imprimeDevolucion() "+titulo1+titulo2+titulo3);

			impresionFactura.AddCabecera(titulo1+titulo2+titulo3);			
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		}
		impresionFactura.AddCabecera("----------------------------------------");			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());
		String sValorDevolucion = FormatoNumero.formatoCero(valorDevolucion);
		impresionFactura.AddCabecera(centrado("Total devolucion "+sValorDevolucion));			
		impresionFactura.AddCabecera(impresionFactura.DarEspacio());

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
	
	private String centrado(String sTitulo) {
		int titulo = (44 - sTitulo.length())/2;
		String sTituloEspacio = "";
		for (int i = 0; i < titulo; i++) {
			sTituloEspacio = sTituloEspacio+" ";
		}
		sTituloEspacio = sTituloEspacio +sTitulo;
		return sTituloEspacio;
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

	public ConDevoluciones getConDevoluciones() {
		return conDevoluciones;
	}

	public void setConDevoluciones(ConDevoluciones conDevoluciones) {
		this.conDevoluciones = conDevoluciones;
	}

}
