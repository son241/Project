<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>
<%@page import="DAL.*" %>
<%@page import="controllers.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <link href="${applicationScope.path}/css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <%request.setAttribute("path", request.getContextPath());%>
        <c:set value="${path}" var="path" scope="application"/>
        <div id="container">
            <div id="header">
                <div id="logo-admin">
                    Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>
                        <li><a href="${applicationScope.path}/account/signin">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <a href="${applicationScope.path}/dashboard"><li>Dashboard</li></a>
                        <a href="${applicationScope.path}/order/manage"><li>Orders</li></a>
                        <a href="${applicationScope.path}/product/manage"><li>Products</li></a>
                        <a href="#"><li>Customers</li></a>
                    </ul>
                </div>
                <c:if test="${sessionScope.AccSession eq null or sessionScope.AccSession.getRole() ne 1}">
                    <%response.sendError(401);%>
                </c:if>