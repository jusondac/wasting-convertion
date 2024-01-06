package Dropbox;

import java.util.UUID;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Dropbox.Dropbox;
import Dropbox.DropboxFrame;
import dao.DropboxDao;

public class DropboxButtonSimpanActionListener implements ActionListener {

    private DropboxFrame DropboxFrame;
    private DropboxDao DropboxDao;

    // Konstruktor kelas BiodataButtonSimpanActionListener
    public DropboxButtonSimpanActionListener(DropboxFrame DropboxFrame, DropboxDao DropboxDao) {
        this.DropboxFrame = DropboxFrame;
        this.DropboxDao = DropboxDao;
    }

    // Metode yang akan dipanggil ketika tombol Simpan ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengambil nilai dari form biodata
        String location = this.DropboxFrame.getLocation();
        String point = this.DropboxFrame.getPoint();
        
        // Memeriksa apakah location kosong
        if (location.isEmpty()) {
            this.DropboxFrame.showAlert("Text field tidak boleh Kosong");
        } else {
            // Jika location tidak kosong, membuat objek Biodata baru
            Dropbox Dropbox = new Dropbox();
            Dropbox.setId(UUID.randomUUID().toString());
            Dropbox.setLocation(location);
            Dropbox.setPoint(point);


            // Menambahkan biodata ke tabel dan database
            this.DropboxFrame.addDropbox(Dropbox);
            this.DropboxDao.insert(Dropbox);
        }
    }
}
