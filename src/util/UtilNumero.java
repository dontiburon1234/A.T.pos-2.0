package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class UtilNumero {
	
	
	/**
	 * Metodo que redondea un numero
	 * @param numero
	 * @param decimales
	 * @return
	 */
	public static double  redondearDecimal (double numero, int decimales) {
		System.out.println("lpq - redondearDecimal numero: " + numero + " decimales: " + decimales);
		if (numero == 0) {
			return 0;
		}
		//BigDecimal bgNumero = new BigDecimal(Math.round(numero * Math.pow(10, decimales)) / Math.pow(numero, decimales));
		
		String val = Double.toString(numero);
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(decimales, RoundingMode.HALF_UP);
		return big.doubleValue();
	}
	
	public static String formatearNumero (double numero) {
		DecimalFormatSymbols dfsSimboloDecimal = new DecimalFormatSymbols();
		dfsSimboloDecimal.setDecimalSeparator('.');
		dfsSimboloDecimal.setGroupingSeparator(',');
		DecimalFormat dfFormato = new DecimalFormat("###,###,###", dfsSimboloDecimal);
		return dfFormato.format(numero);
	}
	
	public static String formatearNumero (double numero, int decimales) {
		DecimalFormatSymbols dfsSimboloDecimal = new DecimalFormatSymbols();
		dfsSimboloDecimal.setDecimalSeparator('.');
		dfsSimboloDecimal.setGroupingSeparator(',');
		String strFormato = "###,###,###";
		if (decimales > 0) {
			strFormato += ".";
			for (int i = 0; i < decimales; i++) {
				strFormato += "0";
			}
		}
		DecimalFormat dfFormato = new DecimalFormat(strFormato, dfsSimboloDecimal);
		return dfFormato.format(numero);
	}
	
	/**
	 * Determina si un numero tiene parte decimal
	 * @param strNumero
	 * @return true si es entero, false si no es entero
	 */
	public static boolean esEntero (double dblNumero) {
		System.err.println("lpq - numero: " + dblNumero);
		long lngEntero = (long) dblNumero;
		double dblFraccion = dblNumero - lngEntero;
		if (dblFraccion == 0) {
			System.err.println("lpq - Es entero");
			return true;
		} else {
			System.err.println("lpq - No es entero");
			return false;
		}
	}
}
