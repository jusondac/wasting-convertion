package Waste;
import Category.Categories;
import Dropbox.Dropbox;
 
public class Waste {
    // Deklarasi variabel-variabel yang akan menyimpan data biodata
    private String id;
    private Dropbox dropbox;
    private Categories category;

    // Setter untuk mengatur nilai variabel id
    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
    
    public void setDropbox(Dropbox dropbox) {
        this.dropbox = dropbox;
    }
  
    // Getter untuk mendapatkan nilai variabel id
    public String getId() {
        return id;
    }
     
    public Categories getCategory() {
        return category;
    }

    public Dropbox getDropbox() {
        return dropbox;
    }
}
