package general;

import javax.swing.table.DefaultTableModel;

public class TableModelWithEditChoise extends DefaultTableModel{
	boolean flag = true;
	
	public TableModelWithEditChoise(String[][] a, String[] b) {
		super(a, b);
	}
	
	public void setEditable(boolean choice) {
		flag = choice;
	}
	
	public boolean isCellEditable(int row, int column){  
        return false;  
    }
}
