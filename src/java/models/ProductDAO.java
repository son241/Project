/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import DAL.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    // Action: Read all products
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "Select * From Products";
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);

            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                //Khởi tạo đối tượng kiểu Product
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                //Bổ sung 'p' vào 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public Product getProduct(int productID) {
        ArrayList<Product> list = new ProductDAO().getProducts();
        for (Product item : list) {
            if (item.getProductID() == productID) {
                return item;
            }
        }
        return null;
    }

    public int deleteProduct(Product p) {
        int result = 0;
        try {
            String sql1 = "ALTER TABLE [dbo].[Order Details] DROP CONSTRAINT [FK_Order_Details_Products]";
            String sql2 = "delete Products\n"
                    + "where ProductID =?\n";
            String sql3 = "ALTER TABLE [dbo].[Order Details]  WITH NOCHECK ADD  CONSTRAINT [FK_Order_Details_Products] FOREIGN KEY([ProductID])\n"
                    + "REFERENCES [dbo].[Products] ([ProductID])";

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps2.setString(1, String.valueOf(p.getProductID()));

            ps1.executeUpdate();
            result = ps2.executeUpdate();
            ps3.executeUpdate();

        } catch (Exception e) {
        }
        return result;
    }

   

    public ArrayList<Product> getProductListBySqlQuery(String sql) {
        ArrayList<Product> list = new ArrayList<Product>();

        try {
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);

            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                //Khởi tạo đối tượng kiểu Product
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                //Bổ sung 'p' vào 'products'
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int addProduct(Product p) {
        int result = 0;
        try {
            String sql = "insert Products\n"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, String.valueOf(p.getCategoryID()));
            ps.setString(3, p.getQuantityPerUnit());
            ps.setString(4, String.valueOf(p.getUnitPrice()));
            ps.setString(5, String.valueOf(p.getUnitsInStock()));
            ps.setString(6, String.valueOf(p.getUnitsOnOrder()));
            ps.setString(7, String.valueOf(p.getReorderLevel()));
            ps.setString(8, String.valueOf(p.isDiscontinued()));

            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }

    public int updateProduct(Product p) {
        int result = 0;
        try {
            String sql = "update Products\n"
                    + "set ProductName = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice = ?, UnitsInStock = ?, UnitsOnOrder = ?, ReorderLevel = ?, Discontinued = ?\n"
                    + "where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, String.valueOf(p.getCategoryID()));
            ps.setString(3, p.getQuantityPerUnit());
            ps.setString(4, String.valueOf(p.getUnitPrice()));
            ps.setString(5, String.valueOf(p.getUnitsInStock()));
            ps.setString(6, String.valueOf(p.getUnitsOnOrder()));
            ps.setString(7, String.valueOf(p.getReorderLevel()));
            ps.setString(8, String.valueOf(p.isDiscontinued()));
            ps.setString(9, String.valueOf(p.getProductID()));

            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }

//    public static void main(String[] args) {
//
//        ArrayList<Product> list = new ProductDAO().getProductsByPageWithProductName(1, 4, "%a%", 1);
//        for (Product item : list) {
//            System.out.println(item.toString());
//        }
//    }
}
