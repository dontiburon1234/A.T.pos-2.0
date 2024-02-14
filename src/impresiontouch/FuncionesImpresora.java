package impresiontouch;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class FuncionesImpresora {

	public static void abrirCajon (boolean blnImpresoraDefecto, String strImpresora) {
		
		System.out.println("FuncionesImpresora "+"AQUI VAMOS CON SIN PUERTAS");
		
		// Abrir monedero
		byte[] byAbreMonedero = determinarByteAbrirCajon(strImpresora);
		//byte[] byAbreMonedero = {(char) 27, (char) 112, (char) 48, (char) 55, (char) 121};
		//byte[] byAbreMonedero = {(char) 7};
		try {
			DocFlavor dfFactura = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			
//			PrintService psFactura = PrintServiceLookup.lookupDefaultPrintService();
//			DocPrintJob dpjFactura = psFactura.createPrintJob();
			byte[] byFactura;
			byte[] byMonedero;
			try {
				Doc doc = new SimpleDoc(byAbreMonedero, dfFactura, null);
				
				
				if (blnImpresoraDefecto) {
					PrintService psFactura = PrintServiceLookup.lookupDefaultPrintService();
					DocPrintJob dpjFactura = psFactura.createPrintJob();
					dpjFactura.print(doc, null);
				} else {
					PrintService[] psFactura = PrintServiceLookup.lookupPrintServices(null, new HashAttributeSet(new PrinterName(strImpresora, null)));
					DocPrintJob dpjFactura = psFactura[0].createPrintJob();
					dpjFactura.print(doc, null);
				}
				System.out.println("lpq - abre el cajon");
				
				// Cortar papel
				//char[] CORTAR_PAPEL=new char[]{0x1B,'m'};
				//imp.write(CORTAR_PAPEL);
				//if(abrir){
					//char ABRIR_GAVETA[]={(char)27,(char)112,(char)0,(char)10,(char)100};
					//imp.write(ABRIR_GAVETA);
				//}
			} catch (Exception e) {
				System.out.println("FuncionesImpresora.abrirCajon() ERROR EN IMPRESION "+e);
				e.printStackTrace();
				for (int i = 0; i < 1000; i ++) {
					int b = 3-5;
				}
				//abrirCajon(strImpresora, logCaja);
			}
		} catch (Exception e) {
			System.out.println("FuncionesImpresora.abrirCajon() ERROR EN IMPRESION "+e);
			e.printStackTrace();
		}
	}
	
	public static void cortarPapel (boolean blnImpresoraDefecto, String strImpresora) {
		byte[] byCortaPapel = determinarByteCortarPapel(strImpresora);
		//byte[] byCortaPapel = {(char) 0x1B, (char) 'm'};
		//byte[] byCortaPapel = {(char) 27, (char) 100, (byte) 2};
		try {
			DocFlavor dfFactura = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			
			byte[] byFactura;
			byte[] byMonedero;
			try {
				Doc doc = new SimpleDoc(byCortaPapel, dfFactura, null);
				
				if (blnImpresoraDefecto) {
					PrintService psFactura = PrintServiceLookup.lookupDefaultPrintService();
					DocPrintJob dpjFactura = psFactura.createPrintJob();
					dpjFactura.print(doc, null);
				} else {
					PrintService[] psFactura = PrintServiceLookup.lookupPrintServices(null, new HashAttributeSet(new PrinterName(strImpresora, null)));
					DocPrintJob dpjFactura = psFactura[0].createPrintJob();
					dpjFactura.print(doc, null);
				}
				System.out.println("lpq - corta papel");
				
				// Cortar papel
				//char[] CORTAR_PAPEL=new char[]{0x1B,'m'};
				//imp.write(CORTAR_PAPEL);
				//if(abrir){
					//char ABRIR_GAVETA[]={(char)27,(char)112,(char)0,(char)10,(char)100};
					//imp.write(ABRIR_GAVETA);
				//}
			} catch (Exception e) {
				System.out.println("FuncionesImpresora.cortarPapel() ERROR EN IMPRESION ");
				//logCaja.escribirLog("logCaja", "Error en impresion ", e);
				e.printStackTrace();
				for (int i = 0; i < 1000; i ++) {
					int b = 3-5;
				}
				//abrirCajon(strImpresora, logCaja);
			}
		} catch (Exception e) {
			System.out.println("FuncionesImpresora.cortarPapel() ERROR EN IMPRESION ");
			e.printStackTrace();
			//logCaja.escribirLog("logCaja", "Error en impresion ", e);
		}
	}
	
	public static byte[] determinarByteCortarPapel (String strImpresora) {
		if (strImpresora.startsWith("Star")) {
			byte[] byCortaPapel = {(char) 27, (char) 100, (byte) 2};
			return byCortaPapel;
		}
		byte[] byCortaPapel = {(char) 0x1B, (char) 'm'};
		return byCortaPapel;
	}
	
	public static byte[] determinarByteAbrirCajon (String strImpresora) {
		if (strImpresora.startsWith("Star")) {
			byte[] byAbreMonedero = {(char) 7};
			return byAbreMonedero;
		}
		byte[] byAbreMonedero = {(char) 27, (char) 112, (char) 48, (char) 55, (char) 121};
		return byAbreMonedero;
	}

}
