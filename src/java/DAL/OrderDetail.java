/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    int OrderID;
    int ProductID;
    double UnitPrice;
    int Quantity;
    double Discount;

    public OrderDetail(int OrderID, int ProductID, double UnitPrice, int Quantity, double Discount) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Discount = Discount;
    }

    public OrderDetail(int OrderID, int ProductID, double UnitPrice, int Quantity) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderID=" + OrderID + ", ProductID=" + ProductID + ", UnitPrice=" + UnitPrice + ", Quantity=" + Quantity + ", Discount=" + Discount + '}';
    }
    
    
}
