/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proj.model.InvoiceHeader;
import proj.model.InvoiceItem;
import proj.view.InvoiceDialog;
import proj.view.InvoicesTableModel;
import proj.view.ItemDialog;
import proj.view.ItemsTableModule;
import proj.view.ProjFram;

/**
 *
 * @author noura.zakarya
 */
public class InvController implements ActionListener, ListSelectionListener {

    private ProjFram frame;
    InvoiceDialog invoiceDialog;
    ItemDialog itemDialog;

    public InvController(ProjFram fram) {
        this.frame = fram;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New Invoice":
                newInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Item":
                newItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "Load Data":
                loadData();
                break;
            case "Save Data":
                saveData();
                break;
            case "newInvoiceOk":
                newInvoiceOk();
                break;
            case "newInvoiceCancel":
                newInvoiceCancel();
                break;
            case "NewItemOk":
                NewItemOk();
                break;
            case "NewItemCancel":
                NewItemCancel();
                break;

        }
    }

    private void newInvoice() {
        invoiceDialog = new InvoiceDialog(frame);
        invoiceDialog.setVisible(true);
        invoiceDialog.setSize(500, 150);
        invoiceDialog.setLocation(50, 50);
        invoiceDialog.setTitle("Create New Invoice");
        invoiceDialog.setLocation(50, 50);
    }

    private void deleteInvoice() {
        int selectedIndex = frame.getHeaderTbl().getSelectedRow();
        if (selectedIndex != -1) {
            ArrayList< InvoiceHeader> invoices = frame.getInvoices();
            InvoiceHeader invoice = frame.getInvoices().get(selectedIndex);

            ItemsTableModule itemtable = new ItemsTableModule(invoice.getItem());
            ArrayList<InvoiceItem> item = itemtable.getItems();

            item.removeAll(item);

            invoices.remove(selectedIndex);
            frame.getInvoiceTable().fireTableDataChanged();
            frame.getItemsTbl().setModel(itemtable);
            frame.ChangeName(frame.getItemsTbl(), 0, "No.");
            frame.ChangeName(frame.getItemsTbl(), 1, "Item Name");
            frame.ChangeName(frame.getItemsTbl(), 2, "Item Price");
            frame.ChangeName(frame.getItemsTbl(), 3, "Item Count");
            frame.ChangeName(frame.getItemsTbl(), 4, "Total");
            itemtable.fireTableDataChanged();
            frame.getItemsTable().fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(frame, "You Should Select InvoiceHeader", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newItem() {
        int selectedRow = frame.getHeaderTbl().getSelectedRow();
        if (selectedRow != -1) {
            itemDialog = new ItemDialog(frame);
            itemDialog.setVisible(true);
            itemDialog.setSize(400, 150);
            itemDialog.setLocation(50, 50);
            itemDialog.setTitle("Create New Item");
        } else {
            JOptionPane.showMessageDialog(frame, "You Should Select Invoice Header", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void deleteItem() {
        int seletedRowInv = frame.getHeaderTbl().getSelectedRow();
        int selectRowItem = frame.getItemsTbl().getSelectedRow();
        if (selectRowItem != -1 && seletedRowInv != -1) {
            InvoiceHeader invoice = frame.getInvoices().get(seletedRowInv);
            invoice.getItem().remove(selectRowItem);

            InvoicesTableModel invoicesTableModel = new InvoicesTableModel(frame.getInvoices());
            frame.getHeaderTbl().setModel(invoicesTableModel);
            frame.getInvoiceTable().fireTableDataChanged();

            ItemsTableModule itemtable = new ItemsTableModule(invoice.getItem());
            frame.getItemsTbl().setModel(itemtable);
            itemtable.fireTableDataChanged();
            frame.getInvoiceTable().fireTableDataChanged();
        } else if (seletedRowInv == -1) {
            JOptionPane.showMessageDialog(frame, "You Should Select Invoice Header", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "You Should Select Item", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getHeaderTbl().getSelectedRow();
        if (selectedIndex != -1) {
            System.err.println("" + selectedIndex);
            InvoiceHeader currentInvHeader = frame.getInvoices().get(selectedIndex);
            frame.getInvNumlbl().setText("" + currentInvHeader.getInvNum());
            frame.getInvdatelbl().setText("" + currentInvHeader.getInvDate());
            frame.getCuslbl().setText(currentInvHeader.getCustName());
            frame.getTotallbl().setText("" + currentInvHeader.getTotalInv());
            ItemsTableModule items = new ItemsTableModule(currentInvHeader.getItem());
            frame.getItemsTbl().setModel(items);
            frame.ChangeName(frame.getItemsTbl(), 0, "No.");
            frame.ChangeName(frame.getItemsTbl(), 1, "Item Name");
            frame.ChangeName(frame.getItemsTbl(), 2, "Item Price");
            frame.ChangeName(frame.getItemsTbl(), 3, "Item Count");
            frame.ChangeName(frame.getItemsTbl(), 4, "Total");

            items.fireTableDataChanged();

        }

    }

    private void loadData() {
        ArrayList<InvoiceHeader> headerlist = new ArrayList<>();
        ArrayList<InvoiceItem> itemlist = new ArrayList<>();
        JFileChooser fc = new JFileChooser();
        try {
            int i = fc.showOpenDialog(null);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Path filepath = Paths.get(file.getAbsolutePath());

                java.util.List<String> headerlines = Files.readAllLines(filepath);
                try {
                    for (String line : headerlines) {
                        String[] lineparts = line.split(",");

                        int invnum = Integer.parseInt(lineparts[0]);
                        String date = lineparts[1];
                        String invname = lineparts[2];
                        InvoiceHeader header = new InvoiceHeader(invnum, date, invname);
                        headerlist.add(header);
                    }
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(frame, "Wrong File Format", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "File Not Exits", "Error", JOptionPane.ERROR_MESSAGE);

        }

        int x = fc.showOpenDialog(null);
        if (x == JFileChooser.APPROVE_OPTION) {
            File files = fc.getSelectedFile();
            Path filepaths = Paths.get(files.getAbsolutePath());
            try {
                if (filepaths != null) {
                    java.util.List<String> itemlines = Files.readAllLines(filepaths);
                    try {
                        for (String item : itemlines) {
                            String[] itemsparts = item.split(",");
                            int itemnum = Integer.parseInt(itemsparts[0]);
                            String itemname = itemsparts[1];
                            double price = Double.parseDouble(itemsparts[2]);
                            int count = Integer.parseInt(itemsparts[3]);
                            InvoiceHeader headerInv = null;
                            for (InvoiceHeader headerInvs : headerlist) {

                                if (itemnum == headerInvs.getInvNum()) {
                                    headerInv = headerInvs;
                                    break;
                                }
                            }
                            InvoiceItem itemObj = new InvoiceItem(itemnum, itemname, count, price, headerInv);
                            InvoiceItem itemss = new InvoiceItem(itemnum, itemname, count, price);
                            headerInv.getItem().add(itemObj);

                            itemlist.add(itemss);
                        }
                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(frame, "Wrong File Format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    frame.setInvoices(headerlist);
                    InvoicesTableModel invoiceTableModel = new InvoicesTableModel(headerlist);

                    frame.setInvoiceTable(invoiceTableModel);
                    frame.getHeaderTbl().setModel(invoiceTableModel);
                    frame.ChangeName(frame.getHeaderTbl(), 0, "No.");
                    frame.ChangeName(frame.getHeaderTbl(), 1, "Data");
                    frame.ChangeName(frame.getHeaderTbl(), 2, "Customer");
                    frame.ChangeName(frame.getHeaderTbl(), 3, "Total");

                    frame.getInvoiceTable().fireTableDataChanged();

                    frame.getNewInvBtn().setEnabled(true);
                    frame.getDeltInvBtn().setEnabled(true);
                    frame.getNewItemBtn().setEnabled(true);
                    frame.getDelItemBtn().setEnabled(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "File2 Not Exits ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


private void saveData() {
        ArrayList<InvoiceHeader> invoices = frame.getInvoices();
        if (invoices != null) {
            String headrs = "";
            String items = "";
            for (InvoiceHeader inv : invoices) {
                String headrCSV = inv.getAsFormatCSV();
                headrs += headrCSV;
                headrs += "\n";

                for (InvoiceItem item : inv.getItem()) {
                    String itemCsv = item.getAsFormatCSV();
                    items += itemCsv;
                    items += "\n";
                }
            }
            try {
                JFileChooser fc = new JFileChooser();
                int result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION);
                {
                    File headerWriter = fc.getSelectedFile();
                    FileWriter headerFw = new FileWriter(headerWriter);
                    headerFw.write(headrs);
                    headerFw.flush();
                    headerFw.close();
                }
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File itemWriter = fc.getSelectedFile();
                    FileWriter ItemFw = new FileWriter(itemWriter);
                    ItemFw.write(items);
                    ItemFw.flush();
                    ItemFw.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "You Should Upload Files ", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    @SuppressWarnings("empty-statement")
private void newInvoiceOk() {

        String customerName = invoiceDialog.getCustomerName().getText();
        String invoiceDate = invoiceDialog.getInvoiceDate().getText();
        int invnum = frame.getNextInv() + 1;
        try {
            String[] DateParts = invoiceDate.split("-");
            int day = Integer.parseInt(DateParts[0]);
            int month = Integer.parseInt(DateParts[1]);
            if (DateParts.length < 3) {
                JOptionPane.showMessageDialog(frame, "Wrong Data Format, should be DD-MM-YYYY ", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (day > 32 || month > 12) {

                JOptionPane.showMessageDialog(frame, "Wrong Data Format, should be DD-MM-YYYY", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                InvoiceHeader invoice = new InvoiceHeader(invnum, invoiceDate, customerName);
                frame.getInvoices().add(invoice);
                frame.getInvoiceTable().fireTableDataChanged();
                this.invoiceDialog.dispose();
                invoiceDialog = null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Wrong Data Format, should be DD-MM-YYYY", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void newInvoiceCancel() {
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    private void NewItemOk() {

        String ItemName = itemDialog.getItemNameTxt().getText();
        try {
            double Price = Double.parseDouble(itemDialog.getItemPriceTxt().getText());
            int count = Integer.parseInt(itemDialog.getItemCountTxt().getText());

            // try {
            int selectedInvoice = frame.getHeaderTbl().getSelectedRow();

            if (selectedInvoice != -1) {
                InvoiceHeader invHeader = frame.getInvoices().get(selectedInvoice);
                InvoiceItem invItem = new InvoiceItem(invHeader.getInvNum(), ItemName, count, Price, invHeader);
                // invItem.getTotal();
                invHeader.getItem().add(invItem);
                ItemsTableModule itemTableModule = (ItemsTableModule) frame.getItemsTbl().getModel();
                itemTableModule.getItems();
                itemTableModule.fireTableDataChanged();
                frame.getInvoiceTable().fireTableDataChanged();

            }
            //} catch (Exception e) {
            // System.out.println("check point");
            // JOptionPane.showMessageDialog(frame, "You Should Select InvoiceHeader", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(frame, "Invaild format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        itemDialog.dispose();
        itemDialog = null;
    }

    private void NewItemCancel() {
        itemDialog.dispose();
        itemDialog = null;
    }

}
