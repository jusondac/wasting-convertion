package Waste;

import dao.WasteDao;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
/**
 * WasteButtonHapusActionListener
 */
public class WasteButtonHapusActionListener implements ActionListener{

    private WasteFrame wasteFrame;
    private WasteDao wasteDao;
    private List<Waste> data;

    public WasteButtonHapusActionListener(WasteFrame wasteFrame, WasteDao wasteDao) {
        this.wasteFrame = wasteFrame;
        this.wasteDao = wasteDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.wasteFrame.getTable();
        int selected = table.getSelectedRow();
        String id = (String) table.getValueAt(selected, 0);
        this.wasteFrame.removeData(selected);
        this.wasteDao.delete(id);
    }
}