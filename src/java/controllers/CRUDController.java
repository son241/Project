/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import DAL.*;
import java.io.File;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author Admin
 */
public class CRUDController extends HttpServlet {

    boolean create;
    boolean update;
    boolean delete;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {

            try {
                create = Boolean.parseBoolean(req.getParameter("create"));
            } catch (Exception e) {
                create = false;
            }

            try {
                update = Boolean.parseBoolean(req.getParameter("update"));
            } catch (Exception e) {
                update = false;
            }

            try {
                delete = Boolean.parseBoolean(req.getParameter("delete"));
            } catch (Exception e) {
                delete = false;
            }

            //Create product
            if (create) {
                requsestCreateProduct(req, resp);
            }

            //Edit product
            if (update) {
                requsestUpdateProduct(req, resp);
            }

            //Delete Product
            if (delete) {
                requsestDeleteProduct(req, resp);
            }

            if (!create && !update && !delete) {
                resp.sendError(404);
            }
        } else {
            resp.sendError(401);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (create) {
            implementCreateProduct(req, resp);
        }

        if (update) {
            implementUpdateProduct(req, resp);
        }
    }

    private void requsestCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("success-msg");
        req.setAttribute("categoryList", new CategoryDAO().getCategory());
        req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
    }

    private void implementCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = validateProductData(req);
        
        if ((boolean) req.getAttribute("status")) {
            Product p = (Product) req.getAttribute("product");
            int result = new ProductDAO().addProduct(p);
            if (result != 0) {
                req.setAttribute("success-msg", "Successfully added!");
                req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
            } else {
                req.setAttribute("error-msg", "Error occured, fail to add!");
                req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
            }

        } else {
            req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
        }

    }

    private void requsestDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteID = req.getParameter("deleteID");
        if (deleteID == null) {
            req.setAttribute("deleteID", req.getParameter("pid"));
            req.setAttribute("page", req.getParameter("currentPage"));
            req.getRequestDispatcher("manage").forward(req, resp);
        } else {
            implementDeleteProduct(req, resp);
        }
    }

    private void implementDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("deleteID"));
        int result = new ProductDAO().deleteProduct(new ProductDAO().getProduct(pid));

        if (result != 0) {
            req.setAttribute("success-delete-msg", "Successfully deleted!");
            req.removeAttribute("deleteID");
            req.getRequestDispatcher("manage").forward(req, resp);
        }
    }

    private void requsestUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
        Product p = new ProductDAO().getProduct(pid);
        ArrayList<Category> categoryList = new CategoryDAO().getCategory();

        req.setAttribute("p", p);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("../edit-product.jsp").forward(req, resp);
    }

    private void implementUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = validateProductData(req);
        if ((boolean) req.getAttribute("status")) {
            Product p = (Product) req.getAttribute("product");
            int result = new ProductDAO().updateProduct(p);
            if (result != 0) {
                req.setAttribute("success-msg", "Successfully updated!");
                req.getRequestDispatcher("../edit-product.jsp").forward(req, resp);
            } else {
                req.setAttribute("error-msg", "Error occured, fail to update!");
                req.getRequestDispatcher("../edit-product.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("../edit-product.jsp").forward(req, resp);
        }

    }

    HttpServletRequest validateProductData(HttpServletRequest req) {
        int pid;
        try {
            pid = Integer.parseInt(req.getParameter("txtProductID"));
        } catch (Exception e) {
            pid = 0;
        }
        String ProductName = req.getParameter("txtProductName").trim();
        String QuantityPerUnit = req.getParameter("txtQuantityPerUnit").trim();
        String CategoryID = req.getParameter("category").trim();
        String UnitPrice = (req.getParameter("txtUnitPrice")).trim();
        String UnitsInStock = (req.getParameter("txtUnitsInStock")).trim();
        int UnitsOnOrder;
        try {
            UnitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
        } catch (Exception e) {
            UnitsOnOrder = 0;
        }
        String ReorderLevel = (req.getParameter("txtReorderLevel")).trim();
        boolean Discontinued = req.getParameter("chkDiscontinued") == null ? false : true;

        req.setAttribute("ProductID", pid);
        req.setAttribute("ProductName", ProductName);
        req.setAttribute("CategoryID", CategoryID);
        req.setAttribute("QuantityPerUnit", QuantityPerUnit);
        req.setAttribute("UnitPrice", UnitPrice);
        req.setAttribute("UnitsOnOrder", UnitsOnOrder);
        req.setAttribute("UnitsInStock", UnitsInStock);
        req.setAttribute("ReorderLevel", ReorderLevel);
        req.setAttribute("Discontinued", Discontinued);
        req.setAttribute("categoryList", new CategoryDAO().getCategory());

        if (!ProductName.isEmpty() && !QuantityPerUnit.isEmpty() && UnitPrice.matches("[0-9]+(\\.){0,1}[0-9]*") && UnitsInStock.matches("[0-9]+")
                && ReorderLevel.matches("[0-9]+")) {
            Product p = new Product(pid, ProductName, Integer.parseInt(CategoryID), QuantityPerUnit, Double.parseDouble(UnitPrice), Integer.parseInt(UnitsInStock), UnitsOnOrder, Integer.parseInt(ReorderLevel), Discontinued);
            req.setAttribute("product", p);
            req.setAttribute("status", true);
        } else {
            if (ProductName.isEmpty()) {
                req.setAttribute("emptyName", "ProductName required!");
            }

            if (QuantityPerUnit.isEmpty()) {
                req.setAttribute("emptyQuantityPerUnit", "QuantityPerUnit required!");
            }

            if (UnitPrice.isEmpty()) {
                req.setAttribute("emptyUnitPrice", "UnitPrice required!");
            }

            if (UnitsInStock.isEmpty()) {
                req.setAttribute("emptyUnitsInStock", "UnitsInStock required!");
            }

            if (ReorderLevel.isEmpty()) {
                req.setAttribute("emptyReorderLevel", "ReorderLevel required!");
            }

            if (!UnitsInStock.matches("[0-9]+")) {
                req.setAttribute("invalidUnitsInStock", "Invalid number format!");
            }

            if (!ReorderLevel.matches("[0-9]+")) {
                req.setAttribute("invalidReorderLevel", "Invalid number format!");
            }

            if (!UnitPrice.matches("[0-9]+(\\.){0,1}[0-9]*")) {
                req.setAttribute("invalidUnitPrice", "Invalid number format!");
            }

            req.setAttribute("status", false);
        }
        return req;
    }
}
