/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ItemDialog extends JDialog {

    private JLabel ItemNamelbl;
    private JTextField ItemNameTxt;
    private JLabel ItemPriceLbl;
    private JTextField ItemPriceTxt;
    private JLabel ItemCountLbl;
    private JTextField ItemCountTxt;
    private JButton OkBtn;
    private JButton CancelBtn;

    public ItemDialog(ProjFram fram) {
        ItemNamelbl = new JLabel("ItemName");
        ItemNameTxt = new JTextField(20);
        ItemPriceLbl = new JLabel("ItemPrice");
        ItemPriceTxt = new JTextField(20);
        ItemCountLbl = new JLabel("ItemCount");
        ItemCountTxt = new JTextField(20);
        OkBtn = new JButton("Ok");
        CancelBtn = new JButton("Cancel");

        OkBtn.setActionCommand("NewItemOk");
        OkBtn.addActionListener(fram.getController());

        CancelBtn.setActionCommand("NewItemCancel");
        CancelBtn.addActionListener(fram.getController());
        setLayout(new GridLayout(4, 2));
        add(ItemNamelbl);

        add(ItemNameTxt);

        add(ItemPriceLbl);

        add(ItemPriceTxt);

        add(ItemCountLbl);

        add(ItemCountTxt);

        add(OkBtn);

        add(CancelBtn);
    }

    public JTextField getItemNameTxt() {
        return ItemNameTxt;
    }

    public void setItemNameTxt(JTextField ItemNameTxt) {
        this.ItemNameTxt = ItemNameTxt;
    }

    public JTextField getItemPriceTxt() {
        return ItemPriceTxt;
    }

    public void setItemPriceTxt(JTextField ItemPriceTxt) {
        this.ItemPriceTxt = ItemPriceTxt;
    }

    public JTextField getItemCountTxt() {
        return ItemCountTxt;
    }

    public void setItemCountTxt(JTextField ItemCountTxt) {
        this.ItemCountTxt = ItemCountTxt;
    }

}
