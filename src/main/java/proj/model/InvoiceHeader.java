/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author noura.zakarya
 */
public class InvoiceHeader {
    
    private int InvNum;
    private String InvDate;
    private String CustName;
    private ArrayList<InvoiceItem> Item ;
   

    public InvoiceHeader(int InvNum, String InvDate, String CustName) {
        this.InvNum = InvNum;
        this.InvDate = InvDate;
        this.CustName = CustName;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public int getInvNum() {
      
        return InvNum;
    }

    public void setInvNum(int InvNum) {
        this.InvNum = InvNum;
    }

    public String getInvDate() {
        return InvDate;
    }

    public void setInvDate(String InvDate) {
        this.InvDate = InvDate;
    }

    public ArrayList<InvoiceItem> getItem() {
        if( Item == null){
        Item =new ArrayList();
        }
        return Item;
    }

    public void setItem(ArrayList<InvoiceItem> Item) {
        this.Item = Item;
    }

    @Override
    public String toString() {
        return "Header{" + "InvNum=" + InvNum + ", InvDate=" + InvDate + ", CustName=" + CustName + '}';
    }
    
    public double getTotalInv(){
        double total = 0;
    for (InvoiceItem item : getItem()){
         total+=item.getTotal();
    }
        return total;
    }
      public String  getAsFormatCSV()
    {
        return InvNum + "," + InvDate +","+CustName;
    }
    
    
}
