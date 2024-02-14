package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatoNumero {

	public static String formato(double value) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
		DecimalFormat myFormatter = new DecimalFormat("###,###.######",dfs);
		String output = myFormatter.format(value);
		return output;	
	}

	public static String formatoCero(double value) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
		DecimalFormat myFormatter = new DecimalFormat("###,###",dfs);
		String output = myFormatter.format(value);
		return output;	
	}
	
	public static String formato(String value) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
		DecimalFormat myFormatter = new DecimalFormat("###,###.######",dfs);
		String output = "";
		String entero = "0";
		String decimal = "0";

		if(value.equals("")){
			output = "";
		}else if(value.subSequence(value.length()-1, value.length()).equals(".")){
			output = myFormatter.format(Double.valueOf(value))+".";
		}else if(value.indexOf(".")>0 && value.subSequence(value.length()-1, value.length()).equals("0")){
			entero = value.substring(0,value.indexOf("."));
			decimal = value.substring(value.indexOf(".")+1, value.length());
			output = myFormatter.format(Double.valueOf(entero))+"."+decimal;
		}else if(value.indexOf(".")>0 && !value.subSequence(value.length()-1, value.length()).equals("0")){
			entero = value.substring(0,value.indexOf("."));
			decimal = value.substring(value.indexOf(".")+1, value.length());
			output = myFormatter.format(Double.valueOf(entero))+"."+decimal;
		}else{
			output = myFormatter.format(Double.valueOf(value));	
		}
		return output;	
	}
}
