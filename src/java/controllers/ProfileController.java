/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.CustomerDAO;

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = ((Account)req.getSession().getAttribute("AccSession")).getCustomerID();
        Customer c = new CustomerDAO().getCustomer(customerID);
        req.setAttribute("customer", c);
        req.getRequestDispatcher("../profile.jsp").forward(req, resp);
        Customer c1 = new CustomerDAO().getCustomer(((Account)req.getSession().getAttribute("AccSession")).getCustomerID());
    }
    
}
