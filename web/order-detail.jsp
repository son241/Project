<%@include file="./template/admin-header.jsp" %>

<%
    if(request.getSession().getAttribute("AccSession") != null && ((Account)request.getSession().getAttribute("AccSession")).getRole() == 1){
        Order o = new OrderDAO().getOrder(Integer.parseInt(request.getParameter("id")));
        ArrayList<OrderDetail> ods = new OrderDetailDAO().getOrderDetails(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("o", o);
        request.setAttribute("ods", ods);
        request.setAttribute("ProductDAO", new ProductDAO());
    }
%>
<div id="content-right">
    <div class="path-admin">ORDER DETAIL</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div>
                <div class="profile-order-title">
                    <div class="profile-order-title-left">
                        <div>OrderID: <p style="color: blue; display: inline-block; margin-left: 4px">#${o.getOrderID()}</p></div>
                        <div>Order creation date: ${o.getOrderDate()}</div>
                    </div>


                    <div class="profile-order-title-right">
                        <c:choose>
                            <c:when test="${o.getShippedDate() ne null}">
                                <span style="color: green;">Completed</span>
                            </c:when>
                            <c:when test="${o.getShippedDate() eq null and o.getRequiredDate() ne null}">
                                <span style="color: blue;">Pending</span>                               
                            </c:when>
                            <c:when test="${o.getRequiredDate() eq null}">
                                <span style="color: red;">Canceled</span>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
                <c:forEach items="${ods}" var="od">
                    <div class="profile-order-content" style="background-color: white;">
                        <div class="profile-order-content-col1">
                            <a href="detail.html"><img src="img/2.jpg" width="100%"/></a>
                        </div>
                        <div class="profile-order-content-col2">${ProductDAO.getProduct(od.getProductID()).getProductName()}</div>
                        <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                        <div class="profile-order-content-col4">${od.getUnitPrice()} $</div>
                    </div>
                </c:forEach>         

            </div>
        </div>
    </div>
</div>
</div>
<%@include file="./template/admin-footer.jsp" %>