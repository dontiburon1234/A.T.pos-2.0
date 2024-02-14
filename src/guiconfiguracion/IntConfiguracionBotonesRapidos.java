package guiconfiguracion;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import com.google.gson.Gson;

import clases.DatosBR;
import controlador.ConConfiguracion;
import database.MaestroDB;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntConfiguracionBotonesRapidos extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> comboBoxRed;
	private JComboBox<Integer> comboBoxGreen;
	private JComboBox<Integer> comboBoxBlue;
	private JButton[] botonesRapidos;
	private String[] celdasV = {"A","B","C","D","E"};
	private String[] celdasH = {"1","2","3","4","5","6","7","8","9","10"};
	private String[] celdas = {"A1","B1","C1","D1","E1","A2","B2","C2","D2","E2","A3","B3","C3","D3","E3","A4","B4","C4","D4","E4",
			"A5","B5","C5","D5","E5","A6","B6","C6","D6","E6","A7","B7","C7","D7","E7","A8","B8","C8","D8","E8","A9","B9","C9","D9","E9","A10",
			"B10","C10","D10","E10"};
	private int pocisionCelda;
	private ArrayList<DatosBR> arrayDatosBR = new ArrayList<DatosBR>();
	private DatosBR[] arrayDatosBR1;
	private Gson gson1 = new Gson();
	private MaestroDB maestroDB = new MaestroDB();
	private Integer[] rangoColor = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
			21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,
			41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
			61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,
			81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,
			101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,
			121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,
			141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,
			161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,
			181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,
			201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,
			221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,
			241,242,243,244,245,246,247,248,249,250,251,252,253,254,255};
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JLabel lblR;
	private JLabel lblG;
	private JLabel lblB;
	private JLabel lblCelda;
	private JButton btnGrabar;
	private ConConfiguracion conConfiguracion;
	private JTextField txtTitulo1;
	private JTextField txtTitulo2;
	private JTextField txtTitulo3;
	private JButton btnExtranjera;
	private JTextField txtExtranjera;

	public IntConfiguracionBotonesRapidos() {
		//setBackground(new Color(153,153,255));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{32, 26, 25, 26, 25, 26, 25, 55, 29, 29, 20, 25, 35, 68, 57, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		txtExtranjera = new JTextField();
		txtExtranjera.setText("");
		txtExtranjera.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtExtranjera = new GridBagConstraints();
		gbc_txtExtranjera.fill = GridBagConstraints.BOTH;
		gbc_txtExtranjera.insets = new Insets(0, 0, 5, 0);
		gbc_txtExtranjera.gridwidth = 3;
		gbc_txtExtranjera.gridx = 0;
		gbc_txtExtranjera.gridy = 0;
		add(txtExtranjera, gbc_txtExtranjera);
		txtExtranjera.setColumns(10);
		
		btnExtranjera = new JButton();
		btnExtranjera.setText("Dolar");
		btnExtranjera.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnExtranjera = new GridBagConstraints();
		gbc_btnExtranjera.fill = GridBagConstraints.BOTH;
		gbc_btnExtranjera.insets = new Insets(0, 0, 5, 0);
		gbc_btnExtranjera.gridwidth = 3;
		gbc_btnExtranjera.gridx = 0;
		gbc_btnExtranjera.gridy = 1;
		add(btnExtranjera, gbc_btnExtranjera);
		
		JLabel lblTable1 = new JLabel("1 Tabla");
		lblTable1.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTable1 = new GridBagConstraints();
		gbc_lblTable1.fill = GridBagConstraints.BOTH;
		gbc_lblTable1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTable1.gridwidth = 2;
		gbc_lblTable1.gridx = 0;
		gbc_lblTable1.gridy = 2;
		add(lblTable1, gbc_lblTable1);

		txtTitulo1 = new JTextField();
		txtTitulo1.setText("");
		txtTitulo1.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtTitulo1 = new GridBagConstraints();
		gbc_txtTitulo1.fill = GridBagConstraints.BOTH;
		gbc_txtTitulo1.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo1.gridwidth = 3;
		gbc_txtTitulo1.gridx = 0;
		gbc_txtTitulo1.gridy = 3;
		add(txtTitulo1, gbc_txtTitulo1);
		txtTitulo1.setColumns(10);

		JLabel lblTable2 = new JLabel("2 Tabla");
		lblTable2.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTable2 = new GridBagConstraints();
		gbc_lblTable2.fill = GridBagConstraints.BOTH;
		gbc_lblTable2.insets = new Insets(0, 0, 5, 5);
		gbc_lblTable2.gridwidth = 2;
		gbc_lblTable2.gridx = 0;
		gbc_lblTable2.gridy = 4;
		add(lblTable2, gbc_lblTable2);

		txtTitulo2 = new JTextField();
		txtTitulo2.setText("");
		txtTitulo2.setBackground(Color.GREEN);
		txtTitulo2.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtTitulo2 = new GridBagConstraints();
		gbc_txtTitulo2.fill = GridBagConstraints.BOTH;
		gbc_txtTitulo2.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo2.gridwidth = 3;
		gbc_txtTitulo2.gridx = 0;
		gbc_txtTitulo2.gridy = 5;
		add(txtTitulo2, gbc_txtTitulo2);
		txtTitulo2.setColumns(10);

		JLabel lblTable3 = new JLabel("3 Tabla");
		lblTable3.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTable3 = new GridBagConstraints();
		gbc_lblTable3.fill = GridBagConstraints.BOTH;
		gbc_lblTable3.insets = new Insets(0, 0, 5, 5);
		gbc_lblTable3.gridwidth = 2;
		gbc_lblTable3.gridx = 0;
		gbc_lblTable3.gridy = 6;
		add(lblTable3, gbc_lblTable3);

		txtTitulo3 = new JTextField();
		txtTitulo3.setText("");
		txtTitulo3.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtTitulo3 = new GridBagConstraints();
		gbc_txtTitulo3.fill = GridBagConstraints.BOTH;
		gbc_txtTitulo3.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo3.gridwidth = 3;
		gbc_txtTitulo3.gridx = 0;
		gbc_txtTitulo3.gridy = 7;
		add(txtTitulo3, gbc_txtTitulo3);
		txtTitulo3.setColumns(10);

		lblCelda = new JLabel("Celda");
		lblCelda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCelda.setFont(new Font( "arial",Font.BOLD,20));
		GridBagConstraints gbc_lblCelda = new GridBagConstraints();
		gbc_lblCelda.fill = GridBagConstraints.BOTH;
		gbc_lblCelda.insets = new Insets(0, 0, 5, 0);
		gbc_lblCelda.gridwidth = 3;
		gbc_lblCelda.gridx = 0;
		gbc_lblCelda.gridy = 8;
		add(lblCelda, gbc_lblCelda);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.fill = GridBagConstraints.BOTH;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 9;
		add(lblCodigo, gbc_lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setText("");
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.fill = GridBagConstraints.BOTH;
		gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigo.gridwidth = 2;
		gbc_txtCodigo.gridx = 1;
		gbc_txtCodigo.gridy = 9;
		add(txtCodigo, gbc_txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblArticulo = new JLabel("Articulo");
		lblArticulo.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblArticulo = new GridBagConstraints();
		gbc_lblArticulo.fill = GridBagConstraints.BOTH;
		gbc_lblArticulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticulo.gridx = 0;
		gbc_lblArticulo.gridy = 10;
		add(lblArticulo, gbc_lblArticulo);

		txtArticulo = new JTextField();
		txtArticulo.setText("");
		txtArticulo.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_txtArticulo = new GridBagConstraints();
		gbc_txtArticulo.fill = GridBagConstraints.BOTH;
		gbc_txtArticulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtArticulo.gridwidth = 3;
		gbc_txtArticulo.gridx = 0;
		gbc_txtArticulo.gridy = 11;
		add(txtArticulo, gbc_txtArticulo);
		txtArticulo.setColumns(10);

		lblR = new JLabel("Red");
		lblR.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.fill = GridBagConstraints.BOTH;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 0;
		gbc_lblR.gridy = 12;
		add(lblR, gbc_lblR);

		lblG = new JLabel("Green");
		lblG.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblG = new GridBagConstraints();
		gbc_lblG.fill = GridBagConstraints.BOTH;
		gbc_lblG.insets = new Insets(0, 0, 5, 5);
		gbc_lblG.gridx = 1;
		gbc_lblG.gridy = 12;
		add(lblG, gbc_lblG);

		lblB = new JLabel("Blue");
		lblB.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.fill = GridBagConstraints.BOTH;
		gbc_lblB.insets = new Insets(0, 0, 5, 0);
		gbc_lblB.gridx = 2;
		gbc_lblB.gridy = 12;
		add(lblB, gbc_lblB);

		comboBoxRed = new JComboBox<Integer>(rangoColor);
		comboBoxRed.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_comboBoxRed = new GridBagConstraints();
		gbc_comboBoxRed.fill = GridBagConstraints.VERTICAL;
		//gbc_comboBoxRed.gridwidth = 3;
		gbc_comboBoxRed.gridx = 0;
		gbc_comboBoxRed.gridy = 13;
		add(comboBoxRed, gbc_comboBoxRed);

		comboBoxGreen = new JComboBox<Integer>(rangoColor);
		comboBoxGreen.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_comboBoxGreen = new GridBagConstraints();
		gbc_comboBoxGreen.fill = GridBagConstraints.VERTICAL;
		//gbc_comboBoxGreen.gridwidth = 3;
		gbc_comboBoxGreen.gridx = 1;
		gbc_comboBoxGreen.gridy = 13;
		add(comboBoxGreen, gbc_comboBoxGreen);

		comboBoxBlue = new JComboBox<Integer>(rangoColor);
		comboBoxBlue.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_comboBoxBlue = new GridBagConstraints();
		gbc_comboBoxBlue.fill = GridBagConstraints.VERTICAL;
		//gbc_comboBoxBlue.gridwidth = 3;
		gbc_comboBoxBlue.gridx = 2;
		gbc_comboBoxBlue.gridy = 13;
		add(comboBoxBlue, gbc_comboBoxBlue);
		
		btnGrabar = new JButton("Grabar");
		btnGrabar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_btnGrabar = new GridBagConstraints();
		gbc_btnGrabar.fill = GridBagConstraints.BOTH;
		gbc_btnGrabar.gridwidth = 3;
		gbc_btnGrabar.gridx = 0;
		gbc_btnGrabar.gridy = 14;
		add(btnGrabar, gbc_btnGrabar);


	}

	public ConConfiguracion getConConfiguracion() {
		return conConfiguracion;
	}

	public void setConConfiguracion(ConConfiguracion conConfiguracion) {
		this.conConfiguracion = conConfiguracion;
	}

	public JComboBox<Integer> getComboBoxRed() {
		return comboBoxRed;
	}

	public void setComboBoxRed(JComboBox<Integer> comboBoxRed) {
		this.comboBoxRed = comboBoxRed;
	}

	public JComboBox<Integer> getComboBoxGreen() {
		return comboBoxGreen;
	}

	public void setComboBoxGreen(JComboBox<Integer> comboBoxGreen) {
		this.comboBoxGreen = comboBoxGreen;
	}

	public JComboBox<Integer> getComboBoxBlue() {
		return comboBoxBlue;
	}

	public void setComboBoxBlue(JComboBox<Integer> comboBoxBlue) {
		this.comboBoxBlue = comboBoxBlue;
	}

	public JButton[] getBotonesRapidos() {
		return botonesRapidos;
	}

	public void setBotonesRapidos(JButton[] botonesRapidos) {
		this.botonesRapidos = botonesRapidos;
	}

	public String[] getCeldasV() {
		return celdasV;
	}

	public void setCeldasV(String[] celdasV) {
		this.celdasV = celdasV;
	}

	public String[] getCeldasH() {
		return celdasH;
	}

	public void setCeldasH(String[] celdasH) {
		this.celdasH = celdasH;
	}

	public String[] getCeldas() {
		return celdas;
	}

	public void setCeldas(String[] celdas) {
		this.celdas = celdas;
	}

	public int getPocisionCelda() {
		return pocisionCelda;
	}

	public void setPocisionCelda(int pocisionCelda) {
		this.pocisionCelda = pocisionCelda;
	}

	public ArrayList<DatosBR> getArrayDatosBR() {
		return arrayDatosBR;
	}

	public void setArrayDatosBR(ArrayList<DatosBR> arrayDatosBR) {
		this.arrayDatosBR = arrayDatosBR;
	}

	public DatosBR[] getArrayDatosBR1() {
		return arrayDatosBR1;
	}

	public void setArrayDatosBR1(DatosBR[] arrayDatosBR1) {
		this.arrayDatosBR1 = arrayDatosBR1;
	}

	public Gson getGson1() {
		return gson1;
	}

	public void setGson1(Gson gson1) {
		this.gson1 = gson1;
	}

	public MaestroDB getMaestroDB() {
		return maestroDB;
	}

	public void setMaestroDB(MaestroDB maestroDB) {
		this.maestroDB = maestroDB;
	}

	public Integer[] getRangoColor() {
		return rangoColor;
	}

	public void setRangoColor(Integer[] rangoColor) {
		this.rangoColor = rangoColor;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtArticulo() {
		return txtArticulo;
	}

	public void setTxtArticulo(JTextField txtArticulo) {
		this.txtArticulo = txtArticulo;
	}

	public JLabel getLblR() {
		return lblR;
	}

	public void setLblR(JLabel lblR) {
		this.lblR = lblR;
	}

	public JLabel getLblG() {
		return lblG;
	}

	public void setLblG(JLabel lblG) {
		this.lblG = lblG;
	}

	public JLabel getLblB() {
		return lblB;
	}

	public void setLblB(JLabel lblB) {
		this.lblB = lblB;
	}

	public JLabel getLblCelda() {
		return lblCelda;
	}

	public void setLblCelda(JLabel lblCelda) {
		this.lblCelda = lblCelda;
	}

	public JButton getBtnGrabar() {
		return btnGrabar;
	}

	public void setBtnGrabar(JButton btnGrabar) {
		this.btnGrabar = btnGrabar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getTxtTitulo1() {
		return txtTitulo1;
	}

	public void setTxtTitulo1(JTextField txtTitulo1) {
		this.txtTitulo1 = txtTitulo1;
	}

	public JTextField getTxtTitulo2() {
		return txtTitulo2;
	}

	public void setTxtTitulo2(JTextField txtTitulo2) {
		this.txtTitulo2 = txtTitulo2;
	}

	public JTextField getTxtTitulo3() {
		return txtTitulo3;
	}

	public void setTxtTitulo3(JTextField txtTitulo3) {
		this.txtTitulo3 = txtTitulo3;
	}

	public JButton getBtnExtranjera() {
		return btnExtranjera;
	}

	public void setBtnExtranjera(JButton btnExtranjera) {
		this.btnExtranjera = btnExtranjera;
	}

	public JTextField getTxtExtranjera() {
		return txtExtranjera;
	}

	public void setTxtExtranjera(JTextField txtExtranjera) {
		this.txtExtranjera = txtExtranjera;
	}
	
}
