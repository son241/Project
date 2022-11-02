/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
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
public class ProductManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {
            int pageTh;
            try {
                pageTh = Integer.parseInt(req.getParameter("page"));
            } catch (Exception e) {
                pageTh = 1;
            }
            final int sizePerPage = 4;
            int numberOfPage = 0;
            int listSize = 0;
            int categoryFilter;
            try {
                categoryFilter = Integer.parseInt(req.getParameter("category"));
            } catch (Exception e) {
                categoryFilter = -1;
            }
            String txtSearch;
            try {
                txtSearch = req.getParameter("txtSearch").trim();
            } catch (Exception e) {
                txtSearch = "";
            }

            String sql = "Select * From Products";

            if (categoryFilter != -1) {
                sql = "SELECT * FROM Products\n"
                        + "where CategoryID = " + categoryFilter + "\n"
                        + "Order by ProductID\n";
            }

            if (!txtSearch.isEmpty()) {
                sql = "SELECT * FROM Products\n"
                        + "where ProductName LIKE '%" + txtSearch + "%'\n";
            }

            if (!txtSearch.isEmpty() && categoryFilter != -1) {
                sql = "SELECT * FROM Products\n"
                        + "where ProductName LIKE '%" + txtSearch + "%' and CategoryID = " + categoryFilter + "\n"
                        + "Order by ProductID\n";
            }

            ArrayList<Product> allItem = new ProductDAO().getProductListBySqlQuery(sql);
            ArrayList<Product> subList = new MyGeneric<Product>(allItem).dataPaging(pageTh, sizePerPage);
            listSize = allItem.size();

            numberOfPage = numberOfPage(sizePerPage, listSize);

            req.setAttribute("numberOfPage", numberOfPage);
            req.setAttribute("subList", subList);
            req.setAttribute("categoryDAO", new CategoryDAO());
            req.setAttribute("currentPage", pageTh);
            req.setAttribute("categoryFilter", categoryFilter);
            req.setAttribute("txtSearch", txtSearch);
            req.setAttribute("deleteID", req.getAttribute("deleteID"));
            req.setAttribute("success-delete-msg", req.getAttribute("success-delete-msg"));

            req.getRequestDispatcher("../product.jsp").forward(req, resp);
        } else {
            resp.sendError(401);
        }
    }

    private int numberOfPage(int numberOfItem, int listSize) {
        if (listSize % numberOfItem == 0) {
            return listSize / numberOfItem;
        } else {
            return listSize / numberOfItem + 1;
        }
    }

    private ArrayList<Product> getAllItemBySql(String sql) {
        return new ProductDAO().getProductListBySqlQuery(sql);
    }

}
