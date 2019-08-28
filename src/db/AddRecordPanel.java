package db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddRecordPanel extends JPanel {
	
	Db db;
	JComboBox<String> pats;
	JComboBox<String> docs;
	JComboBox<String> dis;
	ComboBoxModel<String> patsModel;
	ComboBoxModel<String> docsModel;
	ComboBoxModel<String> disModel;
	
	
	public AddRecordPanel(Db database, Dimension size, String tableName) throws IOException {
		db = database;
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
		setBounds(0, 360, size.width, size.width - 360);
		Dimension elSize = new Dimension(199, 29);		
		String[] tableNames = db.getTableColumnsNames(tableName);
		
		JButton addBtn = new JButton("SUBMIT");
		JLabel[] txts = new JLabel[tableNames.length];
		JTextField[] fields = new JTextField[4];

		
		docs = new JComboBox<String>();
		pats = new JComboBox<String>();
		dis = new JComboBox<String>();
		
		docs.setBounds(140, 15 + elSize.height * 3, elSize.width, elSize.height);
		pats.setBounds(140, 15 + elSize.height * 4, elSize.width, elSize.height);
		dis.setBounds(140, 15 + elSize.height * 5, elSize.width, elSize.height);
		
		docs.setSize(elSize);
		pats.setSize(elSize);
		dis.setSize(elSize);
		
		add(docs);
		add(pats);
		add(dis);		
		
		loadComboBoxes();
		
		for (int i = 0, j = 0; i < txts.length; ++i, ++j)
		{
			txts[i] = new JLabel(tableNames[i]);
			txts[i].setBounds(40, 15 + elSize.height * i, elSize.width, elSize.height);
			txts[i].setSize(elSize);
			add(txts[i]);
			
			if (i == 0 || i == 1 || i == 2 || i == 6)
			{
				fields[j] = new JTextField();
				fields[j].setBounds(140, 15 + elSize.height * i, elSize.width, elSize.height);
				fields[j].setSize(elSize);
				add(fields[j]);
			}
			else
				--j;
		}
			
		addBtn.setBounds(372, 189, elSize.width, elSize.height);
		addBtn.setSize(elSize);
		addBtn.setContentAreaFilled(false);
		add(addBtn);
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				try {
					checkDateTxtField(fields[1]);
					checkTimeTxtField(fields[2]);
					if (!fields[0].getText().isEmpty() && !fields[1].getText().isEmpty() && !fields[2].getText().isEmpty())
					{
						try {
							Object[] row = new Object[] {fields[0].getText(), fields[1].getText(),
									fields[2].getText(), docs.getSelectedItem(), pats.getSelectedItem(),
									dis.getSelectedItem(), fields[3].getText()};
							
							db.addTableRow(tableName, row);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						for (int i = 0; i < fields.length; ++i)
							fields[i].setText("");	
					}					
					setVisible(false);
				} catch (NullPointerException | InvalidTimeFormatException | InvalidDateFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public void loadComboBoxes() {
		
		try {
			docsModel = new DefaultComboBoxModel<String>((String[]) db.getDataFromColumn("Doctors", "Surname"));;
			patsModel = new DefaultComboBoxModel<String>((String[]) db.getDataFromColumn("Patients", "Surname"));
			disModel = new DefaultComboBoxModel<String>((String[]) db.getDataFromColumn("Diseases", "Name"));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pats.setModel(patsModel);
		docs.setModel(docsModel);
		dis.setModel(disModel);
	}
	

	private void checkTimeTxtField(JTextField field) throws InvalidTimeFormatException, NullPointerException {
		String str = field.getText();
		Pattern p = Pattern.compile("(1?\\d|2[0-3])\\:([0-5]\\d)$");
		Matcher m = p.matcher(str);
		if (!m.matches())
			throw new InvalidTimeFormatException();
	}
	
	private void checkDateTxtField(JTextField field) throws InvalidDateFormatException, NullPointerException {
		String str = field.getText();
		Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
		Matcher m = p.matcher(str);
		if (!m.matches())
			throw new InvalidDateFormatException();
	}
}
