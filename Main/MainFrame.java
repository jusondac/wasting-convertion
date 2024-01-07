package Main;
import dao.*;

 import Category.Categories;
 import Waste.Waste;
 import Dropbox.Dropbox;

import Category.CategoriesFrame;
import Dropbox.DropboxFrame;
import Waste.WasteFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {


    private CategoriesFrame categoriesFrame;
    private DropboxFrame dropboxFrame;
    private WasteFrame wasteFrame;

    private JButton buttonCategory;
    private JButton buttonDropbox;
    private JButton buttonWaste;

    private CategoriesDao categoriesDao;
    private WasteDao wasteDao;
    private DropboxDao dropboxDao;

    public MainFrame() {
        JFrame jframe = this;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(jframe, "Do you want to Exit ?", "Exit Confirmation : ", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else if (result == JOptionPane.NO_OPTION) jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);

        this.categoriesDao = new CategoriesDao();
        this.dropboxDao = new DropboxDao();
        this.wasteDao = new WasteDao();

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonCategory = new JButton("Category");
        this.buttonDropbox = new JButton("Dropbox");
        this.buttonWaste = new JButton("Waste");

        this.buttonCategory.addActionListener(actionListener);
        this.buttonDropbox.addActionListener(actionListener);
        this.buttonWaste.addActionListener(actionListener);

        this.add(buttonCategory);
        this.add(buttonDropbox);
        this.add(buttonWaste);
    }

    public JButton getButtonCategory() { return buttonCategory; }
    public JButton getButtonDropbox() { return buttonDropbox; }
    public JButton getButtonWaste() { return buttonWaste; }

    public void showCategoryFrame() {
        if(categoriesFrame == null) {
             categoriesFrame = new CategoriesFrame(categoriesDao);
        }
        categoriesFrame.setVisible(true);
    }

    public void showWasteFrame() {
        if(wasteFrame == null) {
             wasteFrame = new WasteFrame(wasteDao, categoriesDao, dropboxDao);
        }
        wasteFrame.populateComboDropbox();
        wasteFrame.populateComboCategory();
        wasteFrame.setVisible(true);
    }

    public void showDropboxFrame() {
        if(dropboxFrame == null) {
             dropboxFrame = new DropboxFrame(dropboxDao);
        }
        dropboxFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

}

