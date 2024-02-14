package util;

import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import clases.TablaFormaPago;

public class TablaFormaPagoModelo implements TableModel, TableModelListener  {

	private LinkedList<TablaFormaPago> formaPagoList = new LinkedList<TablaFormaPago>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("TablaFormaPagoModelo.tableChanged() TableModelEvent "+e.getFirstRow());
	}

	@Override
	public int getRowCount() {
		return formaPagoList.size();
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
			titulo = "RECIBIDO";
			break;
		case 1:
			titulo = "DESCRIPCION";
			break;
		case 2:
			titulo = "Vr. BASE";
			break;
		case 3:
			titulo = "IVA";
			break;
		case 4:
			titulo = "TOTAL";
			break;
		case 5:
			titulo = "CAMBIO";
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
			return Double.class; // RECIBIDO
		case 1:
			return String.class;// DESCRIPCION
		case 2:
			return Double.class; // Vr. BASE
		case 3:
			return Double.class; // IVA
		case 4:
			return Double.class; // TOTAL
		case 5:
			return Double.class; // CAMBIO
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
		TablaFormaPago aux;

		// Se obtiene el articulo de la fila indicada
		aux = (TablaFormaPago)(formaPagoList.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		double d;
		switch (columnIndex){
		case 0:
			d = aux.getRecibido();
			return d;
		case 1:
			return aux.getDescripcion();
		case 2:
			d = aux.getValorBase();
			return d;
		case 3:
			d = aux.getIva();
			return d;
		case 4:
			d = aux.getTotal();
			return d;
		case 5:
			d = aux.getCambio();
			return d;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Obtiene la persona de la fila indicada
		TablaFormaPago aux;
		aux = (TablaFormaPago)(formaPagoList.get(rowIndex));
		
		// Cambia el campo de Persona que indica columnIndex, poniendole el aValue que se nos pasa.
		switch (columnIndex){
		case 0:
			aux.setRecibido(((Double)aValue).doubleValue());
			break;
		case 1:
			aux.setDescripcion((String) aValue);
			break;
		case 2:
			aux.setValorBase(((Double)aValue).doubleValue());
			break;
		case 3:
			aux.setIva(((Double)aValue).doubleValue());
			break;
		case 4:
			aux.setTotal(((Double)aValue).doubleValue());
		case 5:
			aux.setCambio(((Double)aValue).doubleValue());
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

	public void adicionaFormaPago(TablaFormaPago tablaFormaPago) {
		// Añade la factura pendiente al modelo 
		formaPagoList.add(tablaFormaPago);

		// Avisa a los suscriptores creando un TableModelEvent...
		TableModelEvent evento;
		evento = new TableModelEvent (this, this.getRowCount()-1, this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		// ... y avisando a los suscriptores
		avisaSuscriptores (evento);
	}

	public void borraFormaPago(int rowDelete) {
		// Se borra la fila 
		formaPagoList.remove(rowDelete);

		// Y se avisa a los suscriptores, creando un TableModelEvent...
		TableModelEvent evento = new TableModelEvent (this, rowDelete, rowDelete, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

		// ... y pasándoselo a los suscriptores
		avisaSuscriptores (evento);
	}

	private void avisaSuscriptores (TableModelEvent evento){
		// Bucle para todos los suscriptores en la lista, se llama al metodo
		// tableChanged() de los mismos, pasándole el evento.
		for (int i=0; i<listeners.size(); i++)
			((TableModelListener)listeners.get(i)).tableChanged(evento);
	}
}
