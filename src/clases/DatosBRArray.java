package clases;

import java.util.ArrayList;

public class DatosBRArray {
	private ArrayList<DatosBR> arrayDatosBR;
	private String titulo1;
	private String titulo2;
	private String titulo3;
	
	public DatosBRArray(ArrayList<DatosBR> arrayDatosBR, String titulo1, String titulo2, String titulo3) {
		super();
		this.arrayDatosBR = arrayDatosBR;
		this.titulo1 = titulo1;
		this.titulo2 = titulo2;
		this.titulo3 = titulo3;
	}
	
	public ArrayList<DatosBR> getArrayDatosBR() {
		return arrayDatosBR;
	}
	public void setArrayDatosBR(ArrayList<DatosBR> arrayDatosBR) {
		this.arrayDatosBR = arrayDatosBR;
	}
	public String getTitulo1() {
		return titulo1;
	}
	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}
	public String getTitulo2() {
		return titulo2;
	}
	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}
	public String getTitulo3() {
		return titulo3;
	}
	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}
	
	
}
