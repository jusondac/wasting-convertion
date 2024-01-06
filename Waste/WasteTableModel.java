package Waste;

import java.util.List;

import Category.Categories;

public class WasteTableModel {

    private String[] columnNames = { "Nama", "Jenis Waste" };
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
        Categories value = "";

        switch (col) {
            case 0:
                value = rowItem.getCategory();
                break;
            case 1:
                value = rowItem.getJenisWaste().getDropbox();
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
            fireTableRowsDeleted(rowIndex, rowIndex); // Wastes tahu JTable bahwa baris telah dihapus
        } else {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
    }

}
