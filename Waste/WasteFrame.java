package Waste;

import java.lang.reflect.Waste;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WasteFrame {
    this.wasteList=wasteDao.findAll();this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.wasteDao=wasteDao;this.jenisWasteDao=jenisWasteDao;

    JLabel labelInput = new JLabel("Nama: ");labelInput.setBounds(15,40,350,10);

    textFieldNama=new JTextField();textFieldNama.setBounds(15,60,350,30);

    JLabel labelJenis = new JLabel("Jenis Waste: ");labelJenis.setBounds(15,100,350,10);

    comboJenis=new JComboBox();comboJenis.setBounds(15,120,150,30);

    JButton button = new JButton("Simpan");button.setBounds(15,160,100,40);

    JButton buttonHapus = new JButton("Hapus");buttonHapus.setBounds(115,160,100,40);

    JButton buttonEdit = new JButton("Edit");buttonEdit.setBounds(215,160,100,40);

    this.table=new JTable();
    JScrollPane scrollableTale = new JScrollPane(table);scrollableTale.setBounds(15,220,350,200);

    tableModel=new WasteTableModel(wasteList);table.setModel(tableModel);

    WasteButtonSimpanActionListener actionListener = new WasteButtonSimpanActionListener(this, wasteDao);
    WasteButtonHapusActionListener actionListenerHapus = new WasteButtonHapusActionListener(this, wasteDao);
    WasteButtonEditActionListener actionListenerEdit = new WasteButtonEditActionListener(this, wasteDao);this.wasteList);button.addActionListener(actionListener);buttonHapus.addActionListener((ActionListener)actionListenerHapus);buttonEdit.addActionListener(actionListenerEdit);

    this.add(button);this.add(buttonHapus);this.add(buttonEdit);this.add(textFieldNama);this.add(labelInput);this.add(labelJenis);this.add(comboJenis);this.add(scrollableTale);

    this.setSize(400,500);this.setLayout(null);
    }

    public boolean isEmptyField() {
        return Objects.equals(this.textFieldNama.getText(), "");
    }

    public void populateComboJenis() {
        this.jenisWasteList = this.jenisWasteDao.findAll();
        comboJenis.removeAllItems();
        for(JenisWaste jenisWaste : this.jenisWasteList) {
            comboJenis.addItem(jenisWaste.getNama());
        }
    }

    public String getNama() {return textFieldNama.getText();}

    public void setTextFieldNama(String nama) {this.textFieldNama.setText(nama);}

    public JTable getTable() { return table; }

    public JenisWaste getJenisWaste() {
        return jenisWasteList.get(comboJenis.getSelectedIndex());
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void removeData(int selected) {
//        this.wasteList.remove(selected);
        this.tableModel.remove(selected);
    }

    public void addWaste(Waste waste) {
        tableModel.add(waste);
        textFieldNama.setText("");
    }

    public void updateWaste(Waste update, int selected) {
        this.tableModel.update(update, selected);
    }

    public Category getCategory() {
        return null;
    }
}
