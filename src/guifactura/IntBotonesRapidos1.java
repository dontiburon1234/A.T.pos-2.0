package guifactura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.google.gson.Gson;

import clases.DatosBRArray;
import controlador.ConConfiguracion;
import controlador.ConFactura;

public class IntBotonesRapidos1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton[] botonesRapidos;
	private String[] celdasV = {"A","B","C","D","E"};
	private String[] celdasH = {"1","2","3","4","5","6","7","8","9","10"};
	private DatosBRArray datosBRArray;
	private Gson gson1 = new Gson();
	private ConFactura conFactura;
	private ConConfiguracion conConfiguracion;

	public IntBotonesRapidos1() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		String sistemaOperativo = System.getProperty("os.name").toUpperCase();
		try {
			String nombreArchivoLicencia;
			if (sistemaOperativo.indexOf("WINDOWS") != -1) {
				File dirWindows = new File("C:/tmp/lic/serv/");
				nombreArchivoLicencia = dirWindows.getAbsolutePath() + "/BotonesRapidos.bin";
			} else {
				nombreArchivoLicencia = "/var/lic/serv/BotonesRapidos.bin";
			}
			BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoLicencia));
			String currentLine = reader.readLine();
			reader.close();
			if(!currentLine.equals("")){
				datosBRArray = gson1.fromJson(currentLine, DatosBRArray.class);
			}else{
				datosBRArray = null;
			}

		} catch (Exception e) {
			datosBRArray = null;
			//e.printStackTrace();
		}		

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		botonesRapidos = new JButton[50];
		int k=0;
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 5; i++) {
				botonesRapidos[k] = new JButton("<html><CENTER>"+""+"<br/>"+celdasV[i]+celdasH[j]+"</CENTER></html>"); //("<html><CENTER>"+nombreBoton[k]+"<br/>"+celdasV[i]+celdasH[j]+"</CENTER></html>");
				botonesRapidos[k].setPreferredSize(new Dimension(70, 50));
				botonesRapidos[k].setBorder(raisedbevel);
				botonesRapidos[k].setVerticalTextPosition(AbstractButton.TOP);
				botonesRapidos[k].setForeground(new Color(0,0,0));
				botonesRapidos[k].setBackground(new Color(255,255,255));
				if(datosBRArray!=null){
					for (int l = 0; l < datosBRArray.getArrayDatosBR().size(); l++) {
						if(datosBRArray.getArrayDatosBR().get(l).getTabbed() == 1){
							if(k== datosBRArray.getArrayDatosBR().get(l).getPosicionCelda()){
								botonesRapidos[k].setBackground(new Color(datosBRArray.getArrayDatosBR().get(l).getRed(), datosBRArray.getArrayDatosBR().get(l).getGreen(),datosBRArray.getArrayDatosBR().get(l).getBlue()));
								botonesRapidos[k].setText("<html><CENTER>"+datosBRArray.getArrayDatosBR().get(l).getArticulo()+"<br/>"+datosBRArray.getArrayDatosBR().get(l).getCodigo()+"</CENTER></html>");
							}
						}
					}
				}else{
					botonesRapidos[k].setBackground(new Color(255,255,255));
				}

				botonesRapidos[k].setMargin(new Insets(-20,0, 0,0));
				botonesRapidos[k].setFont(new Font("Arial", Font.PLAIN, 10));
				GridBagConstraints gbc_btnBotonesRapidos = new GridBagConstraints();
				gbc_btnBotonesRapidos.insets = new Insets(5, 5, 5, 5);
				gbc_btnBotonesRapidos.gridx = i;
				gbc_btnBotonesRapidos.gridy = j;
				gbc_btnBotonesRapidos.fill = GridBagConstraints.BOTH;
				add(botonesRapidos[k], gbc_btnBotonesRapidos);
				k++;
			}
		}
	}

	public JButton[] getBotonesRapidos() {
		return botonesRapidos;
	}

	public void setBotonesRapidos(JButton[] botonesRapidos) {
		this.botonesRapidos = botonesRapidos;
	}

	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}

	public ConConfiguracion getConConfiguracion() {
		return conConfiguracion;
	}

	public void setConConfiguracion(ConConfiguracion conConfiguracion) {
		this.conConfiguracion = conConfiguracion;
	}


}
