package Waste;

public class Waste {
    // Deklarasi variabel-variabel yang akan menyimpan data biodata
    private String type;
    private String dropbox;
    private String category;

    // Setter untuk mengatur nilai variabel id
    public void setType(String type) {
        this.type = type;
    }

    // Setter untuk mengatur nilai variabel nama
    public void setDropbox(String dropbox) {
        this.dropbox = dropbox;
    }

    // Setter untuk mengatur nilai variabel Point
    public void setCategory(String category) {
        this.category = category;
    }

    // Getter untuk mendapatkan nilai variabel id
    public String getType() {
        return type;
    }

    // Getter untuk mendapatkan nilai variabel nama
    public String getDropbox() {
        return dropbox;
    }

    // Getter untuk mendapatkan nilai variabel Point
    public String getCategory() {
        return category;
    }
}
