package gui;

import javax.swing.JPanel;
import javax.swing.border.Border;

import controlador.ConLogin;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntKeyLetras extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConLogin conLogin;
	private Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	private static final int pady = 20;
	private static final int padx = 45;
	private static final int tLetraText = 20;
	private Color colorLetras = Color.decode("#FFCB00");
	private JButton btnQ;
	private JButton btnW;
	private JButton btnE;
	private JButton btnR;
	private JButton btnT;
	private JButton btnY;
	private JButton btnU;
	private JButton btnI;
	private JButton btnO;
	private JButton btnP;
	private JButton btnA;
	private JButton btnS;
	private JButton btnD;
	private JButton btnF;
	private JButton btnG;
	private JButton btnH;
	private JButton btnJ;
	private JButton btnK;
	private JButton btnL;
	private JButton btnEnne;
	private JButton btnZ;
	private JButton btnEspacio;
	private JButton btnX;
	private JButton btnC;
	private JButton btnV;
	private JButton btnB;
	private JButton btnN;
	private JButton btnM;
	private JButton btnPunto;
	private JButton btnGuionMedio;
	private JButton btnGuionBajo;
	
	public IntKeyLetras() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnQ = new JButton("Q");
		btnQ.setBackground(colorLetras);
		btnQ.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnQ.setBorder(raisedbevel);
		GridBagConstraints gbc_btnQ = new GridBagConstraints();
		gbc_btnQ.insets = new Insets(0, 5, 5, 5);
		gbc_btnQ.gridx = 0;
		gbc_btnQ.gridy = 0;
		gbc_btnQ.ipadx = padx;
		gbc_btnQ.ipady = pady;
		gbc_btnQ.fill = GridBagConstraints.BOTH;
		add(btnQ, gbc_btnQ);
		
		btnW = new JButton("W");
		btnW.setBackground(colorLetras);
		btnW.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnW.setBorder(raisedbevel);
		GridBagConstraints gbc_btnW = new GridBagConstraints();
		gbc_btnW.insets = new Insets(0, 0, 5, 5);
		gbc_btnW.gridx = 1;
		gbc_btnW.gridy = 0;
		gbc_btnW.ipadx = padx;
		gbc_btnW.ipady = pady;
		gbc_btnW.fill = GridBagConstraints.BOTH;
		add(btnW, gbc_btnW);
		
		btnE = new JButton("E");
		btnE.setBackground(colorLetras);
		btnE.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnE.setBorder(raisedbevel);
		GridBagConstraints gbc_btnE = new GridBagConstraints();
		gbc_btnE.insets = new Insets(0, 0, 5, 5);
		gbc_btnE.gridx = 2;
		gbc_btnE.gridy = 0;
		gbc_btnE.ipadx = padx;
		gbc_btnE.ipady = pady;
		gbc_btnE.fill = GridBagConstraints.BOTH;
		add(btnE, gbc_btnE);
		
		btnR = new JButton("R");
		btnR.setBackground(colorLetras);
		btnR.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnR.setBorder(raisedbevel);
		GridBagConstraints gbc_btnR = new GridBagConstraints();
		gbc_btnR.insets = new Insets(0, 0, 5, 5);
		gbc_btnR.gridx = 3;
		gbc_btnR.gridy = 0;
		gbc_btnR.ipadx = padx;
		gbc_btnR.ipady = pady;
		gbc_btnR.fill = GridBagConstraints.BOTH;
		add(btnR, gbc_btnR);
		
		btnT = new JButton("T");
		btnT.setBackground(colorLetras);
		btnT.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnT.setBorder(raisedbevel);
		GridBagConstraints gbc_btnT = new GridBagConstraints();
		gbc_btnT.insets = new Insets(0, 0, 5, 5);
		gbc_btnT.gridx = 4;
		gbc_btnT.gridy = 0;
		gbc_btnT.ipadx = padx;
		gbc_btnT.ipady = pady;
		gbc_btnT.fill = GridBagConstraints.BOTH;
		add(btnT, gbc_btnT);
		
		btnY = new JButton("Y");
		btnY.setBackground(colorLetras);
		btnY.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnY.setBorder(raisedbevel);
		GridBagConstraints gbc_btnY = new GridBagConstraints();
		gbc_btnY.insets = new Insets(0, 0, 5, 5);
		gbc_btnY.gridx = 5;
		gbc_btnY.gridy = 0;
		gbc_btnY.ipadx = padx;
		gbc_btnY.ipady = pady;
		gbc_btnY.fill = GridBagConstraints.BOTH;
		add(btnY, gbc_btnY);
		
		btnU = new JButton("U");
		btnU.setBackground(colorLetras);
		btnU.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnU.setBorder(raisedbevel);
		GridBagConstraints gbc_btnU = new GridBagConstraints();
		gbc_btnU.insets = new Insets(0, 0, 5, 5);
		gbc_btnU.gridx = 6;
		gbc_btnU.gridy = 0;
		gbc_btnU.ipadx = padx;
		gbc_btnU.ipady = pady;
		gbc_btnU.fill = GridBagConstraints.BOTH;
		add(btnU, gbc_btnU);
		
		btnI = new JButton("I");
		btnI.setBackground(colorLetras);
		btnI.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnI.setBorder(raisedbevel);
		GridBagConstraints gbc_btnI = new GridBagConstraints();
		gbc_btnI.insets = new Insets(0, 0, 5, 5);
		gbc_btnI.gridx = 7;
		gbc_btnI.gridy = 0;
		gbc_btnI.ipadx = padx;
		gbc_btnI.ipady = pady;
		gbc_btnI.fill = GridBagConstraints.BOTH;
		add(btnI, gbc_btnI);
		
		btnO = new JButton("O");
		btnO.setBackground(colorLetras);
		btnO.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnO.setBorder(raisedbevel);
		GridBagConstraints gbc_btnO = new GridBagConstraints();
		gbc_btnO.insets = new Insets(0, 0, 5, 5);
		gbc_btnO.gridx = 8;
		gbc_btnO.gridy = 0;
		gbc_btnO.ipadx = padx;
		gbc_btnO.ipady = pady;
		gbc_btnO.fill = GridBagConstraints.BOTH;
		add(btnO, gbc_btnO);
		
		btnP = new JButton("P");
		btnP.setBackground(colorLetras);
		btnP.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnP.setBorder(raisedbevel);
		GridBagConstraints gbc_btnP = new GridBagConstraints();
		gbc_btnP.insets = new Insets(0, 0, 5, 5);
		gbc_btnP.gridx = 9;
		gbc_btnP.gridy = 0;
		gbc_btnP.ipadx = padx;
		gbc_btnP.ipady = pady;
		gbc_btnP.fill = GridBagConstraints.BOTH;
		add(btnP, gbc_btnP);
		
		btnA = new JButton("A");
		btnA.setBackground(colorLetras);
		btnA.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnA.setBorder(raisedbevel);
		GridBagConstraints gbc_btnA = new GridBagConstraints();
		gbc_btnA.insets = new Insets(0, 5, 5, 5);
		gbc_btnA.gridx = 0;
		gbc_btnA.gridy = 1;
		gbc_btnA.ipadx = padx;
		gbc_btnA.ipady = pady;
		gbc_btnA.fill = GridBagConstraints.BOTH;
		add(btnA, gbc_btnA);
		
		btnS = new JButton("S");
		btnS.setBackground(colorLetras);
		btnS.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnS.setBorder(raisedbevel);
		GridBagConstraints gbc_btnS = new GridBagConstraints();
		gbc_btnS.insets = new Insets(0, 0, 5, 5);
		gbc_btnS.gridx = 1;
		gbc_btnS.gridy = 1;
		gbc_btnS.ipadx = padx;
		gbc_btnS.ipady = pady;
		gbc_btnS.fill = GridBagConstraints.BOTH;
		add(btnS, gbc_btnS);
		
		btnD = new JButton("D");
		btnD.setBackground(colorLetras);
		btnD.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnD.setBorder(raisedbevel);
		GridBagConstraints gbc_btnD = new GridBagConstraints();
		gbc_btnD.insets = new Insets(0, 0, 5, 5);
		gbc_btnD.gridx = 2;
		gbc_btnD.gridy = 1;
		gbc_btnD.ipadx = padx;
		gbc_btnD.ipady = pady;
		gbc_btnD.fill = GridBagConstraints.BOTH;
		add(btnD, gbc_btnD);
		
		btnF = new JButton("F");
		btnF.setBackground(colorLetras);
		btnF.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnF.setBorder(raisedbevel);
		GridBagConstraints gbc_btnF = new GridBagConstraints();
		gbc_btnF.insets = new Insets(0, 0, 5, 5);
		gbc_btnF.gridx = 3;
		gbc_btnF.gridy = 1;
		gbc_btnF.ipadx = padx;
		gbc_btnF.ipady = pady;
		gbc_btnF.fill = GridBagConstraints.BOTH;
		add(btnF, gbc_btnF);
		
		btnG = new JButton("G");
		btnG.setBackground(colorLetras);
		btnG.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnG.setBorder(raisedbevel);
		GridBagConstraints gbc_btnG = new GridBagConstraints();
		gbc_btnG.insets = new Insets(0, 0, 5, 5);
		gbc_btnG.gridx = 4;
		gbc_btnG.gridy = 1;
		gbc_btnG.ipadx = padx;
		gbc_btnG.ipady = pady;
		gbc_btnG.fill = GridBagConstraints.BOTH;
		add(btnG, gbc_btnG);
		
		btnH = new JButton("H");
		btnH.setBackground(colorLetras);
		btnH.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnH.setBorder(raisedbevel);
		GridBagConstraints gbc_btnH = new GridBagConstraints();
		gbc_btnH.insets = new Insets(0, 0, 5, 5);
		gbc_btnH.gridx = 5;
		gbc_btnH.gridy = 1;
		gbc_btnH.ipadx = padx;
		gbc_btnH.ipady = pady;
		gbc_btnH.fill = GridBagConstraints.BOTH;
		add(btnH, gbc_btnH);
		
		btnJ = new JButton("J");
		btnJ.setBackground(colorLetras);
		btnJ.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnJ.setBorder(raisedbevel);
		GridBagConstraints gbc_btnJ = new GridBagConstraints();
		gbc_btnJ.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ.gridx = 6;
		gbc_btnJ.gridy = 1;
		gbc_btnJ.ipadx = padx;
		gbc_btnJ.ipady = pady;
		gbc_btnJ.fill = GridBagConstraints.BOTH;
		add(btnJ, gbc_btnJ);
		
		btnK = new JButton("K");
		btnK.setBackground(colorLetras);
		btnK.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnK.setBorder(raisedbevel);
		GridBagConstraints gbc_btnK = new GridBagConstraints();
		gbc_btnK.insets = new Insets(0, 0, 5, 5);
		gbc_btnK.gridx = 7;
		gbc_btnK.gridy = 1;
		gbc_btnK.ipadx = padx;
		gbc_btnK.ipady = pady;
		gbc_btnK.fill = GridBagConstraints.BOTH;
		add(btnK, gbc_btnK);
		
		btnL = new JButton("L");
		btnL.setBackground(colorLetras);
		btnL.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnL.setBorder(raisedbevel);
		GridBagConstraints gbc_btnL = new GridBagConstraints();
		gbc_btnL.insets = new Insets(0, 0, 5, 5);
		gbc_btnL.gridx = 8;
		gbc_btnL.gridy = 1;
		gbc_btnL.ipadx = padx;
		gbc_btnL.ipady = pady;
		gbc_btnL.fill = GridBagConstraints.BOTH;
		add(btnL, gbc_btnL);
		
		btnEnne = new JButton("\u00D1");
		btnEnne.setBackground(colorLetras);
		btnEnne.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnEnne.setBorder(raisedbevel);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 9;
		gbc_button.gridy = 1;
		gbc_button.ipadx = padx;
		gbc_button.ipady = pady;
		gbc_button.fill = GridBagConstraints.BOTH;
		add(btnEnne, gbc_button);
		
		btnZ = new JButton("Z");
		btnZ.setBackground(colorLetras);
		btnZ.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnZ.setBorder(raisedbevel);
		GridBagConstraints gbc_btnZ = new GridBagConstraints();
		gbc_btnZ.insets = new Insets(0, 5, 5, 5);
		gbc_btnZ.gridx = 0;
		gbc_btnZ.gridy = 2;
		gbc_btnZ.ipadx = padx;
		gbc_btnZ.ipady = pady;
		gbc_btnZ.fill = GridBagConstraints.BOTH;
		add(btnZ, gbc_btnZ);
		
		btnEspacio = new JButton("Espacio");
		btnEspacio.setBackground(colorLetras);
		btnEspacio.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnEspacio.setBorder(raisedbevel);
		GridBagConstraints gbc_btnEspacio = new GridBagConstraints();
		gbc_btnEspacio.gridwidth = 10;
		gbc_btnEspacio.insets = new Insets(0, 5, 5, 5);
		gbc_btnEspacio.gridx = 0;
		gbc_btnEspacio.gridy = 3;
		gbc_btnEspacio.ipadx = padx;
		gbc_btnEspacio.ipady = pady;
		gbc_btnEspacio.fill = GridBagConstraints.BOTH;
		add(btnEspacio, gbc_btnEspacio);
		
		btnX = new JButton("X");
		btnX.setBackground(colorLetras);
		btnX.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnX.setBorder(raisedbevel);
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.insets = new Insets(0, 0, 5, 5);
		gbc_btnX.gridx = 1;
		gbc_btnX.gridy = 2;
		gbc_btnX.ipadx = padx;
		gbc_btnX.ipady = pady;
		gbc_btnX.fill = GridBagConstraints.BOTH;
		add(btnX, gbc_btnX);
		
		btnC = new JButton("C");
		btnC.setBackground(colorLetras);
		btnC.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnC.setBorder(raisedbevel);
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.insets = new Insets(0, 0, 5, 5);
		gbc_btnC.gridx = 2;
		gbc_btnC.gridy = 2;
		gbc_btnC.ipadx = padx;
		gbc_btnC.ipady = pady;
		gbc_btnC.fill = GridBagConstraints.BOTH;
		add(btnC, gbc_btnC);
		
		btnV = new JButton("V");
		btnV.setBackground(colorLetras);
		btnV.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnV.setBorder(raisedbevel);
		GridBagConstraints gbc_btnV = new GridBagConstraints();
		gbc_btnV.insets = new Insets(0, 0, 5, 5);
		gbc_btnV.gridx = 3;
		gbc_btnV.gridy = 2;
		gbc_btnV.ipadx = padx;
		gbc_btnV.ipady = pady;
		gbc_btnV.fill = GridBagConstraints.BOTH;
		add(btnV, gbc_btnV);
		
		btnB = new JButton("B");
		btnB.setBackground(colorLetras);
		btnB.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnB.setBorder(raisedbevel);
		GridBagConstraints gbc_btnB = new GridBagConstraints();
		gbc_btnB.insets = new Insets(0, 0, 5, 5);
		gbc_btnB.gridx = 4;
		gbc_btnB.gridy = 2;
		gbc_btnB.ipadx = padx;
		gbc_btnB.ipady = pady;
		gbc_btnB.fill = GridBagConstraints.BOTH;
		add(btnB, gbc_btnB);
		
		btnN = new JButton("N");
		btnN.setBackground(colorLetras);
		btnN.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnN.setBorder(raisedbevel);
		GridBagConstraints gbc_btnN = new GridBagConstraints();
		gbc_btnN.insets = new Insets(0, 0, 5, 5);
		gbc_btnN.gridx = 5;
		gbc_btnN.gridy = 2;
		gbc_btnN.ipadx = padx;
		gbc_btnN.ipady = pady;
		gbc_btnN.fill = GridBagConstraints.BOTH;
		add(btnN, gbc_btnN);
		
		btnM = new JButton("M");
		btnM.setBackground(colorLetras);
		btnM.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnM.setBorder(raisedbevel);
		GridBagConstraints gbc_btnM = new GridBagConstraints();
		gbc_btnM.insets = new Insets(0, 0, 5, 5);
		gbc_btnM.gridx = 6;
		gbc_btnM.gridy = 2;
		gbc_btnM.ipadx = padx;
		gbc_btnM.ipady = pady;
		gbc_btnM.fill = GridBagConstraints.BOTH;
		add(btnM, gbc_btnM);
		
		btnPunto = new JButton(".");
		btnPunto.setBackground(colorLetras);
		btnPunto.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnPunto.setBorder(raisedbevel);
		GridBagConstraints gbc_btnPunto = new GridBagConstraints();
		gbc_btnPunto.insets = new Insets(0, 0, 5, 5);
		gbc_btnPunto.gridx = 7;
		gbc_btnPunto.gridy = 2;
		gbc_btnPunto.ipadx = padx;
		gbc_btnPunto.ipady = pady;
		gbc_btnPunto.fill = GridBagConstraints.BOTH;
		add(btnPunto, gbc_btnPunto);
		
		btnGuionMedio = new JButton("-");
		btnGuionMedio.setBackground(colorLetras);
		btnGuionMedio.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnGuionMedio.setBorder(raisedbevel);
		GridBagConstraints gbc_btnGuionMedio = new GridBagConstraints();
		gbc_btnGuionMedio.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuionMedio.gridx = 8;
		gbc_btnGuionMedio.gridy = 2;
		gbc_btnGuionMedio.ipadx = padx;
		gbc_btnGuionMedio.ipady = pady;
		gbc_btnGuionMedio.fill = GridBagConstraints.BOTH;
		add(btnGuionMedio, gbc_btnGuionMedio);
		
		btnGuionBajo = new JButton("_");
		btnGuionBajo.setBackground(colorLetras);
		btnGuionBajo.setFont(new Font("Tahoma", Font.BOLD, tLetraText));
		btnGuionBajo.setBorder(raisedbevel);
		GridBagConstraints gbc_btnGuionBajo = new GridBagConstraints();
		gbc_btnGuionBajo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuionBajo.gridx = 9;
		gbc_btnGuionBajo.gridy = 2;
		gbc_btnGuionBajo.ipadx = padx;
		gbc_btnGuionBajo.ipady = pady;
		gbc_btnGuionBajo.fill = GridBagConstraints.BOTH;
		add(btnGuionBajo, gbc_btnGuionBajo);
	}

	public ConLogin getConLogin() {
		return conLogin;
	}

	public void setConLogin(ConLogin conLogin) {
		this.conLogin = conLogin;
	}

	public JButton getBtnQ() {
		return btnQ;
	}

	public void setBtnQ(JButton btnQ) {
		this.btnQ = btnQ;
	}

	public JButton getBtnW() {
		return btnW;
	}

	public void setBtnW(JButton btnW) {
		this.btnW = btnW;
	}

	public JButton getBtnE() {
		return btnE;
	}

	public void setBtnE(JButton btnE) {
		this.btnE = btnE;
	}

	public JButton getBtnR() {
		return btnR;
	}

	public void setBtnR(JButton btnR) {
		this.btnR = btnR;
	}

	public JButton getBtnT() {
		return btnT;
	}

	public void setBtnT(JButton btnT) {
		this.btnT = btnT;
	}

	public JButton getBtnY() {
		return btnY;
	}

	public void setBtnY(JButton btnY) {
		this.btnY = btnY;
	}

	public JButton getBtnU() {
		return btnU;
	}

	public void setBtnU(JButton btnU) {
		this.btnU = btnU;
	}

	public JButton getBtnI() {
		return btnI;
	}

	public void setBtnI(JButton btnI) {
		this.btnI = btnI;
	}

	public JButton getBtnO() {
		return btnO;
	}

	public void setBtnO(JButton btnO) {
		this.btnO = btnO;
	}

	public JButton getBtnP() {
		return btnP;
	}

	public void setBtnP(JButton btnP) {
		this.btnP = btnP;
	}

	public JButton getBtnA() {
		return btnA;
	}

	public void setBtnA(JButton btnA) {
		this.btnA = btnA;
	}

	public JButton getBtnS() {
		return btnS;
	}

	public void setBtnS(JButton btnS) {
		this.btnS = btnS;
	}

	public JButton getBtnD() {
		return btnD;
	}

	public void setBtnD(JButton btnD) {
		this.btnD = btnD;
	}

	public JButton getBtnF() {
		return btnF;
	}

	public void setBtnF(JButton btnF) {
		this.btnF = btnF;
	}

	public JButton getBtnG() {
		return btnG;
	}

	public void setBtnG(JButton btnG) {
		this.btnG = btnG;
	}

	public JButton getBtnH() {
		return btnH;
	}

	public void setBtnH(JButton btnH) {
		this.btnH = btnH;
	}

	public JButton getBtnJ() {
		return btnJ;
	}

	public void setBtnJ(JButton btnJ) {
		this.btnJ = btnJ;
	}

	public JButton getBtnK() {
		return btnK;
	}

	public void setBtnK(JButton btnK) {
		this.btnK = btnK;
	}

	public JButton getBtnL() {
		return btnL;
	}

	public void setBtnL(JButton btnL) {
		this.btnL = btnL;
	}

	public JButton getBtnEnne() {
		return btnEnne;
	}

	public void setBtnEnne(JButton btnEnne) {
		this.btnEnne = btnEnne;
	}

	public JButton getBtnZ() {
		return btnZ;
	}

	public void setBtnZ(JButton btnZ) {
		this.btnZ = btnZ;
	}

	public JButton getBtnX() {
		return btnX;
	}

	public void setBtnX(JButton btnX) {
		this.btnX = btnX;
	}

	public JButton getBtnC() {
		return btnC;
	}

	public void setBtnC(JButton btnC) {
		this.btnC = btnC;
	}

	public JButton getBtnV() {
		return btnV;
	}

	public void setBtnV(JButton btnV) {
		this.btnV = btnV;
	}

	public JButton getBtnB() {
		return btnB;
	}

	public void setBtnB(JButton btnB) {
		this.btnB = btnB;
	}

	public JButton getBtnN() {
		return btnN;
	}

	public void setBtnN(JButton btnN) {
		this.btnN = btnN;
	}

	public JButton getBtnM() {
		return btnM;
	}

	public void setBtnM(JButton btnM) {
		this.btnM = btnM;
	}

	public JButton getBtnPunto() {
		return btnPunto;
	}

	public void setBtnPunto(JButton btnPunto) {
		this.btnPunto = btnPunto;
	}

	public JButton getBtnQuion() {
		return btnGuionMedio;
	}

	public void setBtnQuion(JButton btnQuion) {
		this.btnGuionMedio = btnQuion;
	}

	public JButton getBtnPor() {
		return btnGuionBajo;
	}

	public void setBtnPor(JButton btnPor) {
		this.btnGuionBajo = btnPor;
	}

	public JButton getBtnEspacio() {
		return btnEspacio;
	}

	public void setBtnEspacio(JButton btnEspacio) {
		this.btnEspacio = btnEspacio;
	}

	public JButton getBtnGuionMedio() {
		return btnGuionMedio;
	}

	public void setBtnGuionMedio(JButton btnGuionMedio) {
		this.btnGuionMedio = btnGuionMedio;
	}

	public JButton getBtnGuionBajo() {
		return btnGuionBajo;
	}

	public void setBtnGuionBajo(JButton btnGuionBajo) {
		this.btnGuionBajo = btnGuionBajo;
	}

	
	
}
