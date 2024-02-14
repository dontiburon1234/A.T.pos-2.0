package guifactura;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.ConConfiguracion;
import controlador.ConFactura;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

public class IntContenedor extends JPanel {
	private static final long serialVersionUID = 1L;
	final static String TITULO_BR1 = "Botones Rapidos 1";
	final static String TITULO_BR2 = "Botones Rapidos 2";
	final static String TITULO_BR3 = "Botones Rapidos 3";
	final static String TITULO_LC = "Lista de Compras";
	final static int extraWindowWidth = 100;
	private ConFactura conFactura;
	private ConConfiguracion conConfiguracion;
	private JTabbedPane tabbedPane;

	/**
	 * @wbp.parser.constructor
	 */
	public IntContenedor(IntBotonesRapidos1 intBotonesRapidos1,
			IntBotonesRapidos2 intBotonesRapidos2,
			IntBotonesRapidos3 intBotonesRapidos3,
			IntListaCompra intListaCompra) {

		/*ImageIcon icon1 = createImageIcon("/images/iconoConfiguracion_32x38.png");
		ImageIcon icon2 = createImageIcon("/images/iconoFactura_32x38.png");
		ImageIcon icon3 = createImageIcon("/images/iconoSalida_32x38.png");
		ImageIcon icon4 = createImageIcon("/images/iconoReporte_32x32.png");*/

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{900, 0};
		gridBagLayout.rowHeights = new int[]{760, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagLayout gridBagLayout_1 = (GridBagLayout) intBotonesRapidos1.getLayout();
		gridBagLayout_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};

		GridBagLayout gridBagLayout_2 = (GridBagLayout) intBotonesRapidos2.getLayout();
		gridBagLayout_2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};

		GridBagLayout gridBagLayout_3 = (GridBagLayout) intBotonesRapidos3.getLayout();
		gridBagLayout_3.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};

		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		int k=0;
		if(intListaCompra != null){
			//tabbedPane.addTab(TITULO_LC, icon4, intListaCompra);
			tabbedPane.addTab(TITULO_LC, null, intListaCompra);
			k=1;
		}
		
		/*tabbedPane.addTab(TITULO_BR1,icon1, intBotonesRapidos1);
		tabbedPane.addTab(TITULO_BR2,icon2, intBotonesRapidos2);
		tabbedPane.addTab(TITULO_BR3,icon3, intBotonesRapidos3);*/

		tabbedPane.addTab(TITULO_BR1,null, intBotonesRapidos1);
		tabbedPane.addTab(TITULO_BR2,null, intBotonesRapidos2);
		tabbedPane.addTab(TITULO_BR3,null, intBotonesRapidos3);

		tabbedPane.setBackgroundAt(k,Color.WHITE);
		tabbedPane.setBackgroundAt(k+1, Color.RED);
		tabbedPane.setBackgroundAt(k+2, Color.BLUE);
		//tabbedPane.setIconAt(1, new ImageIcon(getClass().getResource("/images/iconoConfiguracion_32x38.png")));
		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(tabbedPane, gbc_tabbedPane);
	}

	public IntContenedor(IntListaCompra intListaCompra) {
		/*ImageIcon icon1 = createImageIcon("/images/iconoConfiguracion_32x38.png");
		ImageIcon icon2 = createImageIcon("/images/iconoFactura_32x38.png");
		ImageIcon icon3 = createImageIcon("/images/iconoSalida_32x38.png");
		ImageIcon icon4 = createImageIcon("/images/iconoReporte_32x32.png");*/

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{800, 0};
		gridBagLayout.rowHeights = new int[]{660, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		if(intListaCompra != null){
			//tabbedPane.addTab(TITULO_LC, icon4, intListaCompra);
			tabbedPane.addTab(TITULO_LC, null, intListaCompra);
		}
		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(tabbedPane, gbc_tabbedPane);
	}
	
	public ConFactura getConFactura() {
		return conFactura;
	}

	public void setConFactura(ConFactura conFactura) {
		this.conFactura = conFactura;
	}

	public ConConfiguracion getConConfiguracion() {
		return conConfiguracion;
	}

	public void setConConfiguracion(ConConfiguracion conConfiguracion) {
		this.conConfiguracion = conConfiguracion;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = IntContenedor.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("No puede encontrar el archivo Icon: " + path);
			return null;
		}
	}
}
