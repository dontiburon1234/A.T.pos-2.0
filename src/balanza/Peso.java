package balanza;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import guifactura.IntCapturaArticulo;
import main.OperacionesPuerto;
import util.G;
import util.UtilNumero;

public class Peso {
	
	private boolean blnFinalizaCapturaPuerto = false;
	private boolean blnCapturaPeso = true;
	private boolean blnAceptaCantidadPeso = true;
	private boolean blnCapturaCantidad = false;
	
	public Peso() {
		super();
	}

	public void pesoBalanza(int id_unidad_medida, IntCapturaArticulo intCapturaArticulo) {

		OperacionesPuerto operacionesPuerto;
		
		// Determina si captura peso
		if (id_unidad_medida == 2) { // el id 2 corresponde a kilogramo Captura peso
			if (blnCapturaPeso == true) {
				System.out.println("Peso.pesoBalanza() Captura peso");
				intCapturaArticulo.getTxtCantidad().setText("0");
				// Establece parametros para traer el peso de la bascula
				JSONObject joParametroBascula = new JSONObject();
				joParametroBascula.put("puerto", G.getInstance().licenciaGlobal.getPuertoBascula());
				operacionesPuerto = new OperacionesPuerto(joParametroBascula, "leerPuerto");
				blnFinalizaCapturaPuerto = false;
				while (!blnFinalizaCapturaPuerto) {
					JSONObject joResPuerto = operacionesPuerto.registrarOperacion();
					if ((boolean) joResPuerto.get("resultado")) {
						try {
							// Convierte la lectura del puerto en un double y lo asigna a cantidad
							double dblPeso = UtilNumero.redondearDecimal(Double.parseDouble((String) joResPuerto.get("data")), 3);
							intCapturaArticulo.getTxtCantidad().setText(Double.toString(dblPeso));
							System.out.println("Peso.pesoBalanza() lpq - Leyendo puerto: \nPeso: " + dblPeso + "\n...");
							break;
						} catch (Exception e) {
							System.out.println("Peso.pesoBalanza() lpq - peso erroneo "+e);
							e.printStackTrace();
						}
						blnAceptaCantidadPeso = false;
					} else {
						JOptionPane.showMessageDialog(null, joResPuerto.get("mensaje"));
						intCapturaArticulo.getTxtCantidad().setEditable(true);
						intCapturaArticulo.getTxtCantidad().setText("");
						intCapturaArticulo.getTxtCantidad().requestFocus();
						return;
					}
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						System.out.println("Peso.pesoBalanza()Thread.sleep(2000) "+e);
						//e.printStackTrace();
					}
				}
			} else {
				blnAceptaCantidadPeso = true;
			}
			intCapturaArticulo.getTxtCantidad().requestFocus();
		} else { // en este caso no aplica porque el usuario localizado en txtCantidad solicita la cantidad a la balanza
			if (blnCapturaCantidad) {
				intCapturaArticulo.getTxtCantidad().requestFocus();
			} else {
				//jbGuardarItem.doClick();
			}
		}
	}
	
	
}
