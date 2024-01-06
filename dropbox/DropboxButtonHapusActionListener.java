package Dropbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import Dropbox.DropboxFrame;
import dao.DropboxDao;

public class DropboxButtonHapusActionListener implements ActionListener {

    // Variabel-variabel untuk menyimpan objek BiodataFrame, BiodataDao, dan data biodata
    private DropboxFrame DropboxFrame;
    private DropboxDao DropboxDao;

    // Konstruktor kelas BiodataButtonHapusActionListener
    public DropboxButtonHapusActionListener(DropboxFrame DropboxFrame, DropboxDao DropboxDao) {
        this.DropboxFrame = DropboxFrame;
        this.DropboxDao = DropboxDao;
    }

    // Metode yang akan dipanggil ketika tombol Hapus ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.DropboxFrame.getTable();
        int selected = table.getSelectedRow();
        
        // Mendapatkan nilai Nama dari biodata yang dipilih
        String location = (String) table.getValueAt(selected, 0);
        
        // Mencari biodata berdasarkan nama
        // Dropbox Dropbox = this.DropboxDao.findByName(nama);
        
        // Menghapus data pada tabel dan database
        this.DropboxFrame.removeData(selected);
        // this.DropboxDao.delete(Dropbox);
    }
}