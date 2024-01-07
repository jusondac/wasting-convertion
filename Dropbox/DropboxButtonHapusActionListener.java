package Dropbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import dao.DropboxDao;

public class DropboxButtonHapusActionListener implements ActionListener {

    // Variabel-variabel untuk menyimpan objek BiodataFrame, BiodataDao, dan data biodata
    private DropboxFrame dropboxFrame;
    private DropboxDao dropboxDao;

    // Konstruktor kelas BiodataButtonHapusActionListener
    public DropboxButtonHapusActionListener(DropboxFrame DropboxFrame, DropboxDao DropboxDao) {
        this.dropboxFrame = DropboxFrame;
        this.dropboxDao = DropboxDao;
    }

    // Metode yang akan dipanggil ketika tombol Hapus ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.dropboxFrame.getTable();
        int selected = table.getSelectedRow();

        // Mendapatkan nilai Nama dari biodata yang dipilih
        String location = (String) table.getValueAt(selected, 0);
        // Mencari biodata berdasarkan nama
        Dropbox dropbox = this.dropboxDao.findByLocation(location);
        System.out.println(dropbox);
        // Menghapus data pada tabel dan database
        this.dropboxFrame.removeData(selected);
        this.dropboxDao.delete(dropbox);
    }
}