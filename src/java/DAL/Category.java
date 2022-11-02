/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Admin
 */
public class Category {
    private int CategoryID;
    private String CategoryName;
    private String Description;
    private String Picture;

    public Category(int CategoryID, String CategoryName, String Description, String Picture) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
        this.Picture = Picture;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    @Override
    public String toString() {
        return "Category{" + "CategoryID=" + CategoryID + ", CategoryName=" + CategoryName + ", Description=" + Description + ", Picture=" + Picture + '}';
    }
    
    
    
}
