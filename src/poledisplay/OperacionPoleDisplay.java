package poledisplay;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.MaestroDB;
import gnu.io.CommPortIdentifier;
import jssc.SerialPort;
import jssc.SerialPortException;
import util.G;

public class OperacionPoleDisplay {

	static Enumeration<?> portList;
	static CommPortIdentifier portId;
	static SerialPort serialPort;
	static OutputStream outputStream;
	String  defaultPort = "";

	public OperacionPoleDisplay(MaestroDB maestroDB) {
		defaultPort = maestroDB.cargarUnParametro("poleDisplay");
		G.getInstance().defaultPort = defaultPort;
		if(G.getInstance().defaultPort.equals("")) {
			System.out.println("OperacionPoleDisplay.OperacionPoleDisplay() NO HAY POLE_DISPLAY");
		}else {
			System.out.println("OperacionPoleDisplay.OperacionPoleDisplay() EL PUERTO DEL POLE_DISPLAY ES "+G.getInstance().defaultPort);
		}
	}

	public void StartDisplay() {
		boolean portFound = false;
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();

			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

				if (portId.getName().equals(defaultPort)) {
					System.out.println("OperacionPoleDisplay.StartDisplay() Found port " + defaultPort);
					portFound = true;
					serialPort = new SerialPort(defaultPort); 

					if(!serialPort.isOpened()) {
						try {
							// opening port
							serialPort.openPort();

							serialPort.setParams(SerialPort.BAUDRATE_9600,
									SerialPort.DATABITS_8, 
									SerialPort.STOPBITS_1, 
									SerialPort.PARITY_NONE);

							System.out.println("OperacionPoleDisplay.StartDisplay() Display is online now serialPort.isOpened() "+serialPort.isOpened());
						} catch (SerialPortException e) {
							System.out.println("OperacionPoleDisplay.StartDisplay() "+e);
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(2000); // Be sure data is xferred before closing
					} catch (Exception e) {
						System.out.println("OperacionPoleDisplay.StartDisplay() "+e);
					}
				} 
			} 
		}

		System.out.println("OperacionPoleDisplay.StartDisplay() 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30");
		if (!portFound) {
			System.out.println("OperacionPoleDisplay.StartDisplay() port " + defaultPort + " not found. IF portFound "+portFound);
			G.getInstance().defaultPortFounf = false;
		} else {
			System.out.println("OperacionPoleDisplay.StartDisplay() port " + defaultPort + " found. ELSE portFound "+portFound);
			G.getInstance().defaultPortFounf = true;
		}

	}   
	public void ClearDisplay(){
		try{
			serialPort.writeBytes(ESCPOS.SELECT_DISPLAY);
			serialPort.writeBytes(ESCPOS.VISOR_CLEAR);
			serialPort.writeBytes(ESCPOS.VISOR_HOME);
		}
		catch (SerialPortException e) {
			e.printStackTrace();
		}
	}
	public void PrintFirstLine(String text){
		try{
			ClearDisplay();
			if(text.length()>20)            //Display can hold only 20 characters per line.Most of displays have 2 lines.
				text=text.substring(0,20);
			serialPort.writeString(text);
		}
		catch (SerialPortException e) {
			System.out.println("OperacionPoleDisplay.PrintFirstLine() "+e);
			e.printStackTrace();
		}

	}
	public void PrintSecondLine(String text){
		try{
			serialPort.writeBytes(ESCPOS.SELECT_DISPLAY);
			serialPort.writeBytes(ESCPOS.Down_Line);
			serialPort.writeBytes(ESCPOS.Left_Line);
			if(text.length()>20)
				text=text.substring(0,20);
			serialPort.writeString(text);
		}
		catch (SerialPortException e) {
			System.out.println("OperacionPoleDisplay.PrintSecondLine() "+e);
			e.printStackTrace();
		}
	}
	public void ShowGreeting(){
		String text1="*****Thank You******";                              // 20 characters
		String text2="     Come Again     ";                              //20 characters
		ClearDisplay();
		PrintFirstLine(text1);
		PrintSecondLine(text2);
		try {
			Thread.sleep(5000); //Greeting will dislpay 5 seconds.
		} catch (InterruptedException ex) {
			Logger.getLogger(OperacionPoleDisplay.class.getName()).log(Level.SEVERE, null, ex);
		}
		ClearDisplay();
	}
	public void init(){
		try{
			serialPort.writeBytes(ESCPOS.Anim);
		}
		catch (SerialPortException e) {
			System.out.println("OperacionPoleDisplay.init() "+e);
			e.printStackTrace();
		}
	}

	public void close(){
		try {
			serialPort.closePort();
			System.out.println("OperacionPoleDisplay.close() serialPort.isOpened() "+serialPort.isOpened());
		} catch (SerialPortException e) {
			System.out.println("OperacionPoleDisplay.close() "+e);
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {
		//////////////////////  THIS IS HOW USE THIS CLASS //////////////////////////
		OperacionPoleDisplay operacionPoleDisplay=new OperacionPoleDisplay();
		operacionPoleDisplay.StartDisplay();   //optimal choice is start when system start.
		operacionPoleDisplay.PrintFirstLine("  Java Open Source  ");
		operacionPoleDisplay.PrintSecondLine(" Say Hello TO World ");
		try {
			Thread.sleep(5000);     //wait 5 seconds.otherwise unable to see above outputs.   
		} catch (InterruptedException ex) {
			System.out.println("OperacionPoleDisplay.main() wait 5 seconds.otherwise unable to see above outputs");
			ex.printStackTrace();
			Logger.getLogger(OperacionPoleDisplay.class.getName()).log(Level.SEVERE, null, ex);
		}
		operacionPoleDisplay.ShowGreeting();
		operacionPoleDisplay.close();     //optimal choice is close when exit from system.


		////////////////////// JOIN TO SHARE KNOWLADGE ////////////////////////////////  

	}*/

}
