/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import DAL.Order;
import DAL.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO extends DBContext {

    public ArrayList<OrderDetail> getOrderDetails(int orderID) {
        ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            String sql = "select * from [Order Details]\n"
                    + "where OrderID = ?";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(orderID));
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");

                OrderDetail od = new OrderDetail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                orderDetailList.add(od);
            }
        } catch (SQLException e) {
        }
        return orderDetailList;
    }

    public int addOrderDetail(OrderDetail od) {
        int status = 0;
        try {
            String sql = "insert into [Order Details]\n"
                    + "values(?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(od.getOrderID()));
            ps.setString(2, String.valueOf(od.getProductID()));
            ps.setString(3, String.valueOf(od.getUnitPrice()));
            ps.setString(4, String.valueOf(od.getQuantity()));
            ps.setString(5, String.valueOf(od.getDiscount()));

            status = ps.executeUpdate();

        } catch (Exception e) {
        }
        return status;
    }

    public static void main(String[] args) {
        ArrayList<OrderDetail> list = new OrderDetailDAO().getOrderDetails(11075);
        for (OrderDetail orderDetail : list) {
            System.out.println(orderDetail);
        }
//        ArrayList<OrderDetail> orderDetailList = new OrderDetailDAO().getOrderDetails();
    }

}
