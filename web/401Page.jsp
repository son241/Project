<%-- 
    Document   : errorPage
    Created on : Oct 24, 2022, 11:37:42 PM
    Author     : Admin
--%>
<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            /*======================
                404 page
            =======================*/

            .page_404{
                padding:40px 0;
                background:#fff;
                font-family: cursive;
            }

            .page_404  img{
                width:100%;
            }

            .four_zero_four_bg{

                background-image: url(https://cdn.dribbble.com/users/285475/screenshots/2083086/dribbble_1.gif);
                height: 400px;
                background-position: center;
                background-repeat: no-repeat;
            }


            .four_zero_four_bg h1{
                font-size:80px;
                text-align: center;
            }

            .four_zero_four_bg h3{
                font-size:80px;
            }

            .link_404{
                color: #fff!important;
                padding: 10px 20px;
                background: #39ac31;
                margin: 20px 0;
                display: inline-block;
            }
            .contant_box_404{
                margin-top:-50px;
                text-align: center;
            }

            .contant_box_404 a{
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <%
    ErrorData ed = null;
    boolean handled = false; // Set to true after handling the error
    
    // Get the PageContext
    if(pageContext != null) {  
        // Get the ErrorData
         ed = null;
        
        try {
            ed = pageContext.getErrorData();           
        } catch(NullPointerException ne) {
            // If the error page was accessed directly, a NullPointerException
            // is thrown at (PageContext.java:514).
            // Catch and ignore it... it effectively means we can't use the ErrorData
        }

        // Display error details for the user
        if(ed != null) {
            request.setAttribute("error-status",  ed.getStatusCode());
            handled = true;
    }
}
 %>
        <section class="page_404">
            <div class="container">
                <div class="row">	
                    <div class="col-sm-12 ">
                        <div class="col-sm-10 col-sm-offset-1  text-center">
                            <div class="four_zero_four_bg">
                                <h1 class="text-center "><%=ed.getStatusCode()%></h1>
    
                            </div>

                            <div class="contant_box_404">
                                <h3 class="h2">
                                    Look like you're not permited
                                </h3>
                                
                                <p>Access denied!</p>

                                <a href="<%=request.getContextPath()%>/index.jsp" class="link_404">Go to Home</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
