/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import DAL.Employee;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class EmployeeDAO extends DBContext {

    public Employee getEmployee(int id) {
        Employee e = null;
        try {
            String sql = "select * from Employees\n"
                    + "where EmployeeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                int DepartmentID = rs.getInt("DepartmentID");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");

                e = new Employee(EmployeeID, LastName, FirstName, DepartmentID, Title, TitleOfCourtesy, BirthDate, HireDate, Address);
            }
        } catch (Exception ex) {
        }
        return e;
    }
}
