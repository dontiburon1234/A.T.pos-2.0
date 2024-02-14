package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;

import clases.DatosBR;
import clases.DatosBRArray;
import database.MaestroDB;
import guiconfiguracion.IntConfiguracion;
import guiconfiguracion.IntConfiguracionBotonesRapidos;
import guiconfiguracion.IntExplicacion;
import guifactura.IntBotonesRapidos1;
import guifactura.IntBotonesRapidos2;
import guifactura.IntBotonesRapidos3;
import guifactura.IntContenedor;
import guifactura.IntFactura;
import modelo.ModConfiguracion;

public class ConConfiguracion implements ActionListener, FocusListener, ChangeListener {

	private IntConfiguracion intConfiguracion;
	private IntConfiguracionBotonesRapidos intConfiguracionBotonesRapidos;
	private IntExplicacion intExplicacion;
	private IntBotonesRapidos1 intBotonesRapidos1;
	private IntBotonesRapidos2 intBotonesRapidos2;
	private IntBotonesRapidos3 intBotonesRapidos3;
	private IntContenedor intContenedor;
	private ModConfiguracion modConfiguracion;
	private IntFactura intFactura;
	private DatosBRArray datosBRArray;
	private int pocisionCelda;
	private Gson gson1 = new Gson();
	private MaestroDB maestroDB;
	private String[] celdas = {"A1","B1","C1","D1","E1","A2","B2","C2","D2","E2","A3","B3","C3","D3","E3","A4","B4","C4","D4","E4",
			"A5","B5","C5","D5","E5","A6","B6","C6","D6","E6","A7","B7","C7","D7","E7","A8","B8","C8","D8","E8","A9","B9","C9","D9","E9","A10",
			"B10","C10","D10","E10"};
	private int tableta = 0;
	private JButton[] boton = null;

	public ConConfiguracion(IntConfiguracionBotonesRapidos intConfiguracionBotonesRapidos,
			IntExplicacion intExplicacion, IntBotonesRapidos1 intBotonesRapidos1, IntBotonesRapidos2 intBotonesRapidos2,
			IntBotonesRapidos3 intBotonesRapidos3, IntContenedor intContenedor, ModConfiguracion modConfiguracion,
			IntFactura intFactura, MaestroDB maestroDB) {

		this.intConfiguracionBotonesRapidos = intConfiguracionBotonesRapidos;
		this.intExplicacion = intExplicacion;
		this.intBotonesRapidos1 = intBotonesRapidos1;
		this.intBotonesRapidos2 = intBotonesRapidos2;
		this.intBotonesRapidos3 = intBotonesRapidos3;
		this.intContenedor = intContenedor;
		this.modConfiguracion = modConfiguracion;
		this.intFactura = intFactura;
		this.maestroDB = maestroDB;

		this.intContenedor.getTabbedPane().addChangeListener(this);

		this.intConfiguracionBotonesRapidos.getTxtCodigo().addFocusListener(this);
		this.intConfiguracionBotonesRapidos.getTxtArticulo().addFocusListener(this);
		this.intConfiguracionBotonesRapidos.getComboBoxRed().addActionListener(this);
		this.intConfiguracionBotonesRapidos.getComboBoxGreen().addActionListener(this);
		this.intConfiguracionBotonesRapidos.getComboBoxBlue().addActionListener(this);
		this.intConfiguracionBotonesRapidos.getBtnGrabar().addActionListener(this);
		this.intConfiguracionBotonesRapidos.getBtnExtranjera().addActionListener(this);

		int k=0;
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 5; i++) {
				this.intBotonesRapidos1.getBotonesRapidos()[k].addActionListener(this);
				this.intBotonesRapidos2.getBotonesRapidos()[k].addActionListener(this);
				this.intBotonesRapidos3.getBotonesRapidos()[k].addActionListener(this);
				k=k+1;
			}
		}

		this.intConfiguracionBotonesRapidos.getTxtTitulo1().addFocusListener(this);
		this.intConfiguracionBotonesRapidos.getTxtTitulo2().addFocusListener(this);
		this.intConfiguracionBotonesRapidos.getTxtTitulo3().addFocusListener(this);

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

			intConfiguracionBotonesRapidos.getTxtTitulo1().setText(datosBRArray.getTitulo1());
			intConfiguracionBotonesRapidos.getTxtTitulo2().setText(datosBRArray.getTitulo2()); 
			intConfiguracionBotonesRapidos.getTxtTitulo3().setText(datosBRArray.getTitulo3());
			intContenedor.getTabbedPane().setTitleAt(0, datosBRArray.getTitulo1());
			intContenedor.getTabbedPane().setTitleAt(1, datosBRArray.getTitulo2());
			intContenedor.getTabbedPane().setTitleAt(2, datosBRArray.getTitulo3());

		} catch (Exception e) {
			datosBRArray = null;
			//e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tableta == 0){
			boton = this.intBotonesRapidos1.getBotonesRapidos();
		}else if(tableta == 1){
			boton = this.intBotonesRapidos2.getBotonesRapidos();
		}else if(tableta == 2){
			boton = this.intBotonesRapidos3.getBotonesRapidos();
		}

		for (int i = 0; i < boton.length; i++) {
			if(e.getSource()==boton[i]){
				this.intConfiguracionBotonesRapidos.getLblCelda().setText(celdas[i]);
				pocisionCelda = i;
				for (int j = 0; j < datosBRArray.getArrayDatosBR().size(); j++) {
					/*System.out.println("ConConfiguracion.actionPerformed() tableta "+tableta+
							" arrayDatosBR.get(j).getTabbed() "+datosBRArray.getArrayDatosBR().get(j).getTabbed()+
							" PosicionCelda "+datosBRArray.getArrayDatosBR().get(j).getPosicionCelda()+
							" i "+i );*/
					if((tableta+1) == datosBRArray.getArrayDatosBR().get(j).getTabbed()){
						if(datosBRArray.getArrayDatosBR().get(j).getPosicionCelda()==i){
							System.out.println("ConConfiguracion.actionPerformed() i "+i+" codigo "+datosBRArray.getArrayDatosBR().get(j).getCodigo());
							this.intConfiguracionBotonesRapidos.getTxtCodigo().setText(datosBRArray.getArrayDatosBR().get(j).getCodigo());
							this.intConfiguracionBotonesRapidos.getTxtArticulo().setText(datosBRArray.getArrayDatosBR().get(j).getArticulo());
							this.intConfiguracionBotonesRapidos.getComboBoxRed().setSelectedIndex(datosBRArray.getArrayDatosBR().get(j).getRed());
							this.intConfiguracionBotonesRapidos.getComboBoxGreen().setSelectedIndex(datosBRArray.getArrayDatosBR().get(j).getGreen());
							this.intConfiguracionBotonesRapidos.getComboBoxBlue().setSelectedIndex(datosBRArray.getArrayDatosBR().get(j).getBlue());
						}
					}
				}
			}
		}
		if(e.getSource() == this.intConfiguracionBotonesRapidos.getComboBoxRed()){
			boton[pocisionCelda].setBackground(new Color(this.intConfiguracionBotonesRapidos.getComboBoxRed().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxGreen().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxBlue().getSelectedIndex()));
		}else if(e.getSource() == this.intConfiguracionBotonesRapidos.getComboBoxGreen()){
			boton[pocisionCelda].setBackground(new Color(this.intConfiguracionBotonesRapidos.getComboBoxRed().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxGreen().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxBlue().getSelectedIndex()));
		}else if(e.getSource() == this.intConfiguracionBotonesRapidos.getComboBoxBlue()){
			boton[pocisionCelda].setBackground(new Color(this.intConfiguracionBotonesRapidos.getComboBoxRed().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxGreen().getSelectedIndex(),this.intConfiguracionBotonesRapidos.getComboBoxBlue().getSelectedIndex()));
		}

		if(e.getSource() == this.intConfiguracionBotonesRapidos.getBtnGrabar()){
			String sistemaOperativo = System.getProperty("os.name").toUpperCase();

			// Borrar los botones rápidos repetidos solo deja el último que se haya creado
			String codigo = intConfiguracionBotonesRapidos.getTxtCodigo().getText();
			for (int i = 0; i < datosBRArray.getArrayDatosBR().size() ; i++) {
				if(codigo.equals(datosBRArray.getArrayDatosBR().get(i).getCodigo())) {
					System.out.println("ConConfiguracion.actionPerformed() tableta "+(tableta+1)+" codigo "+datosBRArray.getArrayDatosBR().get(i).getCodigo());
					datosBRArray.getArrayDatosBR().remove(i);
				}
			}		

			if(intConfiguracionBotonesRapidos.getTxtCodigo().getText().length()>0) {
				DatosBR datosBR = new DatosBR((tableta+1), pocisionCelda, 
						this.intConfiguracionBotonesRapidos.getTxtCodigo().getText(), 
						this.intConfiguracionBotonesRapidos.getTxtArticulo().getText(), 
						this.intConfiguracionBotonesRapidos.getComboBoxRed().getSelectedIndex(), 
						this.intConfiguracionBotonesRapidos.getComboBoxGreen().getSelectedIndex(), 
						this.intConfiguracionBotonesRapidos.getComboBoxBlue().getSelectedIndex());
				datosBRArray.getArrayDatosBR().add(datosBR);
				datosBRArray = new DatosBRArray(datosBRArray.getArrayDatosBR(), intConfiguracionBotonesRapidos.getTxtTitulo1().getText(), 
						intConfiguracionBotonesRapidos.getTxtTitulo2().getText(), intConfiguracionBotonesRapidos.getTxtTitulo3().getText());
			} else {
				JOptionPane.showMessageDialog(null, "Artículo no guardado!","Error", JOptionPane.ERROR_MESSAGE);
			}

			String gsonArrayNuevo = gson1.toJson(datosBRArray);
			System.out.println("ConConfiguracion.actionPerformed() gsonArrayNuevo "+gsonArrayNuevo);

			try {
				String nombreArchivoBotonesRapidos;
				if (sistemaOperativo.indexOf("WINDOWS") != -1) {
					File dirWindows = new File("C:/tmp/lic/serv/");
					nombreArchivoBotonesRapidos = dirWindows.getAbsolutePath() + "/BotonesRapidos.bin";
				} else {
					nombreArchivoBotonesRapidos = "/var/lic/serv/BotonesRapidos.bin";
				}
				FileWriter writer = new FileWriter(nombreArchivoBotonesRapidos);  
				BufferedWriter buffer = new BufferedWriter(writer);
				buffer.write(gsonArrayNuevo); 
				buffer.close();  
				System.out.println("Hecho");
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		} else if(e.getSource() == this.intConfiguracionBotonesRapidos.getBtnExtranjera()){
			System.out.println("ConConfiguracion.actionPerformed() Dolar "+intConfiguracionBotonesRapidos.getTxtExtranjera().getText());
			double extranjera = Double.valueOf(intConfiguracionBotonesRapidos.getTxtExtranjera().getText());
			maestroDB.actualizaExtranjera(extranjera);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		String resultadoRapido = "";
		if(e.getSource()==this.intConfiguracionBotonesRapidos.getTxtCodigo()){
			if(!this.intConfiguracionBotonesRapidos.getTxtCodigo().getText().toString().equals("")){
				resultadoRapido = maestroDB.traeNombreArticulo(this.intConfiguracionBotonesRapidos.getTxtCodigo().getText().toString());
				if(!resultadoRapido.equals("Bad")){
					this.intConfiguracionBotonesRapidos.getTxtArticulo().setText(maestroDB.traeNombreArticulo(this.intConfiguracionBotonesRapidos.getTxtCodigo().getText().toString()));
					//System.out.println("IntConfiguracionBotonesRapidos.focusLost() txtCodigo");

					//System.out.println("ConConfiguracion.focusLost() pocisionCelda "+pocisionCelda);
					if(pocisionCelda!=0) {
						boton[pocisionCelda].setText("<html><CENTER>"+this.intConfiguracionBotonesRapidos.getTxtArticulo().getText()+"<br/>"+this.intConfiguracionBotonesRapidos.getTxtCodigo().getText()+"</CENTER></html>");
					}else {
						JOptionPane.showMessageDialog(null, "Primero seleccione el bot\u00F3n", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					intConfiguracionBotonesRapidos.getTxtCodigo().setText("");
					JOptionPane.showMessageDialog(null, "Código no encontrado","Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Código no puede ser vacio","Error",JOptionPane.ERROR_MESSAGE);
			}

		}else if(e.getSource()==this.intConfiguracionBotonesRapidos.getTxtArticulo()){
			//System.out.println("IntConfiguracionBotonesRapidos.focusLost() txtArticulo pocisionCelda "+pocisionCelda);

			if(pocisionCelda!=0) {
				boton[pocisionCelda].setText("<html><CENTER>"+this.intConfiguracionBotonesRapidos.getTxtArticulo().getText()+"<br/>"+this.intConfiguracionBotonesRapidos.getTxtCodigo().getText()+"</CENTER></html>");
			}/*else {
				JOptionPane.showMessageDialog(null, "Primero selecciones el bot\u00F3n", "Error", JOptionPane.ERROR_MESSAGE);
			}*/
		}

		if(e.getSource() == this.intConfiguracionBotonesRapidos.getTxtTitulo1() ||e.getSource() == this.intConfiguracionBotonesRapidos.getTxtTitulo2()||e.getSource() == this.intConfiguracionBotonesRapidos.getTxtTitulo3() ){
			intContenedor.getTabbedPane().setTitleAt(0, this.intConfiguracionBotonesRapidos.getTxtTitulo1().getText());
			intContenedor.getTabbedPane().setTitleAt(1, this.intConfiguracionBotonesRapidos.getTxtTitulo2().getText());
			intContenedor.getTabbedPane().setTitleAt(2, this.intConfiguracionBotonesRapidos.getTxtTitulo3().getText());
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tableta = this.intContenedor.getTabbedPane().getSelectedIndex();
		this.intConfiguracionBotonesRapidos.getTxtCodigo().setText("");
		this.intConfiguracionBotonesRapidos.getTxtArticulo().setText("");
		/*this.intConfiguracionBotonesRapidos.getComboBoxRed().setSelectedIndex(0);
		this.intConfiguracionBotonesRapidos.getComboBoxGreen().setSelectedIndex(0);
		this.intConfiguracionBotonesRapidos.getComboBoxBlue().setSelectedIndex(0);*/
		//System.out.println("ConConfiguracion.stateChanged() tableta "+tableta);
	}

	public IntConfiguracion getIntConfiguracion() {
		return intConfiguracion;
	}

	public void setIntConfiguracion(IntConfiguracion intConfiguracion) {
		this.intConfiguracion = intConfiguracion;
	}

	public IntConfiguracionBotonesRapidos getIntConfiguracionBotonesRapidos() {
		return intConfiguracionBotonesRapidos;
	}

	public void setIntConfiguracionBotonesRapidos(IntConfiguracionBotonesRapidos intConfiguracionBotonesRapidos) {
		this.intConfiguracionBotonesRapidos = intConfiguracionBotonesRapidos;
	}

	public IntExplicacion getIntExplicacion() {
		return intExplicacion;
	}

	public void setIntExplicacion(IntExplicacion intExplicacion) {
		this.intExplicacion = intExplicacion;
	}

	public IntBotonesRapidos1 getIntBotonesRapidos1() {
		return intBotonesRapidos1;
	}

	public void setIntBotonesRapidos1(IntBotonesRapidos1 intBotonesRapidos1) {
		this.intBotonesRapidos1 = intBotonesRapidos1;
	}

	public IntBotonesRapidos2 getIntBotonesRapidos2() {
		return intBotonesRapidos2;
	}

	public void setIntBotonesRapidos2(IntBotonesRapidos2 intBotonesRapidos2) {
		this.intBotonesRapidos2 = intBotonesRapidos2;
	}

	public IntBotonesRapidos3 getIntBotonesRapidos3() {
		return intBotonesRapidos3;
	}

	public void setIntBotonesRapidos3(IntBotonesRapidos3 intBotonesRapidos3) {
		this.intBotonesRapidos3 = intBotonesRapidos3;
	}

	public IntContenedor getIntContenedor() {
		return intContenedor;
	}

	public void setIntContenedor(IntContenedor intContenedor) {
		this.intContenedor = intContenedor;
	}

	public ModConfiguracion getModConfiguracion() {
		return modConfiguracion;
	}

	public void setModConfiguracion(ModConfiguracion modConfiguracion) {
		this.modConfiguracion = modConfiguracion;
	}

	public IntFactura getIntFactura() {
		return intFactura;
	}

	public void setIntFactura(IntFactura intFactura) {
		this.intFactura = intFactura;
	}
}
