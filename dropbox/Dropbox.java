package Dropbox;

public class Dropbox {

    // Deklarasi variabel-variabel yang akan menyimpan data biodata
    private String id;
    private String location;
    private String point;

   public void setId(String id) {
       this.id = id;
   }

   public void setLocation(String location) {
       this.location = location;
   }
   
   public void setPoint(String point) {
       this.point = point;
   }

   public String getId() {
       return id;
   }

   public String getLocation() {
       return location;
   }

   public String getPoint() {
       return point;
   }
}
