package db;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ControlPanel extends JPanel {
	
	Db db;
	boolean blocked = true;
	EntitiesPanel patientPanel;
	EntitiesPanel doctorPanel;
	EntitiesPanel diseasePanel;
	RecordsPanel recordPanel;

	public ControlPanel(Dimension size) throws IOException {
		setBackground(new Color(121, 112, 176));
		setLayout(null);
		setSize(size);
		
		// DATABASE
		db = new Db("data/hospital.mdb");				

		// BUTTONS
		ImageIcon mainIcon = new ImageIcon("imgs/main.png");
		ImageIcon patIcon = new ImageIcon("imgs/pat.png");
		ImageIcon docIcon = new ImageIcon("imgs/doc.png");
		ImageIcon diseaseIcon = new ImageIcon("imgs/disease.png");
		ImageIcon recordIcon = new ImageIcon("imgs/record.png");
		ImageIcon adminIcon = new ImageIcon("imgs/admin.png");

		JButton toMain = new JButton(mainIcon);
		JButton toPatient = new JButton(patIcon);
		JButton toDoctor = new JButton(docIcon);
		JButton toDisease = new JButton(diseaseIcon);
		JButton toRecord = new JButton(recordIcon);
		JButton getAdmin = new JButton(adminIcon);

		JButton[] array = { toMain, toPatient, toDoctor, toDisease, toRecord, getAdmin };
		Dimension btnSize = new Dimension(166, 90);

		for (int i = 0; i < array.length - 1; ++i)
			array[i].setBounds(0, i * 90, btnSize.width, btnSize.height);
		getAdmin.setBounds(0, 520, btnSize.width, btnSize.height);

		for (JButton el : array) {
			el.setSize(btnSize);
			el.setContentAreaFilled(false);
			add(el);
		}

		// PANEL CONTROLL
		CardLayout cardLayout = new CardLayout();
		JPanel base = new JPanel();
		Dimension panSize = new Dimension(634, 650);
		base.setSize(panSize);
		base.setBounds(166, 0, panSize.width, panSize.height);
		base.setLayout(cardLayout);
		add(base);

		HomePanel homePanel = new HomePanel(panSize);
		patientPanel = new EntitiesPanel(panSize, db, "Patients", "imgs/patheader.png");
		doctorPanel = new EntitiesPanel(panSize, db, "Doctors", "imgs/patheader2.png");
		diseasePanel = new EntitiesPanel(panSize, db, "Diseases", "imgs/patheader3.png");	
		recordPanel = new RecordsPanel(panSize, db, "Records", "imgs/patheader4.png");

		base.add(homePanel, "home");
		base.add(patientPanel, "patient");
		base.add(doctorPanel, "doctor");
		base.add(diseasePanel, "disease");
		base.add(recordPanel, "record");
		
		block();

		// LISTENER
		class ButtonListener implements ActionListener {

			private JPanel controlled;
			private CardLayout layout;
			private String tag;

			public ButtonListener(JPanel arg0, CardLayout arg1, String arg2) {
				controlled = arg0;
				layout = arg1;
				tag = arg2;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(controlled, tag);
			}
		}

		toMain.addActionListener(new ButtonListener(base, cardLayout, "home"));
		toPatient.addActionListener(new ButtonListener(base, cardLayout, "patient"));
		toDoctor.addActionListener(new ButtonListener(base, cardLayout, "doctor"));
		toDisease.addActionListener(new ButtonListener(base, cardLayout, "disease"));
		toRecord.addActionListener(new ButtonListener(base, cardLayout, "record"));
		
		getAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (blocked)
					block();
				else if (authentification())
					block();
				
			}
		});
	}
	
	boolean authentification() {
		JPasswordField authPane = new JPasswordField();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		messagePanel.add(new JLabel("Введите пароль:"));
		messagePanel.add(authPane);
		JOptionPane.showConfirmDialog(null, messagePanel, "Пароль", -1, JOptionPane.WARNING_MESSAGE);
		
		String myPas = String.valueOf(authPane.getPassword());
		
		return myPas.equals("admin");
	}
	
	void block() {
		blocked = !blocked;
		
		patientPanel.block(blocked);
		doctorPanel.block(blocked);
		diseasePanel.block(blocked);
		recordPanel.block(blocked);
	}
}
