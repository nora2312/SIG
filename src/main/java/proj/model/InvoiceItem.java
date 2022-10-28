/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.model;

import java.util.ArrayList;

/**
 *
 * @author noura.zakarya
 */
public class InvoiceItem {
private int Invnum ;
private String ItemName;
private int Count;
private double Price;
private  InvoiceHeader Invoice ;

    public InvoiceItem(int Invnum, String ItemName, int Count, double Price, InvoiceHeader Invoice) {
        this.Invnum = Invnum;
        this.ItemName = ItemName;
        this.Count = Count;
        this.Price = Price;
        this.Invoice = Invoice;
    }

    public InvoiceItem(int Invnum, String ItemName, int Count, double Price) {
        this.Invnum = Invnum;
        this.ItemName = ItemName;
        this.Count = Count;
        this.Price = Price;
    }


 public double getTotal(){
 return Count*Price;
 }

    public int getInvnum() {
        return Invnum;
    }

    public void setInvnum(int Invnum) {
        this.Invnum = Invnum;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public InvoiceHeader getInvoice() {
      if (Invoice == null){
      Invoice = new InvoiceHeader(Invnum, ItemName, ItemName);
      }
        return Invoice;
    }

    public void setInvoice(InvoiceHeader Invoice) {
        this.Invoice = Invoice;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "Invnum=" + Invnum + ", ItemName=" + ItemName + ", Count=" + Count + ", Price=" + Price + ", Invoice=" + Invoice + '}';
    }
        public String  getAsFormatCSV()
    {
        return Invnum + "," + ItemName+","+Price+","+Count;
    }

   



  
   
    
}
