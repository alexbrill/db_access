package db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Index;
import com.healthmarketscience.jackcess.Relationship;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class Db {
	private static Database db;
	String address;

	public Db(String file_address) throws IOException {
		address = file_address;
		db = DatabaseBuilder.open(new File(address));
		db.setColumnOrder(Table.ColumnOrder.DISPLAY);
	}

	public static String[] getTableColumnsNames(String name) throws IOException {
		Table table = db.getTable(name);
		String[] names = new String[table.getColumnCount()];
		int i = 0;
		for (Column column : table.getColumns()) {
			names[i] = column.getName();
			i++;
		}

		return names;
	}

	public Object[][] getDataFromTable(String name) throws IOException {
		Table table = db.getTable(name);
		Object[][] data = new Object[table.getRowCount()][table.getColumnCount()];
		int i = 0;

		for (Row row : table) {
			int j = 0;
			for (Column column : table.getColumns()) {
				data[i][j] = row.get(column.getName());
				j++;
			}
			i++;
		}

		return data;
	}

	public Object[] getDataFromColumn(String name, String colName) throws IOException {
		Table table = db.getTable(name);
		Object[] data = new String[table.getRowCount()];
 		
		Column col = table.getColumn(colName);
		int i = 0;
		for (Row row : table)
			data[i++] = col.getRowValue(row);

		return data;		
	}
	
	public void replaceDataInTable(String name, Object[][] data) throws IOException
	{
		Table table = db.getTable(name);
		var colNames = getTableColumnsNames(name);
		
		for (Row row : table)
			table.deleteRow(row);
		
		for (int i = 0, j; i < data.length; ++i)
		{
			HashMap<String, Object> map = new HashMap();		
			
			for (j = 0; j < data[i].length; ++j)
				map.put(colNames[j], data[i][j]);
			
			table.addRowFromMap(map);		
		}		
	}
	
	public void addTableRow(String name, Object[] data) throws IOException {
		Table table = db.getTable(name);
		var colNames = getTableColumnsNames(name);
		
		HashMap<String, Object> map = new HashMap();		
		
		for (int j = 0; j < data.length; ++j)
			map.put(colNames[j], data[j]);
		
		table.addRowFromMap(map);	
	}
	
	public Object[] getDataFromRow(String name, String colName, Object docKey) throws IOException {
		Table table = db.getTable(name);
		Object[] data = new String[table.getColumnCount()];
		
		int i = 0;
		Column col = table.getColumn(colName);
		
		for (Row row : table)
		{
			if (col.getRowValue(row).equals(docKey))
				for (Column column : table.getColumns())
					data[i++] = row.get(column.getName());
		}
		
		return data;		
	}
	
	public void deleteTableRow(String name, String colName, Object object) throws IOException {
		Table table = db.getTable(name);
		Cursor cursor = CursorBuilder.createCursor(table);
		boolean found = cursor.findFirstRow(Collections.singletonMap(colName, object));
		if (found)
			cursor.deleteCurrentRow();
	}

	public static void main(String[] args) {

		try {
			Db a = new Db("data/hospital.mdb");
			var b = a.getDataFromRow("Patients", "Surname", "Novikov");
			
			for (var el : b)
				System.out.println(el);
			
			// open existed db
			//Object[][] b = a.getDataFromTable("Patients");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
