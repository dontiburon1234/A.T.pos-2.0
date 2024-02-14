package guifactura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class IntPedientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> listFacturasPendientes;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JButton btnEliminar;
	private JButton btnPeso;
	
	public IntPedientes() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 100};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		listFacturasPendientes = new JList<String>(listModel);
		listFacturasPendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFacturasPendientes.setSelectedIndex(0);
		listFacturasPendientes.setFont(new Font("Tahoma",Font.PLAIN,25));
		listFacturasPendientes.setVisibleRowCount(8);
		
		JScrollPane listScrollPane = new JScrollPane(listFacturasPendientes);
		listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		
		GridBagConstraints gbc_listFacturasPendientes = new GridBagConstraints();
		gbc_listFacturasPendientes.gridwidth = 2;
		gbc_listFacturasPendientes.gridheight = 2;
		gbc_listFacturasPendientes.insets = new Insets(0, 0, 5, 5);
		gbc_listFacturasPendientes.fill = GridBagConstraints.BOTH;
		gbc_listFacturasPendientes.gridx = 0;
		gbc_listFacturasPendientes.gridy = 0;
		add(listScrollPane, gbc_listFacturasPendientes);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.ipady = 5;
		gbc_btnEliminar.ipadx = 5;
		gbc_btnEliminar.fill = GridBagConstraints.BOTH;
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 2;
		add(btnEliminar, gbc_btnEliminar);
		
		btnPeso = new JButton("Peso");
		btnPeso.setBackground(Color.CYAN);//(Color.ORANGE);
		btnPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_btnPeso = new GridBagConstraints();
		gbc_btnPeso.ipady = 5;
		gbc_btnPeso.ipadx = 5;
		gbc_btnPeso.fill = GridBagConstraints.BOTH;
		gbc_btnPeso.gridx = 0;
		gbc_btnPeso.gridy = 2;
		add(btnPeso, gbc_btnPeso);

	}

	public JList<String> getListFacturasPendientes() {
		return listFacturasPendientes;
	}

	public void setListFacturasPendientes(JList<String> listFacturasPendientes) {
		this.listFacturasPendientes = listFacturasPendientes;
	}

	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnPeso() {
		return btnPeso;
	}

	public void setBtnPeso(JButton btnPeso) {
		this.btnPeso = btnPeso;
	}
	
}
