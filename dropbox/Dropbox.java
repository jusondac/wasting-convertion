package dropbox;

public class Dropbox {

    // Deklarasi variabel-variabel yang akan menyimpan data biodata
    private String id;
    private String nama;
    private String point;

    // Setter untuk mengatur nilai variabel id
    public void setId(String id) {
        this.id = id;
    }
    
    // Setter untuk mengatur nilai variabel nama
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    // Setter untuk mengatur nilai variabel Point
    public void setPoint(String point) {
        this.point = point;
    }

    // Getter untuk mendapatkan nilai variabel id
    public String getId() {
        return id;
    }
    
    // Getter untuk mendapatkan nilai variabel nama
    public String getNama() {
        return nama;
    }

    // Getter untuk mendapatkan nilai variabel Point
    public String getPoint() {
        return point;
    }
    
}
