package general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class PatientPanel extends JPanel {
	TableModelWithEditChoise disease_story_model;
	JTable disease_story_table;
	JScrollPane scroll;

	public PatientPanel() {
		setBackground(Color.WHITE);		
		setLayout(new BorderLayout());

		JPanel patient_data = new JPanel();
		patient_data.setLayout(new GridLayout(3, 2));
		patient_data.setBackground(Color.WHITE);
		
		JLabel patient_label = new JLabel("Patient: ");
		JLabel patient_name = new JLabel("Someone");		
		JLabel birthday_label = new JLabel("Date of birthday: ");
		JLabel birthday_name = new JLabel("10.12.1998");		
		JLabel adress_label = new JLabel("Adress: ");
		JLabel adress_name = new JLabel("Baker str.");

		patient_data.add(patient_label);
		patient_data.add(patient_name);		
		patient_data.add(birthday_label);
		patient_data.add(birthday_name);		
		patient_data.add(adress_label);
		patient_data.add(adress_name);
		
		

		String[] columns = { "DOCTOR", "DISEASE", "MEMO" };
		String[][] data = {{"DOCTOR", "DISEASE", "MEMO"}};
		disease_story_model = new TableModelWithEditChoise(data, columns);
		disease_story_table = new JTable(disease_story_model);
		scroll = new JScrollPane(disease_story_table);
		disease_story_table.setSelectionForeground(new Color(255, 255, 255));
		disease_story_table.setSelectionBackground(new Color(59, 59, 59));
		
		
		JToolBar arrows = new JToolBar();
		arrows.setBackground(new Color(150, 150, 150));
		arrows.setFloatable(false);
		arrows.setLayout(new BorderLayout());
		arrows.setBackground(Color.WHITE);
		
		JButton left = new JButton(new ImageIcon("imgs/left.png"));
		JButton right = new JButton(new ImageIcon("imgs/right.png"));
		left.setContentAreaFilled(false);
		right.setContentAreaFilled(false);
		
		arrows.add(left, BorderLayout.WEST);
		arrows.add(right, BorderLayout.EAST);
		
		
		
		add(patient_data, BorderLayout.NORTH);	
		add(scroll, BorderLayout.CENTER);
		add(arrows, BorderLayout.SOUTH);
	}
}
