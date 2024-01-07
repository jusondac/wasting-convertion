package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getButtonCategory()) {
            mainFrame.showCategoryFrame();
        } else if (e.getSource() == mainFrame.getButtonDropbox()) {
            mainFrame.showDropboxFrame();
        } else if (e.getSource() == mainFrame.getButtonPrint()) {
            mainFrame.showReportFrame();
        } else if (e.getSource() == mainFrame.getButtonWaste()){
            mainFrame.showWasteFrame();
        }
    }
}
