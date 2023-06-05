package aknakereso;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class RanglistaData extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636816878168126106L;
	List<Ranglista> ranglista = new ArrayList<Ranglista>();
    
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
    	Ranglista elem = ranglista.get(rowIndex);
    	switch(columnIndex) 
    	{
    		case 0: return elem.getDate();
    		case 1: return elem.getNehezseg();
    		default: return elem.getIdo();
    	}
    }

	public int getColumnCount() 
	{
		return 3;
	}

	public int getRowCount() 
	{
		return ranglista.size();
	}
	
	public String getColumnName(int column) 
	{
		switch(column) 
    	{
    		case 0: return "Dátum";
    		case 1: return "Nehézség";
    		default: return "Idõ";
    	}
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{ 
	    if(columnIndex == 2 || columnIndex == 3)
	        return true;
	    else
	        return false;
	}
	
	public void addListaelem(long ido, String nehezseg)
	{
		Ranglista uj = new Ranglista(ido, nehezseg);
		ranglista.add(uj);
		fireTableRowsInserted(ranglista.size()-1, ranglista.size()-1);
	}
}
