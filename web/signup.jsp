<%@include file="./template/header.jsp" %>
<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="signup" style="color: red;">SIGN UP</a></span>
            <span><a href="signin">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <%
                if(request.getAttribute("sucess-msg") != null){
            %>
            <div><h1 style="color: red">Successfully</h1></div>
            <%
            }
            %>
            <form action="signup" method="post">
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input type="text" name="company-name" value="${companyName}"/><br/>
                <span class="msg-error"><%if(request.getAttribute("empty-companyName")!=null){out.println("Company name is required");}%></span><br/>

                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input type="text" name="contact-name" value="${contactName}"/><br/>
                <span class="msg-error"><%if(request.getAttribute("empty-contactName")!=null){out.println("Contact name is required");}%></span><br/>

                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input type="text" name="contact-title" value="${contactTitle}"/><br/>
                <span class="msg-error"><%if(request.getAttribute("empty-contactTitle")!=null){out.println("Contact title required");}%></span><br/>

                <label>Address<span style="color: red;">*</span></label><br/>
                <input type="text" name="address" value="${address}"/><br/>
                <span class="msg-error"><%if(request.getAttribute("empty-address")!=null){out.println("Address is required");}%></span><br/>

                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="email" value="${email}"/><br/>
                <span class="msg-error">
                    <%
                        if(request.getAttribute("empty-email")!=null){
                            out.println("Email is required");
                        }
                        if(request.getAttribute("empty-email")==null && request.getAttribute("existed-email-msg")!=null){
                            out.println(request.getAttribute("existed-email-msg"));
                        }
                        if(request.getAttribute("error-email-format") != null && request.getAttribute("existed-email-msg")==null && request.getAttribute("empty-email")==null){
                            out.println(request.getAttribute("error-email-format"));
                        }
                    %>
                </span><br/>

                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="pass" value="${pass}"/><br/>
                <span class="msg-error"><%if(request.getAttribute("empty-pass")!=null){out.println("Password is required");}%></span><br/>

                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="re-pass" value="${rePass}"/><br/>
                <span class="msg-error">
                    <%
                    if(request.getAttribute("empty-rePass")!=null){
                        out.println("Re-Password is required");
                    };
                    
                    if(request.getAttribute("error-re-pass-msg")!=null && request.getAttribute("empty-rePass")== null){
                        out.println(request.getAttribute("error-re-pass-msg"));
                    };
                    %>
                </span><br/>

                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>
            <%if(request.getAttribute("sucess-msg") != null){%>
            <div class="success-signup overlay">
                <div class="success-signup-content">
                    <p><strong>Success! </strong>welcome<br> <%=request.getAttribute("username")%>.<br/></p>

                    <button class="visit-shop"><a href="<%=request.getContextPath()%>/account/signin?txtEmail=<%=request.getAttribute("txtEmail")%>&txtPass=<%=request.getAttribute("txtPass")%>">Go To Shop</a></button>
                </div>
            </div>  
            <%}%>
            <%if(request.getAttribute("error-msg") != null){%>
            <div class="fail-signup overlay">
                <div class="fail-signup-content">
                    <p><strong>Fail! </strong><%=request.getAttribute("error-msg")%>.<br/></p>

                    <button class="close-error"><a>Close</a></button>
                </div>
            </div>  
            <%}%>
        </div>
    </div>
</div>
<script>
    var overlay = document.querySelector(".overlay");
    var modalSuccess = document.querySelector(".success-signup-content");
    var modalFail = document.querySelector(".fail-signup-content");
    var closeFail = document.querySelector("close-error");
    overlay.onclick = function () {
        overlay.style.display = "none";
//        console.log(overlay);
    };
    modalSuccess.onclick = function (e) {
        e.stopPropagation();
    };
    
    modalFail.onclick = function (e) {
        e.stopPropagation();
    };
    
    closeFail.onclick = function (e) {
        overlay.style.display = "none";
    };
</script>
<%@include file="./template/footer.jsp" %>