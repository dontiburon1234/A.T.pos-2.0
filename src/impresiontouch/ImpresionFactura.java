package impresiontouch;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

import util.G;

public class ImpresionFactura {

	static ArrayList<String> CabezaLineas;
	static ArrayList<String> subCabezaLineas;
	static ArrayList<String> items;
	static ArrayList<String> totales;
	static ArrayList<String> LineasPie;

	public ImpresionFactura() {
		super();		
		CabezaLineas=new ArrayList<String>();
		subCabezaLineas=new ArrayList<String>();
		items=new ArrayList<String>();
		totales=new ArrayList<String>();
		LineasPie=new ArrayList<String>();
	}
	public void AddCabecera(String line){ 
		CabezaLineas.add(line);
	}
	public static void AddSubCabecera(String line){
		subCabezaLineas.add(line);
	}
	public static void AddItem(String cantidad,String item,String price){
		OrderItem newItem = new OrderItem(' ');
		items.add(newItem.GeneraItem(cantidad,item, price,""));
	}
	public static void AddTotal(String name,String price){
		OrderTotal newTotal = new OrderTotal(' ');
		totales.add(newTotal.GeneraTotal(name, price));
	}
	public void AddPieLinea(String line){
		LineasPie.add(line);
	}
	public static String DibujarLinea(int valor){
		String raya="";
		for(int x=0;x<valor;x++){
			raya+="=";
		}
		return raya;
	}
	public String DarEspacio(){
		return "\n";
	}
	public void ImprimirDocumento(boolean abreCajon){

		System.out.println("ImpresionFactura.ImprimirDocumento()");

		String cadena="";
		for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++ ){
			cadena+=CabezaLineas.get(cabecera);
		}
		for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){
			cadena+=subCabezaLineas.get(subcabecera);
		}
		for(int ITEM=0;ITEM<items.size();ITEM++){
			cadena+=items.get (ITEM);
		}
		for(int total=0;total<totales.size();total++){
			cadena+=totales.get(total);
		}
		for(int pie=0;pie<LineasPie.size();pie++){
			cadena+=LineasPie.get(pie);
		}

		if(!G.getInstance().licenciaGlobal.getImpresora().equals("")) {
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			DocPrintJob pj = service.createPrintJob();
			byte[]bytes =cadena.getBytes();
			Doc doc = new SimpleDoc(bytes, flavor,null);
			try{
				pj.print(doc,null);
				FuncionesImpresora.cortarPapel(true, G.getInstance().licenciaGlobal.getImpresora());
				if(abreCajon) {
					FuncionesImpresora.abrirCajon(true, G.getInstance().licenciaGlobal.getImpresora());
				}
			}catch(Exception e){ 
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "No tiene definida la impresora en la licencia");//EPSON TM-m10 Receipt5
		}
	}
}
