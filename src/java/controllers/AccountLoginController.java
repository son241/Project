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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import models.AccountDAO;

/**
 *
 * @author Admin
 */
public class AccountLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy dữ liệu từ 'login' form chuyển lên
        String email = req.getParameter("txtEmail").trim();
        String pass = req.getParameter("txtPass").trim();

        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("pass", pass);
        Account acc = new AccountDAO().getAccount(email, pass);
        if (acc != null) {
            // Cấp Session 

            req.getSession().setAttribute("AccSession", acc);
            req.getSession().removeAttribute("CartSession");
            req.getSession().removeAttribute("email");
            req.getSession().removeAttribute("pass");
            //Điều hướng tới index.jsp
            if (acc.getRole() == 2) {
                resp.sendRedirect("../index.jsp");
            }else{
                resp.sendRedirect(req.getContextPath()+"/product/manage");
            }
        } else if (!email.equals("") && !pass.equals("")) {
            //Đóng gói thống điệp lỗi về doGet (Login.jsp)
            req.setAttribute("error-msg", "This account does not exist!");
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            if (email.equals("")) {
                req.setAttribute("empty-email", "yes");
            }

            if (pass.equals("")) {
                req.setAttribute("empty-pass", "yes");
            }

            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Chuyển tiếp yêu cầu của người dùng sang 'login.jsp'
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("CartSession");
            req.getSession().removeAttribute("AccSession");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            String email = req.getParameter("txtEmail");
            String pass = req.getParameter("txtPass");

            if (email != null && pass != null) {
                doPost(req, resp);
            } else {
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }

}
