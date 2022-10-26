/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.view;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import proj.model.InvoiceHeader;

public class InvoicesTableModel extends AbstractTableModel {

    ArrayList<InvoiceHeader> invoices;
    String[] col = new String[]{"No.", "Date", "CustomerName", "TotalInvoice"};

    public InvoicesTableModel(ArrayList<InvoiceHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {

        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoice.getInvNum();
            case 1:
                return invoice.getInvDate();
            case 2:
                return invoice.getCustName();
            case 3:
                return invoice.getTotalInv();

            default:
                return "";
        }

    }

    public void setValueAt(InvoiceHeader aValue, int rowIndex, int columnIndex) {
        if (rowIndex != -1){
        InvoiceHeader invoice = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0:
                invoice.setInvNum(aValue.getInvNum());
                break;
            case 1:
                invoice.setInvDate(aValue.getInvDate());
                break;

            case 2:
                invoice.setCustName(aValue.getCustName());
                break;

        }
        fireTableDataChanged();
    }}


}
