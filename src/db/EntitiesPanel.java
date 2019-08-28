package db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EntitiesPanel extends JPanel {
	
	Db database;
	ExTableModel tableModel;
	String tableName;
	String imagePath; 
	JTable table;
	JScrollPane scroll;
	JButton[] buttons;

	public EntitiesPanel(Dimension size, Db db, String tn, String ip) throws IOException {
		setBackground(new Color(135, 130, 167));
		setLayout(null);		
		database = db;
		tableName = tn;
		imagePath = ip;
		
		//header
		JLabel header = new JLabel(new ImageIcon(imagePath));
		header.setBounds(0, 0, size.width, 90);
		add(header);
		
		//table				
		table = new JTable();
		loadTable();
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 90, size.width, 180);
		add(scroll);
		
		//buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(0, 270, size.width, 90);
		
		ImageIcon addIcon = new ImageIcon("imgs/add.png");
		ImageIcon deleteIcon = new ImageIcon("imgs/delete.png");
		ImageIcon refreshIcon = new ImageIcon("imgs/refresh.png");
		ImageIcon saveIcon = new ImageIcon("imgs/save.png");
		
		JButton addBtn = new JButton(addIcon);
		JButton deleteBtn = new JButton(deleteIcon);
		JButton refreshBtn = new JButton(refreshIcon);
		JButton saveBtn = new JButton(saveIcon);
		
		JButton[] array = {addBtn, deleteBtn, refreshBtn, saveBtn};
		buttons = array;
		Dimension btnSize = new Dimension(size.width / 4, 90);
		
		int i = 0;
		for (JButton el : array)
		{
			el.setSize(btnSize);
			el.setBounds(btnSize.width * i++, 0, btnSize.width, btnSize.height);
			el.setContentAreaFilled(false);
			buttonPanel.add(el);
		}
			
		add(buttonPanel);
		
		//Special input panel
		JPanel lowerPanel1 = new AddPanel(database, size, tableName);		
		add(lowerPanel1);
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lowerPanel1.isVisible())
					lowerPanel1.setVisible(false);
				else
					lowerPanel1.setVisible(true);							
			}
		});
		
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadTable();
				
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					db.replaceDataInTable(tableName, tableModel.getData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (table.getSelectedRow() != -1)
						db.deleteTableRow(tableName, Db.getTableColumnsNames(tableName)[0],
							table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
	}
	
	
	public void loadTable() {
		try {
			var tmp1 = database.getDataFromTable(tableName);
			var tmp2 = database.getTableColumnsNames(tableName);			
			tableModel = new ExTableModel(tmp1, tmp2);
			table.setModel(tableModel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void block(boolean flag) {
		for (int i = 0; i < buttons.length; ++i)
			buttons[i].setEnabled(flag);
	}
}
