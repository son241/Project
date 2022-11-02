/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.Account;
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
public class AccountDAO extends DBContext {

    public Account getAccount(String email, String pass) {
        Account acc = null;
        try {
            String sql = "select * from Accounts\n"
                    + "where Email =? and Password =?";
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Set giá trị cho các tham số của 'sql'
            ps.setString(1, email);
            ps.setString(2, pass);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (SQLException e) {
        }
        return acc;
    }

    public ArrayList<Account> getAccountList() {
        ArrayList<Account> list = new ArrayList<Account>();
        try {
            String sql = "select * from Accounts";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);

            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                list.add(new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int addAccount(Account acc) {
        int i = 0;
        try {
            String sql = "insert into Accounts\n"
                    + "values(?, ?, ?, null, 2)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getEmail());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getCustomerID());
            i = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return i;
    }

    public static void main(String[] args) {
        Account acc = new AccountDAO().getAccount("cust1@gmail.com", "123");
        System.out.println(acc);
    }
}
