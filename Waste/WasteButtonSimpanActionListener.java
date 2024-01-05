package Waste;

import java.lang.reflect.Waste;
import java.util.UUID;

import dropbox.Dropbox;

import java.awt.event.*;

public class WasteButtonSimpanActionListener implements ActionListener {
    private WasteFrame wasteFrame;
    private WasteDao wasteDao;

    public WasteButtonSimpanActionListener(WasteFrame wasteFrame, WasteDao wasteDao) {
        this.wasteDao = wasteDao;
        this.wasteFrame = wasteFrame;
    }

    public void actionPerformed(ActionEvent e) {
        String waste = this.wasteFrame.getNama();
        if (waste.isEmpty()) {
            this.wasteFrame.showAlert("Nama tidak boleh Kosong");
        } else {
            Category categoryWaste = this.wasteFrame.getCategory();            
            Dropbox dropboxWaste = this.wasteFrame.getDropbox();

            Waste waste = new Waste();
            waste.setId(UUID.randomUUID().toString());
            waste.setCategory(category);
            waste.setDropbox(dropbox);

            this.wasteFrame.addWaste(waste);
            this.wasteDao.insert(waste);
        }
    }
}
