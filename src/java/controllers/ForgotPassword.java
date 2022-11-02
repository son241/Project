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
import java.util.ArrayList;
import models.AccountDAO;
import javax.activation.*;

/**
 *
 * @author Admin
 */
public class ForgotPassword extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        req.setAttribute("email", email);
        if (!isExistedEmail(email)) {
            req.setAttribute("status", false);
            req.getRequestDispatcher("../forgot.jsp").forward(req, resp);
        }else{
//            Mailer 
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../forgot.jsp").forward(req, resp);
    }
    
    boolean isExistedEmail(String email){
        ArrayList<Account> accountList = new AccountDAO().getAccountList();
        for (Account item : accountList) {
            if (item.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
    
}
