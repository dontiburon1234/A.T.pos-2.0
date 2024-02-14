package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class IntReporteBase extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public IntReporteBase() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab1", null, panel_1, null);
	}

}
