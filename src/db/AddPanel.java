package db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel {
	
	Db db;

	public AddPanel(Db database, Dimension size, String tableName) throws IOException {
		db = database;
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
		setBounds(0, 360, size.width, size.width - 360);
		String[] tableNames = db.getTableColumnsNames(tableName);
		Dimension elSize = new Dimension(199, 29);
		
		JButton addBtn = new JButton("SUBMIT");
		JLabel[] txts = new JLabel[tableNames.length];
		JTextField[] fields = new JTextField[tableNames.length];
		
		for (int i = 0; i < tableNames.length; ++i)
		{
			txts[i] = new JLabel(tableNames[i]);
			txts[i].setBounds(120, 30 + elSize.height * i, elSize.width, elSize.height);
			txts[i].setSize(elSize);
			add(txts[i]);	
			
			fields[i] = new JTextField();
			fields[i].setBounds(230, 30 + elSize.height * i, elSize.width, elSize.height);
			fields[i].setSize(elSize);
			add(fields[i]);
		}

				
		addBtn.setBounds(372, 187, elSize.width, elSize.height);
		addBtn.setSize(elSize);
		addBtn.setContentAreaFilled(false);
		add(addBtn);
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean check = true;
				
				for (int i = 0; i < fields.length; ++i) {
					if (fields[i].getText().isEmpty())
						check = false;
				}
				
				if (check)
				{
					try {
						Object[] row = new Object[fields.length];
						
						for (int i = 0; i < fields.length; ++i)
							row[i] = fields[i].getText();						
						
						db.addTableRow(tableName, row);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					for (int i = 0; i < fields.length; ++i)
						fields[i].setText("");				
				}
				
				setVisible(false);				
			}
		});
		
	}
	
}
