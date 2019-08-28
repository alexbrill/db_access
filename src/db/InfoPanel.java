package db;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class InfoPanel extends JPanel {
	
	Db db;
	JTable table;
	JLabel[] txtsPat;
	JLabel[] txtsDoc;
	JLabel[] txtsDis;
	
	public InfoPanel(Db database, Dimension size, String tableName, JTable t) throws IOException {
		db = database;
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
		setBounds(0, 360, size.width, size.width - 360);
		Dimension elSize = new Dimension(199, 29);
		String[] tableNames = db.getTableColumnsNames(tableName);
		table = t;
		
		JLabel[] txts = new JLabel[3];
		
		for (int i = 0; i < txts.length; ++i)
		{
			txts[i] = new JLabel();
			txts[i].setBounds(40 + 150 * i, 30 , elSize.width, elSize.height);
			txts[i].setSize(elSize);
			add(txts[i]);	
		}
		
		txts[0].setText("Patient");
		txts[1].setText("Doctor");
		txts[2].setText("Disease");
		
		txtsPat = new JLabel[3];
		txtsDoc = new JLabel[3];
		txtsDis = new JLabel[3];
		
		for (int i = 0; i < txtsPat.length; ++i)
		{
			txtsPat[i] = new JLabel();
			txtsPat[i].setBounds(85, 45 + elSize.height * i, elSize.width, elSize.height);
			txtsPat[i].setSize(elSize);
			add(txtsPat[i]);

			txtsDoc[i] = new JLabel();
			txtsDoc[i].setBounds(85 + 150, 45 + elSize.height * i, elSize.width, elSize.height);
			txtsDoc[i].setSize(elSize);
			add(txtsDoc[i]);

			txtsDis[i] = new JLabel();
			txtsDis[i].setBounds(85 + 300, 45 + elSize.height * i, elSize.width, elSize.height);
			txtsDis[i].setSize(elSize);
			add(txtsDis[i]);
		}		
	}
	
	public void update() {
		int row = table.getSelectedRow();		
		Object docKey = table.getValueAt(row, 3);
		Object patKey = table.getValueAt(row, 4);
		Object disKey = table.getValueAt(row, 5);
		
		try {
			var temp1 = db.getDataFromRow("Doctors", "Surname", docKey);
			var temp2 = db.getDataFromRow("Patients", "Surname", patKey);
			var temp3 = db.getDataFromRow("Diseases", "Name", disKey);
			
			for (int i = 0; i < txtsDoc.length; ++i)
				txtsDoc[i].setText((String) temp1[i]);
			
			for (int i = 0; i < txtsPat.length; ++i)
				txtsPat[i].setText((String) temp2[i]);
			
			for (int i = 0; i < txtsDis.length; ++i)
				txtsDis[i].setText((String) temp3[i]);			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
			
		
		
	}
}
