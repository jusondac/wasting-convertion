package Category;

import java.util.UUID;
import dao.CategoriesDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.UUID;

public class CategoriesButtonSimpanActionListener implements ActionListener{

    // Variabel-variabel untuk menyimpan objek BiodataFrame dan BiodataDao
    private CategoriesFrame categoriesFrame;
    private CategoriesDao categoriesDao;

    // Konstruktor kelas BiodataButtonSimpanActionListener
    public CategoriesButtonSimpanActionListener(CategoriesFrame categoriesFrame, CategoriesDao categoriesDao) {
        this.categoriesFrame = categoriesFrame;
        this.categoriesDao = categoriesDao;
    }

    // Metode yang akan dipanggil ketika tombol Simpan ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengambil nilai dari form biodata
        String nama = this.categoriesFrame.getName();
        String point = this.categoriesFrame.getPoint();
        
        // Memeriksa apakah nama kosong
        if (nama.isEmpty()) {
            this.categoriesFrame.showAlert("Nama Kategori tidak boleh Kosong");
        } else if (point.isEmpty()) {
            this.categoriesFrame.showAlert("Poin tidak boleh Kosong");
        } else {
            // Jika nama tidak kosong, membuat objek Biodata baru
            Categories categories = new Categories();
            categories.setId(UUID.randomUUID().toString());
            categories.setNama(nama);
            categories.setPoint(point);


            // Menambahkan biodata ke tabel dan database
            this.categoriesFrame.addCategories(categories);
            this.categoriesDao.insert(categories);
        }
    }
}
