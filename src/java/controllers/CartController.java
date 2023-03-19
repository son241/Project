/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.CartItem;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.ProductDAO;

/**
 *
 * @author Admin
 */
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CartItem> cart = (ArrayList<CartItem>) req.getSession().getAttribute("CartSession");
        int productID = Integer.parseInt(req.getParameter("productID"));
        Product p = new ProductDAO().getProduct(productID);
        boolean buy = false;
        try {
            buy = Boolean.parseBoolean(req.getParameter("buy"));
        } catch (Exception e) {
            buy = false;
        }
        if (cart == null) {
            cart = new ArrayList<CartItem>();
            cart.add(new CartItem(p, 1));
            req.setAttribute("add-cart-status", true);
            req.getSession().setAttribute("CartSession", cart);
        } else {
            if (!isExisted(cart, p)) {
                cart.add(new CartItem(p, 1));
            } else {
                for (CartItem item : cart) {
                    if (item.getP().getProductID() == p.getProductID()) {
                        int oldQuantity = item.getQuantity();
                        item.setQuantity(oldQuantity + 1);
                        break;
                    }
                }
            }
            req.getSession().setAttribute("CartSession", cart);
            req.setAttribute("add-cart-status", true);
        }

        req.setAttribute("p", p);
        if (!buy) {
            req.getRequestDispatcher("../detail.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("../cart.jsp").forward(req, resp);
        }
    }

    boolean isExisted(ArrayList<CartItem> cart, Product p) {
        for (CartItem item : cart) {
            if (item.getP().getProductID() == p.getProductID()) {
                return true;
            }
        }
        return false;
    }

}
