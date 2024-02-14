package poledisplay;

import database.MaestroDB;
import util.G;

public class PoleDisplay implements Runnable{

	private OperacionPoleDisplay operacionPoleDisplay;
	private MaestroDB maestroDB;
	private String lineaUno;
	private String lineaDos;
	private boolean inicio;

	public PoleDisplay(OperacionPoleDisplay operacionPoleDisplay, MaestroDB maestroDB, 
			String lineaUno, String lineaDos, boolean inicio) {
		this.operacionPoleDisplay = operacionPoleDisplay;
		this.maestroDB = maestroDB;
		this.lineaUno = lineaUno;
		this.lineaDos = lineaDos;
		this.inicio = inicio;
	}

	@Override
	public void run() {
		//  Pole Display Inicio Pole Display Inicio Pole Display Inicio Pole Display Inicio Pole Display Inicio
		try {
			operacionPoleDisplay = new OperacionPoleDisplay(maestroDB);
			if(inicio) {
				if(G.getInstance().defaultPort.equals("")) {
					System.out.println("PoleDisplay.run() NO HAY POLE_DISPLAY");
				}else {
					System.out.println("PoleDisplay.run() EL PUERTO DEL POLE_DISPLAY ES "+G.getInstance().defaultPort);
					operacionPoleDisplay.StartDisplay(); //optimal choice is start when system start.
				}
			}
			operacionPoleDisplay.PrintFirstLine(lineaUno);
			operacionPoleDisplay.PrintSecondLine(lineaDos);
			//operacionPoleDisplay.close();
		} catch (Exception e) {
			operacionPoleDisplay = null;
		}
		try {
			Thread.sleep(10000);   
		} catch (InterruptedException ex) {
			System.out.println("OperacionPoleDisplay.main() wait 2 seconds.otherwise unable to see above outputs");
			ex.printStackTrace();
		}

		if(operacionPoleDisplay!=null) {
			//operacionPoleDisplay.ClearDisplay();
			//operacionPoleDisplay.close();
		}		
		// Pole Display Final Pole Display Final Pole Display Final Pole Display Final Pole Display Final Pole Display Final
	}

}
