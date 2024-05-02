package cta.designe.listener;

import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

public class TableListenerModel extends DefaultTableModel {
	
	
	
	private final Class[] columnClass = new Class[] {
	        String.class, String.class, String.class, String.class, Boolean.class
	};
	
	
	
	public TableListenerModel(Vector model, Vector modelColumns) {
		super(model,modelColumns);
	}

	
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
     
 
}
