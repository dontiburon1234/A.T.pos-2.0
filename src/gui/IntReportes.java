package gui;

import javax.swing.JPanel;

import controlador.ConReportes;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class IntReportes extends JPanel {

	private static final long serialVersionUID = 1L;

	private ConReportes conReportes;

	private int tLetra = 14;
	private JRadioButton rdbtnReporteParcialX;
	private JRadioButton rdbtnReporteFinalZ;
	private JButton btnImprimir;
	private JButton btnResumen;
	private JTextArea taListadoReporte;
	private JTextArea taListadoResumen;
	
	private JComboBox<String> jcbAnnoDesde;
	private JComboBox<String> jcbMesDesde;
	private JComboBox<String> jcbDiaDesde;

	private JComboBox<String> jcbAnnoHasta;
	private JComboBox<String> jcbMesHasta;
	private JComboBox<String> jcbDiaHasta;

	public IntReportes() {
		setLayout(null);

		rdbtnReporteParcialX = new JRadioButton("Reporte parcial X");
		rdbtnReporteParcialX.setBounds(56, 164, 164, 23);
		rdbtnReporteParcialX.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(rdbtnReporteParcialX);

		rdbtnReporteFinalZ = new JRadioButton("Reporte final Z");
		rdbtnReporteFinalZ.setBounds(56, 198, 125, 23);
		rdbtnReporteFinalZ.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(rdbtnReporteFinalZ);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnReporteParcialX);
		group.add(rdbtnReporteFinalZ);
		
		Border paneEdge = BorderFactory.createLineBorder(Color.GRAY);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(56, 263, 150, 50);
		btnImprimir.setBorder(paneEdge);
		btnImprimir.setPreferredSize(new Dimension(150,50));
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnImprimir);
		
		btnResumen = new JButton("Resumen");
		btnResumen.setBounds(680, 263, 150, 50);
		btnResumen.setBorder(paneEdge);
		btnResumen.setPreferredSize(new Dimension(150,50));
		btnResumen.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(btnResumen);

		taListadoReporte = new JTextArea("");
		taListadoReporte.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		taListadoReporte.setLineWrap(false);
		taListadoReporte.setWrapStyleWord(false);
		taListadoReporte.setEditable(false);
		
		JScrollPane areaScrollPane = new JScrollPane(taListadoReporte);
		areaScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		areaScrollPane.setBounds(225, 100, 420, 470); //.setBounds(256, 35, 420, 470);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Reporte"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						areaScrollPane.getBorder()));
		add(areaScrollPane);
		
		String[] anno = {"2018","2019","2020","2021","2022"};
		String[] mes = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		String[] dia = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31"};
		
		JLabel tituloAnno = new JLabel("Año");
		tituloAnno.setBounds(833, 45, 46, 22);
		tituloAnno.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloAnno);

		JLabel tituloMes = new JLabel("Mes");
		tituloMes.setBounds(907, 45, 46, 22);
		tituloMes.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMes);

		JLabel tituloDia = new JLabel("Dia");
		tituloDia.setBounds(970, 45, 46, 22);
		tituloDia.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloDia);
		
		jcbAnnoDesde = new JComboBox<String>(anno);
		jcbAnnoDesde.setBounds(833, 65, 60, 22);
		jcbAnnoDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoDesde);

		jcbMesDesde = new JComboBox<String>(mes);
		jcbMesDesde.setBounds(907, 65, 50, 22);
		jcbMesDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesDesde);

		jcbDiaDesde = new JComboBox<String>(dia);
		jcbDiaDesde.setBounds(970, 65, 50, 22);
		jcbDiaDesde.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaDesde);
		
		JLabel tituloAnno1 = new JLabel("Año");
		tituloAnno1.setBounds(1050, 45, 46, 22);
		tituloAnno1.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloAnno1);

		JLabel tituloMes1 = new JLabel("Mes");
		tituloMes1.setBounds(1124, 45, 46, 22);
		tituloMes1.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloMes1);

		JLabel tituloDia1 = new JLabel("Dia");
		tituloDia1.setBounds(1187, 45, 46, 22);
		tituloDia1.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(tituloDia1);

		jcbAnnoHasta = new JComboBox<String>(anno);
		jcbAnnoHasta.setBounds(1050, 65, 60, 22);
		jcbAnnoHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbAnnoHasta);

		jcbMesHasta = new JComboBox<String>(mes);
		jcbMesHasta.setBounds(1124, 65, 50, 22);
		jcbMesHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbMesHasta);

		jcbDiaHasta = new JComboBox<String>(dia);
		jcbDiaHasta.setBounds(1187, 65, 50, 22);
		jcbDiaHasta.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		add(jcbDiaHasta);
		
		taListadoResumen = new JTextArea(100,40);
		taListadoResumen.setFont(new Font("Tahoma", Font.PLAIN, tLetra));
		taListadoResumen.setLineWrap(false);
		taListadoResumen.setWrapStyleWord(false);
		taListadoResumen.setEditable(false);

		JScrollPane areaScrollPaneResumen = new JScrollPane(taListadoResumen);
		areaScrollPaneResumen.getVerticalScrollBar().setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
		areaScrollPaneResumen.setBounds(833, 100, 420, 470);
		areaScrollPaneResumen.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPaneResumen.setPreferredSize(new Dimension(250, 250));
		areaScrollPaneResumen.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Resumen"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						areaScrollPaneResumen.getBorder()));
		add(areaScrollPaneResumen);
		
		JLabel lblComprobanteInforme = new JLabel("Comprobante ");
		lblComprobanteInforme.setBounds(56, 35, 350, 35);
		lblComprobanteInforme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblComprobanteInforme);
		
		JLabel lblComprobanteInformeDiario = new JLabel("Informe D\u00A1ario (POS)");
		lblComprobanteInformeDiario.setBounds(56, 55, 350, 35);
		lblComprobanteInformeDiario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblComprobanteInformeDiario);
		
		
		
	}
	
	public JComboBox<String> getJcbAnnoHasta() {
		return jcbAnnoHasta;
	}
	public void setJcbAnnoHasta(JComboBox<String> jcbAnnoHasta) {
		this.jcbAnnoHasta = jcbAnnoHasta;
	}
	public JComboBox<String> getJcbMesHasta() {
		return jcbMesHasta;
	}
	public void setJcbMesHasta(JComboBox<String> jcbMesHasta) {
		this.jcbMesHasta = jcbMesHasta;
	}
	public JComboBox<String> getJcbDiaHasta() {
		return jcbDiaHasta;
	}
	public void setJcbDiaHasta(JComboBox<String> jcbDiaHasta) {
		this.jcbDiaHasta = jcbDiaHasta;
	}
	public JComboBox<String> getJcbAnnoDesde() {
		return jcbAnnoDesde;
	}
	public void setJcbAnnoDesde(JComboBox<String> jcbAnnoDesde) {
		this.jcbAnnoDesde = jcbAnnoDesde;
	}
	public JComboBox<String> getJcbMesDesde() {
		return jcbMesDesde;
	}
	public void setJcbMesDesde(JComboBox<String> jcbMesDesde) {
		this.jcbMesDesde = jcbMesDesde;
	}
	public JComboBox<String> getJcbDiaDesde() {
		return jcbDiaDesde;
	}
	public void setJcbDiaDesde(JComboBox<String> jcbDiaDesde) {
		this.jcbDiaDesde = jcbDiaDesde;
	}
	public JRadioButton getRdbtnReporteParcialX() {
		return rdbtnReporteParcialX;
	}
	public void setRdbtnReporteParcialX(JRadioButton rdbtnReporteParcialX) {
		this.rdbtnReporteParcialX = rdbtnReporteParcialX;
	}
	public JRadioButton getRdbtnReporteFinalZ() {
		return rdbtnReporteFinalZ;
	}
	public void setRdbtnReporteFinalZ(JRadioButton rdbtnReporteFinalZ) {
		this.rdbtnReporteFinalZ = rdbtnReporteFinalZ;
	}
	public JButton getBtnResumen() {
		return btnResumen;
	}
	public void setBtnResumen(JButton btnResumen) {
		this.btnResumen = btnResumen;
	}
	public JButton getBtnImprimir() {
		return btnImprimir;
	}
	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}
	public JTextArea getTaListadoReporte() {
		return taListadoReporte;
	}
	public void setTaListadoReporte(JTextArea taListadoReporte) {
		this.taListadoReporte = taListadoReporte;
	}
	public JTextArea getTaListadoResumen() {
		return taListadoResumen;
	}
	public void setTaListadoResumen(JTextArea taListadoResumen) {
		this.taListadoResumen = taListadoResumen;
	}
	public ConReportes getConReportes() {
		return conReportes;
	}
	public void setConReportes(ConReportes conReportes) {
		this.conReportes = conReportes;
	}
}
