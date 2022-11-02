/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Category;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;
import models.MyGeneric;
import models.ProductDAO;

/**
 *
 * @author Admin
 */
public class ProductListController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageTh;
        final int sizePerPage = 12;
        try {
            pageTh = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            pageTh = 1;
        }
        
        int categoryID = Integer.parseInt(req.getParameter("category-id"));       
        ArrayList<Category> categoryList = new CategoryDAO().getCategory();
        ArrayList<Product> output = getProductsByPageWithCategoryID(pageTh, sizePerPage, categoryID);
        
        req.setAttribute("currentPage", pageTh);
        req.setAttribute("numberOfPage", getNumberOfPage(getListSize(categoryID), sizePerPage));
        req.setAttribute("c", new CategoryDAO().getCategory(categoryID));
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("productList", output);
//        resp.getWriter().println(categoryID);
        req.getRequestDispatcher("category.jsp").forward(req, resp);
    }
    
    int getListSize(int cateID){
        ArrayList<Product> list = new ProductDAO().getProducts();
        int count = 0;
        for (Product item : list) {
            if (item.getCategoryID() == cateID) {
                count++;
            }
        }
        return count;
    }
    
    int getNumberOfPage(int listSize, int sizePerPage){
        if (listSize % sizePerPage == 0) {
            return listSize / sizePerPage;
        }else{
            return listSize / sizePerPage + 1;
        }
    }
    
     public ArrayList<Product> getProductsByPageWithCategoryID(int pageTh, int sizePerPage, int categoryID) {
        ArrayList<Product> list = new ArrayList<Product>();
        ArrayList<Product> allItem = new ArrayList<Product>();
        int offset = pageTh * sizePerPage - sizePerPage;

        String sql = "SELECT * FROM Products\n"
                + "where CategoryID = " + categoryID + "\n"
                + "Order by ProductID\n";
        allItem = new ProductDAO().getProductListBySqlQuery(sql);
        list = new MyGeneric<Product>(allItem).dataPaging(pageTh, sizePerPage);
        return list;
    }
}
