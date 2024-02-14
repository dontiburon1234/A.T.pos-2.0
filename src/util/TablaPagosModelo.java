package util;

import java.util.Date;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import clases.TablaPagos;

public class TablaPagosModelo  implements TableModel, TableModelListener  {

	private LinkedList<TablaPagos> pagosList = new LinkedList<TablaPagos>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

	
	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("TablaPagosModelo.tableChanged() "+e.getSource());
	}

	@Override
	public int getRowCount() {
		return pagosList.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String titulo = null;
		switch (columnIndex) {
		case 0:
			titulo = "ID";
			break;
		case 1:
			titulo = "FECHA";
			break;
		case 2:
			titulo =  "NOMBRE";
			break;
		case 3:
			titulo = "FACTURA";
			break;
		case 4:
			titulo =  "DESCUENTO";
			break;
		case 5:
			titulo =  "PAGADO";
			break;
		default:
			titulo = "NULO";
			break;
		}
		return titulo;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// Devuelve la clase que hay en cada columna.
		switch (columnIndex) {
		case 0:
			return Integer.class; //id_pago
		case 1:
			return Date.class;  //fecha de pago
		case 2:
			return String.class; //nombre comercial
		case 3:
			return Double.class; // cantidad
		case 4:
			return Double.class; // valorUnitario
		case 5:
			return Double.class; // descuento
		default:
			// Devuelve una clase Object por defecto.
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TablaPagos aux;

		// Se obtiene el articulo de la fila indicada
		aux = (TablaPagos)(pagosList.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		double d;
		Date dFecha;
		switch (columnIndex){
		case 0:
			return aux.getId_pagos();
		case 1:
			dFecha = aux.getFecha();
			return dFecha;
		case 2:
			return aux.getNombreComercial();
		case 3:
			d = aux.getValorFactura();
			return d;
		case 4:
			d = aux.getValorDescuento();
			return d;
		case 5:
			d = aux.getValorPagado();
			return d;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Obtiene la persona de la fila indicada
		TablaPagos aux;
		aux = (TablaPagos)(pagosList.get(rowIndex));

		// Cambia el campo de Persona que indica columnIndex, poniendole el aValue que se nos pasa.
		switch (columnIndex){
		case 0:
			aux.setId_pagos((int) aValue);
		case 1:
			aux.setFecha((Date)aValue);//aux.setVendedor(((Integer)aValue).intValue());
			break;
		case 2:
			aux.setNombreComercial((String)aValue);
			break;
		case 3:
			aux.setValorFactura(((Double)aValue).doubleValue());
			break;
		case 4:
			aux.setValorDescuento(((Double)aValue).doubleValue());
			break;
		case 5:
			aux.setValorPagado(((Double)aValue).doubleValue());
			break;
		default:
			break;
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	public void adicionaPagos(TablaPagos tablaPagos) {
		// Añade el sartículo al modelo 
		pagosList.add(tablaPagos);

		// Avisa a los suscriptores creando un TableModelEvent...
		TableModelEvent evento;
		evento = new TableModelEvent (this, this.getRowCount()-1, this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		// ... y avisando a los suscriptores
		avisaSuscriptores (evento);
	}
	
	public void borraPago(int rowDelete) {
		try {
			// Se borra la fila 
			if(rowDelete!=-1) pagosList.remove(rowDelete);

			// Y se avisa a los suscriptores, creando un TableModelEvent...
			TableModelEvent evento = new TableModelEvent (this, rowDelete, rowDelete, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
			
			// ... y pasándoselo a los suscriptores
			avisaSuscriptores (evento);
		} catch (Exception e) {
			System.out.println("TablaPagosModelo.borraPago() ERROR rowDelete "+rowDelete+" tamnaño "+pagosList.size()+" "+e);
			e.printStackTrace();
		}
	}
	
	private void avisaSuscriptores (TableModelEvent evento){
		// Bucle para todos los suscriptores en la lista, se llama al metodo
		// tableChanged() de los mismos, pasándole el evento.
		for (int i=0; i<listeners.size(); i++)
			((TableModelListener)listeners.get(i)).tableChanged(evento);
	}
}
