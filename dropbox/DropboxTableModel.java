package Dropbox;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dropbox.Dropbox;

public class DropboxTableModel extends AbstractTableModel {

    // Array untuk menyimpan nama-nama kolom pada tabel
    private String[] columnNames = {"Location", "Point"};
    
    // List untuk menyimpan data biodata
    private List<Dropbox> data;

    // Konstruktor kelas BiodataTableModel
    public DropboxTableModel(List<Dropbox> data) {
        this.data = data;
    }

    // Metode untuk mendapatkan jumlah kolom pada tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Metode untuk mendapatkan jumlah baris pada tabel
    public int getRowCount() {
        return data.size();
    }

    // Metode untuk mendapatkan nama kolom pada indeks tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Metode untuk mendapatkan nilai pada sel tertentu dalam tabel
    public Object getValueAt(int row, int col) {
        Dropbox rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getLocation();
                break;
            case 1:
                value = rowItem.getPoint();
                break;
        }
        return value;
    }

    // Metode untuk memeriksa apakah sel dapat diedit
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Metode untuk menambahkan data biodata ke tabel
    public void add(Dropbox value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Metode untuk mengupdate data biodata pada tabel
    public void update(Dropbox update, int selected) {
        data.set(selected, update);
        fireTableCellUpdated(selected, 0);
        fireTableCellUpdated(selected, 1);
    }

    // Metode untuk menghapus data pada tabel
    public void remove(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        } else {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
    }
}
