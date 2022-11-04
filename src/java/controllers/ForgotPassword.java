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
import java.util.Properties;
import models.AccountDAO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        req.setAttribute("email", email);
        if (!isExistedEmail(email)) {
            req.setAttribute("status", false);
            req.getRequestDispatcher("../forgot.jsp").forward(req, resp);
        } else {

            String host = "localhost";
            String result;
            // Recipient's email ID needs to be mentioned.
            String to = email;

            // Sender's email ID needs to be mentioned
            String from = "SonNV";

            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host);

            // Get the default Session object.
            Session mailSession = Session.getDefaultInstance(properties);

            try {
                Properties prop = new Properties();
                prop.put("mail.smtp.auth", true);
                prop.put("mail.smtp.starttls.enable", "true");
                prop.put("mail.smtp.host", "smtp.mailtrap.io");
                prop.put("mail.smtp.port", "25");
                prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(mailSession);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Now set the actual message
                message.setText("This is actual message");

                // Send message
                Transport.send(message);
                result = "Sent message successfully....";
            } catch (MessagingException mex) {
                mex.printStackTrace();
                result = "Error: unable to send message....";
            }
            resp.getWriter().print(result);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../forgot.jsp").forward(req, resp);
    }

    boolean isExistedEmail(String email) {
        ArrayList<Account> accountList = new AccountDAO().getAccountList();
        for (Account item : accountList) {
            if (item.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

}
