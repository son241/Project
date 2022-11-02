/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.OrderDAO;

/**
 *
 * @author Admin
 */
public class CancelOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("order-id"));
        int role = ((Account)req.getSession().getAttribute("AccSession")).getRole();
        String sql = "update Orders\n"
                + "set RequiredDate = null\n"
                + "where OrderID = " + orderID;

        if (new OrderDAO().updateOrder(sql) != 0) {
            if (role == 1) {
                resp.sendRedirect(req.getContextPath() + "/order/manage?page=" + req.getParameter("page") + "&txtStartOrderDate="+req.getParameter("txtStartOrderDate")+"&txtEndOrderDate="+req.getParameter("txtEndOrderDate"));
            } else {
                resp.sendRedirect("../profile2.jsp");
            }
        }
    }

//    Order getOrderByID(int id){
//        ArrayList<Order> orderList = new ArrayList<Order>();
//    }
}
