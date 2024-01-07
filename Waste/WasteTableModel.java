package Waste;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class WasteTableModel extends AbstractTableModel {

    private String[] columnNames = { "id","Location", "Jenis Waste" };
    private List<Waste> data;

    public WasteTableModel(List<Waste> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Waste rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getId();
                break;
            case 1:
                value = rowItem.getDropbox().getLocation();
                break;
            case 2:
                value = rowItem.getCategory().getNama();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Waste value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(Waste update, int selected) {
        data.set(selected, update);
        fireTableCellUpdated(selected, 0);
        fireTableCellUpdated(selected, 1);
    }

    public void remove(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex); // Wastei tahu JTable bahwa baris telah dihapus
        } else {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
    }
}
