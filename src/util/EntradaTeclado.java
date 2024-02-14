package util;

import java.awt.event.KeyEvent;

public class EntradaTeclado {

	public EntradaTeclado() {
		// TODO Auto-generated constructor stub
	}

	public static void validarNumero (KeyEvent e) {
		char cKey = e.getKeyChar();
		//if (cKey < '0' || cKey > '9') {
		if (cKey < KeyEvent.VK_0 || cKey > KeyEvent.VK_9) {	
			e.consume();
		}
	}

	public static void validarDecimal (KeyEvent e, String entrada) {
		char[] chChar = entrada.toCharArray();
		boolean existePunto = false;
		for (int i = 0; i < chChar.length; i ++) {
			if (chChar[i] == '.') {
				existePunto = true;
				break;
			}
		}
		char chKey = e.getKeyChar();
		if (existePunto) {
			validarNumero(e);
		} else {
			//if ((chKey < '0' || chKey > '9') && chKey != '.') {
			if ((chKey < KeyEvent.VK_0 || chKey > KeyEvent.VK_9) && chKey != '.') {
				e.consume();
			}
		}
	}

	public static void validarSigno (KeyEvent e, String entrada) {

	}
	

}
