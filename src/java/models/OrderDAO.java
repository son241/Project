/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import DAL.Order;
import DAL.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    public ArrayList<Order> getOrders(String customerID) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            String sql = "select * from Orders\n"
                    + "where CustomerID = ?\n"
                    + "order by OrderID desc";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerID);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = Date.valueOf(rs.getString("OrderDate").split(" ")[0]);
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Order o = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                orderList.add(o);
            }
        } catch (SQLException e) {
        }
        return orderList;
    }

    public Order getOrder(int orderID) {
        Order o = null;
        try {
            String sql = "select * from Orders\n"
                    + "where OrderID= ?\n";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = Date.valueOf(rs.getString("OrderDate").split(" ")[0]);
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                o = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                
            }
        } catch (SQLException e) {
        }
        return o;
    }

    public ArrayList<Order> getOrdersBySqlQurey(String sql) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Order o = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                orderList.add(o);
            }
        } catch (SQLException e) {
        }
        return orderList;
    }

    public Order addOrder(Order o) {
        int status = 0;
        Order output = null;
        try {
            String sql = "insert into Orders(CustomerID, OrderDate, RequiredDate, ShipAddress, Freight)\n"
                    + "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(o.getCustomerID()));
            ps.setString(2, f.format(o.getOrderDate()));
            ps.setString(3, f.format(o.getRequiredDate()));
            ps.setString(4, o.getShipAddress());
            try {
                ps.setString(5, String.valueOf(o.getFreight()));
            } catch (Exception e) {
                ps.setString(5, "0");
            }

            status = ps.executeUpdate();

            if (status != 0) {
                String sqlGetLastOrder = "select * from Orders\n"
                        + "where OrderID = (select max(OrderID) from Orders)";

                //Buoc 2: Tạo đối tượng PrepareStatement
                PreparedStatement ps1 = connection.prepareStatement(sqlGetLastOrder);

                //Buoc 3: Thực thi truy vấn
                ResultSet rs = ps1.executeQuery();

                //Buoc 4: Xử lý kết quả trả về
                while (rs.next()) {
                    //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                    int OrderID = rs.getInt("OrderID");
                    String CustomerID = rs.getString("CustomerID");
                    Date OrderDate = rs.getDate("OrderDate");
                    Date RequiredDate = rs.getDate("RequiredDate");
                    double Freight = Double.parseDouble(rs.getString("Freight"));
                    String ShipAddress = rs.getString("ShipAddress");

                    output = new Order(OrderID, CustomerID, OrderDate, RequiredDate, ShipAddress);
                }
            }

        } catch (SQLException e) {
        }
        return output;
    }

    public int updateOrder(String sql) {
        int result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }
    
    public double getDataNumber(String sql){
        double result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                result = rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return result;
    }

}
