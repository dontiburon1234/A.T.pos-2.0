package controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.Almacen;
import clases.BaseIva;
import clases.Caja;
import clases.DevolucionVenta;
import clases.DevolucionesBaseIva;
import clases.Empresa;
import clases.Factura;
import clases.IvaValor;
import clases.MedioPago;
import clases.MedioPagoFactura;
import clases.Remision;
import clases.Resumen;
import database.MaestroDB;
import gui.IntReportes;
import impresiontouch.ImpresionFactura;
import main.Principal;
import modelo.ModeloDevoluciones;
import modelo.ModeloReportes;
import util.FormatoNumero;
import util.G;

public class ConReportes {

	private IntReportes intReportes;
	private ModeloReportes modeloReportes;
	private ModeloDevoluciones modeloDevoluciones;
	// private Principal principal;
	private MaestroDB maestroDB;
	private String inputDia = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private java.sql.Timestamp fecha = java.sql.Timestamp.valueOf(inputDia);
	private String reporte = "";
	private int id_cajero;
	private String serialEquipo;

	private String annoDesde;
	private String mesDesde;
	private String diaDesde;
	private String horaDesde;
	private String minDesde;
	private String annoHasta;
	private String mesHasta;
	private String diaHasta;
	private String horaHasta;
	private String minHasta;

	public ConReportes(IntReportes intReportes, ModeloReportes modeloReportes, Principal principal,
			MaestroDB maestroDB) {
		
		super();
		this.intReportes = intReportes;
		this.modeloReportes = modeloReportes;
		// this.principal = principal;
		this.maestroDB = maestroDB;

		
		
		id_cajero = G.getInstance().getIdCajero;
		/*
		 * try { serialEquipo = getSerialNumber(); } catch (IOException |
		 * InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		serialEquipo = SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber();

		intReportes.getRdbtnReporteParcialX().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reporte = "X";
				actualizaListReportes(reporte);
			}
		});

		intReportes.getRdbtnReporteFinalZ().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reporte = "Z";
				actualizaListReportes(reporte);
			}
		});

		intReportes.getBtnImprimir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				imprimeListReportes(reporte);
			}
		});

		/*
		 * intReportes.getBtnResumen().addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { //
		 * actualizaListResumen(); imprimeListResumen(); } });
		 */
		
		periodoReportes();
		actualizaListResumen();
		// actualizaListReportes(reporte);
	}

	private void actualizaListReportes(String reporte) {
		try {
			String fechaDedes = annoDesde + "-" + mesDesde + "-" + diaDesde + " " + horaDesde + ":" + minDesde
					+ ":00.000";
			java.sql.Timestamp fechaInicial = java.sql.Timestamp.valueOf(fechaDedes);

			String fechaHasta = annoHasta + "-" + mesHasta + "-" + diaHasta + " " + horaHasta + ":" + minHasta
					+ ":00.000";
			java.sql.Timestamp fechaFinal = java.sql.Timestamp.valueOf(fechaHasta);

			if (reporte.equals("")) { // no se ha seleccionado reporte
				System.out.println("ConReportes.actualizaListReportes() no se ha seleccionado reporte reporte " + reporte);
				intReportes.getTaListadoReporte().setText("");

			} else {
				System.out.println("ConReportes.actualizaListReportes() reporte " + reporte);
				intReportes.getTaListadoReporte().setText("");
				intReportes.getTaListadoReporte().append("\n");
				Empresa empresa = maestroDB.traeEmpresa();
				intReportes.getTaListadoReporte().append(centrado(empresa.getNombre() + " NIT " + empresa.getNit()));
				intReportes.getTaListadoReporte().append("\n");
				intReportes.getTaListadoReporte().append(centrado("Serial Equipo " + serialEquipo));
				intReportes.getTaListadoReporte().append("\n");

				String sFecha = "" + fecha;
				sFecha = sFecha.substring(0, sFecha.indexOf("."));
				intReportes.getTaListadoReporte().append(centrado("Fecha: " + sFecha));
				intReportes.getTaListadoReporte().append("\n");

				int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
				Almacen almacen = maestroDB.almacen(id_almacen);
				int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
				Caja caja = maestroDB.caja(id_Caja);
				intReportes.getTaListadoReporte()
						.append(centrado("Almacen " + almacen.getNombre() + " Caja " + caja.getNombre()));
				intReportes.getTaListadoReporte().append("\n");
				intReportes.getTaListadoReporte().append(centrado("Cajero " + G.getInstance().cajero));
				intReportes.getTaListadoReporte().append("\n");
				intReportes.getTaListadoReporte().append(centrado("Reporte " + reporte));
				intReportes.getTaListadoReporte().append("\n");

				ArrayList<Factura> alFactura = maestroDB.traerFacturaSegunRangoFechas(id_almacen, id_Caja, id_cajero,
						fechaInicial, fechaFinal);
				if (alFactura.size() != 0) {
					String prefijo = maestroDB.traePrefijo(id_Caja);
					int numeroFacturaInicial = (int) alFactura.get(0).getNumero();
					int numeroFacturaFinal = (int) alFactura.get(alFactura.size() - 1).getNumero();
					String sFechaInicial = "" + fechaInicial;
					String sFechaFinal = "" + fechaFinal;
					sFechaInicial = sFechaInicial.substring(0, 16);
					sFechaFinal = sFechaFinal.substring(0, 16);

					intReportes.getTaListadoReporte().append(centrado("Factura                Fecha"));
					intReportes.getTaListadoReporte().append("\n");
					intReportes.getTaListadoReporte().append(centrado("Inicial " + prefijo + "-" + numeroFacturaInicial
							+ "   " + alFactura.get(0).getFecha() + " " + alFactura.get(0).getHora())); // sFechaInicial));
					intReportes.getTaListadoReporte().append("\n");
					intReportes.getTaListadoReporte()
							.append(centrado("  Final " + prefijo + "-" + numeroFacturaFinal + "   "
									+ alFactura.get(alFactura.size() - 1).getFecha() + " "
									+ alFactura.get(alFactura.size() - 1).getHora())); // sFechaFinal));
					intReportes.getTaListadoReporte().append("\n");

					ArrayList<IvaValor> alIvaValor = maestroDB.traeTotalIvaPeriodo(numeroFacturaInicial,
							numeroFacturaFinal, id_Caja);
					ArrayList<BaseIva> alBaseIva = maestroDB.traeTablaIva();
					
					if(empresa.getId_regimen()==1) {
						intReportes.getTaListadoReporte().append("\n");
						intReportes.getTaListadoReporte().append(centrado("Detalle IVA"));
						intReportes.getTaListadoReporte().append("\n");
						intReportes.getTaListadoReporte()
								.append(centrado("   Tasa                Impuesto        Facturado"));
						intReportes.getTaListadoReporte().append("\n");
						for (int i = 0; i < alIvaValor.size(); i++) {
							for (int j = 0; j < alBaseIva.size(); j++) {
								if (alIvaValor.get(i).getIdIva() == alBaseIva.get(j).getIdBaseIva()) {
									String sTotalxIva = FormatoNumero.formatoCero(alIvaValor.get(i).getTotalxIva());
									String sValorIva = FormatoNumero.formatoCero(alIvaValor.get(i).getValorIva());
									int iEspacio2 = 20 - sValorIva.length();
									String espacio2 = "";
									for (int k = 0; k < iEspacio2; k++) {
										espacio2 = espacio2 + ".";
									}
									int iEspacio1 = 20 - sTotalxIva.length();
									String espacio1 = "";
									for (int k = 0; k < iEspacio1; k++) {
										espacio1 = espacio1 + ".";
									}
									String titulo1 = alBaseIva.get(j).getNombreBaseIva();
									int iTitulo1 = 10 - titulo1.length();
									for (int k = 0; k < iTitulo1; k++) {
										titulo1 = " " + titulo1;
									}
									intReportes.getTaListadoReporte()
											.append(titulo1 + espacio1 + sTotalxIva + espacio2 + sValorIva);
									intReportes.getTaListadoReporte().append("\n");
								}
							}
						}
					}
					
					intReportes.getTaListadoReporte().append("\n");
					intReportes.getTaListadoReporte().append(
							centrado("Total de transacciones " + (numeroFacturaFinal - numeroFacturaInicial + 1)));

					double totalVenta = 0;
					for (int i = 0; i < alFactura.size(); i++) {
						// System.out.println("ConReportes.actualizaListReportes() alFactura i "+i+"
						// numero factura "+alFactura.get(i).getNumero());
						totalVenta = totalVenta + alFactura.get(i).getValor_factura();
					}
					String sTotalVenta = FormatoNumero.formatoCero(totalVenta);
					intReportes.getTaListadoReporte().append("\n");
					intReportes.getTaListadoReporte().append(centrado("Total Venta          $" + sTotalVenta));
					intReportes.getTaListadoReporte().append("\n");

					ArrayList<DevolucionVenta> alDevolucionVenta = maestroDB.traeDevolucionesPeriodo(
							G.getInstance().licenciaGlobal.getIdCaja(), fechaInicial, fechaFinal);
					/*
					 * String sGsonAlDevolucionVenta = ""; Gson gson = new Gson();
					 * sGsonAlDevolucionVenta = gson.toJson(alDevolucionVenta); System.out.
					 * println("ConReportes.actualizaListReportes() sGsonAlDevolucionVenta GSON "
					 * +sGsonAlDevolucionVenta);
					 */

					double dTotalDevolucion = 0;
					ArrayList<DevolucionesBaseIva> alDevolucionesBaseIva = maestroDB.traeDevolucionesBaseIva(id_Caja,
							fechaInicial, fechaFinal);
					if (alDevolucionesBaseIva.size() != 0) {
						intReportes.getTaListadoReporte().append("\n");
						intReportes.getTaListadoReporte()
								.append(centrado("Devolución ventas remisión " + alDevolucionVenta.size()));
						intReportes.getTaListadoReporte().append("\n");
					}

					for (int i = 0; i < alDevolucionesBaseIva.size(); i++) {
						for (int j = 0; j < alBaseIva.size(); j++) {
							if (alBaseIva.get(j).getIdBaseIva() == alDevolucionesBaseIva.get(i).getId_base_iva()) {
								String devolucionIva = FormatoNumero.formatoCero(alDevolucionesBaseIva.get(i).getIva());
								String devolucionTotal = FormatoNumero
										.formatoCero(alDevolucionesBaseIva.get(i).getTotal());

								int iEspacio2 = 20 - devolucionIva.length();
								String espacio2 = "";
								for (int k = 0; k < iEspacio2; k++) {
									espacio2 = espacio2 + ".";
								}

								int iEspacio3 = 20 - devolucionTotal.length();
								String espacio3 = "";
								for (int k = 0; k < iEspacio3; k++) {
									espacio3 = espacio3 + ".";
								}

								dTotalDevolucion = dTotalDevolucion + alDevolucionesBaseIva.get(i).getTotal();
								intReportes.getTaListadoReporte().append(centrado(alBaseIva.get(j).getNombreBaseIva()
										+ espacio2 + devolucionIva + espacio3 + devolucionTotal));
								intReportes.getTaListadoReporte().append("\n");
							}
						}
					}
					if (alDevolucionesBaseIva.size() != 0) {
						String sTotalDevolucion = FormatoNumero.formatoCero(dTotalDevolucion);
						intReportes.getTaListadoReporte()
								.append(centrado("Total Devolución          $" + sTotalDevolucion));
						intReportes.getTaListadoReporte().append("\n");
					}

					intReportes.getTaListadoReporte().append("\n");
					intReportes.getTaListadoReporte().append(centrado("Formas de pago"));
					intReportes.getTaListadoReporte().append("\n");

					ArrayList<MedioPago> alMedioPago = new ArrayList<MedioPago>();
					alMedioPago = maestroDB.alMedioPago();

					ArrayList<MedioPagoFactura> alMedioPagoFactura = new ArrayList<MedioPagoFactura>();
					alMedioPagoFactura = maestroDB.traerFormasDePagoPeriodo(numeroFacturaInicial, numeroFacturaFinal,
							id_Caja);

					double totalPagado = 0;
					double medioPagoEfectivo = 0;
					double pesosDevueltosExtranjera = 0;
					int cantidadTransada = 0;
					for (int k = 0; k < alMedioPago.size(); k++) {
						totalPagado = 0;
						cantidadTransada = 0;
						
						double pagadoEnDolares = 0.0;
						double pagadoEnEuros = 0.0;
						
						for (int i = 0; i < alMedioPagoFactura.size(); i++) {
							if (alMedioPagoFactura.get(i).getId_medio_pago() == alMedioPago.get(k).getId()) {
								totalPagado = totalPagado + alMedioPagoFactura.get(i).getValor_pago();
								cantidadTransada++;
								
								if(alMedioPagoFactura.get(i).getId_entidad_bancaria()==8) {
									pagadoEnDolares = pagadoEnDolares+ alMedioPagoFactura.get(i).getEfectivo_recibido() / alMedioPagoFactura.get(i).getPorcentaje_comision();
									//System.out.println("ConReportes.actualizaListReportes() X pagadoEnDolares "+pagadoEnDolares+" totalPagado "+totalPagado+" cantidadTransada "+cantidadTransada+" efectivoRecibido "+alMedioPagoFactura.get(i).getEfectivo_recibido() +" comision "+ alMedioPagoFactura.get(i).getPorcentaje_comision());
								}
								if(alMedioPagoFactura.get(i).getId_entidad_bancaria()==9) {
									pagadoEnEuros = pagadoEnEuros+ alMedioPagoFactura.get(i).getEfectivo_recibido() / alMedioPagoFactura.get(i).getPorcentaje_comision();
									//System.out.println("ConReportes.actualizaListReportes() X numero " + alMedioPagoFactura.get(i).getNumero() + "pagadoEnEuros "+pagadoEnEuros+" totalPagado "+totalPagado+" cantidadTransada "+cantidadTransada+" efectivoRecibido "+alMedioPagoFactura.get(i).getEfectivo_recibido() +" comision "+ alMedioPagoFactura.get(i).getPorcentaje_comision());
								}
							}
						}
						if (totalPagado > 0) {
							String sTotalPagado = FormatoNumero.formatoCero(totalPagado);
							int iEspacio1 = 30 - alMedioPago.get(k).getNombre().length();
							String titulo = alMedioPago.get(k).getNombre();

							if (alMedioPago.get(k).getNombre().equals("TARJETA CRÉDITO")) {
								for (int h = 0; h < (iEspacio1 / 2 + 1); h++) {
									titulo = titulo + ".";
								}
							} else if (alMedioPago.get(k).getNombre().equals("TARJETA DÉBITO")) {
								for (int h = 0; h < (iEspacio1 / 2 + 2); h++) {
									titulo = titulo + ".";
								}
							} else {
								for (int h = 0; h < iEspacio1; h++) {
									titulo = titulo + ".";
								}
							}

							String tituloFinal = ".....";
							
							if(pagadoEnDolares>0) {
								intReportes.getTaListadoReporte().append("Dolares recibidos "+pagadoEnDolares+"\n");
							}
							if(pagadoEnDolares>0) {
								intReportes.getTaListadoReporte().append("Euros recibidos "+pagadoEnEuros+"\n");
							}							
							
							intReportes.getTaListadoReporte()
									.append(titulo + cantidadTransada + tituloFinal + sTotalPagado);
							intReportes.getTaListadoReporte().append("\n");
						}
					}

					for (int k = 0; k < alMedioPagoFactura.size(); k++) {
						if (alMedioPagoFactura.get(k).getId_medio_pago() == 1) {
							medioPagoEfectivo = medioPagoEfectivo + alMedioPagoFactura.get(k).getValor_pago();
						}
					}
					
					for (int j = 0; j < alMedioPagoFactura.size(); j++) {
						if(alMedioPagoFactura.get(j).getId_medio_pago() == 9) {
							pesosDevueltosExtranjera = pesosDevueltosExtranjera + (alMedioPagoFactura.get(j).getEfectivo_recibido()-alMedioPagoFactura.get(j).getValor_pago());	//efectivo_recibido - valor_pago
						}
					}

					double saldoDevolucionSinRemision = 0;
					for (int i = 0; i < alDevolucionVenta.size(); i++) {
						if (alDevolucionVenta.get(i).getOrigen_devolucion().equals("remision")) {
							saldoDevolucionSinRemision = saldoDevolucionSinRemision
									+ alDevolucionVenta.get(i).getValor();
						}
					}

					double saldoRemision = 0;
					ArrayList<Remision> alRemision = maestroDB
							.traeRemisiones(G.getInstance().licenciaGlobal.getIdCaja(), fechaInicial, fechaFinal);
					for (int i = 0; i < alRemision.size(); i++) {
						saldoRemision = saldoRemision + alRemision.get(i).getValor_remision();
					}
					String sSaldoRemision = FormatoNumero.formatoCero(saldoRemision);
					
					if(!sSaldoRemision.equals("0")) {
						intReportes.getTaListadoReporte().append(
							"REMISIÓN......................" + alDevolucionVenta.size() + "....." + sSaldoRemision);
					}
					
					intReportes.getTaListadoReporte().append("\n");
					System.out.println("ConReportes.actualizaListReportes() medioPagoEfectivo " + medioPagoEfectivo
							+ " dTotalDevolucion " + dTotalDevolucion + " saldoDevolucionSinRemision "
							+ saldoDevolucionSinRemision+" pesosDevueltosExtranjera "+pesosDevueltosExtranjera);
										
					double saldoCaja = medioPagoEfectivo - dTotalDevolucion + saldoDevolucionSinRemision - pesosDevueltosExtranjera;
					String sSaldoCaja = FormatoNumero.formatoCero(saldoCaja);
					intReportes.getTaListadoReporte().append(centrado("Saldo en caja Efectivo  $" + sSaldoCaja));
					intReportes.getTaListadoReporte().append("\n");
				}
			}

		} catch (Exception e) {
			System.out.println("ConReportes.actualizaListReportes() " + e);
			e.printStackTrace();
		}
	}

	private void actualizaListResumen() {

		/*
		 * try { String fechaDedes = annoDesde + "-" + mesDesde + "-" + diaDesde + " " +
		 * horaDesde + ":" + minDesde + ":00.000"; java.sql.Timestamp fechaInicial =
		 * java.sql.Timestamp.valueOf(fechaDedes);
		 * 
		 * String fechaHasta = annoHasta + "-" + mesHasta + "-" + diaHasta + " " +
		 * horaHasta + ":" + minHasta + ":00.000"; java.sql.Timestamp fechaFinal =
		 * java.sql.Timestamp.valueOf(fechaHasta);
		 * 
		 * System.out.println("ConReportes.actualizaListResumen() reporte " +
		 * fechaDedes+" fechaHasta "+fechaHasta);
		 * 
		 * intReportes.getTaListadoResumen().setFont(new Font("monospaced", Font.PLAIN,
		 * 12));
		 * 
		 * intReportes.getTaListadoResumen().setText("");
		 * intReportes.getTaListadoResumen().setText("");
		 * intReportes.getTaListadoResumen().append("\n"); Empresa empresa =
		 * maestroDB.traeEmpresa();
		 * intReportes.getTaListadoResumen().append(centrado(empresa.getNombre() +
		 * " NIT " + empresa.getNit())); intReportes.getTaListadoResumen().append("\n");
		 * 
		 * String sFecha = "" + fecha; sFecha = sFecha.substring(0,
		 * sFecha.indexOf("."));
		 * intReportes.getTaListadoResumen().append(centrado("Fecha de Impresión: " +
		 * sFecha)); intReportes.getTaListadoResumen().append("\n");
		 * 
		 * int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen(); Almacen
		 * almacen = maestroDB.almacen(id_almacen); int id_Caja =
		 * G.getInstance().licenciaGlobal.getIdCaja(); Caja caja =
		 * maestroDB.caja(id_Caja); intReportes.getTaListadoResumen()
		 * .append(centrado("Almacen " + almacen.getNombre() + " Caja " +
		 * caja.getNombre())); intReportes.getTaListadoResumen().append("\n");
		 * intReportes.getTaListadoResumen().append(centrado("Cajero " +
		 * G.getInstance().cajero)); intReportes.getTaListadoResumen().append("\n");
		 * intReportes.getTaListadoResumen().append(centrado("Resumen"));
		 * intReportes.getTaListadoResumen().append("\n");
		 * 
		 * String formatoFecha = fechaDedes.substring(0, fechaDedes.indexOf("."));
		 * intReportes.getTaListadoResumen().append(centrado("Fecha Dedes "+
		 * formatoFecha)); intReportes.getTaListadoResumen().append("\n"); formatoFecha
		 * = fechaHasta.substring(0, fechaHasta.indexOf("."));
		 * intReportes.getTaListadoResumen().append(centrado("Fecha Hasta "+formatoFecha
		 * )); intReportes.getTaListadoResumen().append("\n");
		 * 
		 * ArrayList<Resumen> traerResumenSegunRangoFechas =
		 * maestroDB.traerResumenSegunRangoFechas(id_almacen, id_Caja, id_cajero,
		 * fechaInicial, fechaFinal); if (traerResumenSegunRangoFechas.size() != 0) {
		 * String format1 = "%1$5s %2$-30s %3$-15s"; String format2 = "%1$15s %2$15s";
		 * String someLine; //System.out.println("ConReportes.actualizaListResumen()" +
		 * "Tamaño del resumen " +
		 * traerResumenSegunRangoFechas.size()+" fechaInicial "+fechaInicial+
		 * " fechaFinal "+fechaFinal);
		 * 
		 * for (int i = 0; i < traerResumenSegunRangoFechas.size(); i++) { someLine =
		 * String.format(format1,
		 * traerResumenSegunRangoFechas.get(i).getCodigoArticulo(),
		 * traerResumenSegunRangoFechas.get(i).getNombreArticulo(),
		 * traerResumenSegunRangoFechas.get(i).getCantidad());
		 * intReportes.getTaListadoResumen().append(someLine + "\n");
		 * 
		 * String f1 =
		 * FormatoNumero.formatoCero(traerResumenSegunRangoFechas.get(i).getUnitario());
		 * String f2 =
		 * FormatoNumero.formatoCero(traerResumenSegunRangoFechas.get(i).getTotal());
		 * 
		 * someLine = String.format(format2, f1, "$" + f2);
		 * intReportes.getTaListadoResumen().append(someLine + "\n"); } }
		 * 
		 * ArrayList<Factura> alFactura = null; alFactura =
		 * maestroDB.traerFacturaSegunRangoFechas(id_almacen, id_Caja, id_cajero,
		 * fechaInicial, fechaFinal);
		 * 
		 * if (alFactura.size() != 0) { String sFechaInicial = "" + fechaInicial; String
		 * sFechaFinal = "" + fechaFinal; sFechaInicial = sFechaInicial.substring(0,
		 * 16); sFechaFinal = sFechaFinal.substring(0, 16);
		 * 
		 * double totalVenta = 0; for (int i = 0; i < alFactura.size(); i++) {
		 * totalVenta = totalVenta + alFactura.get(i).getValor_factura();
		 * System.out.println("ConReportes.actualizaListReportes() * alFactura i "
		 * +i+" numero factura "+alFactura.get(i).getNumero()+" totalVenta "
		 * +totalVenta+" fechaInicial "+fechaInicial+ " fechaFinal "+fechaFinal); }
		 * String sTotalVenta = FormatoNumero.formatoCero(totalVenta);
		 * intReportes.getTaListadoResumen().append("\n");
		 * intReportes.getTaListadoResumen().append(centrado("Total Venta          $" +
		 * sTotalVenta)); intReportes.getTaListadoResumen().append("\n"); }
		 * 
		 * } catch (Exception e) {
		 * System.out.println("ConReportes.actualizaListReportes() " + e);
		 * e.printStackTrace(); }
		 */
	}

	
	private void imprimeListReportes(String reporte) {
		try {
			String fechaDedes = annoDesde + "-" + mesDesde + "-" + diaDesde + " " + horaDesde + ":" + minDesde
					+ ":00.000";
			java.sql.Timestamp fechaInicial = java.sql.Timestamp.valueOf(fechaDedes);

			String fechaHasta = annoHasta + "-" + mesHasta + "-" + diaHasta + " " + horaHasta + ":" + minHasta
					+ ":00.000";
			java.sql.Timestamp fechaFinal = java.sql.Timestamp.valueOf(fechaHasta);

			ImpresionFactura impresionFactura = new ImpresionFactura();

			if (reporte.equals("")) { // no se ha seleccionado reporte
				System.out.println("ConReportes.actualizaListReportes() reporte " + reporte);
				intReportes.getTaListadoReporte().setText("");

			} else {
				int respuestaCaja = 1;
				if (reporte.equals("Z")) {
					Object[] options = { "Imprimir", "Cancelar" };
					respuestaCaja = JOptionPane.showOptionDialog(null,
							"Si imprime el reporte Z no podrá facturar más " + "por el día de hoy.",
							"Impresión Comprobante de Informe Diario", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				}

				if ((reporte.equals("Z") && respuestaCaja == 0) || reporte.equals("X")) {
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					Empresa empresa = maestroDB.traeEmpresa();
					impresionFactura.AddCabecera(centrado(empresa.getNombre() + " NIT " + empresa.getNit()));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					impresionFactura.AddCabecera(centrado("Serial Equipo " + serialEquipo));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());

					String sFecha = "" + fecha;
					sFecha = sFecha.substring(0, sFecha.indexOf("."));
					impresionFactura.AddCabecera(centrado("Fecha: " + sFecha));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());

					int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
					Almacen almacen = maestroDB.almacen(id_almacen);
					int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
					Caja caja = maestroDB.caja(id_Caja);
					impresionFactura
							.AddCabecera(centrado("Almacen " + almacen.getNombre() + " Caja " + caja.getNombre()));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					impresionFactura.AddCabecera(centrado("Cajero " + G.getInstance().cajero));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					impresionFactura.AddCabecera(centrado("Reporte " + reporte));
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());

					ArrayList<Factura> alFactura = maestroDB.traerFacturaSegunRangoFechas(id_almacen, id_Caja,
							id_cajero, fechaInicial, fechaFinal);
					if (alFactura.size() != 0) {
						String prefijo = maestroDB.traePrefijo(id_Caja);
						int numeroFacturaInicial = (int) alFactura.get(0).getNumero();
						int numeroFacturaFinal = (int) alFactura.get(alFactura.size() - 1).getNumero();
						String sFechaInicial = "" + fechaInicial;
						String sFechaFinal = "" + fechaFinal;
						sFechaInicial = sFechaInicial.substring(0, 16);
						sFechaFinal = sFechaFinal.substring(0, 16);

						impresionFactura.AddCabecera(centrado("Factura                Fecha"));
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						impresionFactura.AddCabecera(centrado("Inicial " + prefijo + "-" + numeroFacturaInicial + "   "
								+ alFactura.get(0).getFecha() + " " + alFactura.get(0).getHora())); // sFechaInicial));
						// alFactura.get(alFactura.size()-1).getFecha()+"
						// "+alFactura.get(alFactura.size()-1).getHora())); //sFechaInicial));
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						impresionFactura.AddCabecera(centrado("  Final " + prefijo + "-" + numeroFacturaFinal + "   "
								+ alFactura.get(alFactura.size() - 1).getFecha() + " "
								+ alFactura.get(alFactura.size() - 1).getHora())); // sFechaFinal));
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());

						ArrayList<IvaValor> alIvaValor = maestroDB.traeTotalIvaPeriodo(numeroFacturaInicial,
								numeroFacturaFinal, id_Caja);
						ArrayList<BaseIva> alBaseIva = maestroDB.traeTablaIva();
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						
						
						if(empresa.getId_regimen()==1) {
							impresionFactura.AddCabecera(centrado("Detalle IVA"));
							impresionFactura.AddCabecera(impresionFactura.DarEspacio());
							for (int i = 0; i < alIvaValor.size(); i++) {
								for (int j = 0; j < alBaseIva.size(); j++) {
									if (alIvaValor.get(i).getIdIva() == alBaseIva.get(j).getIdBaseIva()) {
										String sTotalxIva = FormatoNumero.formatoCero(alIvaValor.get(i).getTotalxIva());
										String sValorIva = FormatoNumero.formatoCero(alIvaValor.get(i).getValorIva());
										int iEspacio2 = 15 - sValorIva.length();
										String espacio2 = "";
										for (int k = 0; k < iEspacio2; k++) {
											espacio2 = espacio2 + ".";
										}
										int iEspacio1 = 15 - sTotalxIva.length();
										String espacio1 = "";
										for (int k = 0; k < iEspacio1; k++) {
											espacio1 = espacio1 + ".";
										}
										String titulo1 = alBaseIva.get(j).getNombreBaseIva();
										int iTitulo1 = 10 - titulo1.length();
										for (int k = 0; k < iTitulo1; k++) {
											titulo1 = " " + titulo1;
										}
										impresionFactura
												.AddCabecera(titulo1 + espacio1 + sTotalxIva + espacio2 + sValorIva);
										impresionFactura.AddCabecera("\n");
									}
								}
							}
						}
						impresionFactura.AddCabecera(
								centrado("Total de transacciones " + (numeroFacturaFinal - numeroFacturaInicial + 1)));
						//impresionFactura.AddCabecera("\n");

						double totalVenta = 0;
						for (int i = 0; i < alFactura.size(); i++) {
							// System.out.println("ConReportes.actualizaListReportes() alFactura i "+i+"
							// numero factura "+alFactura.get(i).getNumero());
							totalVenta = totalVenta + alFactura.get(i).getValor_factura();
						}
						String sTotalVenta = FormatoNumero.formatoCero(totalVenta);
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						impresionFactura.AddCabecera(centrado("Total Venta          $" + sTotalVenta));
						//impresionFactura.AddCabecera(impresionFactura.DarEspacio());

						ArrayList<DevolucionVenta> alDevolucionVenta = maestroDB.traeDevolucionesPeriodo(
								G.getInstance().licenciaGlobal.getIdCaja(), fechaInicial, fechaFinal);
						/*
						 * String sGsonAlDevolucionVenta = ""; Gson gson = new Gson();
						 * sGsonAlDevolucionVenta = gson.toJson(alDevolucionVenta); System.out.
						 * println("ConReportes.actualizaListReportes() sGsonAlDevolucionVenta GSON "
						 * +sGsonAlDevolucionVenta);
						 */

						double dTotalDevolucion = 0;
						ArrayList<DevolucionesBaseIva> alDevolucionesBaseIva = maestroDB
								.traeDevolucionesBaseIva(id_Caja, fechaInicial, fechaFinal);

						if (alDevolucionesBaseIva.size() != 0) {
							impresionFactura.AddCabecera(impresionFactura.DarEspacio());
							impresionFactura.AddCabecera(
									centrado("Devoluci\u00F3n ventas remisi\u00F3n " + alDevolucionVenta.size()));
							impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						}

						for (int i = 0; i < alDevolucionesBaseIva.size(); i++) {
							for (int j = 0; j < alBaseIva.size(); j++) {
								if (alBaseIva.get(j).getIdBaseIva() == alDevolucionesBaseIva.get(i).getId_base_iva()) {
									String devolucionIva = FormatoNumero
											.formatoCero(alDevolucionesBaseIva.get(i).getIva());
									String devolucionTotal = FormatoNumero
											.formatoCero(alDevolucionesBaseIva.get(i).getTotal());

									int iEspacio2 = 15 - devolucionIva.length();
									String espacio2 = "";
									for (int k = 0; k < iEspacio2; k++) {
										espacio2 = espacio2 + ".";
									}

									int iEspacio3 = 15 - devolucionTotal.length();
									String espacio3 = "";
									for (int k = 0; k < iEspacio3; k++) {
										espacio3 = espacio3 + ".";
									}

									String titulo1 = alBaseIva.get(j).getNombreBaseIva();
									int iTitulo1 = 10 - titulo1.length();
									for (int k = 0; k < iTitulo1; k++) {
										titulo1 = " " + titulo1;
									}

									dTotalDevolucion = dTotalDevolucion + alDevolucionesBaseIva.get(i).getTotal();
									impresionFactura.AddCabecera(
											centrado(titulo1 + espacio2 + devolucionIva + espacio3 + devolucionTotal));
									impresionFactura.AddCabecera(impresionFactura.DarEspacio());
								}
							}
						}

						if (alDevolucionesBaseIva.size() != 0) {
							String sTotalDevolucion = FormatoNumero.formatoCero(dTotalDevolucion);
							impresionFactura
									.AddCabecera(centrado("Total Devoluci\u00F3n          $" + sTotalDevolucion));
							impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						}

						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						impresionFactura.AddCabecera(centrado("Formas de pago"));
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());

						ArrayList<MedioPago> alMedioPago = new ArrayList<MedioPago>();
						alMedioPago = maestroDB.alMedioPago();

						ArrayList<MedioPagoFactura> alMedioPagoFactura = new ArrayList<MedioPagoFactura>();
						alMedioPagoFactura = maestroDB.traerFormasDePagoPeriodo(numeroFacturaInicial,
								numeroFacturaFinal, id_Caja);

						double totalPagado = 0;
						int cantidadTransada = 0;
						for (int k = 0; k < alMedioPago.size(); k++) {
							totalPagado = 0;
							cantidadTransada = 0;
							
							double pagadoEnDolares = 0.0;
							double pagadoEnEuros = 0.0;
							
							for (int i = 0; i < alMedioPagoFactura.size(); i++) {
								if (alMedioPagoFactura.get(i).getId_medio_pago() == alMedioPago.get(k).getId()) {
									totalPagado = totalPagado + alMedioPagoFactura.get(i).getValor_pago();
									cantidadTransada++;
									
									if(alMedioPagoFactura.get(i).getId_entidad_bancaria()==8) {
										pagadoEnDolares = pagadoEnDolares+ alMedioPagoFactura.get(i).getEfectivo_recibido() / alMedioPagoFactura.get(i).getPorcentaje_comision();
										//System.out.println("ConReportes.actualizaListReportes() X pagadoEnDolares "+pagadoEnDolares+" totalPagado "+totalPagado+" cantidadTransada "+cantidadTransada+" efectivoRecibido "+alMedioPagoFactura.get(i).getEfectivo_recibido() +" comision "+ alMedioPagoFactura.get(i).getPorcentaje_comision());
									}
									if(alMedioPagoFactura.get(i).getId_entidad_bancaria()==9) {
										pagadoEnEuros = pagadoEnEuros+ alMedioPagoFactura.get(i).getEfectivo_recibido() / alMedioPagoFactura.get(i).getPorcentaje_comision();
										//System.out.println("ConReportes.actualizaListReportes() X numero " + alMedioPagoFactura.get(i).getNumero() + "pagadoEnEuros "+pagadoEnEuros+" totalPagado "+totalPagado+" cantidadTransada "+cantidadTransada+" efectivoRecibido "+alMedioPagoFactura.get(i).getEfectivo_recibido() +" comision "+ alMedioPagoFactura.get(i).getPorcentaje_comision());
									}
								}
							}
							if (totalPagado > 0) {
								String sTotalPagado = FormatoNumero.formatoCero(totalPagado);
								int iEspacio1 = 20 - (alMedioPago.get(k).getNombre().length());
								String titulo = alMedioPago.get(k).getNombre();
								for (int h = 0; h < iEspacio1; h++) {
									titulo = titulo + ".";
								}
								String tituloFinal = ".....";
								
								if(pagadoEnDolares>0) {
									impresionFactura.AddCabecera("Dolares recibidos "+pagadoEnDolares);
									impresionFactura.AddCabecera(impresionFactura.DarEspacio());
								}
								if(pagadoEnDolares>0) {
									impresionFactura.AddCabecera("Euros recibidos "+pagadoEnEuros);
									impresionFactura.AddCabecera(impresionFactura.DarEspacio());
								}
								
								impresionFactura.AddCabecera(titulo + cantidadTransada + tituloFinal + sTotalPagado);
								impresionFactura.AddCabecera(impresionFactura.DarEspacio());
							}
						}

						double medioPagoEfectivo = 0;
						for (int k = 0; k < alMedioPagoFactura.size(); k++) {
							if (alMedioPagoFactura.get(k).getId_medio_pago() == 1) {
								medioPagoEfectivo = medioPagoEfectivo + alMedioPagoFactura.get(k).getValor_pago();
							}
						}
						double pesosDevueltosExtranjera = 0;
						for (int j = 0; j < alMedioPagoFactura.size(); j++) {
							if(alMedioPagoFactura.get(j).getId_medio_pago() == 9) {
								pesosDevueltosExtranjera = pesosDevueltosExtranjera + (alMedioPagoFactura.get(j).getEfectivo_recibido()-alMedioPagoFactura.get(j).getValor_pago());	//efectivo_recibido - valor_pago
							}
						}

						double saldoDevolucionSinRemision = 0;
						for (int i = 0; i < alDevolucionVenta.size(); i++) {
							if (alDevolucionVenta.get(i).getOrigen_devolucion().equals("remision")) {
								saldoDevolucionSinRemision = saldoDevolucionSinRemision
										+ alDevolucionVenta.get(i).getValor();
							}
						}

						double saldoRemision = 0;
						ArrayList<Remision> alRemision = maestroDB
								.traeRemisiones(G.getInstance().licenciaGlobal.getIdCaja(), fechaInicial, fechaFinal);
						for (int i = 0; i < alRemision.size(); i++) {
							saldoRemision = saldoRemision + alRemision.get(i).getValor_remision();
						}
						String sSaldoRemision = FormatoNumero.formatoCero(saldoRemision);

						if (!sSaldoRemision.equals("0")) {
							impresionFactura.AddCabecera(
									"REMISION............" + alDevolucionVenta.size() + "....." + sSaldoRemision);
							impresionFactura.AddCabecera(impresionFactura.DarEspacio());
						}
						double saldoCaja = medioPagoEfectivo - dTotalDevolucion + saldoDevolucionSinRemision - pesosDevueltosExtranjera;
						String sSaldoCaja = FormatoNumero.formatoCero(saldoCaja);
						impresionFactura.AddCabecera(centrado("Saldo en caja Efectivo  $" + sSaldoCaja));
						impresionFactura.AddCabecera(impresionFactura.DarEspacio());
					}

					if (reporte.equals("Z")) {
						int id_caja = G.getInstance().licenciaGlobal.getIdCaja();
						int id_usuario = G.getInstance().getIdCajero;
						boolean comprobanteDiarioCaja = maestroDB.comprobanteDiarioCaja(id_caja, id_usuario);
						System.out.println(
								"ConReportes.imprimeListReportes() comprobanteDiarioCaja " + comprobanteDiarioCaja);
					}
				}

				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				impresionFactura.AddCabecera("========================================");
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				impresionFactura.AddPieLinea(centrado(" FIN"));
				impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
				impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
				impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
				impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
				impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
				impresionFactura.ImprimirDocumento(true);
			}
		} catch (Exception e) {
			System.out.println("ConReportes.imprimeListReportes() " + e);
			e.printStackTrace();
		}
	}

	private void imprimeListResumen() {
		System.out.println("ConReportes.imprimeListResumen2()");
		try {
			String fechaDedes = annoDesde + "-" + mesDesde + "-" + diaDesde + " " + horaDesde + ":" + minDesde
					+ ":00.000";
			java.sql.Timestamp fechaInicial = java.sql.Timestamp.valueOf(fechaDedes);

			String fechaHasta = annoHasta + "-" + mesHasta + "-" + diaHasta + " " + horaHasta + ":" + minHasta
					+ ":00.000";
			java.sql.Timestamp fechaFinal = java.sql.Timestamp.valueOf(fechaHasta);

			ImpresionFactura impresionFactura = new ImpresionFactura();

			Empresa empresa = maestroDB.traeEmpresa();
			impresionFactura.AddCabecera(centrado(empresa.getNombre() + " NIT " + empresa.getNit()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			String sFecha = "" + fecha;
			sFecha = sFecha.substring(0, sFecha.indexOf("."));
			impresionFactura.AddCabecera(centrado("Fecha impresión: " + sFecha));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			int id_almacen = G.getInstance().licenciaGlobal.getIdAlmacen();
			Almacen almacen = maestroDB.almacen(id_almacen);
			int id_Caja = G.getInstance().licenciaGlobal.getIdCaja();
			Caja caja = maestroDB.caja(id_Caja);
			impresionFactura.AddCabecera(centrado("Almacen " + almacen.getNombre() + " Caja " + caja.getNombre()));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			impresionFactura.AddCabecera(centrado("Cajero " + G.getInstance().cajero));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			String sFechaInicial = "" + fechaInicial;
			String sFechaFinal = "" + fechaFinal;
			sFechaInicial = sFechaInicial.substring(0, 10);
			sFechaFinal = sFechaFinal.substring(0, 10);

			impresionFactura.AddCabecera(centrado("Resumen FI " + sFechaInicial + " FF " + sFechaFinal));
			impresionFactura.AddCabecera(impresionFactura.DarEspacio());

			ArrayList<Resumen> traerResumenSegunRangoFechas = maestroDB.traerResumenSegunRangoFechas(id_almacen,
					id_Caja, id_cajero, fechaInicial, fechaFinal);
			if (traerResumenSegunRangoFechas.size() != 0) {
				String format1 = "%1$5s %2$-30s %3$-15s";
				String format2 = "%1$3s %2$10s";
				String someLine;

				for (int i = 0; i < traerResumenSegunRangoFechas.size(); i++) {
					someLine = String.format(format1, traerResumenSegunRangoFechas.get(i).getCodigoArticulo(),
							traerResumenSegunRangoFechas.get(i).getNombreArticulo(),
							traerResumenSegunRangoFechas.get(i).getCantidad());
					impresionFactura.AddCabecera(someLine);

					String f1 = FormatoNumero.formatoCero(traerResumenSegunRangoFechas.get(i).getUnitario());
					String f2 = FormatoNumero.formatoCero(traerResumenSegunRangoFechas.get(i).getTotal());

					someLine = String.format(format2, f1, "$" + f2);
					impresionFactura.AddCabecera(someLine);
					impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				}
			}

			ArrayList<Factura> alFactura = maestroDB.traerFacturaSegunRangoFechas(id_almacen, id_Caja, id_cajero,
					fechaInicial, fechaFinal);
			if (alFactura.size() != 0) {

				double totalVenta = 0;
				for (int i = 0; i < alFactura.size(); i++) {
					totalVenta = totalVenta + alFactura.get(i).getValor_factura();
				}
				String sTotalVenta = FormatoNumero.formatoCero(totalVenta);
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
				impresionFactura.AddCabecera(centrado("Total Venta          $" + sTotalVenta));
				impresionFactura.AddCabecera(impresionFactura.DarEspacio());
			}

			impresionFactura.AddPieLinea(centrado(" FIN"));
			impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
			impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
			impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
			impresionFactura.AddPieLinea(impresionFactura.DarEspacio());
			impresionFactura.ImprimirDocumento(true);

		} catch (Exception e) {
			System.out.println("ConReportes.actualizaListReportes() " + e);
			e.printStackTrace();
		}
	}

	static String getSerialNumber() throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("wmic", "baseboard", "get", "serialnumber");
		Process process = pb.start();
		process.waitFor();
		String serialNumber = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if (line.length() < 1 || line.startsWith("SerialNumber")) {
					continue;
				}
				serialNumber = line;
				break;
			}
		}
		return serialNumber;
	}

	private void periodoReportes() {
		String sFecha = "" + fecha;
		annoDesde = sFecha.substring(0, 4);
		mesDesde = sFecha.substring(5, 7);
		diaDesde = sFecha.substring(8, 10);
		horaDesde = "00"; // sFecha.substring(11, 13);
		minDesde = "00"; // sFecha.substring(14, 16);

		annoHasta = sFecha.substring(0, 4);
		mesHasta = sFecha.substring(5, 7);
		diaHasta = sFecha.substring(8, 10);
		horaHasta = "23"; // sFecha.substring(11, 13);
		minHasta = "59"; // sFecha.substring(14, 16);
		// System.out.println("ConPagos.periodoPagos() sFecha "+sFecha+"
		// "+annoDesde+"-"+mesDesde+"-"+diaDesde+" "+horaDesde+":"+minDesde+":00");

		intReportes.getJcbAnnoDesde().setSelectedItem(annoDesde);
		intReportes.getJcbMesDesde().setSelectedItem(mesDesde);
		intReportes.getJcbDiaDesde().setSelectedItem(diaDesde);

		intReportes.getJcbAnnoHasta().setSelectedItem(annoHasta);
		intReportes.getJcbMesHasta().setSelectedItem(mesHasta);
		intReportes.getJcbDiaHasta().setSelectedItem(diaHasta);

		intReportes.getJcbAnnoDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoDesde = (String) intReportes.getJcbAnnoDesde().getSelectedItem();
				actualizaListResumen();
			}
		});
		intReportes.getJcbMesDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesDesde = (String) intReportes.getJcbMesDesde().getSelectedItem();
				actualizaListResumen();
			}
		});
		intReportes.getJcbDiaDesde().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaDesde = (String) intReportes.getJcbDiaDesde().getSelectedItem();
				actualizaListResumen();
			}
		});
		intReportes.getJcbAnnoHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annoHasta = (String) intReportes.getJcbAnnoHasta().getSelectedItem();
				actualizaListResumen();
			}
		});
		intReportes.getJcbMesHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesHasta = (String) intReportes.getJcbMesHasta().getSelectedItem();
				actualizaListResumen();
			}
		});
		intReportes.getJcbDiaHasta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaHasta = (String) intReportes.getJcbDiaHasta().getSelectedItem();
				actualizaListResumen();
			}
		});
	}

	public IntReportes getIntReportes() {
		return intReportes;
	}

	public void setIntReportes(IntReportes intReportes) {
		this.intReportes = intReportes;
	}

	public ModeloReportes getModeloReportes() {
		return modeloReportes;
	}

	public void setModeloReportes(ModeloReportes modeloReportes) {
		this.modeloReportes = modeloReportes;
	}

	private String centrado(String sTitulo) {
		int titulo = (44 - sTitulo.length()) / 2;
		String sTituloEspacio = "";
		for (int i = 0; i < titulo; i++) {
			sTituloEspacio = sTituloEspacio + " ";
		}
		sTituloEspacio = sTituloEspacio + sTitulo;
		return sTituloEspacio;
	}

}
