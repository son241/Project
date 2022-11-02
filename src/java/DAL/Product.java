/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Admin
 */
public class Product {
    private int ProductID;
    private String ProductName;
    private int CategoryID;
    private String QuantityPerUnit;
    private double UnitPrice;
    private int UnitsInStock;
    private int UnitsOnOrder;
    private int ReorderLevel;
    private boolean Discontinued;

    public Product(int ProductID, String ProductName, int CategoryID, String QuantityPerUnit, double UnitPrice, int UnitsInStock, int UnitsOnOrder, int ReorderLevel, boolean Discontinued) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.QuantityPerUnit = QuantityPerUnit;
        this.UnitPrice = UnitPrice;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
        this.ReorderLevel = ReorderLevel;
        this.Discontinued = Discontinued;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getUnitsInStock() {
        return UnitsInStock;
    }

    public void setUnitsInStock(int UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    public int getUnitsOnOrder() {
        return UnitsOnOrder;
    }

    public void setUnitsOnOrder(int UnitsOnOrder) {
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public int getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(int ReorderLevel) {
        this.ReorderLevel = ReorderLevel;
    }

    public boolean isDiscontinued() {
        return Discontinued;
    }

    public void setDiscontinued(boolean Discontinued) {
        this.Discontinued = Discontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", CategoryID=" + CategoryID + ", UnitPrice=" + UnitPrice + '}';
    }
    
    
}
