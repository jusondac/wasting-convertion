package Category;

// import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.List;
import javax.swing.JTable;
import dao.CategoriesDao;


public class CategoriesButtonHapusActionListener implements ActionListener {

    // Variabel-variabel untuk menyimpan objek BiodataFrame, BiodataDao, dan data biodata
    private CategoriesFrame categoriesFrame;
    private CategoriesDao categoriesDao;

    // Konstruktor kelas BiodataButtonHapusActionListener
    public CategoriesButtonHapusActionListener(CategoriesFrame categoriesFrame, CategoriesDao categoriesDao) {
        this.categoriesFrame = categoriesFrame;
        this.categoriesDao = categoriesDao;
    }

    // Metode yang akan dipanggil ketika tombol Hapus ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.categoriesFrame.getTable();
        int selected = table.getSelectedRow();
        
        // Mendapatkan nilai Nama dari biodata yang dipilih
        String nama = (String) table.getValueAt(selected, 0);
        
        // Mencari biodata berdasarkan nama
        Categories categories = this.categoriesDao.findByName(nama);
        
        // Menghapus data pada tabel dan database
        this.categoriesFrame.removeData(selected);
        this.categoriesDao.delete(categories);
    }
}