package util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import database.MaestroDB;

public class CargarLogo {
	
	private static int altoLogo;
	private static int anchoLogo;
	private static int alto = 0;
	private static MaestroDB maestroDB;
	
	public CargarLogo(MaestroDB maestroDB) {
		CargarLogo.maestroDB = maestroDB;
	}
	
	public static void logo() {
		
		String anchoPapel = maestroDB.cargarUnParametro("anchoPapel");
		G.getInstance().anchoPapel = Integer.valueOf(anchoPapel);
		
		String pasoRenglon = maestroDB.cargarUnParametro("pasoRenglon");
		G.getInstance().pasoRenglon = Integer.valueOf(pasoRenglon);
		
		String strFileLogo = G.getInstance().licenciaGlobal.getLogoPDF();
		Image imgLogo = null;
		
		try {
			imgLogo = ImageIO.read(new File(strFileLogo));
			altoLogo = imgLogo.getHeight(null);
			anchoLogo = imgLogo.getWidth(null);

			float pro =  (float) ((float)anchoLogo/(float)(G.getInstance().anchoPapel));
			float pre = (float) (altoLogo/pro);
			alto = (int)pre;

			G.getInstance().imgLogo = imgLogo;
			G.getInstance().alto = alto;
			
		} catch (IOException e) {
			System.out.println("CargarLogo.logo()  ERROR CARGAR LA IMAGEN ");
			e.printStackTrace();
		}
	}
	
}
