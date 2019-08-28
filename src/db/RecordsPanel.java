package db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RecordsPanel extends JPanel {
	
	Db database;
	ExTableModel tableModel;
	String tableName;
	String imagePath; 
	JTable table;
	JScrollPane scroll;
	JButton[] buttons;
	
	public RecordsPanel(Dimension size, Db db, String tn, String ip) throws IOException {
		setBackground(new Color(135, 130, 167));
		setLayout(null);
		database = db;
		tableName = tn;
		imagePath = ip;
		
		JLabel header = new JLabel(new ImageIcon(imagePath));
		header.setBounds(0, 0, size.width, 90);
		add(header);
		
		table = new JTable();
		loadTable();
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 90, size.width, 180);
		add(scroll);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(0, 270, size.width, 90);
		
		ImageIcon infoIcon = new ImageIcon("imgs/info.png");
		ImageIcon addIcon = new ImageIcon("imgs/add.png");
		ImageIcon deleteIcon = new ImageIcon("imgs/delete.png");
		ImageIcon refreshIcon = new ImageIcon("imgs/refresh.png");		
		
		JButton infoBtn = new JButton(infoIcon);
		JButton addBtn = new JButton(addIcon);
		JButton deleteBtn = new JButton(deleteIcon);
		JButton refreshBtn = new JButton(refreshIcon);
		
		JButton[] array = {infoBtn, addBtn, deleteBtn, refreshBtn};
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
		
		InfoPanel lowerPanel1 = new InfoPanel(database, size, tableName, table);
		AddRecordPanel lowerPanel2 = new AddRecordPanel(database, size, tableName);	
		add(lowerPanel2);
		add(lowerPanel1);
		
		infoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1)
					if (lowerPanel1.isVisible())
						lowerPanel1.setVisible(false);
					else
					{
						lowerPanel1.update();
						lowerPanel1.setVisible(true);
						lowerPanel2.setVisible(false);
					}
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lowerPanel2.isVisible())
					lowerPanel2.setVisible(false);
				else
				{
					lowerPanel2.setVisible(true);
					lowerPanel1.setVisible(false);
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
		
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadTable();
				
			}
		});

		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				lowerPanel1.update();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void loadTable() {
		try {
			var tmp1 = database.getDataFromTable(tableName);
			var tmp2 = database.getTableColumnsNames(tableName);			
			tableModel = new ExTableModel(tmp1, tmp2);
			tableModel.setEditable(false);
			table.setModel(tableModel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void block(boolean flag) {
		for (int i = 1; i < buttons.length; ++i)
			buttons[i].setEnabled(flag);
	}
}
