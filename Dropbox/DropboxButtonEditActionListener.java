package Dropbox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

import Dropbox.Dropbox;
import Dropbox.DropboxFrame;
import dao.DropboxDao;

public class DropboxButtonEditActionListener implements ActionListener {

    // Variabel-variabel untuk menyimpan data Dropbox dan objek-objek terkait
    private final List<Dropbox> dropboxList;
    private DropboxFrame dropboxFrame;
    private DropboxDao dropboxDao;

    // Konstruktor kelas DropboxButtonEditActionListener
    public DropboxButtonEditActionListener(DropboxFrame dropboxFrame, DropboxDao dropboxDao,
            List<Dropbox> dropboxList) {
        this.dropboxDao = dropboxDao;
        this.dropboxFrame = dropboxFrame;
        this.dropboxList = dropboxList;
    }

    // Metode yang akan dipanggil ketika tombol Edit ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.dropboxFrame.getTable();
        int selected = table.getSelectedRow();
        if (selected == -1 ) {
            this.dropboxFrame.showAlert("Pilih data yang ingin diedit");
        } else {
            // Mendapatkan nilai dari tabel untuk Dropbox yang dipilih
            String location = (String) table.getValueAt(selected, 0);

            // Jika ada isian kosong pada form, mengisi form dengan data Dropbox yang
            // dipilih
            if (this.dropboxFrame.isEmptyField()) {
                this.dropboxFrame.setTextlocation(location);
            } else {
                // Jika form tidak kosong, membuat objek Dropbox baru untuk diupdate
                Dropbox updateDropbox = new Dropbox();
                Dropbox dropbox = dropboxList.get(selected);
                if (this.dropboxFrame.getDropboxLocation().isEmpty()) {
                    this.dropboxFrame.showAlert("Nama Lokasi tidak boleh kosong");
                } else {
                    updateDropbox.setId(dropbox.getId());
                    updateDropbox.setLocation(this.dropboxFrame.getDropboxLocation());

                    // Melakukan update data menggunakan DropboxDao
                    this.dropboxDao.update(updateDropbox);

                    // Mengupdate data pada tabel dan membersihkan isian form
                    this.dropboxFrame.updateDropbox(updateDropbox, selected);
                    this.dropboxFrame.setTextlocation("");
                }
            }
        }
    }

}