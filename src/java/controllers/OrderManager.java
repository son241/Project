/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import models.CustomerDAO;
import models.EmployeeDAO;
import models.MyGeneric;
import models.OrderDAO;

/**
 *
 * @author Admin
 */
public class OrderManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account)req.getSession().getAttribute("AccSession")).getRole() == 1) {
            String fromDate = req.getParameter("txtStartOrderDate");
            String toDate = req.getParameter("txtEndOrderDate");
            String sql;
            if (toDate == null) {
                toDate = Date.valueOf(LocalDate.now()).toString();
            }
            int pageTh;
            try {
                pageTh = Integer.parseInt(req.getParameter("page"));
            } catch (Exception e) {
                pageTh = 1;
            }

            if (fromDate != null) {
                if (!fromDate.isEmpty()) {
                    sql = "select * from Orders\n"
                            + "where OrderDate between '" + fromDate + "' and '" + toDate + "' ";
                } else {
                    sql = "select * from Orders order by OrderDate desc";
                }
            } else {
                sql = "select * from Orders order by OrderDate desc";
            }

            ArrayList<Order> allItem = new OrderDAO().getOrdersBySqlQurey(sql);
            ArrayList<Order> list = new MyGeneric<Order>(allItem).dataPaging(pageTh, 5);
            int numberOfPage = allItem.size() % 5 == 0 ? allItem.size() / 5 : allItem.size() / 5 + 1;
            req.setAttribute("list", list);
            req.setAttribute("fromDate", fromDate);
            req.setAttribute("toDate", toDate);
            req.setAttribute("currentPage", pageTh);
            req.setAttribute("numberOfPage", numberOfPage);
            req.setAttribute("EmployeeDAO", new EmployeeDAO());
            req.setAttribute("CustomerDAO", new CustomerDAO());
            req.getRequestDispatcher("../order.jsp").forward(req, resp);
        }else{
            resp.sendError(401);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
