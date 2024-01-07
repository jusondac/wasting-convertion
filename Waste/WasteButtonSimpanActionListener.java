package Waste;

//import java.lang.reflect.Waste;
import java.util.UUID;

import Dropbox.Dropbox;

import java.awt.event.*;
import Category.Categories;
import Dropbox.Dropbox;
import dao.WasteDao;

public class WasteButtonSimpanActionListener implements ActionListener {
    private WasteFrame wasteFrame;
    private WasteDao wasteDao;
//    private WasteDao wasteDao;

    public WasteButtonSimpanActionListener(WasteFrame wasteFrame, WasteDao wasteDao) {
        this.wasteDao = wasteDao;
        this.wasteFrame = wasteFrame;
    }

    public void actionPerformed(ActionEvent e) {
        Categories category = this.wasteFrame.getCategory();
        Dropbox dropbox = this.wasteFrame.getDropbox();

        Waste waste = new Waste();
        waste.setId(UUID.randomUUID().toString());
        waste.setCategory(category);
        waste.setDropbox(dropbox);

        this.wasteFrame.addWaste(waste);
        this.wasteDao.insert(waste);
    }
}
