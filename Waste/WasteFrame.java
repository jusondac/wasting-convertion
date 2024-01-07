package Waste;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

import Category.Categories;
import Dropbox.Dropbox;

import dao.CategoriesDao;
import dao.DropboxDao;
import dao.WasteDao;

public class WasteFrame extends JFrame {
    private List<Categories> categoriesList;
    private List<Dropbox> dropboxList;
    private List<Waste> wasteList;

    private WasteTableModel tableModel;

    private JComboBox comboDropbox;
    private JComboBox comboCategories;

    private WasteDao wasteDao;
    private CategoriesDao categoriesDao;
    private DropboxDao dropboxDao;
    private JTable table;

    public WasteFrame(WasteDao wasteDao, CategoriesDao CategoriesDao, DropboxDao dropboxDao)  {
        this.wasteList = wasteDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.wasteDao = wasteDao;
        this.dropboxDao = dropboxDao;
        this.categoriesDao = CategoriesDao;

        JLabel labelInput = new JLabel("Lokasi : ");
        labelInput.setBounds(15, 40, 350, 10);

        JLabel labelJenis = new JLabel("Kategori: ");
        labelJenis.setBounds(15,100,350,10);

        comboDropbox = new JComboBox();
        comboDropbox.setBounds(15, 60,150, 30);

        comboCategories = new JComboBox();
        comboCategories.setBounds(15, 120,150, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,160,100,40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(115,160,100,40);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(215,160,100,40);

        this.table = new JTable();
        JScrollPane scrollableTale = new JScrollPane(table);
        scrollableTale.setBounds(15,220,350,200);

        tableModel = new WasteTableModel(wasteList);
        table.setModel(tableModel);

        WasteButtonSimpanActionListener actionListener = new WasteButtonSimpanActionListener(this, wasteDao);
        WasteButtonHapusActionListener actionListenerHapus = new WasteButtonHapusActionListener(this, wasteDao);
//        WasteButtonEditActionListener actionListenerEdit = new WasteButtonEditActionListener(this, wasteDao,  this.wasteList);
        button.addActionListener(actionListener);
        buttonHapus.addActionListener(actionListenerHapus);
//        buttonEdit.addActionListener(actionListenerEdit);

        this.add(button);
        this.add(buttonHapus);
        this.add(buttonEdit);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(scrollableTale);
        this.add(comboCategories);
        this.add(comboDropbox);

        this.setSize(400,500);
        this.setLayout(null);
    }

    public void populateComboDropbox() {
        this.dropboxList = this.dropboxDao.findAll();
        comboDropbox.removeAllItems();
        for(Dropbox dropbox : this.dropboxList) {
            comboDropbox.addItem(dropbox.getLocation());
        }
    }

    public void populateComboCategory() {
        this.categoriesList = this.categoriesDao.findAll();
        comboCategories.removeAllItems();
        for(Categories category : this.categoriesList) {
            comboCategories.addItem(category.getNama());
        }
    }

    public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                WasteDao wasteDao = new WasteDao();
                CategoriesDao categoriesDao = new CategoriesDao();
                DropboxDao dropboxDao = new DropboxDao();
                public void run() {
                    WasteFrame runnable = new WasteFrame(wasteDao, categoriesDao, dropboxDao);
                    runnable.populateComboCategory();
                    runnable.populateComboDropbox();
                    runnable.setVisible(true);
                }
            });
    }
    public JTable getTable() { return table; }
    public Categories getCategory() { return categoriesList.get(comboCategories.getSelectedIndex()); }
    public Dropbox getDropbox() { return dropboxList.get(comboDropbox.getSelectedIndex()); }

    public void removeData(int selected) {
        this.tableModel.remove(selected);
    }

    public void addWaste(Waste waste) {
        tableModel.add(waste);
    }

}
