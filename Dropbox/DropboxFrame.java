package Dropbox;

import java.util.List;
import java.util.Objects;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dropbox.DropboxButtonHapusActionListener;
import Dropbox.DropboxButtonSimpanActionListener;
import Dropbox.DropboxFrame;
import Dropbox.DropboxTableModel;
import Dropbox.Dropbox;
import Dropbox.DropboxButtonEditActionListener;
import Waste.WasteFrame;
import dao.CategoriesDao;
import dao.DropboxDao;
import dao.WasteDao;

public class DropboxFrame extends JFrame {

    // Variabel-variabel untuk menyimpan objek BiodataDao, komponen GUI, model tabel, dan data biodata
    private DropboxDao DropboxDao;
    private DropboxTableModel tableModel;
    private JTable table;
    private List<Dropbox> DropboxList;
    private JLabel labellocation, labelpoint;
    private JTextField textlocation, textpoint;
    private JButton buttonsimpan, buttonhapus, buttonedit;

    // Konstruktor kelas BiodataFrame
    public DropboxFrame(DropboxDao dropboxDao) {
        JFrame jframe = this;


        // Mengambil data biodata dari database dan menampilkannya pada konsol
        this.DropboxList = DropboxDao.findAll();
        for (Dropbox Dropbox: this.DropboxList) {
            System.out.println(Dropbox.getLocation() +" "+Dropbox.getPoint());
        }

        // Menginisialisasi objek BiodataDao
        this.DropboxDao = new DropboxDao();

        // Menginisialisasi komponen GUI

        labellocation = new JLabel("location :");labellocation.setBounds(15,40,350,15);
        textlocation = new JTextField();textlocation.setBounds(15,60,300,30);

        buttonsimpan = new JButton("Simpan");buttonsimpan.setBounds(15,100,100,40);
        buttonedit = new JButton("Edit");buttonedit.setBounds(115,100,80,40);
        buttonhapus = new JButton("Hapus");buttonhapus.setBounds(195,100,80,40);

        this.table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15,150,300,200);
        tableModel = new DropboxTableModel(DropboxList);
        table.setModel(tableModel);

        // Menambahkan listener untuk tombol Simpan, Edit, dan Hapus
        DropboxButtonSimpanActionListener actionListenerSimpan = new DropboxButtonSimpanActionListener(this, DropboxDao);
        DropboxButtonHapusActionListener actionListenerHapus = new DropboxButtonHapusActionListener(this, DropboxDao);
        DropboxButtonEditActionListener actionListenerEdit = new DropboxButtonEditActionListener(this, DropboxDao,  this.DropboxList);

        buttonsimpan.addActionListener(actionListenerSimpan);
        buttonhapus.addActionListener(actionListenerHapus);
        buttonedit.addActionListener(actionListenerEdit);

        // Menambahkan komponen GUI ke frame
        this.add(labellocation);
        this.add(textlocation);
        this.add(buttonsimpan);
        this.add(buttonedit);
        this.add(buttonhapus);
        this.add(scrollableTable);

        // Mengatur ukuran dan layout frame
        this.setSize(330,450);
        this.setLayout(null);
    }

    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            DropboxDao dropboxDao = new DropboxDao();
            public void run() {
                DropboxFrame runnable = new DropboxFrame(dropboxDao);
                runnable.setVisible(true);
            }
        });
    }

    // Metode getter untuk mendapatkan objek tabel
    public JTable getTable() {
        return table;
    }

    // Metode getter untuk mendapatkan nilai location dari form
    public String getDropboxLocation() { return textlocation.getText(); }

    // Metode getter untuk mendapatkan nilai Alamat dari form
    public String getPoint() { return textpoint.getText(); }

    // Metode setter untuk mengatur nilai location pada form
    public void setTextlocation(String textlocation) { this.textlocation.setText(textlocation); }

    // Metode setter untuk mengatur nilai Nomor Telepon pada form
    public void setTextpoint(String textpoint) { this.textpoint.setText(textpoint); }

    // Metode untuk menghapus data pada tabel
    public void removeData(int selected) {
        this.tableModel.remove(selected);
    }

    // Metode untuk menambahkan data biodata pada tabel
    public void addDropbox(Dropbox Dropbox) {
        tableModel.add(Dropbox);
        textlocation.setText("");
    }

    // Metode untuk menampilkan pesan pop-up
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Metode untuk memeriksa apakah form kosong
    public boolean isEmptyField() {
        return (Objects.equals(this.textlocation.getText(), "")) && Objects.equals(this.textpoint.getText(), "");
    }

    // Metode untuk mengupdate data biodata pada tabel
    public void updateDropbox(Dropbox update, int selected) {
        this.tableModel.update(update, selected);
    }
}