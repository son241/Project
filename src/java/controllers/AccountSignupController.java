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
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import models.AccountDAO;
import models.CustomerDAO;

/**
 *
 * @author Admin
 */
public class AccountSignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String companyName = req.getParameter("company-name").trim();
        String contactName = req.getParameter("contact-name").trim();
        String contactTitle = req.getParameter("contact-title").trim();
        String address = req.getParameter("address").trim();
        String email = req.getParameter("email").trim();
        String pass = req.getParameter("pass").trim();
        String rePass = req.getParameter("re-pass").trim();

        req.setAttribute("companyName", companyName);
        req.setAttribute("contactName", contactName);
        req.setAttribute("contactTitle", contactTitle);
        req.setAttribute("address", address);
        req.setAttribute("email", email);
        req.setAttribute("pass", pass);
        req.setAttribute("rePass", rePass);

        String customerId = "";
        customerId += getRandomChar(companyName) + getRandomChar(contactName) + getRandomChar(contactTitle) + getRandomChar(companyName) + getRandomChar(contactTitle);

        if ((!companyName.isEmpty() && !contactName.isEmpty() && !contactTitle.isEmpty() && !address.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !rePass.isEmpty())
                && pass.equals(rePass) && !isExisted(email) && email.matches("[a-zA-z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}")) {
            Customer c = new Customer(customerId.toUpperCase(), companyName, contactName, contactTitle, address, Date.valueOf(LocalDate.now()));
            Account acc = new Account(email, pass, customerId, 0, 2);          
            int addCustomer = new CustomerDAO().addCustomer(c);
            int addAccount = new AccountDAO().addAccount(acc);
            if ( addCustomer!= 0 && addAccount != 0) {
                req.setAttribute("sucess-msg", "Account sginup successfully!!!");

                req.removeAttribute("companyName");
                req.removeAttribute("contactName");
                req.removeAttribute("contactTitle");
                req.removeAttribute("address");
                req.removeAttribute("email");
                req.removeAttribute("pass");
                req.removeAttribute("rePass");

                req.setAttribute("username", contactName);
                req.setAttribute("txtEmail", email);
                req.setAttribute("txtPass", pass);
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            }else{
                req.setAttribute("error-msg", "Error occured");
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            }
        } else {
            if (!email.matches("[a-zA-z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}")) {
                req.setAttribute("error-email-format", "Invalid email format!");
            }
            if (!pass.equals(rePass)) {
                req.removeAttribute("rePass");
                req.setAttribute("error-re-pass-msg", "Re-password does not match!");

            }
            if (isExisted(email)) {
                req.setAttribute("existed-email-msg", "This email was registered before!");
            }

            if (companyName.isEmpty()) {
                req.setAttribute("empty-companyName", "yes");
            }

            if (contactName.isEmpty()) {
                req.setAttribute("empty-contactName", "yes");
            }

            if (contactTitle.isEmpty()) {
                req.setAttribute("empty-contactTitle", "yes");
            }

            if (address.isEmpty()) {
                req.setAttribute("empty-address", "yes");
            }

            if (email.isEmpty()) {
                req.setAttribute("empty-email", "yes");
            }

            if (pass.isEmpty()) {
                req.setAttribute("empty-pass", "yes");
            }

            if (rePass.isEmpty()) {
                req.setAttribute("empty-rePass", "yes");
            }
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("../index.jsp");
        }
    }

    public boolean isExisted(String email) {
        ArrayList<Account> list = new AccountDAO().getAccountList();
        for (Account item : list) {
            if (item.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public String getRandomChar(String text) {
        String n = "";
        if (text.isEmpty()) {
            return "";
        }
        int random;
        do {
            random = ThreadLocalRandom.current().nextInt(0, text.length() - 1);
            n = String.valueOf(text.charAt(random));
        } while (n.equals(" "));

        return n.toUpperCase()  ;
    }

    public static void main(String[] args) {
        String str = "";
        str = String.valueOf(null);
        System.out.println(str);
    }

}
