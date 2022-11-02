<%@page import="DAL.*" %>
<%@page import="java.util.*" %>
<%@page import="models.*" %>
<%@page errorPage="/errorPage.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
        <title>Index</title>
        <%
            String path = request.getContextPath();
            request.setAttribute("path", path);
        %>
        <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .search {
                width: 100%;
                position: relative;
                display: flex;
            }

            .searchTerm {
                width: 100%;
                border: 3px solid #e17542;
                border-right: none;
                padding: 5px;
                height: 20px;
                border-radius: 5px 0 0 5px;
                outline: none;
                color: #e17542;
            }

            .searchTerm:focus{
                color: #e17542;
            }

            .searchButton {
                width: 40px;
                height: 36px;
                border: 1px solid #e17542;
                background: #e17542;
                text-align: center;
                color: #fff;
                border-radius: 0 5px 5px 0;
                cursor: pointer;
                font-size: 20px;
            }

            /*Resize the wrap to see the search bar change!*/
            .wrap{
                width: 30%;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
    </head>
    <body lang="vi">
        <c:set var="path" value="${path}" scope="application"/>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=path%>/index.jsp"><img src="<%=request.getContextPath()%>/img/logo.png"/></a>
                </div>         
                <form action="<%=path%>/index.jsp">
                    <div class="wrap">
                        <div class="search">
                            <input type="text" class="searchTerm" name="txtSearch" placeholder="What are you looking for?">

                            <input style="color: white" type="submit" class="searchButton" value="&#128270">
                        </div>
                    </div>
                </form>
                <div id="banner">
                    <ul>
                        <c:choose>
                            <c:when test="${sessionScope.CartSession ne null}">
                                <li><a href="<%=path%>/cart.jsp">Cart: <%=((ArrayList<Product>)request.getSession().getAttribute("CartSession")).size()%></a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="<%=path%>/cart.jsp">Cart: 0</a></li>
                                </c:otherwise>
                            </c:choose>

                        <%
                            Account acc = (Account)request.getSession().getAttribute("AccSession");
                            if (acc == null){
                        %>                                                       
                        <li><a href="<%=request.getContextPath()%>/account/signin">SignIn</a></li>
                        <li><a href="<%=request.getContextPath()%>/account/signup">SignUp</a></li>
                            <%}%>

                        <%
                            if (acc != null){
                        %>
                        <%if(acc.getRole() == 1){%>
                        <li><a href="${applicationScope.path}/product/manage">Product</a></li>
                            <%}%>
                        <li><a href="<%=request.getContextPath()%>/account/profile">Profile</a></li>
                        <li><a href="<%=request.getContextPath()%>/account/signin">signOut</a></li>
                            <%}%>

                    </ul>
                </div>
            </div>
