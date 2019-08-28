package db;

import javax.swing.table.DefaultTableModel;

public class ExTableModel extends DefaultTableModel {
	private boolean editable = true;
	
	
	public ExTableModel(Object[][] b, Object[] c) {
		super(b, c);
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return editable;		
	}
	
	public void setEditable(boolean arg0) {
		editable = arg0;
	}
	
	public Object[][] getData() {
		int rows = getRowCount();
		int cols = getColumnCount();
		Object[][] data = new Object[rows][cols];

		for (int i = 0, j; i < rows; ++i)
			for (j = 0; j < cols; ++j)
				data[i][j] = getValueAt(i, j);
		
		return data;		
	}
	
	public static void main(String[] args) {
		//ExTableModel model = new ExTableModel();
	}

	
}
