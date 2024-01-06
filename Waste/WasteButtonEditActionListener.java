package Waste;

import java.util.List;
import java.awt.event.*;

import javax.swing.JTable;

import dao.WasteDao;

/**
 * WasteButtonEditActionListener
 */
public class WasteButtonEditActionListener implements ActionListener {
    private final List<Waste> wasteList;
    private WasteFrame wasteFrame;
    private WasteDao wasteDao;

    public WasteButtonEditActionListener(WasteFrame wasteFrame, WasteDao wasteDao, List<Waste> waste) {
        this.wasteDao = wasteDao;
        this.wasteFrame = wasteFrame;
        this.wasteList = waste;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.wasteFrame.getTable();
        int selected = table.getSelectedRow();
        String nama = (String) table.getValueAt(selected, 0);
        if (this.wasteFrame.isEmptyField()) {
            wasteFrame.setTextFieldNama(nama);
        } else {
            Waste updateWaste = new Waste();
            Waste waste = wasteList.get(selected);
            updateWaste.setId(waste.getId());
            // updateWaste.setCategory(wasteFrame.getCategory);
            // updateWaste.setDropbox(wasteFrame.getDropbox);
            // this.wasteDao.update(updateWaste);
            this.wasteFrame.updateWaste(updateWaste, selected);
            wasteFrame.setTextFieldNama("");
        }
    }

}