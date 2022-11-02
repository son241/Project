/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CustomerDAO;
import models.OrderDAO;
import models.OrderDetailDAO;

/**
 *
 * @author Admin
 */
public class CartAction extends HttpServlet {

    boolean add;
    boolean sub;
    boolean remove;
    boolean order;
    ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("CartSession") != null && ((ArrayList<CartItem>) req.getSession().getAttribute("CartSession")).size() > 0) {
            cart = (ArrayList<CartItem>) req.getSession().getAttribute("CartSession");
            add = getBooleanValue(req.getParameter("add"));
            sub = getBooleanValue(req.getParameter("sub"));
            remove = getBooleanValue(req.getParameter("remove"));
            order = getBooleanValue(req.getParameter("order"));

            if (add || sub) {
                changeQuantity(req, resp);
            }

            if (remove) {
                removeItem(req, resp);
            }

            if (order) {
                try {
                    submitOrder(req, resp);
                } catch (Exception ex) {
                    Logger.getLogger(CartAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            resp.sendRedirect("../index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getSession().getAttribute("CartSession") != null) {
                submitOrder(req, resp);
            } else {
                resp.sendRedirect("../index.jsp");
            }
        } catch (Exception e) {
//            resp.sendError(500);
        }
    }

    boolean getBooleanValue(String value) {
        boolean flag = false;
        try {
            flag = Boolean.parseBoolean(value);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    private void changeQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("productID"));

        for (CartItem item : cart) {
            if (item.getP().getProductID() == pid) {
                if (add) {
                    item.setQuantity(item.getQuantity() + 1);
                    break;
                }

                if (sub) {
                    item.setQuantity(item.getQuantity() - 1);
                    if (item.getQuantity() == 0) {
                        removeItem(pid);
                        break;
                    }
                }
            }
        }

        req.getSession().setAttribute("CartSession", cart);
        req.getRequestDispatcher("../cart.jsp").forward(req, resp);
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("productID"));
        removeItem(pid);
        req.getSession().setAttribute("CartSession", cart);
        req.getRequestDispatcher("../cart.jsp").forward(req, resp);
    }

    private void removeItem(int pid) {
        for (CartItem item : cart) {
            if (item.getP().getProductID() == pid) {
                cart.remove(cart.indexOf(item));
                return;
            }
        }
    }

    private void submitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = validateInputData(req, resp);
        cart = (ArrayList<CartItem>) req.getSession().getAttribute("CartSession");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        String CompanyName = ((String) req.getAttribute("CompanyName"));
        String ContactName = ((String) req.getAttribute("ContactName"));
        String ContactTitle = ((String) req.getAttribute("ContactTitle"));
        String Address = ((String) req.getAttribute("Address"));
        String RequiredDate = ((String) req.getAttribute("RequiredDate"));

        Account acc = (Account) req.getSession().getAttribute("AccSession");
        Order o = null;
        Customer c = null;

        if (acc != null) {
            c = new CustomerDAO().getCustomer(acc.getCustomerID());
            c.setAddress(Address);
            o = new Order(0, acc.getCustomerID(), Date.valueOf(LocalDate.now()), Date.valueOf(RequiredDate), c.getAddress());
        } else {
            do {
                c = new Customer(getRandomString(0), CompanyName, ContactName, ContactTitle, Address, Date.valueOf(LocalDate.now()));
            } while (new CustomerDAO().addCustomer(c) == 0);
            o = new Order(0, c.getCustomerID(), Date.valueOf(LocalDate.now()), Date.valueOf(RequiredDate), Address);
        }

        boolean success = false;
        o = new OrderDAO().addOrder(o);
        if (o != null) {
            ArrayList<OrderDetail> OrderDetailList = new ArrayList<OrderDetail>();
            for (CartItem item : cart) {
                OrderDetail od = new OrderDetail(o.getOrderID(), item.getP().getProductID(), item.getP().getUnitPrice(), item.getQuantity(), 0);
                item.setUnitPrice(od.getQuantity() * od.getUnitPrice() - od.getQuantity() * od.getUnitPrice() * od.getDiscount());
                OrderDetailList.add(od);
            }

            for (OrderDetail item : OrderDetailList) {
                success = addOrderDetail(item);
            }
        }

        if (success) {
            req.getSession().removeAttribute("CartSession");
            req.setAttribute("success-msg", "Successfull Ordering");
            if (acc != null) {
                req.getRequestDispatcher("../profile1.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("../index.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("fail-msg", "Fail, error occured");
            req.getRequestDispatcher("../cart.jsp").forward(req, resp);
        }
    }

    public String getRandomString(int length) {
        String text = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String output = "";
        for (int i = 1; i <= 5; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, text.length() - 1);
            String n = String.valueOf(text.charAt(random));
            output += n;
        }

        return output;
    }

    private boolean addOrderDetail(OrderDetail od) {
        int i = 0;
        i = new OrderDetailDAO().addOrderDetail(od);

        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    private double getTotalAmountOrder(ArrayList<OrderDetail> odList) {
        double total = 0;
        for (OrderDetail item : odList) {
            total += (item.getQuantity() * item.getUnitPrice()) - (item.getQuantity() * item.getUnitPrice() * item.getDiscount());
        }
        return total;
    }

    String getString(String value) {
        String s = "";
        try {
            s = value;
        } catch (Exception e) {
            s = "";
        }
        return s;
    }

    private HttpServletRequest validateInputData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CompanyName = req.getParameter("txtCompanyName").trim();
        String ContactName = req.getParameter("txtContactName").trim();
        String ContactTitle = req.getParameter("txtContactTitle").trim();
        String Address = req.getParameter("txtAddress").trim();
        String RequiredDate = req.getParameter("txtRequiredDate").trim();

        req.setAttribute("CompanyName", CompanyName);
        req.setAttribute("ContactName", ContactName);
        req.setAttribute("ContactTitle", ContactTitle);
        req.setAttribute("Address", Address);
        req.setAttribute("RequiredDate", RequiredDate);

        if (CompanyName.isEmpty() || ContactName.isEmpty() || ContactTitle.isEmpty() || Address.isEmpty()
                || RequiredDate.isEmpty() || !Date.valueOf(RequiredDate).after(Date.valueOf(LocalDate.now()))) {
            if (CompanyName.isEmpty()) {
                req.setAttribute("emptyCompanyName", "CompanyName required");
            }

            if (ContactName.isEmpty()) {
                req.setAttribute("emptyContactName", "ContactName required");
            }

            if (ContactTitle.isEmpty()) {
                req.setAttribute("emptyContactTitle", "ContactTitle required");
            }

            if (Address.isEmpty()) {
                req.setAttribute("emptyAddress", "Address required");
            }

            if (RequiredDate.isEmpty()) {
                req.setAttribute("emptyRequiredDate", "RequiredDate required");
            }

            if (!RequiredDate.isEmpty()) {
                if (!Date.valueOf(RequiredDate).after(Date.valueOf(LocalDate.now()))) {
                    req.setAttribute("invalidRequiredDate", "Invalid RequiredDate");
                }
            }

            req.getRequestDispatcher("../cart.jsp").forward(req, resp);
            return null;
        }
        return req;
    }

}
