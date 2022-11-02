/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.Category;
import DAL.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getCategory() {
        ArrayList<Category> list = new ArrayList<Category>();
        try {
            String sql = "Select * from Categories";
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");
                list.add(new Category(CategoryID, CategoryName, Description, Picture));
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public Category getCategory(int id){
        ArrayList<Category> list = new CategoryDAO().getCategory();
        for (Category item : list) {
            if (item.getCategoryID() == id) {
                return item;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        ArrayList<Category> list = new CategoryDAO().getCategory();
        for (Category category : list) {
            System.out.println(category);
        }
    }
}
