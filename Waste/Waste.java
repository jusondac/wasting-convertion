package Waste;
import Category.Categories;
import dropbox.Dropbox;
 
public class Waste {
    // Deklarasi variabel-variabel yang akan menyimpan data biodata
    private String type;
    private Dropbox  dropbox;
    private Categories category;

    // Setter untuk mengatur nilai variabel id
    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
    
    public void setDropbox(Dropbox dropbox) {
        this.dropbox = dropbox;
    }
  
    // Getter untuk mendapatkan nilai variabel id
    public String getType() {
        return type;
    }
     
    public Categories getCategory() {
        return category;
    }

    public Dropbox getDropbox() {
        return dropbox;
    }
}
