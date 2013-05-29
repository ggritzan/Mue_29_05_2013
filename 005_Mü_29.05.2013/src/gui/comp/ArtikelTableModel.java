package gui.comp;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vo.Artikel;


/**
 * TableModel f�r Artikel-Objekte (Skelett)
 * @author Marcel
 *
 */
public class ArtikelTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -5473134880835886184L;

	private List<Artikel> artikelList;

    private String[] columnNames = {"Bezeichnung", "Preis", "Anzahl"};

    private Class<?>[] columnClass = {String.class, Float.class, Integer.class };

	public ArtikelTableModel(List<Artikel> l) {
		this.artikelList = l;
	}

    @Override
    public int getRowCount() {
        return artikelList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {

        Artikel artikel = artikelList.get(row);

        switch (col) {
            case 0:
                return  artikel.getName();
            case 1:
                return  artikel.getPrice();
            case 2:
                return  artikel.getQuantity();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
      return columnClass[col];
    }
}