/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.Customer;
import DAL.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    public Customer getCustomer(String customerID) {
        Customer c = null;
        try {
            String sql = "select * from Customers\n"
                    + "where CustomerID =?";
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Set giá trị cho các tham số của 'sql'
            ps.setString(1, customerID);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreatedDate = rs.getDate("CreatedDate");

                c = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, CreatedDate);
            }
        } catch (SQLException e) {
        }
        return c;
    }

    public int addCustomer(Customer c) {
        int i = 0;
        try {
            String sql = "insert into Customers\n"
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getCompanyName());
            ps.setString(3, c.getContactName());
            ps.setString(4, c.getContactTitle());
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getCreatedDate().toString());
            i = ps.executeUpdate();
        } catch (Exception e) {
        }
        return i;
    }

    public int updateCustomer(Customer c) {
        int i = 0;
        try {
            String sql = "update Customers\n"
                    + "set CompanyName =?, ContactName =?, ContactTitle =?, Address =?\n"
                    + "where CustomerID =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getContactName());
            ps.setString(3, c.getContactTitle());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCustomerID());
            i = ps.executeUpdate();
        } catch (Exception e) {
        }
        return i;
    }

    
    public static void main(String[] args) {
        
    }
}
