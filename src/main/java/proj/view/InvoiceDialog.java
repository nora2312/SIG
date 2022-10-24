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

public class InvoiceDialog extends JDialog {

    private JLabel cusNametxt;
    private JLabel invDate;
    private JButton okBtn;
    private JButton cancelBtn;
    private JTextField customerName;
    private JTextField InvoiceDate;

    public InvoiceDialog(ProjFram fram) {
        cusNametxt = new JLabel("Customer Name");
        customerName = new JTextField(20);

        invDate = new JLabel("Invoice Date");
        InvoiceDate = new JTextField(20);

        okBtn = new JButton("Ok");
        cancelBtn = new JButton("cancel");
        
        okBtn.setActionCommand("newInvoiceOk");
        okBtn.addActionListener(fram.getController());
        
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        
        cancelBtn.addActionListener(fram.getController());
        
        setLayout(new GridLayout(3, 2));
        add(cusNametxt);
        add(customerName);
        add(invDate);
        add(InvoiceDate);
        add(okBtn);
        add(cancelBtn);
        
        

    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JTextField customerName) {
        this.customerName = customerName;
    }

    public JTextField getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(JTextField InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }
    
  

}
