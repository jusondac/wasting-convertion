package Dropbox;

import dao.DropboxDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DropboxButtonKonversiActionListener implements ActionListener {
    // Variabel-variabel untuk menyimpan data Dropbox dan objek-objek terkait
    private DropboxFrame dropboxFrame;
    private DropboxDao dropboxDao;
    private List<Dropbox> DropboxList;

    // Konstruktor kelas DropboxButtonEditActionListener
    public DropboxButtonKonversiActionListener(DropboxFrame dropboxFrame, DropboxDao dropboxDao) {
        this.dropboxDao = dropboxDao;
        this.dropboxFrame = dropboxFrame;
    }

    // Metode yang akan dipanggil ketika tombol Edit ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.dropboxFrame.getTable();
        int selected = table.getSelectedRow();
        System.out.println("selected row in index : "+selected);
        this.DropboxList = DropboxDao.konversiPoint();
        int i = 0;
        for(Dropbox dropbox : this.DropboxList) {
            this.dropboxDao.updateKonversi(dropbox);
            this.dropboxFrame.updateDropbox(dropbox, i);
            i++;
        }
    }
}
