package Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
// import Category.CategoriesFrame;
import dao.CategoriesDao;
import Category.CategoriesFrame;

public class CategoriesButtonEditActionListener implements ActionListener  {
    
    // Variabel-variabel untuk menyimpan data categories dan objek-objek terkait
    private final List<Categories> categoriesList;
    private CategoriesFrame categoriesFrame;
    private CategoriesDao categoriesDao;
    
    // Konstruktor kelas categoriesButtonEditActionListener
    public CategoriesButtonEditActionListener(CategoriesFrame categoriesFrame, CategoriesDao categoriesDao, List<Categories> categoriesList) {
        this.categoriesDao = categoriesDao;
        this.categoriesFrame = categoriesFrame;
        this.categoriesList = categoriesList;
    }

    // Metode yang akan dipanggil ketika tombol Edit ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.categoriesFrame.getTable();
        int selected = table.getSelectedRow();
        
        // Mendapatkan nilai dari tabel untuk categories yang dipilih
        String name = (String) table.getValueAt(selected, 0);
        String point = (String) table.getValueAt(selected, 1);
        
        // Jika ada isian kosong pada form, mengisi form dengan data categories yang dipilih
        if (this.categoriesFrame.isEmptyField()) {
            this.categoriesFrame.setTextnama(name);
            this.categoriesFrame.setTextpoint(point);
        } else {
            // Jika form tidak kosong, membuat objek categories baru untuk diupdate
            Categories updateCategories = new Categories();
            Categories categories = categoriesList.get(selected);
            updateCategories.setId(categories.getId());
            updateCategories.setNama(this.categoriesFrame.getName());
            updateCategories.setPoint(this.categoriesFrame.getPoint());
            
            // Melakukan update data menggunakan categoriesDao
            this.categoriesDao.update(updateCategories);
            
            // Mengupdate data pada tabel dan membersihkan isian form
            this.categoriesFrame.updateCategories(updateCategories, selected);
            this.categoriesFrame.setTextnama("");
            this.categoriesFrame.setTextpoint("");
        }
    }
}
