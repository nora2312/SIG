/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import proj.model.InvoiceItem;

public class ItemsTableModule extends AbstractTableModel{

    public ItemsTableModule(ArrayList<InvoiceItem> items) {
        this.items = items;
    }
   ArrayList< InvoiceItem> items;
   String [] col = new String []{"No.","Item Name","Item Price","Count","Item Total"};

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       InvoiceItem item = items.get(rowIndex);
        switch (columnIndex){
            case 0 : return item.getInvoice().getInvNum();
            case 1 :return item.getItemName();
            case 2 :return item.getPrice();
            case 3 :return item.getCount();
            case 4 :return item.getTotal();
            default: return "";
        }
    }

    public ArrayList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }
   
    
}
