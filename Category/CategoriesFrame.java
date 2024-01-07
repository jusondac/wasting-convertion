package Category;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Waste.WasteFrame;
import dao.CategoriesDao;
import dao.DropboxDao;
import dao.WasteDao;

import javax.swing.table.AbstractTableModel;

public class CategoriesFrame extends JFrame{
        
    // Variabel-variabel untuk menyimpan objek BiodataDao, komponen GUI, model tabel, dan data biodata
    private CategoriesDao categoriesDao;
    private CategoriesTableModel tableModel;
    private JTable table;
    private List<Categories> categoriesList;
    private JLabel labelnama, labelpoint;
    private JTextField textnama, textpoint;
    private JButton buttonsimpan,  buttonhapus, buttonedit;

    // Konstruktor kelas BiodataFrame
    public CategoriesFrame(CategoriesDao categoriesDao) {
        JFrame jframe = this;
        
        // Mengambil data biodata dari database dan menampilkannya pada konsol
        this.categoriesList = CategoriesDao.findAll();
        for (Categories categories: this.categoriesList) {
            System.out.println(categories.getNama() +" "+categories.getPoint());
        }
        
        // Menginisialisasi objek BiodataDao
        this.categoriesDao = new CategoriesDao();


        labelnama = new JLabel("Nama :");labelnama.setBounds(15,40,350,15);
        labelpoint = new JLabel("Point :");labelpoint.setBounds(15,100,350,15);

        textnama = new JTextField();textnama.setBounds(15,60,300,30);
        textpoint = new JTextField();textpoint.setBounds(15,120,300,30);

        buttonsimpan = new JButton("Simpan");buttonsimpan.setBounds(15,160,100,40);
        buttonedit = new JButton("Edit");buttonedit.setBounds(115,160,80,40);
        buttonhapus = new JButton("Hapus");buttonhapus.setBounds(195,160,80,40);

        this.table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15,210,300,200);
        tableModel = new CategoriesTableModel(categoriesList);
        table.setModel(tableModel);

        // Menambahkan listener untuk tombol Simpan, Edit, dan Hapus
        CategoriesButtonSimpanActionListener actionListenerSimpan = new CategoriesButtonSimpanActionListener(this, categoriesDao);
        CategoriesButtonHapusActionListener actionListenerHapus = new CategoriesButtonHapusActionListener(this, categoriesDao);
        CategoriesButtonEditActionListener actionListenerEdit = new CategoriesButtonEditActionListener(this, categoriesDao,  this.categoriesList);

        buttonsimpan.addActionListener(actionListenerSimpan);
        buttonhapus.addActionListener(actionListenerHapus);
        buttonedit.addActionListener(actionListenerEdit);

        // Menambahkan komponen GUI ke frame
        this.add(labelnama);
        this.add(labelpoint);
        this.add(textnama);
        this.add(textpoint);
        this.add(buttonsimpan);
        this.add(buttonedit);
        this.add(buttonhapus);
        this.add(scrollableTable);

        // Mengatur ukuran dan layout frame
        this.setSize(330,470);
        this.setLayout(null);
    }

    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            CategoriesDao categoriesDao = new CategoriesDao();
            public void run() {
                CategoriesFrame runnable = new CategoriesFrame(categoriesDao);
                runnable.setVisible(true);
            }
        });
    }

    // Metode getter untuk mendapatkan objek tabel
    public JTable getTable() { return table; }

    // Metode getter untuk mendapatkan nilai Nama dari form
    public String getName() { return textnama.getText(); }
    
    // Metode getter untuk mendapatkan nilai Alamat dari form
    public String getPoint() { return textpoint.getText(); }

    // Metode setter untuk mengatur nilai Nama pada form
    public void setTextnama(String textnama) { this.textnama.setText(textnama); }
    
    // Metode setter untuk mengatur nilai Nomor Telepon pada form
    public void setTextpoint(String textpoint) { this.textpoint.setText(textpoint); }

    // Metode untuk menghapus data pada tabel
    public void removeData(int selected) { this.tableModel.remove(selected); }

    // Metode untuk menambahkan data biodata pada tabel
    public void addCategories(Categories categories) {
        tableModel.add(categories);
        textnama.setText("");
        textpoint.setText("");
    }

    // Metode untuk menampilkan pesan pop-up
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Metode untuk memeriksa apakah form kosong
    public boolean isEmptyField() {
        return (Objects.equals(this.textnama.getText(), "")) && Objects.equals(this.textpoint.getText(), "");
    }

    // Metode untuk mengupdate data biodata pada tabel
    public void updateCategories(Categories update, int selected) {
        this.tableModel.update(update, selected);
    }

    public CategoriesDao getCategoriesDao() {
        return categoriesDao;
    }

    public void setCategoriesDao(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    public CategoriesTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(CategoriesTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public JLabel getLabelnama() {
        return labelnama;
    }

    public void setLabelnama(JLabel labelnama) {
        this.labelnama = labelnama;
    }

    public JLabel getLabelpoint() {
        return labelpoint;
    }

    public void setLabelpoint(JLabel labelpoint) {
        this.labelpoint = labelpoint;
    }

    public JTextField getTextnama() {
        return textnama;
    }

    public void setTextnama(JTextField textnama) {
        this.textnama = textnama;
    }

    public JTextField getTextpoint() {
        return textpoint;
    }

    public void setTextpoint(JTextField textpoint) {
        this.textpoint = textpoint;
    }

    public JButton getButtonsimpan() {
        return buttonsimpan;
    }

    public void setButtonsimpan(JButton buttonsimpan) {
        this.buttonsimpan = buttonsimpan;
    }

    public JButton getButtonhapus() {
        return buttonhapus;
    }

    public void setButtonhapus(JButton buttonhapus) {
        this.buttonhapus = buttonhapus;
    }

    public JButton getButtonedit() {
        return buttonedit;
    }

    public void setButtonedit(JButton buttonedit) {
        this.buttonedit = buttonedit;
    }
}