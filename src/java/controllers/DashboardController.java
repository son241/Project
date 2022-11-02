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
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {
            int totalGuest = (int) new OrderDAO().getDataNumber("select Count(*) from Customers\n"
                    + "where CustomerID not in (select CustomerID from Accounts where CustomerID is not null)");

            int totalCustomer = (int) new OrderDAO().getDataNumber("select count(*) from Accounts\n"
                    + "where CustomerID is not null");

            int newCustomer = (int) new OrderDAO().getDataNumber("select Count(*) from Customers\n"
                    + "where CreatedDate <= GETDATE() and CreatedDate >= GETDATE() - 30");

            int totalCusGuest = (int) new OrderDAO().getDataNumber("select count(*) from Customers");

            double totalOrder = new OrderDAO().getDataNumber("select ROUND(sum(a.TotalAmount), 2)\n"
                    + "from (select o.OrderID, sum(od.UnitPrice * od.Quantity - od.UnitPrice * od.Quantity * od.Discount) as TotalAmount\n"
                    + "from Orders o, [Order Details] od\n"
                    + "where o.OrderID = od.OrderID\n"
                    + "group by o.OrderID) as a");

            double weeklySale = new OrderDAO().getDataNumber("select ROUND(sum(a.TotalAmount), 2)\n"
                    + "from (select o.OrderID, sum(od.UnitPrice * od.Quantity - od.UnitPrice * od.Quantity * od.Discount) as TotalAmount, o.OrderDate\n"
                    + "from Orders o, [Order Details] od\n"
                    + "where o.OrderID = od.OrderID and OrderDate >= GETDATE() - 7 and OrderDate <= GETDATE()\n"
                    + "group by o.OrderID, o.OrderDate) as a");

            double[] monthSale = new double[12];

            for (int i = 0; i < monthSale.length; i++) {
                try {
                    monthSale[i] = new OrderDAO().getDataNumber("select ROUND(sum(a.TotalAmount), 2)\n"
                            + "from (select o.OrderID, sum(od.UnitPrice * od.Quantity - od.UnitPrice * od.Quantity * od.Discount) as TotalAmount, o.OrderDate\n"
                            + "from Orders o, [Order Details] od\n"
                            + "where o.OrderID = od.OrderID and MONTH(OrderDate) = " + (i + 1) + " and Year(OrderDate) = Year(GETDATE())\n"
                            + "group by o.OrderID, o.OrderDate) as a");
                } catch (Exception e) {
                    monthSale[i] = 0;
                }
            }

            req.setAttribute("totalCustomer", totalCustomer);
            req.setAttribute("totalGuest", totalCusGuest);
            req.setAttribute("newCustomer", newCustomer);
            req.setAttribute("totalCusGuest", totalCusGuest);
            req.setAttribute("totalOrder", totalOrder);
            req.setAttribute("weeklySale", weeklySale);
            req.setAttribute("monthSale", monthSale);
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        }else{
            resp.sendError(401);
        }
    }

}
