package general;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	CardLayout cardLayout;

	public RightPanel(CardLayout cL) {
		cardLayout = cL;		
		setLayout(cardLayout);
		setPreferredSize(new Dimension(633, 650));
		
		JPanel main_panel = new MainPanel();
		JPanel pat_panel = new PatientPanel();
		JPanel doc_panel = new DoctorPanel();
		JPanel shed_panel = new SchedulePanel();
		
		
		add(main_panel, "1");
		add(pat_panel, "2");
		add(doc_panel, "3");
		add(shed_panel, "4");
		
	}
}
