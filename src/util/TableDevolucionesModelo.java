package util;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import clases.TablaDevoluciones;

public class TableDevolucionesModelo implements TableModel, TableModelListener  {

	private LinkedList<TablaDevoluciones> devolucionesList = new LinkedList<TablaDevoluciones>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

	
	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("TableDevolucionesModelo.tableChanged() "+e.getSource());
	}

	@Override
	public int getRowCount() {
		return devolucionesList.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String titulo = null;
		switch (columnIndex) {
		case 0:
			titulo = "VENDEDOR";
			break;
		case 1:
			titulo =  "CODIGO";
			break;
		case 2:
			titulo = "ARTICULO";
			break;
		case 3:
			titulo =  "UNIDADES";
			break;
		case 4:
			titulo =  "CANTIDAD";
			break;
		case 5:
			titulo = "VALOR";
			break;
		case 6:
			titulo = "DESCUENTO";
			break;
		case 7:
			titulo = "TOTAL";
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
			return Integer.class;  //vendedor
		case 1:
			return String.class; //codigo
		case 2:
			return String.class; //articulo
		case 3:
			return Integer.class; //unidades
		case 4:
			return Double.class; // cantidad
		case 5:
			return Double.class; // valorUnitario
		case 6:
			return Double.class; // descuento
		case 7:
			return Double.class; // valorTotal
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
		TablaDevoluciones aux;

		// Se obtiene el articulo de la fila indicada
		aux = (TablaDevoluciones)(devolucionesList.get(rowIndex));

		// Se obtiene el campo apropiado según el valor de columnIndex
		int i;
		double d;
		switch (columnIndex){
		case 0:
			i = aux.getVendedor();
			return i; //new Integer(aux.getVendedor());
		case 1:
			return aux.getCodigoArticulo();
		case 2:
			return aux.getNombreArticulo();
		case 3:
			i = aux.getUnidades();
			return i; //new Integer(aux.getUnidades());
		case 4:
			d = aux.getCantidad();
			return d; //new Double(aux.getCantidad());
		case 5:
			d = aux.getValorUnitario();
			return d; // new Double(aux.getValorUnitario());
		case 6:
			d = aux.getDescuento();
			return d; //new Double(aux.getDescuento());
		case 7:
			d = aux.getValorTotal();
			return d; //new Double(aux.getValorTotal());
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Obtiene la persona de la fila indicada
		TablaDevoluciones aux;
		aux = (TablaDevoluciones)(devolucionesList.get(rowIndex));

		// Cambia el campo de Persona que indica columnIndex, poniendole el aValue que se nos pasa.
		switch (columnIndex){
		case 0:
			aux.setVendedor(((Integer)aValue).intValue());
			break;
		case 1:
			aux.setCodigoArticulo((String)aValue);
			break;
		case 2:
			aux.setNombreArticulo((String)aValue);
		case 3:
			aux.setUnidades(((Integer)aValue).intValue());
			break;
		case 4:
			aux.setCantidad(((Double)aValue).doubleValue());
			break;
		case 5:
			aux.setValorUnitario(((Double)aValue).doubleValue());
			break;
		case 6:
			aux.setDescuento(((Double)aValue).doubleValue());
			break;
		case 7:
			aux.setValorTotal(((Double)aValue).doubleValue());
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

	public void adicionaArticulo(TablaDevoluciones tablaDevoluciones) {
		// Añade el sartículo al modelo 
		devolucionesList.add(tablaDevoluciones);

		// Avisa a los suscriptores creando un TableModelEvent...
		TableModelEvent evento;
		evento = new TableModelEvent (this, this.getRowCount()-1, this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		// ... y avisando a los suscriptores
		avisaSuscriptores (evento);
	}
	
	public void borraArticulo(int rowDelete) {
		try {
			// Se borra la fila 
			
			//System.out.println("TablaFacturaModelo.borraArticulo() articulosList.size() "+articulosList.size()+" rowDelete "+rowDelete+" ROWdelete ROWdelete ROWdelete ROWdelete ROWdelete ROWdelete");
			
			if(rowDelete!=-1) devolucionesList.remove(rowDelete);

			// Y se avisa a los suscriptores, creando un TableModelEvent...
			TableModelEvent evento = new TableModelEvent (this, rowDelete, rowDelete, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

			// ... y pasándoselo a los suscriptores
			avisaSuscriptores (evento);
		} catch (Exception e) {
			System.out.println("TableDevolucionesModelo.borraArticulo() ERROR rowDelete "+rowDelete+" tamnaño "+devolucionesList.size()+e);
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
