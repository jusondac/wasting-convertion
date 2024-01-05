package Waste;

import java.lang.reflect.Member;
import java.util.List;
import java.awt.event.*;

import javax.swing.JTable;

/**
 * WasteButtonHapusActionListener
 */
public class WasteButtonHapusActionListener implements ActionListener{

    private WasteFrame wasteFrame;
    private WasteFrame wasteDao;
    private List<Waste> data;

    public WasteButtonHapusActionListener(WasteFrame wasteFrame, WasteFrame wasteDao) {
        this.wasteFrame = wasteFrame;
        this.wasteDao = wasteDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.wasteFrame.getTable();
        int selected = table.getSelectedRow();
        String category = (String) table.getValueAt(selected, 0);
        Waste waste = this.wasteDao.findByCategory(category);
        this.wasteFrame.removeData(selected);
        this.wasteDao.delete(waste);
    }
}