<%@include file="./template/header.jsp" %>
<%@page import="java.util.*" %>
<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="<%=request.getContextPath()%>/account/signup">SIGN UP</a></span>
            <span><a href="<%=request.getContextPath()%>/account/signin" style="color: red;">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <%
                String email = (String)request.getAttribute("email");
                String pass = (String)request.getAttribute("pass");
                String errorMsg = (String)request.getAttribute("error-msg");
                if(errorMsg != null){   
            %>
            <div style="color: red; text-align: center"><p><%=errorMsg%></p></div>
                    <%}%>
            <form action="signin" method="post">
                <label for="email">Email<span style="color: red;">*</span></label><br/>
                <input type="text" id="email" name="txtEmail" value="${email}"/><br/>

                <%if((String)request.getAttribute("empty-email") != null){%>
                    <span class="msg-error">Email is required</span><br/>
                <%}%>

                <label for="pass">Password<span style="color: red;">*</span></label><br/>
                <input type="password" id="pass" name="txtPass" value="${pass}"/><br/>
                
                <%if((String)request.getAttribute("empty-pass") != null){%>
                    <span class="msg-error">Password is required</span><br/>
                <%}%>
                
                <div><a href="<%=path%>/account/forgot">Forgot password?</a></div>
                <input type="submit" value="SIGN IN" /><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>

<%@include file="./template/footer.jsp" %>